package com.farenet.nodo.maestro.api.task.logic;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.springframework.context.ApplicationContext;


import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoBaseRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoOTRepository;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.services.ST_Service;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.task.domain.DataTask;
import com.farenet.nodo.maestro.api.task.domain.Execute;
import com.farenet.nodo.maestro.api.task.domain.Task;
import com.farenet.nodo.maestro.api.util.NumberToLetter;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

import cl.dbnet.carga.ArrayOfDatosAdicionales;
import cl.dbnet.carga.ArrayOfDetalle;
import cl.dbnet.carga.ArrayOfEnvioPdf;
import cl.dbnet.carga.ArrayOfImptoReten;
import cl.dbnet.carga.ArrayOfPago;
import cl.dbnet.carga.ArrayOfReferencias;
import cl.dbnet.carga.CamposDetalle;
import cl.dbnet.carga.CamposEnvioPdf;
import cl.dbnet.carga.CamposHead;
import cl.dbnet.carga.CustomerETDLoadASP;
import cl.dbnet.carga.CustomerETDLoadASPSoap;
import cl.dbnet.carga.DatosAdicionales;
import cl.dbnet.carga.DescuentosRecargosyOtros;
import cl.dbnet.carga.Detalle;
import cl.dbnet.carga.Encabezado;
import cl.dbnet.carga.EnvioPdf;
import cl.dbnet.carga.Extras;
import cl.dbnet.carga.ImptoReten;
import cl.dbnet.carga.Pago;
import cl.dbnet.carga.Referencias;

@Execute(target = "enviar.carga.sunat")
public class CargaComprobantesSUNAT extends Task {

	private static final QName SERVICE_NAME = new QName("http://www.dbnet.cl", "CustomerETDLoadASP");

	private EntityManager em;

	private CustomerETDLoadASPSoap port;

	private TriggerTaskFactory trigger;

	private Inspeccion inspeccion;

	private SeriedocumentoBaseRepository seriedocumentoBaseRepository;
	
	private SeriedocumentoOTRepository seriedocumentoOTRepository;

	private MaestroService maestroService;

	private ST_Service st_service;

	public CargaComprobantesSUNAT(DataTask data, ApplicationContext appContext) {
		super(data, appContext);
		// TODO Auto-generated constructor stub
		try {
			new Thread().sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		}
		EntityManagerFactory emf = (EntityManagerFactory) appContext.getBean("entityManagerFactory");
		em = emf.createEntityManager();

		// trigger service
		trigger = appContext.getBean(TriggerTaskFactory.class);

		// repositorio
		seriedocumentoBaseRepository = appContext.getBean(SeriedocumentoBaseRepository.class);
		
		seriedocumentoOTRepository = appContext.getBean(SeriedocumentoOTRepository.class);

		// servicio de aumentar recibo/factura
		maestroService = appContext.getBean(MaestroService.class);

		// obtener el service
		st_service = appContext.getBean(ST_Service.class);

		// obtenemos el nro documento inspeccion
		String nrodocumentoinspeccion = (String) data.data;

		// buscamos la inspeccion

		try {

			String hql1 = "SELECT ins FROM Inspeccion ins WHERE nrodocumentoinspeccion = :nrodocumentoinspeccion";
			Query query = em.createQuery(hql1);
			query.setParameter("nrodocumentoinspeccion", nrodocumentoinspeccion);

			inspeccion = (Inspeccion) query.getSingleResult();

			// parametro de wsdl
			URL wsdlURL = CustomerETDLoadASP.WSDL_LOCATION;
			CustomerETDLoadASP ss = new CustomerETDLoadASP(wsdlURL, SERVICE_NAME);
			port = ss.getCustomerETDLoadASPSoap();

		} catch (NoResultException e) {
			trigger.launch("", "enviar.carga.sunat.error", "No existe inspeccion " + nrodocumentoinspeccion,
					Util.printError(e));
			e.printStackTrace();
			em.close();
		} catch (Exception e) {
			trigger.launch("", "enviar.carga.sunat.error", "Error inspeccion " + nrodocumentoinspeccion,
					Util.printError(e));
			e.printStackTrace();
			em.close();

		}

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
			Query query = em.createQuery(hql.toString());
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
			 Query query = em.createQuery(hql.toString());
			 query.setParameter("iddetalle", iddetalle);
			 result=query.getSingleResult().toString();
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 return result;
	 }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// enviar a digiflow

