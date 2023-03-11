package com.farenet.nodo.maestro.api.sunat.services;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.xml.namespace.QName;

import cl.dbnet.carga.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Moneda;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumentoidentidad;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoOT;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoBaseRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoOTRepository;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Carga;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Comprobantes;
import com.farenet.nodo.maestro.api.sunat.domain.ST_NotaDeCredito;
import com.farenet.nodo.maestro.api.sunat.repository.ST_ComprobantesRepository;
import com.farenet.nodo.maestro.api.sunat.repository.ST_NotaDeCreditoRepository;
import com.farenet.nodo.maestro.api.sunat.repository.ST_MensajeRepository;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.NumberToLetter;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

import cl.dbnet.pdf.ConsultaDocumentosPeru;
import cl.dbnet.pdf.ConsultaDocumentosPeruSoap;

@Service
@Transactional
public class ST_Service {
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private SeriedocumentoBaseRepository seriedocumentoBaseRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ST_CargaService st_CargaService;
	
	@Autowired 
	private ST_MensajeService st_MensajeService;
	
	@Autowired
	private ST_MensajeRepository st_mensajerepository;
	
	@Autowired
	private SeriedocumentoOTRepository seriedocumentoOTRepository;
	
	@Autowired
	private ST_ComprobantesRepository st_ComprobantesRepository;
	
	@Autowired
	private ST_NotaDeCreditoRepository st_NotaDeCreditoRepository;
	
	@Autowired
	private TriggerTaskFactory trigger;
	
	@Autowired
	private InspeccionRepository inspeccionRepository;
	
	@Autowired
	private BajaComprobantesManualSUNATService bajaComprobantesSunatManual;
	
	private static final QName SERVICE_NAME = new QName("http://www.dbnet.cl", "CustomerETDLoadASP");
	private static final QName SERVICE_NAME_PDF = new QName("http://www.dbnet.cl", "consultaDocumentosPeru");

	
	public String CargaMasivaNotadeCredito(List<ST_NotaDeCredito> creditoBeans) {
		ST_Carga st_Carga = new ST_Carga();
		String mensaje = "";
		List<ST_Mensaje> lstMensajes = new ArrayList<>();
		Integer dok = 0;
		String error = "";
		for(ST_NotaDeCredito bean : creditoBeans) {
			ST_Mensaje st_Mensaje = new ST_Mensaje();
			//validacion si ya fue creado el nro de ref
			if(notaDeCreditoEmitida(bean.getSerieRef(), bean.getNroRucEmpresa(), bean.getCorrelativoRef())){
				st_Mensaje = envioNotaCredito(bean);
				bean.setNrodocumentoinspeccion(st_Mensaje.getNrodocumentoinspeccion());
				lstMensajes.add(st_Mensaje);
				if(st_Mensaje != null && st_Mensaje.getCodigo().equals("DOK")) {
					dok++;
				}else {
					error += bean.getSerieRef()+"-"+bean.getCorrelativoRef();
				}
			}else{
				error += bean.getSerieRef()+"-"+bean.getCorrelativoRef();
			}
		}
		mensaje = "Se cargaron "+ dok +" comprobantes - " ;
		if(!error.isEmpty()) mensaje += " tuvieron error con los comprobantes "+ error ;
		st_Carga.setUsuario(Util.getUserNameContext());
		String[] planta = creditoBeans.get(0).getNrodocumentoinspeccion().split("-");
		st_Carga.setPlanta(planta[1]);
		st_Carga.setSt_Mensajes(lstMensajes);
		creditoBeans.forEach(bean -> bean.setSt_carga(st_Carga));
		st_Carga.setSt_NotaDeCreditos(creditoBeans);
		st_Carga.setTipoCarga("CARGA_NOTACREDITO");
		st_CargaService.guardar(st_Carga);
		return mensaje;
	}
	
	public String cargaMasivaComprobante (List<ST_Comprobantes> boletaFacturaBeans) {
		ST_Carga st_Carga = new ST_Carga();
		List<ST_Mensaje> lstMensajes = new ArrayList<>();
		Integer dok = 0;
		String error = "", mensaje = "";
		for(ST_Comprobantes bean : boletaFacturaBeans) {
			ST_Mensaje st_Mensaje = new ST_Mensaje();
			st_Mensaje = envioBoletaFactura(bean);
			lstMensajes.add(st_Mensaje);
			if(st_Mensaje != null && st_Mensaje.getCodigo().equals("DOK")) {
				
			} else {
				error = bean.getSerie();
			}
			String[] correlativo = st_Mensaje.getNrocomprobante().split("-");
			bean.setCorrelativo(Long.valueOf(correlativo[1]));
		}
		mensaje = "Se cargaron "+ lstMensajes.size() +" comprobantes - " ;
		if(!error.isEmpty()) mensaje += " tuvieron error "+ error ;
		st_Carga.setUsuario(Util.getUserNameContext());
		st_Carga.setPlanta(boletaFacturaBeans.get(0).getPlanta());
		st_Carga.setSt_Mensajes(lstMensajes);
		boletaFacturaBeans.forEach(bean -> bean.setSt_carga(st_Carga));
		boletaFacturaBeans.forEach(bean ->{
			if (bean.getFormapago().equalsIgnoreCase("CREDITO")) {
				bean.setComprobanteestado("PEN");
			} else {
				bean.setComprobanteestado("CAN");
			}
		});
		st_Carga.setSt_Comprobantes(boletaFacturaBeans);
		st_Carga.setTipoCarga("CARGA_COMPROBANTE");
		st_CargaService.guardar(st_Carga);
		return mensaje;
	}
	
	private ST_Mensaje envioBoletaFactura(ST_Comprobantes boletaFacturaBean) {
		
		ST_Mensaje st_Mensaje = null;
		Double baseImponible = UIUtils.calcularBaseImponible(boletaFacturaBean.getTotal());
		Double igv = UIUtils.round(boletaFacturaBean.getTotal() - baseImponible,2);
		
		URL wsdlURL = CustomerETDLoadASP.WSDL_LOCATION;
	    CustomerETDLoadASP ss = new CustomerETDLoadASP(wsdlURL, SERVICE_NAME);
	    CustomerETDLoadASPSoap port = ss.getCustomerETDLoadASPSoap();
	    
	    Long correlativo;
	    
	    if(boletaFacturaBean.getSerie().startsWith("F")) {
	    	correlativo = maestroService.getNroNuevoFacturaManual(boletaFacturaBean.getPlanta().substring(0,boletaFacturaBean.getPlanta().indexOf("-")));
	    }else {
	    	correlativo = maestroService.getNroNuevoBoletaManual(boletaFacturaBean.getPlanta().substring(0,boletaFacturaBean.getPlanta().indexOf("-")));
	    }
	    
	    String nrocomprobante = UIUtils.formato8NroComprobante(boletaFacturaBean.getSerie(),correlativo);
	    
	    try {
	    	
	    	SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
			SimpleDateFormat dtfMiliSeconds = new SimpleDateFormat(UIUtils.FORMATO_HORA_WITH_MILISECONDS);
			
			//encabezado
			cl.dbnet.carga.Encabezado _putCustomerETDLoad_encabezado = new Encabezado();
			CamposHead camposEncabezado = new CamposHead();
			
			Tipodocumentoidentidad tipodocumentoidentidad = null;
						
			camposEncabezado.setTipoDTE(boletaFacturaBean.getTipo().substring(0, boletaFacturaBean.getTipo().indexOf("-")));
			 
			if(boletaFacturaBean.getNrodocumentoidentidad().length() == 11) {
				camposEncabezado.setSerie(boletaFacturaBean.getSerie());
				tipodocumentoidentidad = maestroService.getTipodocuementoIdentidadByKey("ruc");
			}else {
				camposEncabezado.setSerie(boletaFacturaBean.getSerie());
				tipodocumentoidentidad = maestroService.getTipodocuementoIdentidadByKey("dni");
			}
			 
			Moneda moneda = maestroService.getMonedaByKey("sol");
			Empresa empresa = maestroService.getEmpresaByRuc(boletaFacturaBean.getRucEmpresa());
			 
			camposEncabezado.setCorrelativo(UIUtils.leadingZeros(correlativo, UIUtils.CEROS_8IZQUIERDA));
			camposEncabezado.setFchEmis(dtf.format(new Date()));
			camposEncabezado.setTipoMoneda("PEN");
			camposEncabezado.setRUTEmis(boletaFacturaBean.getRucEmpresa());
			camposEncabezado.setTipoRucEmis("6");
			camposEncabezado.setRznSocEmis(empresa.getNombre());
			camposEncabezado.setDirEmis(empresa.getDireccion());
			camposEncabezado.setComuEmis("150131"); //ubigeo de dasso
			camposEncabezado.setRUTRecep(boletaFacturaBean.getNrodocumentoidentidad().trim());
			camposEncabezado.setTipoRutReceptor(tipodocumentoidentidad.getCodigosunat().trim());
			camposEncabezado.setRznSocRecep(boletaFacturaBean.getNombreCompleto().trim());
			camposEncabezado.setDirRecep(boletaFacturaBean.getDireccion().trim());
			if(nrocomprobante.contains("FA")){
				System.out.println("Forma de pago: " +boletaFacturaBean.getFormapago());
				
				if(boletaFacturaBean.getFormapago().equals("CONTADO")){
					camposEncabezado.setFormaPago("Contado");
				}else if(boletaFacturaBean.getFormapago().equals("CREDITO")|| boletaFacturaBean.getFormapago().equals("ADELANTADO")){
					camposEncabezado.setFormaPago("Credito");
					//camposEncabezado.setMontoNetoPendPago(Double.toString(UIUtils.round(MontoTotal, 2)));
					camposEncabezado.setMontoNetoPendPago(Double.toString(UIUtils.round(boletaFacturaBean.getTotal(), 2)));
				}
			}
			camposEncabezado.setMntExe("0.00");
			camposEncabezado.setMntExo("0.00");
			camposEncabezado.setMntTotBoni("0.00");
			camposEncabezado.setMntTotAnticipo("0.00");
			camposEncabezado.setHoraEmision(dtfMiliSeconds.format(new Date()));
			//camposEncabezado.setNumPlacaVehi(comprobante.getPlacaMotor());
			
			//para gratuita
			camposEncabezado.setMntTotGrat("0.00");
			camposEncabezado.setMntTotal(Double.toString(boletaFacturaBean.getTotal()));
			camposEncabezado.setMntNeto(Double.toString(baseImponible));
			 
			_putCustomerETDLoad_encabezado.setCamposEncabezado(camposEncabezado);
			 
			ArrayOfImptoReten arrayImptoReten = new ArrayOfImptoReten();
			ImptoReten imptoReten = new ImptoReten();
			imptoReten.setCodigoImpuesto("1000"); 
			imptoReten.setTasaImpuesto("18");
			imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(igv,2)));
			imptoReten.setMontoImpuestoBase(Double.toString(baseImponible));
			 
