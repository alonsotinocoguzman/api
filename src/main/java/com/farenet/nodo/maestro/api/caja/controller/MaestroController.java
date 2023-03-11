package com.farenet.nodo.maestro.api.caja.controller;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.*;
import com.farenet.nodo.maestro.api.caja.domain.bean.CitvInfoBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.MaquinaEditedBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.PrecioConceptoBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.SistEventsBean;
import com.farenet.nodo.maestro.api.caja.service.ConceptoPrecioService;
import com.farenet.nodo.maestro.api.caja.service.IncorporacionService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.caja.service.MaquinaService;
import com.farenet.nodo.maestro.api.inspeccion.domain.*;
import com.farenet.nodo.maestro.api.inspeccion.repository.LineaRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipoPlacaRepository;
import com.farenet.nodo.maestro.api.linea.domain.*;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/maestro")
public class MaestroController extends BaseController{

	// private static final Logger logger =
	// LoggerFactory.getLogger(MaestroController.class);

	@Autowired
	private MaestroService maestroService;

	@Autowired
	private PlantaRepository plantaRepository;
	
	@Autowired
	private IncorporacionService incorporacionService;
	
	@Autowired
	private LineaRepository lineaRepository;
	
	@Autowired
	private MaquinaService maquinaservice;
	
	@Autowired
	private ConceptoPrecioService precioconceptoservice;
	
	@Autowired 
	private TipoPlacaRepository tipoplacaRepository;

	@RequestMapping(value = "/periodoreinspeccion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Periodoreinspeccion> listarPeriodoreinspecciones() {
		return maestroService.listarPeriodoreinspecciones();
	}

	@RequestMapping(value = "/empresas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Empresa> listarEmpresas() {
		return maestroService.listarEmpresas();
	}

	@RequestMapping(value = "/plantas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Planta> listarPlantas() {
		return maestroService.listarPlantas();
	}

	@RequestMapping(value = "/tipoplacas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TipoPlaca> listarTipoPlacas() {
		return maestroService.listarTipoPlacas();
	}

	/*@RequestMapping(value = "/citvanterior", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<CitvAnterior> listarcitvanterior() {
		return maestroService.listarCITV();
	}*/
	
	

	@RequestMapping(value = "/lineas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Linea> listarLineas() {
		return maestroService.listarLineas();
	}

	@RequestMapping(value = "/lineas/por-planta/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Linea> listarLineasPorPlanta(@PathVariable String planta_key) {
		Planta planta = maestroService.getPlantaByKey(planta_key);
		return maestroService.listarLineasByPlanta(planta);
	}

	@RequestMapping(value = "/conceptoinspecciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Conceptoinspeccion> listarConceptosDeInspecciones() {
		return maestroService.listarConceptoinspecciones(true);
	}

	@RequestMapping(value = "/formasdepagos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Formapago> listarFormasDePagos() {
		return maestroService.listarFormasDePagos();
	}
	
	@RequestMapping(value = "/tipopagodescuentos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TipoPagoDescuento> listarTipoPagoDescuento() {
		return maestroService.listarTipoPagoDescuento();
	}
	
	@RequestMapping(value = "/tipodecontados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TipoContado> listarTiposDeContados() {
		return maestroService.listarTiposDeContado();
	}

