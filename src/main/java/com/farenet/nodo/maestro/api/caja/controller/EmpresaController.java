package com.farenet.nodo.maestro.api.caja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.service.EmpresaService;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.linea.repository.EmpresaRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class EmpresaController extends BaseController{

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private EmpresaRepository empresaRepository;

	@RequestMapping(value = "/empresas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Empresa> getAll() throws Exception {

		return empresaService.getAll();
	}
	
	
	@RequestMapping(value = "/empresas/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Empresa> getAll(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {

		return empresaService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	@RequestMapping(value = "/empresa", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String save(@RequestBody Empresa empresa) {
		empresaRepository.save(empresa);
		return "ok";
	}

	@RequestMapping(value = "/empresa/{empresa_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Empresa getByKey(@PathVariable String empresa_key) {
		return empresaService.getOne(empresa_key);
	}

	@RequestMapping(value = "/empresa/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Empresa> get(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return empresaService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}
	
	@RequestMapping(value = "/empresa/planta/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getOneByPlanta(@PathVariable String planta) {
		return empresaService.getOneByPlanta(planta);
	}
}