			arrayImptoReten.getImptoReten().add(imptoReten);
			_putCustomerETDLoad_encabezado.setImptoReten(arrayImptoReten);
			 
			 
			 
			//detalles
			ArrayOfDetalle _putCustomerETDLoad_detalles = new ArrayOfDetalle();
			 
			Detalle detalle = new Detalle();
			CamposDetalle campoDetalle = new CamposDetalle();
			campoDetalle.setNroLinea(1);
			campoDetalle.setNroLinDet("1");
			campoDetalle.setQtyItem("1");
			campoDetalle.setUnmdItem("NIU");
			campoDetalle.setVlrCodigo("S001");
			campoDetalle.setNmbItem(boletaFacturaBean.getDescripcionServ());
			campoDetalle.setPrcItem(Double.toString(boletaFacturaBean.getTotal() ));
			campoDetalle.setMontoItem(Double.toString(baseImponible));
			campoDetalle.setPrcItemSinIgv(Double.toString(baseImponible));
			campoDetalle.setDescuentoMonto("0.00");
			campoDetalle.setMontoItem(Double.toString(baseImponible));
			campoDetalle.setIndExe("10");
			campoDetalle.setCodigoTipoIgv("1000"); 
			campoDetalle.setTasaIgv("18");
			campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(igv,2)));
			detalle.setDetalles(campoDetalle);
			 
			_putCustomerETDLoad_detalles.getDetalle().add(detalle);
			 
			//descuentos regargos y otros
			DescuentosRecargosyOtros _putCustomerETDLoad_descuentosRecargosyOtros = new DescuentosRecargosyOtros();
			ArrayOfDatosAdicionales datosAdicionales = new ArrayOfDatosAdicionales();
			 
			//nro de informe
			DatosAdicionales adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("2");
			adicional.setNmrLineasAdicSunat("2");
			adicional.setDescripcionAdicsunat("-");
			datosAdicionales.getDatosAdicionales().add(adicional);
			 
			//marca del auto
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("5");
			adicional.setNmrLineasAdicSunat("5");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getMarca());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("7");
			adicional.setNmrLineasAdicSunat("7");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getTipoVehiculo());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//nro motor
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("8");
			adicional.setNmrLineasAdicSunat("8");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getNroMotor());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//modelo
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("6");
			adicional.setNmrLineasAdicSunat("6");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getModelo());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			// get tipo de pago (credito / contado)
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("4");
			adicional.setNmrLineasAdicSunat("4");

			if (boletaFacturaBean.getFormapago().equalsIgnoreCase("CREDITO")) {
				adicional.setDescripcionAdicsunat("Credito");
			} else {
				adicional.setDescripcionAdicsunat("Contado");
			}
			
			//get telefono
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("9");
			adicional.setNmrLineasAdicSunat("9");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getTelefono());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//placa
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("15");
			adicional.setNmrLineasAdicSunat("15");
			adicional.setDescripcionAdicsunat(boletaFacturaBean.getPlaca());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			Planta planta = maestroService.getPlantaByKey(boletaFacturaBean.getPlanta().substring(0, boletaFacturaBean.getPlanta().indexOf("-")));
			
			//planta
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("10");
			adicional.setNmrLineasAdicSunat("10");
			adicional.setDescripcionAdicsunat(planta.getNombre());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//direccion planta
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("11");
			adicional.setNmrLineasAdicSunat("11");
			adicional.setDescripcionAdicsunat(planta.getDireccion());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//monto a texto
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("1");
			adicional.setNmrLineasAdicSunat("1");
			adicional.setDescripcionAdicsunat(NumberToLetter.convertNumberToLetter(boletaFacturaBean.getTotal()));
			datosAdicionales.getDatosAdicionales().add(adicional);
			_putCustomerETDLoad_descuentosRecargosyOtros.setDatosAdicionales(datosAdicionales);
			
	        Extras _putCustomerETDLoad_extras = new Extras() ;


			Calendar cal = Calendar.getInstance();
			if(nrocomprobante.contains("FA")) {
				if (boletaFacturaBean.getFormapago().equals("CREDITO")) {
					cal.setTimeInMillis(boletaFacturaBean.getFechcreacion().getTime());
					cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(30));
					ArrayOfPago pagos = new ArrayOfPago();
					Pago pago= new Pago();
					pago.setCuota("1");
					pago.setFechaVencCuota(String.valueOf(dtf.format(cal.getTime().getTime())));
					//pago.setMontoCuota(Double.toString(UIUtils.round(MontoTotal, 2)));
					pago.setMontoCuota(Double.toString(UIUtils.round(boletaFacturaBean.getTotal(), 2)));
					pagos.getPago().add(pago);
					_putCustomerETDLoad_descuentosRecargosyOtros.setPagos(pagos);
				}
			}

	        ArrayOfEnvioPdf arrayOfEnvioPdf = new ArrayOfEnvioPdf();
	        EnvioPdf envioPdf = new EnvioPdf();
	        
	        CamposEnvioPdf camposEnvioPdf = new CamposEnvioPdf();
	        camposEnvioPdf.setMailEnvi(boletaFacturaBean.getEmail());
	        
	        envioPdf.setCamposEnvioPdf(camposEnvioPdf);
	        arrayOfEnvioPdf.getEnvioPdf().add(envioPdf);
	        _putCustomerETDLoad_extras.setEnvioPdf(arrayOfEnvioPdf);
	        
	        Mensaje _putCustomerETDLoad__return = port.putCustomerETDLoad(_putCustomerETDLoad_extras, _putCustomerETDLoad_encabezado, _putCustomerETDLoad_detalles, _putCustomerETDLoad_descuentosRecargosyOtros);
	         String pdf = "";
	        //obtenemos el pdf
	 		pdf = getPdf(boletaFacturaBean.getRucEmpresa(), 
	 				nrocomprobante, 
	 				boletaFacturaBean.getTipo().substring(0, boletaFacturaBean.getTipo().indexOf("-")), 
	 				new Date(),
	 				boletaFacturaBean.getTotal(), 
	 				false);
	        
	        //grabando en el st_mensaje
	        st_Mensaje = new ST_Mensaje("ALTA_MANUAL",_putCustomerETDLoad__return, null, nrocomprobante,pdf);
			st_MensajeService.guardar(st_Mensaje);
			if(_putCustomerETDLoad__return.getCodigo().equals("DOK")) {
				if(boletaFacturaBean.getSerie().startsWith("F")) {
			    	maestroService.nextNumeroFacturaManual(planta.getKey());
			    }else {
			    	maestroService.nextNumeroBoletaManual(planta.getKey());
			    }
			}
		} catch(Exception e) {
			trigger.launch("", "enviar.comprobante.carga.sunat.error", nrocomprobante ,  Util.printError(e));
			e.printStackTrace();
		}
	    
	    return st_Mensaje;
	}
	
	public String getNroComprobanteByInspeccion(String nrodocumentoinspeccion, Long id) {
		String hql = "SELECT st.nrocomprobante from ST_Mensaje st where st.nrodocumentoinspeccion = :nrodocumentoinspeccion order by st.fechaCreacion desc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nrodocumentoinspeccion", nrodocumentoinspeccion);
		query.setFirstResult(0);
		query.setMaxResults(1);
		String result = query.getSingleResult().toString();
		
		if(result != null && !result.isEmpty()) {
			String hqlSum = "update Comprobante " + "set nrocomprobante = :nrocomprobante WHERE id = :id ";
			Query query2 = entityManager.createQuery(hqlSum);
			query2.setParameter("nrocomprobante", result);
			query2.setParameter("id", id);
			query2.executeUpdate();
		}
		
		return result;
	}
	
	private Boolean notaDeCreditoEmitida(String serieRef, String ruc, Long correlativoRef){
		
		try{
			String hql = "SELECT nc from ST_NotaDeCredito nc where serieRef =:serie and nroRucEmpresa =:ruc and correlativoRef = :correlativo";
			Query query = entityManager.createQuery(hql);
			query.setParameter("serie", serieRef);
			query.setParameter("ruc", ruc);
			query.setParameter("correlativo", correlativoRef);
			query.setFirstResult(0);
			query.setMaxResults(1);
			ST_NotaDeCredito credito = (ST_NotaDeCredito) query.getSingleResult();
			return false;
		}catch (NoResultException e) {
			// TODO: handle exception
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return true;
		}
	}
	
	private ST_Mensaje envioNotaCredito(ST_NotaDeCredito notaCreditoBean) {
        
		Double baseImponible = UIUtils.calcularBaseImponible(notaCreditoBean.getImporteNC());
		Double igv = notaCreditoBean.getImporteNC() - baseImponible;
		
		Inspeccion inspeccion = null;
		ST_Mensaje st_Mensaje = null;
		Planta planta = null;
		SeriedocumentoBase seriedocumentoBase = null;
		
        String nrocomprobanteRef = UIUtils.formato8NroComprobante(notaCreditoBean.getSerieRef(),notaCreditoBean.getCorrelativoRef());
		
		//parametro de wsdl
		URL wsdlURL = CustomerETDLoadASP.WSDL_LOCATION;
	    CustomerETDLoadASP ss = new CustomerETDLoadASP(wsdlURL, SERVICE_NAME);
	    CustomerETDLoadASPSoap port = ss.getCustomerETDLoadASPSoap();
        
        try{
			
        	String hql1 = "SELECT ins FROM Comprobante com "
					+ "inner join com.empresa emp "
					+ "inner join com.inspeccion ins "
					+ "WHERE com.nrocomprobante = :nrocomprobante and emp.ruc = :ruc";
			Query query = entityManager.createQuery(hql1);
			query.setParameter("nrocomprobante", nrocomprobanteRef);
			query.setParameter("ruc", notaCreditoBean.getNroRucEmpresa());
			
			inspeccion = (Inspeccion)query.getSingleResult();
	        
		}catch(NoResultException e) {
			trigger.launch("", "enviar.notacredito.carga.sunat.error", "No existe inspeccion " + nrocomprobanteRef,  Util.printError(e));
	        e.printStackTrace();
        }catch(Exception e) {
        	trigger.launch("", "enviar.notacredito.carga.sunat.error", "Error inspeccion " + nrocomprobanteRef ,  Util.printError(e));
	        e.printStackTrace();
        }
        
        if(inspeccion==null)
			return null;
		
		if(inspeccion.getComprobantes() == null) 
			return null;
		
		try {
			
			Comprobante comprobante = inspeccion.getLastComprobante();
			
			
			
			SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
			SimpleDateFormat dtfMiliSeconds = new SimpleDateFormat(UIUtils.FORMATO_HORA_WITH_MILISECONDS);
			SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT_HOME);
			
			seriedocumentoBase = seriedocumentoBaseRepository.findOneByEmpresaAndSerie(notaCreditoBean.getNroRucEmpresa(), notaCreditoBean.getSerieRef()); 
			planta = seriedocumentoBase.getPlanta();
			
			//encabezado
			Encabezado _putCustomerETDLoad_encabezado = new Encabezado();
			CamposHead camposEncabezado = new CamposHead();
			
			String nrocomprobante = "";
			Long correlativo = 0l;
			
			camposEncabezado.setTipoDTE("07");
				 
			if(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
				
				camposEncabezado.setSerie(seriedocumentoBase.getSerienotacreditofactura());
				correlativo = maestroService.nextIdNotaCreditoFactura(planta.getKey());
				nrocomprobante = UIUtils.formato8NroComprobante(seriedocumentoBase.getSerienotacreditofactura(),correlativo);
				
			}else {
				
				camposEncabezado.setSerie(seriedocumentoBase.getSerienotacreditoboleta());
				correlativo = maestroService.nextIdNotaCreditoBoleta(planta.getKey());
				nrocomprobante = UIUtils.formato8NroComprobante(seriedocumentoBase.getSerienotacreditoboleta(),correlativo);
				
			}
			
			camposEncabezado.setCorrelativo(UIUtils.leadingZeros(correlativo, UIUtils.CEROS_8IZQUIERDA));
			camposEncabezado.setFchEmis(dtf.format(new Date()));
			camposEncabezado.setTipoMoneda("PEN");
			camposEncabezado.setRUTEmis(comprobante.getEmpresa().getRuc());
			camposEncabezado.setTipoRucEmis("6");
			camposEncabezado.setRznSocEmis(comprobante.getEmpresa().getNombre());
			camposEncabezado.setDirEmis(comprobante.getEmpresa().getDireccion());
			camposEncabezado.setComuEmis("150131"); //ubigeo de dasso
			camposEncabezado.setRUTRecep(comprobante.getCliente().getNrodocumentoidentidad().trim());
			camposEncabezado.setTipoRutReceptor(comprobante.getCliente().getTipodocumentoidentidad().getCodigosunat());
			camposEncabezado.setRznSocRecep(comprobante.getCliente().getNombrecompleto().trim());
			camposEncabezado.setDirRecep(comprobante.getCliente().getDireccion().trim() + " " + comprobante.getCliente().getDistrito().getNombre().trim() + " " + comprobante.getCliente().getProvincia().getNombre().trim());
			camposEncabezado.setSustento(notaCreditoBean.getSustento());
			camposEncabezado.setTipoNotaCredito(notaCreditoBean.getTipo());
			camposEncabezado.setMntExe("0.00");
			camposEncabezado.setMntExo("0.00");
			camposEncabezado.setMntTotBoni("0.00");
			camposEncabezado.setMntTotAnticipo("0.00");
			camposEncabezado.setHoraEmision(dtfMiliSeconds.format(new Date()));
			camposEncabezado.setMntTotGrat("0.00");
			camposEncabezado.setMntNeto(Double.toString(baseImponible));
			camposEncabezado.setMntTotal(Double.toString(notaCreditoBean.getImporteNC()));
			
			_putCustomerETDLoad_encabezado.setCamposEncabezado(camposEncabezado);
			
			//no se envia si es gratuito
			ArrayOfImptoReten arrayImptoReten = new ArrayOfImptoReten();
			
			ImptoReten imptoReten = new ImptoReten();
			imptoReten.setCodigoImpuesto("1000"); 
			imptoReten.setTasaImpuesto("18");
			imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(igv, 2)));
			imptoReten.setMontoImpuestoBase(Double.toString(baseImponible));
					 
			arrayImptoReten.getImptoReten().add(imptoReten);
				
			_putCustomerETDLoad_encabezado.setImptoReten(arrayImptoReten);
				 
			//detalles
			ArrayOfDetalle _putCustomerETDLoad_detalles = new ArrayOfDetalle();
				 
			Detalle detalle = new Detalle();
			CamposDetalle campoDetalle = new CamposDetalle();
			campoDetalle.setNroLinea(1);
			campoDetalle.setNroLinDet("1");
			campoDetalle.setQtyItem("1");
			campoDetalle.setUnmdItem("NIU");
			campoDetalle.setVlrCodigo(comprobante.getConceptoInspeccion().getKey());
			campoDetalle.setNmbItem(comprobante.getConceptoInspeccion().getNombre());
			campoDetalle.setPrcItem(Double.toString(notaCreditoBean.getImporteNC()));
			campoDetalle.setMontoItem(Double.toString(baseImponible));
			campoDetalle.setPrcItemSinIgv(Double.toString(baseImponible));
			campoDetalle.setDescuentoMonto("0.00");
			campoDetalle.setIndExe("10");
			campoDetalle.setCodigoTipoIgv("1000");
			campoDetalle.setTasaIgv("18");
			campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(igv,2)));
			detalle.setDetalles(campoDetalle);
			
			_putCustomerETDLoad_detalles.getDetalle().add(detalle);
			
			//descuentos regargos y otros
			DescuentosRecargosyOtros _putCustomerETDLoad_descuentosRecargosyOtros = new DescuentosRecargosyOtros();
			ArrayOfDatosAdicionales datosAdicionales = new ArrayOfDatosAdicionales();
				 
			
			DatosAdicionales adicional = new DatosAdicionales();
			
			//telefono cliente
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("9");
			adicional.setNmrLineasAdicSunat("9");
			adicional.setDescripcionAdicsunat(comprobante.getCliente().getTelefono());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//planta
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("10");
			adicional.setNmrLineasAdicSunat("10");
			adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getNombre());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//direccion planta
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("11");
			adicional.setNmrLineasAdicSunat("11");
			adicional.setDescripcionAdicsunat(planta.getDireccion());
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//monto a texto
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("1");
			adicional.setNmrLineasAdicSunat("1");
			adicional.setDescripcionAdicsunat(NumberToLetter.convertNumberToLetter(notaCreditoBean.getImporteNC()));
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			//fecha comprobante referencia
			adicional = new DatosAdicionales();
			adicional.setTipoAdicSunat("01");
			adicional.setNmrLineasDetalle("12");
			adicional.setNmrLineasAdicSunat("12");
			adicional.setDescripcionAdicsunat(dtfPeru.format(comprobante.getFechapago()));
			datosAdicionales.getDatosAdicionales().add(adicional);
			
			_putCustomerETDLoad_descuentosRecargosyOtros.setDatosAdicionales(datosAdicionales);
			
			ArrayOfReferencias arrayOfReferencias = new ArrayOfReferencias();
			
			Referencias referencias = new Referencias();
			referencias.setNroLinRef("1");
			 if(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
				 referencias.setTpoDocRef("01");
			 }else {
				 referencias.setTpoDocRef("03");
			 }
			 referencias.setSerieRef(notaCreditoBean.getSerieRef());
			 referencias.setFolioRef(UIUtils.leadingZeros(notaCreditoBean.getCorrelativoRef(), UIUtils.CEROS_8IZQUIERDA));
			
			arrayOfReferencias.getReferencias().add(referencias);
			_putCustomerETDLoad_descuentosRecargosyOtros.setReferencias(arrayOfReferencias);
			
			Extras _putCustomerETDLoad_extras = new Extras() ;
			
			Mensaje _putCustomerETDLoad__return = port.putCustomerETDLoad(_putCustomerETDLoad_extras, _putCustomerETDLoad_encabezado, _putCustomerETDLoad_detalles, _putCustomerETDLoad_descuentosRecargosyOtros);
			
			//grabando en el st_mensaje
			st_Mensaje = new ST_Mensaje("NOTA_MASIVO",_putCustomerETDLoad__return, inspeccion.getNrodocumentoinspeccion(), nrocomprobante);
			st_MensajeService.guardar(st_Mensaje);
		        
		}catch(Exception e) {
			trigger.launch("", "enviar.notacredito.carga.sunat.error", inspeccion.getNrodocumentoinspeccion() ,  Util.printError(e));
			 e.printStackTrace();
		}
		
		return st_Mensaje;
		
	}
	
	public String getPDFMensajesBycomprobanteAndPlanta(String nrocomprobante, String planta)
	{
		String hql ="SELECT mensaje FROM ST_Mensaje mensaje WHERE nrocomprobante = :nrocomprobante and nrodocumentoinspeccion like :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nrocomprobante", nrocomprobante);
		query.setParameter("planta", "INS-"+planta+"-%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		ST_Mensaje mensaje = (ST_Mensaje) query.getSingleResult();
		
		if(mensaje==null)
			return null;
		
		return mensaje.getPdf();
	}
	
	public String getPDFMensajes(String nrocomprobante)
	{
		ST_Mensaje mensaje = st_mensajerepository.findByNroComprobante(nrocomprobante);
		
		if(mensaje==null)
			return null;
		
		return mensaje.getPdf();
	}
	
	public List<String> getSeriesGeneradas(){
		return st_ComprobantesRepository.findAllSeries();
	}
	
	public String getUltimoCorrelativoByPlanta(String planta) {
		String hql = "SELECT distinct(serie) FROM ST_Comprobantes WHERE planta LIKE :planta ";
        Query query = entityManager.createQuery(hql);
        query.setParameter("planta", planta+"-%");
        List<String> series = query.getResultList();
        
        StringBuffer resultado = new StringBuffer();
        for(String serie : series){
        	String hql2 = "SELECT correlativo FROM ST_Comprobantes where planta LIKE :planta and serie = :serie order by correlativo desc";
            Query query2 = entityManager.createQuery(hql2);
            query2.setParameter("planta", planta+"-%");
            query2.setParameter("serie", serie);
            query2.setMaxResults(1);
            resultado.append(UIUtils.formato8NroComprobante(serie, Long.valueOf(query2.getSingleResult().toString()))).append("\n");
        }
        return resultado.toString();
	}
	
	public String getNroComprobante(String nrocomprobante, String planta) {
		try{
			String hql = "SELECT st FROM ST_Mensaje st where nrocomprobante = :nrocomprobante and nrodocumentoinspeccion like :planta";
			Query query = entityManager.createQuery(hql);
			query.setParameter("nrocomprobante", nrocomprobante);
			query.setParameter("planta", "INS-"+planta+"-%");
			query.setFirstResult(0);
			query.setMaxResults(1);
			
			ST_Mensaje st_Mensaje = (ST_Mensaje) query.getSingleResult();
			
			return st_Mensaje.getNrocomprobante();
		}catch (Exception e) {
			return null;
		}
	}
	public String getNroComprobantebyInspeccionAndComprobante(String nrocomprobante, String nroinspeccion){
		try {
			String hql ="Select st from ST_Mensaje st where nrocomprobante = :nrocomprobante and nrodocumentoinspeccion = :nroinspeccion ";
			Query query= entityManager.createQuery(hql);
			query.setParameter("nrocomprobante", nrocomprobante);
			query.setParameter("nroinspeccion",nroinspeccion);
			query.setFirstResult(0);
			query.setMaxResults(1);

			ST_Mensaje st_Mensaje = (ST_Mensaje) query.getSingleResult();

			return st_Mensaje.getNrocomprobante();
		}
		catch (Exception e){
			return null;
		}
	}
	
	public String getpdfBySerieAndCorrelativo(String nrocomprobante, String planta) {
		String pdf = null;
		try{
			String hql = "SELECT st FROM ST_Mensaje st where nrocomprobante = :nrocomprobante and nrodocumentoinspeccion like :planta order by st.fechaCreacion desc";
			Query query = entityManager.createQuery(hql);
			query.setParameter("nrocomprobante", nrocomprobante);
			query.setParameter("planta", "INS-"+planta+"-%");
			query.setFirstResult(0);
			query.setMaxResults(1);
			
			ST_Mensaje st_Mensaje = (ST_Mensaje) query.getSingleResult();
			
			if(st_Mensaje != null && st_Mensaje.getPdf() !=null && !st_Mensaje.getPdf().isEmpty()){
				pdf = st_Mensaje.getPdf();
			}else{
				Comprobante comprobante = inspeccionRepository.findByNrodocumentoinspeccion(st_Mensaje.getNrodocumentoinspeccion()).getLastComprobante();
				pdf = getPdf(
						comprobante.getEmpresa().getRuc(),
						comprobante.getNrocomprobante(),
						(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc"))?"01":"03",
						comprobante.getFechapago(),
						comprobante.getImportetotal(), 
						comprobante.getTipodescuento()!=null?(comprobante.getTipodescuento().getKey().equals("corte")?true:false):false);
			}
			if(st_Mensaje == null && pdf == null){
				return nrocomprobante;
			}
		}catch(NoResultException e){
			return nrocomprobante;
		}
		
		
		return pdf;
	}
	
	public String getpdfBySerieAndCorrelativo(String serie, String correlativo,String planta) {
		String pdf = null;
		

		String hql = "SELECT cc FROM ST_Comprobantes cc where serie = :serie and correlativo = :correlativo and planta like :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("serie", serie);
		query.setParameter("correlativo", Long.valueOf(correlativo));
		query.setParameter("planta", planta+"-%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		ST_Comprobantes bean = (ST_Comprobantes) query.getSingleResult();
		  
		pdf = getPdf(
				bean.getRucEmpresa(), 
				UIUtils.formato8NroComprobante(bean.getSerie(), bean.getCorrelativo()), 
				bean.getTipo().substring(0, bean.getTipo().indexOf("-")), 
				bean.getFechcreacion(), 
				bean.getTotal(), 
				false);
		return pdf;
	}
	

	
	public String getpdfNotaCreditoBySerieAndCorrelativo(String serie, String correlativo, String planta) {
		String pdf = null;
		
		String hql = "SELECT cc FROM ST_NotaDeCredito cc where serieRef = :serie and correlativoRef = :correlativo and nrodocumentoinspeccion like :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("serie", serie);
		query.setParameter("correlativo", Long.valueOf(correlativo));
		query.setParameter("planta", "INS-"+planta+"-%");
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		ST_NotaDeCredito bean = (ST_NotaDeCredito) query.getSingleResult();
		
		ST_Mensaje mensaje = st_MensajeService.findByInspeccionAndTipo(bean.getNrodocumentoinspeccion());
		
		if(mensaje != null && mensaje.getPdf() != null && !mensaje.getPdf().trim().isEmpty()){
			return mensaje.getPdf();
		}
		  
		pdf = getPdf(
				bean.getNroRucEmpresa(), 
				mensaje.getNrocomprobante(), 
				"7", 
				bean.getFechcreacion(), 
				bean.getImporteNC(), 
				false);
		return pdf;
	}
	
	public void generarComprobante(String nroinspeccion, String nrocomprobante){
		Inspeccion inspeccion = inspeccionRepository.findByNrodocumentoinspeccion(nroinspeccion);
		enviaraDgf(inspeccion, nrocomprobante);
	}
	
	public void enviarFacturaConsolidadaByIdf(List<Long> ids) {
		String hql = "SELECT DISTINCT com FROM Comprobante com"
				+ " inner join com.ordenTrabajo ot"
				+ " where ot.id in (:ids)";
		Query query = entityManager.createQuery(hql);
		query.setParameter("ids", ids);
		List<Comprobante> comprobantes = query.getResultList();
		enviarFacturaConsolidadDgf(comprobantes);
		cambiarEstadoOt(ids);
	}
	
	private void cambiarEstadoOt(List<Long> ids) {
		// TODO Auto-generated method stub
		
		Comprobanteestado comprobanteestado = maestroService.getOneComprobanteestado("CAN");
		String hql = "update OrdenTrabajo set fechaestado = :fechaestado , comprobanteestado = :comprobanteestado where id in (:ids)";
		Query query = entityManager.createQuery(hql);
		query.setParameter("fechaestado", new Timestamp(new Date().getTime()));
		query.setParameter("comprobanteestado", comprobanteestado);
		query.setParameter("ids", ids);
		query.executeUpdate();
	}

	public void getNroComprobanteIns(String planta, Date fecha){
		List<String> resultado = new ArrayList<>();
		Timestamp fechaMin = new Timestamp(fecha.getTime());
        Timestamp fechaMax = new Timestamp(fecha.getTime());
		String hql = "SELECT concat(ins.nrodocumentoinspeccion,'|',com.nrocomprobante) from Comprobante com"
				+ " inner join com.inspeccion ins "
				+ " where com.nrocomprobante is not null "
				+ " and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
				+ " and ins.nrodocumentoinspeccion like :planta ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("fechaMin", fechaMin);
		query.setParameter("fechaMax", fechaMax);
		query.setParameter("planta", "INS-"+planta+"-%");
		resultado = query.getResultList();
		
		for(String bean : resultado){
			try{
				String nroinspeccion = bean.substring(0, bean.indexOf("|"));
				String nrocomprobante = bean.substring(bean.indexOf("|")+1, bean.length());
				generarComprobante(nroinspeccion, nrocomprobante);
				trigger.launch("", "enviar.alta.sunat.manual", "Se envia " , nroinspeccion +" - "+ nrocomprobante);
			}catch (Exception e) {
				// TODO: handle exception
				trigger.launch("", "enviar.alta.sunat.manual.error", "Se envia " + bean,  UIUtils.printError(e) );
			}
		}
	}
	
	 public String getPdf(String rucEmisor, String nrocomprobante, String tipoDTE, Date fecha, Double montototal, Boolean isCortesia)
	 {
		  URL wsdlURL = ConsultaDocumentosPeru.WSDL_LOCATION;
		  ConsultaDocumentosPeru ss = new ConsultaDocumentosPeru(wsdlURL, SERVICE_NAME_PDF);
	      ConsultaDocumentosPeruSoap port = ss.getConsultaDocumentosPeruSoap();  
	        
	      SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
			
	      String[] parseComprobante = nrocomprobante.split("-");
	      
	      
	      System.out.println("Invoking getDocumentoPDF...");
	        java.lang.String _getDocumentoPDF_ruttEmpr = rucEmisor;
	        java.lang.String _getDocumentoPDF_folioDTE = parseComprobante[1];
	        java.lang.String _getDocumentoPDF_tipoDTE = tipoDTE;
	        java.lang.String _getDocumentoPDF_serieInte = parseComprobante[0];
	        java.lang.String _getDocumentoPDF_fechaDTE = dtf.format(fecha);
	        java.lang.String _getDocumentoPDF_monTotal = (isCortesia)?"0.00":Double.toString(montototal );
	        cl.dbnet.pdf.ArrayOfString _getDocumentoPDF__return = port.getDocumentoPDF(_getDocumentoPDF_ruttEmpr, _getDocumentoPDF_folioDTE, _getDocumentoPDF_tipoDTE, _getDocumentoPDF_serieInte, _getDocumentoPDF_fechaDTE, _getDocumentoPDF_monTotal);
	        System.out.println("getDocumentoPDF.result=" + _getDocumentoPDF__return);

	        List<String> respuesta = _getDocumentoPDF__return.getString();
	        
	        if(respuesta.get(1)==null || respuesta.get(1).length()==0)
	        {
	        
	        		trigger.launch("", "enviar.pdf.sunat.error","Nro Comprobante : " + nrocomprobante, respuesta.get(2));
			     
	        }
	        
	        
	       return _getDocumentoPDF__return.getString().get(1);
	        
	 }
	 public String getNroCuotas(Long iddetalle){
		 String result="";
		try
		{
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT de.nrocuotas ")
			.append(" from DescuentoDetalle dd ")
			.append(" inner join dd.descuento de ")
			.append(" WHERE dd.id =: iddetalle");
			Query query = entityManager.createQuery(hql.toString());
			query.setParameter("iddetalle", iddetalle);
			result=query.getSingleResult().toString();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		 return result;
	 }
	 public String getNroDiasVence(Long iddetalle)
	 {
		 String result="";
		 try
		 {
			 StringBuilder hql = new StringBuilder();
			 hql.append(" SELECT de.diascredito ")
					 .append(" from DescuentoDetalle dd ")
					 .append(" inner join dd.descuento de ")
					 .append(" WHERE dd.id =: iddetalle");
			 Query query = entityManager.createQuery(hql.toString());
			 query.setParameter("iddetalle", iddetalle);
			 result=query.getSingleResult().toString();
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 return result;
	 }
	 private void enviaraDgf(Inspeccion inspeccion, String nrocomprobante) {
		 
		 URL wsdlURL = CustomerETDLoadASP.WSDL_LOCATION;
		 CustomerETDLoadASP ss = new CustomerETDLoadASP(wsdlURL, SERVICE_NAME);
		 CustomerETDLoadASPSoap port = ss.getCustomerETDLoadASPSoap();
		 
		 String serie = nrocomprobante.split("-")[0];
		 Long correlativo = Long.valueOf(nrocomprobante.split("-")[1]);
		 String Nrocuotas="";
		 String NroDiasVence="";
		 Calendar cal = Calendar.getInstance();
		 
		 try {
			 
			 Comprobante comprobante = inspeccion.getLastComprobante();
			 Extras _putCustomerETDLoad_extras = new Extras() ;
			 Encabezado _putCustomerETDLoad_encabezado = new Encabezado();
			 ArrayOfDetalle _putCustomerETDLoad_detalles = new ArrayOfDetalle();
			 DescuentosRecargosyOtros _putCustomerETDLoad_descuentosRecargosyOtros = new DescuentosRecargosyOtros();
			 if(comprobante.getDescuentoComprobante()!=null) {
				 if (comprobante.getDescuentoComprobante().getTipodescuento().getKey().equals("cotizacion"))
				 {
					 Nrocuotas="1";//getNroCuotas(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle())).substring(0,1);
					 NroDiasVence= "30";//getNroDiasVence(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle())).substring(0,2);
				 }else {
					 Nrocuotas="1";
					 NroDiasVence= "1";
				 }
			 }
			 try{

				 double PrcItemSinIgv=0.0;
				 double DetMontoCargoDescuento=0.0;
				 double preciounitario=0.0;
				 double MontoTotal=0.0;
				 double MontoItem=0.0;
				 double ImpuestoIgv=0.0;
				 double base=0.0;
				 double factor=0.0;
				 if(comprobante.getTotaldscto()<0){

					 PrcItemSinIgv=UIUtils.round(comprobante.getImportetotal()/1.18, 2);
					 DetMontoCargoDescuento=UIUtils.round(0, 2);
					 preciounitario=UIUtils.round((PrcItemSinIgv-1)*1.18, 2);
					 MontoItem=UIUtils.round(PrcItemSinIgv*1-DetMontoCargoDescuento, 2);
					 ImpuestoIgv=UIUtils.round(MontoItem*0.18, 2);
					 base=UIUtils.round(PrcItemSinIgv*1, 2);
					 factor=UIUtils.round(DetMontoCargoDescuento/base, 2);
					 MontoTotal=MontoItem;
				 }else{

					 PrcItemSinIgv=UIUtils.round(comprobante.getTotalsindscto()/1.18, 2);
					 DetMontoCargoDescuento=UIUtils.round(comprobante.getTotaldscto()/1.18, 2);
					 preciounitario=UIUtils.round((PrcItemSinIgv-1)*1.18, 2);
					 MontoItem=UIUtils.round(PrcItemSinIgv*1-DetMontoCargoDescuento, 2);
					 ImpuestoIgv=UIUtils.round(MontoItem*0.18, 2);
					 base=UIUtils.round(PrcItemSinIgv*1, 2);
					 factor=UIUtils.round(DetMontoCargoDescuento/base, 2);
					 MontoTotal=MontoItem;
				 }

				 if(comprobante.getFechapago()==null)comprobante.setFechapago(new Timestamp(new Date().getTime()));
				 
				 SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
				 SimpleDateFormat dtfMiliSeconds = new SimpleDateFormat(UIUtils.FORMATO_HORA_WITH_MILISECONDS);
				 
				 //encabezado
				 CamposHead camposEncabezado = new CamposHead();
				 
				 if(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
					 camposEncabezado.setTipoDTE("01");
					 camposEncabezado.setSerie(serie);
				 }else {
					 camposEncabezado.setTipoDTE("03");
					 camposEncabezado.setSerie(serie);
				 }
				 
				 camposEncabezado.setCorrelativo(UIUtils.leadingZeros(correlativo, UIUtils.CEROS_8IZQUIERDA));
				 camposEncabezado.setFchEmis(dtf.format(comprobante.getFechapago()));
				 camposEncabezado.setTipoMoneda("PEN");
				 camposEncabezado.setRUTEmis(comprobante.getEmpresa().getRuc());
				 camposEncabezado.setTipoRucEmis("6");
				 camposEncabezado.setRznSocEmis(comprobante.getEmpresa().getNombre());
				 camposEncabezado.setDirEmis(comprobante.getEmpresa().getDireccion());
				 camposEncabezado.setComuEmis("150131"); //ubigeo de dasso
				 camposEncabezado.setCodigoLocalAnexo(comprobante.getLinea().getPlanta().getCodlocalsunat());
				 if(nrocomprobante.contains("FE")){
					 if(comprobante.getComprobanteestado().getKey().equals("CAN")){
						 camposEncabezado.setFormaPago("Contado");
					 }else if(comprobante.getComprobanteestado().getKey().equals("PEN")){
						 camposEncabezado.setFormaPago("Credito");
						 //camposEncabezado.setMontoNetoPendPago(Double.toString(UIUtils.round(MontoTotal, 2)));
						 camposEncabezado.setMontoNetoPendPago(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
					 }
				 }
				 camposEncabezado.setRUTRecep(comprobante.getCliente().getNrodocumentoidentidad());
				 camposEncabezado.setTipoRutReceptor(comprobante.getCliente().getTipodocumentoidentidad().getCodigosunat());
				 camposEncabezado.setRznSocRecep(comprobante.getCliente().getNombrecompleto());
				 camposEncabezado.setDirRecep(comprobante.getCliente().getDireccion() + " " + comprobante.getCliente().getDistrito().getNombre() + " " + comprobante.getCliente().getProvincia().getNombre());
				 camposEncabezado.setMntExe("0.00");
				 camposEncabezado.setMntExo("0.00");
				 camposEncabezado.setMntTotBoni("0.00");
				 camposEncabezado.setMntTotAnticipo("0.00");
				 camposEncabezado.setHoraEmision(dtfMiliSeconds.format(comprobante.getFechapago()));

				 //para gratuita
				 if(comprobante.getTipodescuento()==null
						 || !comprobante.getTipodescuento().getKey().equals("corte")) {
					 
					 camposEncabezado.setMntTotGrat("0.00");
					 camposEncabezado.setMntTotGrat("0.00");
					 if(comprobante.getTipodescuento()!=null){
						 camposEncabezado.setMntNeto(Double.toString(UIUtils.round(MontoItem, 2)));//2
						 camposEncabezado.setMntTotal(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));//1
					 }else{
						 camposEncabezado.setMntTotal(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
						 camposEncabezado.setMntNeto(Double.toString(UIUtils.round(comprobante.getImportetotal()/1.18, 2)));//2
					 }
					
				 }else {
					 camposEncabezado.setMntTotGrat(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
					 //camposEncabezado.setMntTotGrat(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
					 camposEncabezado.setMntTotal("0.00");
					 camposEncabezado.setMntNeto(Double.toString(UIUtils.round(0.0, 2)));
					 
				 }
				 
				 _putCustomerETDLoad_encabezado.setCamposEncabezado(camposEncabezado);
				 
				 //no se envia si es gratuito
				 ArrayOfImptoReten arrayImptoReten = new ArrayOfImptoReten();
				 ImptoReten imptoReten = new ImptoReten();
				 if(comprobante.getTipodescuento()==null || !comprobante.getTipodescuento().getKey().equals("corte")) {
					 
					 imptoReten.setCodigoImpuesto("1000");
					 imptoReten.setTasaImpuesto("18");

					 if(comprobante.getTipodescuento()!=null){
						 //imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(MontoItem, 2)));//3
						 if(comprobante.getTotaldscto()<0){
							 imptoReten.setMontoImpuesto(Double.toString(UIUtils.round((comprobante.getImportetotal()/1.18)*0.18, 2)));//20 TOTAL IGV 18% RI
							 imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(comprobante.getImportetotal()/1.18, 2)));//3
						 }else{
							 imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(MontoItem*0.18, 2)));//20
							 //imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(base, 2)));//3
							 imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(comprobante.getImportetotal()/1.18, 2)));//3
							 //imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(1.1, 2)));//3 OPERACION GRAVADA RI
						 }
					 }else{
						 imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(comprobante.getIgv(), 2)));//20
						 imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(comprobante.getImportetotal()/1.18, 2)));//3
					 }

					 arrayImptoReten.getImptoReten().add(imptoReten);
					 _putCustomerETDLoad_encabezado.setImptoReten(arrayImptoReten);
					 
				 }else {
					 
					 /*imptoReten.setCodigoImpuesto("9996");
					 imptoReten.setTasaImpuesto("18");
					 imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(comprobante.getIgv(), 2) ));
					 imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));*/
					 
				 }
				 
				 //detalles
				 Detalle detalle = new Detalle();
				 CamposDetalle campoDetalle = new CamposDetalle();
				 campoDetalle.setNroLinea(1);
				 campoDetalle.setNroLinDet("1");
				 campoDetalle.setQtyItem("1");
				 campoDetalle.setUnmdItem("NIU");
				 campoDetalle.setVlrCodigo(comprobante.getConceptoInspeccion().getKey());
				 campoDetalle.setNmbItem(comprobante.getConceptoInspeccion().getNombre());
				 //campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)  ));
				 //campoDetalle.setMontoItem(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));


				 //campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));//21
				 if(nrocomprobante.contains("FE")) {
					 if (comprobante.getDescuentoComprobante() != null && !comprobante.getTipodescuento().getKey().equals("corte")) {
						 campoDetalle.setPrcItem(Double.toString(UIUtils.round((MontoItem+(MontoItem*0.18))/1, 2)));
					 }else{
						 if(comprobante.getDescuentoComprobante() != null && comprobante.getTipodescuento().getKey().equals("corte")){
							 campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getBaseimponible(), 2)));
						 }else{
							 campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
						 }
					 }
				 }else{
					 campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));
				 }
				 if(nrocomprobante.contains("FE")) {
					 if (comprobante.getDescuentoComprobante() != null) {
						 if(comprobante.getTipodescuento().getKey().equals("corte")){
							 campoDetalle.setMontoItem(Double.toString(UIUtils.round(comprobante.getBaseimponible(), 2)));//22
						 }else{
							 campoDetalle.setMontoItem(Double.toString(UIUtils.round(MontoItem, 2)));//22
						 }
						 //campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));
					 }else{
						 campoDetalle.setMontoItem(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));//22
					 }
				 }else{
					 campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));
				 }
				 if(comprobante.getDescuentoComprobante() != null){
					 if(comprobante.getTipodescuento().getKey().equals("corte")){
						 campoDetalle.setMontoItem(Double.toString(UIUtils.round(comprobante.getBaseimponible(), 2)));//22
					 }else{
						 campoDetalle.setMontoItem(Double.toString(UIUtils.round(MontoItem, 2)));//22
					 }
				 }else{
					 campoDetalle.setMontoItem(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));//22
				 }

				 campoDetalle.setDescuentoMonto("0.00");
				 if(comprobante.getTipodescuento()!=null && comprobante.getTipodescuento().getKey().equals("corte")) {
					 campoDetalle.setPrcItemSinIgv("0.00");
					 campoDetalle.setIndExe("13");
					 campoDetalle.setCodigoTipoIgv("9996");
				 }else {
					 //campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
					 if(nrocomprobante.contains("FE")) {
						 if (comprobante.getDescuentoComprobante() != null) {
							 //campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));//4
							 if(comprobante.getTotaldscto()<0){
								 campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(PrcItemSinIgv, 2)));//4
							 }else{
								 if(!comprobante.getTipodescuento().getKey().equals("corte")){
									 campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(comprobante.getTotalsindscto()/1.18, 2)));//4 VALOR UNITARIO RI
								 }else{
									 campoDetalle.setPrcItemSinIgv("0.00");//4 VALOR UNITARIO RI
								 }
							 }
						 }else{
							 campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round((comprobante.getImportetotal())/1.18, 2)));//4
						 }
					 }else{
						 campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));//4
					 }
					 campoDetalle.setIndExe("10");
					 campoDetalle.setCodigoTipoIgv("1000");
				 }

				 
				 campoDetalle.setTasaIgv("18");
				 if(comprobante.getDescuentoComprobante() != null){
					 if(comprobante.getTipodescuento().getKey().equals("corte")){
						 campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(comprobante.getIgv(), 2)));//5
					 }else{
						 campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(MontoItem*0.18, 2)));//5
					 }
				 }else{
					 campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(comprobante.getIgv(), 2)));//5
				 }
				 /*** Datos del descuento ***/
				 if(comprobante.getDescuentoComprobante() != null && !comprobante.getTipodescuento().getKey().equals("corte")){
					 campoDetalle.setDetMontoCargoDescuento(Double.toString(UIUtils.round(DetMontoCargoDescuento,2)));//7 Descuento RI
					 //campoDetalle.setDetMontoCargoDescuento(Double.toString(UIUtils.round(comprobante.getTotaldscto(),2)));//7
					 campoDetalle.setDetMBaseCargoDescuento(Double.toString(UIUtils.round(PrcItemSinIgv, 2)));//8
					 //campoDetalle.setDetMBaseCargoDescuento(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));//8
					 campoDetalle.setDetIndCargoDescuento("0");
					 campoDetalle.setDetCodigoCargoDescuento("00");
					 //campoDetalle.setDetFactorCargoDescuento(Double.toString(UIUtils.round(comprobante.getTotaldscto()/comprobante.getImportetotal(),4)));//6
					 campoDetalle.setDetFactorCargoDescuento(Double.toString(UIUtils.round(factor,4)));//6
				 }
				 detalle.setDetalles(campoDetalle);
				 
				 _putCustomerETDLoad_detalles.getDetalle().add(detalle);
				 
				 //descuentos regargos y otros
				 ArrayOfDatosAdicionales datosAdicionales = new ArrayOfDatosAdicionales();
				 
				 //nro de informe
				 DatosAdicionales adicional = new DatosAdicionales();
				 adicional.setTipoAdicSunat("01");
				 adicional.setNmrLineasDetalle("2");
				 adicional.setNmrLineasAdicSunat("2");
				 
				 String nroinspecciones = (inspeccion.getNrodocumentoInforme() != null)
						 ? inspeccion.getNrodocumentoInforme().toString() : "-";
						 
				adicional.setDescripcionAdicsunat(nroinspecciones);
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//adicional solo si es cortesia
				if(comprobante.getTipodescuento()!=null && comprobante.getTipodescuento().getKey().equals("corte")) {
					adicional = new DatosAdicionales();
					adicional.setTipoAdicSunat("01");
					adicional.setNmrLineasDetalle("16");
					adicional.setNmrLineasAdicSunat("16");
					adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
					datosAdicionales.getDatosAdicionales().add(adicional);
				}
				else{

					adicional= new DatosAdicionales();
					adicional.setTipoAdicSunat("01");
					adicional.setNmrLineasDetalle("16");
					adicional.setNmrLineasAdicSunat("16");
					//adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getTotalsindscto(),2)));//10
					if(DetMontoCargoDescuento==0){
						adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal(),2)));//10 SUBTOTAL RI
					}else{
						adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getTotalsindscto(),2)));//10 SUBTOTAL RI
					}
					datosAdicionales.getDatosAdicionales().add(adicional);

					adicional= new DatosAdicionales();
					adicional.setTipoAdicSunat("01");
					adicional.setNmrLineasDetalle("17");
					adicional.setNmrLineasAdicSunat("17");
					//adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
					//adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getTotaldscto(), 2)));//9
					if(DetMontoCargoDescuento==0){
						adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(0, 2)));//9
					}else{
						adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getTotaldscto(), 2)));//9
					}
					datosAdicionales.getDatosAdicionales().add(adicional);
				}
				
				//marca del auto
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("5");
				adicional.setNmrLineasAdicSunat("5");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getMarca().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("7");
				adicional.setNmrLineasAdicSunat("7");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getVehiculoclase().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//nro motor
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("8");
				adicional.setNmrLineasAdicSunat("8");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getNromotor());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//modelo
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("6");
				adicional.setNmrLineasAdicSunat("6");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getModelo().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);
			
				//get telefono
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("9");
				adicional.setNmrLineasAdicSunat("9");
				adicional.setDescripcionAdicsunat(comprobante.getCliente().getTelefono());
				datosAdicionales.getDatosAdicionales().add(adicional);
					 
				//get tipo de pago (credito / contado)
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("4");
				adicional.setNmrLineasAdicSunat("4");
					 
				if(comprobante.getFormaPago().getKey().equals("credito")) {
					adicional.setDescripcionAdicsunat("Credito");
				}else {
					adicional.setDescripcionAdicsunat("Contado");
				}
				
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//placa
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("15");
				adicional.setNmrLineasAdicSunat("15");
				adicional.setDescripcionAdicsunat(comprobante.getPlacaMotor());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("10");
				adicional.setNmrLineasAdicSunat("10");
				adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//direccion planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("11");
				adicional.setNmrLineasAdicSunat("11");
				adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getDireccion());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//monto a texto
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("1");
				adicional.setNmrLineasAdicSunat("1");
				if(comprobante.getTipodescuento()!=null && comprobante.getTipodescuento().getKey().equals("corte")) {
					adicional.setDescripcionAdicsunat("CERO CON 00/100 NUEVOS SOLES");
				} else {
					adicional.setDescripcionAdicsunat(NumberToLetter.convertNumberToLetter(comprobante.getImportetotal()));
				}
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				

				
				/*adicional= new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("17");
				adicional.setNmrLineasAdicSunat("17");
				adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
				datosAdicionales.getDatosAdicionales().add(adicional);

				adicional= new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("18");
				adicional.setNmrLineasAdicSunat("18");
				adicional.setDescripcionAdicsunat(Double.toString(comprobante.getTotaldscto()));
				datosAdicionales.getDatosAdicionales().add(adicional);*/

				 _putCustomerETDLoad_descuentosRecargosyOtros.setDatosAdicionales(datosAdicionales);

				 if(nrocomprobante.contains("FE")) {
					 if (comprobante.getFormaPago().getKey().equals("credito")) {
						 cal.setTimeInMillis(comprobante.getFechapago().getTime());
						 cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(NroDiasVence));
						 ArrayOfPago pagos = new ArrayOfPago();
						 Pago pago= new Pago();
						 pago.setCuota(Nrocuotas);
						 pago.setFechaVencCuota(String.valueOf(dtf.format(cal.getTime().getTime())));
						 //pago.setMontoCuota(Double.toString(UIUtils.round(MontoTotal, 2)));
						 pago.setMontoCuota(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
						 pagos.getPago().add(pago);

						 _putCustomerETDLoad_descuentosRecargosyOtros.setPagos(pagos);
					 }
				 }
				
			    ArrayOfEnvioPdf arrayOfEnvioPdf = new ArrayOfEnvioPdf();
			    EnvioPdf envioPdf = new EnvioPdf();
			    
			    CamposEnvioPdf camposEnvioPdf = new CamposEnvioPdf();
			    camposEnvioPdf.setMailEnvi(comprobante.getCliente().getEmail());
			    
			    envioPdf.setCamposEnvioPdf(camposEnvioPdf);
			    arrayOfEnvioPdf.getEnvioPdf().add(envioPdf);
			    _putCustomerETDLoad_extras.setEnvioPdf(arrayOfEnvioPdf);
			    
			}catch (Exception e) {
				 trigger.launch("", "enviar.carga.sunat.error", inspeccion.getNrodocumentoinspeccion() ,  Util.printError(e));
				 e.printStackTrace();
				 throw new Exception("Error al generar envio a DGF");
			}
			 
			 cl.dbnet.carga.Mensaje _putCustomerETDLoad__return = port.putCustomerETDLoad(_putCustomerETDLoad_extras, _putCustomerETDLoad_encabezado, _putCustomerETDLoad_detalles, _putCustomerETDLoad_descuentosRecargosyOtros);
			 
			 String pdf = "";
			 if(_putCustomerETDLoad__return.getCodigo().equals("DOK")) {
				 
				 //obtenemos el pdf
				 pdf = getPdf(comprobante.getEmpresa().getRuc(),
						 nrocomprobante,
						 comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")?"01":"03",
						 comprobante.getFechapago(),
				 		 comprobante.getImportetotal(), 
				 		 (comprobante.getTipodescuento()==null || !comprobante.getTipodescuento().getKey().equals("corte"))?false:true);
			         
			 }
			 if(_putCustomerETDLoad__return.getTrackId() == null || _putCustomerETDLoad__return.getTrackId().isEmpty()) {
				 _putCustomerETDLoad__return.setTrackId("trackid");
			 }
			 st_MensajeService.guardar(new ST_Mensaje("ALTA",_putCustomerETDLoad__return, inspeccion.getNrodocumentoinspeccion(), nrocomprobante,pdf));
			        
			
		 }catch(Exception e) {
			 trigger.launch("", "enviar.carga.sunat.error", inspeccion.getNrodocumentoinspeccion() ,  Util.printError(e));
			 e.printStackTrace();
		 }
	 }
	 
	 public void guardar(ST_Mensaje st_Mensaje){
		 st_Mensaje.setId(null);
		 st_mensajerepository.save(st_Mensaje);
	 }
	 
	 public String anularComprobante(String nrocomprobante, String planta, String observacion){
		 return bajaComprobantesSunatManual.enviar(nrocomprobante, planta, observacion);
	 }
	 
	 private void enviarFacturaConsolidadDgf(List<Comprobante> lstComprobantes) {
		 
		 URL wsdlURL = CustomerETDLoadASP.WSDL_LOCATION;
		 CustomerETDLoadASP ss = new CustomerETDLoadASP(wsdlURL, SERVICE_NAME);
		 CustomerETDLoadASPSoap port = ss.getCustomerETDLoadASPSoap();
		 
		 List<Long> ids = new ArrayList<>();
		 
		 SeriedocumentoOT seriedocumentoOT = seriedocumentoOTRepository.findOneByEmpresa(lstComprobantes.get(0).getEmpresa().getKey());
		 
		 String serie = seriedocumentoOT.getSeriedoc();
		 Long correlativo = seriedocumentoOT.getCorrelativoDoc()+1;
		 double importeTotal = 0.0;
		 double sumadescuento=0;
		 double igvdescuento=0;
		 double baseimponibledescuent=0;
		 double igvTotal = 0;
		 double sumaimportesindscto = 0;

		 double baseimponibleTotal = 0;
		 String Nrocuotas="";
		 String NroDiasVence="";
		 Calendar cal = Calendar.getInstance();
		 String nrocomprobante = UIUtils.formato8NroComprobante(serie, correlativo);
		 
		 for(Comprobante comprobante : lstComprobantes) {
			 importeTotal += comprobante.getImportetotal();
			 sumadescuento += comprobante.getTotaldscto();
			 sumaimportesindscto += comprobante.getTotalsindscto();

			 ids.add(comprobante.getId());
		 }
		 
		 
		 baseimponibleTotal = UIUtils.calcularBaseImponible(importeTotal);
		 igvTotal = UIUtils.calcularIgv(baseimponibleTotal, importeTotal);
		 
		 baseimponibledescuent=UIUtils.calcularBaseImponible(sumadescuento);
		 igvdescuento=UIUtils.calcularIgv(baseimponibledescuent, sumadescuento);
		 
		 try {
			 
			 Comprobante comprobante = lstComprobantes.get(0);
			 Extras _putCustomerETDLoad_extras = new Extras() ;
			 Encabezado _putCustomerETDLoad_encabezado = new Encabezado();
			 ArrayOfDetalle _putCustomerETDLoad_detalles = new ArrayOfDetalle();
			 DescuentosRecargosyOtros _putCustomerETDLoad_descuentosRecargosyOtros = new DescuentosRecargosyOtros();
			 if(comprobante.getDescuentoComprobante()!=null){
			 	Nrocuotas="1";//getNroCuotas(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle())).substring(0,1);
			 	NroDiasVence= "30";//getNroDiasVence(Long.valueOf(comprobante.getDescuentoComprobante().getIdDescuentoDetalle())).substring(0,2);
			 }else {
				 Nrocuotas="1";
				 NroDiasVence= "1";
			 }
			 try{
				 
				 SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
				 SimpleDateFormat dtfMiliSeconds = new SimpleDateFormat(UIUtils.FORMATO_HORA_WITH_MILISECONDS);
				 
				 //encabezado
				 CamposHead camposEncabezado = new CamposHead();
				 
				 camposEncabezado.setTipoDTE("01");
				 camposEncabezado.setSerie(serie);
				 camposEncabezado.setCorrelativo(UIUtils.leadingZeros(correlativo, UIUtils.CEROS_8IZQUIERDA));
				 camposEncabezado.setFchEmis(dtf.format(new Date()));
				 camposEncabezado.setTipoMoneda("PEN");
				 camposEncabezado.setRUTEmis(comprobante.getEmpresa().getRuc());
				 camposEncabezado.setTipoRucEmis("6");
				 camposEncabezado.setRznSocEmis(comprobante.getEmpresa().getNombre());
				 camposEncabezado.setDirEmis(comprobante.getEmpresa().getDireccion());
				 camposEncabezado.setComuEmis("150131"); //ubigeo de dasso
				 camposEncabezado.setCodigoLocalAnexo(comprobante.getLinea().getPlanta().getCodlocalsunat());
				 camposEncabezado.setRUTRecep(comprobante.getCliente().getNrodocumentoidentidad());
				 camposEncabezado.setTipoRutReceptor("6");
				 if(nrocomprobante.contains("FE")){
					 if(comprobante.getComprobanteestado().getKey().equals("CAN")){
						 camposEncabezado.setFormaPago("Contado");
					 }else if(comprobante.getComprobanteestado().getKey().equals("PEN")){
						 camposEncabezado.setFormaPago("Credito");
						 if(importeTotal > 700.0)
							 camposEncabezado.setMontoNetoPendPago(""+ (importeTotal-(importeTotal*0.12)) );
						  else
							  camposEncabezado.setMontoNetoPendPago(String.valueOf(importeTotal));
					 }
				 }
				 if(importeTotal > 700.0) {
					 camposEncabezado.setTipoOperacion("1001");
				 }
				 camposEncabezado.setRznSocRecep(comprobante.getCliente().getNombrecompleto());
				 camposEncabezado.setDirRecep(comprobante.getCliente().getDireccion() + " " + comprobante.getCliente().getDistrito().getNombre() + " " + comprobante.getCliente().getProvincia().getNombre());
				 camposEncabezado.setMntExe("0.00");
				 camposEncabezado.setMntExo("0.00");
				 camposEncabezado.setMntTotBoni("0.00");
				 camposEncabezado.setMntTotAnticipo("0.00");
				 camposEncabezado.setHoraEmision(dtfMiliSeconds.format(new Date()));
				 
				 camposEncabezado.setMntTotGrat("0.00");
				 camposEncabezado.setMntTotal(Double.toString(UIUtils.round(importeTotal, 2) ));
				 camposEncabezado.setMntNeto(Double.toString(UIUtils.round(baseimponibleTotal, 2)));
					
				 
				 _putCustomerETDLoad_encabezado.setCamposEncabezado(camposEncabezado);
				 
				 //Enviar detraccion si es mayor 700
				 
				 if(importeTotal > 700.0) {
					 ArrayOfDetaDetraccion arrayOfDetaDetraccion = new ArrayOfDetaDetraccion();
					 DetaDetraccion detaDetraccion = new DetaDetraccion();
					 
					 detaDetraccion.setCodiDetraccion("2003");
					 detaDetraccion.setValorDetraccion("-");
					 detaDetraccion.setMntDetraccion(""+UIUtils.calcularMontoDetraccion(importeTotal));
					 detaDetraccion.setPorcentajeDetraccion("12");
					 
					 arrayOfDetaDetraccion.getDetaDetraccion().add(detaDetraccion);
					 
					 detaDetraccion = new DetaDetraccion();
					 
					 detaDetraccion.setCodiDetraccion("3000");
					 detaDetraccion.setValorDetraccion("037");
					 detaDetraccion.setMntDetraccion("");
					 detaDetraccion.setPorcentajeDetraccion("");
					 
					 arrayOfDetaDetraccion.getDetaDetraccion().add(detaDetraccion);
					 
					 detaDetraccion = new DetaDetraccion();
					 
					 detaDetraccion.setCodiDetraccion("3001");
					 detaDetraccion.setValorDetraccion(comprobante.getEmpresa().getCtaBancoNacion());
					 detaDetraccion.setMntDetraccion("");
					 detaDetraccion.setPorcentajeDetraccion("");
					 
					 arrayOfDetaDetraccion.getDetaDetraccion().add(detaDetraccion);
					 _putCustomerETDLoad_encabezado.setDetaDetraccion(arrayOfDetaDetraccion); 
				 } 
				
					
				 
				 //no se envia si es gratuito
				 ArrayOfImptoReten arrayImptoReten = new ArrayOfImptoReten();
				 ImptoReten imptoReten = new ImptoReten();
				 
				 imptoReten.setCodigoImpuesto("1000");
				 imptoReten.setTasaImpuesto("18");
				 imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(igvTotal, 2) ));
				 imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(baseimponibleTotal, 2)));
				 
				 arrayImptoReten.getImptoReten().add(imptoReten);
				 _putCustomerETDLoad_encabezado.setImptoReten(arrayImptoReten);
				 
				 int contDetalle = 0;
				 for(Comprobante bean : lstComprobantes) {
					 contDetalle++;
					//detalles
					 Detalle detalle = new Detalle();
					 CamposDetalle campoDetalle = new CamposDetalle();
					 campoDetalle.setNroLinea(contDetalle);
					 campoDetalle.setNroLinDet(contDetalle+"");
					 campoDetalle.setQtyItem("1");
					 campoDetalle.setUnmdItem("NIU");
					 campoDetalle.setVlrCodigo(bean.getConceptoInspeccion().getKey());
					 campoDetalle.setNmbItem(bean.getConceptoInspeccion().getNombre()  + " - " + bean.getOrdenTrabajo().getNroOT() + " - " + bean.getPlacaMotor());
					 campoDetalle.setPrcItem(Double.toString(UIUtils.round(bean.getImportetotal(), 2)  ));
					 //campoDetalle.setPrcItem(Double.toString(UIUtils.round(bean.getTotalsindscto(), 2)  ));//3
					 campoDetalle.setMontoItem(Double.toString(UIUtils.round(bean.getImportetotal() - bean.getIgv(), 2)));//4
					 campoDetalle.setDescuentoMonto("0.00");
					 campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(bean.getImportetotal() - bean.getIgv(), 2)));
					 //campoDetalle.setPrcItemSinIgv(Double.toString(UIUtils.round(bean.getImportetotal(), 2)));//5
					 campoDetalle.setIndExe("10");
					 campoDetalle.setCodigoTipoIgv("1000");
					 
					 campoDetalle.setTasaIgv("18");
					 campoDetalle.setImpuestoIgv(Double.toString(UIUtils.round(bean.getIgv(), 2) ));

					 /*if(nrocomprobante.contains("FE")) {
						 if (comprobante.getDescuentoComprobante() != null) {
							 campoDetalle.setPrcItem(Double.toString(UIUtils.round(((comprobante.getImportetotal() - comprobante.getTotaldscto()) / Integer.valueOf(Nrocuotas)) * 1.18, 2)));
						 } else {
							 campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));//21
						 }
					 }*/
					 /*** Datos del descuento
					 campoDetalle.setDetIndCargoDescuento("0");
					 campoDetalle.setDetCodigoCargoDescuento("00");
					 campoDetalle.setDetFactorCargoDescuento(Double.toString(UIUtils.round(bean.getTotaldscto()/(bean.getImportetotal() - bean.getIgv()),4)));
					 //campoDetalle.setDetMontoCargoDescuento(Double.toString(bean.getTotaldscto()));
					 campoDetalle.setDetMontoCargoDescuento(Double.toString(UIUtils.round(bean.getTotaldscto(),2)));//8
					 campoDetalle.setDetMBaseCargoDescuento(Double.toString(UIUtils.round(bean.getImportetotal() - bean.getIgv(), 2)));***/
					 
					 detalle.setDetalles(campoDetalle);
					 
			
					
					 
					 _putCustomerETDLoad_detalles.getDetalle().add(detalle);
					 
				 };
				 
				 
				 
				 //descuentos regargos y otros
				 ArrayOfDatosAdicionales datosAdicionales = new ArrayOfDatosAdicionales();
				 
				 //nro de informe
				 
				 DatosAdicionales adicional = new DatosAdicionales();
				 adicional.setTipoAdicSunat("01");
				 adicional.setNmrLineasDetalle("2");
				 adicional.setNmrLineasAdicSunat("2");
				 adicional.setDescripcionAdicsunat("-");
				 datosAdicionales.getDatosAdicionales().add(adicional);
				
				//marca del auto
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("5");
				adicional.setNmrLineasAdicSunat("5");
				adicional.setDescripcionAdicsunat("-");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("7");
				adicional.setNmrLineasAdicSunat("7");
				adicional.setDescripcionAdicsunat("-");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//nro motor
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("8");
				adicional.setNmrLineasAdicSunat("8");
				adicional.setDescripcionAdicsunat("-");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//modelo
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("6");
				adicional.setNmrLineasAdicSunat("6");
				adicional.setDescripcionAdicsunat("-");
				datosAdicionales.getDatosAdicionales().add(adicional);
			
				//get telefono
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("9");
				adicional.setNmrLineasAdicSunat("9");
				adicional.setDescripcionAdicsunat(comprobante.getCliente().getTelefono());
				datosAdicionales.getDatosAdicionales().add(adicional);
					 
				//get tipo de pago (credito / contado)
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("4");
				adicional.setNmrLineasAdicSunat("4");	 
				adicional.setDescripcionAdicsunat("Contado");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//placa
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("15");
				adicional.setNmrLineasAdicSunat("15");
				adicional.setDescripcionAdicsunat("-");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("10");
				adicional.setNmrLineasAdicSunat("10");
				adicional.setDescripcionAdicsunat("DASSO");
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//direccion planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("11");
				adicional.setNmrLineasAdicSunat("11");
				adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getEmpresa().getDireccion());
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				//monto a texto
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("1");
				adicional.setNmrLineasAdicSunat("1");
				adicional.setDescripcionAdicsunat(NumberToLetter.convertNumberToLetter(importeTotal));
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				adicional= new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("16");
				adicional.setNmrLineasAdicSunat("16");
				//adicional.setDescripcionAdicsunat(Double.toString(comprobante.getTotalsindscto()));
				adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(sumaimportesindscto, 2)));
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				adicional= new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("17");
				adicional.setNmrLineasAdicSunat("17");
				//adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
				//adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(sumadescuento, 2)));
				adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(sumadescuento, 2)));
				datosAdicionales.getDatosAdicionales().add(adicional);
				
				_putCustomerETDLoad_descuentosRecargosyOtros.setDatosAdicionales(datosAdicionales);

				 if(nrocomprobante.contains("FE")) {
					 if (comprobante.getFormaPago().getKey().equals("credito")) {
						 Timestamp timestamp= new Timestamp(System.currentTimeMillis());

						 //cal.setTimeInMillis(comprobante.getFechapago().getTime());
						 cal.setTimeInMillis(timestamp.getTime());
						 cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(NroDiasVence));
						 ArrayOfPago pagos = new ArrayOfPago();
						 Pago pago;
						 pago= new Pago();
						 pago.setCuota(Nrocuotas);
						 pago.setFechaVencCuota(String.valueOf(dtf.format(cal.getTime().getTime())));

						 if(importeTotal > 700.0)
							 pago.setMontoCuota(""+(importeTotal-(importeTotal*0.12)));
						 else
							 pago.setMontoCuota(String.valueOf(importeTotal));
						 pagos.getPago().add(pago);

						 _putCustomerETDLoad_descuentosRecargosyOtros.setPagos(pagos);
					 }
				 }
			    ArrayOfEnvioPdf arrayOfEnvioPdf = new ArrayOfEnvioPdf();
			    EnvioPdf envioPdf = new EnvioPdf();
			    
			    CamposEnvioPdf camposEnvioPdf = new CamposEnvioPdf();
			    camposEnvioPdf.setMailEnvi(comprobante.getCliente().getEmail());
			    
			    envioPdf.setCamposEnvioPdf(camposEnvioPdf);
			    arrayOfEnvioPdf.getEnvioPdf().add(envioPdf);
			    _putCustomerETDLoad_extras.setEnvioPdf(arrayOfEnvioPdf);
			    
			}catch (Exception e) {
				 trigger.launch("", "enviar.carga.sunat.error", "factura",  Util.printError(e));
				 e.printStackTrace();
				 throw new Exception("Error al generar envio a DGF");
			}
			 
			 cl.dbnet.carga.Mensaje _putCustomerETDLoad__return = port.putCustomerETDLoad(_putCustomerETDLoad_extras, _putCustomerETDLoad_encabezado, _putCustomerETDLoad_detalles, _putCustomerETDLoad_descuentosRecargosyOtros);
			 
			 String pdf = "";
			 if(_putCustomerETDLoad__return.getCodigo().equals("DOK")) {
				 
				 //obtenemos el pdf
				 pdf = getPdf(comprobante.getEmpresa().getRuc(),
						 nrocomprobante,
						 "01",
						 new Date(),
				 		 importeTotal, 
				 		 false);
				 
				 String hqlUpCorr = "update SeriedocumentoOT set correlativoDoc = :correlativoDoc WHERE id = :id ";
					Query queryCorr = entityManager.createQuery(hqlUpCorr);
					queryCorr.setParameter("correlativoDoc", correlativo);
					queryCorr.setParameter("id", seriedocumentoOT.getId());
					queryCorr.executeUpdate();
					
				String hql = "update Comprobante set nrocomprobante = :nrocomprobante where id in (:ids)";
				Query query = entityManager.createQuery(hql);
				query.setParameter("nrocomprobante", nrocomprobante);
				query.setParameter("ids", ids);
				query.executeUpdate();
				 seriedocumentoOTRepository.save(seriedocumentoOT);
			 }
			 st_MensajeService.guardar(new ST_Mensaje("ALTA",_putCustomerETDLoad__return, "", nrocomprobante,pdf));
			        
			
		 }catch(Exception e) {
			 trigger.launch("", "enviar.carga.sunat.error", "" ,  Util.printError(e));
			 e.printStackTrace();
		 }
		 
	 }
	
}
