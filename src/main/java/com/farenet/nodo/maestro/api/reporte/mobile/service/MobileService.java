package com.farenet.nodo.maestro.api.reporte.mobile.service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Descuento;
import com.farenet.nodo.maestro.api.caja.domain.DescuentoCliente;
import com.farenet.nodo.maestro.api.caja.domain.DescuentoDetalle;
import com.farenet.nodo.maestro.api.caja.domain.Formapago;
import com.farenet.nodo.maestro.api.caja.domain.TipoPagoDescuento;
import com.farenet.nodo.maestro.api.caja.repository.DescuentoDetalleRepository;
import com.farenet.nodo.maestro.api.caja.service.DescuentoService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Certificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CartaSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CortesiaSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.DescuentoSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.EdicionInspeccionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.EstadisticaBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlacaInspeccionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ReportAllBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ReportBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ReportBean.PlantReport;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class MobileService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PlantaRepository plantaRepository;
	
	@Autowired
	private DescuentoService descuentoService;
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private DescuentoDetalleRepository descuentoDetalleRepository;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	public List<EstadisticaBean> getEstadistica(String fecha) {
		List<EstadisticaBean> result = new ArrayList<>();
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		
		StringBuffer hql = new StringBuffer("select p.key, p.nombre,")
				.append(" (sum(case when i.nrodocumentoreinspeccion is not null then 1 else 0 end )) as reinspeccion,")
				.append(" (sum(case when com.duplicado = true then 1 else 0 end )) as duplicado,")
				.append(" (sum(case when i.inspeccionestado_key = 'ANU' then 1 else 0 end )) as anulado,")
				.append(" (sum(case when cer.observacionanulado is not null and cer.observacionanulado != 'DUPLICADO' then 1 else 0 end)) as reimpresion,")
				.append(" (sum(case when cer.observacionanulado is not null and cer.observacionanulado != 'DUPLICADO'")
				.append(" and cer.fechanulacion BETWEEN cer.fechanulacion AND (ce.fechacreacion + interval '30 minute') then 1 else 0 end)) as errorimpresion,")
				.append(" (sum(com.totaldscto)) as totalDescuento,")
				.append(" (sum(case when d.tipodescuento_key = 'promocion' then com.totaldscto else 0 end)) as montopromocion,")
				.append(" (sum(case when d.tipodescuento_key = 'aliestrategica' then com.totaldscto else 0 end)) as montoalianza,")
				.append(" (sum(case when d.tipodescuento_key = 'cotizacion' then com.totaldscto else 0 end)) as montocotizacion,")
				.append(" (sum(case when d.tipodescuento_key = 'corte' then com.totaldscto else 0 end)) as montocortesia")
				.append(" from comprobante com")
				.append(" inner join inspeccion i on com.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion")
				.append(" inner join linea l on com.linea_key = l.key")
				.append(" inner join planta p on l.planta_key = p.key")
				.append(" left outer join descuentocomprobante d on com.descuentocomprobante_id = d.id")
				.append(" left outer join certificado cer on i.nrodocumentoinspeccion = cer.inspeccion_nrodocumentoinspeccion")
				.append(" left outer join certificado_error ce on cer.nrodocumentocertificado = ce.certificado_nrodocumentocertificado")
				.append(" where to_date(to_char(com.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+fecha+"' and '"+fecha+"'")
				.append(" group by p.key, p.nombre")
				.append(" order by p.key");
		
		Query query = entityManager.createNativeQuery(hql.toString());

		List<Object> objects = (List<Object>) query.getResultList();
		Iterator<Object> itr = objects.iterator();
		while(itr.hasNext()){
			EstadisticaBean bean = new EstadisticaBean();
			Object[] obj = (Object[]) itr.next();
			bean.key = String.valueOf(obj[0]);
			bean.nombre = String.valueOf(obj[1]);
			bean.cantidadReins = Long.valueOf(!String.valueOf(obj[2]).equals("null") ? String.valueOf(obj[2]):"0");
			bean.cantidadDupli = Long.valueOf(!String.valueOf(obj[3]).equals("null") ? String.valueOf(obj[3]):"0");
			bean.cantidadAnu = Long.valueOf(!String.valueOf(obj[4]).equals("null") ? String.valueOf(obj[4]):"0");
			bean.cantidadReimpr = Long.valueOf(!String.valueOf(obj[5]).equals("null") ? String.valueOf(obj[5]):"0");
			bean.cantidadErrorImpr = Long.valueOf(!String.valueOf(obj[6]).equals("null") ? String.valueOf(obj[6]):"0");
			bean.montoDesc = UIUtils.round(Double.valueOf(!String.valueOf(obj[7]).equals("null") ? String.valueOf(obj[7]):"0"), 2);
			bean.montoPromocion = UIUtils.round(Double.valueOf(!String.valueOf(obj[8]).equals("null") ? String.valueOf(obj[8]):"0"), 2);
			bean.montoAlianza = UIUtils.round(Double.valueOf(!String.valueOf(obj[9]).equals("null") ? String.valueOf(obj[9]):"0"), 2);
			bean.montoCotizacion = UIUtils.round(Double.valueOf(!String.valueOf(obj[10]).equals("null") ? String.valueOf(obj[10]):"0"), 2);
			bean.montoCortesia = UIUtils.round(Double.valueOf(!String.valueOf(obj[11]).equals("null") ? String.valueOf(obj[11]):"0"), 2);
			bean.id = bean.key+"_"+fecha;
			bean.fecha = fecha;
			
			result.add(bean);
		}
		
		return result;
	}

	public ReportBean getReport(Date date) throws Exception {

		SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		String fechaIni = dtfPeru.format(date);
		String fechaFin = dtfPeru.format(calendar.getTime());

		List<ReportAllBean> allBeans = new ArrayList<>();
		ReportBean reportBean = new ReportBean();

		StringBuffer hql = new StringBuffer("select ")
				.append("    case ")
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-98-%' then '98'")
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-103-%' then '103'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-13-%' then '13'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-140-%' then '140' ")
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-133-%' then '133'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-61-%' then '61'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-139-%' then '139'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-43-%' then '43'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-138-%' then '138'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-18-%' then '18'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-125-%' then '125'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-25-%' then '25'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-200-%' then '200'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-201-%' then '201'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-202-%' then '202'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-150-%' then '150'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-160-%' then '160'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-203-%' then '203'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-170-%' then '170'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-180-%' then '180'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-190-%' then '190'" )
				.append("        else null" )
				.append("    end as planta_nombre," )
				.append("    sum(c.importetotal) as importe_total, " )
				.append("    sum(case when c.importetotal is null or c.importetotal <> 0 then 1 else 0 end) as facturados," )
				.append("    sum(case when ins.nrodocumentoreinspeccion is not null then 1 else 0 end) as inspecciones," ) 
				.append("    cast(sum(case when ins.resultado = 'D' then 1 else 0 end) as float)/cast(sum(case when ins.inspeccionestado_key is null or ins.inspeccionestado_key <> 'ANU' then 1 else 0 end) as float)*100 as desaprobados" )
				.append("  from comprobante as c" )
				.append("  inner join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" )
				.append("  where c.fechapago > '"+fechaIni+"' and c.fechapago < '"+fechaFin+"' ")
				.append("  and ins.inspeccionestado_key is not null" )
				.append("  and ins.inspeccionestado_key <> 'ANU'" )
				.append("  and (c.tipodescuento_key <> 'corte' or c.tipodescuento_key is null) ")
				.append("  group by GROUPING SETS" )
				.append("    (case " )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-98-%' then '98'")
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-103-%' then '103'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-13-%' then '13'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-140-%' then '140' ")
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-133-%' then '133'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-61-%' then '61'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-139-%' then '139'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-43-%' then '43'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-138-%' then '138'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-18-%' then '18'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-125-%' then '125'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-25-%' then '25'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-200-%' then '200'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-201-%' then '201'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-202-%' then '202'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-150-%' then '150'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-160-%' then '160'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-203-%' then '203'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-170-%' then '170'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-180-%' then '180'" )
				.append("        when c.inspeccion_nrodocumentoinspeccion like 'INS-190-%' then '190'" )
				.append("        else null" )
				.append("    end, () ) " );

		Query query = entityManager.createNativeQuery(hql.toString());

		List<Object> result = (List<Object>) query.getResultList();
		Iterator<Object> itr = result.iterator();
		while(itr.hasNext()){
			ReportAllBean allBean = new ReportAllBean();
			Object[] obj = (Object[]) itr.next();
			allBean.setPlantakey(String.valueOf(obj[0]));
			allBean.setImporte(Double.valueOf(!String.valueOf(obj[1]).equals("null") ? String.valueOf(obj[1]):"0"));
			allBean.setFacturados(Long.valueOf(!String.valueOf(obj[2]).equals("null") ? String.valueOf(obj[2]):"0"));
			allBean.setInspecciones(Long.valueOf(!String.valueOf(obj[3]).equals("null") ? String.valueOf(obj[3]):"0"));
			allBean.setDesaprobados(Double.valueOf(!String.valueOf(obj[4]).equals("null") ? String.valueOf(obj[4]):"0"));
			allBeans.add(allBean);
		}

		List<ReportBean.PlantReport> plantReports = new ArrayList<>();

		for(ReportAllBean bean : allBeans) {
			if(!bean.getPlantakey().equals("null")) {
				ReportBean.PlantReport plantReport = new PlantReport();
				plantReport.id =bean.getPlantakey()+"_"+date.getTime();
				plantReport.name = plantaRepository.findNombreById(bean.getPlantakey());
				plantReport.income = (double) Math.round(bean.getImporte()  * 100d) /100d;
				plantReport.processed = Integer.valueOf(bean.getInspecciones().toString());
				plantReport.invoiced = bean.getFacturados().longValue();
				plantReport.disapproved = (double) Math.round(bean.getDesaprobados()  * 100d) /100d;
				plantReports.add(plantReport);
			}else {
				reportBean.id = String.valueOf(date.getTime() / 1000);
				reportBean.totalDisapproved = (double) Math.round(bean.getDesaprobados()  * 100d) /100d;
				reportBean.totalIncome = (double) Math.round(bean.getImporte());
				reportBean.totalInvoiced = bean.getFacturados().longValue();
				reportBean.date = date.getTime() / 1000;
			}
		}
		reportBean.plantReports = plantReports;

		if(plantReports.isEmpty()) {
			throw new Exception("No hay reporte para este dia");
		}
    	
    	/*
    	
    	List<ReportBean.PlantReport> plantReports = new ArrayList<>();

        Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
        Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU"); 
        Timestamp fechaCurrent = new Timestamp(date.getTime());
        
        String hql1 = "SELECT new "+ReportBean.class.getName()
        		+"('',sum(com.importetotal),count(ins))"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
                + " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax"
                + " and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0 ";
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechapagoMin", fechaCurrent);
        query.setParameter("fechapagoMax", fechaCurrent);
        query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        reportBean = (ReportBean) query.getSingleResult();
        
        
        reportBean.id = String.valueOf(date.getTime() / 1000);
        reportBean.date = date.getTime() / 1000;
        
        String hql2 = "SELECT new "+ReportBean.PlantReport.class.getName()
        		+"(CONCAT(pl.key,'_',:fech),pl.nombre,sum(com.importetotal),count(com))"
        		+ " FROM Comprobante com"
        		+ " inner join com.inspeccion ins"
        		+ " inner join com.linea li"
                + " inner join li.planta pl"
                + " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax "
                + "and com.comprobanteestado != :comprobanteestadoanulado and com.importetotal != 0 group by pl.key ";
        Query query2 = entityManager.createQuery(hql2);
        query2.setParameter("fechapagoMin", fechaCurrent);
        query2.setParameter("fechapagoMax", fechaCurrent);
        query2.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
        query2.setParameter("fech", date.getTime());
        plantReports = query2.getResultList();
        
        for(ReportBean.PlantReport report : plantReports) {
            report.processed = getProcesados(fechaCurrent, report.name, inspeccionestadoAnulado);
            report.disapproved = getPorcDesaprobado(fechaCurrent, report.name, comprobanteestadoAnulado, report.processed);
            reportBean.totalDisapproved += (report.disapproved * report.invoiced) / 100d;

        }
        
        reportBean.totalDisapproved = (reportBean.totalDisapproved * 100d) / reportBean.totalInvoiced;
        reportBean.totalDisapproved = (double) Math.round(reportBean.totalDisapproved * 100d) / 100d;
        reportBean.plantReports = plantReports;
        */
		return reportBean;
	}

	private int getProcesados(Timestamp fechaCurrent,String plantaNombre, Inspeccionestado inspeccionestadoAnulado) {

		String hqlProc = "SELECT count(com) "
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join com.linea li"
				+ " inner join li.planta pl"
				+ " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax"
				+ " and ins.inspeccionestado != :inspeccionestadoAnulado and pl.nombre = :planta ";
		Query query = entityManager.createQuery(hqlProc);
		query.setParameter("fechapagoMin", fechaCurrent);
		query.setParameter("fechapagoMax", fechaCurrent);
		query.setParameter("inspeccionestadoAnulado", inspeccionestadoAnulado);
		query.setParameter("planta", plantaNombre);
		return  ((Long) query.getSingleResult()).intValue();

	}

	private double getPorcDesaprobado(Timestamp fechaCurrent,String plantaNombre, Comprobanteestado comprobanteestadoAnulado, int processed) {
		String hqlDes = "SELECT count(ins)"
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join com.linea li"
				+ " inner join li.planta pl"
				+ " WHERE to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax"
				+ " and ins.resultado = 'D' " + " and com.comprobanteestado != :comprobanteestadoanulado and pl.nombre = :planta ";

		Query query = entityManager.createQuery(hqlDes);
		query.setParameter("fechapagoMin", fechaCurrent);
		query.setParameter("fechapagoMax", fechaCurrent);
		query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
		query.setParameter("planta", plantaNombre);
		Long number = (Long) query.getSingleResult();
		Double pDesaprobadoDouble = Double.valueOf((Double.valueOf(number) / Double.valueOf(processed)) * 100);
		Double pDesaprobado = Util.round(pDesaprobadoDouble, 2);
		return pDesaprobado;
	}
	
	public void saveDescuento(DescuentoSaveBean descuentoSaveBean) throws Exception {
		
		Planta planta = maestroService.getPlantaByKey(descuentoSaveBean.getPlanta());
		Conceptoinspeccion conceptoinspeccion = maestroService.getConceptoByKey(descuentoSaveBean.getConceptoinspeccion());
		TipoPagoDescuento tipoPagoDescuento = maestroService.getTipoPagoDescuentoByKey(descuentoSaveBean.getTipoPagoDescuento());
		Formapago formapago = maestroService.getFormaPagoByKey("contado");
		
		Calendar calendar = Calendar.getInstance();
		String anho = calendar.get(Calendar.YEAR)+"";
		anho = anho.substring(2, 4);
		String mes = new SimpleDateFormat("MMMM",new Locale("es", "ES")).format(calendar.getTime()).substring(0,3).toUpperCase();
		
		String hql = "SELECT d"
				+ " FROM Descuento d"
				+ " inner join d.tipodescuento td"
				+ " where td.key = 'aliestrategica' and d.nombre like :nombre";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nombre", "%AUTORIZADO%"+mes+anho+"%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		Descuento descuento = (Descuento) query.getSingleResult();
    	
    	if(descuento == null) {
    		throw new Exception("Descuento no existe"); 
    	}
    	
    	DescuentoDetalle newDetalle = new DescuentoDetalle();
    	newDetalle.setConceptoinspeccion(conceptoinspeccion);
    	newDetalle.setPlanta(planta);
    	newDetalle.setTipoPagoDescuento(tipoPagoDescuento);
    	newDetalle.setMonto(descuentoSaveBean.getMonto());
    	newDetalle.setMontoActivacion(descuentoSaveBean.getMonto());
    	newDetalle.setUsuariocreacion(maestroService.getOneByUsername(Util.getUserNameContext()));
    	newDetalle.setFormaPago(formapago);
    	newDetalle.setFechInicio(descuento.getFechInicio());
    	newDetalle.setFechFin(descuento.getFechFin());
    	newDetalle.setDescuento(descuento);
    	
    	DescuentoCliente cliente = new DescuentoCliente();
    	cliente.setPlaca(descuentoSaveBean.getPlaca());
    	cliente.setFechInicio(descuento.getFechInicio());
    	cliente.setDiasEmpieza(10l);
    	List<DescuentoCliente> list = new ArrayList<>();
    	list.add(cliente);
    	newDetalle.setDescuentoclientes(list);
    	
    	descuentoDetalleRepository.save(newDetalle);
    	
	}
	
	public void saveCortesia(CortesiaSaveBean cortesiaSaveBean) {
		Planta planta = maestroService.getPlantaByKey(cortesiaSaveBean.getPlanta());
		TipoPagoDescuento tipoPagoDescuento = maestroService.getTipoPagoDescuentoByKey("POR");
		Formapago formapago = maestroService.getFormaPagoByKey("contado");
		
		Calendar calendar = Calendar.getInstance();
		String anho = calendar.get(Calendar.YEAR)+"";
		anho = anho.substring(2, 4);
		String mes = new SimpleDateFormat("MMMM",new Locale("es", "ES")).format(calendar.getTime()).substring(0,3).toUpperCase();
		
		String hql = "SELECT d"
				+ " FROM Descuento d"
				+ " inner join d.tipodescuento td"
				+ " where td.key = 'corte' and d.nombre like :nombre";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nombre", "%"+mes+anho+"%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		Descuento descuento = (Descuento) query.getSingleResult();
		List<DescuentoDetalle> lstDetalles = new ArrayList<>();
		
		Usuario usuario = maestroService.getOneByUsername(Util.getUserNameContext());
		
		
		List<Conceptoinspeccion> lstConcepto = maestroService.listarConceptoinspecciones(true);
		DescuentoCliente cliente = new DescuentoCliente();
    	cliente.setPlaca(cortesiaSaveBean.getPlaca());
    	cliente.setFechInicio(descuento.getFechInicio());
    	cliente.setDiasEmpieza(10l);
    	List<DescuentoCliente> list = new ArrayList<>();
    	list.add(cliente);
		
		for(Conceptoinspeccion conceptoinspeccion : lstConcepto) {
			DescuentoDetalle descuentoDetalle = new DescuentoDetalle();
			descuentoDetalle.setConceptoinspeccion(conceptoinspeccion);
			descuentoDetalle.setPlanta(planta);
			descuentoDetalle.setTipoPagoDescuento(tipoPagoDescuento);
			descuentoDetalle.setMonto(0d);
			descuentoDetalle.setUsuariocreacion(usuario);
			descuentoDetalle.setMontoActivacion(0d);
			descuentoDetalle.setFormaPago(formapago);
			descuentoDetalle.setFechInicio(descuento.getFechInicio());
			descuentoDetalle.setFechFin(descuento.getFechFin());
			descuentoDetalle.setDescuento(descuento);
	    	
	    	
	    	descuentoDetalle.setDescuentoclientes(list);
	    	lstDetalles.add(descuentoDetalle);
		}
		
		descuentoDetalleRepository.saveAll(lstDetalles);
	}
	
	public void saveCarta(CartaSaveBean cartaSaveBean) throws Exception {
		
		Planta planta = maestroService.getPlantaByKey(cartaSaveBean.getPlanta());
		Conceptoinspeccion conceptoinspeccion = maestroService.getConceptoByKey(cartaSaveBean.getConceptoinspeccion());
		TipoPagoDescuento tipoPagoDescuento = maestroService.getTipoPagoDescuentoByKey(cartaSaveBean.getTipoPagoDescuento());
		Formapago formapago = maestroService.getFormaPagoByKey("contado");
		
		Calendar calendar = Calendar.getInstance();
		String anho = calendar.get(Calendar.YEAR)+"";
		anho = anho.substring(2, 4);
		String mes = new SimpleDateFormat("MMMM",new Locale("es", "ES")).format(calendar.getTime()).substring(0,3).toUpperCase();
		
		String hql = "SELECT d"
				+ " FROM Descuento d"
				+ " inner join d.tipodescuento td"
				+ " where td.key = 'aliestrategica' and d.nombre like :nombre";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nombre", "%CARTA%"+mes+anho+"%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		Descuento descuento = (Descuento) query.getSingleResult();
    	
    	if(descuento == null) {
    		throw new Exception("Descuento no existe"); 
    	}
    	
    	DescuentoDetalle newDetalle = new DescuentoDetalle();
    	newDetalle.setConceptoinspeccion(conceptoinspeccion);
    	newDetalle.setPlanta(planta);
    	newDetalle.setTipoPagoDescuento(tipoPagoDescuento);
    	newDetalle.setMonto(cartaSaveBean.getMonto());
    	newDetalle.setMontoActivacion(cartaSaveBean.getMonto());
    	newDetalle.setUsuariocreacion(maestroService.getOneByUsername(Util.getUserNameContext()));
    	newDetalle.setFormaPago(formapago);
    	newDetalle.setFechInicio(descuento.getFechInicio());
    	newDetalle.setFechFin(descuento.getFechFin());
    	newDetalle.setDescuento(descuento);
    	
    	DescuentoCliente cliente = new DescuentoCliente();
    	cliente.setPlaca(cartaSaveBean.getPlaca());
    	cliente.setFechInicio(descuento.getFechInicio());
    	cliente.setDiasEmpieza(10l);
    	List<DescuentoCliente> list = new ArrayList<>();
    	list.add(cliente);
    	newDetalle.setDescuentoclientes(list);
    	
    	descuentoDetalleRepository.save(newDetalle);
		
	}
	
	
	
	public List<PlacaInspeccionBean> getPlacaInspeccionByPlacaandFecha(String placa, String fecha, String planta) {
		String[] parte = fecha.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(parte[0]), Integer.parseInt(parte[1])-1, Integer.parseInt(parte[2]));
		Timestamp fechaTime = new Timestamp(calendar.getTimeInMillis());
		
		
		String hql = "SELECT new "+PlacaInspeccionBean.class.getName()+
				"(ins.nrodocumentoinspeccion, pl.nombre, ins.fechcreacion, com.placaMotor, com.nrocomprobante,"
				+ "ci.abreviatura, li.nombre, ie.nombre, ins.resultado, mar.nombre, mod.nombre,"
				+ "CONCAT(cli.nombres,' ', cli.apellidos), cli.nombrerazonsocial, cert.nrodocumentoCertificado, tins.key,"
				+ "taut.key, tcer.key, using.user, cert.nrohojaValorada)"
				+ " FROM Comprobante com"
				+ " inner join com.cliente cli"
				+ " inner join com.inspeccion ins"
				+ " left join ins.tipocertificado tcer"
				+ " left join ins.tipoinspeccion tins"
				+ " left join ins.tipoautorizacion taut"
				+ " left join ins.usuarioingcertificador using"
				+ " left join ins.certificados cert"
				+ " inner join ins.vehiculo ve"
				+ " inner join ve.marca mar"
				+ " inner join ve.modelo mod"
				+ " inner join ins.inspeccionestado ie"
				+ " inner join com.linea li"
				+ " inner join li.planta pl"
				+ " inner join com.conceptoInspeccion ci"
				+ " where com.placaMotor like :placa and pl.key = :planta and "
				+ " to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax";
		Query query = entityManager.createQuery(hql);
		query.setParameter("placa", "%"+placa.toUpperCase()+ "%");
		query.setParameter("fechaMin", fechaTime);
		query.setParameter("fechaMax", fechaTime);
		query.setParameter("planta", planta);
		
		return query.getResultList();
	}

	public List<PlacaInspeccionBean> getPlacaInspeccionNoConsolidadoByPlacaandFecha(String placa, String fecha, String planta) {
		String[] parte = fecha.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(parte[0]), Integer.parseInt(parte[1])-1, Integer.parseInt(parte[2]));
		Timestamp fechaTime = new Timestamp(calendar.getTimeInMillis());
		
		
		String hql = "SELECT new "+PlacaInspeccionBean.class.getName()+
				"(ins.nrodocumentoinspeccion, pl.nombre, ins.fechcreacion, com.placaMotor, com.nrocomprobante,"
				+ "ci.abreviatura, li.nombre, ie.nombre, ins.resultado, mar.nombre, mod.nombre,"
				+ "CONCAT(cli.nombres,' ', cli.apellidos), cli.nombrerazonsocial, cert.nrodocumentoCertificado, tins.key,"
				+ "taut.key, tcer.key, using.user, cert.nrohojaValorada)"
				+ " FROM Comprobante com"
				+ " inner join com.cliente cli"
				+ " inner join com.inspeccion ins"
				+ " left join ins.tipocertificado tcer"
				+ " left join ins.tipoinspeccion tins"
				+ " left join ins.tipoautorizacion taut"
				+ " left join ins.usuarioingcertificador using"
				+ " left join ins.certificados cert"
				+ " inner join ins.vehiculo ve"
				+ " inner join ve.marca mar"
				+ " inner join ve.modelo mod"
				+ " inner join ins.inspeccionestado ie"
				+ " inner join com.linea li"
				+ " inner join li.planta pl"
				+ " inner join com.conceptoInspeccion ci"
				+ " where com.placaMotor like :placa and pl.key = :planta and ie.key != 'CON' and "
				+ " to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax";
		Query query = entityManager.createQuery(hql);
		query.setParameter("placa", "%"+placa.toUpperCase()+ "%");
		query.setParameter("fechaMin", fechaTime);
		query.setParameter("fechaMax", fechaTime);
		query.setParameter("planta", planta);
		
		return query.getResultList();
	}

}
