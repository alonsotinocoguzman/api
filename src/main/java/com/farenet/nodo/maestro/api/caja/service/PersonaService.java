package com.farenet.nodo.maestro.api.caja.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.controller.PersonaController;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.repository.PersonaRepository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionRepository;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class PersonaService {

	private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);

	@Autowired
	private PersonaRepository personaRepository;

	public ResultPageBean<Persona> getAll(PageBean pageBean) {

		ResultPageBean<Persona> result = new ResultPageBean<Persona>(
				personaRepository.findAll(pageBean.convertToPage()));

		return result;
	}

	public List<Persona> getAllPersonasNaturalesHabilitado() {

		List<Persona> result = personaRepository.findPersonasNaturalesByEstado(true);

		return result;
	}

	public ResultPageBean<Persona> getAllEmpresasHabilitado(String field, PageBean pageBean) {
		ResultPageBean<Persona> result = new ResultPageBean<Persona>(personaRepository.findPersonasEmpresaByEstado(true,
				"%" + field.toUpperCase() + "%", field.toUpperCase(), pageBean.convertToPage()));

		return result;
	}

	public ResultPageBean<Persona> get(String field, PageBean pageBean) {

		ResultPageBean<Persona> result = new ResultPageBean<Persona>(
				personaRepository.findAllByCriteriaByEstado("%" + field + "%", true, pageBean.convertToPage()));

		if (result.getContent().size() != 0)
			return result;

		result = new ResultPageBean<Persona>(
				personaRepository.findByNombresLikeIgnoreCase("%" + field + "%", pageBean.convertToPage()));

		if (result.getContent().size() != 0)
			return result;

		result = new ResultPageBean<Persona>(
				personaRepository.findByNombrerazonsocialLikeIgnoreCase("%" + field + "%", pageBean.convertToPage()));

		if (result.getContent().size() != 0)
			return result;

		result = new ResultPageBean<Persona>(
				personaRepository.findByApellidosLikeIgnoreCase("%" + field + "%", pageBean.convertToPage()));

		return result;

	}

	@Cacheable("personas")
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}
	
	public Persona getOneByNroDocumento(String nrodocumentoidentidad){
		return personaRepository.findOneByNrodocumentoidentidad(nrodocumentoidentidad);
	}

	public void savePersona(Persona persona) {
		try {
			personaRepository.saveAndFlush(persona);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}

	// usar desde GeneralController.
	@CacheEvict(cacheNames = "personas", allEntries = true)
	public void refreshCache() {
		logger.info("Restableciendo cache personas");
	}
}
