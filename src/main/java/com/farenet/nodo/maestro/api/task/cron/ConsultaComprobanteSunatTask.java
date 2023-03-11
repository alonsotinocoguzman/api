package com.farenet.nodo.maestro.api.task.cron;

import java.net.URL;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.sunat.domain.bean.ConsultaSunatBean;
import com.farenet.nodo.maestro.api.sunat.services.ST_MensajeService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;

import cl.dbnet.carga.CustomerETDLoadASP;
import cl.dbnet.consulta.ConsultaEstado;
import cl.dbnet.consulta.ConsultaEstadoResponse;
import cl.dbnet.consulta.Service1;
import cl.dbnet.consulta.Service1Soap;

@Component
public class ConsultaComprobanteSunatTask  {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ST_MensajeService st_MensajeService;
	
	@Autowired
    private TriggerTaskFactory trigger;
	
	private static final QName SERVICE_NAME = new QName("http://www.dbnet.cl", "Service1");
	
	Service1Soap port;
	
	@Async
    @Scheduled(cron="0 0 0-5 * * *")
	void execute(){
		
			List<ConsultaSunatBean> consultaSunatBeans = st_MensajeService.getAllSunatSinEnviar();
			
			URL wsdlURL = Service1.WSDL_LOCATION;
			Service1 ss = new Service1(wsdlURL, SERVICE_NAME);
	        port = ss.getService1Soap();
	        for(ConsultaSunatBean bean : consultaSunatBeans){
	        	try{
	        		String tipodocumento = bean.getTipodocumento();
		        	String[] comprobante = bean.getNrocomprobante().split("-");
		        	String resultado = port.consultaEstado(bean.getRucempresa(), Integer.valueOf(tipodocumento), Integer.valueOf(comprobante[1]), comprobante[0]);
		        	st_MensajeService.actualizarEstadoStMensaje(resultado.substring(0, resultado.indexOf(";")), bean.getIdst());
		        	trigger.launch("", "consultar.estado.sunat",  bean.getNrocomprobante(), resultado);
	        	}catch (Exception e) {
		        	// TODO: handle exception
	        		e.printStackTrace();
	        		UIUtils.printError(e);
		        	trigger.launch("", "consultar.estado.sunat.error",  bean.getNrocomprobante(), UIUtils.printError(e));
		        	
		        }
	        	
	        }
	        
		
	}
	
}
