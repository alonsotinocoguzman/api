package com.farenet.nodo.maestro.api.reporte.service;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumentoidentidad;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteestadoRepository;
import com.farenet.nodo.maestro.api.caja.repository.TipodocumentoidentidadRepository;
import com.farenet.nodo.maestro.api.caja.service.DescuentoService;
import com.farenet.nodo.maestro.api.callcenter.bean.SMSCAPI;
import com.farenet.nodo.maestro.api.callcenter.service.VentaCAPIService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.*;
import com.farenet.nodo.maestro.api.inspeccion.service.VehiculoService;
import com.farenet.nodo.maestro.api.reporte.bean.*;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.service.UsuarioService;
import com.farenet.nodo.maestro.api.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Luis Consideraciones a tener en cuenta en los reportes: Cuando se
 *         haga algun reporte que tenga que ver con calculos relacionados con el
 *         comprobante, no se tomara el criterio de inspeccionestado anulado
 */
@Service
@Transactional
public class ReporteService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CierreRepository cierreRepository;

    @Autowired
    private ComprobanteestadoRepository comprobanteEstadoRepository;

    @Autowired
    private InspeccionestadoRepository inspeccionEstadoRepository;

    @Autowired
    private ConceptoinspeccionRepository conceptoinspeccionRepository;

    @Autowired
    private TipodocumentoidentidadRepository tipodocumentoidentidadRepository;
    
    @Autowired
    private PlantaRepository plantaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VentaCAPIService ventaCapiService;


    public List<ReporteCierrecajaBean> getReporteCierrecaja(Cierre cierre, Linea linea) {
        List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierre.getPlanta());

        Timestamp fechaMax = Util.getFechMaxCierre(cierre);
        Timestamp fechaMin = Util.getFechMinCierre(cierresOrdenados, cierre);

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
        Tipodocumentoidentidad ruc = tipodocumentoidentidadRepository.findOneByKey("ruc");

        // Credito solo en empresas
        String hql1 = "SELECT new " + ReporteCierrecajaBean.class.getName()
        		+ "(fp.nombre,p.nombrerazonsocial,com.fechapago,CONCAT(per.nombres,' ',per.apellidos),com.placaMotor,"
                + "ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante,ce.nombre,com.nrooperacionBanco,vc.nombre,"
                + "sum(com.importetotal - com.igv), sum(com.igv),sum(com.importetotal))"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join ins.inspeccionestado ie"
        		+ " inner join com.formaPago fp"
        		+ " inner join com.cliente p"
        		+ " inner join com.comprobanteestado ce"
        		+ " inner join ins.vehiculo v"
        		+ " inner join v.vehiculoclase vc"
        		+ " inner join ins.usuariocreacion usu"
        		+ " inner join usu.persona per"
                + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax and (p.tipodocumentoidentidad = :tipodocumentoidentidad)  "
                + " and com.linea = :linea and com.comprobanteestado != :comprobanteestadoanulado"
                + " and fp.key = :fp_credito"
                + " group by fp.nombre,p.nombrerazonsocial, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),ins.placaMotor,ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante, ce.nombre, com.nrooperacionBanco,vc.nombre"
                + " order by fp.nombre";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechapagoMin", fechaMin);
        query.setParameter("fechapagoMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("linea", linea);
        query.setParameter("tipodocumentoidentidad", ruc);
        query.setParameter("fp_credito", "credito");
        List<ReporteCierrecajaBean> reporteCierrecajas1 = query.getResultList();

        // Deposito en banco con sus bancos si es que tuviera, y cualquier otra
        // forma de pago que no sea crédito
        String hql2 = "SELECT new " + ReporteCierrecajaBean.class.getName()
                + "(fp.nombre,ef.nombre, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),com.placaMotor,"
                + "ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante,ce.nombre,com.nrooperacionBanco,vc.nombre,"
                + "sum(com.importetotal - com.igv), sum(com.igv),sum(com.importetotal)) "
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join ins.inspeccionestado ie"
                + " inner join com.formaPago fp"
                + " inner join com.comprobanteestado ce"
        		+ " inner join ins.vehiculo v"
        		+ " left join com.entidadFinanciera ef"
        		+ " inner join v.vehiculoclase vc"
        		+ " inner join ins.usuariocreacion usu"
                + " inner join usu.persona per"
                + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax"
                + " and com.linea = :linea and com.comprobanteestado != :comprobanteestadoanulado"
                + " and fp.key != :fp_credito"
                + " group by fp.nombre, ef.nombre, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),ins.placaMotor,ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante, ce.nombre,com.nrooperacionBanco, vc.nombre"
                + " order by fp.nombre, ef.nombre";
        
        query = entityManager.createQuery(hql2);

        query.setParameter("fechapagoMin", fechaMin);
        query.setParameter("fechapagoMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("linea", linea);
        query.setParameter("fp_credito", "credito");

        List<ReporteCierrecajaBean> reporteCierrecajas2 = query.getResultList();

        reporteCierrecajas1.addAll(reporteCierrecajas2);

        return reporteCierrecajas1;
    }

    public List<ReporteCierrecajaBean> getReporteCierrecajaUsuario(Usuario usuario, String planta_key) {
        Timestamp fechaMax = Util.finDay();
        Timestamp fechaMin = Util.initDay();

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

        // Credito solo en empresas
        String hql1 = "SELECT new " + ReporteCierrecajaBean.class.getName()
                + "(fp.nombre,p.nombrerazonsocial, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),com.placaMotor"
                + ",ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante,ce.nombre,com.nrooperacionBanco,vc.nombre"
                + ",sum(com.importetotal - com.igv), sum(com.igv),sum(com.importetotal))"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join ins.inspeccionestado ie"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " inner join com.formaPago fp"
                + " inner join com.cliente p"
                + " inner join com.comprobanteestado ce"
                + " inner join ins.vehiculo v"
                + " inner join v.vehiculoclase vc"
                + " inner join ins.usuariocreacion usu"
                + " inner join usu.persona per"
                + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax and (p.nombrerazonsocial != '' or p.nombrerazonsocial != null)  "
                + " and com.comprobanteestado != :comprobanteestadoanulado"
                + " and fp.key = :fp_credito and ins.usuariocreacion = :usuario" + " and pl.key = :planta_key"
                + " group by fp.nombre,p.nombrerazonsocial, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),ins.placaMotor,ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante,ce.nombre,com.nrooperacionBanco,vc.nombre"
                + " order by fp.nombre";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechapagoMin", fechaMin);
        query.setParameter("fechapagoMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("usuario", usuario);
        query.setParameter("planta_key", planta_key);
        query.setParameter("fp_credito", "credito");
        List<ReporteCierrecajaBean> reporteCierrecajas1 = query.getResultList();

        // Deposito en banco con sus bancos si es que tuviera, y cualquier otra
        // forma de pago que no sea crédito
        String hql2 = "SELECT new " + ReporteCierrecajaBean.class.getName()
                + "(fp.nombre,ef.nombre,com.fechapago,CONCAT(per.nombres,' ',per.apellidos),com.placaMotor,"
                + "ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante,ce.nombre,com.nrooperacionBanco,vc.nombre,"
                + "sum(com.importetotal - com.igv), sum(com.igv),sum(com.importetotal))"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join ins.inspeccionestado ie"
                + " inner join com.linea li"
                + " inner join li.planta pl" 
                + " inner join com.comprobanteestado ce"
                + " inner join com.formaPago fp"
                + " inner join ins.vehiculo v"
                + " left join com.entidadFinanciera ef"
                + " inner join v.vehiculoclase vc"
                + " inner join ins.usuariocreacion usu"
                + " inner join usu.persona per"
                + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax"
                + " and com.comprobanteestado != :comprobanteestadoanulado"
                + " and fp.key != :fp_credito and ins.usuariocreacion = :usuario" + " and pl.key = :planta_key"
                + " group by fp.nombre, ef.nombre, com.fechapago,CONCAT(per.nombres,' ',per.apellidos),ins.placaMotor,ins.nrodocumentoinspeccion,ie.nombre,com.nrocomprobante, ce.nombre,com.nrooperacionBanco, vc.nombre"
                + " order by fp.nombre, ef.nombre";
        query = entityManager.createQuery(hql2);

        query.setParameter("fechapagoMin", fechaMin);
        query.setParameter("fechapagoMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("usuario", usuario);
        query.setParameter("planta_key", planta_key);
        query.setParameter("fp_credito", "credito");

        List<ReporteCierrecajaBean> reporteCierrecajas2 = query.getResultList();

        reporteCierrecajas1.addAll(reporteCierrecajas2);

        return reporteCierrecajas1;
    }

    public Double getSumImporteComprobantesActivos(String sedeKey, Date date) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(date);

        Calendar calendarInitYear = Calendar.getInstance();
        calendarInitYear.set(Calendar.YEAR, calendarCurrent.get(Calendar.YEAR));
        calendarInitYear.set(Calendar.MONTH, calendarCurrent.get(Calendar.MONTH));
        calendarInitYear.set(Calendar.DAY_OF_MONTH, 1);

        Timestamp fechaCurrent = new Timestamp(date.getTime());
        Timestamp fechaCurrentInitMonth = new Timestamp(calendarInitYear.getTime().getTime());

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
        
        String hql1 = "SELECT sum(com.importetotal)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join com.linea li"
        		+ " inner join li.planta pl"
        		+ " WHERE to_date(to_chart(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax "
        		+ "and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0 and pl.key = :sedeKey ";

        TypedQuery<Double> typedQueryCount = entityManager.createQuery(hql1, Double.class);

        typedQueryCount.setParameter("fechapagoMin", fechaCurrentInitMonth);
        typedQueryCount.setParameter("fechapagoMax", fechaCurrent);
        typedQueryCount.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        typedQueryCount.setParameter("sedeKey", sedeKey);
        Double sum = typedQueryCount.getSingleResult();
        return sum;
    }

    public Long getComprobantesActivos(String sedeKey, Date date) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(date);

        Calendar calendarInitYear = Calendar.getInstance();
        calendarInitYear.set(Calendar.YEAR, calendarCurrent.get(Calendar.YEAR));
        calendarInitYear.set(Calendar.MONTH, calendarCurrent.get(Calendar.MONTH));
        calendarInitYear.set(Calendar.DAY_OF_MONTH, 1);

        Timestamp fechaCurrent = new Timestamp(date.getTime());
        Timestamp fechaCurrentInitMonth = new Timestamp(calendarInitYear.getTime().getTime());

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        String hql1 = "SELECT count(distinct ins) "
                + "FROM Comprobante com com.inspeccion ins inner join com.linea li "
                + "inner join li.planta pl "
                + "WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax "
                + "and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal!=0 and pl.key = :sedeKey ";

        TypedQuery<Long> typedQueryCount = entityManager.createQuery(hql1, Long.class);

        typedQueryCount.setParameter("fechapagoMin", fechaCurrentInitMonth);
        typedQueryCount.setParameter("fechapagoMax", fechaCurrent);
        typedQueryCount.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        typedQueryCount.setParameter("sedeKey", sedeKey);
        Long cant = typedQueryCount.getSingleResult();
        return cant;
    }

    public List<ReporteConsolidadoInspeccionVentasBean> getReporteconsolidadoInspeccionVentasCurrentMonth(Date date) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);

        Calendar calendarInitYear = Calendar.getInstance();
        calendarInitYear.set(Calendar.YEAR, calendarCurrent.get(Calendar.YEAR));
        calendarInitYear.set(Calendar.MONTH, calendarCurrent.get(Calendar.MONTH));
        calendarInitYear.set(Calendar.DAY_OF_MONTH, 1);

        Timestamp fecha1AnioBack = new Timestamp(calendar.getTimeInMillis());
        Timestamp fechaCurrent = new Timestamp(date.getTime());
        Timestamp fechaCurrentInitMonth = new Timestamp(calendarInitYear.getTime().getTime());

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        String hql1 = "SELECT new " + ReporteConsolidadoInspeccionVentasBean.class.getName()
        		+ "(pl.nombre,0,0,pl.key,sum(ins.cantimpresiones),count(ins),sum(com.importetotal),0,0,0,0,0,0.0)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join com.linea li"
        		+ " inner join li.planta pl"
        		+ " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax "
                + "and com.comprobanteestado != :comprobanteestadoanulado group by pl.key ";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechapagoMin", fechaCurrentInitMonth);
        query.setParameter("fechapagoMax", fechaCurrent);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        List<ReporteConsolidadoInspeccionVentasBean> reportes = query.getResultList();

        calendar = Calendar.getInstance();
        int anioactual = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, -1);
        int anioant = calendar.get(Calendar.YEAR);
        for (ReporteConsolidadoInspeccionVentasBean reporte : reportes) {
            reporte.setAnioactual(anioactual);
            reporte.setAnioant(anioant);
            reporte.setP1_desaprobado(getPorcentajeDesaprobado(reporte.getSede_key(), fechaCurrentInitMonth,
                    fechaCurrent, comprobanteestadoAnulado, reporte.getProc()).intValue());
            reporte.setEa(getEA(reporte.getSede_key(), fecha1AnioBack, fechaCurrent, comprobanteestadoAnulado).intValue());
            reporte.setIngresos_anioant(getIngresosAnioAnterior(reporte.getSede_key(), fecha1AnioBack,
                    fechaCurrent, comprobanteestadoAnulado).intValue());
//            reporte.setEaDinero(getEADinero(reporte.getSede_key(), fecha1AnioBack, fechaCurrent, comprobanteestadoAnulado));
        }
        return reportes;
    }

    public List<ReporteConsolidadoInspeccionVentasBean> getReporteconsolidadoInspeccionVentas(Date date) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);

        Calendar calendarInitYear = Calendar.getInstance();
        calendarInitYear.set(Calendar.YEAR, calendarCurrent.get(Calendar.YEAR));
        calendarInitYear.set(Calendar.MONTH, 1);
        calendarInitYear.set(Calendar.DAY_OF_MONTH, 1);

        Timestamp fecha1AnioBack = new Timestamp(calendar.getTimeInMillis());
        Timestamp fechaCurrent = new Timestamp(date.getTime());
        Timestamp fechaCurrentInitYear = new Timestamp(calendarInitYear.getTime().getTime());

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        String hql1 = "SELECT new " + ReporteConsolidadoInspeccionVentasBean.class.getName()
        		+ "(pl.nombre,0,0,pl.key,0,count(ins),sum(com.importetotal),0.0,0,0,0,0,0.0)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join com.linea li"
                + " inner join li.planta pl"
                + " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax "
                + "and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0 group by pl.key ";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechapagoMin", fechaCurrent);
        query.setParameter("fechapagoMax", fechaCurrent);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        List<ReporteConsolidadoInspeccionVentasBean> reportes = query.getResultList();

        calendar = Calendar.getInstance();
        int anioactual = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, -1);
        int anioant = calendar.get(Calendar.YEAR);
        for (ReporteConsolidadoInspeccionVentasBean reporte : reportes) {
            reporte.setProc(getProc(reporte.getSede_key(), fechaCurrent, fechaCurrent).intValue());
            reporte.setAnioactual(anioactual);
            reporte.setAnioant(anioant);
            reporte.setP1_desaprobado(getPorcentajeDesaprobado(reporte.getSede_key(), fechaCurrent,
                    fechaCurrent, comprobanteestadoAnulado, reporte.getProc()));
           //reporte.setEa(getEA(reporte.getSede_key(), fecha1AnioBack, fechaCurrent, comprobanteestadoAnulado).intValue());
            reporte.setIngresos_anioant(getIngresosAnioAnterior(reporte.getSede_key(), fecha1AnioBack,
                    fechaCurrent, comprobanteestadoAnulado).intValue());
            //reporte.setEaDinero(getEADinero(reporte.getSede_key(), fecha1AnioBack, fechaCurrent, comprobanteestadoAnulado));
        }
        
        return reportes;
    }

    private Double getPorcentajeDesaprobado(String planta_key, Timestamp fechaMin, Timestamp fechaMax,
                                            Comprobanteestado comprobanteestadoAnulado, int inspTotal) {
    	String hql1 = " select count(ins)"
    			+ " FROM Comprobante com"
    			+ " inner join com.inspeccion ins"
    			+ " inner join com.linea li"
    			+ " inner join li.planta pl"
    			+ " WHERE pl.key = :planta_key"
    			+ " and to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and ins.resultado = 'D' " + " and com.comprobanteestado != :comprobanteestadoanulado";
        TypedQuery<Long> query = entityManager.createQuery(hql1, Long.class);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("planta_key", planta_key);
        Long number = query.getSingleResult();
        Double pDesaprobadoDouble = Double.valueOf((Double.valueOf(number) / Double.valueOf(inspTotal)) * 100);
        Double pDesaprobado = Util.round(pDesaprobadoDouble, 2);
        return pDesaprobado;
    }

    private Long getProc(String planta_key, Timestamp fechaMin, Timestamp fechaMax) {

        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");
        
        String hql1 = " select count(ins)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
                + " inner join com.linea linea"
                + " inner join linea.planta pl"
                + " where pl.key = :planta_key"
                + " and to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and ins.inspeccionestado != :inspeccionestadoAnulado";
        TypedQuery<Long> query = entityManager.createQuery(hql1, Long.class);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("inspeccionestadoAnulado", inspeccionestadoAnulado);
        query.setParameter("planta_key", planta_key);
        Long number = query.getSingleResult();
        return number;
    }

    /**
     * Calcular la EA
     *
     * @param planta_key
     * @param fechaMin
     * @param fechaMax
     * @param comprobanteestadoAnulado
     * @return
     */
    private Long getEA(String planta_key, Timestamp fechaMin, Timestamp fechaMax,
                       Comprobanteestado comprobanteestadoAnulado) {
    	String hql1 = " select new " + ReporteConsolidadoInspeccionVentasEABean.class.getName()
                + " (pl.key, year(ins.fechconsolidado),month(ins.fechconsolidado),count(com))"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " WHERE pl.key = :planta_key"
                + " and to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and com.comprobanteestado != :comprobanteestadoanulado"
                + " group by pl.key,year(ins.fechconsolidado),month(ins.fechconsolidado),day(ins.fechconsolidado)"
                + " order by year(ins.fechconsolidado),month(ins.fechconsolidado)";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("planta_key", planta_key);
        List<ReporteConsolidadoInspeccionVentasEABean> results = query.getResultList();

        // iteramos los resultados para el calculo
        Long cantIns = (long) 0;
        Long ea = (long) 0;
        int cantDias = 0;
        int cantMeses = 0;
        for (int i = 0; i < results.size(); i++) {
            cantDias++;
            // Si la cantidad de resultados no excede el limite permitido se
            // compara el mes actual con el mes de la siguiente iteración
            if (results.size() >= (i + 2)) {
                cantIns += results.get(i).getCant_ins();
                // Si el mes que continua es diferente al mes actual, es decir
                // se detecta un cambio de mes
                // Entonces alli luego de la sumatoria de inspecciones se divide
                // entre la candidad de dias trabajados recorridos
                if (results.get(i).getMonth() == results.get(i + 1).getMonth()) {
                } else {
                    cantMeses++;
                    cantIns = cantIns / cantDias;
                    ea += cantIns;
                    cantIns = (long) 0;
                    cantDias = 0;
                }
            } else {
                cantMeses++;
                cantIns = cantIns / cantDias;
                ea += cantIns;
                cantIns = (long) 0;
                cantDias = 0;
            }
        }
        ea = (ea / cantMeses);
        return ea;
    }

    private double getEADinero(String planta_key, Timestamp fechaMin, Timestamp fechaMax,
                               Comprobanteestado comprobanteestadoAnulado) {
    	
    	String hql1 = "select new " + ReporteConsolidadoInspeccionVentasEADineroBean.class.getName()
                + " (pl.key, year(ins.fechconsolidado),month(ins.fechconsolidado),sum(com.importetotal))"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " WHERE pl.key = :planta_key"
                + " and to_date(to_char(ins.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and com.comprobanteestado != :comprobanteestadoanulado"
                + " group by pl.key,year(ins.fechconsolidado),month(ins.fechconsolidado),day(ins.fechconsolidado)"
                + " order by year(ins.fechconsolidado),month(ins.fechconsolidado)";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query.setParameter("planta_key", planta_key);
        List<ReporteConsolidadoInspeccionVentasEADineroBean> results = query.getResultList();

        // iteramos los resultados para el calculo
        double ammountImporte = 0;
        double ea = 0;
        int cantDias = 0;
        int cantMeses = 0;
        for (int i = 0; i < results.size(); i++) {
            cantDias++;
            // Si la cantidad de resultados no excede el limite permitido se
            // compara el mes actual con el mes de la siguiente iteración
            if (results.size() >= (i + 2)) {
                ammountImporte += results.get(i).getAmmountImporte();
                // Si el mes que continua es diferente al mes actual, es decir
                // se detecta un cambio de mes
                // Entonces alli luego de la sumatoria de inspecciones se divide
                // entre la candidad de dias trabajados recorridos
                if (results.get(i).getMonth() == results.get(i + 1).getMonth()) {
                } else {
                    cantMeses++;
                    ammountImporte = ammountImporte / cantDias;
                    ea += ammountImporte;
                    ammountImporte = 0;
                    cantDias = 0;
                }
            } else {
                cantMeses++;
                ammountImporte = ammountImporte / cantDias;
                ea += ammountImporte;
                ammountImporte = 0;
                cantDias = 0;
            }
        }
        ea = (ea / cantMeses);
        return Util.round(ea, 2);
    }

    /**
     * Obtiene el valor del promedio de inspecciones del año anterior
     *
     * @param planta_key
     * @param fechaMin
     * @param fechaMax
     * @param comprobanteestadoAnulado
     * @return
     */
    private Double getIngresosAnioAnterior(String planta_key, Timestamp fechaMin, Timestamp fechaMax,
                                           Comprobanteestado comprobanteestadoAnulado) {
    	String hql1 = "SELECT sum(com.importetotal)/count(ins)"
    			+ " from Comprobante com"
    			+ " inner join com.inspeccion ins"
                + " inner join com.linea linea"
                + " inner join linea.planta pl"
                + " where pl.key = :planta_key and"
                + " to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and com.comprobanteestado != :comprobanteestadoanulado";
        TypedQuery<Double> query = entityManager.createQuery(hql1, Double.class);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", planta_key);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        Double number = query.getSingleResult();
        return (number != null) ? number : 0;
    }

    public List<ResultReportBean<InspeccionReporteBean>>  getAllByDateRangeByPlanta(Date fechIni, Date fechFin, String planta) {
    	
        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());

        List<ResultReportBean<InspeccionReporteBean>> resultado = new ArrayList();
        
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
        	ResultReportBean<InspeccionReporteBean> bean = new ResultReportBean<>();
        	List<InspeccionReporteBean> result = new ArrayList();
        	
        	String hql1 = "SELECT DISTINCT new "+ InspeccionReporteBean.class.getName()
            		+ "(ins.nrodocumentoinspeccion,incert.nrodocumentoCertificado,ins.nrodocumentoInforme,tpins.nombre,tpt.ambito,ie.nombre,ins.observacionAnulado,ins.observacionRetirado,"
            		+ "SUBSTRING(CONCAT(ins.fechconsolidado,''),1,19),ins.vigencia,SUBSTRING(CONCAT(ins.fechvencimiento,''),1,19),"
            		+ "ins.nrodocumentoreinspeccion,CONCAT(cajper.nombres,' ',cajper.apellidos),com.nrocomprobante,conins.abreviatura,CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,"
            		+ "cli.nrodocumentoidentidad,li.nombre,comest.nombre,fp.nombre,entfi.nombre,ctaco.nroctacorriente,mon.nombre,"
            		+ "SUBSTRING(CONCAT(com.fechapago,''),1,19),tar.nombre,pag.nrooperacionBanco,pag.nrooperacionTarjeta,tipdes.nombre,con.nombre,cam.nombre,"
            		+ "conej.user,com.totalsindscto,com.totaldscto,com.baseimponible,com.igv,com.importetotal,com.placaMotor,tp.nroplaca,"
            		+ "veh.nromotor,marc.nombre,mode.nombre,veh.aniofabricacion,col.nombre,combus.nombre,marcarro.nombre,veh.nroserie,"
            		+ "catg.nombre,veh.categoriaextra,veh.nroasientos,veh.nropasajeros,veh.nroejes,veh.nroruedas,CONCAT(veh.nrocilindros,''),"
            		+ "veh.pesoseco,veh.pesobruto,veh.nropuertas,CONCAT(veh.nrosalidaemergencia,''),veh.kilometraje,veh.alto,veh.longitud,"
            		+ "veh.ancho,veh.nrosoat,aseg.nombre,SUBSTRING(CONCAT(veh.fechiniciotarjetapropiedad,''),1,19),"
            		+ "SUBSTRING(CONCAT(veh.fechfintarjetapropiedad,''),1,19),SUBSTRING(CONCAT(ins.fechanulacion,''),1,19),ins.resultado,"
            		+ "ins.observacion,SUBSTRING(CONCAT(incert.fechcreacion,''),1,19),CONCAT(ceanpe.nombres,' ',ceanpe.apellidos),CONCAT(cmusanu.nombres,' ',cmusanu.apellidos),incert.observacionAnulado,incert.anulado,"
            		+ "incert.nrohojaValorada,CONCAT(peranuin.nombres,' ',peranuin.apellidos),ins.observacion)"
            		+ " FROM Comprobante com"
            		+ " left join com.pagos pag"
            		+ " left join com.inspeccion ins"
            		+ " left join ins.usuarioanulacion insusanu"
            		+ " left join insusanu.persona peranuin"
            		+ " left join ins.certificados incert"
            		+ " left join incert.usuarioanulacion certanu"
            		+ " left join certanu.persona ceanpe"
            		+ " left join ins.inspeccionestado ie"
            		+ " left join ins.tipoautorizacion tpt"
            		+ " left join ins.tipoinspeccion tpins"
            		+ " left join ins.usuariocaja uscaja"
            		+ " left join uscaja.persona cajper"
                    + " left join ins.vehiculo veh"
                    + " left join veh.marca marc"
                    + " left join veh.modelo mode"
                    + " left join veh.color col"
                    + " left join veh.combustible combus"
                    + " left join veh.marcacarroceria marcarro"
                    + " left join veh.categoria catg"
                    + " left join veh.aseguradora aseg"
                    + " left join veh.tarjetapropiedad tp"
                    + " left join com.usuarioanulacion comusanu"
                    + " left join comusanu.persona cmusanu"
                    + " left join com.cliente cli"
                    + " left join com.conceptoInspeccion conins"
                    + " left join com.comprobanteestado comest"
                    + " left join pag.entidadFinanciera entfi"
                    + " left join pag.cuentacorriente ctaco"
                    + " left join pag.moneda mon"
                    + " left join pag.tarjeta tar"
                    + " left join com.tipodescuento tipdes"
                    + " left join com.campania cam"
                    + " left join com.convenio con"
                    + " left join con.ejecutivo conej"
                    + " left join com.formaPago fp"
                    + " left join com.linea li"
                    + " left join li.planta pl"
                    + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                    + " and pl.key = :planta_key and ins.nrodocumentoinspeccion != null"
                    + " order by ins.nrodocumentoinspeccion desc";
            		
            Query query = entityManager.createQuery(hql1);
            query.setParameter("fechaMin", fechaMin);
            query.setParameter("fechaMax", fechaMax);
            query.setParameter("planta_key", planta_key);
            result = query.getResultList();
            
            for(int i=0;i<result.size();i++){
                for(int j=0;j<result.size()-1;j++){
                	if(i!=j){
                		if(result.get(i).getNroDocIns()!= null && result.get(j).getNroDocIns() != null){
                			if(result.get(i).getNroDocIns().equals(result.get(j).getNroDocIns())){
                				result.remove(i); 
                			}
                		}
                	}
                }
            }
            
            bean.setData(result);
            bean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(bean);
        	
        }
        
        
        return resultado;
    }
    
    public List<ReporteConvenioBean> getReporteConvenio(){
    	List<ReporteConvenioBean> result = new ArrayList<>();
    	List<ReporteConvenioBean> conve = new ArrayList<>();
    	String hql1 = "SELECT new "+ReporteConvenioBean.class.getName()
    			+ "(c.id,c.nombre,c.estado,per.nrodocumentoidentidad,per.nombrerazonsocial,CONCAT(ejper.nombres ,' ',ejper.apellidos),c.lineaCreditoMaxima)"
    			+ " FROM Convenio c"
    			+ " inner join c.conveniodetalles cd"
    			+ " left join c.persona per"
    			+ " left join c.ejecutivo eje"
    			+ " left join eje.persona ejper"
    			+ " order by c.nombre desc";
    	Query query = entityManager.createQuery(hql1);
    	conve = query.getResultList();
    	
    	for(ReporteConvenioBean bean : conve){
    		List<ConceptoPrecioBean> report = new ArrayList<>();
    		String hql2 = "SELECT new "+ConceptoPrecioBean.class.getName()
        			+ "(CONCAT(cd.montoFlat,''),ci.key,fp.key)"
        			+ " FROM Conveniodetalle cd"
        			+ " left join cd.convenio c"
        			+ " inner join cd.conceptoinspeccion ci"
        			+ " inner join cd.formaPago fp"
        			+ " WHERE c.id = :key";
        	Query query2 = entityManager.createQuery(hql2);
        	query2.setParameter("key",bean.getId());
        	report = query2.getResultList();
        	result.add(new ReporteConvenioBean(bean).validarCampos(bean, report));
    	}
    	
    	for(int i=0;i<result.size();i++){
            for(int j=0;j<result.size()-1;j++){
            	if(i!=j){
            		if(result.get(i).getId()!= null && result.get(j).getId() != null){
            			if(result.get(i).getId().equals(result.get(j).getId())){
            				result.remove(i); 
            			}
            		}
            	}
            }
        }
    	
    	return result;
    }
    
    public List<ReporteExpedientesBean> getReporteExpedientes(Date fechIni, Date fechFin){
    	
    	Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ReporteExpedientesBean> resultado = new ArrayList<>();
        String hql1 = "SELECT DISTINCT new "+ ReporteExpedientesBean.class.getName()
        		+"(pl.nombre,0,count(com),0,0,pl.key)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join com.linea li"
                + " inner join li.planta pl"
        		+ " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        		+ " and com.comprobanteestado != :comprobanteestadoanulado and ins.nrodocumentoinspeccion != null"
        		+ " and com.importetotal != 0"
        		+ " group by pl.key";
        
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        resultado = query.getResultList();
        for (ReporteExpedientesBean reporte : resultado) {
            reporte.setInspeccion(getProcesadoIns(reporte.getSede_key(), fechaMin, fechaMax).intValue());
            reporte.setXml(getProcesadoIns(reporte.getSede_key(), fechaMin, fechaMax).intValue());
            reporte.setExpediente(getProcesadoExpediente(reporte.getSede_key(), fechaMin, fechaMax).intValue());
        }
        
        return resultado;
    }
    
    private Integer getProcesadoExpediente(String planta_key, Timestamp fechaMin, Timestamp fechaMax) {
        
        String hql1 = " select ex.cantidad"
        		+ " FROM Expediente ex"
                + " where ex.planta = :planta_key"
                + " and to_date(to_char(ex.fecha, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax";
        TypedQuery<Integer> query = entityManager.createQuery(hql1, Integer.class);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", planta_key);
        Integer number = query.getSingleResult();
        return number;
    }
    
    private Long getProcesadoIns(String planta_key, Timestamp fechaMin, Timestamp fechaMax) {
    	
    	Inspeccionestado inspeccionestadoRetirado = inspeccionEstadoRepository.findOneByKey("RETIRADO");
        
        String hql1 = " select count(ins)"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
                + " inner join com.linea linea"
                + " inner join linea.planta pl"
                + " where pl.key = :planta_key"
                + " and to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and ins.inspeccionestado != :inspeccionestadoRetirado";
        TypedQuery<Long> query = entityManager.createQuery(hql1, Long.class);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", planta_key);
        query.setParameter("inspeccionestadoRetirado", inspeccionestadoRetirado);
        Long number = query.getSingleResult();
        return number;
    }
    
    public List<ResultReportBean<CertificadoReporteBean>> getCertificadoByDateRangeByPlanta(Date fechIni, Date fechFin, String planta) {
    	
        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ResultReportBean<CertificadoReporteBean>> resultado = new ArrayList<>();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
        	ResultReportBean<CertificadoReporteBean> bean = new ResultReportBean<>();
        	List<CertificadoReporteBean> result = new ArrayList();
        	
        	 String hql1 = "SELECT DISTINCT new "+ CertificadoReporteBean.class.getName()
             		+ "(ins.nrodocumentoinspeccion,incert.nrodocumentoCertificado,SUBSTRING(CONCAT(ins.fechconsolidado,''),1,19),ins.vigencia,SUBSTRING(CONCAT(ins.fechvencimiento,''),1,19),"
             		+ "com.placaMotor,tp.nroplaca,li.nombre,ins.nrodocumentoInforme,tpins.nombre,conins.abreviatura,ins.resultado,incert.anulado,tpt.ambito,ins.nrodocumentoreinspeccion,"
             		+ "com.nrocomprobante,CONCAT(ceanpe.nombres,' ',ceanpe.apellidos),certer.descripcionError,incert.nrohojaValorada,certer.tipoError,CONCAT(pco.nombres,' ',pco.apellidos)"
             		+ ",CONCAT(pca.nombres,' ',pca.apellidos),CONCAT(certer.fechacreacion,''))"
             		+ " FROM Comprobante com"
             		+ " inner join com.inspeccion ins"
             		+ " inner join ins.usuarioconsolidado uco"
             		+ " left join uco.persona pco"
             		+ " inner join ins.usuariocaja uca"
             		+ " left join uca.persona pca"
             		+ " inner join ins.certificados incert"
             		+ " left join incert.certificadoErrors certer"
             		+ " left join incert.usuarioanulacion certanu"
             		+ " left join certanu.persona ceanpe"
             		+ " left join ins.inspeccionestado ie"
             		+ " left join ins.tipoautorizacion tpt"
             		+ " left join ins.tipoinspeccion tpins"
             		+ " left join ins.usuariocaja uscaja"
             		+ " left join uscaja.persona cajper"
                     + " left join ins.vehiculo veh"
                     + " left join veh.marca marc"
                     + " left join veh.modelo mode"
                     + " left join veh.color col"
                     + " left join veh.combustible combus"
                     + " left join veh.marcacarroceria marcarro"
                     + " left join veh.categoria catg"
                     + " left join veh.aseguradora aseg"
                     + " left join veh.tarjetapropiedad tp"
                     + " left join com.usuarioanulacion comusanu"
                     + " left join comusanu.persona cmusanu"
                     + " left join com.cliente cli"
                     + " left join com.conceptoInspeccion conins"
                     + " left join com.comprobanteestado comest"
                     + " left join com.tipodescuento tipdes"
                     + " left join com.campania cam"
                     + " left join com.convenio con"
                     + " left join con.ejecutivo conej"
                     + " inner join com.formaPago fp"
                     + " inner join com.linea li"
                     + " inner join li.planta pl"
                     + " WHERE to_date(to_char(incert.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                     + " and pl.key = :planta_key and ins.nrodocumentoinspeccion != null"
                     + " order by ins.nrodocumentoinspeccion desc";
             		
             Query query = entityManager.createQuery(hql1);
             query.setParameter("fechaMin", fechaMin);
             query.setParameter("fechaMax", fechaMax);
             query.setParameter("planta_key", planta_key);
             result = query.getResultList();
             
             
             for(int i=0;i<result.size();i++){
     			for(int j=0;j<result.size()-1;j++){
     				if(i!=j){
     					if(result.get(i).getNroCert().equals(result.get(j).getNroCert())){
     						result.remove(i);
     					}
     				}
     			}
     		}
        	
             bean.setData(result);
             bean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
             
             resultado.add(bean);
        }


        return resultado;
    }
    
    public List<ResultReportBean<ContabilidadReporteBean>> getContabilidadByDateRangeByPlanta(Date fechIni, Date fechFin, String planta) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        
        List<ResultReportBean<ContabilidadReporteBean>> resultado = new ArrayList();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
        	ResultReportBean<ContabilidadReporteBean> data = new ResultReportBean<>();
        	List<ContabilidadReporteBean> report = new ArrayList<>();
        	String hql1 = "select distinct tipodocume6_.nombre as col_0_0_, comprobant0_.nrocomprobante as col_1_0_, substring((comprobant0_.fechapago||''), 1, 19) as col_2_0_," 
        			+ " persona8_.nrodocumentoidentidad as col_3_0_, (persona8_.nombres||' '||persona8_.apellidos) as col_4_0_, persona8_.nombrerazonsocial as col_5_0_," 
        			+ " moneda9_.nombre as col_6_0_, comprobant0_.baseimponible as col_7_0_, comprobant0_.igv as col_8_0_, comprobant0_.importetotal as col_9_0_, "
                    + " comprobant10_.nombre as col_10_0_, tipodescue7_.nombre as col_11_0_, descuentoc4_.nombreDescuento as col_12_0_, formapago11_.nombre as col_13_0_, "
                    + " tipocontad12_.nombre as col_14_0_, pagos1_.baseimponible as col_15_0_, pagos1_.igv as col_16_0_, pagos1_.importe as col_17_0_, tarjeta2_.nombre as col_18_0_," 
                    + " pagos1_.nrooperacionTarjeta as col_19_0_, entidadfin3_.nombre as col_20_0_, pagos1_.nrooperacionBanco as col_21_0_" 
                    + " from comprobante comprobant0_ "
                    + " left outer join pago pagos1_ on comprobant0_.id=pagos1_.comprobante_id "
                    + " left outer join tarjeta tarjeta2_ on pagos1_.tarjeta_key=tarjeta2_.key "
                    + " left outer join entidadfinanciera entidadfin3_ on pagos1_.entidadFinanciera_key=entidadfin3_.key" 
                    + " left outer join moneda moneda9_ on pagos1_.moneda_key=moneda9_.key "
                    + " left outer join tipocontado tipocontad12_ on pagos1_.tipoContado_key=tipocontad12_.key "
                    + " left outer join descuentocomprobante descuentoc4_ on comprobant0_.descuentoComprobante_id=descuentoc4_.id "
                    + " left outer join inspeccion inspeccion5_ on comprobant0_.inspeccion_nrodocumentoinspeccion=inspeccion5_.nrodocumentoinspeccion" 
                    + " left outer join tipodocumento tipodocume6_ on comprobant0_.tipodocumento_key=tipodocume6_.key "
                    + " left outer join tipodescuento tipodescue7_ on comprobant0_.tipodescuento_key=tipodescue7_.key "
                    + " left outer join persona persona8_ on comprobant0_.cliente_nrodocumentoidentidad=persona8_.nrodocumentoidentidad" 
                    + " inner join comprobanteestado comprobant10_ on comprobant0_.comprobanteestado_key=comprobant10_.key" 
                    + " left outer join formapago formapago11_ on comprobant0_.formaPago_key=formapago11_.key" 
                    + " left outer join linea linea13_ on comprobant0_.linea_key=linea13_.key "
                    + " left outer join planta planta14_ on linea13_.planta_key=planta14_.key "
                    + " where (to_date(to_char(comprobant0_.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechIni)+"' and '"+dateFormat.format(fechFin)+"') and planta14_.key='"+planta_key+"' and comprobant0_.nrocomprobante is not null" 
                    + " order by comprobant0_.nrocomprobante desc";
            
        	Query query = entityManager.createNativeQuery(hql1);
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                ContabilidadReporteBean bean = new ContabilidadReporteBean();
                bean.setTipoDoc(obj[0] != null && obj[0] != "" ? obj[0].toString():null);
                bean.setNrocomprobante(obj[1] != null ? obj[1].toString():null);
                bean.setFecha(obj[2] != null ? obj[2].toString():null);
                bean.setDniOruc(obj[3] != null ? obj[3].toString():null);
                bean.setClienteDNI(obj[4] != null ? obj[4].toString():null);
                bean.setClineteRUC(obj[5] != null ? obj[5].toString():null);
                bean.setMoneda(obj[6] != null ? obj[6].toString():null);
                bean.setTotalBase(Double.valueOf(obj[7]!=null?obj[7].toString().trim():"0"));
                bean.setTotalIgv(Double.valueOf(obj[8]!=null?obj[8].toString().trim():"0"));
                bean.setTotal(Double.valueOf(obj[9]!=null?obj[9].toString().trim():"0"));
                bean.setEstadoComprobante(obj[10] != null ? obj[10].toString():null);
                bean.setTipodescuento(obj[11] != null ? obj[11].toString():null);
                bean.setNombreDescuento(obj[12] != null ? obj[12].toString():null);
                bean.setFormaPago(obj[13] != null ? obj[13].toString():null);
                bean.setTipoContado(obj[14] != null ? obj[14].toString():null);
                bean.setBasePago(Double.valueOf(obj[15]!=null?obj[15].toString().trim():"0"));
                bean.setIgvPago(Double.valueOf(obj[16]!=null?obj[16].toString().trim():"0"));
                bean.setImportePago(Double.valueOf(obj[17]!=null?obj[17].toString().trim():"0"));
                bean.setTarjeta(obj[18] != null ? obj[18].toString():null);
                bean.setNroOperacionTarjeta(obj[19] != null ? obj[19].toString():null);
                bean.setEntidadFinanciera(obj[20] != null ? obj[20].toString():null);
                bean.setNroOperacionBanco(obj[21] != null ? obj[21].toString():null);
                report.add(bean);
            }
            
            data.setData(report);
            data.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(data);
        }

        
        
        return resultado;
    }
    
    public List<ComprobantesAtrasadosReporteBean> getAllComprobantesAtrasadosByDateRangeByPlanta(Date fechIni, Date fechFin, String planta,Integer hora) {

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());

        List<ComprobantesAtrasadosReporteBean> resultado = new ArrayList();
        String hql1 = "SELECT new " + ComprobantesAtrasadosReporteBean.class.getName()
        		+ "( i.nrodocumentoinspeccion as nrodocumentoinspeccion"
        		+ ", p.nombre as planta"
        		+ ", c.placaMotor as nro_placa"
        		+ ", (' ' || c.fechapago) as horaInicio "
        		+ ", (' ' || i.fechconsolidado) as horaFin"
        		+ ", (' ' || (i.fechconsolidado - c.fechapago)) as tiempo "
        		+ ", ie.nombre)"
        		+ " FROM Comprobante c "
        		+ " inner join c.inspeccion i "
        		+ " inner join i.inspeccionestado ie"
        		+ " inner join c.linea l "
        		+ " inner join l.planta p "
                + " WHERE EXTRACT(EPOCH FROM (i.fechconsolidado - c.fechapago))/3600 > :hora"
                + " and to_date(to_char(c.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and NOT i.resultado IS null "
                + " and NOT i.resultado = ''";
        
        if(!planta.equals("TODOS")) {
	        	hql1+=" and p.key = :planta_key "
	            + " order by planta desc, tiempo desc";
        }else {
        	
	        	List<String> plantas = getAllIdPlantaByUser();
	        	String sqlIn= "";
	        	for(String sp : plantas)
	        	{
	        		sqlIn+= "'"+sp+"'";
	        	}
	        
	        	hql1+=" and p.key IN ("+sqlIn+") order by planta desc, tiempo desc";
        }
        
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        if(!planta.equals("TODOS")) {
            query.setParameter("planta_key", planta);
        }
        query.setParameter("hora", hora);
        
        resultado = query.getResultList();
        
        for(int i=0;i<resultado.size();i++){
            for(int j=0;j<resultado.size()-1;j++){
            	if(i!=j){
            		if(resultado.get(i).getNrodocumentoinspeccion()!= null && resultado.get(j).getNrodocumentoinspeccion() != null){
            			if(resultado.get(i).getNrodocumentoinspeccion().equals(resultado.get(j).getNrodocumentoinspeccion())){
            				resultado.remove(i); 
            			}
            		}
            	}
            }
        }
        
        
        return resultado;
    }
    
    public List<SunatReporteBean> getSunatByDateRangeByPlanta(Date fechIni, Date fechFin, String planta) {

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());

        List<SunatReporteBean> resultado = new ArrayList();
        String hql1 = "SELECT DISTINCT new "+SunatReporteBean.class.getName()
        		+ "(com.nrocomprobante,'',emp.ruc,ins.nrodocumentoreinspeccion,ins.resultado,"
        		+ "cert.nrodocumentoCertificado,ins.nrodocumentoInforme,ie.key,com.importetotal,'',"
        		+ "com.fechcreacion,tipins.key,mone.key,com.placaMotor,ci.key,docid.key,cli.nrodocumentoidentidad,"
        		+ "CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,'')"
        		+ " FROM Comprobante com"
        		+ " left join com.inspeccion ins"
        		+ " inner join com.linea li"
        		+ " left join com.moneda mone"
        		+ " left join com.cliente cli"
        		+ " left join cli.tipodocumentoidentidad docid"
        		+ " inner join com.conceptoInspeccion ci"
        		+ " left join ins.certificados cert"
        		+ " left join ins.inspeccionestado ie"
        		+ " inner join ins.tipoinspeccion tipins"
                + " left join li.planta pl"
                + " left join pl.empresa emp"
                + " WHERE to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and pl.key = :planta_key and ins.resultado != null and ins.resultado != ''"
                + " order by com.fechcreacion desc";
        
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", planta);
        
        List<SunatReporteBean> resultadoParcial = query.getResultList();
        
        for(int i=0;i<resultadoParcial.size();i++){
            for(int j=0;j<resultadoParcial.size()-1;j++){
            	if(i!=j){
            		if(resultadoParcial.get(i).getNroComprobante()!= null && resultadoParcial.get(j).getNroComprobante() != null){
            			if(resultadoParcial.get(i).getNroComprobante().equals(resultadoParcial.get(j).getNroComprobante())){
            				resultadoParcial.remove(i); 
            			}
            		}
            	}
            }
        }
        
        for(SunatReporteBean bean : resultadoParcial) 
        { 
          resultado.add(new SunatReporteBean(bean.nroComprobante,bean.nroSerie,bean.ruc,bean.reInspeccion,bean.resultado,
        		  bean.nroCertificado,bean.nroInforme,bean.insEstado,bean.importeTotal,bean.importeCadena,bean.fechComprobante,
        		  bean.tipoInspeccion,bean.moneda,bean.nroPlaca,bean.conceptoInspeccion,bean.tipoDocIdentidad,
        		  bean.nroIdentidad,bean.nomPersona,bean.nomRazonSocial,bean.fecha).validarCampos(bean));
        } 
        
        return resultadoParcial;
    }

    public List<Cierrecaja> getReporteCierreCajaByDateRangeByPlanta(Date fechIni, Date fechFin, String planta) {

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());

        List<Cierrecaja> resultado = new ArrayList();
        String hql1 = "SELECT cj FROM Cierrecaja cj"
                + " inner join cj.planta pl"
                + " WHERE to_date(to_char(cj.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and pl.key = :planta_key"
                + " order by cj.fechcreacion desc";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", planta);
        resultado = query.getResultList();

        return resultado;
    }

    public List<ResultReportBean<OperacionesReporteBean>> getReporteOperaciones(Date fechIni, Date fechFin, String planta) {
        
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<String> plantas = new ArrayList<>();
        List<ResultReportBean<OperacionesReporteBean>> resultado = new ArrayList();
        
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
        	
        	ResultReportBean<OperacionesReporteBean> reporteBean = new ResultReportBean<>();
        	List<OperacionesReporteBean> result = new ArrayList<>();
        	
        	String hql1 = "SELECT DISTINCT new "+ OperacionesReporteBean.class.getName()
            		+ "(ins.nrodocumentoInforme,incert.nrodocumentoCertificado,incert.anulado,SUBSTRING(CONCAT(ins.fechcreacion,''),1,19),ins.resultado,incert.observacionAnulado)"
            		+ " FROM Comprobante com"
            		+ " left join com.inspeccion ins"
            		+ " left join ins.certificados incert"
            		+ " left join ins.inspeccionestado ie"
            		+ " left join com.linea li"
                    + " inner join li.planta pl"
                    + " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                    + " and pl.key = :planta_key"
                    + " order by ins.nrodocumentoInforme desc";
            
            Query query = entityManager.createQuery(hql1);
            query.setParameter("fechaMin", fechaMin);
            query.setParameter("fechaMax", fechaMax);
            query.setParameter("planta_key", planta_key);
            result = query.getResultList();
            
            reporteBean.setData(result);
            reporteBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            resultado.add(reporteBean);
        	
        }
        
        return resultado;
    }

    public List<ResultReportBean<ReporteGerenciaBean>> getReporteGerenciaByDateRangeByConcepto(Date fechIni, Date fechFin,
                                                                             String concepto_key, String planta) {

    	Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<ResultReportBean<ReporteGerenciaBean>> resultado = new ArrayList<>();
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        		plantas = getAllIdPlantaByUser();
        }else {
        		plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
        	ResultReportBean<ReporteGerenciaBean> bean = new ResultReportBean<>();
        	List<ReporteGerenciaBean> lst = new ArrayList();
            String hql1 = "SELECT DISTINCT new " + ReporteGerenciaBean.class.getName()
                    + "(ci.abreviatura, count(ins),sum(com.importetotal))"
                    + " FROM Comprobante com"
                    + " left join com.inspeccion ins"
                    + " left join com.conceptoInspeccion ci"
                    + " inner join com.linea li"
                    + " inner join li.planta pl"
                    + " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                    + " and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0" 
                    + " and pl.key = :planta_key ";
            
            if (!concepto_key.equals("todos")) {
                hql1 += " and ci.key = :concepto_key";
            }
            hql1 += " group by ci.abreviatura";

            Query query = entityManager.createQuery(hql1);

            query.setParameter("fechaMin", fechaMin);
            query.setParameter("fechaMax", fechaMax);
            if (!concepto_key.equals("todos")) {
                query.setParameter("concepto_key", concepto_key);
            }
            query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
            query.setParameter("planta_key", planta_key);
            lst = query.getResultList();
            
            bean.setData(lst);
            bean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            resultado.add(bean);
        	
        }

        return resultado;
    }
    
       
    public List<ReporteOT> getReporteOT(Date fechIni, Date fechFin,
            String empresa_key) {
           
        	Timestamp fechaMin = new Timestamp(fechIni.getTime());
            Timestamp fechaMax = new Timestamp(fechFin.getTime());
            

    		Date fechapago = new Date();
    		Date fechaestado = new Date();
            

 
            DateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
                    
     
                List<ReporteOT> lst = new ArrayList<>();

                String hql = "select fechaPago,i.usuariocaja_username,"+
                "c.placamotor,ce.nrodocumentocertificado,c.comprobanteestado_key,i.inspeccionestado_key,"+
                "c.nrocomprobante,ot.fechaestado, ot.nroot,ot.comprobanteestado_key as estadocomprobante,"+
                "dc.nombredescuento,c.totalsindscto, ci.abreviatura,dc.tipodescuento_key from comprobante c " + 
                "left join empresa e on c.empresa_key=e.key "+
                "left join  certificado ce on c.inspeccion_nrodocumentoinspeccion=ce.inspeccion_nrodocumentoinspeccion " + 
                "left join inspeccion i on  c.inspeccion_nrodocumentoinspeccion=i.nrodocumentoinspeccion " + 
                "left join conceptoinspeccion ci on c.conceptoinspeccion_key = ci.key "+
                "left join descuentocomprobante dc on c.descuentocomprobante_id = dc.id "+
                "left join ordentrabajo ot on c.ordentrabajo_id=ot.id " + 
                "where  e.key='"+empresa_key+"' and ot.comprobanteestado_key!='PEN' " + 
                "and  to_date(to_char(c.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') " + 
                "between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'";
                
                Query query = entityManager.createNativeQuery(hql);
                List<Object> result = (List<Object>) query.getResultList();
                Iterator<Object> itr = result.iterator();
                while(itr.hasNext()){
                    Object[] obj = (Object[]) itr.next();
                    ReporteOT bean = new ReporteOT();
                    try {
                    	fechapago = dateFormat.parse(obj[0].toString());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
                    bean.setFechapago(fechapago);
                    bean.setUsuario(obj[1] != null ? obj[1].toString():null);
                    bean.setPlacamotor(obj[2] != null ? obj[2].toString():null);
                    bean.setNrodocumentocertificado(obj[3] != null ? obj[3].toString():null);
                    bean.setEstadocomprobante(obj[4] != null ? obj[4].toString():null);
                    bean.setEstadoinspeccion(obj[5] != null ? obj[5].toString():null);
                    bean.setNrocomprobante(obj[6] != null ? obj[6].toString():null);
                    try {
                    	fechaestado = dateFormat.parse(obj[7].toString());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
                    bean.setFechaestado(fechaestado);
                    bean.setNroot(obj[8] != null ? obj[8].toString():null);
                    bean.setEstadoot(obj[9] != null ? obj[9].toString():null);
                    bean.setNombredescuento(obj[10] != null ? obj[10].toString():null);
                    bean.setTotalsindscto(Double.valueOf(obj[11]!=null?obj[11].toString().trim():"0"));
                    bean.setConceptoinspeccion(obj[12]!=null ? obj[12].toString():null);
                    bean.setTipodsctokey(obj[13]!=null ? obj[13].toString():null);
                    lst.add(bean);
                }
            	

  
        return lst;
}
    
    public List<ReporteOT> getReporteOTPendiente(Date fechIni, Date fechFin,String empresa_key)
    {
        
     	Timestamp fechaMin = new Timestamp(fechIni.getTime());
         Timestamp fechaMax = new Timestamp(fechFin.getTime());
         

 		Date fechapago = new Date();
 		Date fechaestado = new Date();
         


         DateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
                 
  
             List<ReporteOT> lst = new ArrayList<>();

             String hql = "select fechaPago,i.usuariocaja_username,"+
             "c.placamotor,case i.resultado when 'A' then ce.nrodocumentocertificado else i.nrodocumentoinforme  end,c.comprobanteestado_key,i.inspeccionestado_key,"+
             "c.nrocomprobante,ot.fechaestado, ot.nroot,ot.comprobanteestado_key as estadocomprobante,"+
             "dc.nombredescuento,c.importetotal, ci.abreviatura,dc.tipodescuento_key,c.cliente_nrodocumentoidentidad,p.nombre from comprobante c " + 
             "left join empresa e on c.empresa_key=e.key "+
             "left join  certificado ce on c.inspeccion_nrodocumentoinspeccion=ce.inspeccion_nrodocumentoinspeccion " + 
             "left join inspeccion i on  c.inspeccion_nrodocumentoinspeccion=i.nrodocumentoinspeccion " + 
             "left join conceptoinspeccion ci on c.conceptoinspeccion_key = ci.key "+
             "left join descuentocomprobante dc on c.descuentocomprobante_id = dc.id "+
             "left join linea l on c.linea_key=l.key "+
             "left join  planta p on l.planta_key=p.key "+
             "left join ordentrabajo ot on c.ordentrabajo_id=ot.id " + 
             "where  e.key='"+empresa_key+"' and ot.comprobanteestado_key!='CAN' " + 
             "and  to_date(to_char(c.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') " + 
             "between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'";
             Query query = entityManager.createNativeQuery(hql);
             List<Object> result = (List<Object>) query.getResultList();
             Iterator<Object> itr = result.iterator();
             while(itr.hasNext()){
                 Object[] obj = (Object[]) itr.next();
                 ReporteOT bean = new ReporteOT();
                 try {
                 	fechapago = dateFormat.parse(obj[0].toString());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
                 bean.setFechapago(fechapago);
                 bean.setUsuario(obj[1] != null ? obj[1].toString():null);
                 bean.setPlacamotor(obj[2] != null ? obj[2].toString():null);
                 bean.setNrodocumentocertificado(obj[3] != null ? obj[3].toString():null);
                 bean.setEstadocomprobante(obj[4] != null ? obj[4].toString():null);
                 bean.setEstadoinspeccion(obj[5] != null ? obj[5].toString():null);
                 bean.setNrocomprobante(obj[6] != null ? obj[6].toString():null);
                 try {
                 	fechaestado = dateFormat.parse(obj[7].toString());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
                 bean.setFechaestado(fechaestado);
                 bean.setNroot(obj[8] != null ? obj[8].toString():null);
                 bean.setEstadoot(obj[9] != null ? obj[9].toString():null);
                 bean.setNombredescuento(obj[10] != null ? obj[10].toString():null);
                 bean.setTotalsindscto(Double.valueOf(obj[11]!=null?obj[11].toString().trim():"0"));
                 bean.setConceptoinspeccion(obj[12]!=null ? obj[12].toString():null);
                 bean.setTipodsctokey(obj[13]!=null ? obj[13].toString():null);
                 bean.setNrodocumentoidentidad(obj[14]!=null ? obj[14].toString():null);
                 bean.setNombreplanta(obj[15]!=null ? obj[15].toString():null);
                 lst.add(bean);
             }
             return lst;
    }

    
    
    
    public List<ResultReportBean<ReporteVisitasBean>> getReporteVisitasByDateRangeByConcepto(Date fechIni, Date fechFin,
            String concepto_key, String planta) {

    	Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
    	Timestamp fechaMax = new Timestamp(fechFin.getTime());
    	
    	List<String> plantas = new ArrayList<>();
    	if(planta.equals("todos")) {
    		plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
    	
    	List<ResultReportBean<ReporteVisitasBean>> resultado = new ArrayList();
    	
    	for(String planta_key : plantas) {
    		
    		ResultReportBean<ReporteVisitasBean> reporteBean = new ResultReportBean<>();
    		
    		List<ReporteVisitasBean> resultReport = new ArrayList<>();
    		
    		String hql1 = "SELECT new " + ReporteVisitasBean.class.getName()
        			+ "(com.placaMotor, count(ins))"
        			+ " FROM Comprobante com"
        			+ " left join com.inspeccion ins"
        			+ " left join com.conceptoInspeccion ci"
        			+ " inner join com.linea li"
                    + " inner join li.planta pl"
        			+ " where to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0"
        			+ " and ins.nrodocumentoreinspeccion is null"
        			+ " and pl.key = :planta_key ";

        	if (!concepto_key.equals("todos")) {
        		hql1 += " and ci.key = :concepto_key";
        	}
        	hql1 += " group by com.placaMotor";

        	Query query = entityManager.createQuery(hql1);

        	query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	if (!concepto_key.equals("todos")) {
        		query.setParameter("concepto_key", concepto_key);
        	}
        	query.setParameter("planta_key", planta_key);
        	query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        	resultReport = query.getResultList();
        	
        	reporteBean.setData(resultReport);
            reporteBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reporteBean);
    		
    	}
    	
    	return resultado;
    }
    
    public List<ResultReportBean<ReporteControlEstacionesBean>> getReporteTiempoEstacion(Date fechIni, Date fechFin, String planta, String etapa){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ResultReportBean<ReporteControlEstacionesBean>> resultado = new ArrayList();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
                
        for(String planta_key : plantas) {
            ResultReportBean<ReporteControlEstacionesBean> reportBean = new ResultReportBean<>();

            List<ReporteControlEstacionesBean> lst = new ArrayList<>();
            
            String hql = "SELECT new "+ ReporteControlEstacionesBean.class.getName()
            		+ "(com.fechapago,rm.fechainicio,rm.fechafin,tmq.descripcion,ins.nrodocumentoinspeccion,rm.manual,com.placaMotor,ins.fechconsolidado)"
            		+ " FROM Comprobante com"
            		+ " inner join com.inspeccion ins"
            		+ " inner join ins.resultadosMaquina rm"
            		+ " inner join rm.maquina mq"
            		+ " inner join mq.tipomaquina tmq"
            		+ " inner join com.linea li"
                    + " inner join li.planta pl"
        			+ " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and pl.key = :planta_key ";
            if(!etapa.equals("todos")) {
            	hql += " and tmq.key = :etapa";
            }
            Query query = entityManager.createQuery(hql);
            query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	query.setParameter("planta_key", planta_key);
        	if(!etapa.equals("todos")) {
        		query.setParameter("etapa", etapa);
        	}
        	lst = query.getResultList();
        	
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        }
        
        return resultado;
    }
    
    public List<ResultReportBean<ReporteDescuentoConsolidadoBean>> getReporteDescuentosConsolidado (Date fechIni, Date fechFin, String planta){
    	
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
    	List<ResultReportBean<ReporteDescuentoConsolidadoBean>> resultado = new ArrayList<>();
    	
    	List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
            ResultReportBean<ReporteDescuentoConsolidadoBean> reportBean = new ResultReportBean<>();

        	List<ReporteDescuentoConsolidadoBean> lst = new ArrayList<>();
        	
        	String hql = "SELECT new "+ReporteDescuentoConsolidadoBean.class.getName()
            		+ "(des.nombreDescuento,tides.nombre,sum(com.totaldscto),sum(com.importetotal),count(ins.nrodocumentoinspeccion))"
            		+ " FROM Comprobante com"
            		+ " inner join com.tipodescuento tides"
            		+ " inner join com.inspeccion ins"
            		+ " inner join com.descuentoComprobante des"
            		+ " inner join com.linea li"
                    + " inner join li.planta pl"
                    + " where to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and com.importetotal > 0 and pl.key = :planta_key"
        			+ " group by des.nombreDescuento,tides.nombre"
        			+ " order by des.nombreDescuento,tides.nombre asc";
            Query query = entityManager.createQuery(hql);
            query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	query.setParameter("planta_key", planta_key);
        	lst = query.getResultList();
        	
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        	
        }
        
    	return resultado;
    }
    
    public List<ResultReportBean<ReporteVehiculosInspeccionadosBean>> getReporteVehiculosInspeccionados (Date fechIni, Date fechFin, String planta){
    	
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
    	List<ResultReportBean<ReporteVehiculosInspeccionadosBean>> resultado = new ArrayList<>();
    	
    	List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
            ResultReportBean<ReporteVehiculosInspeccionadosBean> reportBean = new ResultReportBean<>();

        	List<ReporteVehiculosInspeccionadosBean> lst = new ArrayList<>();
        	
        	String hql = "SELECT new "+ReporteVehiculosInspeccionadosBean.class.getName()
            		+ "(cer.nrodocumentoCertificado,ins.nrodocumentoInforme,tins.nombre,taut.ambito,iest.nombre,ins.observacion,ins.fechconsolidado,ins.vigencia,"
            		+ "ins.nrodocumentoreinspeccion,ci.abreviatura,li.nombre,com.placaMotor,ve.aniofabricacion,cbu.nombre,cat.nombre,ve.categoriaextra,ins.resultado)"
            		+ " FROM Comprobante com"
            		+ " inner join com.inspeccion ins"
            		+ " left join ins.certificados cer"
            		+ " inner join ins.tipoinspeccion tins"
            		+ " inner join ins.tipoautorizacion taut"
            		+ " inner join ins.inspeccionestado iest"
            		+ " inner join com.conceptoInspeccion ci"
            		+ " inner join ins.vehiculo ve"
            		+ " inner join ve.combustible cbu"
            		+ " inner join ve.categoria cat"
            		+ " inner join com.linea li"
                    + " inner join li.planta pl"
                    + " where to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and pl.key = :planta_key ";
            Query query = entityManager.createQuery(hql);
            query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	query.setParameter("planta_key", planta_key);
        	lst = query.getResultList();
        	
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        	
        }
        
    	return resultado;
    }
    
    public List<ResultReportBean<ReporteDescuento>> getReporteDescuentoBean(Date fechIni, Date fechFin, String planta){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ResultReportBean<ReporteDescuento>> resultado = new ArrayList();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        for(String planta_key : plantas) {
            ResultReportBean<ReporteDescuento> reportBean = new ResultReportBean<>();

            List<ReporteDescuento> lst = new ArrayList<>();
            
            String hql = "SELECT new "+ ReporteDescuento.class.getName()
            		+ "(to_char(com.fechapago, 'YYYY-MM-DD'),tipdes.nombre,sum(com.totaldscto),sum(com.totalsindscto))"
            		+ " FROM Comprobante com"
            		+ " left join com.tipodescuento tipdes"
            		+ " inner join com.inspeccion ins"
            		+ " inner join com.linea li"
                    + " inner join li.planta pl"
        			+ " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and pl.key = :planta_key "
        			+ " group by to_char(com.fechapago, 'YYYY-MM-DD'),tipdes.nombre " 
        			+ " order by to_char(com.fechapago, 'YYYY-MM-DD'),tipdes.nombre ";
            Query query = entityManager.createQuery(hql);
            query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	query.setParameter("planta_key", planta_key);
        	lst = query.getResultList();
        	
        	lst.forEach(bean ->{
        		bean.setPorcentaje((bean.getMontoDescuento()/bean.getMontoTotal())*100);
        	});
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        }
        
        
        return resultado;
    }
    
    public List<ResultReportBean<ReporteControlTiempoBean>> getReporteControlBean(Date fechIni, Date fechFin, String planta){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ResultReportBean<ReporteControlTiempoBean>> resultado = new ArrayList();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
                
        for(String planta_key : plantas) {
            ResultReportBean<ReporteControlTiempoBean> reportBean = new ResultReportBean<>();

            List<ReporteControlTiempoBean> lst = new ArrayList<>();
            
            String hql =
                    "select com.fechcreacion as inicioCaja, com.fechapago as finCaja, ins.fechconsolidado as entregaCert,"
            		+ " concat('',ins.fechconsolidado - com.fechcreacion) as tiempoOperativo, concat('',com.fechapago - com.fechcreacion) as tiempoCaja,"
            		+ " concat('',fechconsolidado -"
            		+ " (select rm.fechcreacion"
            		+ " from resultado_maquina rm"
            		+ " inner join inspeccion insp on rm.inspeccion_nrodocumentoinspeccion = insp.nrodocumentoinspeccion"
            		+ " where insp.nrodocumentoinspeccion = ins.nrodocumentoinspeccion order by rm.fechcreacion desc limit 1 )) as tiempoCert,"
            		+ " com.placamotor as placa, li.nombre as linea,"
            		+ " concat('',(select rma.fechcreacion from resultado_maquina rma"
            		+ " inner join inspeccion insp on rma.inspeccion_nrodocumentoinspeccion = insp.nrodocumentoinspeccion"
            		+ " inner join maquina as mq on rma.maquina_id = mq.id"
            		+ " inner join tipomaquina as tmq on mq.tipomaquina_key = tmq.key"
            		+ " where insp.nrodocumentoinspeccion = ins.nrodocumentoinspeccion and tmq.isfoto = true order by rma.fechcreacion asc  limit 1 )"
            		+ " - com.fechapago)  as estacionUno,"
            		+ " concat('',(select rma.fechcreacion from resultado_maquina rma"
            		+ " inner join inspeccion insp on rma.inspeccion_nrodocumentoinspeccion = insp.nrodocumentoinspeccion"
            		+ " inner join maquina as mq on rma.maquina_id = mq.id"
            		+ " inner join tipomaquina as tmq on mq.tipomaquina_key = tmq.key"
            		+ " where insp.nrodocumentoinspeccion = ins.nrodocumentoinspeccion and tmq.isfoto = true order by rma.fechcreacion asc  limit 1 offset 1)"
            		+ " - com.fechapago)  as estacionDos,"
            		+ " concat('',(select rma.fechcreacion from resultado_maquina rma"
            		+ " inner join inspeccion insp on rma.inspeccion_nrodocumentoinspeccion = insp.nrodocumentoinspeccion"
            		+ " inner join maquina as mq on rma.maquina_id = mq.id"
            		+ " inner join tipomaquina as tmq on mq.tipomaquina_key = tmq.key"
            		+ " where insp.nrodocumentoinspeccion = ins.nrodocumentoinspeccion and tmq.isfoto = true order by rma.fechcreacion asc  limit 1 offset 2)"
            		+ " - com.fechapago) as estacionTres,"
            		+ " concat(to_char(com.fechapago, 'HH12 AM'), ' hasta ' , to_char((com.fechapago + interval '1 hour'), 'HH12 AM')  ) as intervalo,"
            		+ " cat.nombre as categoria,"
            		+ " cla.nombre as clase,"
            		+ " dsco.tipodescuento_key as tipoDescuento,"
            		+ " dsco.nombredescuento as nombreDesc"
            		+ " from comprobante as com"
            		+ " left join descuentocomprobante dsco on com.descuentocomprobante_id = dsco.id"
            		+ " inner join conceptoinspeccion as ci on com.conceptoinspeccion_key = ci.key"
            		+ " inner join inspeccion as ins on com.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion"
            		+ " inner join linea as li on com.linea_key = li.key"
            		+ " inner join planta as pl on li.planta_key = pl.key"
            		+ " inner join vehiculo as ve on ins.vehiculo_nromotor = ve.nromotor"
            		+ " inner join categoria as cat on ve.categoria_key = cat.key"
            		+ " inner join vehiculoclase as cla on ve.vehiculoclase_key = cla.key"
            		+ " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"' and pl.key = '"+planta_key+"'"
            		+ " and inspeccionestado_key = 'CON' and ci.pasaporlinea = true ";
            Query query = entityManager.createNativeQuery(hql);
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                ReporteControlTiempoBean bean = new ReporteControlTiempoBean();
                bean.setInicioCaja(obj[0] != null ? obj[0].toString():null);
                bean.setFinCaja(obj[1] != null ? obj[1].toString():null);
                bean.setEntregaCert(obj[2] != null ? obj[2].toString():null);
                bean.setTiempoOperativo(obj[3] != null ? obj[3].toString():null);
                bean.setTiempoCaja(obj[4] != null ? obj[4].toString():null);
                bean.setTiempoCert(obj[5] != null ? obj[5].toString():null);
                bean.setPlaca(obj[6] != null ? obj[6].toString():null);
                bean.setLinea(obj[7] != null ? obj[7].toString():null);
                bean.setEstacionUno(obj[8] != null ? obj[8].toString():null);
                bean.setEstacionDos(obj[9] != null ? obj[9].toString():null);
                bean.setEstacionTres(obj[10] != null ? obj[10].toString():null);
                bean.setIntervalo(obj[11] != null ? obj[11].toString():null);
                bean.setCategoria(obj[12] != null ? obj[12].toString():null);
                bean.setClase(obj[13] != null ? obj[13].toString():null);
                bean.setTipoDescuento(obj[14] != null ? obj[14].toString():null);
                bean.setNombreDescuento(obj[15] != null ? obj[15].toString():null);
                lst.add(bean);
            }
        	
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        }
        
        return resultado;
    }

    public List<ResultReportBean<ComercialReporteBean>> getReporteComercialByRangeDate(Date fechIni, Date fechFin, String planta) {
    	
        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        List<ResultReportBean<ComercialReporteBean>> resultado = new ArrayList();
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        
        for(String planta_key : plantas) {

            ResultReportBean<ComercialReporteBean> reportBean = new ResultReportBean<>();

            List<ComercialReporteBean> lst = new ArrayList<>();
            
            StringBuffer hql = new StringBuffer("select DISTINCT ")
            		.append("c.ID,")
            		.append(" p2.nombre as nombre,")
            		.append(" to_char(c.fechapago,'DD/MM/YYYY hh:mi:ss') as fecha,")
            		.append(" extract(YEAR from c.fechapago) as anio,")
            		.append(" extract(MONTH from c.fechapago) as mes,")
            		.append(" extract(DAY from c.fechapago) as dia,")
            		.append(" extract(HOUR from c.fechapago) as hora,")
            		.append(" v.categoria_key as categoria,")
            		.append(" c2.abreviatura as tipoServicio,")
            		.append(" m2.nombre as marca,")
            		.append(" m3.nombre as modelo,")
            		.append(" v.aniofabricacion as anioFabricacion,")
            		.append(" p.nombrerazonsocial as razonSocial,")
            		.append(" concat(p.nombres, p.apellidos) as personaNatural,")
            		.append(" p.tipodocumentoidentidad_key as tipoDocIdent,")
            		.append(" p.nrodocumentoidentidad as docCliente,")
            		.append(" c.placamotor as placa,")
            		.append(" c.nrocomprobante as numeroFactura,")
            		.append(" c.totalsindscto as precio,")
            		.append(" c.importetotal as importeFacturado,")
            		.append(" c.totaldscto as descuento,")
            		.append(" t.nombre as tipoDescuento,")
            		.append(" c3.nombre as sinNubeNombreCampania,")
            		.append(" c4.nombre as sinNubeNombreConvenio,")
            		.append(" d2.nombredescuento as nubeNombreDescuento,")
            		.append(" c.formapago_key as tipoPago,")
            		.append(" p.telefono as telefono,")
            		.append(" p.email as correo,")
            		.append(" p.direccion as direccion,")
            		.append(" d3.nombre as distrito,")
            		.append(" i.inspeccionestado_key as estadoDocumento,")
            		.append(" i.resultado as resultadoInspeccion,")
            		.append(" a.nombre as aseguradora,")
            		.append(" to_char(v.fechiniciotarjetapropiedad,'DD/MM/YYYY') as fechaInicioSoat,")
            		.append(" to_char(v.fechfintarjetapropiedad,'DD/MM/YYYY') as fechaFinSoat,")
            		.append(" v.kilometraje as kilometraje,")
            		.append(" c5.nombre as combustible,")
            		.append(" c.comprobanteestado_key as estadoComprobante,")
            		.append(" concat(upe.nombres,' ', upe.apellidos) as usuarioCaja,")
            		.append(" concat(pereje.nombres, ' ', pereje.apellidos ) as ejecutivo,")
            		.append(" v.longitud as largo,")
            		.append(" v.ancho as ancho,")
            		.append(" v.alto as alto,")
            		.append(" rm.data ->> 'eje1' as eje1,")
            		.append(" rm.data ->> 'eje2' as eje2,")
            		.append(" rm.data ->> 'eje3' as eje3,")
            		.append(" rm.data ->> 'eje4' as eje4,")
            		.append(" rm.data ->> 'eje5' as eje5")
            		.append(" from comprobante as c")
            		.append(" left join inspeccion as i on c.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion")
            		.append(" left join vehiculo as v on i.vehiculo_nromotor = v.nromotor")
            		.append(" left join conceptoinspeccion c2 on c.conceptoinspeccion_key = c2.key")
            		.append(" left join marca m2 on v.marca_key = m2.key")
            		.append(" left join modelo m3 on v.modelo_key = m3.key")
            		.append(" left join persona p on c.cliente_nrodocumentoidentidad = p.nrodocumentoidentidad")
            		.append(" left join tipodescuento t on c.tipodescuento_key = t.key")
            		.append(" left join campania c3 on c.campania_id = c3.id")
            		.append(" left join convenio c4 on c.convenio_id = c4.id")
            		.append(" left join descuentocomprobante d2 on c.descuentocomprobante_id = d2.id")
            		.append(" left join distrito d3 on p.distrito_key = d3.key")
            		.append(" left join aseguradora a on v.aseguradora_key = a.key")
            		.append(" left join combustible c5 on v.combustible_key = c5.key")
            		.append(" left join linea l on c.linea_key = l.key")
            		.append(" left join planta p2 on l.planta_key = p2.key")
            		.append(" left join usuario as use on i.usuariocaja_username = use.username")
            		.append(" left join persona as upe on use.persona_nrodocumentoidentidad = upe.nrodocumentoidentidad")
            		.append(" left join descuentodetalle as ddt on ddt.id = CAST(d2.iddescuentodetalle as bigint)")
            		.append(" left join descuento as des on des.id = ddt.descuento_id")
            		.append(" left join usuario as eje on des.ejecutivo_username = eje.username")
            		.append(" left join persona as pereje on eje.persona_nrodocumentoidentidad = pereje.nrodocumentoidentidad")
            		.append(" left join resultado_maquina as rm on i.nrodocumentoinspeccion = rm.inspeccion_nrodocumentoinspeccion")
            		.append(" left join maquina as m on rm.maquina_id=m.id")
            		.append(" where to_date(to_char(fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
            		.append(" and c.nrocomprobante is not null")
            		.append(" and c.comprobanteestado_key <> 'ANU'")
            		.append(" and c.comprobanteestado_key is not null")
            		.append(" and (m.descripcion like '%PROFUNDIMETR%' or i.inspeccionestado_key = 'RETIRADO')")
            		.append(" and p2.key = '"+planta_key+"'")
            		.append(" and c2.key NOT in('12','43','26','27','41','42')")
            		.append(" union all")
            		.append(" select DISTINCT")
            		.append(" c.ID,")
            		.append(" p2.nombre as nombre,")
            		.append(" to_char(c.fechapago,'DD/MM/YYYY hh:mi:ss') as fecha,")
            		.append(" extract(YEAR from c.fechapago) as anio,")
            		.append(" extract(MONTH from c.fechapago) as mes,")
            		.append(" extract(DAY from c.fechapago) as dia,")
            		.append(" extract(HOUR from c.fechapago) as hora,")
            		.append(" v.categoria_key as categoria,")
            		.append(" c2.abreviatura as tipoServicio,")
            		.append(" m2.nombre as marca,")
            		.append(" m3.nombre as modelo,")
            		.append(" v.aniofabricacion as anioFabricacion,")
            		.append(" p.nombrerazonsocial as razonSocial,")
            		.append(" concat(p.nombres, p.apellidos) as personaNatural,")
            		.append(" p.tipodocumentoidentidad_key as tipoDocIdent,")
            		.append(" p.nrodocumentoidentidad as docCliente,")
            		.append(" c.placamotor as placa,")
            		.append(" c.nrocomprobante as numeroFactura,")
            		.append(" c.totalsindscto as precio,")
            		.append(" c.importetotal as importeFacturado,")
            		.append(" c.totaldscto as descuento,")
            		.append(" t.nombre as tipoDescuento,")
            		.append(" c3.nombre as sinNubeNombreCampania,")
            		.append(" c4.nombre as sinNubeNombreConvenio,")
            		.append(" d2.nombredescuento as nubeNombreDescuento,")
            		.append(" c.formapago_key as tipoPago,")
            		.append(" p.telefono as telefono,")
            		.append(" p.email as correo,")
            		.append(" p.direccion as direccion,")
            		.append(" d3.nombre as distrito,")
            		.append(" i.inspeccionestado_key as estadoDocumento,")
            		.append(" i.resultado as resultadoInspeccion,")
            		.append(" a.nombre as aseguradora,")
            		.append(" to_char(v.fechiniciotarjetapropiedad,'DD/MM/YYYY') as fechaInicioSoat,")
            		.append(" to_char(v.fechfintarjetapropiedad,'DD/MM/YYYY') as fechaFinSoat,")
            		.append(" v.kilometraje as kilometraje,")
            		.append(" c5.nombre as combustible,")
            		.append(" c.comprobanteestado_key as estadoComprobante,")
            		.append(" concat(upe.nombres,' ', upe.apellidos) as usuarioCaja,")
            		.append(" concat(pereje.nombres, ' ', pereje.apellidos ) as ejecutivo,")
            		.append(" v.longitud as largo,")
            		.append(" v.ancho as ancho,")
            		.append(" v.alto as alto,")
            		.append(" '' as eje1,")
            		.append(" '' as eje2,")
            		.append(" '' as eje3,")
            		.append(" '' as eje4,")
            		.append(" '' as eje5")
            		.append(" from comprobante as c")
            		.append(" left join inspeccion as i on c.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion")
            		.append(" left join vehiculo as v on i.vehiculo_nromotor = v.nromotor")
            		.append(" left join conceptoinspeccion c2 on c.conceptoinspeccion_key = c2.key")
            		.append(" left join marca m2 on v.marca_key = m2.key")
            		.append(" left join modelo m3 on v.modelo_key = m3.key")
            		.append(" left join persona p on c.cliente_nrodocumentoidentidad = p.nrodocumentoidentidad")
            		.append(" left join tipodescuento t on c.tipodescuento_key = t.key")
            		.append(" left join campania c3 on c.campania_id = c3.id")
            		.append(" left join convenio c4 on c.convenio_id = c4.id")
            		.append(" left join descuentocomprobante d2 on c.descuentocomprobante_id = d2.id")
            		.append(" left join distrito d3 on p.distrito_key = d3.key")
            		.append(" left join aseguradora a on v.aseguradora_key = a.key")
            		.append(" left join combustible c5 on v.combustible_key = c5.key")
            		.append(" left join linea l on c.linea_key = l.key")
            		.append(" left join planta p2 on l.planta_key = p2.key")
            		.append(" left join usuario as use on i.usuariocaja_username = use.username")
            		.append(" left join persona as upe on use.persona_nrodocumentoidentidad = upe.nrodocumentoidentidad")
            		.append(" left join descuentodetalle as ddt on ddt.id = CAST(d2.iddescuentodetalle as bigint)")
            		.append(" left join descuento as des on des.id = ddt.descuento_id")
            		.append(" left join usuario as eje on des.ejecutivo_username = eje.username")
            		.append(" left join persona as pereje on eje.persona_nrodocumentoidentidad = pereje.nrodocumentoidentidad")
            		.append(" where to_date(to_char(fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
            		.append(" and c.nrocomprobante is not null")
            		.append(" and c.comprobanteestado_key <> 'ANU'")
            		.append(" and p2.key = '"+planta_key+"'")
            		.append(" and c.comprobanteestado_key is not null")
            		.append(" and c2.key in('12','43','26','27','41','42','25')");

            Query query = entityManager.createNativeQuery(hql.toString());
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                ComercialReporteBean bean = new ComercialReporteBean();
                bean.setPlanta(obj[1] != null ? obj[1].toString():null);
                bean.setFecha(obj[2] != null ? obj[2].toString():null);
                bean.setAnio(obj[3] != null ? obj[3].toString():null);
                bean.setMes(obj[4] != null ? obj[4].toString():null);
                bean.setDia(obj[5] != null ? obj[5].toString():null);
                bean.setHora(obj[6] != null ? obj[6].toString():null);
                bean.setCategoria(obj[7] != null ? obj[7].toString():null);
                bean.setTipoServicio(obj[8] != null ? obj[8].toString():null);
                bean.setMarca(obj[9] != null ? obj[9].toString():null);
                bean.setModelo(obj[10] != null ? obj[10].toString():null);
                bean.setAnioFabricacion(obj[11] != null ? Integer.valueOf(obj[11].toString()):null);
                bean.setRazonSocial(obj[12] != null ? obj[12].toString():null);
                bean.setPersonaNatural(obj[13] != null ? obj[13].toString():null);
                bean.setTipoDocIdent(obj[14] != null ? obj[14].toString():null);
                bean.setNroDocCliente(obj[15] != null ? obj[15].toString():null);
                bean.setPlaca(obj[16] != null ? obj[16].toString():null);
                bean.setNumeroFactura(obj[17] != null ? obj[17].toString():null);
                bean.setPrecio(obj[18] != null ? Double.parseDouble(obj[18].toString()):null);
                bean.setImporteFacturado(obj[19] != null ? Double.parseDouble(obj[19].toString()):null);
                bean.setTotalDescuento(obj[20] != null ? Double.parseDouble(obj[20].toString()):null);
                bean.setTipodescuento(obj[21] != null ? obj[21].toString():null);
                bean.setNombreCampania(obj[22] != null ? obj[22].toString():null);
                bean.setNombreConvenio(obj[23] != null ? obj[23].toString():null);
                bean.setNombreDescuentoNube(obj[24] != null ? obj[24].toString():null);
                bean.setTipoPago(obj[25] != null ? obj[25].toString():null);
                bean.setTelefono(obj[26] != null ? obj[26].toString():null);
                bean.setCorreo(obj[27] != null ? obj[27].toString():null);
                bean.setDireccion(obj[28] != null ? obj[28].toString():null);
                bean.setDistrito(obj[29] != null ? obj[29].toString():null);
                bean.setEstadoDocumento(obj[30] != null ? obj[30].toString():null);
                bean.setResultado(obj[31] != null ? obj[31].toString():null);
                bean.setAseguradora(obj[32] != null ? obj[32].toString():null);
                bean.setFechaInicioSoat(obj[33] != null ? obj[33].toString():null);
                bean.setFechaFinSoat(obj[34] != null ? obj[34].toString():null);
                bean.setKilometraje(obj[35] != null ? Double.parseDouble(obj[35].toString()):null);
                bean.setCombustible(obj[36] != null ? obj[36].toString():null);
                bean.setEstadocomprobante(obj[37] != null ? obj[37].toString():null);
                bean.setDigitador(obj[38] != null ? obj[38].toString():null);
                bean.setEjecutivo(obj[39] != null ? obj[39].toString():null);
                bean.setLargo(obj[40] != null ? obj[40].toString():null);
                bean.setAncho(obj[41] != null ? obj[41].toString():null);
                bean.setAlto(obj[42] != null ? obj[42].toString():null);
                bean.setEje1(obj[43]!=null?obj[43].toString():null);
                bean.setEje2(obj[44]!=null?obj[44].toString():null);
                bean.setEje3(obj[45]!=null?obj[45].toString():null);
                bean.setEje4(obj[46]!=null?obj[46].toString():null);
                bean.setEje5(obj[47]!=null?obj[47].toString():null);
                lst.add(bean);
            }

            reportBean.setData(lst);
            reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            resultado.add(reportBean);

        }

        return resultado;
    }
       

    
    public List<ResultReportBean<ReporteComprobanteBean>> getReporteComprobantes(Date fechIni, Date fechFin, String planta_key) {

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<String> plantas = new ArrayList<>();
        if(planta_key.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta_key);
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);

        List<ResultReportBean<ReporteComprobanteBean>> resultado = new ArrayList();
        
        for(String idPlanta : plantas) {
        	
        	ResultReportBean<ReporteComprobanteBean> reporteBean = new ResultReportBean<>();
        	List<ReporteComprobanteBean> lst = new ArrayList<>();
        	
        	String hql1 = "select distinct formapago13_.nombre as col_0_0_, (comprobant0_.fechapago||'') as col_1_0_, (persona10_.nombres||' '||persona10_.apellidos) as col_2_0_,"
        			+ " persona10_.nombrerazonsocial as col_3_0_, (persona7_.nombres||' '||persona7_.apellidos) as col_4_0_, comprobant0_.placaMotor as col_5_0_,"
        			+ " inspeccion8_.nombre as col_6_0_, comprobant0_.nrocomprobante as col_7_0_, comprobant9_.nombre as col_8_0_, vehiculocl15_.nombre as col_9_0_,"
        			+ " comprobant0_.totalsindscto as col_10_0_, comprobant0_.totaldscto as col_11_0_, comprobant0_.baseimponible as col_12_0_, comprobant0_.igv as col_13_0_," 
                    + " comprobant0_.importetotal as col_14_0_, tipodescue4_.nombre as col_15_0_, descuentoc3_.nombreDescuento as col_16_0_, tipocontad2_.nombre as col_17_0_, "
                    + " pagos1_.igv as col_18_0_, pagos1_.baseimponible as col_19_0_, pagos1_.importe as col_20_0_, ot.nroot as nroot "
                    + " from comprobante comprobant0_ "
                    + " left outer join pago pagos1_ on comprobant0_.id=pagos1_.comprobante_id" 
                    + " left outer join tipocontado tipocontad2_ on pagos1_.tipoContado_key=tipocontad2_.key" 
                    + " left outer join descuentocomprobante descuentoc3_ on comprobant0_.descuentoComprobante_id=descuentoc3_.id" 
                    + " left outer join tipodescuento tipodescue4_ on comprobant0_.tipodescuento_key=tipodescue4_.key "
                    + " left outer join inspeccion inspeccion5_ on comprobant0_.inspeccion_nrodocumentoinspeccion=inspeccion5_.nrodocumentoinspeccion" 
                    + " left outer join usuario usuario6_ on inspeccion5_.usuariocaja_username=usuario6_.username "
                    + " left outer join persona persona7_ on usuario6_.persona_nrodocumentoidentidad=persona7_.nrodocumentoidentidad" 
                    + " left outer join inspeccionestado inspeccion8_ on inspeccion5_.inspeccionestado_key=inspeccion8_.key "
                    + " left outer join vehiculo vehiculo14_ on inspeccion5_.vehiculo_nromotor=vehiculo14_.nromotor "
                    + " left outer join vehiculoclase vehiculocl15_ on vehiculo14_.vehiculoclase_key=vehiculocl15_.key" 
                    + " left outer join comprobanteestado comprobant9_ on comprobant0_.comprobanteestado_key=comprobant9_.key" 
                    + " left outer join persona persona10_ on comprobant0_.cliente_nrodocumentoidentidad=persona10_.nrodocumentoidentidad" 
                    + " left outer join linea linea11_ on comprobant0_.linea_key=linea11_.key "
                    + " left outer join planta planta12_ on linea11_.planta_key=planta12_.key "
                    + " left outer join formapago formapago13_ on comprobant0_.formaPago_key=formapago13_.key"
                    + " left outer join ordentrabajo ot on comprobant0_.ordentrabajo_id = ot.id" 
                    + " where (to_date(to_char(comprobant0_.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"') and planta12_.key='"+idPlanta+"' order by comprobant0_.nrocomprobante desc";            
            Query query = entityManager.createNativeQuery(hql1);
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                ReporteComprobanteBean bean = new ReporteComprobanteBean();
                bean.setFormapago(obj[0] != null ? obj[0].toString():null);
                bean.setFecha(obj[1] != null ? obj[1].toString():null);
                bean.setClienteDni(obj[2] != null ? obj[2].toString():null);
                bean.setClienteRuc(obj[3] != null ? obj[3].toString():null);
                bean.setUsuario(obj[4] != null ? obj[4].toString():null);
                bean.setPlacamotor(obj[5] != null ? obj[5].toString():null);
                bean.setInspeccionestado(obj[6] != null ? obj[6].toString():null);
                bean.setNrocomprobante(obj[7] != null ? obj[7].toString():null);
                bean.setComprobanteestado(obj[8] != null ? obj[8].toString():null);
                bean.setVehiculoclase(obj[9] != null ? obj[9].toString():null);
                bean.setTotalsindescuento(Double.valueOf(obj[10]!=null?obj[10].toString().trim():"0"));
                bean.setDescuento(Double.valueOf(obj[11]!=null?obj[11].toString().trim():"0"));
                bean.setBase(Double.valueOf(obj[12]!=null?obj[12].toString().trim():"0"));
                bean.setIgv(Double.valueOf(obj[13]!=null?obj[13].toString().trim():"0"));
                bean.setImportetotal(Double.valueOf(obj[14]!=null?obj[14].toString().trim():"0"));
                bean.setTipDesc(obj[15] != null ? obj[15].toString():null);
                bean.setNomDescuento(obj[16] != null ? obj[16].toString():null);
                bean.setTipocontado(obj[17] != null ? obj[17].toString():null);
                bean.setIgvPago(Double.valueOf(obj[18]!=null?obj[18].toString().trim():"0"));
                bean.setBasePago(Double.valueOf(obj[19]!=null?obj[19].toString().trim():"0"));
                bean.setTotalPago(Double.valueOf(obj[20]!=null?obj[20].toString().trim():"0"));
                bean.setNroot(obj[21] != null ? obj[21].toString():null);
                lst.add(bean);
            }
            
            reporteBean.setData(lst);
            
            reporteBean.setPlanta(plantaRepository.findOneByKey(idPlanta).getNombre());
            
            resultado.add(reporteBean);
        }
        
        return resultado;
    }

    public List<ReporteMtcNewBean> getReporteMtcByRange(Date fechIni, Date fechFin){

        String anio="yyyy";
        String mes="MM";
        String dia="dd";
        SimpleDateFormat dateFormatanio = new SimpleDateFormat(anio);
        SimpleDateFormat dateFormatmes = new SimpleDateFormat(mes);
        SimpleDateFormat dateFormatdia= new SimpleDateFormat(dia);

        int anioini= Integer.parseInt(dateFormatanio.format(fechIni));
        int mesini=Integer.parseInt(dateFormatmes.format(fechIni));
        int diaini=Integer.parseInt(dateFormatdia.format(fechIni));


        int aniofin= Integer.parseInt(dateFormatanio.format(fechFin));
        int mesfin=Integer.parseInt(dateFormatmes.format(fechFin));
        int diafin=Integer.parseInt(dateFormatdia.format(fechFin));

        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        List<ReporteMtcNewBean> resultado = new ArrayList();

        String hql1="SELECT SUM(1) CANTIDAD , T2.abreviatura, T2.nombre, SUM(T2.aprobado) aprobado, SUM(T2.desaprobado) desaprobado,"
                +"SUM(tipoA) tipoA, SUM(tipoB) tipoB, SUM(tipoC) tipoC, SUM(tipoD) tipoD, SUM(tipoE) tipoE,"
                +"SUM(tipoF) tipoF, SUM(tipoG) tipoG, SUM(tipoH) tipoH, SUM(tipoI) tipoI, SUM(tipoJ) tipoJ,"
                +"SUM(tipoK) tipoK"
                +" FROM (SELECT  T1.abreviatura, T1.nombre, T1.nrodocumentoinspeccion, MAX(T1.aprobado) aprobado, MAX(T1.desaprobado) desaprobado, substr(MAX(d.codigovalor),1,1),"
                +"(case when substr(MAX(d.codigovalor),1,1) ='A' then 1 else 0 end) tipoA,"
                +"(case when substr(MAX(d.codigovalor),1,1)='B' then 1 else 0 end) tipoB,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='C' then 1 else 0 end) tipoC,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='D' then 1 else 0 end) tipoD,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='E' then 1 else 0 end) tipoE,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='F' then 1 else 0 end) tipoF,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='G' then 1 else 0 end) tipoG,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='H' then 1 else 0 end) tipoH,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='I' then 1 else 0 end) tipoI,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='J' then 1 else 0 end) tipoJ,"
                +"(case when substr(MAX(d.codigovalor),1,1) ='K' then 1 else 0 end) tipoK"
                +" FROM (select C2.abreviatura, p.nombre, (i.nrodocumentoinspeccion) nrodocumentoinspeccion, (case when i.resultado='A' then 1 else 0 end) aprobado, (case when i.resultado='D' then 1 else 0 end) desaprobado"
                +" from comprobante c"
                +" inner join conceptoinspeccion c2 on c.conceptoinspeccion_key=c2.key"
                +" inner join inspeccion i on c.inspeccion_nrodocumentoinspeccion=i.nrodocumentoinspeccion"
                +" inner join linea l on c.linea_key=l.key"
                +" inner join planta p on l.planta_key=p.key"
                +" WHERE 1 = 1"
                +" and extract(year from i.fechconsolidado)="+anioini
                +" and extract(month from i.fechconsolidado)="+ mesini
                +" AND i.inspeccionestado_key!='ANU' and i.inspeccionestado_key!='RETIRADO'"
                +" GROUP BY C2.abreviatura, p.nombre, i.nrodocumentoinspeccion ) T1 left  join resultado_maquina rm on T1.nrodocumentoinspeccion=rm.inspeccion_nrodocumentoinspeccion"
                +" left  JOIN MAQUINA MAQ ON  RM.maquina_id=MAQ.ID AND MAQ.descripcion LIKE '%VISUAL%'"
                +" left   JOIN  resultado_maquina_defecto rmd on rm.id=rmd.resultado_maquina_id"
                +" left JOIN  defecto d on rmd.defectos_id=d.id"
                +" WHERE  1 = 1 GROUP BY T1.abreviatura, T1.nombre, T1.nrodocumentoinspeccion ,T1.aprobado,T1.desaprobado) T2"
                +" GROUP BY T2.abreviatura, T2.nombre";
        Query query = entityManager.createNativeQuery(hql1);
        System.out.println(hql1);
        List<Object> result = (List<Object>) query.getResultList();
        Iterator<Object> itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            ReporteMtcNewBean bean = new ReporteMtcNewBean();
            bean.setTotal(Integer.valueOf(obj[0]!=null?obj[0].toString():"0"));
            bean.setAbreviatura(obj[1]!=null?obj[1].toString():"-");
            bean.setPlanta(obj[2]!=null?obj[2].toString():"-");
            bean.setAprobado(Integer.valueOf(obj[3]!=null?obj[3].toString():"0"));
            bean.setDesaprobado(Integer.valueOf(obj[4]!=null?obj[4].toString():"0"));
            bean.setDefectoA(Integer.valueOf(obj[5]!=null?obj[5].toString():"0"));
            bean.setDefectoB(Integer.valueOf(obj[6]!=null?obj[6].toString():"0"));
            bean.setDefectoC(Integer.valueOf(obj[7]!=null?obj[7].toString():"0"));
            bean.setDefectoD(Integer.valueOf(obj[8]!=null?obj[8].toString():"0"));
            bean.setDefectoE(Integer.valueOf(obj[9]!=null?obj[9].toString():"0"));
            bean.setDefectoF(Integer.valueOf(obj[10]!=null?obj[10].toString():"0"));
            bean.setDefectoG(Integer.valueOf(obj[11]!=null?obj[11].toString():"0"));
            bean.setDefectoH(Integer.valueOf(obj[12]!=null?obj[12].toString():"0"));
            bean.setDefectoI(Integer.valueOf(obj[13]!=null?obj[13].toString():"0"));
            bean.setDefectoJ(Integer.valueOf(obj[14]!=null?obj[14].toString():"0"));
            bean.setDefectoK(Integer.valueOf(obj[15]!=null?obj[15].toString():"0"));
            resultado.add(bean);
        }

        return resultado;
    }
    
    public List<ResultReportBean<ReporteOperatividadBean>> getOperatividadByDateRangeByPlanta(Date fechIni, Date fechFin,
            String planta) {

    	Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");
    	
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
    	Timestamp fechaMax = new Timestamp(fechFin.getTime());
    	
    	List<ResultReportBean<ReporteOperatividadBean>> resultado = new ArrayList<>();
    	
    	List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }
        
    	for(String planta_key : plantas) {
    		ResultReportBean<ReporteOperatividadBean> bean = new ResultReportBean<>();
    		List<ReporteOperatividadBean> list = new ArrayList();
        	String hql1 = "SELECT new " + ReporteOperatividadBean.class.getName()
        			+ "(ins.nrodocumentoinspeccion,ins.nrodocumentoreinspeccion,ins.resultado,maq.descripcion,rmaq.manual,li.nombre)"
        			+ " FROM Comprobante com"
        			+ " left join com.inspeccion ins"
        			+ " left join ins.inspeccionestado ie"
        			+ " left join ins.resultadosMaquina rmaq"
        			+ " left join rmaq.maquina maq"
        			+ " inner join com.linea li"
        			+ " inner join li.planta pl"
        			+ " where to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
        			+ " and pl.key = :planta_key"
        			+ " and ie != :inspeccionestadoanulado"
        			+ " order by li.nombre, maq.descripcion ";
        	
        	Query query = entityManager.createQuery(hql1);
        	
        	query.setParameter("fechaMin", fechaMin);
        	query.setParameter("fechaMax", fechaMax);
        	query.setParameter("planta_key", planta_key);
        	query.setParameter("inspeccionestadoanulado", inspeccionestadoAnulado);
        	list = query.getResultList();
        	
        	for(int i=0;i<list.size();i++){
    			for(int j=0;j<list.size()-1;j++){
    				if(i!=j){
    					if(list.get(i).getInspeccion().equals(list.get(j).getInspeccion())){
    						list.get(i).setInspeccion("");
    						list.get(i).setResult(null);
    						list.get(i).setReins(null);
    					}
    				}
    			}
    		}
        	
        	bean.setData(list);
        	bean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(bean);
    		
    	}
    	
    	return resultado;
    }

    /**
     * Calculo del reporte MTC de todos los conceptos de inspección.
     *
     * @param //fech
     * @param //planta_key
     * @return
     */
    public List<ResultReportBean<ReporteMtcBean>> getReporteMtcByDateBy(Date fechIni, Date fechFin, String planta) {
        List<Conceptoinspeccion> conceptoinspeccions = conceptoinspeccionRepository.findAll();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechIni);
        int year = calendar.get(Calendar.YEAR);

        SimpleDateFormat dtfM = new SimpleDateFormat("MMMMM", new Locale("es", "ES"));
        String month = dtfM.format(fechIni);

        List<ResultReportBean<ReporteMtcBean>> resultado = new ArrayList<>();



        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        for(String planta_key : plantas) {
            ResultReportBean<ReporteMtcBean> bean = new ResultReportBean<>();
            List<ReporteMtcBean> reporteMtcBeans = new ArrayList();
            for (Conceptoinspeccion conceptoinspeccion : conceptoinspeccions) {
                String concepto_key = conceptoinspeccion.getKey();
                Long totalInspAprobada = getReporteMtcTotalInspeccionesADByDate(fechIni,fechFin, planta_key, concepto_key, "A");
                Long totalInspDesaprobada = getReporteMtcTotalInspeccionesADByDate(fechIni,fechFin, planta_key, concepto_key, "D");

                Long totalIns = totalInspAprobada + totalInspDesaprobada;

                Long indiceAprobado1 = getReporteMtcIndiceAprobado(fechIni,fechFin, concepto_key, planta_key, 1);
                Long indiceAprobado2 = getReporteMtcIndiceAprobado(fechIni,fechFin, concepto_key, planta_key, 2);
                Long indiceAprobado3 = getReporteMtcIndiceAprobado(fechIni,fechFin, concepto_key, planta_key, 3);

                String defectoDesaprobado0 = getReporteMtcDefectoDesaprobado(fechIni,fechFin, concepto_key, planta_key, 0);
                String defectoDesaprobado1 = getReporteMtcDefectoDesaprobado(fechIni,fechFin, concepto_key, planta_key, 1);
                String defectoDesaprobado2 = getReporteMtcDefectoDesaprobado(fechIni,fechFin, concepto_key, planta_key, 2);


                ReporteMtcBean reporteMtcBean = new ReporteMtcBean(conceptoinspeccion.getAbreviatura(), year, month, totalIns,
                        indiceAprobado1, indiceAprobado2, indiceAprobado3, totalInspAprobada, defectoDesaprobado0,
                        defectoDesaprobado1, defectoDesaprobado2, totalInspDesaprobada);
                reporteMtcBeans.add(reporteMtcBean);

            }

            bean.setData(reporteMtcBeans);
            bean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            resultado.add(bean);
        }

        return resultado;
    }


    /**
     * Obtiene la primera letra del defecto que mas se repite en orden
     * descendente.
     *
     * @param //fech
     * @param concepto_key
     * @param planta_key
     * @param pos          empieza en cero
     * @return
     */
    public String getReporteMtcDefectoDesaprobado(Date fechIni, Date fechFin, String concepto_key, String planta_key, int pos) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechIni);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");
        String hql1 = "SELECT new " + ReporteMtcDefectoDesaprobadoBean.class.getName()
                + " (count(ins),substring(defecto.codigovalor,1,1))"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " inner join ins.resultadosMaquina rm"
                + " inner join rm.defectos defecto"
                + " left join com.conceptoInspeccion ci"
                + " WHERE year(ins.fechconsolidado) = :year and month(ins.fechconsolidado) = :month"
                + " and ins.resultado = 'D' and pl.key = :planta_key"
                + " and ins.inspeccionestado != :inspeccionestadoAnulado and ci.key = :concepto_key"
                + " group by substring(defecto.codigovalor,1,1)"
                + " order by count(ins) desc";

        Query query = entityManager.createQuery(hql1);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("planta_key", planta_key);
        query.setParameter("inspeccionestadoAnulado", inspeccionestadoAnulado);
        query.setParameter("concepto_key", concepto_key);

        List<ReporteMtcDefectoDesaprobadoBean> resultado = new ArrayList();
        resultado = query.getResultList();

        String letter = (resultado.size() > pos)
                ? ((resultado.get(pos) != null) ? resultado.get(pos).getDefectoValor() : "") : "";
        return letter;
    }

    /**
     * Obtiene la cantidad de inspeccciones aprobadas en que pasada de
     * inspeccion, primera, segunda, n
     *
     * @param //fech
     * @param concepto_key
     * @param planta_key
     * @param indice
     * @return
     */
    public Long getReporteMtcIndiceAprobado(Date fechIni, Date fechFin, String concepto_key, String planta_key, int indice) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechIni);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

        String hql1 = "SELECT new " + ReporteMtcIndiceAprobadoBean.class.getName()
                + " (count(ins),ins.indiceDesaprobado)"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " inner join com.conceptoInspeccion ci"
                + " WHERE year(ins.fechconsolidado) = :year and month(ins.fechconsolidado) = :month"
                + " and ins.resultado = 'A'" + " and pl.key = :planta_key"
                + " and ins.inspeccionestado != :inspeccionestadoAnulado and ci.key = :concepto_key"
                + " and ins.indiceDesaprobado = :indiceDesaprobado" + " group by ins.indiceDesaprobado";

        Query query = entityManager.createQuery(hql1);

        List<ReporteMtcIndiceAprobadoBean> resultado = new ArrayList();

        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("planta_key", planta_key);
        query.setParameter("inspeccionestadoAnulado", inspeccionestadoAnulado);
        query.setParameter("concepto_key", concepto_key);
        query.setParameter("indiceDesaprobado", indice);
        query.setParameter("indiceDesaprobado", indice);
        resultado = query.getResultList();

        Long cantidad = (long) 0;
        for (ReporteMtcIndiceAprobadoBean rmia : resultado) {
            cantidad += rmia.getCantidad();
        }
        return cantidad;
    }


    /**
     * Cantidad de Inspecciones aprobadas, o desaprobadas dependiendo el
     * parámetro aprobadoDesaprobado
     *
     * @param //fech
     * @param planta_key
     * @param concepto_key
     * @param aprobadoDesaprobado
     * @return
     */
    public Long getReporteMtcTotalInspeccionesADByDate(Date fechIni, Date fechFin, String planta_key, String concepto_key,
                                                       String aprobadoDesaprobado) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechIni);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");
        String hqlSum = "SELECT count(ins)"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " inner join ins.inspeccionestado ie"
                + " inner join com.conceptoInspeccion ci"
                + " WHERE year(ins.fechconsolidado) = :year and month(ins.fechconsolidado) = :month"
                + " and ci.key = :concepto_key and ins.resultado = :aprobadoDesaprobado"
                + " and ins.inspeccionestado != :inspeccionestadoAnulado and pl.key = :planta_key";

        TypedQuery<Long> query = entityManager.createQuery(hqlSum, Long.class);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("concepto_key", concepto_key);
        query.setParameter("aprobadoDesaprobado", aprobadoDesaprobado);
        query.setParameter("inspeccionestadoAnulado", inspeccionestadoAnulado);
        query.setParameter("planta_key", planta_key);
        Long cantidadInspecciones = query.getSingleResult();
        System.out.println(hqlSum);

        return cantidadInspecciones;
    }
    
    public List<ReporteCortesiaBean> getReporteCortesias(Date fechIni, Date fechFin){    	
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
    	
        String hql1 = "SELECT new " + ReporteCortesiaBean.class.getName()
        		+"(com.nrocomprobante,com.importetotal,com.igv,com.baseimponible,com.placaMotor,com.fechapago,pl.nombre)"
        		+ " FROM Comprobante com"
        		+ " inner join com.tipodescuento tipdes"
        		+ " inner join com.linea li"
        		+ " inner join li.planta pl"
        		+ " where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax and"
        		+ " tipdes.key = 'corte' "
        		+ " order by com.fechapago asc ";
        Query query = entityManager.createQuery(hql1);

        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        
    	return query.getResultList();
    }


    public List<ResultReportBean<ReporteFinanzasBean>> getReporteAllFinanzas(Date fechIni, Date fechFin,String planta) {
    	
    	List<ResultReportBean<ReporteFinanzasBean>> resultLst = new ArrayList<>();

        Date fechapago= new Date();

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        SimpleDateFormat dFt = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
        
        
        for(String idPlanta : plantas) {
        	ResultReportBean<ReporteFinanzasBean> reporteBean = new ResultReportBean<>();
        	List<ResultReportBean<ReporteFinanzasBean>> resultado1 = new ArrayList();
        	List<ReporteFinanzasBean> resultado = new ArrayList();
			List<ReporteFinanzasBean> list = new ArrayList<>();
        	
        	StringBuffer hql1 =  new StringBuffer("select fp.nombre as formapago,")
        			.append(" tipcon.nombre as tipocontado,p.nombrerazonsocial as razonsocial,")
        			.append(" com.fechapago as fecha, concat(pe.nombres, ' ', pe.apellidos) as usuario,")
        			.append(" com.placamotor as placamotor,incert.nrodocumentocertificado as nrocertificado, ")
        			.append(" ins.nrodocumentoinforme as nroinforme,ie.nombre as inspeccionestado, ")
        			.append(" com.nrocomprobante as nrocomprobante,ce.nombre as comprobanteestado, ")
        			.append(" pag.nrooperacionbanco as nroopbanco,pag.nrooperaciontarjeta as nrooptarjeta,")
        			.append(" vc.nombre as vehiculoclase,pag.baseimponible as baseimponible, ")
        			.append(" pag.igv as igv,pag.importe as importe, ")
        			.append(" tipdes.nombre as tipodescuento, com.estado as estado, ")
        			.append(" descom.nombredescuento as nombresecuento, pag.id as idpago, ")
        			.append(" case when com.formapago_key ='credito' THEN com.baseimponible end as baseimponpen,")
        			.append(" case when com.formapago_key ='credito' THEN com.igv end as igvpen,")
        			.append(" case when com.formapago_key ='credito' THEN com.importetotal end as importetotalpen,")
        			.append(" ot.nroot as nroOt")
        			.append(" from comprobante com")
        			.append(" left join pago pag on com.id = pag.comprobante_id")
        			.append(" left join descuentocomprobante descom on com.descuentocomprobante_id = descom.id")
        			.append(" left join tipodescuento tipdes on com.tipodescuento_key = tipdes.key")
        			.append(" left join tipocontado tipcon on pag.tipocontado_key = tipcon.key")
        			.append(" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion")
        			.append(" left join certificado incert on ins.nrodocumentoinspeccion = incert.inspeccion_nrodocumentoinspeccion")
        			.append(" left join usuario usuacaja on ins.usuariocaja_username = usuacaja.username")
        			.append(" left join usuario personacaja on usuacaja.username=personacaja.username")
        			.append(" left join inspeccionestado ie on ins.inspeccionestado_key = ie.key")
        			.append(" left join comprobanteestado ce on com.comprobanteestado_key = ce.key")
        			.append(" left join persona p on com.cliente_nrodocumentoidentidad = p.nrodocumentoidentidad")
        			.append(" left join persona pe on usuacaja.persona_nrodocumentoidentidad=pe.nrodocumentoidentidad")
        			.append(" left join linea li on com.linea_key = li.key")
        			.append(" left join planta pl on li.planta_key = pl.key")
        			.append(" left join conceptoinspeccion ci on com.conceptoinspeccion_key = ci.key")
        			.append(" left join formapago fp on com.formapago_key = fp.key")
        			.append(" left join vehiculo v on ins.vehiculo_nromotor = v.nromotor")
        			.append(" left join ordentrabajo ot on com.ordentrabajo_id = ot.id")
        			.append(" left join vehiculoclase vc on v.vehiculoclase_key = vc.key")
        			.append(" where  to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" +dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
        			.append(" and ce.key!='ANU' AND com.importetotal!=0 and (tipdes.nombre is null or descom.nombredescuento not like '%Cuponidad%')")
        			.append(" and pl.key='"+ idPlanta +"'")
        			.append(" ORDER BY concat(fp.nombre,' ',tipcon.nombre)");            
            Query query = entityManager.createNativeQuery(hql1.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator();
            List<ReporteFinanzasBean> unique = new ArrayList<>();
            while (itr.hasNext()) {
				ReporteFinanzasBean bean = new ReporteFinanzasBean();
				Object[] obj = (Object[]) itr.next(); 
				if(obj[10].toString().equalsIgnoreCase("Pendiente")) {
					bean.setFormapago("Credito");									
				}else {
					bean.setFormapago(obj[0]!=null ? obj[0].toString():"");	
					}
				bean.setTipocontado(obj[1]!=null ? obj[1].toString():"");
				bean.setEntidad(obj[2]!=null ? obj[2].toString():"");
				if (obj[3]!=null) {
					try {
						fechapago=dFt.parse(obj[3].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setFecha(fechapago);
				}
				bean.setUsuario(obj[4]!=null ? obj[4].toString():"");
				bean.setPlacamotor(obj[5]!=null ? obj[5].toString():"");
				bean.setNrodocumentocertificado(obj[6]!=null ? obj[6].toString():"");
				bean.setNrodocumentoinforme(obj[7]!=null ? obj[7].toString():"");
				bean.setInspeccionestado(obj[8]!=null ? obj[8].toString():"");
				bean.setNrocomprobante(obj[9]!=null ? obj[9].toString():"");
				bean.setComprobanteestado(obj[10]!=null ? obj[10].toString():"");
				bean.setNrooperacionBanco(obj[11]!=null ? obj[11].toString():"");
				bean.setNrooperacionTarjeta(obj[12]!=null ? obj[12].toString():"");
				bean.setVehiculoclase(obj[13]!=null ? obj[13].toString():"");
				if(obj[0].toString().equalsIgnoreCase("contado")||obj[0].toString().equalsIgnoreCase("Pago Adelantado")) {
					bean.setNeto(obj[14] != null ? Double.valueOf(obj[14].toString()):0);
					bean.setImpuesto(obj[15] != null ? Double.valueOf(obj[15].toString()):0);
					bean.setImportetotal(obj[16] != null ? Double.valueOf(obj[16].toString()):0);
				}else {
					bean.setNeto(obj[21] != null ? Double.valueOf(obj[21].toString()):0);
					bean.setImpuesto(obj[22] != null ? Double.valueOf(obj[22].toString()):0);
					bean.setImportetotal(obj[23] != null ? Double.valueOf(obj[23].toString()):0);
				}
				
				bean.setTipDesc(obj[17]!=null ? obj[17].toString():"");
				bean.setDoblemodalidad(obj[18]!=null? Boolean.valueOf(obj[18].toString()):false);
				bean.setNombreDescuento(obj[19]!=null ? obj[19].toString():"");
				bean.setIdPago(obj[20]!=null ?Long.valueOf(obj[20].toString()):0);
				bean.setNroOt(obj[24]!=null ? obj[24].toString():"");
				list.add(bean);
				
				
			}
            
            
            for(ReporteFinanzasBean bean : list)
            {
            	/*if(bean.getTipocontado() != null) {
            		bean.setFormapago(bean.getTipocontado());
            	}*/ 
            	boolean find  =false;
            	for(ReporteFinanzasBean filter: unique)
            	{
            		if( filter.getIdPago() != null && filter.getIdPago().equals(bean.getIdPago())&&bean.getIdPago()!=0)
            		{
            			find = true;
            			break;
            		}
            	}
            	
            	if(!find) {
            		if(bean.getTipocontado() != null && !bean.getTipocontado().isEmpty()) {
            			if(bean.getFormapago().equalsIgnoreCase("contado")) {
            				bean.setFormapago(bean.getTipocontado());
            			}
            		}
            		unique.add(bean);
            	}
            }
            
            Collections.sort(unique);
            reporteBean.data =  unique;
            reporteBean.setPlanta(plantaRepository.findOneByKey(idPlanta).getNombre());
            resultLst.add(reporteBean);
        }
        
        return resultLst;
    }
public List<ResultReportBean<ReporteFinanzasBean>> getReporteAllFinanzasCuponidad(Date fechIni, Date fechFin,String planta) {
    	
    	List<ResultReportBean<ReporteFinanzasBean>> resultLst = new ArrayList<>();

        Date fechapago= new Date();

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");

        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        SimpleDateFormat dFt = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
        
        
        for(String idPlanta : plantas) {
        	ResultReportBean<ReporteFinanzasBean> reporteBean = new ResultReportBean<>();
        	List<ResultReportBean<ReporteFinanzasBean>> resultado1 = new ArrayList();
        	List<ReporteFinanzasBean> resultado = new ArrayList();
			List<ReporteFinanzasBean> list = new ArrayList<>();
        	
        	StringBuffer hql1 =  new StringBuffer("select fp.nombre as formapago,")
        			.append(" tipcon.nombre as tipocontado,p.nombrerazonsocial as razonsocial,")
        			.append(" com.fechapago as fecha, concat(pe.nombres, ' ', pe.apellidos) as usuario,")
        			.append(" com.placamotor as placamotor,incert.nrodocumentocertificado as nrocertificado, ")
        			.append(" ins.nrodocumentoinforme as nroinforme,ie.nombre as inspeccionestado, ")
        			.append(" com.nrocomprobante as nrocomprobante,ce.nombre as comprobanteestado, ")
        			.append(" pag.nrooperacionbanco as nroopbanco,pag.nrooperaciontarjeta as nrooptarjeta,")
        			.append(" vc.nombre as vehiculoclase,pag.baseimponible as baseimponible, ")
        			.append(" pag.igv as igv,pag.importe as importe, ")
        			.append(" tipdes.nombre as tipodescuento, com.estado as estado, ")
        			.append(" descom.nombredescuento as nombresecuento, pag.id as idpago, ")
        			.append(" case when com.formapago_key ='credito' THEN com.baseimponible end as baseimponpen,")
        			.append(" case when com.formapago_key ='credito' THEN com.igv end as igvpen,")
        			.append(" case when com.formapago_key ='credito' THEN com.importetotal end as importetotalpen,")
        			.append(" ot.nroot as nroOt")
        			.append(" from comprobante com")
        			.append(" left join pago pag on com.id = pag.comprobante_id")
        			.append(" left join descuentocomprobante descom on com.descuentocomprobante_id = descom.id")
        			.append(" left join tipodescuento tipdes on com.tipodescuento_key = tipdes.key")
        			.append(" left join tipocontado tipcon on pag.tipocontado_key = tipcon.key")
        			.append(" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion")
        			.append(" left join certificado incert on ins.nrodocumentoinspeccion = incert.inspeccion_nrodocumentoinspeccion")
        			.append(" left join usuario usuacaja on ins.usuariocaja_username = usuacaja.username")
        			.append(" left join usuario personacaja on usuacaja.username=personacaja.username")
        			.append(" left join inspeccionestado ie on ins.inspeccionestado_key = ie.key")
        			.append(" left join comprobanteestado ce on com.comprobanteestado_key = ce.key")
        			.append(" left join persona p on com.cliente_nrodocumentoidentidad = p.nrodocumentoidentidad")
        			.append(" left join persona pe on usuacaja.persona_nrodocumentoidentidad=pe.nrodocumentoidentidad")
        			.append(" left join linea li on com.linea_key = li.key")
        			.append(" left join planta pl on li.planta_key = pl.key")
        			.append(" left join conceptoinspeccion ci on com.conceptoinspeccion_key = ci.key")
        			.append(" left join formapago fp on com.formapago_key = fp.key")
        			.append(" left join vehiculo v on ins.vehiculo_nromotor = v.nromotor")
        			.append(" left join ordentrabajo ot on com.ordentrabajo_id = ot.id")
        			.append(" left join vehiculoclase vc on v.vehiculoclase_key = vc.key")
        			.append(" where  to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" +dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
        			.append(" and ce.key!='ANU' AND com.importetotal!=0  and descom.nombredescuento like'%Cuponidad%'")
        			.append(" and pl.key='"+ idPlanta +"'")
        			.append(" ORDER BY concat(fp.nombre,' ',tipcon.nombre)");            
            Query query = entityManager.createNativeQuery(hql1.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator();
            List<ReporteFinanzasBean> unique = new ArrayList<>();
            while (itr.hasNext()) {
				ReporteFinanzasBean bean = new ReporteFinanzasBean();
				Object[] obj = (Object[]) itr.next(); 
				if(obj[10].toString().equalsIgnoreCase("Pendiente")) {
					bean.setFormapago("Credito");									
				}else {
					bean.setFormapago(obj[0]!=null ? obj[0].toString():"");	
					}
				bean.setTipocontado(obj[1]!=null ? obj[1].toString():"");
				bean.setEntidad(obj[2]!=null ? obj[2].toString():"");
				if (obj[3]!=null) {
					try {
						fechapago=dFt.parse(obj[3].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setFecha(fechapago);
				}
				bean.setUsuario(obj[4]!=null ? obj[4].toString():"");
				bean.setPlacamotor(obj[5]!=null ? obj[5].toString():"");
				bean.setNrodocumentocertificado(obj[6]!=null ? obj[6].toString():"");
				bean.setNrodocumentoinforme(obj[7]!=null ? obj[7].toString():"");
				bean.setInspeccionestado(obj[8]!=null ? obj[8].toString():"");
				bean.setNrocomprobante(obj[9]!=null ? obj[9].toString():"");
				bean.setComprobanteestado(obj[10]!=null ? obj[10].toString():"");
				bean.setNrooperacionBanco(obj[11]!=null ? obj[11].toString():"");
				bean.setNrooperacionTarjeta(obj[12]!=null ? obj[12].toString():"");
				bean.setVehiculoclase(obj[13]!=null ? obj[13].toString():"");
				if(obj[0].toString().equalsIgnoreCase("contado")||obj[0].toString().equalsIgnoreCase("Pago Adelantado")) {
					bean.setNeto(obj[14] != null ? Double.valueOf(obj[14].toString()):0);
					bean.setImpuesto(obj[15] != null ? Double.valueOf(obj[15].toString()):0);
					bean.setImportetotal(obj[16] != null ? Double.valueOf(obj[16].toString()):0);
				}else {
					bean.setNeto(obj[21] != null ? Double.valueOf(obj[21].toString()):0);
					bean.setImpuesto(obj[22] != null ? Double.valueOf(obj[22].toString()):0);
					bean.setImportetotal(obj[23] != null ? Double.valueOf(obj[23].toString()):0);
				}
				
				bean.setTipDesc(obj[17]!=null ? obj[17].toString():"");
				bean.setDoblemodalidad(obj[18]!=null? Boolean.valueOf(obj[18].toString()):false);
				bean.setNombreDescuento(obj[19]!=null ? obj[19].toString():"");
				bean.setIdPago(obj[20]!=null ?Long.valueOf(obj[20].toString()):0);
				bean.setNroOt(obj[24]!=null ? obj[24].toString():"");
				list.add(bean);
				
				
			}
            
            
            for(ReporteFinanzasBean bean : list)
            {
            	/*if(bean.getTipocontado() != null) {
            		bean.setFormapago(bean.getTipocontado());
            	}*/ 
            	boolean find  =false;
            	for(ReporteFinanzasBean filter: unique)
            	{
            		if( filter.getIdPago() != null && filter.getIdPago().equals(bean.getIdPago())&&bean.getIdPago()!=0)
            		{
            			find = true;
            			break;
            		}
            	}
            	
            	if(!find) {
            		if(bean.getTipocontado() != null && !bean.getTipocontado().isEmpty()) {
            			if(bean.getFormapago().equalsIgnoreCase("contado")) {
            				bean.setFormapago(bean.getTipocontado());
            			}
            		}
            		unique.add(bean);
            	}
            }
            
            Collections.sort(unique);
            reporteBean.data =  unique;
            reporteBean.setPlanta(plantaRepository.findOneByKey(idPlanta).getNombre());
            resultLst.add(reporteBean);
        }
        
        return resultLst;
    }
    
    public List<String> getAllIdPlanta(){
	    	List<String> lstPlantas = new ArrayList<>();
	    	String hq = "select pl.key from Planta pl";
	    	Query query = entityManager.createQuery(hq);
	    	lstPlantas = query.getResultList();
	    	
	    	return lstPlantas;
    }
    
    public List<String> getAllIdPlantaByUser(){
    	
    		String userName = Util.getUserNameContext();
    		List<String> lstPlantas = new ArrayList<>();
    		
    		if(userName!=null)
    		{
    			Usuario usuario = usuarioService.getUsuario(userName);
    			
    			for(Planta p : usuario.plantas)
    			{
    				lstPlantas.add(p.getKey());
    			}
    			
	    	    	return lstPlantas;
	    	    	
    		}else {
    			return null;
    		}
    	
    }

	public List<Cierrecaja> getReporteCierreCaja(Date fech) {
		// TODO Auto-generated method stub
		Timestamp initDay = Util.initDay(fech);
        Timestamp finDay = Util.finDay(fech);
        
        List<Cierrecaja> resultado = new ArrayList();
        
        String hql1 = "SELECT cj FROM Cierrecaja cj"
        		+ " where cj.fechcreacion >= :fechaMin and cj.fechcreacion <= :fechaMax"
        		+ " order by cj.planta asc";
        Query query = entityManager.createQuery(hql1);
        
        query.setParameter("fechaMin", initDay);
        query.setParameter("fechaMax", finDay);
        
        List<Cierrecaja> resultadoParcial = query.getResultList();
        
        return resultadoParcial;
	}

    public List<ReporteCuentasPorCobrar> getReporteCuentasPorCobrar(Date fechIni, Date fechFin, String planta, String nroruc, String formapago) {
            Timestamp fechaMin = new Timestamp(fechIni.getTime());
            Timestamp fechaMax = new Timestamp(fechFin.getTime());
            List<ReporteCuentasPorCobrar> resultado = new ArrayList();
        Date fechacreacion= new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);


        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        //for (String id_planta : plantas){
            String hql ="SELECT emp.nombre as empresa,emp.ruc,tipdi.nombre as tipodocumentoidentidad,cli.nrodocumentoidentidad,pl.nombre as planta,fp.nombre as formapago,cli.nombres ,cli.apellidos,cli.nombrerazonsocial,"
                    +" com.fechapago,com.placaMotor,cert.nrodocumentoCertificado,ins.nrodocumentoInforme,ci.abreviatura,car.nombre as carroceria,"
                    +" com.nrocomprobante,tipdo.key,com.importetotal,com.ordenservicio,veh.kilometraje, concat(per.nombres,' ', per.apellidos ) as nombreejecutivo"
                    +" FROM comprobante com inner join comprobanteestado coes on com.comprobanteestado_key = coes.key"
                    +" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion"
                    +" left join certificado cert on ins.nrodocumentoinspeccion = cert.inspeccion_nrodocumentoinspeccion"
                    +" inner join tipodocumento tipdo on com.tipodocumento_key = tipdo.key"
                    +" inner join empresa emp on com.empresa_key = emp.key"
                    +" inner join persona cli on com.cliente_nrodocumentoidentidad = cli.nrodocumentoidentidad"
                    +" inner join conceptoinspeccion ci on com.conceptoinspeccion_key = ci.key"
                    +" left join tipodocumentoidentidad tipdi on cli.tipodocumentoidentidad_key = tipdi.key"
                    +" inner join formapago fp on com.formapago_key = fp.key"
                    +" inner join linea li on com.linea_key = li.key"
                    +" inner join planta pl on li.planta_key = pl.key"
                    +" inner join vehiculo veh on ins.vehiculo_nromotor = veh.nromotor"
                    +" left join carroceria car on veh.carroceria_key = car.key"
                    +" inner join descuentocomprobante dcom on com.descuentocomprobante_id = dcom.id"
                    +" inner join descuentodetalle ddtll on cast(dcom.iddescuentodetalle as int)= cast(ddtll.id as int)"
                    +" inner join descuento dscto on ddtll.descuento_id = dscto.id"
                    +" inner join usuario use on dscto.ejecutivo_username = use.username"
                    +" inner join persona per on use.persona_nrodocumentoidentidad=per.nrodocumentoidentidad"
                    +" where to_date(to_char(com.fechapago, 'YYYY-MM-DD'),'YYYY-MM-DD') between '" + dateFormat.format(fechIni)+ "' and " + " '" + dateFormat.format(fechFin) + "'";
                    if(!planta.equals("todos")){
                        hql+=" and pl.key='"+planta+"'";
                    }
                    if(formapago != null && !formapago.isEmpty() &&!formapago.equals("todos")){
                        hql+=" and fp.key='"+formapago+"'";
                    }
                    if(nroruc != null && !nroruc.isEmpty()){
                        hql +=" and cli.nrodocumentoidentidad='"+nroruc+"'";
                    }
                    hql+=" and com.comprobanteestado_key != 'ANU' order by cli.nrodocumentoidentidad, emp.nombre asc";

            Query query = entityManager.createNativeQuery(hql.toString());
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();

            while (itr.hasNext()){
                ReporteCuentasPorCobrar bean= new ReporteCuentasPorCobrar();
                Object[] obj = (Object[]) itr.next();
                bean.setRazonfarenet(obj[0]!=null? obj[0].toString():"");
                bean.setNroRucFarenet(obj[1]!=null? obj[1].toString():"");
                bean.setTipodocumento(obj[2]!=null? obj[2].toString():"");
                bean.setNroruc(obj[3]!=null? obj[3].toString():"");
                bean.setPlanta(obj[4]!=null? obj[4].toString():"");
                bean.setFormaPago(obj[5]!=null? obj[5].toString():"");
                bean.setEntidadNombre(obj[6]!=null? obj[6].toString():"");
                bean.setEntidadApellido(obj[7]!=null? obj[7].toString():"");
                bean.setEntidadRazon(obj[8]!=null? obj[8].toString():"");
                try {
                    fechacreacion = format.parse(obj[9].toString());
                }catch (ParseException pe){
                    pe.printStackTrace();
                }
                bean.setFechaPago(fechacreacion);
                bean.setPlaca(obj[10]!= null? obj[10].toString():"");
                bean.setCertificado(obj[11]!=null? obj[11].toString():"");
                bean.setNroinf(obj[12]!=null? obj[12].toString():"");
                bean.setConceptoinspeccion(obj[13]!=null? obj[13].toString():"");
                bean.setTipoVehiculo(obj[14]!=null? obj[14].toString():"");
                bean.setNrocomprobante(obj[15]!=null? obj[15].toString():"");
                bean.setTipocomprobante(obj[16]!=null? obj[16].toString():"");
                bean.setTotal(Double.valueOf(obj[17]!=null?obj[17].toString():"0"));
                bean.setOrdenservicio(obj[18]!=null? obj[18].toString():"");
                bean.setKilometraje(Double.valueOf(obj[19]!=null?obj[19].toString():"0"));
                bean.setNombreejecutivo(obj[20]!=null? obj[20].toString():"");
                resultado.add(bean);
            }
        //}
        return resultado;
      }
    public List<ReporteCuentasPorCobrar> getReporteCxCConsolidado(Date fechIni, Date fechFin, String planta, String formapago) {
        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<ReporteCuentasPorCobrar> resultado = new ArrayList();
        Date fechacreacion= new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);


        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }

        //for (String id_planta : plantas){
        String hql ="SELECT emp.nombre as empresa,emp.ruc,tipdi.nombre as tipodocumentoidentidad,cli.nrodocumentoidentidad,pl.nombre as planta,fp.nombre as formapago,cli.nombres ,cli.apellidos,cli.nombrerazonsocial,"
                +" com.fechapago,com.placaMotor,cert.nrodocumentoCertificado,ins.nrodocumentoInforme,ci.abreviatura,car.nombre as carroceria,"
                +" com.nrocomprobante,tipdo.key,com.importetotal,com.ordenservicio,veh.kilometraje, concat(per.nombres,' ', per.apellidos ) as nombreejecutivo"
                +" FROM comprobante com inner join comprobanteestado coes on com.comprobanteestado_key = coes.key"
                +" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion"
                +" left join certificado cert on ins.nrodocumentoinspeccion = cert.inspeccion_nrodocumentoinspeccion"
                +" inner join tipodocumento tipdo on com.tipodocumento_key = tipdo.key"
                +" inner join empresa emp on com.empresa_key = emp.key"
                +" inner join persona cli on com.cliente_nrodocumentoidentidad = cli.nrodocumentoidentidad"
                +" inner join conceptoinspeccion ci on com.conceptoinspeccion_key = ci.key"
                +" left join tipodocumentoidentidad tipdi on cli.tipodocumentoidentidad_key = tipdi.key"
                +" inner join formapago fp on com.formapago_key = fp.key"
                +" inner join linea li on com.linea_key = li.key"
                +" inner join planta pl on li.planta_key = pl.key"
                +" inner join vehiculo veh on ins.vehiculo_nromotor = veh.nromotor"
                +" left join carroceria car on veh.carroceria_key = car.key"
                +" inner join descuentocomprobante dcom on com.descuentocomprobante_id = dcom.id"
                +" inner join descuentodetalle ddtll on cast(dcom.iddescuentodetalle as int)= cast(ddtll.id as int)"
                +" inner join descuento dscto on ddtll.descuento_id = dscto.id"
                +" inner join usuario use on dscto.ejecutivo_username = use.username"
                +" inner join persona per on use.persona_nrodocumentoidentidad=per.nrodocumentoidentidad"
                +" where to_date(to_char(com.fechapago, 'YYYY-MM-DD'),'YYYY-MM-DD') between '" + dateFormat.format(fechIni)+ "' and " + " '" + dateFormat.format(fechFin) + "'";
        if(!planta.equals("todos")){
            hql+=" and pl.key='"+planta+"'";
        }
        if(formapago != null && !formapago.isEmpty() &&!formapago.equals("todos")){
            hql+=" and fp.key='"+formapago+"'";
        }
        hql+=" and com.comprobanteestado_key != 'ANU' order by cli.nrodocumentoidentidad, emp.nombre asc";

        Query query = entityManager.createNativeQuery(hql.toString());
        List<Object> result = (List<Object>) query.getResultList();
        Iterator<Object> itr = result.iterator();

        while (itr.hasNext()){
            ReporteCuentasPorCobrar bean= new ReporteCuentasPorCobrar();
            Object[] obj = (Object[]) itr.next();
            bean.setRazonfarenet(obj[0]!=null? obj[0].toString():"");
            bean.setNroRucFarenet(obj[1]!=null? obj[1].toString():"");
            bean.setTipodocumento(obj[2]!=null? obj[2].toString():"");
            bean.setNroruc(obj[3]!=null? obj[3].toString():"");
            bean.setPlanta(obj[4]!=null? obj[4].toString():"");
            bean.setFormaPago(obj[5]!=null? obj[5].toString():"");
            bean.setEntidadNombre(obj[6]!=null? obj[6].toString():"");
            bean.setEntidadApellido(obj[7]!=null? obj[7].toString():"");
            bean.setEntidadRazon(obj[8]!=null? obj[8].toString():"");
            try {
                fechacreacion = format.parse(obj[9].toString());
            }catch (ParseException pe){
                pe.printStackTrace();
            }
            bean.setFechaPago(fechacreacion);
            bean.setPlaca(obj[10]!= null? obj[10].toString():"");
            bean.setCertificado(obj[11]!=null? obj[11].toString():"");
            bean.setNroinf(obj[12]!=null? obj[12].toString():"");
            bean.setConceptoinspeccion(obj[13]!=null? obj[13].toString():"");
            bean.setTipoVehiculo(obj[14]!=null? obj[14].toString():"");
            bean.setNrocomprobante(obj[15]!=null? obj[15].toString():"");
            bean.setTipocomprobante(obj[16]!=null? obj[16].toString():"");
            bean.setTotal(Double.valueOf(obj[17]!=null?obj[17].toString():"0"));
            bean.setOrdenservicio(obj[18]!=null? obj[18].toString():"");
            bean.setKilometraje(Double.valueOf(obj[19]!=null?obj[19].toString():"0"));
            bean.setNombreejecutivo(obj[20]!=null? obj[20].toString():"");
            resultado.add(bean);
        }


        return resultado;
    }
    public List<ResultReportBean<ReporteResumenIngresosBean>> getReporteResumenIngresos(Date fechIni, Date fechFin) {

            String anio="yyyy";
            String mes="MM";
            String dia="dd";
            SimpleDateFormat dateFormatanio = new SimpleDateFormat(anio);
            SimpleDateFormat dateFormatmes = new SimpleDateFormat(mes);
            SimpleDateFormat dateFormatdia= new SimpleDateFormat(dia);

            int anioini= Integer.parseInt(dateFormatanio.format(fechIni));
            int mesini=Integer.parseInt(dateFormatmes.format(fechIni));
            int diaini=Integer.parseInt(dateFormatdia.format(fechIni));


            int aniofin= Integer.parseInt(dateFormatanio.format(fechFin));
            int mesfin=Integer.parseInt(dateFormatmes.format(fechFin));
            int diafin=Integer.parseInt(dateFormatdia.format(fechFin));

            List<ResultReportBean<ReporteResumenIngresosBean>> resultado = new ArrayList();

            List<String> lstPlantas = getAllIdPlanta();

            for(String planta : lstPlantas) {
            	if(planta.equals("125") || planta.equals("202") || planta.equals("43")) {
            	}else {

                    ResultReportBean<ReporteResumenIngresosBean> reporteBean = new ResultReportBean<>();
                    List<ReporteResumenIngresosBean> lst = new ArrayList<>();
                    String hql2=""+
                            " select distinct fp.key,pag.tipocontado_key as tipopago,case when fp.key in('contado','pagoadelantado') then sum(pag.importe) else sum(com.importetotal) end as sum "+
                            " from comprobante com "+
                            " left join pago pag on com.id=pag.comprobante_id "+
                            " left join formapago fp on com.formapago_key=fp.key "+
                            " left join tipocontado tp on pag.tipocontado_key=tp.key "+
                            " left join linea li on com.linea_key=li.key "+
                            " left join planta pl on li.planta_key=pl.key "+
                            " where "+
                            " com.nrocomprobante is not null and "+
                            " com.comprobanteestado_key !='ANU' and" +
                            //" fp.key='contado' and " +
                            " com.importetotal !=0 and " +
                            //" tp.key='efectivo' and " +
                            " (com.tipodescuento_key <> 'corte' or com.tipodescuento_key is null) and "+
                            " extract(year from com.fechapago) between "+ anioini +" and " + aniofin+" and " +
                            " extract(month from com.fechapago) between "+ mesini +" and " + mesfin+" and " +
                            " extract(day from com.fechapago) between "+ diaini +" and " + diafin+" and " +
                            " pl.key='"+planta+"'"+
                            " group by GROUPING SETS((fp.key,pag.tipocontado_key))order by fp.key; ";
                    Query querytemp= entityManager.createNativeQuery(hql2.toString());
                    //lst = querytemp.getResultList();
                    List<Object> result = (List<Object>) querytemp.getResultList();
                    Iterator<Object> itr = result.iterator();
                    while(itr.hasNext()){
                        Object[] obj = (Object[]) itr.next();
                        ReporteResumenIngresosBean bean = new ReporteResumenIngresosBean();
                        bean.setFormaPago(obj[0] != null ? obj[0].toString():null);
                        bean.setTipoContado(obj[1] != null ? obj[1].toString():null);
                        bean.setMonto(Double.valueOf(obj[2] != null ? obj[2].toString():"0"));
                        lst.add(bean);
                    }
                    for(ReporteResumenIngresosBean  bean : lst) {
                        if(bean.getFormaPago().equals("contado")
                                && bean.getTipoContado() !=null
                                && !bean.getTipoContado().equals("efectivo")) {
                            bean.setFormaPago(bean.getTipoContado());
                        }
                    }
                        reporteBean.setData(lst);
                        reporteBean.setPlanta(plantaRepository.findOneByKey(planta).getNombre());
                        resultado.add(reporteBean);
            	}	
            }
            return resultado;
        }

    public List<ReporteResumenIngresosBean> llenarResumen(double contado, double credito, double banco,
        double tarjeta, double cheque, double pagoadelantado){
            List<ReporteResumenIngresosBean> result = new ArrayList<>();
            ReporteResumenIngresosBean beanCon = new ReporteResumenIngresosBean("contado", "contado", contado);
            ReporteResumenIngresosBean beanBan = new ReporteResumenIngresosBean("banco", "banco", banco);
            ReporteResumenIngresosBean beanCre = new ReporteResumenIngresosBean("credito", "credito", credito);
            ReporteResumenIngresosBean beanTar = new ReporteResumenIngresosBean("tarjeta", "tarjeta", tarjeta);
            ReporteResumenIngresosBean beanChe = new ReporteResumenIngresosBean("cheque", "cheque", cheque);
            ReporteResumenIngresosBean beanPag = new ReporteResumenIngresosBean("pagoadelantado", "pagoadelantado", pagoadelantado);
            result.add(beanCon);
            result.add(beanBan);
            result.add(beanCre);
            result.add(beanTar);
            result.add(beanChe);
            result.add(beanPag);


            return result;
        }

    public CompleteResultReportSMSBean getReporteEfectividadSMS(
                Date fechIniSMS,
                Date fechFinSMS,
                Date fechIniIns,
                Date fechFinIns,
                String planta_key) {

            Timestamp fechaIniIns = new Timestamp(fechIniIns.getTime());
            Timestamp fechaFinIns = new Timestamp(fechFinIns.getTime());

            CompleteResultReportSMSBean complete = new CompleteResultReportSMSBean();

            List<ResultReportSMSBean<ReporteEfectividadSMSBean>> resultado = new ArrayList();

            Calendar fechaIniSMS = Calendar.getInstance();
            fechaIniSMS.setTime(fechIniSMS);
            fechaIniSMS.set(Calendar.HOUR,0);
            fechaIniSMS.set(Calendar.MINUTE,0);

            Calendar fechaFinSMS = Calendar.getInstance();
            fechaFinSMS.setTime(fechFinSMS);
            fechaFinSMS.set(Calendar.HOUR,23);
            fechaFinSMS.set(Calendar.MINUTE,59);


            //consultamos al servicio de callcenter
            List<SMSCAPI> resultadoSMS = ventaCapiService.findListSMS(fechaIniSMS.getTime(),fechaFinSMS.getTime());

            if(resultadoSMS==null || resultadoSMS.size()==0)
                return null;

            List<String> inSQL = new ArrayList<>();
            for(SMSCAPI sms : resultadoSMS)
            {
                inSQL.add(sms.getTelefono());
            }

            List<String> plantas = new ArrayList<>();
            if(planta_key.equals("todos")) {
                plantas = getAllIdPlanta();
            }else {
                plantas.add(planta_key);
            }

            for(String planta : plantas)
            {

                List<ReporteEfectividadSMSBean> lst = new ArrayList<>();

                String hql1 = "SELECT DISTINCT new " + ReporteEfectividadSMSBean.class.getName()
                        + " (com.placaMotor,pro.telefono,com.importetotal,ins.fechcreacion)"
                        + " FROM Comprobante com"
                        + " inner join com.inspeccion ins"
                        + " inner join com.linea li"
                        + " inner join li.planta pl"
                        + " inner join ins.vehiculo veh"
                        + " inner join veh.tarjetapropiedad tpd"
                        + " inner join tpd.propietario pro"
                        + " where to_date(to_char(ins.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                        + " and pl.key = :planta_key"
                        + " and pro.telefono IN (:placas)";
                Query query = entityManager.createQuery(hql1);

                query.setParameter("fechaMin", fechaIniIns);
                query.setParameter("fechaMax", fechaFinIns);
                query.setParameter("planta_key", planta);
                query.setParameter("placas", inSQL);

                lst = query.getResultList();


                //cantidad inspecciones
                hql1 = "SELECT count(com)"
                        + " FROM Comprobante com"
                        + " inner join com.inspeccion ins"
                        + " inner join com.linea li"
                        + " inner join li.planta pl"
                        + " inner join ins.vehiculo veh"
                        + " inner join veh.tarjetapropiedad tpd"
                        + " inner join tpd.propietario pro"
                        + " where to_date(to_char(ins.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                        + " and pl.key = :planta_key";
                query = entityManager.createQuery(hql1);

                query.setParameter("fechaMin", fechaIniIns);
                query.setParameter("fechaMax", fechaFinIns);
                query.setParameter("planta_key", planta);

                Long cant = (Long)query.getSingleResult();



                ResultReportSMSBean<ReporteEfectividadSMSBean> reporteBean = new ResultReportSMSBean();

                for(ReporteEfectividadSMSBean bean : lst )
                {
                    for(SMSCAPI sms : resultadoSMS)
                    {
                        if(bean.getTelefono().equalsIgnoreCase(sms.getTelefono()))
                        {
                            if(bean.getFechaEnvioSMS()!=null)
                            {
                                if(bean.getFechaEnvioSMS().before(sms.getFecha()))
                                {
                                    bean.setFechaEnvioSMS(sms.getFecha());
                                }
                            }else {
                                bean.setFechaEnvioSMS(sms.getFecha());
                            }

                        }
                    }
                }


                reporteBean.cantidadInspecciones = cant;
                reporteBean.data= lst;
                reporteBean.planta = plantaRepository.findOneByKey(planta).getNombre();

                resultado.add(reporteBean);
            }


            complete.data = resultado;
            complete.totalSMS = resultadoSMS.size();

            return complete;

        }

    public List<ReporteRecibosManualesOfisis> getReporteRecibosManualesOfisis(Date fechIni, Date fechFin,String planta){
    		Timestamp fechaMin = new Timestamp(fechIni.getTime());
            Timestamp fechaMax = new Timestamp(fechFin.getTime());
	    	List<ReporteRecibosManualesOfisis> resultado = new ArrayList();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendarIni = Calendar.getInstance();
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.setTime(fechaMax);
			calendarIni.setTime(fechaMin);
			Integer mes[] = {1,2,3,4,5,6,7,8,9,10,11,12};
			int anioIni= calendarIni.get(Calendar.YEAR);
			int mesIni = mes[calendarIni.get(Calendar.MONTH)];
			int diaIni = calendarIni.get(Calendar.DAY_OF_MONTH);
	
			int anioFni= calendarFin.get(Calendar.YEAR);
			int mesFni = mes[calendarFin.get(Calendar.MONTH)];
			int diaFni = calendarFin.get(Calendar.DAY_OF_MONTH);
			Date fechacreacion= new Date();
			
	        List<String> plantas = new ArrayList<>();
	        if(planta.equals("todos")) {
	        	plantas = getAllIdPlantaByUser();
	        }else {
	        	plantas.add(planta);
	        }
	
	        for(String idPlanta : plantas) {
	        	StringBuffer hql1 =  new StringBuffer( " SELECT DISTINCT c.fechcreacion,c.nrodocumentoidentidad, ")
		        			.append(" c.nombrecompleto, c.serie, c.total, c.planta, c.correlativo, c.comprobanteestado,c.formapago ")
		        			.append(" from st_comprobantes c ")
		        			.append(" inner join st_carga_st_mensaje scm on c.st_carga_id=scm.st_carga_id ")
		        			.append(" inner join st_mensaje sm on scm.st_mensajes_id=sm.id ")
		        			.append(" where planta like '" + idPlanta+"-%' and" )
		        			.append(" sm.codigo='DOK' and ")
		        			.append(" sm.tipo like '%MANUAL' and ")
		    				.append(" extract(year  from c.FECHCREACION)  between "+ anioIni + " and " + anioFni + " and " )
		    				.append(" extract(month from c.FECHCREACION)  between "+ mesIni  + " and " + mesFni  + " and " )
		    				.append(" extract(day   from c.FECHCREACION)  between "+ diaIni  + " and " + diaFni )
		    				.append(" group by c.serie,c.fechcreacion,c.nrodocumentoidentidad,c.total,c.planta,c.correlativo, c.nombrecompleto, c.comprobanteestado,c.formapago order by c.serie,c.correlativo asc");
				Query query = entityManager.createNativeQuery(hql1.toString());
				List<Object> result = (List<Object>) query.getResultList();
				Iterator<Object> itr = result.iterator();
				while(itr.hasNext()) {
					ReporteRecibosManualesOfisis bean = new ReporteRecibosManualesOfisis();
					Object[] obj = (Object[]) itr.next();
					 try {
						 fechacreacion = format.parse(obj[0].toString());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					bean.setFechaEmision(fechacreacion);
					bean.setNumeroDocumento(Long.valueOf(obj[1]!=null?obj[1].toString().trim():"0"));
					bean.setRazonSocial(obj[2]!=null?obj[2].toString().trim():"");
					bean.setSerie(obj[3]!=null?obj[3].toString().trim():"");
					bean.setMontoTotal(Double.valueOf(obj[4]!=null?obj[4].toString().trim():"0"));
					bean.setPlanta(obj[5]!=null?obj[5].toString().trim():"");
					bean.setCorrelativo(Long.valueOf(obj[6]!=null?obj[6].toString().trim():"0"));
					bean.setEstado(obj[7]!=null?obj[7].toString().trim():"");
					bean.setFormapago(obj[8]!=null?obj[8].toString().trim():"");
					resultado.add(bean);
				}
	        }
			return resultado;
	}
	
	public List<ReporteNotasCreditoOfisis>getReporteNotasCreditoOfisis(Date fechIni, Date fechFin, String planta){
		
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
		Timestamp fechaMax = new Timestamp(fechFin.getTime());
		List<ReporteNotasCreditoOfisis> resultado = new ArrayList();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarIni = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaMax);
		calendarIni.setTime(fechaMin);
		Integer mes[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int anioIni= calendarIni.get(Calendar.YEAR);
		int mesIni = mes[calendarIni.get(Calendar.MONTH)];
		int diaIni = calendarIni.get(Calendar.DAY_OF_MONTH);

		int anioFni= calendarFin.get(Calendar.YEAR);
		int mesFni = mes[calendarFin.get(Calendar.MONTH)];
		int diaFni = calendarFin.get(Calendar.DAY_OF_MONTH);
		Date fechacreacion= new Date();
		Date fechareferencia= new Date();
		
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }		
		
        for(String idPlanta : plantas) {		
		StringBuffer hql1 =  new StringBuffer( "select distinct stm.nrocomprobante,stnc.correlativoref,stnc.serieref, ")
				.append(" stnc.fechcreacion as fechapago,com.cliente_nrodocumentoidentidad,stnc.sustento,stnc.tipo,stnc.importenc,stnc.nrodocumentoinspeccion, ")
				.append(" stnc.fechcreacion as fechapago1,stnc.fechcreacion as fechapago2,td.sunattipo,com.importetotal, ")
				.append(" case when pe.tipodocumentoidentidad_key='ruc' then pe.nombrerazonsocial ")
				.append(" when pe.tipodocumentoidentidad_key='dni'then concat(pe.nombres, ' ', pe.apellidos) ")
				.append(" else concat(pe.nombres, ' ', pe.apellidos) end, ") 
				.append(" pl.cuentacajaefectivoofisis,pl.nombre ")
				.append(" from st_notadecredito stnc ")
				.append(" inner join st_carga_st_mensaje stcm on stnc.st_carga_id=stcm.st_carga_id ")
				.append(" inner join st_mensaje stm on stcm.st_mensajes_id=stm.id and stnc.nrodocumentoinspeccion=stm.nrodocumentoinspeccion ")
				.append(" inner join comprobante com on stnc.nrodocumentoinspeccion=com.inspeccion_nrodocumentoinspeccion")
				.append(" inner join tipodocumento td on com.tipodocumento_key=td.key ")
				.append(" inner join persona pe on com.cliente_nrodocumentoidentidad=pe.nrodocumentoidentidad ")
				.append(" inner join linea li on com.linea_key=li.key ")
				.append(" inner join planta pl on li.planta_key=pl.key ")
				.append(" where stm.nrocomprobante like '%C%' and ")
				.append(" com.nrocomprobante like concat_ws('%',stnc.serieref,'-%',stnc.correlativoref) and ")
				.append(" stnc.fechcreacion is not null and ")
				.append(" stm.tipo LIKE 'NOTA%'and ")
				.append(" stm.codigo ='DOK' and ")
				.append(" extract(year from stnc.FECHCREACION)  between "+ anioIni + " and " + anioFni + " and " )
				.append(" extract(month from stnc.FECHCREACION) between "+ mesIni  + " and " + mesFni  + " and " )
				.append(" extract(day from stnc.FECHCREACION)   between "+ diaIni  + " and " + diaFni  + " and " )
				.append(" pl.key='"+idPlanta+"'");
				Query query = entityManager.createNativeQuery(hql1.toString());
				List<Object> result = (List<Object>) query.getResultList();
				Iterator<Object> itr = result.iterator(); 
				while(itr.hasNext()){
				ReporteNotasCreditoOfisis bean = new ReporteNotasCreditoOfisis();
				Object[] obj = (Object[]) itr.next(); 
				bean.setNrocomprobante(obj[0].toString());
				bean.setNumeroDocReferencia(Long.valueOf(obj[1].toString()));
				bean.setSerieDocReferencia(obj[2].toString());
				 try {
					 fechacreacion = format.parse(obj[3].toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				bean.setFechaDocReferencia(fechacreacion);
				bean.setRucDni(obj[4].toString());
				bean.setGlosario(obj[5].toString());
				bean.setTipoDocumento(obj[6].toString());
				bean.setMontoTotal(Double.valueOf(obj[7].toString()));
				try {
					fechareferencia=format.parse(obj[9].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bean.setFechaDocumento(fechareferencia);
				bean.setSerieDocumento((obj[0].toString()).substring(0,4));
				bean.setTipoDocReferencia(obj[11].toString());
				bean.setMontoDocReferencia(obj[12].toString());
				bean.setRazonSocialNombre(obj[13].toString());
				bean.setCuentaContable(Integer.valueOf(obj[14].toString()));
				bean.setPlanta(obj[15].toString());
				resultado.add(bean);
				}
        }
		return resultado;
	}

	public List<ReporteControlAuditoriaBean>getReporteControlAuditoria(Date fechIni, Date fechFin, String planta){
		
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
		Timestamp fechaMax = new Timestamp(fechFin.getTime());
		List<ReporteControlAuditoriaBean> resultado = new ArrayList();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarIni = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaMax);
		calendarIni.setTime(fechaMin);
		Integer mes[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int anioIni= calendarIni.get(Calendar.YEAR);
		int mesIni = mes[calendarIni.get(Calendar.MONTH)];
		int diaIni = calendarIni.get(Calendar.DAY_OF_MONTH);

		int anioFni= calendarFin.get(Calendar.YEAR);
		int mesFni = mes[calendarFin.get(Calendar.MONTH)];
		int diaFni = calendarFin.get(Calendar.DAY_OF_MONTH);
		Date fechaconsolidacion= new Date();
		Date fechaanulacion= new Date();
		Date fechacreacion= new Date();
		
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
        	plantas = getAllIdPlantaByUser();
        }else {
        	plantas.add(planta);
        }	

        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        for(String idPlanta : plantas) {	
        	StringBuffer hql1 =  new StringBuffer("select distinct(c2.nrodocumentocertificado),c.linea_key as Linea,i.fechconsolidado as Fechaconsolidacioninspeccion, ")
        			.append(" c2.fechanulacion as fechaanulacioncert,c.placamotor as Placa,i.resultado as Resultado,case  c2.anulado when true then 'ANULADO' ELSE '' END as Estadocertificado,")
        			.append(" i.inspeccionestado_key as Estadoinspeccion,i.nrodocumentoinspeccion as Idinspeccion,c2.fechcreacion as Fechacreacioncertificado,")
        			.append(" i.usuarioanulacion_username as usuarioanulacioninspeccion,c2.observacionanulado as obsanucertificado,i.observacionanulado as obsanulinspeccion,")
        			.append(" c2.nrohojavalorada as nrohojavalorada,concat(pco.nombres,' ',pco.apellidos) as usuarioConsolidado,concat(pca.nombres,' ',pca.apellidos) as usuarioCaja,")
        			.append(" ce.nrohojavalorada as hojaAnulada,ce.tipoerror as tipoError,ce.descripcionerror as descpError ")
        			.append(" from inspeccion i")
        			.append(" inner join comprobante c on i.nrodocumentoinspeccion = c.inspeccion_nrodocumentoinspeccion")
        			.append(" inner join linea l on l.key =c.linea_key")
        			.append(" inner join planta p on l.planta_key = p.key")
        			.append(" inner join usuario uca on i.usuariocaja_username = uca.username")
        			.append(" inner join persona pca on uca.persona_nrodocumentoidentidad = pca.nrodocumentoidentidad")
        			.append(" inner join usuario uco on i.usuarioconsolidado_username = uco.username")
        			.append(" inner join persona pco on uco.persona_nrodocumentoidentidad = pco.nrodocumentoidentidad")
        			.append(" inner join certificado c2 on i.nrodocumentoinspeccion = c2.inspeccion_nrodocumentoinspeccion")
        			.append(" left join certificado_error ce on c2.nrodocumentocertificado = ce.certificado_nrodocumentocertificado")
    				.append(" where to_date(to_char(i.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
    				.append(" and p.key='"+idPlanta+"'");
			Query query = entityManager.createNativeQuery(hql1.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			while (itr.hasNext()) {
				ReporteControlAuditoriaBean bean = new ReporteControlAuditoriaBean();
				Object[] obj = (Object[]) itr.next(); 
				bean.setNroCertificado(obj[0]!=null ? obj[0].toString():"");
				bean.setLinea(obj[1]!=null ? obj[1].toString():"");
				if (obj[2]!=null) {
					try {
						fechaconsolidacion=format.parse(obj[2].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setFechconsolidado(fechaconsolidacion);
					
				}
				 if(obj[3]!=null) {
						try {
							fechaanulacion=format.parse(obj[3].toString());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						bean.setFechanulacion(fechaanulacion);
				}
				bean.setPlacamotor(obj[4].toString());
				bean.setResultado(obj[5].toString());
				bean.setEstadocertificado(obj[6].toString());
				bean.setInspeccionestado(obj[7].toString());
				bean.setNrodocumentoinspeccion(obj[8].toString());
				if (obj[9] != null) {
					try {
						fechacreacion=format.parse(obj[9].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setFechcreacion(fechacreacion);					
				}
				bean.setUsuarioanulacioninspeccion(obj[10]!= null? obj[10].toString():"");
				bean.setObsanucertificado(obj[11]!=null ? obj[11].toString():"");
				bean.setObsanulinspeccion(obj[12]!=null ? obj[12].toString():"");
				bean.setNrohojavalorada(obj[13]!=null ? obj[13].toString():"");
				bean.setUsuarioConsolidado(obj[14]!=null ?obj[14].toString():"");
				bean.setUsuarioCaja(obj[15]!=null ? obj[15].toString():"");
				bean.setHojaanulada(obj[16]!=null ? obj[16].toString():"");
				bean.setTipoerror(obj[17]!=null ? obj[17].toString():"");
				bean.setDescrerror(obj[18]!=null ? obj[18].toString():"");
				resultado.add(bean);
			}
        			
        	
        }
		return resultado;
	}
    
	public List<ResultReportBean<ReporteDefectoBean>> getReporteDefectoBean(Date fechIni, Date fechFin, String planta){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechapago= new Date();
        
        List<ResultReportBean<ReporteDefectoBean>> resultado = new ArrayList();
        List<ReporteDefectoBean> lstdefectos = new ArrayList();
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        
        for(String planta_key : plantas) {
            ResultReportBean<ReporteDefectoBean> reportBean = new ResultReportBean<>();

            List<ReporteDefectoBean> lst = new ArrayList<>();
            
            StringBuffer hql1 =  new StringBuffer("select pl.nombre as Planta,")
            		.append(" com.placamotor as Placa,")
            		.append(" com.fechapago as Fecha,")
            		.append(" def.codigovalor as CodigoValor,")
            		.append(" case when cert.nrodocumentocertificado <>'' then cert.nrodocumentocertificado else ins.nrodocumentoinforme end as NroDocumentoInspeccion ,")
            		.append(" def.nivelpeligro as NivelPeligrosidad,")
            		.append(" ins.resultado as Resultado,")
            		.append(" ins.inspeccionestado_key as EstadoInspeccion,")
            		.append(" li.nombre as linea,")
            		.append(" ins.observacion as observacioninspe")
            		.append(" from inspeccion ins")
            		.append(" inner join resultado_maquina resm on ins.nrodocumentoinspeccion=inspeccion_nrodocumentoinspeccion")
            		.append(" inner join resultado_maquina_defecto resmd on resm.id=resmd.resultado_maquina_id")
            		.append(" inner join defecto def on  resmd.defectos_id= def.id")
            		.append(" inner join comprobante com on ins.nrodocumentoinspeccion=com.inspeccion_nrodocumentoinspeccion")
            		.append(" left join certificado cert on ins.nrodocumentoinspeccion=cert.inspeccion_nrodocumentoinspeccion")
            		.append(" inner join linea li on com.linea_key=li.key")
            		.append(" inner join planta pl on li.planta_key=pl.key")
            		.append(" where  to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" +dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"'")
            		.append(" and pl.key='"+ planta_key +"'")
            		.append(" order by pl.nombre, fechapago asc");
			Query query = entityManager.createNativeQuery(hql1.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			List<ReporteDefectoBean> list = new ArrayList<>();
			while (itr.hasNext()) {
				ReporteDefectoBean bean = new ReporteDefectoBean();
				Object[] obj = (Object[]) itr.next(); 

				bean.setPlanta(obj[0]!=null ? obj[0].toString():"");
				bean.setPlaca(obj[1]!=null ? obj[1].toString():"");
				if (obj[2]!=null) {
					try {
						fechapago=format.parse(obj[2].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					bean.setFecha(fechapago);
				}
				bean.setCodigoValor(obj[3]!=null ? obj[3].toString():"");
				bean.setNroDocumentoInspeccion(obj[4]!=null ? obj[4].toString():"");
				bean.setNivelPeligrosidad(obj[5]!=null ? obj[5].toString():"");
				bean.setResultado(obj[6]!=null ? obj[6].toString():"");
				bean.setEstadoInspeccion(obj[7]!=null ? obj[7].toString():"");
				bean.setLinea(obj[8]!=null ? obj[8].toString():"");
				bean.setObservacion(obj[9]!=null ? obj[9].toString():"");
				list.add(bean);
				reportBean.setData(list);
				reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
			}
			resultado.add(reportBean);
        }
        return resultado;
    }
        
    //ListadoReporteTipo
    public List<ResultReportBean<ReporteDescuentoTipoBean>> getReporteDescuentoTipoBean(Date dateIni, Date dateFin, String tipos, String planta,String nombreDescuento){
    	Timestamp fechaMin = new Timestamp(dateIni.getTime());
        Timestamp fechaMax = new Timestamp(dateFin.getTime());
        
        List<ResultReportBean<ReporteDescuentoTipoBean>> resultado = new ArrayList();
        
        List<String> plantas = new ArrayList<>();
        if(planta.equals("todos")) {
            plantas = getAllIdPlantaByUser();
        }else {
            plantas.add(planta);
        }
        
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
                
        for(String planta_key : plantas) {
            ResultReportBean<ReporteDescuentoTipoBean> reportBean = new ResultReportBean<>();

            List<ReporteDescuentoTipoBean> lst = new ArrayList<>();
            
            StringBuffer hql = new StringBuffer("select placamotor as Placa,")
            		.append(" comprobante.fechcreacion as Fecha,") 
            		.append(" comprobanteestado_key as Comprobante,")
            		.append(" cliente_nrodocumentoidentidad as DNI,")
            		.append(" p.nombre as NroPlanta,")
            		.append(" totaldscto as Descuento,")
            		.append(" importetotal as Importe, ")
            		.append(" d.nombredescuento as nombreDes")
            		.append(" from comprobante ")
            		.append(" left join descuentocomprobante d on comprobante.descuentocomprobante_id = d.id ")
            		.append(" left join linea l on comprobante.linea_key = l.key")
            		.append(" left join planta p on l.planta_key = p.key")
            		.append(" where to_date(to_char(comprobante.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" + dateFormat.format(dateIni) +"' and '" + dateFormat.format(dateFin) + "'")
                    .append(" and l.planta_key = '" + planta_key + "' and d.tipodescuento_key ='" + tipos + "' and ")
                    .append(" d.nombredescuento = '" + nombreDescuento + "'");
                    	
            Query query = entityManager.createNativeQuery(hql.toString());
            List<Object> result = (List<Object>) query.getResultList();
            Iterator<Object> itr = result.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                ReporteDescuentoTipoBean bean = new ReporteDescuentoTipoBean();
                bean.setPlaca(obj[0] != null ? obj[0].toString():null);
                bean.setFecha(obj[1] != null ? obj[1].toString():null);
                bean.setComprobante(obj[2] != null ? obj[2].toString():null);
                bean.setDni(obj[3] != null ? obj[3].toString():null);
                bean.setPlantaKey(obj[4] != null ? obj[4].toString():null);
                bean.setTotal(Double.valueOf(obj[5].toString()));
                bean.setImporteTotal(Double.valueOf(obj[6].toString()));
                bean.setNombreDescuento(obj[7] != null ? obj[7].toString():null);
                lst.add(bean);
            }
        	
        	reportBean.setData(lst);
        	reportBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
            
            resultado.add(reportBean);
        }
        
        return resultado;
    }
    
    //Listado de inspecciones MTC (conteos)
    public List<ResultReportBean<ReporteInspeccionesMTCBean>> getInspeccionesMTC(Date fechIni, Date fechFin, String planta){
    	
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        
        
        SimpleDateFormat format = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        
        
        List<ResultReportBean<ReporteInspeccionesMTCBean>> lstresult= new ArrayList<>();
    	List<Object> result = new ArrayList<>();
        
        List<String> plantas = new ArrayList<>();
    	if(planta.equals("todos")) {
    		plantas = getAllIdPlantaByUser();
    	}else {
    		plantas.add(planta);
    	}
    	
    	for(String planta_key : plantas) {
    		
    		ResultReportBean<ReporteInspeccionesMTCBean> reporteBean = new ResultReportBean<>();
    		List<ReporteInspeccionesMTCBean> lst = new ArrayList<>();
    		
    	StringBuffer hql1 = new StringBuffer (
    			        " select t1.planta,sum(t1.inspecciones)  inspecciones,"
                                +" sum(t1.aprobados) aprobados,"
                                +" sum(t1.desaprobados) desaprobados,"
                                +" sum(t1.anulados) anulados,"
                                +" sum(t1.duplicados) duplicados,"
                                +" sum(t1.servicios) servicios,"
                                +" sum(t1.retirados_proceso) retirados_proceso,"
                                +" (sum(t1.inspecciones)-(sum(t1.anulados)+sum(t1.retirados_proceso)+sum(t1.servicios)) ) Total_MTC,t1.fecha,t1.linea"
                                +" from (  select to_char(c.fechmodi, 'YYYY-MM-DD') fecha, p.nombre planta,C.placamotor placa,C.inspeccion_nrodocumentoinspeccion INS,l.nombre  linea,"
                                +" case when  c.comprobanteestado_key is not null then 1 else 0 end inspecciones,"
                                //+" case when to_date(to_char(c.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" + format.format(fechaMin) +"'  and '" + format.format(fechaMax) +"' and c.comprobanteestado_key is not null then 1 else 0 end inspecciones,"
                                +" case when comprobanteestado_key <> 'ANU' and duplicado is null and resultado = 'A' then 1 else 0 end aprobados,"
                                +" case when comprobanteestado_key <> 'ANU' and duplicado is null and resultado = 'D' then 1 else 0 end desaprobados,"
                                +" case when c.comprobanteestado_key='ANU' then 1 else 0 end anulados,"
                                +" case when duplicado=true and  comprobanteestado_key <> 'ANU' then 1 else 0 end duplicados,"
                                +" case when comprobanteestado_key <> 'ANU'   and c.conceptoinspeccion_key in('26','27','41','42','25','12') then 1 else 0 end servicios,"
                                +" case when i.inspeccionestado_key not in('ANU','CON') then 1 else 0 end retirados_proceso"
                                +" from comprobante c"
                                +" inner join inspeccion i on c.inspeccion_nrodocumentoinspeccion=i.nrodocumentoinspeccion"
                                +" inner join linea l on c.linea_key = l.key"
                                +" inner join planta p on l.planta_key = p.key"
                                +" where to_date(to_char(c.fechmodi, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '" + format.format(fechaMin) +"' and '" + format.format(fechaMax) +"' and p.key in ('" + planta_key +"')) as t1"
                                +" group by t1.planta,t1.fecha,t1.linea");
        
    	Query query = entityManager.createNativeQuery(hql1.toString());
    	result = (List<Object>) query.getResultList();
    	 
    	Iterator<Object> itr = result.iterator(); 
    	while (itr.hasNext()) {
    		ReporteInspeccionesMTCBean bean  = new ReporteInspeccionesMTCBean();
			Object[] obj = (Object[]) itr.next();
			bean.setPlantas(obj[0] != null ? obj[0].toString():null);
			bean.setInspecciones(obj[1]!= null ? Integer.parseInt(obj[1].toString()):0);
			bean.setAprobados(obj[2]!= null ? Integer.parseInt(obj[2].toString()):0);
			bean.setDesaprobados(obj[3]!= null ? Integer.parseInt(obj[3].toString()):0);
			bean.setAnulados(obj[4]!= null ? Integer.parseInt(obj[4].toString()):0);
			bean.setDuplicados(obj[5]!= null ? Integer.parseInt(obj[5].toString()):0);
			bean.setServicios(obj[6]!= null ? Integer.parseInt(obj[6].toString()):0);
			bean.setRetirados_proceso(obj[7]!= null ? Integer.parseInt(obj[7].toString()):0);
            bean.setTotalmtc(obj[8]!= null ? Integer.parseInt(obj[8].toString()):0);
            bean.setFecha(obj[9] != null ? obj[9].toString():null);
            bean.setLinea(obj[10] != null ? obj[10].toString():null);

			lst.add(bean);
			
		}	
    	reporteBean.setData(lst);
        
        reporteBean.setPlanta(plantaRepository.findOneByKey(planta_key).getNombre());
        
        lstresult.add(reporteBean);
    	}
    	return lstresult;
    }

    public List<ReporteInspeccionesConsolidadas> getReporteinspeccionesConsolidadas(Date fechIni, Date fechFin){
        List<ReporteInspeccionesConsolidadas> resultado = new ArrayList();
        Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendarIni = Calendar.getInstance();
        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(fechaMax);
        calendarIni.setTime(fechaMin);
        Integer mes[] = {1,2,3,4,5,6,7,8,9,10,11,12};
        int anioIni= calendarIni.get(Calendar.YEAR);
        int mesIni = mes[calendarIni.get(Calendar.MONTH)];
        int diaIni = calendarIni.get(Calendar.DAY_OF_MONTH);

        int anioFni= calendarFin.get(Calendar.YEAR);
        int mesFni = mes[calendarFin.get(Calendar.MONTH)];
        int diaFni = calendarFin.get(Calendar.DAY_OF_MONTH);
        Date fechaconsolidacion= new Date();
        Date fechaanulacion= new Date();
        Date fechacreacion= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        StringBuffer hql1 =  new StringBuffer(" select p.nombre as planta,")
                .append("case when c2.nrodocumentocertificado is not null then c2.nrodocumentocertificado else i.nrodocumentoinforme end as nroinformecertificado,")
                .append("case when i.inspeccionestado_key='CON' then 'CONSOLIDADO'when i.inspeccionestado_key='ANU' then 'ANULADO' when i.inspeccionestado_key='RETIRADO' then 'RETIRADO' ELSE '' END as estado,")
                .append("to_char(c.fechcreacion,'yyyy-mm-dd') as fecha,")
                .append("to_char(c.fechcreacion,'hh:mm:ss') as hora,")
                .append("case when i.nrodocumentoreinspeccion is null then 'NO' else 'SI' end as tienereinspeccion,")
                .append("l.nombre as linea,")
                .append("c.placamotor as placa,")
                .append("case when c.duplicado is null then 'No' else 'Si' end as esduplicado,")
                .append("case when c2.nrohojavalorada is null then 'NO TIENE HOJA' else c2.nrohojavalorada end as Nrohojavalorada,")
                .append("case when i.resultado is null  then 'NO TIENE RESULTADO' when i.resultado='' then 'NO TIENE RESULTADO' else i.resultado end as resulatado,")
                .append("extract(year from c.fechcreacion) as anhio,")
                .append("extract(month from c.fechcreacion) as mes,")
                .append("extract(day from c.fechcreacion) as dia")
                .append(" from comprobante c")
                .append(" inner join inspeccion i on c.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion")
                .append(" inner join linea l on c.linea_key = l.key")
                .append(" inner join planta p on l.planta_key = p.key")
                .append(" left join certificado c2 on i.nrodocumentoinspeccion = c2.inspeccion_nrodocumentoinspeccion")
                .append(" where to_char(c.fechapago,'yyyy-mm-dd') between '"+dateFormat.format(fechaMin)+"' and '"+dateFormat.format(fechaMax)+"' order by i.fechconsolidado asc");
        Query query = entityManager.createNativeQuery(hql1.toString());
        List<Object> result = (List<Object>) query.getResultList();
        Iterator<Object> itr = result.iterator();
        while (itr.hasNext()) {
            ReporteInspeccionesConsolidadas bean = new ReporteInspeccionesConsolidadas();
            Object[] obj = (Object[]) itr.next();
            bean.setPlanta(obj[0]!=null ? obj[0].toString():"");
            bean.setNroinformecerti(obj[1]!=null ? obj[1].toString():"");
            bean.setEstadoinspeccion(obj[2]!=null ? obj[2].toString():"");
            bean.setFecha(obj[3]!=null ? obj[3].toString():"");
            bean.setHora(obj[4]!=null ? obj[4].toString():"");
            bean.setTienereinspeccion(obj[5]!=null ? obj[5].toString():"");
            bean.setLinea(obj[6]!=null ? obj[6].toString():"");
            bean.setPlaca(obj[7]!=null ? obj[7].toString():"");
            bean.setEsduplicado(obj[8]!=null ? obj[8].toString():"");
            bean.setHojavalorada(obj[9]!=null ? obj[9].toString():"");
            bean.setResultado(obj[10]!=null ? obj[10].toString():"");
            bean.setAnhio(obj[11]!=null ? obj[11].toString():"");
            bean.setMes(obj[12]!=null ? obj[12].toString():"");
            bean.setDia(obj[13]!=null ? obj[13].toString():"");
            resultado.add(bean);
        }
        return resultado;
    }
    
  //Listado de inspecciones MTC detalles
    public List<ResultReportBean<ReporteInpeccionesMTCDetalleBean>> getInpeccionesMTCDetalle(Date fechIni, Date fechFin, String planta){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
    	
        SimpleDateFormat format = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);

    	Date fechacreacion= new Date();
    	List<ResultReportBean<ReporteInpeccionesMTCDetalleBean>> lstresult= new ArrayList<>();
    	List<Object> result = new ArrayList<>();
    	
    	List<String> plantas = new ArrayList<>();
    	if(planta.equals("todos")) {
    		plantas = getAllIdPlantaByUser();
    	}else {
    		plantas.add(planta);
    	}
    	
    	for(String idPlanta : plantas) {


        	ResultReportBean<ReporteInpeccionesMTCDetalleBean> reporteBean = new ResultReportBean<>();
    		List<ReporteInpeccionesMTCDetalleBean> lst = new ArrayList<>();
    		
    	StringBuffer hql1 = new StringBuffer(" select placamotor, i.fechcreacion, p.nombre as planta, abreviatura as conceptoInspeccion, t2.nombre as tipoinspeccion, "
    	+ " abreviacion as tipocertificado, ambito as tipoautorizacion from comprobante com "
    	+ " inner join linea l on com.linea_key = l.key "
    	+ " inner join planta p on l.planta_key = p.key"
    	+ " left join inspeccion i on com.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion"
    	+ " inner join conceptoinspeccion c on com.conceptoinspeccion_key = c.key "
    	+ " left join tipoautorizacion t on i.tipoautorizacion_key = t.key"
    	+ " left join tipoinspeccion t2 on i.tipoinspeccion_key = t2.key "
    	+ " left join tipocertificado t3 on i.tipocertificado_key = t3.key"
    	+ " where linea_key is not null and (to_char(com.fechcreacion, 'YYYY-MM-DD') "
    	+ " between '" + format.format(fechaMin) + "' and '" + format.format(fechaMax) +"') AND planta_key = '" + idPlanta + "'");
    	 
    	Query query = entityManager.createNativeQuery(hql1.toString());
    	
    	result = (List<Object>) query.getResultList();
    	Iterator<Object> itr = result.iterator(); 
    	while (itr.hasNext()) {
    		ReporteInpeccionesMTCDetalleBean bean  = new ReporteInpeccionesMTCDetalleBean();
			Object[] obj = (Object[]) itr.next();
			bean.setPlacamotor(obj[0] != null ? obj[0].toString():null);
			if (obj[1] != null) {
				try {
					fechacreacion=format.parse(obj[1].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bean.setFechcreacion(fechacreacion);					
			}
		    bean.setPlanta(obj[2] != null ? obj[2].toString():null);
		    bean.setConceptoinspeccion(obj[3] != null ? obj[3].toString():null);
		    bean.setTipoinspeccion(obj[4] != null ? obj[4].toString():null);
		    bean.setTipocertificado(obj[5] != null ? obj[5].toString():null);
		    bean.setTipoautorizacion(obj[6] != null ? obj[6].toString():null);
			lst.add(bean);
			
    	}
          reporteBean.setData(lst);
          
          reporteBean.setPlanta(plantaRepository.findOneByKey(idPlanta).getNombre());
          
          lstresult.add(reporteBean);
		}
    	
    	return lstresult;
    }
    
    //Listado de reinspecciones placa nueva
    public List<ResultReportBean<ReporteReinspeccionesPlacaNuevaBean>> getReinspeccionesPlacaNueva(Date fechIni, Date fechFin, String planta){
    	Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
    	
        SimpleDateFormat format = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);

    	Date fecha= new Date();
    	Date fechamodificacion= new Date();
    	List<ResultReportBean<ReporteReinspeccionesPlacaNuevaBean>> lstresult= new ArrayList<>();
    	List<Object> result = new ArrayList<>();
    	
    	List<String> plantas = new ArrayList<>();
    	if(planta.equals("todos")) {
    		plantas = getAllIdPlantaByUser();
    	}else {
    		plantas.add(planta);
    	}
    	
    	for(String idPlanta : plantas) {


        	ResultReportBean<ReporteReinspeccionesPlacaNuevaBean> reporteBean = new ResultReportBean<>();
    		List<ReporteReinspeccionesPlacaNuevaBean> lst = new ArrayList<>();
    	
    	StringBuffer hql1 = new StringBuffer(""
    			+ "select  p.nombre as Planta,i.fechcreacion as Fecha,  com.placamotor as Placa_Antigua,tc.abreviacion as tipo_servicio,i.nrodocumentoinforme,com.nrocomprobante,com.importetotal," + 
    			"        tp.fechmodi as Fecha2, tp.nroplaca as Placa_Nueva,c.nrodocumentocertificado from comprobante com " + 
    			"        inner join linea l on com.linea_key = l.key" + 
    			"        inner join planta p on l.planta_key = p.key" + 
    			"        inner join inspeccion i on com.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion" + 
    			"        inner join vehiculo v on i.vehiculo_nromotor=v.nromotor" + 
    			"        inner join tarjetapropiedad tp on v.tarjetapropiedad_id=tp.id" + 
    			"		 inner join tipocertificado tc on tc.key=i.tipocertificado_key" + 
    			"		 inner join certificado c on c.inspeccion_nrodocumentoinspeccion=(select cc.inspeccion_nrodocumentoinspeccion from comprobante cc where cc.placamotor=tp.nroplaca)"+
    			"        where p.key = '" + idPlanta + "' and (to_char(i.fechcreacion, 'YYYY-MM-DD')" + 
    			"        between '" + format.format(fechaMin) + "' and '" + format.format(fechaMax) + "') AND (i.observacionanulado like '%INSPECCION CON PLACA%' OR i.observacionanulado like '%INSPECCION POR PLACA%')" + 
    			"");
    	 
    	Query query = entityManager.createNativeQuery(hql1.toString());
    	
    	result = (List<Object>) query.getResultList();
    	Iterator<Object> itr = result.iterator(); 
    	while (itr.hasNext()) {
    		ReporteReinspeccionesPlacaNuevaBean bean  = new ReporteReinspeccionesPlacaNuevaBean();
			Object[] obj = (Object[]) itr.next();
			bean.setPlanta(obj[0] != null ? obj[0].toString():null);
			if (obj[1] != null) {
				try {
					fecha=format.parse(obj[1].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bean.setFecha(fecha);					
			}
		    bean.setPlaca_antigua(obj[2] != null ? obj[2].toString():"");
		    bean.setTiposervicio(obj[3] != null ? obj[3].toString():"");
		    bean.setNrodocumentoinforme(obj[4] != null ? obj[4].toString():"");
		    bean.setNrocomprobante(obj[5]!=null?obj[5].toString():"");
		    bean.setImportetotal(obj[6]!=null? Double.parseDouble(obj[6].toString()):0);
			if (obj[7] != null) {
				try {
					fechamodificacion=format.parse(obj[7].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bean.setFechmodificacion(fechamodificacion);					
			}
		    bean.setPlaca_nueva(obj[8]!=null?obj[8].toString():"");
		    bean.setNumcertificado(obj[9]!=null?obj[9].toString():"");
			lst.add(bean);	
    	}

        reporteBean.setData(lst);	
        reporteBean.setPlanta(plantaRepository.findOneByKey(idPlanta).getNombre());
        lstresult.add(reporteBean);
	}
    	return lstresult;
    }
    
  
	private String dateFormat(Date dateFin) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    
	
}


