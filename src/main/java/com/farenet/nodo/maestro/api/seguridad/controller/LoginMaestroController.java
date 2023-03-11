package com.farenet.nodo.maestro.api.seguridad.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.seguridad.bean.UserAuth;
import com.farenet.nodo.maestro.api.seguridad.service.TokenService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;

@RestController
public class LoginMaestroController extends BaseController{

	@Autowired
	private TokenService tokenService;

	
	@Autowired
	private TriggerTaskFactory trigger;
	 
	@RequestMapping(value = "/loginmaestro", method = RequestMethod.POST)
	public UserAuth login(HttpServletRequest request, @RequestHeader Map<String,String> headers) throws Exception {
			
		if(headers.containsKey("uname") && headers.containsKey("pw")){
			
			try{
				UserAuth auth = tokenService.login(headers.get("uname"),headers.get("pw"),
						request.getRemoteAddr(),request.getHeader("user-agent"));
				
				trigger.launch(headers.get("uname"), "app.login.successful", "Login OK", null);
				
				return auth;
				
			}catch(UsernameNotFoundException uex){
				trigger.launch(headers.get("uname"),  "app.login.failed", "User or password unknown : " + uex.getMessage(), null);
				throw new SecurityException(uex.getMessage());
			}
		}
		
		//COLOCAR EL EVENTO DE LOGIN - AUTENTIFICADO
		trigger.launch("", "app.login.failed", "Login Not OK " + headers.keySet(), null);
		
		
		
		throw new SecurityException("Datos incorrectos");
	}
}
