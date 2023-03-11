package com.farenet.nodo.maestro.api.ofisis.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.service.PlantaService;
import com.farenet.nodo.maestro.api.ofisis.service.OfisisManualService;
import com.farenet.nodo.maestro.api.ofisis.service.SendMailOfisis;
import com.farenet.nodo.maestro.api.ofisis.service.SendManualOffisisWS;

@RestController
@RequestMapping("/ofisis")
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class OfisisManualController extends BaseController {
	
	@Autowired
	private OfisisManualService ofisisManualService;
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private PlantaService plantaService;
	
	@Autowired
	private SendManualOffisisWS sendManualOffisisWS;
	
	@Autowired
	private SendMailOfisis sendMailOfisis;
	
	@RequestMapping(value = "/comprobante", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public void sendOfisisManualAndMail(
			 @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
			 @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
			 @Param("planta") String planta, @Param("correo") String correo) throws Exception {
		
		String nomPlanta = plantaService.getOneByKey(planta).getNombre();
		long cantCompro = comprobanteService.getCountComprobantesByFechaandPlanta(fechIni, fechFin, planta);
		List<String> detalle = sendManualOffisisWS.sendManual(fechIni, fechFin, planta);
		sendMailOfisis.execute(nomPlanta, detalle, cantCompro,correo,fechIni,fechFin);
		
		
	}

}
