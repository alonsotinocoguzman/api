package com.farenet.nodo.maestro.api.caja.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Formapago;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteestadoRepository;
import com.farenet.nodo.maestro.api.caja.repository.FormapagoRepository;
import com.farenet.nodo.maestro.api.caja.service.CierreService;
import com.farenet.nodo.maestro.api.caja.service.CierrecajaService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.repository.LineaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;

@RestController
@RequestMapping("/cierre")
public class CierreController extends BaseController {
	
	@Autowired
	private CierrecajaService cierrecajaService;
	
	@Autowired
	private CierreService cierreService;
	
	@Autowired
	private CierrecajaRepository cierrecajaRepository;
	
	@Autowired
	private ComprobanteestadoRepository comprobanteestadoRepository;
	
	@Autowired
	private FormapagoRepository formapagoRepository;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private LineaRepository lineaRepository;
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private CierreRepository cierreRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CierrecajaController.class);
	
	@RequestMapping(value = "/ultimo-planta/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Cierrecaja getLastCierrecaja(@PathVariable String planta_key) {
        Cierrecaja cierrecaja = cierrecajaService.getLastCierreCajaByPlanta(planta_key);
        return cierrecaja;
    }
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierre> listar() throws Exception {
        return cierreRepository.findAllOrderPlanta();
    }
	
	@RequestMapping(value = "/{cierre_caja_id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Cierrecaja getCierrecaja(@PathVariable String cierre_caja_id) {
        Cierrecaja cierrecaja = cierrecajaRepository.findOneById(cierre_caja_id);
        return cierrecaja;
    }
	
	@RequestMapping(value = "/comprobanteestado/{key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Comprobanteestado getComprobanteEstadoByKey(@PathVariable String key) {
        return comprobanteestadoRepository.findOneByKey(key);
    }
	
	@RequestMapping(value = "/formapago/{key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Formapago getFormaPagoBykey(@PathVariable String key) {
        return formapagoRepository.findOneByKey(key);
    }
	
	@RequestMapping(value = "/inspecciones/reporte", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getAllReportByFechaByPlanta(
			@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta){
		
		return inspeccionService.getAllByDate(fechIni, fechFin, planta);
	}
	
	@RequestMapping(value = "/inspecciones/reporte/duplicado", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getAllDuplicado(
			@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta){
		
		return inspeccionService.getAllDuplicado(fechIni, fechFin, planta);
	}
	
	/*@RequestMapping(value = "/inspeccion/reporte/noanu/{fechini}/{fechfin}/{formapago}/{anulado}/{linea}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getByFormapagoByNoAnuladoByLinea(@PathVariable Long fechini, @PathVariable Long fechfin,
			@PathVariable String formapago, @PathVariable String anulado,@PathVariable String linea)
			throws Exception {

		return inspeccionService.getByFormapagoByNoAnuladoByLinea(new Date(fechini),new Date(fechfin),
				formapago, anulado, linea);
	}*/
	
	@RequestMapping(value = "/inspeccion/reporte/anu/{fechini}/{fechfin}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getByFormapagoByAnuladoByLinea(@PathVariable Long fechini, @PathVariable Long fechfin,
			@PathVariable String planta)
			throws Exception {

		return inspeccionService.getByFormapagoByAnuladoByLinea(new Date(fechini),new Date(fechfin),planta);
	}
	
	@RequestMapping(value = "/inspecciones/obtener", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getByFechaByPlanta(
			@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta){
		
		return inspeccionService.getAllByFecha(fechIni, fechFin, planta);
	}
	
	@RequestMapping(value = "/guardar" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveSite(@RequestBody Cierrecaja cierrecaja) {
        cierrecajaService.grabarCierrrecajaYActualizarEstadocierreparcial(cierrecaja);
        return "ok";
    }
	
	@RequestMapping(value = "/inspeccion/guardar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardar(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.guardar(inspeccion);
		return "ok";
	}
	
	@RequestMapping(value = "/lineas/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Linea> getAllLineasByPlanta(@PathVariable String planta_key)
			throws Exception {
		return lineaRepository.findByPlanta(maestroService.getPlantaByKey(planta_key));
	}

}
