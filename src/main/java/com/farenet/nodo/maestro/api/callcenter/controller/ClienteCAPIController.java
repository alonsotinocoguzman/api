package com.farenet.nodo.maestro.api.callcenter.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.callcenter.bean.ClienteSMSCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.PropietarioCAPI;
import com.farenet.nodo.maestro.api.callcenter.service.ClienteCAPIService;
import com.farenet.nodo.maestro.api.util.UIUtils;

@RestController
@RequestMapping("/capi")
public class ClienteCAPIController {

	@Autowired
	private ClienteCAPIService clienteCAPIService;

	//nuevos servicios

	@RequestMapping(value = "/clientes/llamada/rescate", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<PropietarioCAPI> getAllClientesRescate(){
		return clienteCAPIService.getAllRescateClientes();
	}

	//INFOBIP
	//----------------

	@RequestMapping(value = "/clientes/sms/cliente/porvencer/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<ClienteSMSCAPI> getAllInfoBipSMSClientesPorVencer(@PathVariable String diasvencecert){

		return clienteCAPIService.getAllSMSClientePorVencerClientesfindByFecha(diasvencecert);

	}

	@RequestMapping(value = "/clientes/sms/cliente/vencido/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<ClienteSMSCAPI> getAllInfoBipSMSClientesVencido(@PathVariable String diasvencecert){

		return clienteCAPIService.getAllSMSClienteVencidoClientesfindByFecha(diasvencecert);

	}

	//----------
	//SIGELLA
	//------

	/*
	@RequestMapping(value = "/clientes/sms/porvencer/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<String> getAllSMSClientesPorVencer(@PathVariable String diasvencecert){
		return clienteCAPIService.getAllSMSPorVencerClientesfindByFecha(diasvencecert);

	}

	@RequestMapping(value = "/clientes/sms/vencido/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<String> getAllSMSClientesVencido(@PathVariable String diasvencecert){
		return clienteCAPIService.getAllSMSVencidoClientesfindByFecha(diasvencecert);

	}*/



	@RequestMapping(value = "/clientes/llamada/porvencer/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<PropietarioCAPI> getAllClientesPorVencer(@PathVariable String diasvencecert){
		return clienteCAPIService.getAllPorVencerClientesfindByFecha(diasvencecert);
	}

	@RequestMapping(value = "/clientes/llamada/vencido/{diasvencecert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<PropietarioCAPI> getAllClientesVencido(@PathVariable String diasvencecert){
		return clienteCAPIService.getAllVencidoClientesfindByFecha(diasvencecert);

	}


	//-----
	//NO SE USA


	@RequestMapping(value = "/clientes/{diasvencecert}/{diasvencidoscert}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<PropietarioCAPI> getAllClientes(@PathVariable String diasvencecert,@PathVariable String diasvencidoscert){
		return clienteCAPIService.getAllClientesfindByFecha(diasvencecert, diasvencidoscert);
	}

	//PARA GUARDAR LOS CLIENTES
	@RequestMapping(value = "/clientes/enviar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardar(@RequestBody PropietarioCAPI propietarioCAPI) {
		clienteCAPIService.guardar(propietarioCAPI);
		return "ok";
	}

}