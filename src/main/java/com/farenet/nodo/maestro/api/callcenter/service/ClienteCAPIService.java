package com.farenet.nodo.maestro.api.callcenter.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Departamento;
import com.farenet.nodo.maestro.api.caja.domain.Distrito;
import com.farenet.nodo.maestro.api.caja.domain.Pais;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.domain.Provincia;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumentoidentidad;
import com.farenet.nodo.maestro.api.caja.repository.DepartamentoRepository;
import com.farenet.nodo.maestro.api.caja.repository.DistritoRepository;
import com.farenet.nodo.maestro.api.caja.repository.PaisRepository;
import com.farenet.nodo.maestro.api.caja.repository.PersonaRepository;
import com.farenet.nodo.maestro.api.caja.repository.ProvinciaRepository;
import com.farenet.nodo.maestro.api.caja.repository.TipodocumentoidentidadRepository;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.callcenter.bean.ClienteCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.ClienteSMSCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.ConceptoCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.PropietarioCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.SoatCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.VehiculoCAPI;
import com.farenet.nodo.maestro.api.inspeccion.domain.Certificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.ConceptoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.inspeccion.service.PlantaService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;

@Service
@Transactional
public class ClienteCAPIService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PaisRepository paisRepository;


    @Autowired
    private InspeccionService inspeccionService;;

    @Autowired
    private TriggerTaskFactory trigger;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private InspeccionMigradaService inspeccionMigradaService;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private PlantaService plantaService;

    @Autowired
    private MaestroService maestroService;

    @Autowired
    private ConceptoinspeccionRepository conceptoInspeccionRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    @Autowired
    private TipodocumentoidentidadRepository tipodocumentoidentidadRepository;


    public List<ClienteSMSCAPI> getAllSMSClienteVencidoClientesfindByFecha(String fechFin){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, -Integer.parseInt(fechFin));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        Calendar calendarHOY = Calendar.getInstance();
        calendarHOY.setTime(new Date());
        Timestamp fecha = new Timestamp(calendarHOY.getTime().getTime());
        String fechaHOY = dtfPeru.format(fecha);

        List<ClienteSMSCAPI> resultado = new ArrayList<>();


        String hql="" +
                " select "+
                " p.nombres, p.apellidos,ins3.fechvencimiento,c.placaMotor,p.telefono" +
                " from inspeccion as ins3 ,comprobante as c, persona as p" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1 " +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2 " +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and c.cliente_nrodocumentoidentidad NOT IN " +
                " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true " +
                " and empresa_nrodocumentoidentidad is not null)" +
                " and c.inspeccion_nrodocumentoinspeccion = ins3.nrodocumentoinspeccion " +
                " and c.cliente_nrodocumentoidentidad= p.nrodocumentoidentidad";

        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        Iterator<Object> itr = resultadoTmp.iterator();


        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            ClienteSMSCAPI smscapi = new ClienteSMSCAPI(
                    String.valueOf(obj[0]),
                    String.valueOf(obj[1]),
                    (Date)obj[2],
                    String.valueOf(obj[3]),
                    String.valueOf(obj[4]));

            resultado.add(smscapi);

        }



        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select "+
                        " p.nombres, p.apellidos,tmpins.fechavencimiento,c.placaMotor,p.telefono" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento " +
                        " and c.cliente_nrodocumentoidentidad NOT IN " +
                        " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true" +
                        " and empresa_nrodocumentoidentidad is not null)" +
                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +

                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        resultadoTmp = (List<Object>) queryTmp.getResultList();

        resultado = resultado.stream().filter(Objects::nonNull).collect(Collectors.toList());
        resultadoTmp= resultadoTmp.stream().filter(Objects::nonNull).collect(Collectors.toList());


        //comparar entre ambas listas para crear una sola
        itr = resultadoTmp.iterator();


        while(itr.hasNext()){

            Object[] obj = (Object[]) itr.next();
            boolean found = false;

            for(ClienteSMSCAPI pcapi: resultado)
            {
                String telefonoTmp = String.valueOf(obj[3]);
                if(pcapi.getPlaca().equals(telefonoTmp))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                resultado.add(new ClienteSMSCAPI(
                        String.valueOf(obj[0]),
                        String.valueOf(obj[1]),
                        (Date)obj[2],
                        String.valueOf(obj[3]),
                        String.valueOf(obj[4])));
            }

        }

        //seteamos la fecha
        for(ClienteSMSCAPI sms: resultado)
        {
            sms.setFechaVencimiento(dtf.format(sms.getFecha()));
        }

        trigger.launch("", "callcenter.sms.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }

    public List<ClienteSMSCAPI> getAllSMSClientePorVencerClientesfindByFecha(String fechFin){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, Integer.parseInt(fechFin));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        Calendar calendarHOY = Calendar.getInstance();
        calendarHOY.setTime(new Date());
        Timestamp fecha = new Timestamp(calendarHOY.getTime().getTime());
        String fechaHOY = dtfPeru.format(fecha);

        List<ClienteSMSCAPI> resultado = new ArrayList<>();

        String hql="" +
                " select "+
                " p.nombres, p.apellidos,ins3.fechvencimiento,c.placaMotor,p.telefono" +
                " from inspeccion as ins3 ,comprobante as c, persona as p" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1 " +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2 " +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and c.cliente_nrodocumentoidentidad NOT IN " +
                " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true " +
                " and empresa_nrodocumentoidentidad is not null)" +
                " and c.inspeccion_nrodocumentoinspeccion = ins3.nrodocumentoinspeccion " +
                " and c.cliente_nrodocumentoidentidad= p.nrodocumentoidentidad";

        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        Iterator<Object> itr = resultadoTmp.iterator();


        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            ClienteSMSCAPI smscapi = new ClienteSMSCAPI(
                    String.valueOf(obj[0]),
                    String.valueOf(obj[1]),
                    (Date)obj[2],
                    String.valueOf(obj[3]),
                    String.valueOf(obj[4]));

            resultado.add(smscapi);

        }

        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select "+
                        " p.nombres, p.apellidos,tmpins.fechavencimiento,c.placaMotor,p.telefono" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento " +
                        " and c.cliente_nrodocumentoidentidad NOT IN " +
                        " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true" +
                        " and empresa_nrodocumentoidentidad is not null)" +

                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +

                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        resultadoTmp = (List<Object>) queryTmp.getResultList();

        resultado = resultado.stream().filter(Objects::nonNull).collect(Collectors.toList());
        resultadoTmp= resultadoTmp.stream().filter(Objects::nonNull).collect(Collectors.toList());


        //comparar entre ambas listas para crear una sola
        itr = resultadoTmp.iterator();


        while(itr.hasNext()){

            Object[] obj = (Object[]) itr.next();
            boolean found = false;

            for(ClienteSMSCAPI pcapi: resultado)
            {
                String telefonoTmp = String.valueOf(obj[3]);
                if(pcapi.getPlaca().equals(telefonoTmp))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                resultado.add(new ClienteSMSCAPI(
                        String.valueOf(obj[0]),
                        String.valueOf(obj[1]),
                        (Date)obj[2],
                        String.valueOf(obj[3]),
                        String.valueOf(obj[4])));
            }

        }

        //seteamos la fecha
        for(ClienteSMSCAPI sms: resultado)
        {
            sms.setFechaVencimiento(dtf.format(sms.getFecha()));
        }

        trigger.launch("", "callcenter.sms.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }




    public List<String> getAllSMSPorVencerClientesfindByFecha(String fechFin){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, Integer.parseInt(fechFin));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        List<String> resultado = new ArrayList<>();


        String hql="" +
                " select distinct p.telefono from inspeccion as ins3 , vehiculo as v, tarjetapropiedad as tp, persona as p" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1 " +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2 " +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and ins3.vehiculo_nromotor = v.nromotor" +
                " and v.tarjetapropiedad_id = tp.id " +
                " and tp.propietario_nrodocumentoidentidad = p.nrodocumentoidentidad";

        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        resultado = (List<String>) queryTmp.getResultList();



        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select " +
                        " p.telefono " +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento " +

                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +

                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        List<String> resultadoTmp = (List<String>) queryTmp.getResultList();

        resultado = resultado.stream().filter(Objects::nonNull).collect(Collectors.toList());
        resultadoTmp= resultadoTmp.stream().filter(Objects::nonNull).collect(Collectors.toList());

        //comparar entre ambas listas para crear una sola
        for(String pcapiTmp : resultadoTmp)
        {
            boolean found = false;
            for(String pcapi: resultado)
            {
                if(pcapi.equals(pcapiTmp.toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.sms.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }

    public List<String> getAllSMSVencidoClientesfindByFecha(String fechFin){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, -Integer.parseInt(fechFin));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        List<String> resultado = new ArrayList<>();


        String hql="" +
                " select distinct p.telefono from inspeccion as ins3 , vehiculo as v, tarjetapropiedad as tp, persona as p" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1 " +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2 " +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and ins3.vehiculo_nromotor = v.nromotor" +
                " and v.tarjetapropiedad_id  = tp.id" +
                " and tp.propietario_nrodocumentoidentidad = p.nrodocumentoidentidad";

        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        resultado = (List<String>) queryTmp.getResultList();




        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
        		" select " +
        		" p.telefono " +
        		" from tmp_inspeccion as tmpins" +
                " left join comprobante as c on tmpins.placa = c.placamotor" +
                " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                " inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                " (ins.fechvencimiento > tmpins.fechavencimiento" +
                " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        List<String> resultadoTmp = (List<String>) queryTmp.getResultList();

        resultado = resultado.stream().filter(Objects::nonNull).collect(Collectors.toList());
        resultadoTmp= resultadoTmp.stream().filter(Objects::nonNull).collect(Collectors.toList());

        //comparar entre ambas listas para crear una sola
        for(String pcapiTmp : resultadoTmp)
        {
            boolean found = false;
            for(String pcapi: resultado)
            {
                if(pcapi.equals(pcapiTmp.toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.sms.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }



    public List<PropietarioCAPI> getAllRescateClientes(){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, 30);

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        List<PropietarioCAPI> resultado = new ArrayList<>();
        String hql="SELECT DISTINCT new "+PropietarioCAPI.class.getName()
                + "(pro.nrodocumentoidentidad,pro.nombres,pro.apellidos,pro.nombrerazonsocial,pro.telefono,tdoc.nombre,"
                + "pro.direccion,pro.email,pais.nombre,depa.nombre,prov.nombre,dist.nombre,"
                + "com.placaMotor,ins.fechvencimiento,cli.nrodocumentoidentidad,veh.nromotor,coin.key,'',"
                + "mar.nombre,mod.nombre,veh.aniofabricacion,pla.nombre,ins.fechconsolidado)"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.cliente cli"
                + " inner join com.conceptoInspeccion coin"
                + " inner join ins.certificados cert"
                + " inner join ins.vehiculo veh"
                + " inner join veh.tarjetapropiedad tpd"
                + " inner join tpd.propietario pro"
                + " inner join pro.tipodocumentoidentidad tdoc"
                + " inner join pro.pais pais"
                + " inner join pro.departamento depa"
                + " inner join pro.provincia prov"
                + " inner join pro.distrito dist"
                + " inner join veh.marca mar"
                + " inner join veh.modelo mod"
                + " inner join com.linea lin"
                + " inner join lin.planta pla"
                + " WHERE to_date(to_char(ins.fechvencimiento, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax";

        Query query = entityManager.createQuery(hql);
        query.setParameter("fechaMin", fechaMax);
        query.setParameter("fechaMax", fechaMax);
        resultado = query.getResultList();

        for (PropietarioCAPI reporte : resultado) {
            reporte.setId(Long.toString(new Date().getTime()));
            reporte.setFechavencimientocert(dtf.format(reporte.getFechDate()));
            reporte.setFechaultimarevision(dtf.format(reporte.getFechDateUltimaRevision()));

            reporte.setSoat(inspeccionMigradaService.getSoatByNroMotor(reporte.getIdnromotor()));
            reporte.setCliente(inspeccionMigradaService.getClienteByDocumentoident(reporte.getIdcliente()));
            if(reporte.getSoat().getFcvs()!=null){
                reporte.getSoat().setFechavencimientosoat(dtf.format(reporte.getSoat().getFcvs()));
            }
            reporte.setConcepto(inspeccionMigradaService.getConceptoByKey(reporte.getIdconcepto()));
        }


        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select " +
                        " tmpins.placa," +
                        " tmpins.fechaVencimiento," +
                        " ins.fechvencimiento ," +
                        " ins.fechconsolidado ," +
                        " tmpins.fechaultimainspeccion," +
                        " p.nrodocumentoidentidad ," +
                        " p.nombres ," +
                        " p.apellidos ," +
                        " p.telefono ," +
                        " p.nombrerazonsocial ," +
                        " p.tipodocumentoidentidad_key ," +
                        " p.direccion ," +
                        " p.email ," +
                        " p.pais_key ," +
                        " p.provincia_key ," +
                        " p.distrito_key  ," +
                        " p.departamento_key  ," +
                        " tmpins.marca," +
                        " tmpins.modelo," +
                        " tmpins.anioFabricacion," +
                        " tmpins.planta_key,"+
                        " tmpins.conceptoinspeccion_key" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento" +

                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +


                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        Query queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        //FECHA VENCIMIENTO


        Iterator<Object> itr = resultadoTmp.iterator();

        Map<String, PropietarioCAPI> lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setPlaca(String.valueOf(obj[0]));

            Date fechaVencimientoTmp = (Date)obj[1];
            Date fechaVencimientoIns = (Date)obj[2];
            Date fechaConsIns = (Date)obj[3];
            Date fechaConsTmp = (Date)obj[4];

            if(fechaVencimientoIns!=null)
            {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
                pcapi.setFechaultimarevision(dtf.format(fechaConsIns));
            }else {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoTmp));
                pcapi.setFechaultimarevision(dtf.format(fechaConsTmp));
            }

            pcapi.setNrodocumento(String.valueOf(obj[5]));
            pcapi.setNombre(String.valueOf(obj[6]));
            pcapi.setApellido(String.valueOf(obj[7]));
            pcapi.setTelefono(String.valueOf(obj[8]));
            pcapi.setRazon(String.valueOf(obj[9]));
            pcapi.setTipo(String.valueOf(obj[10]));
            pcapi.setDireccion(String.valueOf(obj[11]));
            pcapi.setEmail(String.valueOf(obj[12]));

            Pais pais = maestroService.getPaisByKey(String.valueOf(obj[13]));
            Provincia provincia = maestroService.getProvinciaByKey(String.valueOf(obj[14]));
            Distrito distrito = maestroService.getDistritoByKey(String.valueOf(obj[15]));
            Departamento departamento = maestroService.getDepartamentoByKey(String.valueOf(obj[16]));

            pcapi.setPais(pais!=null?pais.getNombre():"");
            pcapi.setProvincia(provincia!=null?provincia.getNombre():"");
            pcapi.setDistrito(distrito!=null?distrito.getNombre():"");
            pcapi.setDepartamento(departamento!=null?departamento.getNombre():"");

            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            Planta planta = maestroService.getPlantaByKey(String.valueOf(obj[20]));

            if(planta == null)
                planta = maestroService.getPlantaByKeyMtc(String.valueOf(obj[20]));

            if(planta!=null)
                pcapi.setPlanta(planta.getNombre());
            else
                pcapi.setPlanta("");

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[21]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[21]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);

            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", "", "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        List<PropietarioCAPI> resultadoFinalTmp 	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //comparar entre ambas listas para crear una sola
        for(PropietarioCAPI pcapiTmp : resultadoFinalTmp)
        {
            boolean found = false;
            for(PropietarioCAPI pcapi: resultado)
            {
                if(pcapi.getPlaca().toUpperCase().equals(pcapiTmp.getPlaca().toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }




    public List<PropietarioCAPI> getAllPorVencerClientesfindByFecha(String fecha){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, Integer.parseInt(fecha));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);

        Calendar calendarHOY = Calendar.getInstance();
        calendarHOY.setTime(new Date());
        String fechaHOY = dtfPeru.format(new Timestamp(calendarHOY.getTime().getTime()));

        List<PropietarioCAPI> resultado = new ArrayList<>();
        String hql=" select distinct (cli.nrodocumentoidentidad), cli.nombres, cli.apellidos, cli.nombrerazonsocial,cli.telefono," +
                " tdi.nombre as tdin, p.direccion,p.email, pa.nombre as pan, de.nombre as den,prov.nombre as nom,d.nombre as dnom,com.placamotor," +
                " ins3.fechvencimiento,ins3.fechconsolidado,cli.nrodocumentoidentidad as clin, ci.key,  mar.nombre as marn,mod.nombre as modn," +
                " v.aniofabricacion , pal.nombre as paln" +
                " from inspeccion as ins3 , " +
                " comprobante com," +
                " vehiculo as v, " +
                " tarjetapropiedad as tp, " +
                " persona as p," +
                " pais as pa," +
                " departamento as de," +
                " provincia as prov," +
                " distrito as d," +
                " marca as mar," +
                " modelo as mod," +
                " linea as lin," +
                " planta as pal," +
                " persona as cli," +
                " tipodocumentoidentidad tdi," +
                " conceptoinspeccion ci" +
                "" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and ins3.vehiculo_nromotor = v.nromotor" +
                " and com.inspeccion_nrodocumentoinspeccion = ins3.nrodocumentoinspeccion" +
                " and v.tarjetapropiedad_id = tp.id" +
                " and tp.propietario_nrodocumentoidentidad = p.nrodocumentoidentidad" +
                " and com.cliente_nrodocumentoidentidad = cli.nrodocumentoidentidad" +
                " and com.conceptoinspeccion_key = ci.key" +

                " and com.cliente_nrodocumentoidentidad NOT IN " +
                " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true " +
                " and empresa_nrodocumentoidentidad is not null)" +

                " and p.pais_key = pa.key" +
                " and p.departamento_key = de.key" +
                " and p.distrito_key = d.key " +
                " and p.provincia_key = prov.key" +
                " and v.marca_key = mar.key" +
                " and v.modelo_key = mod.key" +
                " and com.linea_key = lin.key" +
                " and lin.planta_key = pal.key" +
                " and p.tipodocumentoidentidad_key = tdi.key" +
                "";


        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        //creamos los objetos

        Iterator<Object> itr = resultadoTmp.iterator();

        Map<String, PropietarioCAPI> lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setNrodocumento(String.valueOf(obj[0]));

            pcapi.setNombre(String.valueOf(obj[1]));
            pcapi.setApellido(String.valueOf(obj[2]));
            pcapi.setRazon(String.valueOf(obj[3]));
            pcapi.setTelefono(String.valueOf(obj[4]));
            pcapi.setTipo(String.valueOf(obj[5]));
            pcapi.setDireccion(String.valueOf(obj[6]));
            pcapi.setEmail(String.valueOf(obj[7]));

            pcapi.setPais(String.valueOf(obj[8]));
            pcapi.setProvincia(String.valueOf(obj[9]));
            pcapi.setDistrito(String.valueOf(obj[10]));
            pcapi.setDepartamento(String.valueOf(obj[11]));

            pcapi.setPlaca(String.valueOf(obj[12]));

            Date fechaVencimientoIns = (Date)obj[13];
            Date fechaConsIns = (Date)obj[14];
            pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
            pcapi.setFechaultimarevision(dtf.format(fechaConsIns));


            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", String.valueOf(obj[15]), "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[16]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[16]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);


            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            pcapi.setPlanta(String.valueOf(obj[20]));


            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        resultado	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select " +
                        " tmpins.placa," +
                        " tmpins.fechaVencimiento," +
                        " ins.fechvencimiento ," +
                        " ins.fechconsolidado ," +
                        " tmpins.fechaultimainspeccion," +
                        " p.nrodocumentoidentidad ," +
                        " p.nombres ," +
                        " p.apellidos ," +
                        " p.telefono ," +
                        " p.nombrerazonsocial ," +
                        " p.tipodocumentoidentidad_key ," +
                        " p.direccion ," +
                        " p.email ," +
                        " p.pais_key ," +
                        " p.provincia_key ," +
                        " p.distrito_key  ," +
                        " p.departamento_key  ," +
                        " tmpins.marca," +
                        " tmpins.modelo," +
                        " tmpins.anioFabricacion," +
                        " tmpins.planta_key,"+
                        " tmpins.conceptoinspeccion_key" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento" +
                        " and c.cliente_nrodocumentoidentidad NOT IN " +
                        " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true" +
                        " and empresa_nrodocumentoidentidad is not null)" +
                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        resultadoTmp = (List<Object>) queryTmp.getResultList();

        //FECHA VENCIMIENTO


        itr = resultadoTmp.iterator();

        lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setPlaca(String.valueOf(obj[0]));

            Date fechaVencimientoTmp = (Date)obj[1];
            Date fechaVencimientoIns = (Date)obj[2];
            Date fechaConsIns = (Date)obj[3];
            Date fechaConsTmp = (Date)obj[4];

            if(fechaVencimientoIns!=null)
            {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
                pcapi.setFechaultimarevision(dtf.format(fechaConsIns));
            }else {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoTmp));
                pcapi.setFechaultimarevision(dtf.format(fechaConsTmp));
            }

            pcapi.setNrodocumento(String.valueOf(obj[5]));
            pcapi.setNombre(String.valueOf(obj[6]));
            pcapi.setApellido(String.valueOf(obj[7]));
            pcapi.setTelefono(String.valueOf(obj[8]));
            pcapi.setRazon(String.valueOf(obj[9]));
            pcapi.setTipo(String.valueOf(obj[10]));
            pcapi.setDireccion(String.valueOf(obj[11]));
            pcapi.setEmail(String.valueOf(obj[12]));

            Pais pais = maestroService.getPaisByKey(String.valueOf(obj[13]));
            Provincia provincia = maestroService.getProvinciaByKey(String.valueOf(obj[14]));
            Distrito distrito = maestroService.getDistritoByKey(String.valueOf(obj[15]));
            Departamento departamento = maestroService.getDepartamentoByKey(String.valueOf(obj[16]));

            pcapi.setPais(pais!=null?pais.getNombre():"");
            pcapi.setProvincia(provincia!=null?provincia.getNombre():"");
            pcapi.setDistrito(distrito!=null?distrito.getNombre():"");
            pcapi.setDepartamento(departamento!=null?departamento.getNombre():"");

            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            Planta planta = maestroService.getPlantaByKey(String.valueOf(obj[20]));

            if(planta == null)
                planta = maestroService.getPlantaByKeyMtc(String.valueOf(obj[20]));

            if(planta!=null)
                pcapi.setPlanta(planta.getNombre());
            else
                pcapi.setPlanta("");

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[21]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[21]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);

            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", "", "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        List<PropietarioCAPI> resultadoFinalTmp 	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //comparar entre ambas listas para crear una sola
        for(PropietarioCAPI pcapiTmp : resultadoFinalTmp)
        {
            boolean found = false;
            for(PropietarioCAPI pcapi: resultado)
            {
                if(pcapi.getPlaca().toUpperCase().equals(pcapiTmp.getPlaca().toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }


    public List<PropietarioCAPI> getAllVencidoClientesfindByFecha(String fecha){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, -Integer.parseInt(fecha));

        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaFin = dtfPeru.format(fechaMax);


        Calendar calendarHOY = Calendar.getInstance();
        calendarHOY.setTime(new Date());
        String fechaHOY = dtfPeru.format(new Timestamp(calendarHOY.getTime().getTime()));


        List<PropietarioCAPI> resultado = new ArrayList<>();
        String hql=" select distinct (cli.nrodocumentoidentidad), cli.nombres, cli.apellidos, cli.nombrerazonsocial,cli.telefono," +
                " tdi.nombre as tdin, p.direccion,p.email, pa.nombre as pan, de.nombre as den,prov.nombre as nom,d.nombre as dnom,com.placamotor," +
                " ins3.fechvencimiento,ins3.fechconsolidado,cli.nrodocumentoidentidad as clin, ci.key,  mar.nombre as marn,mod.nombre as modn," +
                " v.aniofabricacion , pal.nombre as paln" +
                " from inspeccion as ins3 , " +
                " comprobante com," +
                " vehiculo as v, " +
                " tarjetapropiedad as tp, " +
                " persona as p," +
                " pais as pa," +
                " departamento as de," +
                " provincia as prov," +
                " distrito as d," +
                " marca as mar," +
                " modelo as mod," +
                " linea as lin," +
                " planta as pal," +
                " persona as cli," +
                " tipodocumentoidentidad tdi," +
                " conceptoinspeccion ci" +
                " where ins3.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +
                " and ins3.fechvencimiento > '"+fechaFin+" 00:00:00' " +
                " and ins3.fechvencimiento < '"+fechaFin+" 23:59:59'" +
                " and ins3.vehiculo_nromotor = v.nromotor" +
                " and com.inspeccion_nrodocumentoinspeccion = ins3.nrodocumentoinspeccion" +
                " and v.tarjetapropiedad_id  = tp.id" +
                " and tp.propietario_nrodocumentoidentidad = p.nrodocumentoidentidad" +
                " and com.cliente_nrodocumentoidentidad = cli.nrodocumentoidentidad" +
                " and com.cliente_nrodocumentoidentidad NOT IN " +
                " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true " +
                " and empresa_nrodocumentoidentidad is not null)" +
                " and com.conceptoinspeccion_key = ci.key" +
                " and p.pais_key = pa.key" +
                " and p.departamento_key = de.key" +
                " and p.distrito_key = d.key " +
                " and p.provincia_key = prov.key" +
                " and v.marca_key = mar.key" +
                " and v.modelo_key = mod.key" +
                " and com.linea_key = lin.key" +
                " and lin.planta_key = pal.key" +
                " and p.tipodocumentoidentidad_key = tdi.key";


        Query queryTmp = entityManager.createNativeQuery(hql.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        //creamos los objetos

        Iterator<Object> itr = resultadoTmp.iterator();

        Map<String, PropietarioCAPI> lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setNrodocumento(String.valueOf(obj[0]));

            pcapi.setNombre(String.valueOf(obj[1]));
            pcapi.setApellido(String.valueOf(obj[2]));
            pcapi.setRazon(String.valueOf(obj[3]));
            pcapi.setTelefono(String.valueOf(obj[4]));
            pcapi.setTipo(String.valueOf(obj[5]));
            pcapi.setDireccion(String.valueOf(obj[6]));
            pcapi.setEmail(String.valueOf(obj[7]));

            pcapi.setPais(String.valueOf(obj[8]));
            pcapi.setProvincia(String.valueOf(obj[9]));
            pcapi.setDistrito(String.valueOf(obj[9]));
            pcapi.setDepartamento(String.valueOf(obj[9]));

            pcapi.setPlaca(String.valueOf(obj[12]));

            Date fechaVencimientoIns = (Date)obj[13];
            Date fechaConsIns = (Date)obj[14];
            pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
            pcapi.setFechaultimarevision(dtf.format(fechaConsIns));


            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", String.valueOf(obj[15]), "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[16]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[16]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);


            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            pcapi.setPlanta(String.valueOf(obj[20]));


            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        resultado	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select " +
                        " tmpins.placa," +
                        " tmpins.fechaVencimiento," +
                        " ins.fechvencimiento ," +
                        " ins.fechconsolidado ," +
                        " tmpins.fechaultimainspeccion," +
                        " p.nrodocumentoidentidad ," +
                        " p.nombres ," +
                        " p.apellidos ," +
                        " p.telefono ," +
                        " p.nombrerazonsocial ," +
                        " p.tipodocumentoidentidad_key ," +
                        " p.direccion ," +
                        " p.email ," +
                        " p.pais_key ," +
                        " p.provincia_key ," +
                        " p.distrito_key  ," +
                        " p.departamento_key  ," +
                        " tmpins.marca," +
                        " tmpins.modelo," +
                        " tmpins.anioFabricacion," +
                        " tmpins.planta_key,"+
                        " tmpins.conceptoinspeccion_key" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaFin+" 00:00:00'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+" 23:59:59')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento" +

                        " and c.cliente_nrodocumentoidentidad NOT IN " +
                        " (select empresa_nrodocumentoidentidad from descuento where '"+fechaHOY+"' BETWEEN fechInicio AND fechFin and estado is true" +
                        " and empresa_nrodocumentoidentidad is not null)" +



                        " and  ins.vehiculo_nromotor NOT IN (select vehiculo_nromotor from inspeccion as ins1" +
                        " where ins1.vehiculo_nromotor in (select vehiculo_nromotor from inspeccion as ins2" +
                        " where ins2.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins2.fechvencimiento < '"+fechaFin+" 23:59:59')" +
                        " and ins1.fechvencimiento > '"+fechaFin+" 23:59:59')" +



                        " and ins.fechvencimiento > '"+fechaFin+" 00:00:00'" +
                        " and ins.fechvencimiento < '"+fechaFin+" 23:59:59')");

        queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        resultadoTmp = (List<Object>) queryTmp.getResultList();

        //FECHA VENCIMIENTO


        itr = resultadoTmp.iterator();

        lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setPlaca(String.valueOf(obj[0]));

            Date fechaVencimientoTmp = (Date)obj[1];
            Date fechaVencimientoIns = (Date)obj[2];
            Date fechaConsIns = (Date)obj[3];
            Date fechaConsTmp = (Date)obj[4];

            if(fechaVencimientoIns!=null)
            {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
                pcapi.setFechaultimarevision(dtf.format(fechaConsIns));
            }else {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoTmp));
                pcapi.setFechaultimarevision(dtf.format(fechaConsTmp));
            }

            pcapi.setNrodocumento(String.valueOf(obj[5]));
            pcapi.setNombre(String.valueOf(obj[6]));
            pcapi.setApellido(String.valueOf(obj[7]));
            pcapi.setTelefono(String.valueOf(obj[8]));
            pcapi.setRazon(String.valueOf(obj[9]));
            pcapi.setTipo(String.valueOf(obj[10]));
            pcapi.setDireccion(String.valueOf(obj[11]));
            pcapi.setEmail(String.valueOf(obj[12]));

            Pais pais = maestroService.getPaisByKey(String.valueOf(obj[13]));
            Provincia provincia = maestroService.getProvinciaByKey(String.valueOf(obj[14]));
            Distrito distrito = maestroService.getDistritoByKey(String.valueOf(obj[15]));
            Departamento departamento = maestroService.getDepartamentoByKey(String.valueOf(obj[16]));

            pcapi.setPais(pais!=null?pais.getNombre():"");
            pcapi.setProvincia(provincia!=null?provincia.getNombre():"");
            pcapi.setDistrito(distrito!=null?distrito.getNombre():"");
            pcapi.setDepartamento(departamento!=null?departamento.getNombre():"");

            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            Planta planta = maestroService.getPlantaByKey(String.valueOf(obj[20]));

            if(planta == null)
                planta = maestroService.getPlantaByKeyMtc(String.valueOf(obj[20]));

            if(planta!=null)
                pcapi.setPlanta(planta.getNombre());
            else
                pcapi.setPlanta("");

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[21]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[21]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);

            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", "", "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        List<PropietarioCAPI> resultadoFinalTmp 	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //comparar entre ambas listas para crear una sola
        for(PropietarioCAPI pcapiTmp : resultadoFinalTmp)
        {
            boolean found = false;
            for(PropietarioCAPI pcapi: resultado)
            {
                if(pcapi.getPlaca().toUpperCase().equals(pcapiTmp.getPlaca().toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }

    public List<PropietarioCAPI> getAllClientesfindByFecha(String fechIni, String fechFin){
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);
        Calendar calendarIni = Calendar.getInstance();
        calendarIni.setTime(new Date());
        calendarIni.add(Calendar.DATE, -Integer.parseInt(fechIni));

        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(new Date());
        calendarFin.add(Calendar.DATE, Integer.parseInt(fechFin));

        Timestamp fechaMin = new Timestamp(calendarIni.getTime().getTime());
        Timestamp fechaMax = new Timestamp(calendarFin.getTime().getTime());

        List<PropietarioCAPI> resultado = new ArrayList<>();
        String hql="SELECT DISTINCT new "+PropietarioCAPI.class.getName()
                + "(pro.nrodocumentoidentidad,pro.nombres,pro.apellidos,pro.nombrerazonsocial,pro.telefono,tdoc.nombre,"
                + "pro.direccion,pro.email,pais.nombre,depa.nombre,prov.nombre,dist.nombre,"
                + "com.placaMotor,ins.fechvencimiento,cli.nrodocumentoidentidad,veh.nromotor,coin.key,'',"
                + "mar.nombre,mod.nombre,veh.aniofabricacion,pla.nombre,ins.fechconsolidado)"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " inner join com.cliente cli"
                + " inner join com.conceptoInspeccion coin"
                + " inner join ins.certificados cert"
                + " inner join ins.vehiculo veh"
                + " inner join veh.tarjetapropiedad tpd"
                + " inner join tpd.propietario pro"
                + " inner join pro.tipodocumentoidentidad tdoc"
                + " inner join pro.pais pais"
                + " inner join pro.departamento depa"
                + " inner join pro.provincia prov"
                + " inner join pro.distrito dist"
                + " inner join veh.marca mar"
                + " inner join veh.modelo mod"
                + " inner join com.linea lin"
                + " inner join lin.planta pla"
                + " WHERE to_date(to_char(ins.fechvencimiento, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax";

        Query query = entityManager.createQuery(hql);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        resultado = query.getResultList();

        for (PropietarioCAPI reporte : resultado) {
            reporte.setId(Long.toString(new Date().getTime()));
            reporte.setFechavencimientocert(dtf.format(reporte.getFechDate()));
            reporte.setFechaultimarevision(dtf.format(reporte.getFechDateUltimaRevision()));

            reporte.setSoat(inspeccionMigradaService.getSoatByNroMotor(reporte.getIdnromotor()));
            reporte.setCliente(inspeccionMigradaService.getClienteByDocumentoident(reporte.getIdcliente()));
            if(reporte.getSoat().getFcvs()!=null){
                reporte.getSoat().setFechavencimientosoat(dtf.format(reporte.getSoat().getFcvs()));
            }
            reporte.setConcepto(inspeccionMigradaService.getConceptoByKey(reporte.getIdconcepto()));
        }


        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
        String fechaIni = dtfPeru.format(fechaMin);
        String fechaFin = dtfPeru.format(fechaMax);

        //agregamos la lista de los temporales
        StringBuffer hqlTmp=new StringBuffer(
                " select " +
                        " tmpins.placa," +
                        " tmpins.fechaVencimiento," +
                        " ins.fechvencimiento ," +
                        " ins.fechconsolidado ," +
                        " tmpins.fechaultimainspeccion," +
                        " p.nrodocumentoidentidad ," +
                        " p.nombres ," +
                        " p.apellidos ," +
                        " p.telefono ," +
                        " p.nombrerazonsocial ," +
                        " p.tipodocumentoidentidad_key ," +
                        " p.direccion ," +
                        " p.email ," +
                        " p.pais_key ," +
                        " p.provincia_key ," +
                        " p.distrito_key  ," +
                        " p.departamento_key  ," +
                        " tmpins.marca," +
                        " tmpins.modelo," +
                        " tmpins.anioFabricacion," +
                        " tmpins.planta_key,"+
                        " tmpins.conceptoinspeccion_key" +
                        " from tmp_inspeccion as tmpins" +
                        " left join comprobante as c on tmpins.placa = c.placamotor" +
                        " left join inspeccion as ins on c.inspeccion_nrodocumentoinspeccion = ins.nrodocumentoinspeccion" +
                        "" +
                        "  inner join persona as p on tmpins.dniruc =  p.nrodocumentoidentidad" +
                        "" +
                        " where  (c.inspeccion_nrodocumentoinspeccion is null" +
                        " and  tmpins.fechavencimiento > '"+fechaIni+"'" +
                        " and tmpins.fechavencimiento < '"+fechaFin+"')  or" +
                        "" +
                        "  (ins.fechvencimiento > tmpins.fechavencimiento" +
                        " and ins.fechvencimiento > '"+fechaIni+"'" +
                        " and ins.fechvencimiento < '"+fechaFin+"')");

        Query queryTmp = entityManager.createNativeQuery(hqlTmp.toString());
        List<Object> resultadoTmp = (List<Object>) queryTmp.getResultList();

        //FECHA VENCIMIENTO


        Iterator<Object> itr = resultadoTmp.iterator();

        Map<String, PropietarioCAPI> lstPlacasTmp = new HashMap<>();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            PropietarioCAPI pcapi = new PropietarioCAPI();
            pcapi.setId(Long.toString(new Date().getTime()));
            pcapi.setPlaca(String.valueOf(obj[0]));

            Date fechaVencimientoTmp = (Date)obj[1];
            Date fechaVencimientoIns = (Date)obj[2];
            Date fechaConsIns = (Date)obj[3];
            Date fechaConsTmp = (Date)obj[4];

            if(fechaVencimientoIns!=null)
            {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoIns));
                pcapi.setFechaultimarevision(dtf.format(fechaConsIns));
            }else {
                pcapi.setFechavencimientocert(dtf.format(fechaVencimientoTmp));
                pcapi.setFechaultimarevision(dtf.format(fechaConsTmp));
            }

            pcapi.setNrodocumento(String.valueOf(obj[5]));
            pcapi.setNombre(String.valueOf(obj[6]));
            pcapi.setApellido(String.valueOf(obj[7]));
            pcapi.setTelefono(String.valueOf(obj[8]));
            pcapi.setRazon(String.valueOf(obj[9]));
            pcapi.setTipo(String.valueOf(obj[10]));
            pcapi.setDireccion(String.valueOf(obj[11]));
            pcapi.setEmail(String.valueOf(obj[12]));

            Pais pais = maestroService.getPaisByKey(String.valueOf(obj[13]));
            Provincia provincia = maestroService.getProvinciaByKey(String.valueOf(obj[14]));
            Distrito distrito = maestroService.getDistritoByKey(String.valueOf(obj[15]));
            Departamento departamento = maestroService.getDepartamentoByKey(String.valueOf(obj[16]));

            pcapi.setPais(pais!=null?pais.getNombre():"");
            pcapi.setProvincia(provincia!=null?provincia.getNombre():"");
            pcapi.setDistrito(distrito!=null?distrito.getNombre():"");
            pcapi.setDepartamento(departamento!=null?departamento.getNombre():"");

            VehiculoCAPI vcapi = new VehiculoCAPI();
            vcapi.setMarca(String.valueOf(obj[17]));
            vcapi.setModelo(String.valueOf(obj[18]));
            vcapi.setAnofabricacion(String.valueOf(obj[19]));

            pcapi.setVehiculo(vcapi);

            Planta planta = maestroService.getPlantaByKey(String.valueOf(obj[20]));

            if(planta == null)
                planta = maestroService.getPlantaByKeyMtc(String.valueOf(obj[20]));

            if(planta!=null)
                pcapi.setPlanta(planta.getNombre());
            else
                pcapi.setPlanta("");

            Conceptoinspeccion cin = maestroService.getConceptoByKey(String.valueOf(obj[21]));

            ConceptoCAPI ccapi = new ConceptoCAPI();
            ccapi.set_id(String.valueOf(obj[21]));
            ccapi.setNombre(cin!=null?cin.getNombre():"");

            pcapi.setConcepto(ccapi);

            ClienteCAPI empty = new ClienteCAPI("", "", "", "", "", "", "");
            pcapi.setCliente(empty);
            SoatCAPI emptySoat = new SoatCAPI("", null,dtf.format(new Date()));
            pcapi.setSoat(emptySoat);

            lstPlacasTmp.put(pcapi.getPlaca(), pcapi);
        }

        List<PropietarioCAPI> resultadoFinalTmp 	 = new ArrayList<PropietarioCAPI>(lstPlacasTmp.values());


        //comparar entre ambas listas para crear una sola
        for(PropietarioCAPI pcapiTmp : resultadoFinalTmp)
        {
            boolean found = false;
            for(PropietarioCAPI pcapi: resultado)
            {
                if(pcapi.getPlaca().toUpperCase().equals(pcapiTmp.getPlaca().toUpperCase()))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
                resultado.add(pcapiTmp);
        }

        trigger.launch("", "callcenter.cantidad.enviar", ""+resultado.size() , "");
        return resultado;
    }


    public void guardar(PropietarioCAPI propietarioCAPI) {

        Persona persona = personaRepository.findOneByNrodocumentoidentidad(propietarioCAPI.getNrodocumento());

        Distrito distrito = distritoRepository.findOneByNombre(propietarioCAPI.getDistrito().toUpperCase());


        persona.setTelefono(propietarioCAPI.getTelefono());
        persona.setDireccion(propietarioCAPI.getDireccion());
        persona.setEmail(propietarioCAPI.getEmail());
        persona.setDistrito(distrito);

        personaRepository.save(persona);

    }


}