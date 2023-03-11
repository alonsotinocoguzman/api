package com.farenet.nodo.maestro.api.seguridad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	
	
	public Perfil saveProfile(Perfil profile)
	{
		perfilRepository.save(profile);
		
		return profile;
	}
	
	
	public List<Perfil> getAll()
	{
		return perfilRepository.findAll();
	}
	
	public Perfil updatePerfil(Perfil profile)
	{
		perfilRepository.saveAndFlush(profile);
		
		return profile;
	}
	
	public Perfil getPerfil(String profile)
	{
		return perfilRepository.findByClave(profile);
	}
	
}
