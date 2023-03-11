package com.farenet.nodo.maestro.api.inspeccion.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.farenet.nodo.maestro.api.linea.domain.ResultadoMaquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Pago;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteestadoRepository;
import com.farenet.nodo.maestro.api.caja.repository.DescuentoClienteRepository;
import com.farenet.nodo.maestro.api.caja.repository.DescuentoDetalleRepository;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.caja.service.DescuentoService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.caja.service.PersonaService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Certificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.CertificadoError;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.DatosComercialBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionInicioBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionListBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionSupportSedeBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.ReinspeccionBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.inspeccionMtcBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionestadoRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TarjetapropiedadRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipoautorizacionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipocertificadoRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipoinspeccionRepository;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlacaInspeccionBean;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.services.ST_MensajeService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.UIUtils;

@Service
@Transactional
public class InspeccionService {
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private ComprobanteRepository comprobanteRepository;

	@Autowired
	private InspeccionRepository inspeccionRepository;

	@Autowired
	private TarjetapropiedadRepository tarjetaPropiedadRepository;
	
	@Autowired
	private DescuentoDetalleRepository descuentoDetalleRepository;
	
	@Autowired
	private DescuentoClienteRepository descuentoClienteRepository;

	@Autowired
	private EntityManager entityManager;
	

	@Autowired
	private EntityManager entityManagerReplica;

	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private ST_MensajeService stMensajeService;
	  
    @Autowired
    private TriggerTaskFactory trigger;

	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private DescuentoService descuentoService;

	@Autowired
	private CierreRepository cierreRepository;

	@Autowired
	private ComprobanteestadoRepository comprobanteEstadoRepository;

	@Autowired
	private InspeccionestadoRepository inspeccionEstadoRepository;
	
	@Autowired
	private TipoinspeccionRepository tipoinspeccionRepository;
	
	@Autowired
	private TipocertificadoRepository tipocertificadoRepository;
	
	@Autowired
	private TipoautorizacionRepository tipoautorizacionRepository;
	
	@Autowired
	private PersonaService personaService;

	public List<Inspeccion> getAll() {
		return inspeccionRepository.findAllByEstado(true);
	}
	
	public void saveSupportSede(InspeccionSupportSedeBean bean) throws Exception {
		Inspeccion inspeccion = get(bean.getNroinspeccion());
		Vehiculo vehiculo = vehiculoService.getOneByNroMotor(bean.getNromotor());
		Vehiculo vehiculoNew = vehiculo;
		Boolean cambioVeh = false;
		if(!bean.getNromotor().equals(inspeccion.getVehiculo().getNromotor())) {
			cambioVeh = true;
			vehiculoNew.setNromotor(bean.getNromotor());
		}
		if(!bean.getNroserie().equals(inspeccion.getVehiculo().getNroserie())) {
			cambioVeh = true;
			vehiculoNew.setNroserie(bean.getNroserie());
		}
		
		if(cambioVeh) {
			vehiculoService.save(vehiculoNew);
		}
		
		inspeccion.setTipoinspeccion(tipoinspeccionRepository.findOneBykey(bean.getCodTipoInspeccion()));
		inspeccion.setTipoautorizacion(tipoautorizacionRepository.findOneByKey(bean.getCodTipoAutorizacion()));
		inspeccion.setTipocertificado(tipocertificadoRepository.findOneByKey(bean.getCodTipoCertificado()));
		
		guardar(inspeccion);
	}
	
	public InspeccionSupportSedeBean getOneSupportById(String filtro) {
		InspeccionSupportSedeBean result = null;
		
		Boolean isInspeccion = false;
		
		if(filtro != null && filtro.startsWith("INS-")) {
			isInspeccion = true;
		}
		
		String hql1 = "select new "+InspeccionSupportSedeBean.class.getName()
				+ "(ins.nrodocumentoinspeccion,com.placaMotor,veh.nromotor,veh.nroserie,ti.key,"
				+ "tc.key,ta.key)"
				+ " from Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join ins.tipoinspeccion ti"
				+ " inner join ins.tipocertificado tc"
				+ " inner join ins.tipoautorizacion ta"
				+ " inner join ins.vehiculo veh"
				+ " inner join ins.usuarioingcertificador usu";
				if(isInspeccion) {
					hql1 += " where ins.nrodocumentoinspeccion = :filtro";
				}else{
					hql1 += " where com.placaMotor = :filtro";
				}
					
				hql1 += " order by com.fechapago desc";
		Query query = entityManager.createQuery(hql1);
		query.setParameter("filtro", filtro);
		query.setMaxResults(1);
		result = (InspeccionSupportSedeBean) query.getSingleResult();
		return result;
	}

	public ResultPageBean<Inspeccion> getAll(PageBean pageBean) {

		ResultPageBean<Inspeccion> result = new ResultPageBean<Inspeccion>(
				inspeccionRepository.findAll(pageBean.convertToPage()));
		return result;
	}

	public ResultPageBean<InspeccionListBean> getByCliente(String field, PageBean pageBean)
			throws UnsupportedEncodingException {
		
		Map<String, String> mapField = QueryUtil.splitQuery(field);
		String estado = mapField.get("status");

		String queryCount = "SELECT count(distinct i) FROM Inspeccion i ";
		String query = "SELECT new "+InspeccionListBean.class.getName()
				+ "(c.nrocomprobante,ci.abreviatura,li.nombre,i.nrodocumentoinspeccion,i.nrodocumentoinspeccion,cer.nrodocumentoCertificado,"
				+ "i.fechconsolidado,c.placaMotor,concat(cer.anulado,''),ie.key,ie.key,i.resultado,ot.nroOT)"
				+ " FROM Inspeccion i ";
		
		String join = " left join i.certificados cer"
				+ " inner join i.comprobantes c inner join i.inspeccionestado ie inner join c.conceptoInspeccion ci "
				+ " left join c.ordenTrabajo ot "
				+ " inner join c.linea li ";
		String where = " where 1=1 ";
		
		where += " and i.nrodocumentoinspeccion like :planta ";
		
		
		if(mapField.get("status") != null ) {
			if(estado != null && !estado.isEmpty()) {
				if(estado.equals("A") || estado.equals("D")) {
					where += " and i.resultado = :resultado ";
				}else {
					where += " and ie.key = :insest ";
				}
			}
		}
		
		if(mapField.get("field") != null) {
			
			where += " and c.placaMotor like :placa ";
		}
		
		where += " and i.fechconsolidado BETWEEN :fechaIni AND :fechaFin ";
		
		queryCount += join + " " + where;
		query += join + " " + where;

		TypedQuery<Long> typedQueryCount = entityManager.createQuery(queryCount, Long.class);
		TypedQuery<InspeccionListBean> typedQuery = entityManager.createQuery(query, InspeccionListBean.class);
		
		typedQueryCount.setParameter("planta", "INS-"+mapField.get("planta")+"-%");
		typedQuery.setParameter("planta", "INS-"+mapField.get("planta")+"-%");
		
		if(mapField.get("status") != null ) {
			if(estado != null && !estado.isEmpty()) {
				if(estado.equals("A") || estado.equals("D")) {
					typedQueryCount.setParameter("resultado", mapField.get("status"));
					typedQuery.setParameter("resultado", mapField.get("status"));
				}else {
					typedQueryCount.setParameter("insest", mapField.get("status"));
					typedQuery.setParameter("insest", mapField.get("status"));
				}
			}
		}
		
		if(mapField.get("field") != null) {
			typedQueryCount.setParameter("placa", "%"+mapField.get("field")+"%");
			typedQuery.setParameter("placa", "%"+mapField.get("field")+"%");
		}
		
		
		if(mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
			Timestamp fechaIni = UIUtils.initDay(new Date(Long.valueOf(mapField.get("fechaIni"))));
			Timestamp fechaFin = UIUtils.finDay(new Date(Long.valueOf(mapField.get("fechaFin"))));

			
			typedQueryCount.setParameter("fechaIni", fechaIni);
			typedQueryCount.setParameter("fechaFin", fechaFin);
			
			typedQuery.setParameter("fechaIni", fechaIni);
			typedQuery.setParameter("fechaFin", fechaFin);
		}else {
			typedQueryCount.setParameter("fechaIni", UIUtils.initDay());
			typedQueryCount.setParameter("fechaFin", UIUtils.finDay());
			
			typedQuery.setParameter("fechaIni", UIUtils.initDay());
			typedQuery.setParameter("fechaFin", UIUtils.finDay());
		}
		

		Long numberRows = typedQueryCount.getSingleResult();

		List lstResult = typedQuery.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina)
				.setMaxResults(pageBean.totalFilasPorPagina)
				.getResultList();

