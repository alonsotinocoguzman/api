package com.farenet.nodo.maestro.api.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.service.PerfilService;


public abstract class AbstractService {

	@Autowired
	private PerfilService perfilService;
	
	public Perfil getPerfil()
	{
		//validar los roles del usuario logeado
				if(SecurityContextHolder.getContext().getAuthentication() !=null)
				{
					User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
							
					String key = "";
					
					for(GrantedAuthority auth : user.getAuthorities())
					{
						key = auth.getAuthority();
					}
					
					
					return perfilService.getPerfil(key);
				}
				return null;
	}
	
	public String getToken()
	{
		if(SecurityContextHolder.getContext().getAuthentication() !=null)
		{
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			return user.getUsername();
		}
		
		return null;
	}
}
