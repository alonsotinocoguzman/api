package com.farenet.nodo.maestro.api.task.cron;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.caja.service.DescuentoService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.Util;

@Component
public class DeshabilitarUuidTask {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
    private TriggerTaskFactory trigger;
	
	@Autowired
	private DescuentoService descuentoService;
	
	@Async
	@Scheduled(cron="0 0 23 * * *")
	void execute(){
		try {
			descuentoService.deshabilitarCodigo();
		}catch (Exception e) {
			trigger.launch("", "descuentos.masivo.actualizar.error", "EXCEPTION AL DESACTIVAR DESCUENTO", Util.printError(e));
		}
		
	}

}
