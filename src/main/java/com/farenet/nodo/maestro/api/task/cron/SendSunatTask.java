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

import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.domain.bean.ConsultaSunatBean;
import com.farenet.nodo.maestro.api.sunat.services.ST_MensajeService;
import com.farenet.nodo.maestro.api.sunat.services.ST_Service;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;

import cl.dbnet.consulta.Service1;
import cl.dbnet.consulta.Service1Soap;
import net.minidev.json.writer.BeansMapper.Bean;

@Component
public class SendSunatTask {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ST_MensajeService st_MensajeService;
	
	@Autowired
	private ST_Service st_Service;
	
	@Async
	@Scheduled(cron="0 0 23 * * *")
	void execute(){
		List<ConsultaSunatBean> list = st_MensajeService.getAllSinSt_mensaje();
        
        for(ConsultaSunatBean bean : list) {
        	List<ST_Mensaje> mensaje = st_MensajeService.getMensaje(bean.getNrodocumentoinspeccion());
        	if(mensaje == null || mensaje.isEmpty()) {
        		st_Service.generarComprobante(bean.getNrodocumentoinspeccion(), bean.getNrocomprobante());
        	}
        }
		
	}
	
}
