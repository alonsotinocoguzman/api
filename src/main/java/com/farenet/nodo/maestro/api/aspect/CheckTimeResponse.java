package com.farenet.nodo.maestro.api.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;


@Aspect
@Component
public class CheckTimeResponse {

    @Autowired
    private TriggerTaskFactory trigger;
    
	 @Around("execution(* com.farenet.nodo.maestro.api..*Controller.*(..))")
	    public Object inCITVServicios(ProceedingJoinPoint pjp) throws Throwable {
		 
		 Date inicio = new Date();
		 
		 Object result = pjp.proceed();
		 
		 Date fin = new Date();
		 
		 MethodSignature signature = (MethodSignature) pjp.getSignature();
		 Method method = signature.getMethod();


	     trigger.launch("", "app.time", pjp.getTarget().getClass().getName()+ "."+ method.getName(), ""+ (fin.getTime()-inicio.getTime()));
		 
	     return result;
	 }
}
