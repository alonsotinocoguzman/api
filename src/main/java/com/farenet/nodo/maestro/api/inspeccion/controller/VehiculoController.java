package com.farenet.nodo.maestro.api.inspeccion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.service.VehiculoService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@RequestMapping("/vehiculo")
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class VehiculoController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(VehiculoController.class);

	@Autowired
	private VehiculoService vehiculoService;

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_VEHICULO','EDITAR_VEHICULO')")
	@RequestMapping(value = "/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Vehiculo> getAllVehiculos(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {

		return vehiculoService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_VEHICULO','EDITAR_VEHICULO')")
	@RequestMapping(value = "/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Vehiculo> getVehiculos(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return vehiculoService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_VEHICULO','EDITAR_VEHICULO')")
	@RequestMapping(value = "/placa/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Vehiculo> getVehiculosByPlaca(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return vehiculoService.getVehiculosByPlaca(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','EDITAR_VEHICULO')")
	@RequestMapping( method = RequestMethod.POST)
	public @ResponseBody String saveVehiculo(@RequestBody Vehiculo vehiculo) {
		vehiculoService.save(vehiculo);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','VER_INSPECCION','LISTAR_VEHICULO','EDITAR_VEHICULO')")
	@RequestMapping(value = "/{nromotor}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Vehiculo getOneByNroMotor(@PathVariable String nromotor) {
		return vehiculoService.getOneByNroMotor(nromotor);
	}
	
	@RequestMapping(value = "delete/{nroplaca}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String deleteOneByNroMotor(@PathVariable String nroplaca) throws Exception {
		vehiculoService.deleteByPlaca(nroplaca);
		return "ok";
	}
}
