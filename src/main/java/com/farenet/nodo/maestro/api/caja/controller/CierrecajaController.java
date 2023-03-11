package com.farenet.nodo.maestro.api.caja.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDetalle;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaList;
import com.farenet.nodo.maestro.api.caja.domain.DetalleCierrecaja;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaRepository;
import com.farenet.nodo.maestro.api.caja.service.CierreService;
import com.farenet.nodo.maestro.api.caja.service.CierrecajaService;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.service.LineaService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@RequestMapping("/cierrecaja")
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB','CIERRE_LISTAR_CAJA','CIERRE_CREAR_CAJA')")
public class CierrecajaController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CierrecajaController.class);

    @Autowired
    private CierreService cierreService;

    @Autowired
    private LineaService lineaService;

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private CierrecajaService cierrecajaService;

    @Autowired
    private MaestroService maestroService;

    @Autowired
    private CierrecajaRepository cierrecajaRepository;
    

    @RequestMapping(value = "/get-por-cierre/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Cierrecaja getPorCierre(@PathVariable String planta_key) {
        Planta planta = maestroService.getPlantaByKey(planta_key);
        List<Linea> lineas = maestroService.listarLineasByPlanta(planta);

        Cierrecaja cierrecaja = new Cierrecaja();
        List<CierrecajaDetalle> cierrecajaDetalles = new ArrayList<>();
        // el cierre que corresponde
        Cierre cierre = cierreService.getCurrentCierreInCierrecajaByPlantaToday(planta);
        cierrecaja.setCierre(cierre);
        for (Linea linea : lineas) {
            CierrecajaDetalle cierrecajaDetalle = new CierrecajaDetalle();

            if (cierre != null) {

                double montoSaldo = cierrecajaService.getUltimoSaldoByLinea(linea);
                double montoTotal = comprobanteService.getMontoTotalByDateHourByLineaCancelado(cierre, linea);

                cierrecajaDetalle.setLinea(linea);
                cierrecajaDetalle.setMontoSaldo(montoSaldo);
                cierrecajaDetalle.setMontoTotal(montoTotal);
            }
            cierrecajaDetalles.add(cierrecajaDetalle);
        }
        cierrecaja.setCierrecajadetalle(cierrecajaDetalles);
        return cierrecaja;
    }
    
    @RequestMapping(value = "/get-detalle-por-cierre/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<List<DetalleCierrecaja>> getDetallePorCierre(@PathVariable String planta_key) {
        Planta planta = maestroService.getPlantaByKey(planta_key);
        List<Linea> lineas = maestroService.listarLineasByPlanta(planta);
        List<List<DetalleCierrecaja>> listDetalleCierrecajas = new ArrayList<>();
        for (Linea linea : lineas) {
            List<DetalleCierrecaja> detalleCierres = new ArrayList<>();

            // el cierre que corresponde
            Cierre cierre = cierreService.getCurrentCierreInCierrecajaByPlantaToday(planta);

            if (cierre != null) {
                detalleCierres = comprobanteService.getDetallesMontosByLinea(cierre, linea);
            }
            listDetalleCierrecajas.add(detalleCierres);
        }
        return listDetalleCierrecajas;
    }

    @RequestMapping(value = "/detalle/{cierre_caja_id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<List<DetalleCierrecaja>> getDetalleCierrecaja(@PathVariable String cierre_caja_id) {
        Cierrecaja cierrecaja = cierrecajaRepository.findOneById(cierre_caja_id);
        List<List<DetalleCierrecaja>> listDetalleCierrecajas = new ArrayList<>();

        for (CierrecajaDetalle cierrecajaDetalle : cierrecaja.getCierrecajadetalle()) {
            List<DetalleCierrecaja> detalleCierres = new ArrayList<>();

            if (cierrecaja != null) {
                detalleCierres = comprobanteService.getDetallesMontosByCierrecajadetalle(cierrecajaDetalle);
            }
            listDetalleCierrecajas.add(detalleCierres);
        }
        return listDetalleCierrecajas;
    }

    @RequestMapping(value = "/{cierre_caja_id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Cierrecaja getCierrecaja(@PathVariable String cierre_caja_id) {
        Cierrecaja cierrecaja = cierrecajaRepository.findOneById(cierre_caja_id);
        return cierrecaja;
    }

    @RequestMapping(value = "/cierres", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierre> listar() {
        return cierreService.getAll();
    }

    @RequestMapping(value = "/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Cierrecaja> getAllCierreCaja(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
                                                       @PathVariable Ordenar sort, @PathVariable String column) throws Exception {

        return cierrecajaService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "/cierre-planta/{planta}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierrecaja> getAllCierreCajaByPlanta(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
            @PathVariable Ordenar sort, @PathVariable String column) throws Exception {
    	return null;
    }
    
    @RequestMapping(value = "/all/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierrecaja> getAllCierreCaja(@PathVariable String planta_key) throws Exception {

        return cierrecajaService.getAll(planta_key);
    }

    @RequestMapping(value = "/list/{planta_key}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<CierrecajaList> getAllCierreCajaList(@PathVariable String planta_key, @PathVariable int numPage,
                                                               @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
            throws Exception {
        return cierrecajaService.getCierreCajalist(planta_key, new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "/list/{planta_key}/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<CierrecajaList> getAllCierreCajaList(@PathVariable String planta_key,@PathVariable String field, @PathVariable int numPage,
                                                               @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
            throws Exception {
        return cierrecajaService.getCierreCajalistByfield(planta_key, field, new PageBean(numPage, totalFilasPorPagina, sort, column));
    }

    // @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveSite(@RequestBody Cierrecaja cierrecaja) {
        cierrecajaService.grabarCierrrecajaYActualizarEstadocierreparcial(cierrecaja);
        return "ok";
    }
    
    @RequestMapping(value = "/detalle-cierre", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierrecaja> getCierreCajaDetalleByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fech") Date fech,
            @Param("planta") String planta) throws Exception {
        return cierrecajaService.getDetalleCierreCajaByDateRangeByPlanta(fech, planta);

    }
    
}
