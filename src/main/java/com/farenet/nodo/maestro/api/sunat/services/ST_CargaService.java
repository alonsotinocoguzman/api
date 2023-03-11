package com.farenet.nodo.maestro.api.sunat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.sunat.domain.ST_Carga;
import com.farenet.nodo.maestro.api.sunat.repository.ST_CargaRepository;

@Service
public class ST_CargaService {
	
	@Autowired
	private ST_CargaRepository st_CargaRepository;
	
	public void guardar(ST_Carga st_Carga) {
		st_CargaRepository.save(st_Carga);
	}

}
