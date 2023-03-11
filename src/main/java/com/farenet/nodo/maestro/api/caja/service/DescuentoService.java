package com.farenet.nodo.maestro.api.caja.service;

import com.farenet.nodo.maestro.api.caja.domain.*;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.domain.bean.*;
import com.farenet.nodo.maestro.api.caja.repository.*;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.ConceptoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.DescuentoMasivoClienteRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.DescuentoMasivoDetalleRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.*;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class DescuentoService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private DescuentoDetalleRepository descuentoDetalleRepository;
    
    @Autowired
    private DescuentoClienteRepository descuentoClienteRepository;
    
    @Autowired
    private InspeccionService inspeccionService;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private TipodescuentoRepository tipodescuentoRepository;
    
    @Autowired
    private ConceptoinspeccionRepository conceptoinspeccionRepository;
    
    @Autowired
    private FormapagoRepository formapagoRepository;
    
    @Autowired
    private TipoPagoDescuentoRepository tipoPagoDescuentoRepository;
    
    @Autowired
    private PlantaRepository plantaRepository;
    
    @Autowired
    private ComprobanteRepository comprobanteRepository;
    
    @Autowired
    private DescuentoMasivoRepository descuentoMasivoRepository;
    
    @Autowired
    private DescuentoMasivoDetalleRepository descuentoMasivoDetalleRepository;
    
    @Autowired
    private DescuentoMasivoClienteRepository descuentoMasivoClienteRepository;

	@Autowired
	private TriggerTaskFactory trigger;
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private EnvioEmail envioEmail;
	
	 @Value("${ruta.masivos}")
	private String rutaMasivos;
	
    public String getTipoDescuentoById(Long id) {
    	return  descuentoDetalleRepository.getTipoDescuentoById(id);
    }

    public List<DescuentoComprobante> getDescuentoByPlaca(String planta, String concepto, String placa)
	 {
    	Timestamp hoy = new Timestamp(new Date().getTime());
    	List<DescuentoComprobante> lstDetalle = new ArrayList<>();
    	List<DescuentoComprobante> lstresultado = new ArrayList<>();
    	//buscar por placa  dentro de descuentodetalle, que tengan el mismo concepto y la misma planta
    	String hql = "SELECT new " +DescuentoComprobante.class.getName()
    			+ "(Concat(det.id,''),des.nombre,det.tipoPagoDescuento,det.formaPago,det.montoActivacion,emp.nrodocumentoidentidad,dcli.uuid,des.tipodescuento,des.sello,des.ordenservicio,des.facturaConsolidado)"
    			+ " FROM Descuento des"
    			+ " inner join des.descuentodetalles det"
    			+ " inner join des.tipodescuento td"
    			+ " inner join det.conceptoinspeccion ci"
    			+ " inner join det.planta pl"
    			+ " left join det.descuentoclientes dcli"
    			+ " left join des.empresa emp"
    			+ " WHERE pl.key = :planta_key and ci.key = :concepto_key and dcli.placa = :placa and"
    			+ " des.estado = true and det.estado = true and dcli.estado = true and td.key <> 'promocion' and "
    			+ " :hoy BETWEEN des.fechInicio AND des.fechFin and :hoy BETWEEN det.fechInicio AND det.fechFin";
    	Query query = entityManager.createQuery(hql);
    	query.setParameter("planta_key", planta);
    	query.setParameter("concepto_key", concepto);
    	query.setParameter("placa", placa.toUpperCase());
    	query.setParameter("hoy", hoy);
    	lstDetalle = query.getResultList();
    	
    	String hqlpro = "SELECT new " +DescuentoComprobante.class.getName()
    			+ "(Concat(det.id,''),des.nombre,det.tipoPagoDescuento,det.formaPago,det.monto,emp.nrodocumentoidentidad,'',des.tipodescuento,des.sello,des.ordenservicio,des.facturaConsolidado)"
    			+ " FROM Descuento des"
    			+ " inner join des.descuentodetalles det"
    			+ " inner join des.tipodescuento td"
    			+ " inner join det.conceptoinspeccion ci"
    			+ " inner join det.planta pl"
    			+ " left join des.empresa emp"
    			+ " WHERE pl.key = :planta_key and ci.key = :concepto_key and td.key = 'promocion' and "
    			+ " des.estado = true and det.estado = true and "
    			+ " :hoy BETWEEN des.fechInicio AND des.fechFin and :hoy BETWEEN det.fechInicio AND det.fechFin";
    	Query querypro = entityManager.createQuery(hqlpro);
    	querypro.setParameter("planta_key", planta);
    	querypro.setParameter("concepto_key", concepto);
    	querypro.setParameter("hoy", hoy);
    	lstresultado.addAll(querypro.getResultList());
    	
    	String hql2 = "SELECT new " +DescuentoComprobante.class.getName()
    			+"(Concat(dmdd.id,''),dm.nombre,dmdd.tipoPagoDescuento,dmdd.formaPago,dmdd.monto,emp.nrodocumentoidentidad,'',dm.tipodescuento,dm.sello)"
    			+ " FROM DescuentoMasivo dm"
    			+ " inner join dm.descuentomasivodetalles dmdd"
    			+ " inner join dmdd.conceptoinspeccion ci"
    			+ " inner join dmdd.planta pl"
    			+ " inner join dm.tipodescuento td"
    			+ " left join dm.empresa emp"
    			+ " WHERE pl.key = :planta_key and ci.key = :concepto_key and td.key = 'promocion' and "  
    			+ " dm.estado = true and dmdd.estado = true and "  
    			+ " :hoy BETWEEN dm.fechInicio AND dm.fechFin and :hoy BETWEEN dmdd.fechInicio AND dmdd.fechFin";
    	Query query2 = entityManager.createQuery(hql2);
    	query2.setParameter("planta_key", planta);
    	query2.setParameter("concepto_key", concepto);
    	query2.setParameter("hoy", hoy);
    	lstresultado.addAll(query2.getResultList());
    	
    	String hql3 = "SELECT new " +DescuentoComprobante.class.getName()
    			+"(Concat(dmdd.id,''),dm.nombre,dmdd.tipoPagoDescuento,dmdd.formaPago,dmdd.monto,emp.nrodocumentoidentidad,'',dm.tipodescuento,dm.sello)"
    			+ " FROM DescuentoMasivo dm"
    			+ " inner join dm.descuentomasivodetalles dmdd"
    			+ " left join dm.descuentomasivoclientes dmdc"
    			+ " inner join dmdd.conceptoinspeccion ci"
    			+ " inner join dmdd.planta pl"
    			+ " inner join dm.tipodescuento td"
    			+ " left join dm.empresa emp"
				+ " WHERE pl.key = :planta_key and ci.key = :concepto_key and UPPER(dmdc.placa) = UPPER(:placa) and"  
    			+ " dm.estado = true and dmdd.estado = true and dmdc.estado = true and td.key <> 'promocion' and "  
    			+ " :hoy BETWEEN dm.fechInicio AND dm.fechFin and :hoy BETWEEN dmdd.fechInicio AND dmdd.fechFin";
    	Query query3 = entityManager.createQuery(hql3);
    	query3.setParameter("planta_key", planta);
    	query3.setParameter("placa", placa);
    	query3.setParameter("concepto_key", concepto);
    	query3.setParameter("hoy", hoy);
    	lstDetalle.addAll(query3.getResultList());
    	
    	for(DescuentoComprobante bean : lstDetalle) {
    		DescuentoDetalle detalle = getDescuentoDetalleById(Long.parseLong(bean.getIdDescuentoDetalle()));
    		Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(new Date());
			calendar1.add(Calendar.YEAR, -1);
			if(placa != null) {
				List<Inspeccion> inspeccions =inspeccionService.getAllByfechasPlaca(new Date(), calendar1.getTime(), placa); 
	    		if(inspeccions == null || inspeccions.size()==0) {
	    			if(detalle != null) {
	    				bean.setMonto(detalle.getMonto());
	    			}
	    		}
			}
    		boolean pasa = true ;
    		if(detalle != null) {
    			for(DescuentoCliente cliente : detalle.getDescuentoclientes()) {
        			if(cliente.getPlaca() != null) {
        				if(cliente.getPlaca().equals(placa)) {
        					Calendar calendar = Calendar.getInstance();
        					calendar.setTime(cliente.getFechInicio());
        					calendar.add(Calendar.DATE, cliente.getDiasEmpieza().intValue());
        					calendar.set(Calendar.HOUR, 0);
        					/*if(new Date().before(calendar.getTime())){
        						pasa = false;
        						break;
        					}*/
        				}
                	}
        		}
    		}
    		if(pasa) {
    			lstresultado.add(bean);
    		}
    	}
    	return lstresultado;
	 }
    
    @Transactional
    public void updateEstadoDescuentoMasivo(Boolean estado, Long id) {
    	String hqlSum = "update DescuentoMasivo "
				+ "set estado = :estado WHERE id = :id ";
		Query querySum = entityManager.createQuery(hqlSum);
		querySum.setParameter("estado", estado);
		querySum.setParameter("id", id);
		querySum.executeUpdate();
    }
    
    public List<DescuentoComprobante> getDescuentoByRucOrID(String planta, String concepto, String rucOrId)
	 {
    	Timestamp hoy = new Timestamp(new Date().getTime());
    	
    	SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
    	
    	List<Object> result = new ArrayList<>();
    	
    	List<DescuentoComprobante > lstResultado = new ArrayList<>();
    	
    	StringBuffer hql = new StringBuffer("select DISTINCT dd.id, des.nombre, dd.tipopagodescuento_key, dd.formapago_key,dd.monto,des.empresa_nrodocumentoidentidad,'' as uuid, des.tipodescuento_key,des.sello,des.ordenservicio,des.facturaconsolidado")
    			.append(" from descuentodetalle as dd")
    			.append(" inner join descuento as des on des.id = dd.descuento_id")
    			.append(" inner join conceptoinspeccion as ci on ci.key = dd.conceptoinspeccion_key")
    			.append(" left join persona as per on des.empresa_nrodocumentoidentidad = per.nrodocumentoidentidad")
    			.append(" inner join planta as pl on pl.key = dd.planta_key")
    			.append(" WHERE pl.key = '"+planta+"' and ci.key = '"+concepto+"' and des.estado = true and dd.estado = true")
    			.append(" and '"+dtf.format(UIUtils.initDay())+"' BETWEEN des.fechInicio AND des.fechFin and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dd.fechInicio AND dd.fechFin")
    			.append(" and des.empresa_nrodocumentoidentidad = '"+rucOrId+"' ");
    	Query query = entityManager.createNativeQuery(hql.toString());
    	result = (List<Object>) query.getResultList();  
    	

    	StringBuffer hql1 = new StringBuffer("select DISTINCT dd.id, des.nombre, dd.tipopagodescuento_key, dd.formapago_key,dd.monto,des.empresa_nrodocumentoidentidad,dc.uuid , des.tipodescuento_key,des.sello,des.ordenservicio,des.facturaconsolidado "
    			+ "from descuentocliente as dc "
    			+ "inner join descuentodetalle_descuentocliente as dddc on dc.id = dddc.descuentoclientes_id "
    			+ "inner join descuentodetalle as dd on dddc.descuentodetalle_id = dd.id "
    			+ "inner join descuento as des on des.id = dd.descuento_id "
    			+ "inner join conceptoinspeccion as ci on ci.key = dd.conceptoinspeccion_key "
    			+ "left join persona as per on des.empresa_nrodocumentoidentidad = per.nrodocumentoidentidad "
    			+ "inner join planta as pl on pl.key = dd.planta_key "
    			+ "WHERE pl.key = '"+planta+"' and ci.key = '"+concepto+"' and des.estado = true and dd.estado = true and '"+dtf.format(UIUtils.initDay())+"' BETWEEN des.fechInicio AND des.fechFin "
    			+ "and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dd.fechInicio AND dd.fechFin and dc.uuid = '"+rucOrId+"'"); 

    	Query query2 = entityManager.createNativeQuery(hql1.toString());
    	result.addAll((List<Object>) query2.getResultList());
    	
    	StringBuffer hql2 = new StringBuffer("select distinct dd.id, dm.nombre, dd.tipopagodescuento_key, dd.formapago_key,dd.monto,dm.empresa_nrodocumentoidentidad,dc.uuid , dm.tipodescuento_key ,dm.sello,null,false")
    			.append(" from descuentomasivocliente as dc")
    			.append(" inner join descuentomasivo as dm on dc.descuentomasivo_id = dm.id")
    			.append(" inner join descuentomasivodetalle as dd on dd.descuentomasivo_id = dm.id ")
    			.append(" inner join conceptoinspeccion as ci on ci.key = dd.conceptoinspeccion_key")
    			.append(" inner join planta as pl on pl.key = dd.planta_key")
    			.append(" where pl.key = '"+planta+"' and ci.key = '"+concepto+"' and dd.estado = true and dc.estado = true and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dm.fechInicio AND dm.fechFin " ) 
    			.append("and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dd.fechInicio AND dd.fechFin and dm.empresa_nrodocumentoidentidad = '"+rucOrId+"'");
    	Query query3 = entityManager.createNativeQuery(hql2.toString());
    	result.addAll((List<Object>) query3.getResultList());
    	
    	StringBuffer hql3 = new StringBuffer("select distinct dd.id, dm.nombre, dd.tipopagodescuento_key, dd.formapago_key,dd.monto,dm.empresa_nrodocumentoidentidad,dc.uuid , dm.tipodescuento_key,dm.sello,null,false")
    			.append(" from descuentomasivocliente as dc")
    			.append(" inner join descuentomasivo as dm on dc.descuentomasivo_id = dm.id")
    			.append(" inner join descuentomasivodetalle as dd on dd.descuentomasivo_id = dm.id ")
    			.append(" inner join conceptoinspeccion as ci on ci.key = dd.conceptoinspeccion_key")
    			.append(" inner join planta as pl on pl.key = dd.planta_key")
    			.append(" where pl.key = '"+planta+"' and ci.key = '"+concepto+"' and dd.estado = true and dc.estado = true and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dm.fechInicio AND dm.fechFin " ) 
    			.append("and '"+dtf.format(UIUtils.initDay())+"' BETWEEN dd.fechInicio AND dd.fechFin and dc.uuid = '"+rucOrId+"'");
    	Query query4 = entityManager.createNativeQuery(hql3.toString());
    	result.addAll((List<Object>) query4.getResultList());
    	
    	Iterator<Object> itr = result.iterator(); 
        while(itr.hasNext()){
        	DescuentoComprobante bean = new DescuentoComprobante();
        	Object[] obj = (Object[]) itr.next(); 
        	bean.setIdDescuentoDetalle(Long.valueOf(obj[0].toString()).toString());
        	bean.setNombreDescuento(String.valueOf(obj[1]));
        	bean.setTipoPagoDescuento(tipoPagoDescuentoRepository.findOneByKey(String.valueOf(obj[2])));
        	bean.setFormaPago(formapagoRepository.findOneByKey(String.valueOf(obj[3])));
        	bean.setMonto(Double.valueOf(obj[4].toString()));
        	bean.setRuc(String.valueOf(obj[5]));
        	bean.setUuid(String.valueOf(obj[6]));
        	bean.setTipodescuento(tipodescuentoRepository.findOneByKey(String.valueOf(obj[7])));
        	bean.setSello(Boolean.valueOf(obj[8].toString()));
        	bean.setOrdenservicio(String.valueOf(obj[9]));
        	bean.setFacturaConsolidado(Boolean.valueOf(obj[10]!= null ? obj[10].toString() : "false"));
        	
        	lstResultado.add(bean);
        }
        
    	return lstResultado;
	 }
    
    public List<DescuentoCliente> getAllByDetalle(Long iddetalle){
    	StringBuffer hql = new StringBuffer("select placa, uuid from descuentocliente where descuentodetalle_id = " + iddetalle);
    	Query query = entityManager.createNativeQuery(hql.toString());
    	List<Object> result = query.getResultList();
    	List<DescuentoCliente> lstResultado = new ArrayList<>();
    	Iterator<Object> itr = result.iterator();
    	while (itr.hasNext()) {
			DescuentoCliente cliente = new DescuentoCliente();
			Object[] obj = (Object[]) itr.next();
			cliente.setDiasEmpieza(10L);
			cliente.setFechInicio(UIUtils.initDay());
			cliente.setPlaca(String.valueOf(obj[0] != null ? obj[0]:""));
			cliente.setUuid(String.valueOf(obj[1] != null ? obj[1]:""));
			lstResultado.add(cliente);
		}
    	return lstResultado;
    }
    
    public Descuento getOneByEmpresa(Persona empresa) {
    	return descuentoRepository.getOneByEmpresa(empresa);
    }
    
    public ResultPageBean<DescuentoBean> getAllDescuentoBean(PageBean pageBean){
    	return new ResultPageBean<DescuentoBean>(
    			descuentoRepository.getAllDescuentoBean(pageBean.convertToPage()));
    }
    
    public List<String> getNombresByTipoDescuento(String tipodescuento){
    	String hql = "select nombre "
    			+ "From Descuento "
    			+ "where tipodescuento.key = :tipodescuento";
    	Query query = entityManager.createQuery(hql);
    	query.setParameter("tipodescuento", tipodescuento);
    	return query.getResultList();
    }
    
    
    
    public String getTipoContadoByDescuentoDetalleId(Long id) {
    	String hql1 = "SELECT td.nombre"
    			+ " FROM DescuentoDetalle dd"
    			+ " inner join dd.descuento de"
    			+ " inner join de.tipodescuento td"
    			+ " where dd.id = :iddetalle";
    	Query query = entityManager.createQuery(hql1);
    	query.setParameter("iddetalle", id);
    	return query.getSingleResult().toString();
    }
    
    @Transactional
    public void deleteDescuentoDetalleById(Long id) {
    	try {
    		String hqldet = "DELETE FROM DescuentoDetalle dd WHERE dd.id = :id";
            Query query1 = entityManager.createQuery(hqldet);
            query1.setParameter("id", id);
            query1.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    public ResultPageBean<DescuentoBean> getAllDescuentoBeanByField(String field,PageBean pageBean) throws UnsupportedEncodingException{
    	Map<String, String> mapField = QueryUtil.splitQuery(field);
        String nombre = mapField.get("field");
        String planta = mapField.get("planta");
        String tipodescuento = mapField.get("tipodescuento");
        Boolean activos = Boolean.parseBoolean(mapField.get("activos"));
        
        String Querycount = "SELECT count(des) FROM Descuento des";
        String Query = "SELECT new "+DescuentoBean.class.getName()
        		+ "(des.id,des.nombre,td.nombre,des.limiteMontoMaximo,emp.nombrerazonsocial,des.fechInicio,des.fechFin,des.estado)"
        		+ " FROM Descuento des";
        
        String join = " inner join des.tipodescuento td  left join des.empresa emp";
        String where = " WHERE 1 = 1 ";
        
        if(nombre != null && !nombre.equals("")) {
        	if(nombre.startsWith("1") || nombre.startsWith("2")) {
        		where += " and emp.nrodocumentoidentidad like :nombre ";
        	}else {
        		where += " and UPPER(des.nombre) like UPPER(:nombre) ";
        	}
        }
        
        if(planta != null && !planta.equals("")) {
        	join += " inner join des.descuentodetalles ddt"
        			+ " inner join ddt.planta pl ";
        	where += " and pl.key = :planta "; 
        }
        
        if(tipodescuento != null && !tipodescuento.equals("")) {
        	where += " and td.key = :tipodescuento ";
        }
        
        if(activos) {
        	where += " and des.estado = :activos";
        }
        
        Querycount += join + " " + where;
        Query += join + " " + where;
        
        TypedQuery<Long> typedQueryCount = entityManager.createQuery(Querycount, Long.class);
        TypedQuery<DescuentoBean> typedQuery = entityManager.createQuery(Query, DescuentoBean.class);
        
        if(nombre != null && !nombre.equals("")) {
        	typedQueryCount.setParameter("nombre", "%"+nombre+"%");
        	typedQuery.setParameter("nombre", "%"+nombre+"%");
        }
        
        if(planta != null && !planta.equals("")) {
        	typedQueryCount.setParameter("planta", planta);
        	typedQuery.setParameter("planta", planta);
        }
        
        if(tipodescuento != null && !tipodescuento.equals("")) {
        	typedQueryCount.setParameter("tipodescuento", tipodescuento);
        	typedQuery.setParameter("tipodescuento", tipodescuento);
        }
        
        if(activos) {
        	typedQueryCount.setParameter("activos", activos);
        	typedQuery.setParameter("activos", activos);
        }
        
        Long numberRows = typedQueryCount.getSingleResult();

		List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
				.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

		double before = (double) numberRows / pageBean.totalFilasPorPagina;
		ResultPageBean<DescuentoBean> result = new ResultPageBean<DescuentoBean>((int) Math.ceil(before), numberRows,
				lstResult);

		return result;
        
    }
    
    public DescuentoDetalle getDescuentoDetalleById (Long idDescuentoDetalle) 
    {
    	return descuentoDetalleRepository.getOneById(idDescuentoDetalle);
    }
    
    public Descuento getDescuentoById(Long id) {
    	return descuentoRepository.getOneById(id);
    }
    
    @Transactional
    public void saveDescuento (Descuento descuento) throws Exception{
    	
    	descuento.getDescuentodetalles().stream().sorted((d1, d2) ->d1.getFechFin().compareTo(d2.getFechFin())).collect(Collectors.toList());
    	
    	List<DescuentoCliente> clientes = new ArrayList<>();
    	for(DescuentoDetalle detalle : descuento.getDescuentodetalles()) {
    		if(!detalle.getDescuentoclientes().isEmpty()) {
    			clientes = new ArrayList<>();
    			clientes = detalle.getDescuentoclientes();
    		}
    		List<DescuentoCliente> clientesave = new ArrayList<>();
    		for(DescuentoCliente cliente : clientes) {

    			DescuentoCliente newCliente = new DescuentoCliente(null, cliente.getPlaca(),
    					cliente.getUuid(), cliente.getDiasEmpieza(), cliente.getMaxinspecciones(),
    					cliente.getFechInicio(), cliente.getEstado(), cliente.getInspecciones());
    			clientesave.add(newCliente);

    		}
    		detalle.setUsuariocreacion(maestroService.getOneByUsername(Util.getUserNameContext()));
    		detalle.setDescuentoclientes(clientesave);
    		detalle.setDescuento(descuento);
    	}
    	
    	new Thread(new DiffDescuentoLog("descuentos.crear",null, descuento,Util.getUserNameContext(), trigger)).start();;


		descuentoRepository.save(descuento);
    }
    
    @Transactional
    public void updateDescuentoDetalles(DescuentoEditedBean detalleBean) throws Exception {
    	Descuento descuento = getDescuentoById(detalleBean.getId());
    	
    	if(descuento == null) {
    		throw new Exception("Id del descuento no existe"); 
    	}
    	List<DescuentoDetalle> lstDetalle = new ArrayList<>();
    	List<DescuentoCliente> clientes = new ArrayList<>();
    	List<DescuentoCliente> lstClientes = descuentoClienteRepository.getAllByDescuentoDetalle(detalleBean.getDescuentodetalles().get(0).getId());
    	
    	for(DescuentoDetalle detalle : detalleBean.getDescuentodetalles()) {
    		if(!detalle.getDescuentoclientes().isEmpty()) {
    			clientes = detalle.getDescuentoclientes();

    			clientes.addAll(lstClientes);

    		}
    		
    		List<DescuentoCliente> clientesave = new ArrayList<>();
    		for(DescuentoCliente cliente : clientes) {
    			if(cliente.getId() != null) {
    				cliente = new DescuentoCliente(null, cliente.getPlaca(),
        					cliente.getUuid(), cliente.getDiasEmpieza(), cliente.getMaxinspecciones(),
        					cliente.getFechInicio(), cliente.getEstado(), cliente.getInspecciones());
    			}
    			clientesave.add(cliente);
    		}
    		
    		detalle.setUsuariocreacion(maestroService.getOneByUsername(Util.getUserNameContext()));
    		detalle.setDescuentoclientes(clientes);
    		detalle.setDescuento(descuento);
    		lstDetalle.add(detalle);
    	}
    	
    	descuento.setEstado(detalleBean.getEstado());
    	descuento.setDescuentodetalles(lstDetalle);

		new Thread(new DiffDescuentoLog("descuentos.actualizar",descuentoRepository.getOne(detalleBean.getId()), descuento,Util.getUserNameContext(), trigger)).start();;

		descuentoRepository.save(descuento);

	}
    
    @Transactional
    public void actualizarDatosDescuento(ActualizaDescuentoBean bean) {
    	try {
			Ejecutivo usuario = null;
			if (bean.getEjecutivo() != null && !bean.getEjecutivo().isEmpty())
				usuario = maestroService.getEjecutivoByDni(bean.getEjecutivo());
        	String hql = "update Descuento set porRuc = :ruc, porPlaca = :placa, ejecutivo = :ejecutivo , estado = :estado , ordenservicio = :orden, diascredito = :nrodias, nrocuotas = :nrocuotas, descripcion= :descripcion, tipo_convenio_key =: key where id = :id";
        	Query query = entityManager.createQuery(hql);
			query.setParameter("ejecutivo", usuario);
			query.setParameter("estado", bean.getEstado());
			query.setParameter("orden", bean.getOrden());
			query.setParameter("id", bean.getId());
			query.setParameter("ruc", bean.getRuc());
			query.setParameter("placa", bean.getPlaca());
			query.setParameter("nrodias", bean.getNroDias());
			query.setParameter("nrocuotas", bean.getNroCuotas());
			query.setParameter("descripcion",bean.getDescripcion());
			query.setParameter("key",bean.getKey());
        	query.executeUpdate();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public List<String> getPlacasByIdDescuentoDetalle(Long id){
    	List<String> lstPlacas = new ArrayList<>();
    	String hql = "select distinct dc.placa, dc.uuid from descuentodetalle as dd " + 
    			"inner join descuentodetalle_descuentocliente as dddc on dd.id = dddc.descuentodetalle_id " + 
    			"inner join descuentocliente as dc on dc.id = dddc.descuentoclientes_id " + 
    			"where dd.id = "+id;
    	Query query = entityManager.createNativeQuery(hql);
    	query.setFirstResult(0);
    	query.setMaxResults(10);
    	List<Object> result = (List<Object>) query.getResultList();  
    	Iterator<Object> itr = result.iterator(); 
    	while(itr.hasNext()){
        	Object[] obj = (Object[]) itr.next(); 
    		lstPlacas.add(obj[0] != null ? obj[0].toString():obj[1].toString());
    	}
    	return lstPlacas;
    }
    
    @Transactional
    public void migrarCotizacion(List<CotizacionMigradorBean> migradoresBeans) throws Exception  {
    	List<Planta> plantas = plantaRepository.findAll();
    	List<Descuento> descuentos = new ArrayList<>();
    	
    	Formapago contado = formapagoRepository.findOneByKey("contado");
    	Formapago credito = formapagoRepository.findOneByKey("credito");
    	TipoPagoDescuento Flat = tipoPagoDescuentoRepository.findOneByKey("FLA");
    	
    	for(CotizacionMigradorBean bean : migradoresBeans) {
    		Descuento descuento = new Descuento();
    		List<DescuentoDetalle> detalles = new ArrayList<>();
    		
    		Calendar calendar = Calendar.getInstance();
    		calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
    		Timestamp fechaMin = new Timestamp(new Date().getTime());
            Timestamp fechaMax = new Timestamp(calendar.getTime().getTime());
    		Persona empresa = personaService.getOneByNroDocumento(bean.getNroruc());
    		
    		descuento.setEmpresa(empresa);
    		descuento.setNombre(bean.getNombre());
    		descuento.setLimiteMontoMaximo(bean.getCreditooMaximo());
    		descuento.setTipodescuento(tipodescuentoRepository.findOneByKey("cotizacion"));
    		descuento.setFechInicio(fechaMin);
    		descuento.setFechFin(fechaMax);
    		
    		Descuento descuentoExiste = getOneByEmpresa(empresa); 
    		
    		if(descuentoExiste != null) {
    			descuento.setId(descuentoExiste.getId());
    			for(DescuentoDetalle entity : descuentoExiste.getDescuentodetalles()) {
    				Long count = comprobanteRepository.findCountComprobanteDescuentoId(entity.getId().toString());
    				if(count.intValue() > 0) {
    					entity.setEstado(false);
    					descuentoDetalleRepository.save(entity);
    				}else {
    					deleteDescuentoDetalleById(entity.getId());
    				}
    			}
    		}
    		
    		for(ConceptoPrecioBean precioBean : bean.getConceptoPrecioBeans()) {
    			for(String planta : bean.getPlantas()) {
    				if(planta.equals("0")) {
    					for(Planta planta2 : plantas) {
    						DescuentoDetalle detalle = new DescuentoDetalle();
    						detalle.setPlanta(planta2);
    						detalle.setConceptoinspeccion(conceptoinspeccionRepository.findOneByKey(precioBean.getKey()));
    		    			detalle.setFechInicio(fechaMin);
    		    			detalle.setFechFin(fechaMax);
    		    			detalle.setTipoPagoDescuento(Flat);
    		    			detalle.setDescuento(descuento);
    		    			if(precioBean.getPrecioContado() != null && precioBean.getPrecioContado() > 0) {
    		    				detalle.setMonto(precioBean.getPrecioContado());
    		    				detalle.setFormaPago(contado);
    		    			}
    		    			if(precioBean.getPrecioCredito() != null && precioBean.getPrecioCredito() > 0) {
    		    				detalle.setMonto(precioBean.getPrecioCredito());
    		    				detalle.setFormaPago(credito);
    		    			}
    						detalles.add(detalle);
    					}
    				}else {
    					DescuentoDetalle detalle = new DescuentoDetalle();
    					detalle.setPlanta(plantaRepository.findOneByKey(planta));
    					detalle.setConceptoinspeccion(conceptoinspeccionRepository.findOneByKey(precioBean.getKey()));
    	    			detalle.setFechInicio(fechaMin);
    	    			detalle.setFechFin(fechaMax);
    	    			detalle.setTipoPagoDescuento(Flat);
    	    			detalle.setDescuento(descuento);
    	    			if(precioBean.getPrecioContado() != null && precioBean.getPrecioContado() > 0) {
    	    				detalle.setMonto(precioBean.getPrecioContado());
    	    				detalle.setFormaPago(contado);
    	    			}
    	    			if(precioBean.getPrecioCredito() != null && precioBean.getPrecioCredito() > 0) {
    	    				detalle.setMonto(precioBean.getPrecioCredito());
    	    				detalle.setFormaPago(credito);
    	    			}
						detalles.add(detalle);
    				}
    			}
    		}
    		descuento.setDescuentodetalles(detalles);
    		descuentos.add(descuento);
    	}
    	
    	for(Descuento descuento : descuentos) {
    		saveDescuento(descuento);
    	}
    }
    
    @Transactional
    public void updateDescuentoCliente(DescuentoClientesBean clienteBean) throws Exception {
    	
    	DescuentoDetalle detallesActual = getDescuentoDetalleById(clienteBean.getId()) ;
    	
    	if(detallesActual == null) {
    		throw new Exception("Id del descuento no existe"); 
    	}
    	
    	List<DescuentoCliente> lstNuevos = new ArrayList<>();
    	Boolean isnuevo = true;
    	
    	for(DescuentoCliente descli : detallesActual.getDescuentoclientes()) {
    		descli.setEstado(false);
    		descuentoClienteRepository.save(descli);
    	}
    	
    	for(DescuentoCliente cliente : clienteBean.getDescuentoclientes()) {
    		
    		for(DescuentoCliente actual : detallesActual.getDescuentoclientes()) {
    			if(cliente.getPlaca() != null && !cliente.getPlaca().isEmpty()) {
    				if(cliente.getPlaca().equals(actual.getPlaca())) {
    					actual.setEstado(true);
    		    		descuentoClienteRepository.save(actual);
        				isnuevo = false;
        				break;
        			}
    			}
    			if(cliente.getUuid() != null && !cliente.getUuid().isEmpty()) {
    				if(cliente.getUuid().equals(actual.getUuid())) {
    					actual.setEstado(true);
    		    		descuentoClienteRepository.save(actual);
        				isnuevo = false;
        				break;
        			}
    			}
    			
    		}
    		
    		if(isnuevo) {
    			lstNuevos.add(cliente);
    		}
    	}

		new Thread(new ObjectToLog("descuentos.clientes",lstNuevos,Util.getUserNameContext(), trigger)).start();;

		descuentoClienteRepository.saveAll(lstNuevos);

    }
    
    /**
     * TODO DESCUENTO MASIVOS
     */
    
    public ResultPageBean<DescuentoBean> getAllDescuentoMasivosBean(PageBean pageBean){
    	return new ResultPageBean<DescuentoBean>(
    			descuentoMasivoRepository.getAllDescuentoMasivoBean(pageBean.convertToPage()));
    } 
    
    public ResultPageBean<DescuentoBean> getAllDescuentoMasivoBeanByField(String field,PageBean pageBean) throws UnsupportedEncodingException{
    	Map<String, String> mapField = QueryUtil.splitQuery(field);
        String nombre = mapField.get("field");
        String planta = mapField.get("planta");
        String tipodescuento = mapField.get("tipodescuento");
        Boolean activos = Boolean.parseBoolean(mapField.get("activos"));
        
        String Querycount = "SELECT count(dm) FROM DescuentoMasivo dm";
        String Query = "SELECT new "+DescuentoBean.class.getName()
        		+"(dm.id,dm.nombre,td.nombre,dm.limiteMontoMaximo,emp.nombrerazonsocial,dm.fechInicio,dm.fechFin,dm.estado)"
        		+ " FROM DescuentoMasivo dm";
        
        String join = " inner join dm.tipodescuento td  left join dm.empresa emp";
        String where = " WHERE 1 = 1 ";
        
        if(nombre != null && !nombre.equals("")) {
        	if(nombre.startsWith("1") || nombre.startsWith("2")) {
        		where += " and emp.nrodocumentoidentidad like :nombre ";
        	}else {
        		where += " and UPPER(dm.nombre) like UPPER(:nombre) ";
        	}
        }
        

        if(planta != null && !planta.equals("")) {
        	join += " inner join dm.descuentodetalles ddt"
        			+ " inner join ddt.planta pl ";
        	where += " and pl.key = :planta "; 
        }
        
        if(tipodescuento != null && !tipodescuento.equals("")) {
        	where += " and td.key = :tipodescuento ";
        }
        
        if(activos) {
        	where += " and dm.estado = :activos";
        }
        
        Querycount += join + " " + where;
        Query += join + " " + where;
        
        TypedQuery<Long> typedQueryCount = entityManager.createQuery(Querycount, Long.class);
        TypedQuery<DescuentoBean> typedQuery = entityManager.createQuery(Query, DescuentoBean.class);
        
        if(nombre != null && !nombre.equals("")) {
        	typedQueryCount.setParameter("nombre", "%"+nombre+"%");
        	typedQuery.setParameter("nombre", "%"+nombre+"%");
        }
        
        if(planta != null && !planta.equals("")) {
        	typedQueryCount.setParameter("planta", planta);
        	typedQuery.setParameter("planta", planta);
        }
        
        if(tipodescuento != null && !tipodescuento.equals("")) {
        	typedQueryCount.setParameter("tipodescuento", tipodescuento);
        	typedQuery.setParameter("tipodescuento", tipodescuento);
        }
        
        if(activos) {
        	typedQueryCount.setParameter("activos", activos);
        	typedQuery.setParameter("activos", activos);
        }
        
        Long numberRows = typedQueryCount.getSingleResult();

		List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
				.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

		double before = (double) numberRows / pageBean.totalFilasPorPagina;
		ResultPageBean<DescuentoBean> result = new ResultPageBean<DescuentoBean>((int) Math.ceil(before), numberRows,
				lstResult);

		return result;

    }
    
    public DescuentoMasivo getDescuentoMasivoById(Long id) {
    	return descuentoMasivoRepository.getOneById(id);
    }
    
    @Transactional
    public void undateDescuentoMasivoDetalles(DescuentoMasivoDetalleEditBean detalleEditBean) throws Exception {
    	DescuentoMasivo descuentoMasivo = getDescuentoMasivoById(detalleEditBean.getId());
    	if(descuentoMasivo == null) {
    		throw new Exception("Id del descuento no existe");
    	}
    	
    	List<DescuentoMasivoDetalle> descuentoMasivoDetalles = detalleEditBean.getMasivoDetalles();
    	descuentoMasivoDetalles.forEach(bean -> bean.setDescuentoMasivo(descuentoMasivo));


		new Thread(new DiffDescuentoMasivoLog("descuentos.masivo.actualizar",getDescuentoMasivoById(detalleEditBean.getId()), descuentoMasivo,Util.getUserNameContext(), trigger)).start();;


		updateEstadoDescuentoMasivo(detalleEditBean.getEstado(), detalleEditBean.getId());
    	
    	descuentoMasivoDetalleRepository.saveAll(descuentoMasivoDetalles);


	 }
    
    @Transactional
    public void crearDescuentoMasivo (DescuentoMasivo descuentoMasivo) {
    	descuentoMasivo.getDescuentomasivodetalles().forEach(bean -> bean.setDescuentoMasivo(descuentoMasivo));
    	descuentoMasivo.getDescuentomasivoclientes().forEach(bean -> bean.setDescuentoMasivo(descuentoMasivo));

		new Thread(new DiffDescuentoMasivoLog("descuentos.masivo.actualizar",null, descuentoMasivo,Util.getUserNameContext(), trigger)).start();;

		descuentoMasivoRepository.save(descuentoMasivo);
    }
    
    @Transactional
    public void CrearClientesMasivos (DescuentoMasivoClientesEditBean clientesEditBean) throws Exception{
    	DescuentoMasivo descuentoMasivo = getDescuentoMasivoById(clientesEditBean.getId());
    	if(descuentoMasivo == null) {
    		throw new Exception("Id del descuento no existe");
    	}
    	List<DescuentoMasivoCliente> clientes = clientesEditBean.getDescuentoMasivoClientes();
    	clientes.forEach(bean -> bean.setDescuentoMasivo(descuentoMasivo));

		new Thread(new ObjectToLog("descuentos.masivo.clientes",clientes,Util.getUserNameContext(), trigger)).start();
		
		descuentoMasivoClienteRepository.saveAll(clientes);
		
		String hql = "delete from descuentomasivocliente where descuentomasivo_id = '"+descuentoMasivo.getId()+"'";
    	Query query = entityManager.createNativeQuery(hql);
    	query.executeUpdate();
		
		envioEmail.execute("Se registraron " + clientesEditBean.getDescuentoMasivoClientes().size() + " con exito", clientesEditBean.getCorreo());
    }
    
    public void cargarExcel(String nombreexcel,String descuentoid, Boolean isPlaccas) throws Exception {
    	DescuentoMasivo descuentoMasivo = getDescuentoMasivoById(Long.valueOf(descuentoid));
    	descuentoMasivoClienteRepository.deleteAll(descuentoMasivo.getDescuentomasivoclientes());
    	if(descuentoMasivo == null) {
    		throw new Exception("Id del descuento no existe");
    	}
    	String path = rutaMasivos + "/" + nombreexcel;
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			Session session = (Session) entityManager.getDelegate(); 
			session.setCacheMode(CacheMode.IGNORE);
			inputStream = new FileInputStream(path);
			sc = new Scanner(inputStream, "UTF-8");
			int count = 0;
			while (sc.hasNextLine()) {
				count++;
				String line = sc.nextLine();
				if(line != null && line.isEmpty()) {
					continue;
				}
				DescuentoMasivoCliente masivoCliente = new DescuentoMasivoCliente();
				masivoCliente.setDescuentoMasivo(descuentoMasivo);
				masivoCliente.setEstado(true);
				masivoCliente.setFechFin(descuentoMasivo.getFechFin());
				masivoCliente.setFechInicio(descuentoMasivo.getFechInicio());
				if(isPlaccas) {
					masivoCliente.setPlaca(line.replaceAll("-", "").replaceAll(" ", ""));
				}else {
					masivoCliente.setUuid(line.replaceAll("-", "").replaceAll(" ", ""));
				}
				session.save(masivoCliente);
				//filtro para guardar y limpiar
				if(count % 25 ==0) {
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
			if(sc.ioException() != null) {
				throw sc.ioException();
			}
			session.close();
			
			trigger.launch( "", "termino.masivo.carga",  "descuento id : "+descuentoid +" cantidad cargada : " + count, null);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
    }
    
    public void deshabilitarCodigo() {
    	
    	Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
		String fecha = dateFormat.format(calendar.getTime()) ;
    	
    	List<String> codigos = new ArrayList<>();
    	
    	String hql = "select dc.uuid from comprobante com"+
    			"inner join descuentocomprobante dc on com.descuentocomprobante_id = dc.id"+
    			"where upper(nombredescuento) = upper('INTERSEGURO') and dc.uuid != '' and"+
    			"to_char(fechapago, 'YYYY-MM-DD') between '"+fecha+"' and '"+fecha+"'";
    	Query query = entityManager.createNativeQuery(hql);
    	codigos = query.getResultList();
    	if(codigos != null && !codigos.isEmpty() && codigos.size() > 0) {
    		String hqlUpdate = "update DescuentoMasivoCliente set estado = :valor where uuid in (:uuid)";
        	Query query2 = entityManager.createQuery(hqlUpdate);
        	query2.setParameter("valor", false);
        	query2.setParameter("uuid", codigos);
        	query2.executeUpdate();
    	}
    }

    @Transactional
	public void updateFacturacionDescuento(Long id, Boolean facturaConsolidado) {
		// TODO Auto-generated method stub
    	String hql = "update Descuento set facturaConsolidado = :facturaConsolidado where id = :id";
    	Query query = entityManager.createQuery(hql);
    	query.setParameter("facturaConsolidado", facturaConsolidado);
    	query.setParameter("id", id);
    	query.executeUpdate();
	}
    
}

