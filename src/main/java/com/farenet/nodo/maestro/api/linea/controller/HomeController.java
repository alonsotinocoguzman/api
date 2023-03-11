package com.farenet.nodo.maestro.api.linea.controller;

import java.util.List;
import java.util.Map;

import com.farenet.nodo.maestro.api.linea.domain.TiempoTranscurrido;
import com.farenet.nodo.maestro.api.linea.domain.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.farenet.nodo.maestro.api.linea.domain.ResultadoMaquina;
import com.farenet.nodo.maestro.api.linea.service.AppLineaService;

@RestController
@RequestMapping("/linea")
@Scope("request")
public class HomeController extends BaseController {
	
	@Autowired
	private AppLineaService appLineaService;
	
    @RequestMapping(value = "/inspecciones/{linea_key}/{planta_key}/{pc}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Map<String, VehiculoMaquina> getListPlacas(@PathVariable String linea_key, @PathVariable String planta_key, @PathVariable String pc) throws Exception{
    	return appLineaService.getPlacas(linea_key, planta_key, pc);
    }
    
    @RequestMapping(value = "/appresultado", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	 public String guardarResultado(@RequestBody ResultadoLineaBean lineaBean) throws Exception{
		 appLineaService.guardarResultado(lineaBean);
		 return "ok"; 
	 }
    
    @RequestMapping(value = "/vehiculo/{nroinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public VehiculoResultadoBean getVehiculoMaquinaByNroinspeccion(@PathVariable String nroinspeccion) throws Exception {
    	return appLineaService.getVehiculomaquina(nroinspeccion);
    }

    @RequestMapping(value = "/inspeeccion_observacion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String updateInspeccionObs(@RequestBody ObservacionManualBean bean) throws Exception {
    	return appLineaService.updateOservacionManual(bean);
    }
    @RequestMapping(value="/versioncontrol",method = RequestMethod.GET,produces="application/json; charset=utf-8")
    public List<VersionControl> getVersionControl()throws Exception{
        return appLineaService.getVersionControl();
    }
    @RequestMapping(value="/tiempotranscurrido/{nroinspeccion}",method = RequestMethod.GET,produces="application/json; charset=utf-8")
    public List<TiempoTranscurrido> getTiempotranscurrido(@PathVariable String nroinspeccion)throws Exception{
        return appLineaService.getTiempoTranscurrido(nroinspeccion);
    }

}
