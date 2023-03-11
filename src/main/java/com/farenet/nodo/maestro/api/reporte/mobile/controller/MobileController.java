package com.farenet.nodo.maestro.api.reporte.mobile.controller;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.inspeccion.service.LineaService;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.AnulacionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CartaSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CorrelativosBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CortesiaSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.DescuentoSaveBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ErrorImpresionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.EstadisticaBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlacaInspeccionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlantBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ReportBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.TraspasoBean;
import com.farenet.nodo.maestro.api.reporte.mobile.service.MobileService;
import com.farenet.nodo.maestro.api.soporte.service.EdicionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MobileController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private InspeccionService inspeccionService;

    @Autowired
    private LineaService lineaService;

    @Autowired
    private MobileService mobileService;
    
    @Autowired
    private EdicionService edicionService;

    //buscar reinspeccion
    @RequestMapping(value = "/mobileapi/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public PlacaInspeccionBean getInspeccion(@PathVariable String placa)
            throws Exception {

        return inspeccionService.getPlacaInspeccion(placa);
    }

    @RequestMapping(value = "/mobileapi/plants", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<PlantBean> getPlants() {
        return lineaService.getPlantas();
    }

    @RequestMapping(value = "/mobileapi/reports", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ReportBean getReport(@RequestParam("date") long dataInSeconds) throws Exception {
        Date date = new Date(dataInSeconds * 1000);
        return mobileService.getReport(date);
    }
    
    @RequestMapping(value = "/mobileapi/guardar/descuento", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveDescuento(@RequestBody DescuentoSaveBean descuentoSaveBean) throws Exception {
    	mobileService.saveDescuento(descuentoSaveBean);
    	return "ok";
    }
    
    @RequestMapping(value = "/mobileapi/guardar/cortesia", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveDescuento(@RequestBody CortesiaSaveBean cortesiaSaveBean) throws Exception {
    	mobileService.saveCortesia(cortesiaSaveBean);
    	return "ok";
    }
    
    @RequestMapping(value = "/mobileapi/guardar/cartas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveDescuento(@RequestBody CartaSaveBean cartaSaveBean) throws Exception {
    	mobileService.saveCarta(cartaSaveBean);
    	return "ok";
    }
    
    
    @RequestMapping(value = "/mobileapi/estadisticas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<EstadisticaBean> getEstadistica(@RequestParam("fecha") String fecha) throws Exception {
        return mobileService.getEstadistica(fecha);
    }
    //app soporte
    
    @RequestMapping(value = "/mobileapi/placas/{placa}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<PlacaInspeccionBean> getAllPlacaInspeccionByPlacaAndFecha(@RequestParam("fecha") String fecha, @PathVariable String placa, @PathVariable String planta) throws Exception {
    	return mobileService.getPlacaInspeccionByPlacaandFecha(placa, fecha, planta);
    }
    
    @RequestMapping(value = "/mobileapi/placas-no-consolidado/{placa}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<PlacaInspeccionBean> getAllPlacaNoConsolidadoInspeccionByPlacaAndFecha(@RequestParam("fecha") String fecha, @PathVariable String placa, @PathVariable String planta) throws Exception {
    	return mobileService.getPlacaInspeccionNoConsolidadoByPlacaandFecha(placa, fecha, planta);
    }
    
    
    
    @RequestMapping(value = "/edicion/correlativos", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveCorrelativos(@RequestBody CorrelativosBean correlativosBean) throws Exception {
    	edicionService.actualizarCorrelativos(correlativosBean);
    	return "ok";
    }
    
    @RequestMapping(value = "/edicion/error-impresion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveErrorImpresion(@RequestBody ErrorImpresionBean errorImpresionBean) throws Exception {
    	edicionService.generarErrorImpresion(errorImpresionBean);
    	return "ok";
    }
    
    @RequestMapping(value = "/edicion/traspaso", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String guardarTraspaso(@RequestBody TraspasoBean traspasoBean) throws Exception {
    	edicionService.traspasarResultado(traspasoBean);
    	return "ok";
    }
    
    @RequestMapping(value = "/edicion/anular", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String anularInspeccion(@RequestBody AnulacionBean anulacionBean) throws Exception {
    	edicionService.anularInspeccion(anulacionBean);
    	return "ok";
    }
    
}
