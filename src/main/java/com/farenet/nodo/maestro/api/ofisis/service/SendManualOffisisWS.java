package com.farenet.nodo.maestro.api.ofisis.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.caja.domain.bean.ComprobanteOfisisBean;
import com.farenet.nodo.maestro.api.http.HTTPJsonEngine;
import com.farenet.nodo.maestro.api.ofisis.bean.AnuladoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.ClienteBean;
import com.farenet.nodo.maestro.api.ofisis.bean.InsertBean;
import com.farenet.nodo.maestro.api.ofisis.bean.PagoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.PlantaBean;
import com.farenet.nodo.maestro.api.ofisis.bean.SendAnuladoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.SendComprobanteBean;
import com.farenet.nodo.maestro.api.task.cron.SendOffisisWSTask;
import com.farenet.nodo.maestro.api.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class SendManualOffisisWS extends SendOffisisWSTask {
	
    private
    @Value("${ofisis.url}")
    String url;
    
    public List<String> sendManual(Date fechaMin, Date fechaMax, String codPlanta) {
    	
    	List<String> result = new ArrayList<>();
    	
        List<ComprobanteOfisisBean> lstInspeccionNotSended = comprobanteService.getNotSendedToOffisisByFechaAndPlanta(fechaMin, fechaMax, codPlanta);
        trigger.launch("", "ofisis.begin", "CANTIDAD A ENVIAR A OFFISIS ", lstInspeccionNotSended.size());
        
        // Establish the URL.
        String urlComprobantes = url + "/Comprobante/1";
        String urlAnulados = url + "/Anulado/1";
        
        // Enviar Cierres de Caja
        
        for (ComprobanteOfisisBean comprobante : lstInspeccionNotSended) {
        	
            try {
                // DATOS OFISIS
            	
                anulado = false;
                if (comprobante.comprobanteEstado.equals("ANU"))
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
                	insertAnulado.fechaAnulacion = formatter.format(comprobante.fechaAnulacion);
                	
                	ClienteBean cliente = new ClienteBean();
                	cliente.numDocumento = comprobante.documentoValidado.trim().replace(".", "");;
                	cliente.nombres = (comprobante.nombres==null)?"":""+comprobante.nombres;
                	cliente.tipoDocumento = situacionJuridica;
                	cliente.razonSocial = (comprobante.nombreCliente==null)?"":""+comprobante.nombreCliente;
                	cliente.apellidoPaterno = (comprobante.apellidos==null)?"":""+comprobante.apellidos;
                	cliente.apellidoMaterno = "";
                	cliente.direccion = (comprobante.direccion==null)?"":""+comprobante.direccion;
                	cliente.distrito = (comprobante.distrito==null)?"":""+comprobante.distrito;
                	cliente.email = (comprobante.email==null)?"":""+comprobante.email;
                	cliente.telefono = (comprobante.telefono==null)?"":""+comprobante.telefono;
                	
                	insertAnulado.cliente = cliente;
                	
                	PagoBean pago = new PagoBean ();
                	
                	Map<String,String> formaPago = new HashMap<>();
                	formaPago.put("cod", comprobante.codigoCondicionPago);
                	pago.formaPago = formaPago;
                	
                	Double total = comprobante.totalSinDescuento-comprobante.totalDescuento ;
    				Double subTotal = total /1.18;
    				Double igv = subTotal * 0.18;
    				
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
                    
                	trigger.launch("", "ofisis.anulado.comprobante", "SEND "+comprobante.nroComprobante+"" , comprobante.fechaPago);
                	
                	//enviar al servidor
                	gson = new GsonBuilder().setDateFormat(com.farenet.nodo.maestro.api.util.UIUtils.FORMATO_FECHA).create();
                	status = HTTPJsonEngine.postJSONHttp(urlAnulados, null, gson.toJson(sendAnuladoBean).replace("\\t", ""));
                	
                	trigger.launch("", "ofisis.anulado.comprobante", "OK "+comprobante.nroComprobante+"" , comprobante.fechaPago);
                	
                	comprobanteService.updateSendedtoofisis(comprobante.id);
                	
               }
            	 trigger.launch("", "ofisis.termino.comprobante", comprobante.nroComprobante + " - " +comprobante.fechaPago + " - " + status.getStatuscode(), status.getStatuscode() + " - " + status.getStatus() + " - " + status.getMessage());
             
            } catch (Exception e) {
            	comprobanteService.updateNotSendedtoofisis(comprobante.id);
            	e.printStackTrace();
            	trigger.launch("", "ofisis.error.internal", "EXCEPTION COMPROBANTE  | url = "+ urlComprobantes + " | "+ comprobante.nroComprobante, Util.printError(e));
            	
            	result.add(comprobante.nroComprobante);
            }
           
        }
		return result;
       
        
      
    }
	
}
