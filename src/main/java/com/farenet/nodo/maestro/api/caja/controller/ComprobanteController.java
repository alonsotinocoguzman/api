package com.farenet.nodo.maestro.api.caja.controller;

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

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Descuento;
import com.farenet.nodo.maestro.api.caja.domain.bean.ConsolidacionOrdenBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.ReciboBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.ReciboSunatBean;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@RequestMapping("comprobante")
public class ComprobanteController extends BaseController {
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@RequestMapping(value = "/recibo/{nrocomprobante}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ReciboBean getOneRecibo(@PathVariable String nrocomprobante) {
    	return comprobanteService.getoneRecibo(nrocomprobante);
    }
	
	@RequestMapping(value = "/recibo/{nrocomprobante}/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ReciboBean getOneRecibo(@PathVariable String nrocomprobante,@PathVariable String placa) {
    	return comprobanteService.getoneRecibo(nrocomprobante,placa);
    }

	@RequestMapping(value = "/recibos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReciboBean> getRecibos(  @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key,
            @Param("forma_pago") String forma_pago,
            @Param("ruc") String ruc) {
    	return comprobanteService.getRecibos(fechIni,fechFin,planta_key,forma_pago,ruc);
    }

	
	@RequestMapping(value = "/recibos/sunat", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReciboSunatBean> getRecibosSunat(  @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key,
            @Param("forma_pago") String forma_pago,
            @Param("ruc") String ruc) {
    	return comprobanteService.getRecibosSunat(fechIni,fechFin,planta_key,forma_pago,ruc);
    }
	
	@RequestMapping(value = "/todos/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<String> getAllRecibo(@PathVariable String placa) {
    	return comprobanteService.getAllComprobante(placa);
    }
	
	@RequestMapping(value = "/list/{planta_key}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<ComprobanteListBean> getAllComprobanteList(@PathVariable String planta_key, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column){
		return comprobanteService.getAllComprobanteList(planta_key, new PageBean(numPage, totalFilasPorPagina, sort, column));
		
	}
	
	@RequestMapping(value = "/list/{planta_key}/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<ComprobanteListBean> getAllComprobanteListByField(@PathVariable String planta_key,@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception{
		return comprobanteService.getAllComprobanteListAndField(planta_key,field, new PageBean(numPage, totalFilasPorPagina, sort, column));
		
	}
	
	@RequestMapping(value = "/nrocomprobante/{nrocomprobante}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Comprobante getOneByNrocomprobanteByPlanta(@PathVariable String nrocomprobante, @PathVariable String planta) throws Exception{
		return comprobanteService.getOneByNrocomprobanteAndPlanta(nrocomprobante, planta);
	}
	
	@RequestMapping(value = "/list/consolidacion/ot", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<ConsolidacionOrdenBean> getListConsolidacionBean(
			@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechini") Date fechini,
			@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechfin") Date fechfin,
			@Param("ruc") String ruc,
			@Param("empresa") String empresa) throws Exception{
		return comprobanteService.getConsolidacionBeanList(fechini, fechfin, ruc, empresa);
	}
	
	@RequestMapping(value = "/todos/consolidacion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody List<Comprobante> getAllComprobantesByIds(@RequestBody List<Long> ids) throws Exception{
		return comprobanteService.getAllComprobanteByIds(ids);
	}
	
}