		double before = (double) numberRows / pageBean.totalFilasPorPagina;
		ResultPageBean<InspeccionListBean> result = new ResultPageBean<InspeccionListBean>((int) Math.ceil(before), numberRows,
				lstResult);
		
		result.getContent().forEach(bean -> {
			if(!(bean.getNrocomprobante() != null )) {
				bean.setNrocomprobante(bean.getNroOt());
			}
		});

		return result;

	}
	
	public ResultPageBean<Inspeccion> get(String field, PageBean pageBean) throws UnsupportedEncodingException {
		Map<String, String> mapField = QueryUtil.splitQuery(field);


		String queryCount = "SELECT count(distinct i) FROM Comprobante c ";

		String query = "SELECT distinct i FROM Comprobante c ";

		String join = "";
		String where = " where 1 = 1 ";

		List<Tarjetapropiedad> lstSearch = new ArrayList<Tarjetapropiedad>();
		if (mapField.get("field") != null) {
			lstSearch = tarjetaPropiedadRepository
					.searchPropietarioByNombre("%" + mapField.get("field").toUpperCase() + "%");

			if (lstSearch.size() == 0) {
				lstSearch = tarjetaPropiedadRepository
						.searchPropietarioByDNI("%" + mapField.get("field").toUpperCase() + "%");
			}

			if (lstSearch.size() > 0) {
				join += "JOIN c.inspeccion i " + "JOIN i.vehiculo v " + "join v.tarjetapropiedad t "
						+ "join t.propietario p ";

				where += " and p.nrodocumentoidentidad = :nrodocumentoidentidad ";

				if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
					where += " and i.fechconsolidado BETWEEN :fechaIni AND :fechaFin ";

				}

				if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
					where += " and UPPER(i.resultado)  LIKE UPPER(:status) ";
				}

				if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
					join += " JOIN c.linea l JOIN l.planta pl ";
					where += " and pl.key =  :planta";
				}

				queryCount += join + " " + where;
				query += join + " " + where;

				Tarjetapropiedad tarjetapropiedad = lstSearch.get(0);

				TypedQuery<Long> typedQueryCount = entityManager.createQuery(queryCount, Long.class);
				typedQueryCount.setParameter("nrodocumentoidentidad",
						tarjetapropiedad.getPropietario().getNrodocumentoidentidad());

				TypedQuery<Inspeccion> typedQuery = entityManager.createQuery(query, Inspeccion.class);
				typedQuery.setParameter("nrodocumentoidentidad",
						tarjetapropiedad.getPropietario().getNrodocumentoidentidad());

				// seteamos parametros
				if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
					Date fechaIniStr = new Date(new Long(mapField.get("fechaIni")));
					Date fechaFinStr = new Date(new Long(mapField.get("fechaFin")));

					typedQueryCount.setParameter("fechaIni", fechaIniStr);
					typedQueryCount.setParameter("fechaFin", fechaFinStr);

					typedQuery.setParameter("fechaIni", fechaIniStr);
					typedQuery.setParameter("fechaFin", fechaFinStr);
				}

				if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
					typedQueryCount.setParameter("status", "%" + mapField.get("status") + "%");
					typedQuery.setParameter("status", "%" + mapField.get("status") + "%");
				}

				if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
					typedQueryCount.setParameter("planta", mapField.get("planta"));
					typedQuery.setParameter("planta", mapField.get("planta"));
				}

				Long numberRows = typedQueryCount.getSingleResult();

				List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
						.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

				double before = (double) numberRows / pageBean.totalFilasPorPagina;
				ResultPageBean<Inspeccion> result = new ResultPageBean<Inspeccion>((int) Math.ceil(before), numberRows,
						lstResult);

				return result;

			} else {

				join += "JOIN c.inspeccion i " + "JOIN i.vehiculo v " + "JOIN v.tarjetapropiedad t ";

				where += " and UPPER(t.nroplaca) LIKE UPPER(:placa) ";

				if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
					where += " and i.fechconsolidado BETWEEN :fechaIni AND :fechaFin ";

				}

				if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
					where += " and UPPER(i.resultado)  LIKE UPPER(:status) ";
				}

				if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
					join += " JOIN c.linea l JOIN l.planta pl ";
					where += " and pl.key =  :planta";
				}

				queryCount += join + " " + where;
				query += join + " " + where;

				TypedQuery<Long> typedQueryCount = entityManager.createQuery(queryCount, Long.class);
				typedQueryCount.setParameter("placa", "%" + mapField.get("field").toUpperCase() + "%");

				TypedQuery<Inspeccion> typedQuery = entityManager.createQuery(query, Inspeccion.class);
				typedQuery.setParameter("placa", "%" + mapField.get("field").toUpperCase() + "%");

				// seteamos parametros
				if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
					Date fechaIniStr = new Date(new Long(mapField.get("fechaIni")));
					Date fechaFinStr = new Date(new Long(mapField.get("fechaFin")));

					typedQueryCount.setParameter("fechaIni", fechaIniStr);
					typedQueryCount.setParameter("fechaFin", fechaFinStr);

					typedQuery.setParameter("fechaIni", fechaIniStr);
					typedQuery.setParameter("fechaFin", fechaFinStr);
				}

				if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
					typedQueryCount.setParameter("status", "%" + mapField.get("status") + "%");
					typedQuery.setParameter("status", "%" + mapField.get("status") + "%");
				}

				if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
					typedQueryCount.setParameter("planta", mapField.get("planta"));
					typedQuery.setParameter("planta", mapField.get("planta"));
				}

				Long numberRows = typedQueryCount.getSingleResult();

				List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
						.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

				double before = (double) numberRows / pageBean.totalFilasPorPagina;
				ResultPageBean<Inspeccion> result = new ResultPageBean<Inspeccion>((int) Math.ceil(before), numberRows,
						lstResult);

				return result;
			}

		} else {

			join += " JOIN c.inspeccion i ";

			if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
				where += " and i.fechconsolidado BETWEEN :fechaIni AND :fechaFin ";

			}

			if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
				where += " and UPPER(i.resultado)  LIKE UPPER(:status) ";
			}

			if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
				join += " JOIN c.linea l JOIN l.planta pl ";
				where += " and pl.key =  :planta";
			}

			queryCount += join + " " + where;
			query += join + " " + where;

			TypedQuery<Long> typedQueryCount = entityManager.createQuery(queryCount, Long.class);

			TypedQuery<Inspeccion> typedQuery = entityManager.createQuery(query, Inspeccion.class);

			// seteamos parametros
			if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
				Date fechaIniStr = new Date(new Long(mapField.get("fechaIni")));
				Date fechaFinStr = new Date(new Long(mapField.get("fechaFin")));

				typedQueryCount.setParameter("fechaIni", fechaIniStr);
				typedQueryCount.setParameter("fechaFin", fechaFinStr);

				typedQuery.setParameter("fechaIni", fechaIniStr);
				typedQuery.setParameter("fechaFin", fechaFinStr);
			}

			if (mapField.get("status") != null && !mapField.get("status").trim().equals("")) {
				typedQueryCount.setParameter("status", "%" + mapField.get("status") + "%");
				typedQuery.setParameter("status", "%" + mapField.get("status") + "%");
			}

			if (mapField.get("planta") != null && !mapField.get("planta").trim().equals("")) {
				typedQueryCount.setParameter("planta", mapField.get("planta"));
				typedQuery.setParameter("planta", mapField.get("planta"));
			}

			Long numberRows = typedQueryCount.getSingleResult();

			List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
					.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

			double before = (double) numberRows / pageBean.totalFilasPorPagina;
			ResultPageBean<Inspeccion> result = new ResultPageBean<Inspeccion>((int) Math.ceil(before), numberRows,
					lstResult);

			return result;

		}
	}
	
	public void guardarPdf(String pdf, String nrodocumentoinspeccion){
		ST_Mensaje st_Mensaje = stMensajeService.findByInspeccionAndTipo(nrodocumentoinspeccion);
		st_Mensaje.setPdf(pdf);
		stMensajeService.guardar(st_Mensaje);
	}
	
	public String generarComprobante(String nrodocumentoinspeccion){
		Inspeccion inspeccion = get(nrodocumentoinspeccion);
		trigger.launch("", "enviar.carga.sunat","SE CARGA A SUNAT : " +  inspeccion.getNrodocumentoinspeccion(), inspeccion.getNrodocumentoinspeccion());
		if(inspeccion.getLastComprobante() != null && inspeccion.getLastComprobante().getFechapago()!=null) {
    		Calendar fechaInspeccion = Calendar.getInstance();
    		fechaInspeccion.setTime(inspeccion.getLastComprobante().getFechapago());
    		
    		if(UIUtils.daysBetween(Calendar.getInstance(), fechaInspeccion)>0) {
    			return "No es del mismo dia"; 
    		}else {
    			return "ok";
    		}
    	}
		return "ok";
	}
	
	public void guardarPaseALinea(Inspeccion inspeccion) throws Exception {
		
		
		//envio y validacion temporal
		if(inspeccion.getComprobantes()!=null 
				&& inspeccion.getComprobantes().size()>0
				&& inspeccion.getLastComprobante().getNrocomprobante()==null ) //si no tiene nro de comprobante asignado
		{
			
			if(inspeccion.getLastComprobante().getDescuentoComprobante() != null && 
					inspeccion.getLastComprobante().getDescuentoComprobante().getFacturaConsolidado() != null &&
					inspeccion.getLastComprobante().getDescuentoComprobante().getFacturaConsolidado() &&
					inspeccion.getLastComprobante().getFormaPago().getKey().equals("credito")) {
				
				String nroOT = maestroService.nextOrdenTrabajo(inspeccion.getLastComprobante().getLinea().getPlanta().getEmpresa().getKey());
				inspeccion.getLastComprobante().getOrdenTrabajo().setNroOT(nroOT);
				guardarProceso(inspeccion);
				
			}else {
				
				guardarProceso(inspeccion);
				//enviar a sunat
				trigger.launch("", "enviar.carga.sunat","SE CARGA A SUNAT : " +  inspeccion.getNrodocumentoinspeccion(), inspeccion.getNrodocumentoinspeccion());
			}
			
		}else {
			throw new Exception("No se puede pasar a linea. Revisar la inspeccion");
		}
		
	}
	
		public Inspeccion guardarProceso(Inspeccion inspeccion) throws Exception {
			//recorremos el acceso del certificado
			
			Inspeccionestado inspeccionestado = inspeccionEstadoRepository.findOneByNroInspeccion(inspeccion.getNrodocumentoinspeccion());
			
			if(inspeccionestado != null) {
				if(inspeccionestado.getKey().equals("CON")){
					throw new Exception("Inspeccion ya se encuentra consolidado " +inspeccion.getNrodocumentoinspeccion());
				}
			}
			if(inspeccion.getCertificados()!=null)
			{
				for(Certificado certificado : inspeccion.getCertificados())
				{
					certificado.setInspeccion(inspeccion);
				}
			}

			if(inspeccion.getResultadosMaquina()!=null)
			{
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					resultado.setInspeccion(inspeccion);
				}
			}
			
			for(Comprobante comprobante : inspeccion.getComprobantes())
			{
				if(comprobante.getNrocomprobante()!=null)
				{
					String nroinspeccion = comprobanteService.getComprobanteInspeccion(comprobante.getNrocomprobante(), comprobante.getEmpresa().getRuc());
					
					if(nroinspeccion!=null)
					{
						if(!nroinspeccion.equalsIgnoreCase(inspeccion.getNrodocumentoinspeccion()))
						{
							throw new Exception("Dicho nro de comprobante ya existe para otra inspeccion");
						}
					}
				}
				//verificamos que las placas motor existan en el vehiculo seleccionado
				if(inspeccion.getVehiculo() != null  
						&& inspeccion.getVehiculo().getTarjetapropiedad()!=null) {
					
					Vehiculo v = inspeccion.getVehiculo();
					
					if(!v.getTarjetapropiedad().getNroplaca().equals(comprobante.getPlacaMotor()))
						throw new Exception("Placa no registrada para dicho comprobante");
					
				}
				
				comprobante.setInspeccion(inspeccion);
				if(comprobante.getPagos() != null) {
					for(Pago pago : comprobante.getPagos()) {
						pago.setComprobante(comprobante);
					}
				}
				
				
			}
			
			
			
			if(inspeccion.getVehiculo() != null) {
				vehiculoService.save(inspeccion.getVehiculo());
			}
			
			return inspeccionRepository.save(inspeccion);
		}
		
		public void saveAndAnular(Inspeccion inspeccion) throws Exception {
		
			if(inspeccion.getComprobantes()!=null 
					&& inspeccion.getComprobantes().size()>0 ) //si no tiene nro de comprobante asignado
			{
				//guardamos		
				guardarInspeccionEstadoCon(inspeccion);
				
				//enviar a sunat
				trigger.launch("", "enviar.baja.sunat","SE BAJA LA SUNAT : " +  inspeccion.getNrodocumentoinspeccion(), inspeccion.getNrodocumentoinspeccion());
			    
				
			}else {
				throw new Exception("No se puede anuluar la inspeccion.");
			}
			
			/*for(Comprobante comprobante : inspeccion.getComprobantes())
			{
				
				if(comprobante.getDescuentoComprobante() != null) {
					
					DescuentoDetalle descuentoDetalle =descuentoDetalleRepository.getOne(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle()));
						if(descuentoDetalle.getDescuentoclientes().size()<0) {
							for(DescuentoCliente cliente : descuentoDetalle.getDescuentoclientes()) {
								cliente.getInspecciones().remove(inspeccion);
								descuentoClienteRepository.save(cliente);
								break;
							}
						}
					}
			}*/
		}
		
		public void delete(Inspeccion inspeccion) throws Exception {
			inspeccion.setVehiculo(null);
			Comprobante comprobante = inspeccion.getLastComprobante();
			comprobante.setCliente(null);
			comprobante.setDescuentoComprobante(null);
			comprobanteRepository.delete(comprobante);
			inspeccion.setComprobantes(null);
			inspeccionRepository.save(inspeccion);
			inspeccionRepository.delete(inspeccion);
		}
		
		
		public void guardarConsolidado(Inspeccion inspeccion) throws Exception {
			
			//	Vehiculo vehiculo = inspeccion.getLastComprobante().getVehiculo();
			
			if(inspeccion.getComprobantes().size()==0 || inspeccion.getComprobantes() == null){
				throw new Exception("inspeccion sin Comprobante - " + inspeccion.getNrodocumentoinspeccion());
			}
						
			//recorremos el acceso del certificado
			if(inspeccion.getCertificados()!=null) {
				for(Certificado certificado : inspeccion.getCertificados()) {
					certificado.setInspeccion(inspeccion);
					
					if(certificado.getCertificadoErrors() != null && !certificado.getCertificadoErrors().isEmpty()) {
						for(CertificadoError bean : certificado.getCertificadoErrors()) {
							bean.setCertificado(certificado);
						}
					}
					
				}
			}


			if(inspeccion.getResultadosMaquina()!=null)
			{
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					resultado.setInspeccion(inspeccion);
				}
			}

			for(Comprobante comprobante : inspeccion.getComprobantes()) {
				
				Boolean sendedtoofisis = comprobanteService.getOfisisValue(comprobante.getId());
				
				if(sendedtoofisis !=null) {
					comprobante.setSendedtooffisis(sendedtoofisis);
				}
				comprobante.setInspeccion(inspeccion);
				
				if(comprobante.getPagos() != null) {
					for(Pago pago : comprobante.getPagos()) {
						pago.setComprobante(comprobante);
					}
				}
			}
			
			//vehiculoService.save(vehiculo);
			inspeccionRepository.save(inspeccion);
			
			
		}
		
		public void guardarDuplicado(Inspeccion inspeccion) throws Exception {
			//envio y validacion temporal
			if(inspeccion.getComprobantes()!=null 
					&& inspeccion.getComprobantes().size()>0 ) //si no tiene nro de comprobante asignado
			{
				
				guardarInspeccionEstadoCon(inspeccion);

				//enviar a sunat
				trigger.launch("", "enviar.carga.sunat","SE CARGA A SUNAT : " +  inspeccion.getNrodocumentoinspeccion(), inspeccion.getNrodocumentoinspeccion());
			    
			}else {
				throw new Exception("No se guardar el servicio. Revisar la inspeccion");
			}
		}

		public void guardarServicio(Inspeccion inspeccion) throws Exception {
			
			//envio y validacion temporal
			if(inspeccion.getComprobantes()!=null 
					&& inspeccion.getComprobantes().size()>0
					&& inspeccion.getLastComprobante().getNrocomprobante()==null ) //si no tiene nro de comprobante asignado
			{
				
				guardar(inspeccion);

				//enviar a sunat
				trigger.launch("", "enviar.carga.sunat","SE CARGA A SUNAT : " +  inspeccion.getNrodocumentoinspeccion(), inspeccion.getNrodocumentoinspeccion());
			    
			}else {
				throw new Exception("No se guardar el servicio. Revisar la inspeccion");
			}
			
		}
		
		
		public void guardarInspeccionEstadoCon(Inspeccion inspeccion) throws Exception {
			
			if(inspeccion.getComprobantes().size()==0 || inspeccion.getComprobantes() == null){
				throw new Exception("inspeccion sin Comprobante - " + inspeccion.getNrodocumentoinspeccion());
			}
					
			//recorremos el acceso del certificado
			if(inspeccion.getCertificados()!=null) {
				for(Certificado certificado : inspeccion.getCertificados()) {
					certificado.setInspeccion(inspeccion);
					if(certificado.getCertificadoErrors() != null && !certificado.getCertificadoErrors().isEmpty()) {
						for(CertificadoError bean : certificado.getCertificadoErrors()) {
							bean.setCertificado(certificado);
						}
					}
				}
			}
			
			for(Comprobante comprobante : inspeccion.getComprobantes()) {
				Boolean sendedtoofisis = comprobanteService.getOfisisValue(comprobante.getId());
				
				if(sendedtoofisis !=null) {
					comprobante.setSendedtooffisis(sendedtoofisis);
				}
				
				comprobante.setInspeccion(inspeccion);
				
				if(comprobante.getPagos() != null) {
					for(Pago pago : comprobante.getPagos()) {
						pago.setComprobante(comprobante);
					}
				}
			}


			if(inspeccion.getResultadosMaquina()!=null)
			{
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					resultado.setInspeccion(inspeccion);
				}
			}

			inspeccionRepository.save(inspeccion);
		}
		
	//@CacheEvict(cacheNames = "inspecciones", allEntries = true)
	public void guardar(Inspeccion inspeccion) throws Exception {
	//	Vehiculo vehiculo = inspeccion.getLastComprobante().getVehiculo();
		
		if(inspeccion.getComprobantes().size()==0 || inspeccion.getComprobantes() == null){
			throw new Exception("inspeccion sin Comprobante - " + inspeccion.getNrodocumentoinspeccion());
		}
		
		Inspeccionestado inspeccionestado = inspeccionEstadoRepository.findOneByNroInspeccion(inspeccion.getNrodocumentoinspeccion());
		
		if(inspeccionestado != null) {
			if(inspeccionestado.getKey().equals("CON")){
				throw new Exception("Inspeccion ya se encuentra consolidado " +inspeccion.getNrodocumentoinspeccion());
			}
		}
		
		//recorremos el acceso del certificado
		if(inspeccion.getCertificados()!=null)
		{
			for(Certificado certificado : inspeccion.getCertificados())
			{
				certificado.setInspeccion(inspeccion);
				if(certificado.getCertificadoErrors() != null && !certificado.getCertificadoErrors().isEmpty()) {
					for(CertificadoError bean : certificado.getCertificadoErrors()) {
						bean.setCertificado(certificado);
					}
				}
			}
		}


		if(inspeccion.getResultadosMaquina()!=null)
		{
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				resultado.setInspeccion(inspeccion);
			}
		}
		
		for(Comprobante comprobante : inspeccion.getComprobantes())
		{
			
			Boolean sendedtoofisis = comprobanteService.getOfisisValue(comprobante.getId());
			
			if(sendedtoofisis !=null)
			{
				comprobante.setSendedtooffisis(sendedtoofisis);
			}
			
			/*if(comprobante.getDescuentoComprobante() != null) {
				
				DescuentoDetalle descuentoDetalle =descuentoDetalleRepository.getOne(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle()));
					if(descuentoDetalle.getDescuentoclientes().size()<0) {
						for(DescuentoCliente cliente : descuentoDetalle.getDescuentoclientes()) {
							if(comprobante.getPlacaMotor().equals(cliente.getPlaca())) {
								cliente.getInspecciones().add(inspeccion);
							}
							if(cliente.getUuid() != null) {
								if(cliente.getUuid().equals(comprobante.getDescuentoComprobante().getUuid())) {
									cliente.getInspecciones().add(inspeccion);
								}
							}
							
						}
					}
				}*/
			
			
			comprobante.setInspeccion(inspeccion);
			
			if(comprobante.getPagos() != null) {
				for(Pago pago : comprobante.getPagos()) {
					pago.setComprobante(comprobante);
				}
			}

			
		}
		
		//vehiculoService.save(vehiculo);
		inspeccionRepository.save(inspeccion);
	}

	public void update(Inspeccion inspeccion) {
		inspeccionRepository.saveAndFlush(inspeccion);
	}

	public Inspeccion get(String nrodocumentoinspeccion) {
		return inspeccionRepository.findByNrodocumentoinspeccion(nrodocumentoinspeccion);
	}
	
	public Inspeccion getInspeccionByComprobante(String nrocomprobante,String empresa) {
		return inspeccionRepository.findByComprobante(nrocomprobante,empresa);
	}
	
	public Inspeccion getInspeccionByNroOt(String nroOt,String empresa) {
		return inspeccionRepository.findByNroOtAndEmpresa(nroOt,empresa);
	}

	public PlacaInspeccionBean getPlacaInspeccion(String placa)
	{
		List<Inspeccion> resultado = inspeccionRepository.searchByPlaca(placa);
		
		for(Inspeccion i : resultado)
		{
			if(i.getResultado()!=null)
			{
				
				String placaTarjeta = i.getVehiculo().getTarjetapropiedad().getNroplaca();
				
				if(placaTarjeta!=null)
				{
					PlacaInspeccionBean bean = new PlacaInspeccionBean();
					bean.placa = placaTarjeta;
					bean.fechaVencimiento= i.getFechvencimiento();
					bean.certificado = i.getLastCertificado().getNrodocumentoCertificado();
					bean.planta = i.getLastComprobante().getLinea().getPlanta().getNombre();
					bean.fechaRevision = i.getLastCertificado().getFechcreacion();
					
					return bean;
				}
				
			
				
			}
		}
		
		return null;
	}
	
	/*public List<DatosComercialBean> getPlacaComercial(String placa){
		List<DatosComercialBean> lstresult= new ArrayList<>();
    	List<Object> result = new ArrayList<>();
    	StringBuffer hql =new StringBuffer("select apellido_mat,apellido_pat,correo1, ")
    			.append(" correo2,kilometraje,nombre,placa,telf_casa,telf_movil,acepta_cond ")
    			.append(" from datosclientecomercial where  ")
    			.append(" placa='"+placa+"'");
    	Query query = entityManagerComercial.createNativeQuery(hql.toString());
    	result = (List<Object>) query.getResultList();     	
    	Iterator<Object> itr = result.iterator(); 
    	while (itr.hasNext()) {
    		DatosComercialBean bean = new DatosComercialBean();
        	Object[] obj = (Object[]) itr.next(); 
			bean.setApellido_mat(obj[0]!=null ? obj[0].toString():"");
			bean.setApellido_pat(obj[1]!=null ? obj[1].toString():"");
			bean.setCorreo1(obj[2]!=null ? obj[2].toString():"");
			bean.setCorreo2(obj[3]!=null ? obj[3].toString():"");
			bean.setKilometraje(obj[4]!=null?Double.parseDouble(obj[4].toString()):0);
			bean.setNombre(obj[5] != null ? obj[5].toString():"");
			bean.setPlaca(obj[6]!=null ? obj[6].toString():"");
			bean.setTelf_casa(obj[7]!=null ? obj[7].toString():"");
			bean.setTelf_movil(obj[8]!=null ? obj[8].toString():"");
			bean.setAcepta_cond(obj[9]!=null ? Boolean.parseBoolean(obj[9].toString()):false);
			lstresult.add(bean);
			
		}
    	return lstresult;
    	
	}*/
	
	public Double getByFormapagoByNoAnuladoByLinea(Date fechini, Date fechfin,
			String formapago, String anulado, String linea){
		
		Timestamp fechaMin = new Timestamp(fechini.getTime());
        Timestamp fechaMax = new Timestamp(fechfin.getTime());
        
		String hql1 = "SELECT sum(com.totalsindscto - com.totaldscto) FROM Comprobante com "
				+ " inner join com.inspeccion ins"
				+ " inner join com.comprobanteestado ce"
				+ " inner join com.formaPago fp"
				+ " inner join com.linea li"
				+ " WHERE com.fechapago between :fechaMin and :fechaMax "
				+ " and ce.key <> :anulado and fp.key = :formapago and li.key = :linea and com.nrocomprobante is not null";
		Query query = entityManager.createQuery(hql1);
		query.setParameter("fechaMin", fechaMin);
		query.setParameter("fechaMax", fechaMax);
		query.setParameter("anulado", anulado);
		query.setParameter("formapago", formapago);
		query.setParameter("linea", linea);
		
		Double resultado = (Double)query.getSingleResult();
		return  resultado!=null?resultado:0d;
		
	}
	
	public List<Inspeccion> getAllByfechasPlaca(Date fechini, Date fechfin, String placa){
		
		Timestamp fechaMin = new Timestamp(fechini.getTime());
        Timestamp fechaMax = new Timestamp(fechfin.getTime());
        
		String hql1 = "SELECT ins FROM Comprobante com "
				+ " inner join com.inspeccion ins"
				+ " inner join com.comprobanteestado ce"
				+ " WHERE :fechaMin < com.fechapago and com.fechapago < :fechaMax "
				+ " and ce.key = 'ANU' and com.placaMotor = :placa ";
		Query query = entityManager.createQuery(hql1);
		query.setParameter("fechaMin", fechaMin);
		query.setParameter("fechaMax", fechaMax);
		query.setParameter("placa", placa);
		return  query.getResultList();
		
	}
	
	

	public List<Inspeccion> getByFormapagoByAnuladoByLinea(Date fechini, Date fechfin, String planta){
		
		Timestamp fechaMin = new Timestamp(fechini.getTime());
        Timestamp fechaMax = new Timestamp(fechfin.getTime());
        
		String hql1 = "SELECT ins FROM Comprobante com "
				+ " inner join com.inspeccion ins"
				+ " inner join com.comprobanteestado ce"
				+ " WHERE com.fechanulacion between :fechaMin and :fechaMax "
				+ " and ce.key = 'ANU' and ins.nrodocumentoinspeccion LIKE :planta and com.fechapago < :fechaMin"
				+ " and com.nrocomprobante is not null";
		Query query = entityManager.createQuery(hql1);
		query.setParameter("fechaMin", fechaMin);
		query.setParameter("fechaMax", fechaMax);
		query.setParameter("planta", "INS-"+planta+"-%");
		return  query.getResultList();
		
	}
	
	public String getInspeccionExiste(String planta, String placa){
		Timestamp fechaHoy = new Timestamp(new Date().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -3);
		Timestamp fechMin = new Timestamp(calendar.getTime().getTime());
		
		String hql = "select concat(to_char(i.fechconsolidado,'YYYY-mm-dd'),' - ',c2.abreviatura) from comprobante c"
				+ " inner join linea l on c.linea_key = l.key"
				+ " inner join planta p on l.planta_key = p.key"
				+ " inner join inspeccion i on c.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion"
				+ " inner join conceptoinspeccion c2 on c.conceptoinspeccion_key = c2.key"
				+ " where i.resultado='A' and"
				+ " i.inspeccionestado_key='CON'AND "
				+ " placamotor='"+placa+"' and "
				+ " p.key='"+planta+"' AND "
				+ " i.fechconsolidado between :fechMin and :fechaHoy ";
		Query query = entityManager.createNativeQuery(hql);
		query.setParameter("fechMin", fechMin);
		query.setParameter("fechaHoy", fechaHoy);
		return query.getSingleResult().toString();
	}
	
	public List<ReinspeccionBean> getReinspeccionBean(String placa,String planta){
		List<ReinspeccionBean> lstResult = new ArrayList<>();
		//List<Inspeccion> result= inspeccionRepository.searchByPlaca()
		Timestamp fechaHoy = new Timestamp(new Date().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.WEEK_OF_MONTH, -35);
		Timestamp fechMin = new Timestamp(calendar.getTime().getTime());

    		String hql = "select new "+ReinspeccionBean.class.getName()
    				+ " (com.conceptoInspeccion ,ve.categoria ,ins.tipoinspeccion, ins.tipocertificado, ins.tipoautorizacion, ins.fechconsolidado,"
    				+ "ins.resultado, ins.inspeccionestado)"
    				+ " from Comprobante com"
    				+ " inner join com.inspeccion ins"
    				+ " inner join com.linea li"
    				+ " inner join ins.vehiculo ve"
    				+ " inner join li.planta pl"
    				+ " where com.placaMotor = :placa and pl.key = :planta and"
    				+ " to_date(to_char(ins.fechconsolidado, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechMin and :fechMax"
    				+ " order by ins.fechconsolidado desc ";
    		
    		Query query = entityManager.createQuery(hql);
    		query.setParameter("placa", placa);
    		query.setParameter("planta", planta);
    		query.setParameter("fechMin", fechMin.getYear()+"-"+fechMin.getMonth()+"-01");
    		query.setParameter("fechMax", fechaHoy);
    		
    	    lstResult = query.getResultList();
		return lstResult;
	}
	
	public String getIdByNroInspeccion(String nroinspeccion) {
		if(inspeccionRepository.findIdByNroInspeccion(nroinspeccion) != null) {
			return nroinspeccion;
		}
		return "";
		
	}
	
	public String getLineaByNroInspeccion(String nroinspeccion) {
		if(inspeccionRepository.findLineaByNroInspeccion(nroinspeccion)  != null) {
			return nroinspeccion;
		}
		return "";
	}
	
	public String getValidarInspecionInicio(String placa, String tipoinspeccion,String tipocertificado, String conceptoinspeccion){
		try {
			String hql = "SELECT ins.nrodocumentoinspeccion "
					+ " from Comprobante com"
					+ " inner join com.conceptoInspeccion ci"
					+ " inner join com.inspeccion ins"
					+ " inner join ins.tipoinspeccion ti"
					+ " inner join ins.tipocertificado tc"
					+ " inner join ins.inspeccionestado ie"
					+ " where ti.key = :tipoinspeccion and com.placaMotor = :placa and ci.key = :conceptoinspeccion and ie.key = 'PROCESO' and tc.key = :tipocertificado ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("tipoinspeccion", tipoinspeccion);
			query.setParameter("placa", placa);
			query.setParameter("conceptoinspeccion", conceptoinspeccion);
			query.setParameter("tipocertificado", tipocertificado);
			query.setFirstResult(0);
			query.setMaxResults(1);
			return query.getSingleResult().toString();
		}catch (Exception e) {
			return null;
		}
	}
	
	public Inspeccion getReinspeccionByNrodocumentoinspeccion(String nrodocumentoreinspeccion) {
		return inspeccionRepository.findByReinspeccion(nrodocumentoreinspeccion).get(0);
	}
	
	public Inspeccion getReinspeccionByInspeccion(String nroinspeccion) {
		return inspeccionRepository.findByNrodocumentoinspeccion(nroinspeccion);
	}
	
	
	public List<Inspeccion> getReinspeccionLastByPlaca(String placa) {

		List<Inspeccion> resultado = inspeccionRepository.searchByPlacaComprobante(placa);
		
		for(Inspeccion i : resultado)
		{
			if(i.getResultado()!=null)
			{
				if(i.getInspeccionestado().getKey().equals("RETIRADO") || i.getResultado().toUpperCase().equals("D"))
				{
					 List<Inspeccion> enviar = new ArrayList<>();
					 enviar.add(i);
					 return enviar;
				}else{
					return null;
				}
			}
		}
		
		return null;
	}
	
	public List<Inspeccion> getAllInspeccinoesByPlaca(String placa){
		return inspeccionRepository.searchByPlaca(placa);
	}
	
	public Long getCountInspeccionByPlaca(String placa) {
		return inspeccionRepository.findCountInspeccionByPlaca(placa);
	}
	
	public List<Inspeccion> getAprobadoLastByPlaca(String placa) {

		List<Inspeccion> resultado = inspeccionRepository.searchByPlaca(placa);
		
		for(Inspeccion i : resultado)
		{
			if(i.getResultado()!=null)
			{
				if(i.getResultado().toUpperCase().equals("A"))
				{
					 List<Inspeccion> enviar = new ArrayList<>();
					 enviar.add(i);
					 return enviar;
				}else{
					return null;
				}
			}
		}
		
		return null;
	}
	
	public List<Inspeccion> getAllByFecha(Date fechIni, Date fechFin, String planta){
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<Inspeccion> resultado  = new ArrayList<>(); 
        
        String hql1 = "SELECT ins FROM Inspeccion ins"
        		+ " inner join ins.inspeccionestado ie"
        		+ " WHERE to_date(to_char(ins.fechcreacion, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
                + " and ins.nrodocumentoinspeccion LIKE :planta_key and ie.key = 'PROCESO'" ;
        
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", "INS-"+planta+"-%");
        
        resultado = query.getResultList();
        return resultado;
	}
	
	public Double getSumByFormapagoByAnuladoByLinea(Date fechini, Date fechfin, String planta,String linea){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechini);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
		Date initDay = calendar.getTime();
		String hql1 = "select sum(p.importe)" 
				+ " from comprobante as c "
				+ " inner join pago as p on c.id = p.comprobante_id "
				+ " where c.inspeccion_nrodocumentoinspeccion like ?1 "
				+ " and c.fechanulacion >= ?2 "
				+ " and c.fechanulacion < ?3 "
				+ " and c.formapago_key = 'contado' "
				+ " and p.tipocontado_key = 'efectivo' " 
				+ " and c.linea_key = ?4 "
				+ " and c.fechapago < ?5 "
				+ " and c.fechapago > ?6 "
				+ " and c.nrocomprobante is not null "
				+ " and c.comprobanteestado_key = 'ANU' "
				+ " and (c.tipodescuento_key <> 'corte' or c.tipodescuento_key is null)";
		Query query = entityManager.createNativeQuery(hql1);

		query.setParameter(1, "INS-"+planta+"-%");
        query.setParameter(2, fechini);
        query.setParameter(3, fechfin);
        query.setParameter(4, linea);
        query.setParameter(5, fechini);
        query.setParameter(6, initDay);
		
		Double resultado = (Double)query.getSingleResult();
		return  resultado!=null?resultado:0d;
		
	}
	
	public Double getAllSumByDate(Date fechIni, Date fechFin, String planta, String linea){
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        Double resultado  = 0d; 
        
        SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
        
        String hql1 = "select sum(p.importe) " 
        		+ " from comprobante as c "
        		+ " inner join pago as p on c.id = p.comprobante_id " 
        		+ " where c.inspeccion_nrodocumentoinspeccion like ?1 " 
        		+ " and c.fechapago >= ?2 "
        		+ " and c.fechapago < ?3 "
        		+ " and c.formapago_key = 'contado' " 
        		+ " and p.tipocontado_key = 'efectivo' "
        		+ " and c.linea_key = ?4 "
        		+ " and c.nrocomprobante is not null "
        		+ " and c.comprobanteestado_key = 'CAN' "
        		+ " and (c.tipodescuento_key <> 'corte' or c.tipodescuento_key is null) ";
        
        Query query = entityManager.createNativeQuery(hql1);
        query.setParameter(1, "INS-"+planta+"-%");
        query.setParameter(2, fechIni);
        query.setParameter(3, fechFin);
        query.setParameter(4, linea);
        
        resultado = (Double)query.getSingleResult();
        return resultado!=null?resultado:0d;
	}
	
	public Double getAllSumDuplicado(Date fechIni, Date fechFin, String planta,String linea){
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        Double resultado  = 0d; 
        
        String hql1 = "SELECT sum(com.importetotal) FROM Inspeccion ins"
        		+ " inner join ins.inspeccionestado ie"
        		+ " inner join ins.comprobantes com"
        		+ " inner join com.formaPago for"
        		+ " inner join com.tipoContado tc"
        		+ " inner join com.comprobanteestado cbe"
        		+ " inner join com.linea lin"
        		+ " left join com.tipodescuento tds"
        		+ " WHERE com.fechapago between :fechaMin and :fechaMax "
                + " and ins.nrodocumentoinspeccion LIKE :planta_key and com.duplicado = true and com.nrocomprobante is not null" 
                + " and  com.duplicado is true "
                + " and for.key = 'contado'"
                + " and  tc.key = 'efectivo'"
                + " and cbe.key = 'CAN'"
                + " and lin.key = :linea"
                + " and tds.key <> 'corte'" ;
        
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", "INS-"+planta+"-%");
        query.setParameter("linea", linea);
        
        resultado = (Double)query.getSingleResult();
        return resultado!=null?resultado:0d;
	}
	
	public List<Inspeccion> getAllByDate(Date fechIni, Date fechFin, String planta){
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<Inspeccion> resultado  = new ArrayList<>(); 
        
        String hql1 = "SELECT ins FROM Inspeccion ins"
        		+ " inner join ins.inspeccionestado ie"
        		+ " inner join ins.comprobantes com"
        		+ " WHERE com.fechapago between :fechaMin and :fechaMax "
                + " and ins.nrodocumentoinspeccion LIKE :planta_key and com.nrocomprobante is not null" ;
        
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", "INS-"+planta+"-%");
        
        resultado = query.getResultList();
        return resultado;
	}
	
	public Inspeccion getOneByNroComprobanteAndPlanta(String nrocomprobante, String planta){
		String hql = "select ins from Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " where com.nrocomprobante = :nrocomprobante and ins.nrodocumentoinspeccion like :nrodocumentoinspeccion";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nrocomprobante", nrocomprobante);
		query.setParameter("nrodocumentoinspeccion", "INS-"+planta+"-%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Inspeccion) query.getSingleResult();
	}
	
	public List<Inspeccion> getAllDuplicado(Date fechIni, Date fechFin, String planta){
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
        List<Inspeccion> resultado  = new ArrayList<>(); 
        
        String hql1 = "SELECT ins FROM Inspeccion ins"
        		+ " inner join ins.inspeccionestado ie"
        		+ " inner join ins.comprobantes com"
        		+ " WHERE com.fechapago between :fechaMin and :fechaMax "
                + " and ins.nrodocumentoinspeccion LIKE :planta_key and com.duplicado = true and com.nrocomprobante is not null" ;
        
        Query query = entityManager.createQuery(hql1);
        query.setParameter("fechaMin", fechaMin);
        query.setParameter("fechaMax", fechaMax);
        query.setParameter("planta_key", "INS-"+planta+"-%");
        
        resultado = query.getResultList();
        return resultado;
	}
	
	public List<InspeccionInicioBean> getAllInspeccionesInicioByPlanta(String planta){
		
		List<InspeccionInicioBean> result = new ArrayList<>();
		POSICION posicion = POSICION.CONSOLIDADO;
		String hql = "SELECT DISTINCT new "+InspeccionInicioBean.class.getName()
				+ "(ins.nrodocumentoinspeccion,ci.abreviatura,com.placaMotor,pro.nrodocumentoidentidad,"
				+ "ins.posicion,ins.fechcreacion,ins.resultado,li.nombre,tpd.nombre,"
				+ "pro.nombrerazonsocial,CONCAT(pro.nombres,' ',pro.apellidos))"
				+ " FROM Comprobante com"
				+ " inner join com.conceptoInspeccion ci"
				+ " inner join com.inspeccion ins"
				+ " inner join ins.inspeccionestado ie"
				+ " left join com.linea li"
				+ " left join ins.vehiculo v"
				+ " left join v.tarjetapropiedad tp"
				+ " left join tp.propietario pro"
				+ " left join pro.tipodocumentoidentidad tpd"
				+ " WHERE ie.key = 'PROCESO' and"
				+ " ins.posicion <> :posicion and"
				+ " ins.nrodocumentoinspeccion like :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("posicion", posicion);
		query.setParameter("planta", "INS-"+planta+"-%");
		result = query.getResultList();
		
		List<InspeccionInicioBean> unique = new ArrayList<>();
		
		for(InspeccionInicioBean bean : result) {
			boolean find  =true;
			if(bean.getTipodocumento()!=null) {
				if(bean.getTipodocumento().equals("RUC")) {
					bean.setPropietario(bean.getRazon());
				}else {
					bean.setPropietario(bean.getNombresapellidos());
				}
			}
			for(InspeccionInicioBean filter: unique) {
				if(filter.getNrodocumentoinspeccion().equals(bean.getNrodocumentoinspeccion()))
        		{
        			find = false;
        			break;
        		}
			}
			if(find)
        		unique.add(bean);
			
		}
		
		return unique;
	}
	
	
	public List<Inspeccion> getInspecciones(Date fechIni,Date fechFin, String planta, String formaPago,String ruc)
	{
		
		 Timestamp fechaMin = new Timestamp(fechIni.getTime());
	        Timestamp fechaMax = new Timestamp(fechFin.getTime());

		
	        
		String hql1 = "SELECT ins FROM Inspeccion ins"
				+ " inner join ins.comprobantes com"
				+ " left join com.cliente cli"
				+ " inner join com.linea li"
				+ " inner join li.planta pl";
		
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
		
			hql1+= " inner join com.formaPago cfp ";
		}
		
		//where
		hql1 +=  " WHERE  ins.fechanulacion is  null and ins.resultado is not null and size(ins.certificados) > 0  ";
		
		if(fechaMin !=null && fechaMax!=null) {
			hql1 += " and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax";
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			hql1 += " and cli.nrodocumentoidentidad = :ruc";
		}
		
		if(! (planta.equals("todos")  || planta.equals("") || planta.equals("null")) ) {
			hql1 += " and pl.key = :planta_key";
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
			hql1 += " and cfp.key = :forma_pago ";
		}
				
		
		//invocar la query
		Query query = entityManagerReplica.createQuery(hql1);
		
		if(fechaMin !=null && fechaMax!=null) {
			query.setParameter("fechapagoMin", fechaMin);
	        query.setParameter("fechapagoMax", fechaMax);
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			query.setParameter("ruc", ruc);
		}
		
		if(! (planta.equals("todos")|| planta.equals("")  || planta.equals("null")) ) {
			query.setParameter("planta_key", planta);
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("")  || formaPago.equals("null")) ) {
			query.setParameter("forma_pago", formaPago);
		}
		
		
		return query.getResultList();
	}
	
	
	public List<Inspeccion> getAllInspeccionesByPlanta(String planta){
		POSICION posicion = POSICION.CONSOLIDADO;
		String hql = "SELECT ins FROM Inspeccion ins"
				+ " inner join ins.inspeccionestado ie"
				+ " WHERE ie.key = 'PROCESO' and"
				+ " ins.posicion <> :posicion and"
				+ " ins.nrodocumentoinspeccion like :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("posicion", posicion);
		query.setParameter("planta", "INS-"+planta+"-%");
		return query.getResultList();
	}
	
	public Inspeccion getAllInspeccionesByPlantaAndPlaca(String planta,String placa){
		POSICION posicion = POSICION.CONSOLIDADO;
		String hql = "SELECT ins FROM Inspeccion ins"
				+ " inner join ins.inspeccionestado ie"
				+ " inner join ins.comprobantes com "
				+ " WHERE ie.key = 'PROCESO' and"
				+ " ins.posicion <> :posicion and"
				+ " ins.nrodocumentoinspeccion like :planta"
				+ " and com.placaMotor = :placa ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("posicion", posicion);
		query.setParameter("planta", "INS-"+planta+"-%");
		query.setParameter("placa", placa);
		query.setFirstResult(0);
    	query.setMaxResults(1);
		
		try {
			
			return (Inspeccion)query.getSingleResult();
		
		   }catch(NoResultException e)
	       {
				e.printStackTrace();
	       	return null;
	       	
	       }catch(Exception e)
	       {
	       	e.printStackTrace();
	       	return null;
	       }
	}
	
	
	/*
	public List<Inspeccion> getNotSendedToOffisis() {
		return inspeccionRepository.findBySendedtooffisis(false);
	}*/
	
	
    public  Inspeccion anularInspeccion(Inspeccion inspeccion,
			String observacionRetirado) {

		if (inspeccion.getLastComprobante() != null) {
			inspeccion.getLastComprobante().setObservacionDevolucion(observacionRetirado);
		}
		
		Inspeccionestado inspeccionestadoAnulado = (Inspeccionestado) inspeccionEstadoRepository
		.findOneByKey("ANU");
		
		Comprobanteestado comprobanteEstadoAnulado = (Comprobanteestado) comprobanteEstadoRepository
		.findOneByKey("ANU");
		
		//solo los comprobantes mayores a cero tienen estado o que sean cortesias, y al anular se le setea a cero
		if(inspeccion.getLastComprobante().getImportetotal()>0 || inspeccion.getLastComprobante().getTipodescuento().getKey().toLowerCase().equals("corte"))
		{
			Comprobanteestado comprobanteestadoANU = (Comprobanteestado) comprobanteEstadoRepository
			.findOneByKey("ANU");
			inspeccion.getLastComprobante().setComprobanteestado(comprobanteestadoANU);
			inspeccion.getLastComprobante().setImportetotal(0);
			inspeccion.getLastComprobante().setIgv(0);
			inspeccion.getLastComprobante().setBaseimponible(0);
			}else{
			inspeccion.getLastComprobante().setComprobanteestado(null);
		}
		
		inspeccion.setFechanulacion(new Timestamp(new Date().getTime()));
		inspeccion.getLastComprobante().setFechanulacion(new Timestamp(new Date().getTime()));
		inspeccion.setInspeccionestado(inspeccionestadoAnulado);
		
		if (inspeccion.getLastComprobante() != null) {
			inspeccion.getLastComprobante().setComprobanteestado(comprobanteEstadoAnulado);
		}
		
		return inspeccion;
	}
    
    public synchronized  Inspeccion retirarInspeccion(String planta, Inspeccion inspeccion,
            String observacionRetirado) {

		SeriedocumentoBase seriedocumentoBase = (SeriedocumentoBase) maestroService.getSeriedocumentoBaseByPlanta(planta);
		Date fechactual= new Date();

		SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA);
		
		if (seriedocumentoBase == null) {
			
		} else {
			
			Inspeccionestado inspeccionestadoRetirado = inspeccionEstadoRepository
			.findOneByKey("RETIRADO");
			inspeccion.setObservacionRetirado(observacionRetirado);
			inspeccion.setInspeccionestado(inspeccionestadoRetirado);
			inspeccion.setResultado("");
			if(fechactual!=null){
				inspeccion.setFechconsolidado(new Timestamp(fechactual.getTime()));
			}else{
				fechactual= new Date();
				inspeccion.setFechconsolidado(new Timestamp(fechactual.getTime()));
			}
		}
		return inspeccion;
	}
    
    public List<inspeccionMtcBean> getAllByRangeInspeccion(String inspeccionini, String inspeccionfin) throws ParseException{
    	List<inspeccionMtcBean> lstResult = new ArrayList<>();
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_MEDIUM);
    	List<Object> result = new ArrayList<>();
    	StringBuffer hql = new StringBuffer("select ")
    			.append("com.placamotor as placa,cat.keymtc as categoria,tce.keymtc as tipocer,tau.keymtc as tipoaut,tin.keymtc as tipoins,cer.nrodocumentocertificado,")
    			.append("ins.fechconsolidado,ins.fechvencimiento,com.inspeccion_nrodocumentoinspeccion,veh.nrosoat,veh.fechiniciotarjetapropiedad,veh.fechfintarjetapropiedad,")
    			.append("ase.keymtc,ase.tipopoliza_key ")
    			.append("from comprobante com ")
    			.append("inner join certificado cer on com.inspeccion_nrodocumentoinspeccion=cer.inspeccion_nrodocumentoinspeccion ")
    			.append("inner join linea lin on com.linea_key=lin.key ")
    			.append("inner join planta pla on lin.planta_key=pla.key ")
    			.append("inner join inspeccion ins on com.inspeccion_nrodocumentoinspeccion=ins.nrodocumentoinspeccion ")
    			.append("inner join vehiculo veh on ins.vehiculo_nromotor=veh.nromotor ")
    			.append("inner join categoria cat on veh.categoria_key=cat.key ")
    			.append("inner join tipocertificado tce on ins.tipocertificado_key=tce.key ")
    			.append("inner join tipoautorizacion tau on ins.tipoautorizacion_key=tau.key ")
    			.append("inner join tipoinspeccion tin on ins.tipoinspeccion_key=tin.key ")
    			.append("inner join aseguradora ase on aseguradora_key  = ase.key ")
    			.append("where ins.nrodocumentoinspeccion between '"+inspeccionini+"' and '"+inspeccionfin+"' ")
    			.append("and cer.nrodocumentocertificado like 'DG%' and ins.inspeccionestado_key = 'CON'");
    	Query query = entityManager.createNativeQuery(hql.toString());
    	result = (List<Object>) query.getResultList();  
    	
    	Iterator<Object> itr = result.iterator(); 
    	while(itr.hasNext()){
    		inspeccionMtcBean bean = new inspeccionMtcBean();
        	Object[] obj = (Object[]) itr.next(); 
        	bean.setPlaca(obj[0].toString());
        	bean.setCategoriaKeyMtc(Integer.valueOf(obj[1].toString()));
        	bean.setTipocertifiadoKeyMtc(Integer.valueOf(obj[2].toString()));
        	bean.setTipoautorizacionKeyMtc(Integer.valueOf(obj[3].toString()));
        	bean.setTipoinspeciconKeyMtc(Integer.valueOf(obj[4].toString()));
        	bean.setResultado("2");
        	bean.setNroCertificado(obj[5].toString());
        	bean.setFechaEmisionCert(String.valueOf(obj[6].toString()));
        	bean.setFechaVencimiento(String.valueOf(obj[7].toString()));
        	bean.setNroinspeccion(obj[8].toString());
        	bean.setNroPoliza(obj[9].toString());
        	bean.setFechaIniPoliza(String.valueOf(obj[10].toString()));
        	bean.setFechaFinPoliza(String.valueOf(obj[11].toString()));
        	bean.setAseguradoraKeyMtc(Integer.valueOf(obj[12].toString()));
        	bean.setTipopolizaKeyMtc(obj[13].toString());
        	lstResult.add(bean);
    	}
    	
    	return lstResult;
    }
    
    public void sync(Inspeccion inspeccion) throws Exception {
    	if(inspeccion.getComprobantes().size()==0 || inspeccion.getComprobantes() == null){
			throw new Exception("inspeccion sin Comprobante - " + inspeccion.getNrodocumentoinspeccion());
		}
		
		inspeccion.getResultadosMaquina().forEach(bean -> bean.setId(null));
		//recorremos el acceso del certificado
		if(inspeccion.getCertificados()!=null) {
			for(Certificado certificado : inspeccion.getCertificados()) {
				certificado.setInspeccion(inspeccion);
			}
		}
		
		
		for(Comprobante comprobante : inspeccion.getComprobantes()) {
			comprobante.setInspeccion(inspeccion);
			comprobante.setId(null);
			if(comprobante.getCliente() !=null) personaService.savePersona(comprobante.getCliente());
			if(comprobante.getDescuentoComprobante() != null) comprobante.getDescuentoComprobante().setId(null);
		}
		if(inspeccion.getVehiculo() != null) {
			vehiculoService.sync(inspeccion.getVehiculo());
		}
		inspeccionRepository.save(inspeccion);
    }

	public List<Inspeccion> getReinspeccionLastByPlacaAndConceptoAndPlanta(String placa, String concepto,
			String planta) {
		List<Inspeccion> resultado = inspeccionRepository.searchByPlacaComprobanteandConceptoandPlanta(placa, concepto, planta);
		Timestamp datefirst = new Timestamp(System.currentTimeMillis()); 
		if(resultado.size()>1) {
			List<Inspeccion> resultado2=inspeccionRepository.searchByPlacaComprobanteandConceptoandPlantaAntigua(placa, concepto, planta);
			for(Inspeccion i :resultado2) {
				datefirst= i.getFechcreacion();
				break;
			}
		}
		SimpleDateFormat df= new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
		Date fechaactual= new Date();
		for(Inspeccion i : resultado)
		{
			if(i.getResultado()!=null)
			{
				int dias= UIUtils.getDiasTranscurridos(df.format(datefirst),df.format(fechaactual));

				if(dias<=30){
					if(i.getInspeccionestado().getKey().equals("RETIRADO") || i.getResultado().toUpperCase().equals("D"))
					{
						List<Inspeccion> enviar = new ArrayList<>();
						enviar.add(i);
						return enviar;
					}else{
						return null;
					}
				}
			}
		}
		
		return null;
		
	}
    
}
