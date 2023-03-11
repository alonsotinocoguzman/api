package com.farenet.nodo.maestro.api.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.task.domain.DataTask;

import reactor.core.Reactor;
import reactor.event.Event;

@Component
public class TriggerTaskFactory {


	 @Autowired
	 private Reactor r;

	
	public  void launch(String user,String target, String message, Object data)
	{
		DataTask dataTask = new DataTask();
		dataTask.user= user;
		dataTask.target = target;
		dataTask.message = message;
		dataTask.data = data;
		
		r.notify(target, 
				Event.wrap(dataTask));
		
	}
	
}
