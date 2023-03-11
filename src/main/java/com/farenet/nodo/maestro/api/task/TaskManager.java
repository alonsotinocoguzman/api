package com.farenet.nodo.maestro.api.task;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.caja.controller.PersonaController;
import com.farenet.nodo.maestro.api.event.domain.Log;
import com.farenet.nodo.maestro.api.event.repository.LogRepository;
import com.farenet.nodo.maestro.api.task.domain.DataTask;
import com.farenet.nodo.maestro.api.task.domain.Task;

@Component
public class TaskManager {

	private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource
	private Map<String,List<Class<Task>>> executeTaskMap;
	
	@Autowired
	LogRepository eventRepository;
	
	@Autowired
	private ApplicationContext context;

	public void execute(String target,DataTask data)
	{
		
		if(executeTaskMap!=null)
		{
			List<Class<Task>> lstTaskClass = executeTaskMap.get(target);
			
			if(lstTaskClass!=null)
			{
				for(Class<Task> taskClass : lstTaskClass)
				{
					sendTask(data, taskClass);
				}
			}
			
			//para los vacios
			lstTaskClass = executeTaskMap.get("");
			
			if(lstTaskClass!=null)
			{
				for(Class<Task> taskClass : lstTaskClass)
				{
					sendTask(data, taskClass);
				}
			}
			
		}
			
		
	}

	
	public void log(String target,DataTask data)
	{
		Log log = new Log();
		log.migrate(data);
				
		eventRepository.saveLog(log);
		
			
		
	}
	
	

	private void sendTask(DataTask data, Class<Task> taskClass) {
		try {
			
			Task task = taskClass.getConstructor(DataTask.class,ApplicationContext .class).newInstance(data,context);
			
			taskExecutor.execute(task);
			
			
		} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
}
