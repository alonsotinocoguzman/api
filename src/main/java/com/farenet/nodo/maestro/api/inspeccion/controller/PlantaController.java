package com.farenet.nodo.maestro.api.inspeccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.PlantaConexion;
import com.farenet.nodo.maestro.api.inspeccion.service.PlantaService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@RequestMapping("/planta")
public class PlantaController extends BaseController{

	@Autowired
	private PlantaService plantaService;
	
	@RequestMapping(value = "/activos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<PlantaConexion> getAllPlantasActivos() throws Exception {

		return plantaService.getAllPlantasActivos();
	}
}
