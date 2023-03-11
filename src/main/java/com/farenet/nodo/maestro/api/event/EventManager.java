package com.farenet.nodo.maestro.api.event;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.task.TaskManager;
import com.farenet.nodo.maestro.api.task.domain.DataTask;

import reactor.core.Reactor;
import reactor.event.Event;
import reactor.function.Consumer;
import static reactor.event.selector.Selectors.R;
import static reactor.event.selector.Selectors.T;

@Component
public class EventManager {

	 @Autowired
	 private Reactor r;
	 
	 @Autowired
	 private TaskManager manager;
		
	 @Resource
	  private Properties taskProperties;
	 
	 @PostConstruct
     public void onStartUp() {
		 
		 		
		for (Entry<Object, Object> entry : taskProperties.entrySet())
		{
		   String value = entry.getValue().toString();
		    
		    if(value.indexOf("log")>=0)
		    {
		    	 r.on(R(entry.getKey().toString()), logEvent(entry.getKey().toString()));
		    }
		    
		   
		    
		    if(value.indexOf("execute")>=0)
		    {
		    	 r.on(R(entry.getKey().toString()), executeEvent(entry.getKey().toString()));
		    }
		}
		
		
     }

	 private Consumer<Event<DataTask>> executeEvent(String target) {
		 
	        return data -> manager.execute(target,data.getData());
	  }
	 
	 private Consumer<Event<DataTask>> logEvent(String target) {
		 
	        return data -> manager.log(target,data.getData());
	  }
	 
	 
}
