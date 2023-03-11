package com.farenet.nodo.maestro.api.caja.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.*;
import com.farenet.nodo.maestro.api.caja.domain.bean.*;
import com.farenet.nodo.maestro.api.caja.repository.*;
import com.farenet.nodo.maestro.api.caja.service.CampaniaService;
import com.farenet.nodo.maestro.api.caja.service.ConvenioService;
import com.farenet.nodo.maestro.api.caja.service.DescuentoService;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.ConceptoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.repository.EjecutivoRepository;
import com.farenet.nodo.maestro.api.seguridad.repository.PerfilRepository;
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

import com.farenet.nodo.maestro.api.caja.service.TipoDescuentoService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
@RequestMapping("/descuento")
//@PreAuthorize("hasAnyAuthority('ADMIN','WEB')")
public class DescuentoController extends BaseController{

    @Autowired
    private TipodocumentoidentidadRepository tipodocumentoidentidadRepository;

    @Autowired
    private TipoDescuentoService tipoDescuentoService;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private CampaniaService campaniaService;

    @Autowired
    private CampaniaRepository campaniaRepository;

    @Autowired
    private ConvenioRepository convenioRepository;

    @Autowired
    private TipodescuentoRepository tipodescuentoRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    @Autowired
    private ConceptoinspeccionRepository conceptoinspeccionRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private FormapagoRepository formapagoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EjecutivoRepository ejecutivoRepository;

    
    @Autowired
    private DescuentoService descuentoService;
    
    
    //nuevos descuentos
    @RequestMapping(value = "/placa/{placa}/{planta}/{concepto}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<DescuentoComprobante> getDescuentoByPlaca(@PathVariable String placa,
                                                             @PathVariable String planta, @PathVariable String concepto)
            throws Exception {
    	
        return descuentoService.getDescuentoByPlaca(planta, concepto, placa);
    }
    
    //nuevos descuentos
    @RequestMapping(value = "/rucid/{rucid}/{planta}/{concepto}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<DescuentoComprobante> getDescuentoByRucOrId(@PathVariable String rucid,
                                                             @PathVariable String planta, @PathVariable String concepto)
            throws Exception {

        return descuentoService.getDescuentoByRucOrID(planta, concepto, rucid);
    }
    
