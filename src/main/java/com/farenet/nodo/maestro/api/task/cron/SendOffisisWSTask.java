package com.farenet.nodo.maestro.api.task.cron;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farenet.nodo.maestro.api.caja.service.CierrecajaDepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.domain.bean.ComprobanteOfisisBean;
import com.farenet.nodo.maestro.api.caja.service.CierrecajaService;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.http.HTTPJsonEngine;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.inspeccion.service.VehiculoService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.ofisis.bean.AnuladoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.ClienteBean;
import com.farenet.nodo.maestro.api.ofisis.bean.ConvenioBean;
import com.farenet.nodo.maestro.api.ofisis.bean.DescuentoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.EstadoComprobanteBean;
import com.farenet.nodo.maestro.api.ofisis.bean.InsertBean;
import com.farenet.nodo.maestro.api.ofisis.bean.LocationBean;
import com.farenet.nodo.maestro.api.ofisis.bean.PagoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.PlantaBean;
import com.farenet.nodo.maestro.api.ofisis.bean.SendAnuladoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.SendComprobanteBean;
import com.farenet.nodo.maestro.api.ofisis.bean.TipoDocumentoBean;
import com.farenet.nodo.maestro.api.util.StatusHttp;
import com.farenet.nodo.maestro.api.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class SendOffisisWSTask {
    @Autowired
    private InspeccionService inspeccionService;
    @Autowired
    public ComprobanteService comprobanteService;
    @Autowired
    private CierrecajaService cierrecajaService;
    @Autowired
    public VehiculoService vehiculoService;
    @Autowired
    private CierrecajaDepositoService cierrecajaDepositoService;
    @Autowired
    public TriggerTaskFactory trigger;
    public Boolean anulado = false;
    private
    @Value("${ofisis.url}")
    String url;
    
    private String apeMat = "";
    public StatusHttp status = new StatusHttp();
    
    @Async
//    @Scheduled(cron= "0 25 10 * * *")
    //@Scheduled(fixedDelay=5000)
    public void execute() {
        List<ComprobanteOfisisBean> lstInspeccionNotSended = comprobanteService.getNotSendedToOffisis();
        trigger.launch("", "ofisis.begin", "CANTIDAD A ENVIAR A OFFISIS ", lstInspeccionNotSended.size());
        
        // Establish the URL.
        //String urlComprobantes = url + "/Ofisis";
        String urlComprobantes = url + "/Cobranza";
        //String urlAnulados = url + "/Anulado";
        
        // Enviar Cierres de Caja
        
        for (ComprobanteOfisisBean comprobante : lstInspeccionNotSended) {
        	
            try {
                // DATOS OFISIS
            	
                anulado = false;
                if (comprobante.comprobanteEstado.equals("ANU")||comprobante.EstadoComprobanteSunat.equals("RCH"))
                {
                    anulado = true;
                }
                
                String codigoVendedor = "00";
                if(comprobante.codigoVendedor!=null)
                {
                	codigoVendedor = comprobante.codigoVendedor;
                }
                
                // Persona natura o jurídica
                String situacionJuridica = (comprobante.situacionJuridica.equals("ruc")) ? "RUC" : "DNI";
                
              //Validacion por si es dni
                if(situacionJuridica.equals("DNI")){
                	if(comprobante.documentoValidado.trim().replace(".", "").length() != 8){
                		trigger.launch("", "ofisis.error.comprobante.nrodoc", comprobante.nroComprobante , comprobante.documentoValidado);
                		comprobante.documentoValidado = "11111111";
                		comprobante.nombreCliente = "Clientes";
                		comprobante.apellidos = "Varios";
                	}
                }
                
                
                // Fecha de emisión
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNoFormat = comprobante.fechaPago;
                // Distrito - Código Postal
                String CodPos = "L01";
                if(comprobante.CodPos!=null && !comprobante.CodPos.toLowerCase().equals("null") && !comprobante.CodPos.equals(""))
                {
                	CodPos = comprobante.CodPos;
                }
                
                // Distrito - Ubigeo
                String Municp ="150101";
                if(comprobante.Municp!=null  && !comprobante.Municp.toLowerCase().equals("null") && !comprobante.Municp.equals(""))
                {
                	Municp = comprobante.Municp;
                }
                
                //ponemos el valor a CANCELADO para que inserte el comprobante de manera normal
                comprobante.comprobanteEstado = "CAN";
                
                //insertamos el comprobante normalmente
                InsertBean insertComprobante  = getInsertComprobante(comprobante, codigoVendedor, situacionJuridica, formatter, fechaNoFormat,
						CodPos, Municp);
                
            	//creamos el objeto a enviar
                SendComprobanteBean sendComprobanteBean = new SendComprobanteBean(Arrays.asList(insertComprobante));
                
            	//enviar al servidor
                Gson gson = new GsonBuilder().setDateFormat(com.farenet.nodo.maestro.api.util.UIUtils.FORMATO_FECHA).create();
                status = HTTPJsonEngine.postJSONHttp(urlComprobantes, null, gson.toJson(sendComprobanteBean).replace("\\t", ""));
                
                comprobanteService.updateSendedtoofisis(comprobante.id);
            	
            	if(anulado)
            	{
            		
                	//anulados
                	AnuladoBean insertAnulado = new AnuladoBean();
                	insertAnulado.nroComprobante = comprobante.nroComprobante;
                	if (comprobante.EstadoComprobanteSunat.equals("RCH")) {
						insertAnulado.fechaAnulacion=formatter.format(comprobante.fechaPago);
					}else {
	                	insertAnulado.fechaAnulacion = formatter.format(comprobante.fechaAnulacion);
					}
                	
                	ClienteBean cliente = new ClienteBean();
                	cliente.numDocumento = comprobante.documentoValidado.trim().replace(".", "");;
                	cliente.nombres = (comprobante.nombres==null)?"":""+comprobante.nombres;
                	cliente.tipoDocumento = situacionJuridica;
                	cliente.razonSocial = (comprobante.nombreCliente==null)?"":""+comprobante.nombreCliente;
                	cliente.apellidoPaterno = (comprobante.apellidos==null)?"":""+comprobante.apellidos;
                	cliente.apellidoMaterno = apeMat;
                	cliente.direccion = (comprobante.direccion==null)?"":""+comprobante.direccion;
                	cliente.distrito = (comprobante.distrito==null)?"":""+comprobante.distrito;
                	cliente.email = (comprobante.email==null)?"":""+comprobante.email;
                	cliente.telefono = (comprobante.telefono==null)?"":""+comprobante.telefono;
                	
                	insertAnulado.cliente = cliente;
                	
                	PagoBean pago = new PagoBean ();
                	
                	Map<String,String> formaPago = new HashMap<>();
                	formaPago.put("cod", comprobante.codigoCondicionPago);
                	pago.formaPago = formaPago;
                	
                	Double total =  comprobante.totalSinDescuento - comprobante.totalDescuento;
    				Double igv = total * 0.18;
    				Double subTotal = total - igv;
    				
    				Map<String,String> precio = new HashMap<>();
    				precio.put("subTotal", ""+subTotal);
    				precio.put("igv", ""+igv);
    				precio.put("total", ""+total);
                	
    				pago.precio = precio;
                	
                	insertAnulado.pago = pago;
                	
                	PlantaBean planta = new PlantaBean();
                	planta.codPlanta = comprobante.codigoPlanta;
                	planta.nombrePlanta = comprobante.nombrePlanta;
                	planta.sucursalOfisis = comprobante.sucursal;
                	planta.ubigeoOfisis = comprobante.ubigeo;
                	planta.zonaOfisis =comprobante.zona;
                	planta.empresaOfisis = comprobante.empresa;
                	planta.cuentaCajaEfectivo = comprobante.cuentaCajaEfectivo;
                	planta.codCaja = comprobante.codigoCaja;
                	
                	insertAnulado.planta = planta;
                	
                	//creamos el objeto a enviar
                	SendAnuladoBean sendAnuladoBean = new SendAnuladoBean(Arrays.asList(insertAnulado));
                    
                	trigger.launch("", "ofisis.anulado.comprobante", "SEND"+comprobante.nroComprobante+"" , comprobante.fechaPago);
                	
                	//enviar al servidor
                	gson = new GsonBuilder().setDateFormat(com.farenet.nodo.maestro.api.util.UIUtils.FORMATO_FECHA).create();
                	//status = HTTPJsonEngine.postJSONHttp(urlAnulados, null, gson.toJson(sendAnuladoBean).replace("\\t", ""));
                	
                	trigger.launch("", "ofisis.anulado.comprobante", "OK"+comprobante.nroComprobante+"" , comprobante.fechaPago);
                	
                	comprobanteService.updateSendedtoofisis(comprobante.id);
                	
               }
            	apeMat = "";
            	 trigger.launch("", "ofisis.termino.comprobante", comprobante.nroComprobante + " - " +comprobante.fechaPago + " - " + status.getStatuscode(), status.getStatuscode() + " - " + status.getStatus() + " - " + status.getMessage());
             
            } catch (Exception e) {
            	apeMat = "";
            	comprobanteService.updateNotSendedtoofisis(comprobante.id);
            	e.printStackTrace();
            	trigger.launch("", "ofisis.error.internal", "EXCEPTION COMPROBANTE " + comprobante.nroComprobante, Util.printError(e));
            }
            
           
        }
       
        
      
    }

	public InsertBean getInsertComprobante(ComprobanteOfisisBean comprobante, String codigoVendedor,
			String situacionJuridica, DateFormat formatter, Date fechaNoFormat, String CodPos, String Municp) {
		//creamos el objeto insertar comprobante
		InsertBean insertComprobante = new InsertBean();
		insertComprobante.nroComprobante = comprobante.nroComprobante;
		insertComprobante.fechaPago = formatter.format(fechaNoFormat);
		insertComprobante.conceptoInspeccion = "";
		
		DescuentoBean descuento = new DescuentoBean();
		descuento.tipodescuento = (comprobante.tipodescuento == null)?"":""+comprobante.tipodescuento;
		descuento.nombredescuento = (comprobante.desCampania == null)?(comprobante.desConvenio == null)?(comprobante.nombredescuento == null)?"":comprobante.desConvenio:comprobante.desCampania:comprobante.nombredescuento;
		insertComprobante.descuento = descuento;
		
		ClienteBean cliente = new ClienteBean();
		cliente.numDocumento = comprobante.documentoValidado.trim().replace(".", "");
		cliente.nombres = (comprobante.nombres==null)?"":""+comprobante.nombres;
		cliente.tipoDocumento = situacionJuridica;
		cliente.razonSocial = (comprobante.nombreCliente==null)?"":""+comprobante.nombreCliente;
		cliente.apellidoPaterno = (comprobante.apellidos==null)?"":""+comprobante.apellidos;
		cliente.apellidoMaterno = apeMat;
		cliente.direccion = (comprobante.direccion==null)?"":""+comprobante.direccion;
		cliente.distrito = (comprobante.distrito==null)?"":""+comprobante.distrito;
		cliente.email = (comprobante.email==null)?"":""+comprobante.email;
		cliente.telefono = (comprobante.telefono==null)?"":""+comprobante.telefono;
		
		insertComprobante.cliente = cliente;
		
		EstadoComprobanteBean estado = new EstadoComprobanteBean();
		PagoBean pago = new PagoBean ();
		Map<String,String> formaPago = new HashMap<>();
		formaPago.put("cod", comprobante.codigoCondicionPago);
		formaPago.put("tipo", "");
		pago.formaPago = formaPago;
		
		if(comprobante.comprobanteEstado.equals("CAN"))
		{
			estado.codigo = "1";
			estado.estado= "Activo";
			
			Map<String,String> precio = new HashMap<>();
			precio.put("subTotal", ""+comprobante.baseImponible);
			precio.put("igv", ""+comprobante.igv);
			precio.put("total", ""+comprobante.importeTotal);
			
			pago.precio = precio;
			
			if(anulado){
				Double total = comprobante.totalSinDescuento - comprobante.totalDescuento ;

				Double subTotal = total /1.18;
				Double igv = subTotal * 0.18;

				
				precio = new HashMap<>();
				precio.put("subTotal", ""+subTotal);
				precio.put("igv", ""+igv);
				precio.put("total", ""+total);
				
				pago.precio = precio;
			}
			
		}else if(comprobante.comprobanteEstado.equals("ANU"))
		{
			estado.codigo = "3";
			estado.estado= "Anulado";
			
			//calculamos el monto a enviar basado en total sin descuento y descuento
			
			Double total = comprobante.totalSinDescuento - comprobante.totalDescuento;
			
			Double subTotal = total /1.18;
			Double igv = subTotal * 0.18;

			
			Map<String,String> precio = new HashMap<>();
			precio.put("subTotal", ""+subTotal);
			precio.put("igv", ""+igv);
			precio.put("total", ""+total);
			
			pago.precio = precio;
		}
		
		
		insertComprobante.estadoComprobante = estado;
		
		
		
		
		
		insertComprobante.pago = pago;
		
		ConvenioBean convenio = new ConvenioBean();
		convenio.diasCredito = (comprobante.diasCredito==null)?"":""+comprobante.diasCredito;
		convenio.ruc = (comprobante.rucConvenio!=null)?comprobante.rucConvenio:"";
		convenio.nombreConvenio = (comprobante.nombreConvenio!=null)?comprobante.nombreConvenio:"";
		convenio.codEjecutivo = codigoVendedor;
		
		insertComprobante.convenio = convenio;
		
		
		
		TipoDocumentoBean tipo = new TipoDocumentoBean();
		tipo.codigo = comprobante.tipoDocumento;
		tipo.tipo = "";
		
		insertComprobante.tipoDocumento = tipo;
		
		PlantaBean planta = new PlantaBean();
		planta.codPlanta = comprobante.codigoPlanta;
		planta.nombrePlanta = comprobante.nombrePlanta;
		planta.sucursalOfisis = comprobante.sucursal;
		planta.ubigeoOfisis = comprobante.ubigeo;
		planta.zonaOfisis =comprobante.zona;
		planta.empresaOfisis = comprobante.empresa;
		planta.cuentaCajaEfectivo = comprobante.cuentaCajaEfectivo;
		planta.codCaja = comprobante.codigoCaja;
		
		insertComprobante.planta = planta;
		
		LocationBean locacion = new LocationBean();
		Map<String,String> postal = new HashMap<>();
		postal.put("codPostal", CodPos);
		postal.put("descripcion", "");
		
		List<Map<String, String>> lstPostal = new ArrayList<>();
		lstPostal.add(postal);
		
		
		Map<String,String> ubigeo = new HashMap<>();
		ubigeo.put("numCP", Municp);
		ubigeo.put("descripcion", "");
		
		
		List<Map<String, String>> lstUbigeo = new ArrayList<>();
		lstUbigeo.add(ubigeo);
		
		locacion.postal = lstPostal;
		locacion.ubigeo = lstUbigeo;
		
		insertComprobante.locacion = locacion;
		
		Persona ultimoPropietario = vehiculoService.getLastPropietario(comprobante.nromotor);
		
		if(ultimoPropietario!=null)
		{
			ClienteBean propietario = new ClienteBean();
			propietario.numDocumento = ultimoPropietario.getNrodocumentoidentidad().trim().replace(".", "");;
			propietario.nombres = (ultimoPropietario.getNombres()==null)?"":""+ultimoPropietario.getNombres();
			propietario.tipoDocumento = (ultimoPropietario.getTipodocumentoidentidad()==null ) ? (propietario.nombres != null && !propietario.nombres.equals("")) ? "DNI": "RUC" : ultimoPropietario.getTipodocumentoidentidad().getKey().toUpperCase(); 
			propietario.razonSocial = (ultimoPropietario.getNombrerazonsocial()==null)?"":""+ultimoPropietario.getNombrerazonsocial();
			propietario.apellidoPaterno = (ultimoPropietario.getApellidos()==null)?"":""+ultimoPropietario.getApellidos();
			propietario.apellidoMaterno = "";
			propietario.direccion = (ultimoPropietario.getDireccion()==null)?"":""+ultimoPropietario.getDireccion();
			propietario.distrito = (ultimoPropietario.getDistrito()== null)?"":""+ultimoPropietario.getDistrito().getNombre();
			propietario.email = (ultimoPropietario.getEmail()==null)?"":""+ultimoPropietario.getEmail();
			propietario.telefono = (ultimoPropietario.getTelefono()==null)?"":""+ultimoPropietario.getTelefono();
			
			
			insertComprobante.propietario = propietario;
		}
		insertComprobante.estadoComprobanteSunat=comprobante.EstadoComprobanteSunat;
		insertComprobante.tipoContado=comprobante.tipocontado;
		insertComprobante.pagomixto=comprobante.PagoMixto;
		
		return insertComprobante;
	}

	
}