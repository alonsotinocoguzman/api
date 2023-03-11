package com.farenet.nodo.maestro.api.caja.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.service.PersonaService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@RestController
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class PersonaController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	private PersonaService personaService;

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_PERSONA','EDITAR_PERSONA')")
	@RequestMapping(value = "/personas/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Persona> getAllPersonas(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {
		logger.debug("obtener todas las personas");

		return personaService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_PERSONA','EDITAR_PERSONA')")
	@RequestMapping(value = "/personas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Persona> getAllPersonas() throws Exception {
		logger.debug("obtener todas las personas disponibles");

		return personaService.getAllPersonasNaturalesHabilitado();
	}

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_PERSONA','EDITAR_PERSONA')")
	@RequestMapping(value = "/personas-empresas/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Persona> getAllEmpresas(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {
		logger.debug("obtener todas las empresas disponibles");

		return personaService.getAllEmpresasHabilitado(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_PERSONA','EDITAR_PERSONA')")
	@RequestMapping(value = "/persona/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Persona> getPersonas(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {
		logger.debug("obtener todas las personas");

		return personaService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','EDITAR_PERSONA')")
	@RequestMapping(value = "/persona", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveSite(@RequestBody Persona persona) {
		personaService.savePersona(persona);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_PERSONA','EDITAR_PERSONA')")
	@RequestMapping(value = "/persona/{nrodocumento}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Persona getOneByNroDocumento(@PathVariable String nrodocumento) {
		return personaService.getOneByNroDocumento(nrodocumento);
	}

}
