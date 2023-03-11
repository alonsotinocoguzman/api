package com.farenet.nodo.maestro.api.seguridad;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.service.TokenService;
import com.farenet.nodo.maestro.api.seguridad.service.UsuarioService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TokenService tokenService;
	 

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private TriggerTaskFactory trigger;
	
	public UserDetails loadUserByUsername(String token)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			
		String user = tokenService.validateUserFromToken(token);
				
		if(user==null)
		{
			trigger.launch(token,"app.access.failed", "Token dont exist or not valid", null);
			
			 throw new UsernameNotFoundException("Token dont exist or not valid");
		}
		
		
		Usuario usuario = userService.getUsuario(user);
		
		//guardar registro de actividad de la planta
		String planta = tokenService.getPlantaFromToken(token);
		trigger.launch(token, "sede.activity", planta, usuario.persona.getNombres());	   
			
		
		 return new User(usuario.user, "", true, true, true, true, usuario.getGrantedAuthority());
	}
	
	
}