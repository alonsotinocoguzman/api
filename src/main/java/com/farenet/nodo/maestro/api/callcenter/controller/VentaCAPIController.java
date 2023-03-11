package com.farenet.nodo.maestro.api.callcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.callcenter.bean.PropietarioCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.VentaCAPIBean;
import com.farenet.nodo.maestro.api.callcenter.service.VentaCAPIService;

@RestController
@RequestMapping("ventacall")
public class VentaCAPIController {
	
	@Autowired
	private VentaCAPIService ventaCAPIService;

	@RequestMapping(value = "/venta/{placa}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public VentaCAPIBean getVentaByPlaca(@PathVariable String placa, @PathVariable String planta){
		//VentaCAPIBean bean = new VentaCAPIBean();
		//bean = ventaCAPIService.getVentafindByPlaca(placa,planta);
		return null;
	}

}
