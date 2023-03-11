package com.farenet.nodo.maestro.api.soporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.soporte.domain.bean.DatosCertificadoBean;
import com.farenet.nodo.maestro.api.soporte.domain.bean.EdicionFirmaBean;
import com.farenet.nodo.maestro.api.soporte.service.EdicionService;

@RestController
@RequestMapping("/edicion")
public class EdicionController {
	
	@Autowired
	private EdicionService edicionService;
	
	@RequestMapping(value = "/firma", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String cambiarFirma(@RequestBody EdicionFirmaBean firmaBean) {
		edicionService.cambiarFirma(firmaBean);
		return "ok";
	}
	
	@RequestMapping(value = "/datocertificado", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String cambiarDatosCertificado(@RequestBody DatosCertificadoBean certificadoBean) throws Exception {
		edicionService.cambiarDatosdeCertificado(certificadoBean);
		return "ok";
	}
	

}
