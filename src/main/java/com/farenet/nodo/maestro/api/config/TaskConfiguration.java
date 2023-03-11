package com.farenet.nodo.maestro.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

import org.reflections.Reflections;
//import org.reflections.Reflections;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.farenet.nodo.maestro.api.task.domain.Execute;
import com.farenet.nodo.maestro.api.task.domain.Task;

@Configuration
public class TaskConfiguration {

	@Bean
	public PropertiesFactoryBean taskProperties() {
	    PropertiesFactoryBean bean = new PropertiesFactoryBean();
	    bean.setLocation(new ClassPathResource("tasks.properties"));
	    return bean;
	}
	
	@Bean 
	public ThreadPoolTaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(1);
	        executor.setMaxPoolSize(Integer.MAX_VALUE);
	        executor.setQueueCapacity(Integer.MAX_VALUE);
	        executor.initialize();
	        return executor;
		}
	
	@Bean 
	public Map<String,List<Class<Task>>> executeTaskMap(){
		
		Reflections reflections = new Reflections("com.farenet.nodo.maestro.api.task.logic");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Execute.class);
		
		Map<String, List<Class<Task>>> mapExecute = new ConcurrentHashMap<String, List<Class<Task>>>();
		
		for (Class<?> taskClass : annotated) {
			Execute task = taskClass.getAnnotation(Execute.class);
		    String target = task.target();
		    
		    List<Class<Task>> lstClass = mapExecute.get(target);
		    
		    if(lstClass==null)
		    {
		    	lstClass = new ArrayList<Class<Task>>();
		    	lstClass.add( (Class<Task>)taskClass);
		    	
		    	mapExecute.put(target, lstClass);
		    	
		    }else{
		    	
		    	lstClass.add( (Class<Task>)taskClass);
    	
		    }
		    
		}
      return mapExecute;
	}
	
	
}
