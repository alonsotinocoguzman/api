package com.farenet.nodo.maestro.api.inspeccion.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionInicioBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionListBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionSupportSedeBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.ReinspeccionBean;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.inspeccionMtcBean;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@Scope("prototype")
public class InspeccionController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(InspeccionController.class);

	@Autowired
	private InspeccionService inspeccionService;

	@RequestMapping(value = "/inspeccion/validar/{nroinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getIdByNroInspeccion (@PathVariable String nroinspeccion) throws Exception  {
		return inspeccionService.getIdByNroInspeccion(nroinspeccion);
	}
	
	@RequestMapping(value = "/inspecciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Inspeccion> getInspecciones(  @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key,
            @Param("forma_pago") String forma_pago,
            @Param("ruc") String ruc) {
    		return inspeccionService.getInspecciones(fechIni,fechFin,planta_key,forma_pago,ruc);
    }

	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspecciones/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Inspeccion> getAllInspecciones(@PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return inspeccionService.getAll(new PageBean(numPage, totalFilasPorPagina, sort, column));

	}

//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Inspeccion> getInspeccion(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return inspeccionService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	//buscar reinspeccion
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/reinspeccion/{placa}/{concepto}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getReinspeccionInspeccion(@PathVariable String placa, @PathVariable String concepto, @PathVariable String planta)
			throws Exception {

		return inspeccionService.getReinspeccionLastByPlacaAndConceptoAndPlanta("%" + placa + "%", concepto, planta);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/existe/reinspeccion/{placa}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<ReinspeccionBean> getReinspeccion(@PathVariable String placa, @PathVariable String planta)
			throws Exception {

		//return inspeccionService.getReinspeccionBean(planta, placa);
		return inspeccionService.getReinspeccionBean(placa,planta);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/existe/duplicado/{placa}/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getPlacaExistente(@PathVariable String placa, @PathVariable String planta)
			throws Exception {

		return inspeccionService.getInspeccionExiste(planta, placa);
	}
	
	//buscar reinspeccion
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/reinspeccion/nrodocumento/{nrodocumentoreinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Inspeccion getReinspeccion(@PathVariable String nrodocumentoreinspeccion)
			throws Exception {

		return inspeccionService.getReinspeccionByNrodocumentoinspeccion(nrodocumentoreinspeccion);
	}
	
	//buscar para ser usado en duplicado
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/duplicado/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getDuplicadoInspeccion(@PathVariable String placa)
			throws Exception {

		return inspeccionService.getAprobadoLastByPlaca("%" + placa + "%");
	}
	
	//buscar inspecciones
//		@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
		@RequestMapping(value = "/inspeccion/buscar/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
		public List<Inspeccion> getInspeccionByPlaca(@PathVariable String placa)
				throws Exception {

			return inspeccionService.getAllInspeccinoesByPlaca(placa);
		}

	/// ----------------

	//@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','VER_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/cliente/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<InspeccionListBean> getInspeccionByCliente(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {

		return inspeccionService.getByCliente(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	// --------------
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','VER_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/{nrodocumentoinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Inspeccion get(@PathVariable String nrodocumentoinspeccion) {

		return inspeccionService.get(nrodocumentoinspeccion);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
		@RequestMapping(value = "/inspecciones/proceso/inicio/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
		public List<InspeccionInicioBean> getAllInspeccionesInicioProceso(@PathVariable String planta_key){
			return inspeccionService.getAllInspeccionesInicioByPlanta(planta_key);
		}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspecciones/proceso/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccion> getAllInspeccionesProceso(@PathVariable String planta_key){
		return inspeccionService.getAllInspeccionesByPlanta(planta_key);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspecciones/proceso/placa/{planta_key}/{placa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Inspeccion getAllInspeccionesByPlacaProceso(@PathVariable String planta_key,@PathVariable String placa){
		return inspeccionService.getAllInspeccionesByPlantaAndPlaca(planta_key,placa);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar/proceso", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody Inspeccion guardarProceso(@RequestBody Inspeccion inspeccion) throws Exception {
		return inspeccionService.guardarProceso(inspeccion);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar/linea", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardarLinea(@RequestBody Inspeccion inspeccion) throws Exception {

		 inspeccionService.guardarPaseALinea(inspeccion);
		 return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardar(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.guardar(inspeccion);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar/servicio", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardarServicio(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.guardarServicio(inspeccion);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar/duplicado", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardarDuplicado(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.guardarDuplicado(inspeccion);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/guardar/consolidado", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String guardarConsolidado(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.guardarConsolidado(inspeccion);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/eliminar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String deleteByInspeccion(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.delete(inspeccion);
		return "ok";
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','VER_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/comprobante/{nrocomprobante}/{empresa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Inspeccion getInspeccionByComprobante(@PathVariable String nrocomprobante,@PathVariable String empresa) {
		return inspeccionService.getInspeccionByComprobante(nrocomprobante,empresa);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','VER_INSPECCION','CREAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/nroot/{nroot}/{empresa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Inspeccion getInspeccionByNroOt(@PathVariable String nroot,@PathVariable String empresa) {
		return inspeccionService.getInspeccionByNroOt(nroot, empresa);
	}
	
//	@PreAuthorize("hasAnyAuthority('LISTA_INSPECCION','CREAR_INSPECCION','CERTIFICAR_INSPECCION')")
	@RequestMapping(value = "/inspeccion/anular/guardar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String saveAnular(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.saveAndAnular(inspeccion);
		return "ok";
	}
	
	@RequestMapping(value = "/inspeccion/support/sede/{nroinspeccion}", method = RequestMethod.GET,  produces = "application/json; charset=utf-8")
	public InspeccionSupportSedeBean getOneSuportByIdOrPlaca(@PathVariable String nroinspeccion) {
		return inspeccionService.getOneSupportById(nroinspeccion);
	}
	
	@RequestMapping(value = "/inspeccion/support/sede/save", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	public String saveInspeccionSupport(@RequestBody InspeccionSupportSedeBean bean) throws Exception {
		inspeccionService.saveSupportSede(bean);
		return "ok";
	}
	
	@RequestMapping(value = "/inspeccion/obtener/mtc/{inspeccionini}/{inspeccionfin}", method = RequestMethod.GET,  produces = "application/json; charset=utf-8")
	public List<inspeccionMtcBean> getInspeccionMtc(@PathVariable String inspeccionini, @PathVariable String inspeccionfin) throws Exception {
		return inspeccionService.getAllByRangeInspeccion(inspeccionini,inspeccionfin);
	}
	
	/*@RequestMapping(value = "/inspeccion/datoscomercial/{placa}",method=RequestMethod.GET,produces="application/json; charset=utf-8")
	public List<DatosComercialBean> getDatosComercial(@PathVariable String placa) throws Exception{
		return inspeccionService.getPlacaComercial(placa);
	}*/
	
	@RequestMapping(value = "/inspeccion/validarlinea/{nroinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getLineaByNroInspeccion (@PathVariable String nroinspeccion) throws Exception  {
		return inspeccionService.getLineaByNroInspeccion(nroinspeccion);
	}
	
	@RequestMapping(value = "/inspeccion/existe/inicio/{placa}/{tipoinspeccion}/{tipocertificado}/{conceptoinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String getValidarInspeccionExisteLinea(@PathVariable String placa,
			@PathVariable String tipoinspeccion,
			@PathVariable String tipocertificado,
			@PathVariable String conceptoinspeccion) throws Exception {
		return inspeccionService.getValidarInspecionInicio(placa, tipoinspeccion, tipocertificado, conceptoinspeccion);
	}
	
	@RequestMapping(value = "/inspeccion/sync", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	public String saveInspeccionSupport(@RequestBody Inspeccion inspeccion) throws Exception {
		inspeccionService.sync(inspeccion);
		return "ok";
	}
	
}
