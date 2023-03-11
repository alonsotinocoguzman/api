package com.farenet.nodo.maestro.api.util;

import com.farenet.nodo.maestro.api.caja.domain.Descuento;
import com.farenet.nodo.maestro.api.caja.domain.DescuentoDetalle;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToLog implements Runnable {

	private String target;
	private Object object;
	private TriggerTaskFactory trigger;
	private String username;



	public ObjectToLog(String target, Object object,  String username, TriggerTaskFactory trigger) {
		this.target = target;
		this.object = object;
		this.trigger = trigger;
		this.username = username;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub


		SimpleDateFormat df = new SimpleDateFormat(UIUtils.FORMATO_FECHA);
		ObjectMapper mapper = new ObjectMapper().setDateFormat(df);

		try {
			trigger.launch( username, target, 	"MAPEAR OBJECTO", mapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