    @RequestMapping(value = "/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<DescuentoBean> getAllDescuentoBean(@PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception{
    	return descuentoService.getAllDescuentoBean(new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "/crear", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String saveDescuento(@RequestBody Descuento descuento) throws Exception{
    	descuentoService.saveDescuento(descuento);
    	return "ok";
    }


    @RequestMapping(value = "/actualizardatos", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String getPlacasByIdDescuentoDetalle(@RequestBody ActualizaDescuentoBean actualizaDescuentoBean) throws Exception{
        descuentoService.actualizarDatosDescuento(actualizaDescuentoBean);
        return "ok";
    }
    
    @RequestMapping(value = "/placas/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<String> getPlacasByIdDescuentoDetalle(@PathVariable Long id) throws Exception{
		return descuentoService.getPlacasByIdDescuentoDetalle(id);
    }
    
    @RequestMapping(value = "/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<DescuentoBean> getAllDescuentoBean(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception{
    	return descuentoService.getAllDescuentoBeanByField(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Descuento getDescuentoById(@PathVariable Long id)
            throws Exception {

        return descuentoService.getDescuentoById(id);
    }
    
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String updateDescuento(@RequestBody DescuentoEditedBean descuentoEditedBean)
            throws Exception {
    	descuentoService.updateDescuentoDetalles(descuentoEditedBean);
        return "ok";
    }
    
    @RequestMapping(value = "/actualizarfacturacion/{id}/{facturacion}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String updateFacturacionDescuento(@PathVariable Long id, @PathVariable Boolean facturacion)
            throws Exception {
    	descuentoService.updateFacturacionDescuento(id, facturacion);
        return "ok";
    }
    
    @RequestMapping(value = "/descuentodetalle/actualizar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String updateDescuentoDetalle(@RequestBody DescuentoClientesBean descuentoDetalleBean)
            throws Exception {
    	descuentoService.updateDescuentoCliente(descuentoDetalleBean);
        return "ok";
    }
    
    @RequestMapping(value = "/migrar/cotizacion", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String migrarCotizacion(@RequestBody List<CotizacionMigradorBean> migradoresBeans)
            throws Exception {
    	descuentoService.migrarCotizacion(migradoresBeans);
        return "ok";
    }
    
    @RequestMapping(value = "/tipodescuento/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getTipoDescuentoById(@PathVariable Long id) {
    	return descuentoService.getTipoDescuentoById(id);
    }
    
    @RequestMapping(value = "/nombredescuento/{tipoDescuento}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<String> getDescuentosByTipoDescuento(@PathVariable String tipoDescuento){
    	return descuentoService.getNombresByTipoDescuento(tipoDescuento);
    }
    
    
    
    /** TODO DESCUENTO MASIVOS
     */
    
    @RequestMapping(value = "/masivos/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<DescuentoBean> getAllDescuentoMasivoBean(@PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception{
    	return descuentoService.getAllDescuentoMasivosBean(new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "masivos/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<DescuentoBean> getAllDescuentoMasivoBean(@PathVariable String field, @PathVariable int numPage,
			@PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception{
    	return descuentoService.getAllDescuentoMasivoBeanByField(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
    }
    
    @RequestMapping(value = "/masivo/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public DescuentoMasivo getDescuentoMasivoById(@PathVariable Long id)
            throws Exception {

        return descuentoService.getDescuentoMasivoById(id);
    }
    
    @RequestMapping(value = "/masivos/actualizar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String updateDescuentoMasivos(@RequestBody DescuentoMasivoDetalleEditBean descuentoEditedBean)
            throws Exception {
    	descuentoService.undateDescuentoMasivoDetalles(descuentoEditedBean);
        return "ok";
    }
    
    @RequestMapping(value = "descuento/masivos/crear/codigo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String CrearDescuentoMasivos(@RequestBody DescuentoMasivo descuentoEditedBean)
            throws Exception {
    	descuentoService.crearDescuentoMasivo(descuentoEditedBean);
        return "ok";
    }
    
    @RequestMapping(value = "/masivos/crear/clientes", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String CrearDescuentoMasivos(@RequestBody DescuentoMasivoClientesEditBean clientesEditBean)
            throws Exception {
    	descuentoService.CrearClientesMasivos(clientesEditBean);
        return "ok";
    } 
    
    @RequestMapping(value = "/masivos/placas/excel/{nombreexcel}/{descuentoid}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String CrearDescuentoMasivosPlaca(@PathVariable String nombreexcel, @PathVariable String descuentoid)
            throws Exception {
    	descuentoService.cargarExcel(nombreexcel,descuentoid,true);
        return "ok";
    }
    
    @RequestMapping(value = "/masivos/uuid/excel/{nombreexcel}/{descuentoid}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String CrearDescuentoMasivosUuid(@PathVariable String nombreexcel, @PathVariable String descuentoid)
            throws Exception {
    	descuentoService.cargarExcel(nombreexcel,descuentoid,false);
        return "ok";
    } 
    
    
    
    
    
    
    @RequestMapping(value = "/tiposdescuentos/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Tipodescuento> getAllTipodescuento(@PathVariable int numPage,
                                                             @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column)
            throws Exception {

        return tipoDescuentoService.getAllTipodescuento(new PageBean(numPage, totalFilasPorPagina, sort, column));
    }

    @RequestMapping(value = "/campania/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Campania getCampaniaById(@PathVariable Long id) {
        Campania campania = campaniaRepository.findOneById(id);
        return campania;
    }

    @RequestMapping(value = "/convenio/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Convenio getConvenioById(@PathVariable Long id) {
        Convenio convenio = convenioRepository.findOneById(id);
        return convenio;
    }

    @RequestMapping(value = "/campania", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveCampania(@RequestBody Campania campania) throws CloneNotSupportedException {
        campaniaService.save(campania);
        return "ok";
    }

    @RequestMapping(value = "/convenio", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveConvenio(@RequestBody Convenio convenio) {
        convenioService.save(convenio);
        return "ok";
    }

    @RequestMapping(value = "/campanias-por-planta/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Campania> getAllCampaniasEnabledPorPlantaUpdated(@PathVariable String planta_key, @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fech") Date fech) throws Exception {
        Planta planta = plantaRepository.findOneByKey(planta_key);
        Timestamp fecha = new Timestamp(fech.getTime());
        return campaniaRepository.findAllByPlantaUpdated(planta, fecha);
    }
    
    @RequestMapping(value = "/campanias/{planta_key}/{tipodescuento}/{conceptoinspeccion}/{ruc}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Campania> getCampaniaByplantaTIpoDesConcepto(@PathVariable String planta_key, @PathVariable String tipodescuento,
    		@PathVariable String conceptoinspeccion, @PathVariable String ruc) {
    	return campaniaService.getCampaniaByPlantaAndDescAndConcepto(planta_key, tipodescuento, conceptoinspeccion, ruc);
    }
    
    @RequestMapping(value = "/campanias/{planta_key}/{tipodescuento}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Campania> getAllCampaniaByPlantaAndTipoDescuento(@PathVariable String planta_key, @PathVariable String tipodescuento){
    	return campaniaService.getAllCampaniaByPlantaAndDescuento(planta_key, tipodescuento);
    }

    @RequestMapping(value = "/campanias-por-planta-todos/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Campania> getAllCampaniasEnabledPorPlanta(@PathVariable String planta_key) throws Exception {
        Planta planta = plantaRepository.findOneByKey(planta_key);
        return campaniaRepository.findAllByPlanta(planta);
    }

    @RequestMapping(value = "/campanias-por-tipo/{tipodescuento_key}/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Campania> getAllCampaniasSearchPageable(@PathVariable String tipodescuento_key, @PathVariable String field, @PathVariable int numPage,
                                                                  @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception {
        Tipodescuento tipodescuento = tipodescuentoRepository.findOneByKey(tipodescuento_key);
        PageBean pageBean = new PageBean(numPage, totalFilasPorPagina, sort, column);

        ResultPageBean<Campania> result = campaniaService.get(field, tipodescuento, pageBean);
        return result;
    }

    @RequestMapping(value = "/convenios", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Convenio> getAllConveniosUpdated(@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fech") Date fech) throws Exception {
        Timestamp fecha = new Timestamp(fech.getTime());
        return convenioRepository.findByFechUpdated(fecha);
    }

    @RequestMapping(value = "/convenios-todos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Convenio> getAllConvenios() throws Exception {
        return convenioRepository.findAllByEstado(true);
    }

    @RequestMapping(value = "/convenios/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Convenio> getAllConveniosPageable(@PathVariable int numPage,
                                                            @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception {
        PageBean pageBean = new PageBean(numPage, totalFilasPorPagina, sort, column);

        ResultPageBean<Convenio> result = new ResultPageBean<Convenio>(
                convenioRepository.findAllByEstado(true, pageBean.convertToPage()));
        return result;
    }

    @RequestMapping(value = "/convenios/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Convenio> getAllConveniosSearchPageable(@PathVariable String field, @PathVariable int numPage,
                                                                  @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception {
        PageBean pageBean = new PageBean(numPage, totalFilasPorPagina, sort, column);

        ResultPageBean<Convenio> result = convenioService.get(field, pageBean);
        return result;
    }

    @RequestMapping(value = "/campanias-por-tipo/{tipodescuento_key}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResultPageBean<Campania> getAllCampaniasByTipodescuento(@PathVariable String tipodescuento_key, @PathVariable int numPage,
                                                                   @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort, @PathVariable String column) throws Exception {
        Tipodescuento tipodescuento = tipodescuentoRepository.findOneByKey(tipodescuento_key);
        PageBean pageBean = new PageBean(numPage, totalFilasPorPagina, sort, column);

        ResultPageBean<Campania> result = new ResultPageBean<Campania>(
                campaniaRepository.findByTipodescuento(tipodescuento, pageBean.convertToPage()));
        return result;
    }

    @RequestMapping(value = "/convenios", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveConvenios(@RequestBody List<ConvenioBean> convenioBeans) {
        List<Convenio> convenios = new ArrayList<>();
        long countidEje = 1;
        Tipodocumentoidentidad tipodocumentoRUC = tipodocumentoidentidadRepository.findOneByKey("ruc");
        for (ConvenioBean convenioBean : convenioBeans) {
            Convenio convenio = new Convenio();
            Convenio convenioFind = convenioRepository.findOneByKey(convenioBean.getKey());
            if (convenioFind != null) {
                convenio = convenioFind;
            }
            convenio.setKey(convenioBean.getKey());
            String tipodescuento_key = "";
            if (convenioBean.getTipodescuento().trim().toLowerCase().equals("convenio")) {
                tipodescuento_key = "conve";
            }
            if (convenioBean.getTipodescuento().trim().toLowerCase().equals("promoción")) {
                tipodescuento_key = "promocion";
            }
            if (convenioBean.getTipodescuento().trim().toLowerCase().equals("procedencia")) {
                tipodescuento_key = "proce";
            }
            if (convenioBean.getTipodescuento().trim().toLowerCase().equals("alianza estratégica")) {
                tipodescuento_key = "aliestrategica";
            }
            if (convenioBean.getTipodescuento().trim().toLowerCase().equals("cortecia")) {
                tipodescuento_key = "corte";
            }
            Tipodescuento tipodescuento = tipodescuentoRepository.findOneByKey(tipodescuento_key);
            convenio.setTipodescuento(tipodescuento);
            convenio.setNombre(convenioBean.getNombre());

            Ejecutivo ejecutivo = null;
            if (convenioBean.getEjecutivo() != null && !convenioBean.getEjecutivo().equals("")) {
                ejecutivo = new Ejecutivo();
                String[] nombresSplit = convenioBean.getEjecutivo().split(" ");
                String nombres = nombresSplit[0];
                String apellidos = nombresSplit[1];
                ejecutivo.setUser(convenioBean.getEjecutivo());
                ejecutivo.setContrasenha(convenioBean.getEjecutivo());

                Perfil perfil = perfilRepository.findByClave("asistente_servicio");
                ejecutivo.setPerfil(perfil);

                Persona persona = new Persona();
                persona.setNrodocumentoidentidad(leadingZeros((long) apellidos.hashCode(), 8));
                persona.setNombres(nombres);
                persona.setApellidos(apellidos);
                personaRepository.save(persona);
                ejecutivo.setPersona(persona);
                ejecutivoRepository.save(ejecutivo);
            }

            List<Persona> personasFilter = personaRepository.findAllByCriteriaByEstadoNoPaged(convenioBean.getPersona(), true);
            Persona persona = (personasFilter.size() > 0) ? personasFilter.get(0) : null;
            if (persona == null) {
                persona = new Persona();
                persona.setNrodocumentoidentidad(convenioBean.getPersona());
                persona.setNombrerazonsocial(convenioBean.getNombre());
                persona.setTipodocumentoidentidad(tipodocumentoRUC);
                personaRepository.save(persona);
            }
            convenio.setPersona(persona);

            convenio.setEjecutivo(ejecutivo);
            convenio.setObservaciones(convenioBean.getObservaciones());
            convenio.setCondicionLimitePago(convenioBean.getCondicionLimitePago());
            convenio.setLineaCreditoMaxima(convenioBean.getLineaCreditoMaxima());
            convenio.setLineaCreditoDisponible(convenioBean.getLineaCreditoDisponible());
            convenio.setLineaCreditoDeshabilitada(convenioBean.isLineaCreditoDeshabilitada());

            List<Conveniodetalle> conveniodetalles = new ArrayList<>();
            for (ConveniodetalleBean conveniodetalleBean : convenioBean.getConveniodetalles()) {
                Conveniodetalle conveniodetalle = new Conveniodetalle();
                Conceptoinspeccion conceptoinspeccion = conceptoinspeccionRepository.findOneByAbreviatura(conveniodetalleBean.getConceptoinspeccion());
                conveniodetalle.setConceptoinspeccion(conceptoinspeccion);
                conveniodetalle.setMontoFlat(conveniodetalleBean.getMontoFlat());
                conveniodetalle.setTipoDescuento(conveniodetalleBean.getTipoDescuento());
                conveniodetalle.setFlatActivado(conveniodetalleBean.isFlatActivado());
                conveniodetalle.setValorDescuento(conveniodetalleBean.getValorDescuento());
                String formapago_key = "";
                if (conveniodetalleBean.getFormaPago().trim().toLowerCase().equals("contado")) {
                    formapago_key = "contado";
                }
                if (conveniodetalleBean.getFormaPago().trim().toLowerCase().equals("credito")) {
                    formapago_key = "credito";
                }
                if (conveniodetalleBean.getFormaPago().trim().toLowerCase().equals("banco")) {
                    formapago_key = "banco";
                }
                if (conveniodetalleBean.getFormaPago().trim().toLowerCase().equals("cheque")) {
                    formapago_key = "cheque";
                }
                if (conveniodetalleBean.getFormaPago().trim().toLowerCase().equals("tarjeta")) {
                    formapago_key = "tarjeta";
                }
                Formapago formapago = formapagoRepository.findOneByKey(formapago_key);
                conveniodetalle.setFormaPago(formapago);
                conveniodetalles.add(conveniodetalle);
            }
            convenio.setConveniodetalles(conveniodetalles);
            convenios.add(convenio);
            countidEje++;
        }
        for (Convenio convenio : convenios) {
            convenioService.save(convenio);
        }
        return "ok";
    }

    @RequestMapping(value = "/campanias", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String saveCampanias(@RequestBody List<CampaniaBean> campaniaBeans) throws CloneNotSupportedException {
        List<Campania> campanias = new ArrayList<>();
        List<Conceptoinspeccion> conceptoinspeccions = conceptoinspeccionRepository.findByEstado(true);
        for (CampaniaBean campaniaBean : campaniaBeans) {
            Campania campania = new Campania();
            Campania campaniaFind = null;
            try {
                campaniaFind = (Campania) campaniaRepository.findOneByKey(campaniaBean.getKey()).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            campaniaFind.setCampaniadetalles(new ArrayList<>());
            if (campaniaFind != null) {
                campania = campaniaFind;
            }
            campania.setKey(campaniaBean.getKey());
            campania.setTipoVerificacion(campaniaBean.getTipoVerificacion());
            Timestamp fechFin = null;
            if (campaniaBean.getFechFin() != null) {
                fechFin = new Timestamp(campaniaBean.getFechFin().getTime());
            }
            campania.setFechFin(fechFin);
            Timestamp fechInicio = null;
            if (campaniaBean.getFechInicio() != null) {
                fechInicio = new Timestamp(campaniaBean.getFechInicio().getTime());
            }
            campania.setFechInicio(fechInicio);
            campania.setNombre(campaniaBean.getNombre());
            campania.setObservaciones(campaniaBean.getObservaciones());
            String tipodescuento_key = "";
            if (campaniaBean.getTipodescuento() != null) {
                if (campaniaBean.getTipodescuento().trim().toLowerCase().equals("convenio")) {
                    tipodescuento_key = "conve";
                }
                if (campaniaBean.getTipodescuento().trim().toLowerCase().equals("promoción")) {
                    tipodescuento_key = "promocion";
                }
                if (campaniaBean.getTipodescuento().trim().toLowerCase().equals("procedencia")) {
                    tipodescuento_key = "proc";
                }
                if (campaniaBean.getTipodescuento().trim().toLowerCase().equals("alianza estratégica")) {
                    tipodescuento_key = "aliestrategica";
                }
                if (campaniaBean.getTipodescuento().trim().toLowerCase().equals("cortecia")) {
                    tipodescuento_key = "corte";
                }
            }
            Tipodescuento tipodescuento = tipodescuentoRepository.findOneByKey(tipodescuento_key);
            campania.setTipodescuento(tipodescuento);

            List<Campaniadetalle> campaniadetalles = new ArrayList<>();
            for (CampaniadetalleBean campaniadetalleBean : campaniaBean.getCampaniadetalles()) {
                Campaniadetalle campaniadetalle = new Campaniadetalle();
                if (campaniadetalleBean.getConceptoinspeccion().trim().toLowerCase().equals("todos")) {
                    for (Conceptoinspeccion conceptoinspeccion : conceptoinspeccions) {
                        campaniadetalle = new Campaniadetalle();
                        campaniadetalle.setValorDescuento(campaniadetalleBean.getValorDescuento());
                        campaniadetalle.setTipoDescuento(campaniadetalleBean.getTipoDescuento());
                        campaniadetalle.setConceptoinspeccion(conceptoinspeccion);
                        campaniadetalle.setFlatActivado(campaniadetalleBean.isFlatActivado());
                        campaniadetalle.setMontoFlat(campaniadetalleBean.getMontoFlat());
                        campaniadetalles.add(campaniadetalle);
                    }
                    continue;
                }
                campaniadetalle.setValorDescuento(campaniadetalleBean.getValorDescuento());
                campaniadetalle.setTipoDescuento(campaniadetalleBean.getTipoDescuento());
                Conceptoinspeccion conceptoinspeccion = conceptoinspeccionRepository.findOneByAbreviatura(campaniadetalleBean.getConceptoinspeccion());
                campaniadetalle.setConceptoinspeccion(conceptoinspeccion);
                campaniadetalles.add(campaniadetalle);
            }
            campania.setCampaniadetalles(campaniadetalles);

            List<Persona> personas = new ArrayList<>();
            for (PersonaBean personaBean : campaniaBean.getEmpresas()) {
                List<Persona> personasFilter = personaRepository.findAllByCriteriaByEstadoNoPaged(personaBean.getEmpresa(), true);
                Persona persona = (personasFilter.size() > 0) ? personasFilter.get(0) : null;
                if (persona != null) {
                    personas.add(persona);
                }
            }
            campania.setEmpresas(personas);

            List<Planta> plantas = new ArrayList<>();
            for (PlantaBean plantaBean : campaniaBean.getPlantas()) {
                String planta_key = null;
                if (plantaBean.getPlanta().trim().toLowerCase().equals("argentina")) {
                    planta_key = "18";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("colina")) {
                    planta_key = "13";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("ica")) {
                    planta_key = "25";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("jicamarca")) {
                    planta_key = "43";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("la victoria")) {
                    planta_key = "103";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("piura")) {
                    planta_key = "61";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("surco")) {
                    planta_key = "98";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("cusco")) {
                    planta_key = "21";
                }
                if (plantaBean.getPlanta().trim().toLowerCase().equals("ate")) {
                    planta_key = "84";
                }
                Planta planta = plantaRepository.findOneByKey(planta_key);
                plantas.add(planta);
            }
            campania.setPlantas(plantas);
            campanias.add(campania);
        }
        for (Campania campania : campanias) {
            campaniaService.save(campania);
        }
        return "ok";
    }

    public static String leadingZeros(Long s, int length) {
        if (s.toString().length() >= length)
            return s.toString();
        else
            return String.format("%0" + (length - s.toString().length()) + "d%s", 0, s);
    }
}
