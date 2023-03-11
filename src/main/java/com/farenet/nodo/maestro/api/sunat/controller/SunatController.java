package com.farenet.nodo.maestro.api.sunat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Comprobantes;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.domain.ST_NotaDeCredito;
import com.farenet.nodo.maestro.api.sunat.services.ST_Service;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;

@RestController
@RequestMapping("/sunat")
public class SunatController {
	
	@Autowired
	private ST_Service st_Service;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@RequestMapping(value = "/sync", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String Sincronizar(@RequestBody ST_Mensaje st_Mensaje) throws Exception {
		st_Service.guardar(st_Mensaje);
		return "ok";
	}
	
	@RequestMapping(value = "/cargamasiva/nota-credito", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String NotaCreditoMasiva(@RequestBody List<ST_NotaDeCredito> notaCreditoBeans) throws Exception {
		return st_Service.CargaMasivaNotadeCredito(notaCreditoBeans);
	}
	
	@RequestMapping(value = "/cargamasiva/boletas-facturas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String ComprobantesMasivos(@RequestBody List<ST_Comprobantes> boletaFacturaBeans) throws Exception {
		return st_Service.cargaMasivaComprobante(boletaFacturaBeans);
	}
	

	@RequestMapping(value = "/comprobante/{nrocomprobante}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPDFByNroComprobante (@PathVariable String nrocomprobante) throws Exception  {
		return st_Service.getPDFMensajes(nrocomprobante);
	}
	
	@RequestMapping(value = "/comprobante/{nrocomprobante}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPDFByNroComprobante (@PathVariable String nrocomprobante, @PathVariable String planta) throws Exception  {
		return st_Service.getPDFMensajesBycomprobanteAndPlanta(nrocomprobante,planta);
	}
	
	@RequestMapping(value = "/series", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<String> getSeriesCargadas() throws Exception  {
		return st_Service.getSeriesGeneradas();
	}
	
	@RequestMapping(value = "/correlativo/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getUltimoCorelativoBySerie(@PathVariable String planta) throws Exception  {
		return st_Service.getUltimoCorrelativoByPlanta(planta);
	}
	
	@RequestMapping(value = "/dgf/comprobante/{nrocomprobante}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPdfByComprobanteAndPlanta(@PathVariable String nrocomprobante, @PathVariable String planta) throws Exception  {
		return st_Service.getpdfBySerieAndCorrelativo(nrocomprobante,planta);
	}
	
	@RequestMapping(value = "/dgf/comprobante/{serie}/{correlativo}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPdfBySerieAndCorrelativoAndPlanta(@PathVariable String serie, @PathVariable String correlativo, @PathVariable String planta) throws Exception  {
		return st_Service.getpdfBySerieAndCorrelativo(serie,correlativo,planta);
	}
	
	@RequestMapping(value = "/dgf/notacredito/{serie}/{correlativo}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPdfNotaCreditoBySerieAndCorrelativoAndPlanta(@PathVariable String serie, @PathVariable String correlativo, @PathVariable String planta) throws Exception  {
		return st_Service.getpdfNotaCreditoBySerieAndCorrelativo(serie,correlativo,planta);
	}
	
	@RequestMapping(value = "/inspeccion/generar/{nrodocumentoinspeccion}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String generarComprobante(@PathVariable String nrodocumentoinspeccion) throws Exception {
		return inspeccionService.generarComprobante(nrodocumentoinspeccion);
	}
	
	@RequestMapping(value = "/guardarpdf/{nrodocumentoinspeccion}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardarPdf(@RequestBody String pdf, @PathVariable String nrodocumentoinspeccion) throws Exception {
		inspeccionService.guardarPdf(pdf, nrodocumentoinspeccion);
		return "ok";
	}
	
	@RequestMapping(value = "/inspeccion/enviar/{nrodocumentoinspeccion}/{nrocomprobante}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String enviarComprobanteDgf(@PathVariable String nrodocumentoinspeccion, @PathVariable String nrocomprobante) throws Exception {
		st_Service.generarComprobante(nrodocumentoinspeccion, nrocomprobante);;
		return "ok";
	}

	
	@RequestMapping(value = "/anular/{nrocomprobante}/{planta}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String anularManual(@PathVariable String nrocomprobante,@PathVariable String planta, @RequestBody String observacion) throws Exception {
		return st_Service.anularComprobante(nrocomprobante, planta, observacion);
	}
	
	@RequestMapping(value = "/enviar/{planta}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String anularManual(@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fecha") Date fecha,
			@PathVariable String planta) throws Exception {
		st_Service.getNroComprobanteIns(planta, fecha);
		return "ok";
	}
	
	@RequestMapping(value = "/facturaconsolidada/evniar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String enviarFacturaConsolidada(@RequestBody List<Long> ids) throws Exception {
		st_Service.enviarFacturaConsolidadaByIdf(ids);
		return "ok";
	}
	
	@RequestMapping(value = "/stmensaje/{nrodocumentoinspeccion}/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getNroComprobanteByNroInspeccion(@PathVariable String nrodocumentoinspeccion, @PathVariable Long id) {
		return st_Service.getNroComprobanteByInspeccion(nrodocumentoinspeccion, id);
	}
	
	@RequestMapping(value = "/stmensaje/comprobante/{nrocomprobante}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getStmensaje(@PathVariable String nrocomprobante, @PathVariable String planta) throws Exception {
		return st_Service.getNroComprobante(nrocomprobante, planta);
	}
	@RequestMapping(value = "/comprobanteandinspeccion/{nrocomprobante}/{nroinspeccion}", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public String getStmensajenrocpmomprobante(@PathVariable String nrocomprobante, @PathVariable String nroinspeccion) throws Exception {
		return st_Service.getNroComprobantebyInspeccionAndComprobante(nrocomprobante,nroinspeccion);
	}

}
