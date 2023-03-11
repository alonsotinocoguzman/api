package com.farenet.nodo.maestro.api.caja.controller;

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
import com.farenet.nodo.maestro.api.caja.service.SeriedocumentoService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Seriedocumento;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoRepository;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class SeriedocumentoController extends BaseController{

	@Autowired
	private SeriedocumentoService seriedocumentoService;

	@Autowired
	private SeriedocumentoRepository seriedocumentoRepository;

	@RequestMapping(value = "/seriedocumentos/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Seriedocumento> getAll(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {

		return seriedocumentoService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	@RequestMapping(value = "/seriedocumento", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String save(@RequestBody Seriedocumento seriedocumento) {
		seriedocumentoRepository.save(seriedocumento);
		return "ok";
	}

	@RequestMapping(value = "/seriedocumentos/{seriedocumento_id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Seriedocumento getByKey(@PathVariable Long seriedocumento_id) {
		return seriedocumentoService.getOne(seriedocumento_id);
	}

	@RequestMapping(value = "/seriedocumento/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Seriedocumento> get(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return seriedocumentoService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}
}
