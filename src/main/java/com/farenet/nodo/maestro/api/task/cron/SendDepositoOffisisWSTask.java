package com.farenet.nodo.maestro.api.task.cron;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.farenet.nodo.maestro.api.caja.service.CierrecajaDepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDeposito;
import com.farenet.nodo.maestro.api.caja.service.CierrecajaService;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.http.HTTPJsonEngine;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.inspeccion.service.VehiculoService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.ofisis.bean.BancoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.DepositoBean;
import com.farenet.nodo.maestro.api.ofisis.bean.PlantaBean;
import com.farenet.nodo.maestro.api.ofisis.bean.SendDepositoBean;
import com.farenet.nodo.maestro.api.util.StatusHttp;
import com.farenet.nodo.maestro.api.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;

@Component
public class SendDepositoOffisisWSTask {
    @Autowired
    private InspeccionService inspeccionService;
    @Autowired
    private ComprobanteService comprobanteService;
    @Autowired
    private CierrecajaService cierrecajaService;
    
    @Autowired
    private VehiculoService vehiculoService;
    
    @Autowired
    private CierrecajaDepositoService cierrecajaDepositoService;
    @Autowired
    private TriggerTaskFactory trigger;
    private
    @Value("${ofisis.url}")
    String url;
    File archivo=new File("C:\\JSon.txt");
    private StatusHttp status = new StatusHttp();

    @Async
    //@Scheduled(cron= "0 0/50 0-4,14-18 * * *")
//    @Scheduled(cron= "0 0 23 * * *")
    //@Scheduled(fixedDelay=500)
    public void execute() {
       List<Cierrecaja> lstCierreCaja = cierrecajaService.getNotSendedToOffisis();
        trigger.launch("", "ofisis.begin", "CANTIDAD DE CIERRES A ENVIAR ", lstCierreCaja.size());
        System.out.println("entro al execute");
        
        // Establish the URL.
        String urlDepositos = url + "/Deposito/1";
        

        // Enviar Cierres de Caja
       
        
        //envio depositos
        trigger.launch("", "ofisis.empezo.cierre", "CIERRE " , lstCierreCaja.size());
        for (Cierrecaja cierreCaja : lstCierreCaja) {
        	
        	
            try {
                
                for (CierrecajaDeposito cierredeposito : cierreCaja.getCierrecajadeposito()) {
                    if (cierredeposito.getSendedtooffisis()) {
                        continue;
                    }
                    
                    trigger.launch("", "ofisis.empezo.cierre.deposito", "DEPOSITO " , cierredeposito.getId());
                    
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNoFormat = cierredeposito.getFechaDeposito();
                    
                   
                    //creamos el objeto deposito
                    DepositoBean deposito = new DepositoBean();
                    deposito.fechaDeposito = formatter.format(fechaNoFormat);
                    deposito.monto = ""+cierredeposito.getMonto();
                    
                    BancoBean banco = new BancoBean();
                    banco.nombre = cierredeposito.getCuentacorriente().getEntidadfinanciera().getNombre();
                    banco.nroCta =  cierredeposito.getCuentacorriente().getNroctacorriente().trim();
                    banco.codCtaCte = cierredeposito.getCuentacorriente().getCtaCteOFISIS().trim();
                    banco.codConcepto = cierredeposito.getCuentacorriente().getCodCptoOFISIS().trim();
                    banco.ctaContable = cierredeposito.getCuentacorriente().getCtaContableOFISIS();
                    banco.voucher = ""+cierredeposito.getNroVoucher();
                    
                    
                    deposito.datosBanco = banco;
                    

                	PlantaBean planta = new PlantaBean();
                	planta.codPlanta = cierreCaja.getPlanta().getKey();
                	planta.nombrePlanta = cierreCaja.getPlanta().getNombre();
                	planta.cuentaCajaEfectivo = cierreCaja.getPlanta().getCuentaCajaEfectivoOFISIS();
                	
                	deposito.planta = planta; 
                    
                	//creamos el objeto a enviar
                	
                	SendDepositoBean sendDepositoBean = new SendDepositoBean(Arrays.asList(deposito));
                    
                	//FileWriter escribir=new FileWriter(archivo,true);
                    
                	//enviar al servidor
	               	Gson gson = new GsonBuilder().setDateFormat(com.farenet.nodo.maestro.api.util.UIUtils.FORMATO_FECHA).create();
	               	//escribir.write(gson.toJson(sendDepositoBean).replace("\\t", ""));
	               	//escribir.close();
	               	System.out.println(gson.toJson(sendDepositoBean).replace("\\t", ""));
	               	status = HTTPJsonEngine.postJSONHttp(urlDepositos, null, gson.toJson(sendDepositoBean).replace("\\t", ""));
                    
                    cierredeposito.setSendedtooffisis(true);
                    cierrecajaDepositoService.update(cierredeposito);
                    
                    trigger.launch("", "ofisis.termino.cierre.deposito", "DEPOSITO " + deposito.fechaDeposito + " " + deposito.monto + " - "+ status.getStatuscode(),cierredeposito.getId() + " - "+ status.getStatus());
	                
                }
                
                
             
            } catch (Exception e) {
            	
            	e.printStackTrace();
                trigger.launch("", "ofisis.error.internal", "EXCEPTION CIERRE " + cierreCaja.getId(), Util.printError(e));
            }
            
        }
        
    }

	
}