package com.farenet.nodo.maestro.api.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;


@Component
public class BaseController {


	@Autowired
	private TriggerTaskFactory trigger;
	
	//MANEJO DE EXCEPCIONES

	public static class NotAuthorizedException extends RuntimeException{
        public NotAuthorizedException(String message) {
            super(message);
        }
    }
	
	public static class NotFoundException extends RuntimeException{
        public NotFoundException(String message) {
            super(message);
        }
    }
	
	/*
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public Map<String, String> notAutorizedHandler(NotAuthorizedException e){
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
	    return Collections.singletonMap("message", sw.toString());
	}
	
	@ExceptionHandler()
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> notFoundHandler(NotFoundException e){
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
	    return Collections.singletonMap("message",sw.toString());
	}
	*/

	@ExceptionHandler({Exception.class, RuntimeException.class})
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public void  handleException(HttpServletResponse response, Exception e) throws IOException {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		trigger.launch("","error.internal", "Error IO",sw.toString());
		
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), sw.toString());
		
	}
}