	@RequestMapping(value = "/entidadesfinancieras", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Entidadfinanciera> listarEntidadesFinancieras() {
		return maestroService.listarEntidadesFinancieras();
	}

	@RequestMapping(value = "/cuentascorrientes", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Cuentacorriente> listarCuentasCorrientes() {
		return maestroService.listarCuentasCorrientes();
	}

	@RequestMapping(value = "/monedas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Moneda> listarMonedas() {
		return maestroService.listarMonedas();
	}

	@RequestMapping(value = "/tarjetas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tarjeta> listarTarjetas() {
		return maestroService.listarTarjetas();
	}

	@RequestMapping(value = "/tiposdescuentos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipodescuento> listarTiposDescuentos() {
		return maestroService.listarTiposDescuentos();
	}

	@RequestMapping(value = "/tipodocumentos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipodocumento> listarTiposDocumentos() {
		return maestroService.listarTiposDocumentos();
	}

	@RequestMapping(value = "/categorias", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Categoria> listarCategorias() {
		return maestroService.listarCategorias();
	}

	@RequestMapping(value = "/marcas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Marca> listarMarcas() {
		return maestroService.listarMarcas();
	}

	@RequestMapping(value = "/tipoconvenio", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TipoConvenio> listarConvenios(){return maestroService.listarTipoConvenio();}

	@RequestMapping(value = "/modelos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Modelo> listarModelos() {
		return maestroService.listarModelos();
	}

	@RequestMapping(value = "/combustibles", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Combustible> listarCombustibles() {
		return maestroService.listarCombustibles();
	}

	@RequestMapping(value = "/colores", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Color> listarColores() {
		return maestroService.listarColores();
	}

	@RequestMapping(value = "/clasesdevehiculos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Vehiculoclase> listarClasesDeVehiculos() {
		return maestroService.listarClasesDeVehiculos();
	}

	@RequestMapping(value = "/carrocerias", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Carroceria> listarCarrocerias() {
		return maestroService.listarCarrocerias();
	}

	@RequestMapping(value = "/tipodocumentoidentidades", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipodocumentoidentidad> listarTipodocumentoidentidad() {
		return maestroService.listarTipodocumentoidentidad();
	}

	@RequestMapping(value = "/aseguradoras", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Aseguradora> listarAseguradoras() {
		return maestroService.listarAseguradoras();
	}

	@RequestMapping(value = "/paises", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Pais> listarPaises() {
		return maestroService.listarPaises();
	}

	@RequestMapping(value = "/departamentos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Departamento> listarDepartamentos() {
		return maestroService.listarDepartamentos();
	}

	@RequestMapping(value = "/provincias", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Provincia> listarProvincias() {
		return maestroService.listarProvincias();
	}

	@RequestMapping(value = "/distritos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Distrito> listarDistritos() {
		return maestroService.listarDistritos();
	}

	@RequestMapping(value = "/maquinas/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Maquina> listarMaquinas(@PathVariable String planta_key) {
		Planta planta = plantaRepository.findOneByKey(planta_key);
		return maestroService.listarMaquinasByPlanta(planta);
	}
	
	@RequestMapping(value = "/etapas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Etapa> listarEtapas() {
		return maestroService.listarEtapas();
	}

	@RequestMapping(value = "/lineas-etapas/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<LineaEtapa> listarLineaEtapas(@PathVariable String planta_key) {
		Planta planta = plantaRepository.findOneByKey(planta_key);
		return maestroService.listarLineasEtapasByPlanta(planta);
	}

	@RequestMapping(value = "/normas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Norma> listarNormas() {
		return maestroService.listarNormas();
	}
	@RequestMapping(value = "/categorianorma/{categoria_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<NormaBean> listarNormitas(@PathVariable String categoria_key) throws Exception {
		return maestroService.getNormaByCategoria(categoria_key);
	}

	@RequestMapping(value = "/subnormas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Subnorma> listarSubnormas() {
		return maestroService.listarSubnormas();
	}

	@RequestMapping(value = "/tipomaquinas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipomaquina> listarTipomaquinas() {
		return maestroService.listarTipomaquinas();
	}

	@RequestMapping(value = "/reglafrenos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglafreno> listarReglafrenos() {
		return maestroService.listarReglafrenos();
	}

	@RequestMapping(value = "/reglagases", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglagas> listarReglagases() {
		return maestroService.listarReglagases();
	}

	@RequestMapping(value = "/reglaopacidades", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglaopacidad> listarReglaopacidades() {
		return maestroService.listarReglaopacidades();
	}

	@RequestMapping(value = "/reglasonoras", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglasonora> listarReglasonoras() {
		return maestroService.listarReglasonoras();
	}

	@RequestMapping(value = "/reglasuspenciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglasuspension> listarReglasuspenciones() {
		return maestroService.listarReglasuspenciones();
	}

	@RequestMapping(value = "/reglaalineaciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglaalineacion> listarReglaalineacion() {
		return maestroService.listarReglaalineacion();
	}

	@RequestMapping(value = "/reglaluces", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglaluces> listarReglaluces() {
		return maestroService.listarReglaluces();
	}

	@RequestMapping(value = "/reglaprofundidades", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Reglaprofundidad> listarReglaprofundidades() {
		return maestroService.listarReglaprofundidades();
	}

	@RequestMapping(value = "/tipoinspecciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipoinspeccion> listarTipoinspeccion() {
		return maestroService.listarTipoinspecciones();
	}

	@RequestMapping(value = "/tipocertificados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipocertificado> listarTipocertificados() {
		return maestroService.listarTipocertificados();
	}

	@RequestMapping(value = "/tipoautorizaciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Tipoautorizacion> listarTipoautorizaciones() {
		return maestroService.listarTipoautorizaciones();
	}

	@RequestMapping(value = "/comprobanteestados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Comprobanteestado> listarComprobanteestados() {
		return maestroService.listarComprobanteestados();
	}

	@RequestMapping(value = "/inspeccionestados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Inspeccionestado> listarInspeccionestados() {
		return maestroService.listarInspeccionestados();
	}

	@RequestMapping(value = "/perfiles", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Perfil> listarPerfiles() {
		return maestroService.listarPerfiles();
	}

	@RequestMapping(value = "/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Maestro> getAllMaestros(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {
		return maestroService.listarMaestros(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	@RequestMapping(value = "/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<Maestro> getMaestros(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
			throws Exception {
		return maestroService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Maestro getByByKey(@PathVariable String key) {
		return maestroService.getByKey(key);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveSite(@RequestBody Maestro maestro) {
		maestroService.save(maestro);
		return "ok";
	}

	
	
	
	//sync  maestros
	@RequestMapping(value = "/color", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveColor(@RequestBody Color color) {
		maestroService.saveColor(color);
		return "ok";
	}
	
	@RequestMapping(value = "/marca", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveMarca(@RequestBody Marca marca) {
		maestroService.saveMarca(marca);
		return "ok";
	}
	
	@RequestMapping(value = "/carroceria", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveCarroceria(@RequestBody Carroceria carroceria) {
		maestroService.saveCarroceria(carroceria);
		return "ok";
	}
	
	@RequestMapping(value = "/modelo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveModelo(@RequestBody Modelo modelo) {
		maestroService.saveModelo(modelo);
		return "ok";
	}
	
	@RequestMapping(value = "/clasedevehiculo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveClase(@RequestBody Vehiculoclase clase) {
		maestroService.saveClase(clase);
		return "ok";
	}
	
	@RequestMapping(value = "/condicionvigenciacertificao", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<CondicionvigenciaCertificado> listarCondicionvigenciacertificado() {
		return maestroService.listarCondicionvigenciacertificado();
	}
	
	@RequestMapping(value = "/tipo-poliza/{key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public TipoPoliza getTipoPolizaByKey(@PathVariable String key) {
		return maestroService.getTipoPolizaByKey(key);
	}
	
	@RequestMapping(value = "/tipo-polizas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TipoPoliza> getAllTipoPoliza() {
		return maestroService.getAllTipoPoliza();
	}
	
	@RequestMapping(value = "/seriesdocumentos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Seriedocumento> listarSeriedocumentos() {
		return maestroService.listarSeriesdocumentos();
	}
	
	@RequestMapping(value = "/seriesdocumento/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Seriedocumento getOneByid(@PathVariable String id) {
		return maestroService.getSerieDocumentoByid(id);
	}
	
	@RequestMapping(value = "/seriesdocumentos/planta/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Seriedocumento> getOneByPlantaAndLinea(@PathVariable String planta_key) {
		return maestroService.getSerieDocumentoByPlanta(planta_key);
	}
	
	@RequestMapping(value = "/seriesdocumento/{planta_key}/{linea_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Seriedocumento getOneByPlantaAndLinea(@PathVariable String planta_key,@PathVariable String linea_key) {
		return maestroService.getSerieDocumentoByPlantaAndLinea(planta_key, linea_key);
	}
	
	@RequestMapping(value = "/seriesdocumento", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveSerieDocumento(@RequestBody Seriedocumento seriedocumento) {
		maestroService.saveSerieDocumento(seriedocumento);
		return "ok";
	}
	
	@RequestMapping(value = "/seriesdocumentobase/planta/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public SeriedocumentoBase getOneByPlanta(@PathVariable String planta) {
		return maestroService.getSeriedocumentoBaseByPlanta(planta);
	}
	
	@RequestMapping(value = "/seriesdocumentobase/nextidinspeccion/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getSiguienteNroInspeccion(@PathVariable String planta) {
		return maestroService.nextIdInspeccion(planta);
	}
	
	@RequestMapping(value = "/seriesdocumentoot/nextot/{empresa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getSiguienteNroOt(@PathVariable String empresa) throws Exception {
		return maestroService.nextOrdenTrabajo(empresa);
	}
	
	@RequestMapping(value = "/seriesdocumentoot/{empresa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody SeriedocumentoOT getSerieDocumentoOt(@PathVariable String empresa) throws Exception {
		return maestroService.getOneByEmpresa(empresa);
	}
	
	@RequestMapping(value = "/seriesdocumentobase/nextinforme/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getSiguienteNroInforme(@PathVariable String planta) throws Exception {
		return maestroService.nextInforme(planta);
	}
	

	@RequestMapping(value = "/seriesdocumentobase/nexthojavalorada/{planta}/{linea}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getSiguienteNroHojaValorada(@PathVariable String planta, @PathVariable String linea) throws Exception {
		return maestroService.nextHojaValorada(planta, linea);
	}
	@RequestMapping(value = "/detcaja/all", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<DetCajaBean> getDetelleCajaAll() throws Exception {
		return maestroService.getDetelleCajaAll();
	}
	@RequestMapping(value = "/detallecaja/concepto/{abreviatura}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<DetCajaBean> getDetelleCajabyConcepto(@PathVariable String abreviatura) throws Exception {
		return maestroService.getDetelleCajabyConcepto(abreviatura);
	}

	@RequestMapping(value = "/seriesdocumentobase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveSerieDocumentoBase(@RequestBody SeriedocumentoBase seriedocumentoBase) {
		maestroService.saveSeriedocumentoBase(seriedocumentoBase);
		return "ok";
	}
	
	@RequestMapping(value = "/seriesdocumentobase/actualizarNroIncorporacion/{nroactualIncorporacion}/{id}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveNroactualIncorporacion(@PathVariable Long nroactualIncorporacion, @PathVariable Long id) {
		incorporacionService.updateNroIncorporacion(nroactualIncorporacion, id);
		return "ok";

	}
	
	@RequestMapping(value = "/inspeccionestado/{nrodocumentoinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody Inspeccionestado getInspeccionEstadoByNroDocumentoinspeccion(@PathVariable String nrodocumentoinspeccion) throws Exception {
		return maestroService.findOneByNroDocumentoinspeccion(nrodocumentoinspeccion);
	}
	
	@RequestMapping(value = "/cuentacorriente/{empresa}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<Cuentacorriente> getCuentaCorrienteByEmpresa(@PathVariable String empresa) throws Exception {
		return maestroService.getCuentaCorrienteByEmpresa(empresa);
	}
	
	@RequestMapping(value="/maquina/linea/{linea}", method= RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody List<Maquina> getMaquinaByLinea(@PathVariable String linea) throws Exception{
		return maestroService.getMaquinaByLinea(linea);
	}
	
	@RequestMapping(value="/maquinanueva",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	public @ResponseBody String insertMaquina(@RequestBody MaquinaEditedBean maquina)throws Exception{
		maquinaservice.insertarDatosMaquina(maquina);
		return"ok";
	}
	
	@RequestMapping(value="/maquina",method=RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody String updateMaquina(@RequestBody MaquinaEditedBean maquina)throws Exception{
		maquinaservice.actualizarDatosMaquina(maquina);
		return"ok";
	}
	
	@RequestMapping(value="/precio/concepto/{planta}", method=RequestMethod.GET,produces="application/json; charset=utf-8")
	public List<PrecioConceptoBean> listarConceptosDeTarifaDeInspecciones(@PathVariable String planta) throws Exception {
		return maestroService.listarConceptoinspeccionesprecios(planta);
	}
	@RequestMapping(value="/precio/concepto_preciou",method=RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody String actualizarPrecioConcepto(@RequestBody PrecioConceptoBean precio) throws Exception{		
		precioconceptoservice.ActualizarPrecioConcepto(precio);
		return"Ok";
	}
	
	@RequestMapping(value="/precios/concepto/{id}/{key}/{planta}",method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public List<PrecioConceptoBean> listarPrecioByConceptoAndPlanta(@PathVariable Long id, @PathVariable String key, @PathVariable String planta) throws Exception{
		return maestroService.listarPreciosByConceptoAndPlanta(id, key, planta);
	}
	
	@RequestMapping(value="/maquina/maximo",method= RequestMethod.GET,produces="application/json; charset=utf-8")
	public @ResponseBody List<MaquinaEditedBean> maquinaMaximo()throws Exception{
		return maquinaservice.devuelveIdmaquina();
	}
	
	@RequestMapping(value="/lineaetapa/maximo",method=RequestMethod.GET,produces="application/json; charset=utf-8")
	public @ResponseBody List<MaquinaEditedBean> lineaEtapaMaximo()throws Exception{
		return maquinaservice.devuelveIdLineaEtapa();
	}
	
	@RequestMapping(value="/lieneaetapanueva",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	public @ResponseBody String insertLineaEtapa(@RequestBody MaquinaEditedBean maquina)throws Exception{
		maquinaservice.insertarLineaEtapa(maquina);
		return"ok";
	}
	
	@RequestMapping(value="/lieneaetapamaquinanueva",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	public @ResponseBody String insertLineaEtapaMaquina(@RequestBody MaquinaEditedBean maquina)throws Exception{
		maquinaservice.insertarLineaEtapaMaquina(maquina);
		return"ok";
	}
	@RequestMapping(value="/citvguardar",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	public @ResponseBody String insertinfoCITV(@RequestBody CitvInfoBean bean )throws Exception{
		maestroService.insertarCITVInfo(bean);
		return"ok";
	}
	@RequestMapping(value="/sisteventsave",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	public @ResponseBody String insertSisEvents(@RequestBody SistEventsBean bean )throws Exception{
		maestroService.InsertSistEvents(bean);
		return"ok";
	}

	@RequestMapping(value = "/categoriasByKey/{key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Categoria listCategoriasByKey(@PathVariable String key)throws Exception {
		return maestroService.GetCategoriaByKey(key);
	}

	@RequestMapping(value = "/detallecaja/tipoinspeccion/{abreviatura}/{categoria}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<DetCajaBean> getDetelleCajabyConceptoAndCategoria(@PathVariable String abreviatura,@PathVariable String categoria) throws Exception {
		return maestroService.getDetelleCajabyConceptoAndCategoria(abreviatura,categoria);
	}
	@RequestMapping(value = "/detallecaja/tipocertificado/{abreviatura}/{categoria}/{tipoinspeccion}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<DetCajaBean> getDetelleCajaTipInspeccionbyConceptoAndCategoria(@PathVariable String abreviatura,@PathVariable String categoria,@PathVariable String tipoinspeccion) throws Exception {
		return maestroService.getDetelleCajaTipoinspeccionbyConceptoAndCategoria(abreviatura,categoria,tipoinspeccion);
	}

	@RequestMapping(value = "/detallecaja/tipoautorizacion/{abreviatura}/{categoria}/{tipoinspeccion}/{tipocertificado}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<DetCajaBean> getDetelleCajaTipAutorizacionbyConceptoAndCategoria(@PathVariable String abreviatura,@PathVariable String categoria,@PathVariable String tipoinspeccion,@PathVariable String tipocertificado) throws Exception {
		System.out.println("entro al controlador");
		return maestroService.getDetelleCajaTipoautorizacionbyConceptoAndCategoria(abreviatura,categoria,tipoinspeccion,tipocertificado);
	}

	@RequestMapping(value="/certificadoragas",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public @ResponseBody List<CertificadoraGas> getCertificadoraGas()throws Exception{
		return maestroService.getCertificadoraGas();
	}
	
	/*@RequestMapping(value = "/maestros/{nombre}/{descripcion}/{serie}/{linea}/{tipomaquina}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody List<Maquina> saveMaquinaall(@PathVariable String empresa) throws Exception {
		return maestroService.getCuentaCorrienteByEmpresa(empresa);
	}*/

	
}