		trigger.launch("", "enviar.carga.sunat.log", inspeccion.getNrodocumentoinspeccion(),"Iniciando envio de comprobante");
		if (inspeccion == null) {
			em.close();
			return;
		}

		if (inspeccion.getLastComprobante().getImportetotal() == 0
				&& inspeccion.getLastComprobante().getTipodescuento() == null) {
			em.close();
			return;
		}
		
		Comprobante comprobante = inspeccion.getLastComprobante();
		Long correlativo = 0l;
		SeriedocumentoBase documento = null;
		String nrocomprobante = "";
		 String Nrocuotas="";
		 String NroDiasVence="";
		 Calendar cal = Calendar.getInstance();
		
		em.getTransaction().begin();

		trigger.launch("", "enviar.carga.sunat.log", inspeccion.getNrodocumentoinspeccion(),"Generando objeto a enviar a sunat");
		
		try {

			Extras _putCustomerETDLoad_extras = new Extras();
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

			try {

				if (comprobante.getFechapago() == null)
					comprobante.setFechapago(new Timestamp(new Date().getTime()));


				SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
				SimpleDateFormat dtfMiliSeconds = new SimpleDateFormat(UIUtils.FORMATO_HORA_WITH_MILISECONDS);

				if (comprobante != null && comprobante.getLinea() != null) {
					documento = seriedocumentoBaseRepository
							.findOneByPlanta(comprobante.getLinea().getPlanta().getKey());
				} else {
					String[] nroplanta = inspeccion.getNrodocumentoinspeccion().split("-");
					documento = seriedocumentoBaseRepository.findOneByPlanta(nroplanta[1]);
				}


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


				// encabezado
				CamposHead camposEncabezado = new CamposHead();


				if (comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
					camposEncabezado.setTipoDTE("01");
					camposEncabezado.setSerie(documento.getSeriefactura().trim());


					correlativo = maestroService.nextIdFactura(documento.getPlanta().getKey());
					nrocomprobante = UIUtils.formato8NroComprobante(documento.getSeriefactura(), correlativo);

				} else {

					camposEncabezado.setTipoDTE("03");
					camposEncabezado.setSerie(documento.getSerieboleta().trim());

					correlativo = maestroService.nextIdBoleta(documento.getPlanta().getKey());
					nrocomprobante = UIUtils.formato8NroComprobante(documento.getSerieboleta(), correlativo);

				}

				camposEncabezado.setCorrelativo(UIUtils.leadingZeros(correlativo, UIUtils.CEROS_8IZQUIERDA).trim());
				camposEncabezado.setFchEmis(dtf.format(comprobante.getFechapago()));
				camposEncabezado.setTipoMoneda("PEN");
				camposEncabezado.setRUTEmis(comprobante.getEmpresa().getRuc());
				camposEncabezado.setTipoRucEmis("6");
				camposEncabezado.setRznSocEmis(comprobante.getEmpresa().getNombre());
				camposEncabezado.setDirEmis(comprobante.getEmpresa().getDireccion());
				camposEncabezado.setComuEmis("150131"); // ubigeo de dasso
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
				if (comprobante.getCliente().getTipodocumentoidentidad().getNombre().equals("SIN DNI")) {
					camposEncabezado.setRUTRecep("11111111");
					camposEncabezado.setTipoRutReceptor("C");
				} else {
					camposEncabezado.setRUTRecep(comprobante.getCliente().getNrodocumentoidentidad().trim());
					camposEncabezado.setTipoRutReceptor(comprobante.getCliente().getTipodocumentoidentidad().getCodigosunat());
				}
				camposEncabezado.setRznSocRecep(comprobante.getCliente().getNombrecompleto().trim());

				if (comprobante.getCliente().getTipodocumentoidentidad().getNombre().equals("SIN DNI")) {
					camposEncabezado.setDirRecep("-");
				} else {
					camposEncabezado.setDirRecep(comprobante.getCliente().getDireccion().trim() + " "
							+ comprobante.getCliente().getDistrito().getNombre().trim() + " "
							+ comprobante.getCliente().getProvincia().getNombre().trim());
				}

				camposEncabezado.setMntExe("0.00");
				camposEncabezado.setMntExo("0.00");
				camposEncabezado.setMntTotBoni("0.00");
				camposEncabezado.setMntTotAnticipo("0.00");
				camposEncabezado.setHoraEmision(dtfMiliSeconds.format(comprobante.getFechapago()));
				// camposEncabezado.setNumPlacaVehi(comprobante.getPlacaMotor());
				// para gratuita
				if (comprobante.getTipodescuento() == null
						|| !comprobante.getTipodescuento().getKey().equals("corte")) {

					camposEncabezado.setMntTotGrat("0.00");
					if(comprobante.getTipodescuento()!=null){
						camposEncabezado.setMntNeto(Double.toString(UIUtils.round(MontoItem, 2)));//2
						camposEncabezado.setMntTotal(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));//1
					}else{
						camposEncabezado.setMntTotal(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
						camposEncabezado.setMntNeto(Double.toString(UIUtils.round(comprobante.getImportetotal()/1.18, 2)));//2
					}

				} else {
					camposEncabezado.setMntTotGrat(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));
					//camposEncabezado.setMntTotGrat(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
					camposEncabezado.setMntTotal("0.00");
					camposEncabezado.setMntNeto(Double.toString(UIUtils.round(0.0, 2)));
				}
				
				

				_putCustomerETDLoad_encabezado.setCamposEncabezado(camposEncabezado);

				// no se envia si es gratuito
				ArrayOfImptoReten arrayImptoReten = new ArrayOfImptoReten();
				ImptoReten imptoReten = new ImptoReten();
				if (comprobante.getTipodescuento() == null
						|| !comprobante.getTipodescuento().getKey().equals("corte")) {

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

				} else {
					/*imptoReten.setCodigoImpuesto("9996");
					imptoReten.setTasaImpuesto("18");
					imptoReten.setMontoImpuesto(Double.toString(UIUtils.round(comprobante.getIgv(), 2)));
					imptoReten.setMontoImpuestoBase(Double.toString(UIUtils.round(comprobante.getImportetotal() - comprobante.getIgv(), 2)));*/

				}



				// detalles

				Detalle detalle = new Detalle();
				CamposDetalle campoDetalle = new CamposDetalle();
				campoDetalle.setNroLinea(1);
				campoDetalle.setNroLinDet("1");
				campoDetalle.setQtyItem("1");
				campoDetalle.setUnmdItem("NIU");
				campoDetalle.setVlrCodigo(comprobante.getConceptoInspeccion().getKey());
				campoDetalle.setNmbItem(comprobante.getConceptoInspeccion().getNombre());
				//campoDetalle.setPrcItem(Double.toString(UIUtils.round(comprobante.getTotalsindscto(), 2)));//21
				if(nrocomprobante.contains("FE")) {
					if (comprobante.getDescuentoComprobante() != null && !comprobante.getTipodescuento().getKey().equals("corte")) {
						campoDetalle.setPrcItem(Double.toString(UIUtils.round(Math.round((MontoItem+(MontoItem*0.18))/1), 2)));
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

				if (comprobante.getTipodescuento() != null && comprobante.getTipodescuento().getKey().equals("corte")) {
					campoDetalle.setPrcItemSinIgv("0.00");
					campoDetalle.setIndExe("13");
					campoDetalle.setCodigoTipoIgv("9996");
				} else {
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

				// descuentos regargos y otros
				ArrayOfDatosAdicionales datosAdicionales = new ArrayOfDatosAdicionales();

				// nro de informe
				DatosAdicionales adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("2");
				adicional.setNmrLineasAdicSunat("2");

				String nroinspecciones = (inspeccion.getNrodocumentoInforme() != null)
						? inspeccion.getNrodocumentoInforme().toString()
						: "-";

				adicional.setDescripcionAdicsunat(nroinspecciones);
				datosAdicionales.getDatosAdicionales().add(adicional);

				
				// adicional solo si es cortesia
				if (comprobante.getTipodescuento() != null && comprobante.getTipodescuento().getKey().equals("corte")) {
					adicional = new DatosAdicionales();
					adicional.setTipoAdicSunat("01");
					adicional.setNmrLineasDetalle("16");
					adicional.setNmrLineasAdicSunat("16");
					adicional.setDescripcionAdicsunat(Double.toString(UIUtils.round(comprobante.getImportetotal(), 2)));
					datosAdicionales.getDatosAdicionales().add(adicional);
				}else
				 {
					
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

				// marca del auto
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

				// nro motor
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("8");
				adicional.setNmrLineasAdicSunat("8");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getNromotor());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// modelo
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("6");
				adicional.setNmrLineasAdicSunat("6");
				adicional.setDescripcionAdicsunat(inspeccion.getVehiculo().getModelo().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// get telefono
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("9");
				adicional.setNmrLineasAdicSunat("9");
				adicional.setDescripcionAdicsunat(comprobante.getCliente().getTelefono());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// get tipo de pago (credito / contado)
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("4");
				adicional.setNmrLineasAdicSunat("4");

				if (comprobante.getFormaPago().getKey().equals("credito")) {
					adicional.setDescripcionAdicsunat("Credito");
				} else {
					adicional.setDescripcionAdicsunat("Contado");
				}

				datosAdicionales.getDatosAdicionales().add(adicional);

				// placa
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("15");
				adicional.setNmrLineasAdicSunat("15");
				adicional.setDescripcionAdicsunat(comprobante.getPlacaMotor());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("10");
				adicional.setNmrLineasAdicSunat("10");
				adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getNombre());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// direccion planta
				adicional = new DatosAdicionales();
				adicional.setTipoAdicSunat("01");
				adicional.setNmrLineasDetalle("11");
				adicional.setNmrLineasAdicSunat("11");
				adicional.setDescripcionAdicsunat(comprobante.getLinea().getPlanta().getDireccion());
				datosAdicionales.getDatosAdicionales().add(adicional);

				// monto a texto
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

			
				
				
				_putCustomerETDLoad_descuentosRecargosyOtros.setDatosAdicionales(datosAdicionales);
				
				if(comprobante.getOrdenservicio() != null && !comprobante.getOrdenservicio().isEmpty()) {
					 ArrayOfReferencias arrayOfReferencias = new ArrayOfReferencias();
					 Referencias referencias = new Referencias();
					 referencias.setNroLinRef("1");
					 referencias.setTpoDocRef("801");
					 referencias.setFolioRef(comprobante.getOrdenservicio()); 
					 arrayOfReferencias.getReferencias().add(referencias);
					 _putCustomerETDLoad_descuentosRecargosyOtros.setReferencias(arrayOfReferencias);
				 }


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

			} catch (Exception e) {
				trigger.launch("", "enviar.carga.sunat.error", inspeccion.getNrodocumentoinspeccion(),
						Util.printError(e));
				e.printStackTrace();
				throw new Exception("Error al generar envio a DGF");
			}

			//timeout
			((BindingProvider)port).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 10000);
			((BindingProvider)port).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 10000);
			((BindingProvider)port).getRequestContext().put("com.sun.xml.ws.request.timeout", 10000);
			((BindingProvider)port).getRequestContext().put("com.sun.xml.ws.connect.timeout", 10000);


			cl.dbnet.carga.Mensaje _putCustomerETDLoad__return = port.putCustomerETDLoad(_putCustomerETDLoad_extras,
					_putCustomerETDLoad_encabezado, _putCustomerETDLoad_detalles,
					_putCustomerETDLoad_descuentosRecargosyOtros);

			// grabando en el st_mensaje

			String pdf = "";

			// GUARDAR EL NRO DE COMPROBANTE EN COMPROBANTE --
			String hqlSum = "update Comprobante " + "set nrocomprobante = :nrocomprobante WHERE id = :id ";
			Query querySum = em.createQuery(hqlSum);
			querySum.setParameter("nrocomprobante", nrocomprobante);
			querySum.setParameter("id", comprobante.getId());
			int cant = querySum.executeUpdate();


			//ACTUALIZAMOS EL SERIEDOCUMENTO BASE

			if (comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
				//SI ES FACTURA
				String hqlUpCorr = "update SeriedocumentoBase set nroactualFactura = :nroactualfactura WHERE id = :id ";
				Query queryCorr = em.createQuery(hqlUpCorr);
				queryCorr.setParameter("nroactualfactura", correlativo);
				queryCorr.setParameter("id", documento.getId());
				queryCorr.executeUpdate();
			}else{
				//SI ES BOLETA
				String hqlUpCorr = "update SeriedocumentoBase set nroactualBoleta = :nroactualboleta WHERE id = :id ";
				Query queryCorr = em.createQuery(hqlUpCorr);
				queryCorr.setParameter("nroactualboleta", correlativo);
				queryCorr.setParameter("id", documento.getId());
				queryCorr.executeUpdate();
			}

			if (_putCustomerETDLoad__return.getCodigo().equals("DOK")) {

				trigger.launch("", "enviar.carga.sunat.dok", inspeccion.getNrodocumentoinspeccion(),
						"afectados " + cant + " Id : " + comprobante.getId());
				// ------------
				
				// obtenemos el pdf
				pdf = st_service.getPdf(comprobante.getEmpresa().getRuc(), nrocomprobante,
						comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc") ? "01" : "03",
						comprobante.getFechapago(), comprobante.getImportetotal(),
						(comprobante.getTipodescuento() == null
								|| !comprobante.getTipodescuento().getKey().equals("corte")) ? false : true);

			}else {

				trigger.launch("", "enviar.carga.sunat.err", inspeccion.getNrodocumentoinspeccion(),
						"afectados " + cant + " Id : " + comprobante.getId());
				// ------------
			}
			
			em.persist(new ST_Mensaje("ALTA", _putCustomerETDLoad__return, inspeccion.getNrodocumentoinspeccion(),
					nrocomprobante, pdf));
			em.getTransaction().commit();

		} catch (Exception e) {
			
			// GUARDAR EL NRO DE COMPROBANTE EN COMPROBANTE --
			String hqlSum = "update Comprobante " + "set nrocomprobante = :nrocomprobante WHERE id = :id ";
			Query querySum = em.createQuery(hqlSum);
			querySum.setParameter("nrocomprobante", nrocomprobante);
			querySum.setParameter("id", comprobante.getId());
			int cant = querySum.executeUpdate();
			trigger.launch("", "enviar.carga.sunat.try.dok", inspeccion.getNrodocumentoinspeccion(),
					"afectados " + cant + " Id : " + comprobante.getId());
			
			//ACTUALIZAMOS EL SERIEDOCUMENTO BASE
			if (comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")) {
				//SI ES FACTURA
				String hqlUpCorr = "update SeriedocumentoBase set nroactualFactura = :nroactualfactura WHERE id = :id ";
				Query queryCorr = em.createQuery(hqlUpCorr);
				queryCorr.setParameter("nroactualfactura", correlativo);
				queryCorr.setParameter("id", documento.getId());
				queryCorr.executeUpdate();
			}else{
				//SI ES BOLETA
				String hqlUpCorr = "update SeriedocumentoBase set nroactualBoleta = :nroactualboleta WHERE id = :id ";
				Query queryCorr = em.createQuery(hqlUpCorr);
				queryCorr.setParameter("nroactualboleta", correlativo);
				queryCorr.setParameter("id", documento.getId());
				queryCorr.executeUpdate();
			}
			
			em.getTransaction().commit();
			
			trigger.launch("", "enviar.carga.sunat.error", inspeccion.getNrodocumentoinspeccion(), Util.printError(e));
			e.printStackTrace();

		} finally {
			em.close();
		}

	}

}
