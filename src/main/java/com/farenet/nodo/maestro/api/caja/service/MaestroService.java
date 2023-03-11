package com.farenet.nodo.maestro.api.caja.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.farenet.nodo.maestro.api.caja.domain.*;
import com.farenet.nodo.maestro.api.caja.domain.bean.CitvInfoBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.PrecioConceptoBean;
import com.farenet.nodo.maestro.api.caja.domain.bean.SistEventsBean;
import com.farenet.nodo.maestro.api.caja.repository.*;
import com.farenet.nodo.maestro.api.inspeccion.domain.*;
import com.farenet.nodo.maestro.api.inspeccion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.linea.domain.Etapa;
import com.farenet.nodo.maestro.api.linea.domain.LineaEtapa;
import com.farenet.nodo.maestro.api.linea.domain.Maquina;
import com.farenet.nodo.maestro.api.linea.domain.Norma;
import com.farenet.nodo.maestro.api.linea.domain.Reglaalineacion;
import com.farenet.nodo.maestro.api.linea.domain.Reglafreno;
import com.farenet.nodo.maestro.api.linea.domain.Reglagas;
import com.farenet.nodo.maestro.api.linea.domain.Reglaluces;
import com.farenet.nodo.maestro.api.linea.domain.Reglaopacidad;
import com.farenet.nodo.maestro.api.linea.domain.Reglaprofundidad;
import com.farenet.nodo.maestro.api.linea.domain.Reglasonora;
import com.farenet.nodo.maestro.api.linea.domain.Reglasuspension;
import com.farenet.nodo.maestro.api.linea.domain.Subnorma;
import com.farenet.nodo.maestro.api.linea.domain.Tipomaquina;
import com.farenet.nodo.maestro.api.linea.repository.EmpresaRepository;
import com.farenet.nodo.maestro.api.linea.repository.EtapaRepository;
import com.farenet.nodo.maestro.api.linea.repository.LineaetapaRepository;
import com.farenet.nodo.maestro.api.linea.repository.MaquinaRepository;
import com.farenet.nodo.maestro.api.linea.repository.NormaRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglaalineacionRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglafrenoRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglagasRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglalucesRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglaopacidadRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglaprofundidadRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglasonoraRepository;
import com.farenet.nodo.maestro.api.linea.repository.ReglasuspencionRepository;
import com.farenet.nodo.maestro.api.linea.repository.SubnormaRepository;
import com.farenet.nodo.maestro.api.linea.repository.TipomaquinaRepository;
import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.repository.EjecutivoRepository;
import com.farenet.nodo.maestro.api.seguridad.repository.PerfilRepository;
import com.farenet.nodo.maestro.api.seguridad.service.UsuarioService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.UIUtils;

@Service
@Transactional
public class MaestroService {

	
	@Autowired
	private EntityManager entityManager;
	
    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private ConceptoinspeccionRepository conceptoinspeccionRepository;

    @Autowired
    private FormapagoRepository formapagoRepository;
    
    @Autowired
    private TipoContadoRepository tipoContadoRepository;

    @Autowired
    private EntidadfinancieraRepository entidadfinancieraRepository;

    @Autowired
    private CuentacorrienteRepository cuentacorrienteRepository;

    @Autowired
    private TipodescuentoRepository tipodescuentoRepository;

    @Autowired
    private TipodocumentoRepository tipodocumentoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private CombustibleRepository combustibleRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private VehiculoclaseRepository vehiculoclaseRepository;

    @Autowired
    private CarroceriaRepository carroceriaRepository;

    @Autowired
    private TipodocumentoidentidadRepository tipodocumentoidentidadRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private AseguradoraRepository aseguradoraRepository;

    @Autowired
    private SeriedocumentoRepository seriedocumentoRepository;
    
    @Autowired
    private SeriedocumentoBaseRepository seriedocumentoBaseRepository;
    
    @Autowired
    private SeriedocumentoOTRepository seriedocumentoOTRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PeriodoreinspeccionRepository periodoreinspeccionRepository;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private NormaRepository normaRepository;

    @Autowired
    private SubnormaRepository subnormaRepository;

    @Autowired
    private TipomaquinaRepository tipomaquinaRepository;

    @Autowired
    private ReglafrenoRepository reglafrenoRepository;

    @Autowired
    private ReglagasRepository reglagasRepository;

    @Autowired
    private ReglaopacidadRepository reglaopacidadRepository;

    @Autowired
    private ReglaprofundidadRepository reglaprofundidadRepository;

    @Autowired
    private ReglasonoraRepository reglasonoraRepository;

    @Autowired
    private ReglasuspencionRepository reglasuspencionRepository;

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private LineaetapaRepository lineaEtapaRepository;

    @Autowired
    private ReglaalineacionRepository reglaalineacionRepository;

    @Autowired
    private ReglalucesRepository reglalucesRepository;

    @Autowired
    private TipoinspeccionRepository tipoinspeccionRepository;

    @Autowired
    private TipocertificadoRepository tipocertificadoRepository;

    @Autowired
    private TipoautorizacionRepository tipoautorizacionRepository;

    @Autowired
    private ComprobanteestadoRepository comprobanteestadoRepository;

    @Autowired
    private InspeccionestadoRepository inspeccionestadoRepository;

    @Autowired
    private MonedaRepository monedaRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CondicionvigenciaCertificadoRepository condicionvigenciaCertificadoRepository;
    
    @Autowired
    private TipoPolizaRepository tipoPolizaRepository;
    
    @Autowired
    private TipoPagoDescuentoRepository tipoPagoDescuentoRepository;
    
    @Autowired
    private InspeccionRepository inspeccionRepository;
    
    @Autowired
    private ComprobanteRepository comprobanteRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ConceptoPrecioService precioservice;
    
    @Autowired
    private EjecutivoRepository ejecutivoRepository;
    
    @Autowired
    private TriggerTaskFactory trigger;
    
    @Autowired
    private TipoPlacaRepository tipoplacaRepository;

   @Autowired
    private CertificadoraGasRepository certificadoraGasRepository;

   @Autowired
   private TipoConvenioRepository tipoConvenioRepository;
    
    
    public List<Periodoreinspeccion> listarPeriodoreinspecciones() {
        return periodoreinspeccionRepository.findAll();
    }

    @Cacheable("empresas")
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    @Cacheable("plantas")
    public List<Planta> listarPlantas() {
        return plantaRepository.findAll();
    }
    
    @Cacheable("lineas")
    public List<Linea> listarLineas() {
        return lineaRepository.findAll();
    }

    public Planta getPlantaByKey(String key) {
        return plantaRepository.findOneByKey(key);
    }
    
    public TipoPagoDescuento getTipoPagoDescuentoByKey(String key) {
    	return tipoPagoDescuentoRepository.findOneByKey(key);
    }
    
    public Formapago getFormaPagoByKey(String key) {
    	return formapagoRepository.findOneByKey(key);
    }

    public List<Linea> listarLineasByPlanta(Planta planta) {
        return lineaRepository.findByPlanta(planta);
    }

    @Cacheable("conceptos")
    public List<Conceptoinspeccion> listarConceptoinspecciones(boolean estado) {
        return conceptoinspeccionRepository.findByEstado(estado);
    }

    public List<PrecioConceptoBean> listarConceptoinspeccionesprecios(String planta) throws Exception {
        return precioservice.ListarConceptoPlanta(planta);
    }

    public List<PrecioConceptoBean> listarPreciosByConceptoAndPlanta(Long id, String key, String planta) throws Exception {
        return precioservice.listarPreciosByConceptoAndPlanta(id, key, planta);
    }

    @Cacheable("formaPagos")
    public List<Formapago> listarFormasDePagos() {
        return formapagoRepository.findAll();
    }
    
    @Cacheable("tipoContado")
    public List<TipoContado> listarTiposDeContado() {
        return tipoContadoRepository.findAll();
    }

    @Cacheable("entidadesFinancieras")
    public List<Entidadfinanciera> listarEntidadesFinancieras() {
        return entidadfinancieraRepository.findAll();
    }

    @Cacheable("cuentasCorrientes")
    public List<Cuentacorriente> listarCuentasCorrientes() {
        return cuentacorrienteRepository.findAll();
    }

    @Cacheable("monedas")
    public List<Moneda> listarMonedas() {
        return monedaRepository.findAll();
    }

    @Cacheable("tarjetas")
    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }

    @Cacheable("tiposDescuentos")
    public List<Tipodescuento> listarTiposDescuentos() {
        return tipodescuentoRepository.findAll();
    }

    @Cacheable("tiposDocumentos")
    public List<Tipodocumento> listarTiposDocumentos() {
        return tipodocumentoRepository.findAll();
    }

    @Cacheable("categorias")
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public List<TipoConvenio> listarTipoConvenio(){return tipoConvenioRepository.findAll();}

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    @Cacheable("combustibles")
    public List<Combustible> listarCombustibles() {
        return combustibleRepository.findAll();
    }

    public List<Color> listarColores() {
        return colorRepository.findAll();
    }

    public List<Vehiculoclase> listarClasesDeVehiculos() {
        return vehiculoclaseRepository.findAll();
    }

    public List<Carroceria> listarCarrocerias() {
        return carroceriaRepository.findAll();
    }

    @Cacheable("tiposDocumentosIdentidad")
    public List<Tipodocumentoidentidad> listarTipodocumentoidentidad() {
        return tipodocumentoidentidadRepository.findAll();
    }

    @Cacheable("aseguradoras")
    public List<Aseguradora> listarAseguradoras() {
        return aseguradoraRepository.findAll();
    }

    @Cacheable("paises")
    public List<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    @Cacheable("departamentos")
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    @Cacheable("provincias")
    public List<Provincia> listarProvincias() {
        return provinciaRepository.findAll();
    }

    @Cacheable("distritos")
    public List<Distrito> listarDistritos() {
        return distritoRepository.findAll();
    }

    @Cacheable("seriesDocumentos")
    public List<Seriedocumento> listarSeriesdocumentos() {
        return seriedocumentoRepository.findAll();
    }
    

    @Cacheable("tipoplaca")
    public List<TipoPlaca> listarTipoPlacas() {
    	return tipoplacaRepository.findAll();
	}
    
    //CORRELATIVOS INSPECCION - COMPROBANTE
    
    public Moneda getMonedaByKey(String key) {
    	return monedaRepository.getOneByKey(key);
    }
    
    public Empresa getEmpresaByRuc(String ruc) {
    	return empresaRepository.findOneByRuc(ruc);
    }
    
    public synchronized String nextInforme(String planta) 
    {
    	SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
    		
    	Long nroNuevoInforme = (documento.getNroactualInforme() + 1);
    	/*
    	documento.setNroactualInforme(nroNuevoInforme);
		seriedocumentoBaseRepository.save(documento);
		*/
    	
	 	String hqldet = "UPDATE SeriedocumentoBase SET nroactualInforme = :nroactualinforme WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactualinforme",nroNuevoInforme);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();
        
    		return nroNuevoInforme.toString();
    }
    
    public synchronized String nextHojaValorada(String planta, String linea){
    	SeriedocumentoBase  base = seriedocumentoBaseRepository.findOneByPlanta(planta);
    	Seriedocumento seriedocumento = seriedocumentoRepository.findOneByPlantaAndLinea(planta, linea);
    	Long nroNuevoHojaValorada = (seriedocumento.getNroactual() +1);
    	
    	/*seriedocumento.setNroactual(nroNuevoHojaValorada);
    	seriedocumentoRepository.save(seriedocumento);*/
    	
	 	String hqldet = "UPDATE Seriedocumento SET nroactual = :nroactual WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactual",nroNuevoHojaValorada);
        query1.setParameter("id", seriedocumento.getId());
        query1.executeUpdate();
    	
    	return UIUtils.formatoNroCertificadoByPlanta(base, nroNuevoHojaValorada);
    	
    }

    public synchronized String nextOrdenTrabajo(String empresa) throws Exception
    {
        SeriedocumentoOT documento =  seriedocumentoOTRepository.findOneByEmpresa(empresa);

        Long nroNuevoComprobante = documento.getCorrelativoOT();
        String formatoComprobante = "";

        nroNuevoComprobante = nroNuevoComprobante + 1;
        formatoComprobante = UIUtils.formato8NroComprobante(documento.getSerieot(),nroNuevoComprobante);
        documento.setCorrelativoOT(nroNuevoComprobante);
        seriedocumentoOTRepository.save(documento);

        return formatoComprobante;

    }

    public synchronized SeriedocumentoOT getOneByEmpresa(String empresa) throws Exception {
        return seriedocumentoOTRepository.findOneByEmpresa(empresa);
    }

    public synchronized Long nextIdBoleta(String planta) throws Exception
    {
    		SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
    		String nroComprobante = null;
    		Long nroNuevoComprobante = documento.getNroactualBoleta();
    		String formatoComprobante = "";

    		do {
        		nroNuevoComprobante = nroNuevoComprobante + 1;
        		formatoComprobante = UIUtils.formato8NroComprobante(documento.getSerieboleta(),nroNuevoComprobante);
        		
        		nroComprobante = comprobanteRepository.findNroByComprobante(
        				formatoComprobante,plantaRepository.findOneByKey(planta).getEmpresa().getKey());

        		if(nroComprobante!=null)
                    trigger.launch("", "correlativo.salto",
                            nroComprobante,
                            documento.getSerieboleta().toString()+ " "+documento.getNroactualBoleta().toString());


            }while(nroComprobante!=null);

    		
    		return nroNuevoComprobante;
    		
    	}
    
    	public synchronized Long nextIdFactura(String planta) throws Exception {
    		SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
    		String nroComprobante = null;
    		Long nroNuevoComprobante = documento.getNroactualFactura();
    		String formatoComprobante = "";

    		do {
    			
        		nroNuevoComprobante = nroNuevoComprobante + 1;
        		formatoComprobante = UIUtils.formato8NroComprobante(documento.getSeriefactura(),nroNuevoComprobante);
        		
        		nroComprobante = comprobanteRepository.findNroByComprobante(
        				formatoComprobante,plantaRepository.findOneByKey(planta).getEmpresa().getKey());

                if(nroComprobante!=null)
                    trigger.launch("", "correlativo.salto",
                            nroComprobante,
                            documento.getSerieboleta().toString()+ " "+documento.getNroactualBoleta().toString());


            }while(nroComprobante!=null);

    	return nroNuevoComprobante;
    		
    }
    
    public synchronized Long nextIdNotaCreditoFactura(String planta) throws Exception {
    	SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
		String nroComprobante = null;
		Long nroNuevoComprobante = documento.getNroactualNotaCreditoFactura();
		String formatoComprobante = "";
		
		/* String valComprobante = UIUtils.formato8NroComprobante(documento.getSeriefactura(),nroNuevoComprobante);
		if(comprobanteRepository.findNroByComprobante(valComprobante, plantaRepository.findOneByKey(planta).getEmpresa().getKey()) == null) {
			throw new Exception("Error Salto de Correlativo");
		} */
		
		do {
			nroNuevoComprobante = nroNuevoComprobante + 1;
    		formatoComprobante = UIUtils.formato8NroComprobante(documento.getSerienotacreditofactura(),nroNuevoComprobante);
    		
    		nroComprobante = comprobanteRepository.findNroByComprobante(
    				formatoComprobante,plantaRepository.findOneByKey(planta).getEmpresa().getKey());
    		
		}while(nroComprobante!=null);
		
		/*documento.setNroactualNotaCreditoFactura(nroNuevoComprobante);
		seriedocumentoBaseRepository.save(documento);*/
		
		String hqldet = "UPDATE SeriedocumentoBase SET nroactualNotaCreditoFactura = :nroactualnotacreditofactura WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactualnotacreditofactura",nroNuevoComprobante);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();
		
		return nroNuevoComprobante;    
	}
    
    public synchronized Long nextIdNotaCreditoBoleta(String planta) throws Exception {
    	SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
		String nroComprobante = null;
		Long nroNuevoComprobante = documento.getNroactualNotaCreditoBoleta();
		String formatoComprobante = "";
		
		/* String valComprobante = UIUtils.formato8NroComprobante(documento.getSeriefactura(),nroNuevoComprobante);
		if(comprobanteRepository.findNroByComprobante(valComprobante, plantaRepository.findOneByKey(planta).getEmpresa().getKey()) == null) {
			throw new Exception("Error Salto de Correlativo");
		} */
		
		do {
			
    		nroNuevoComprobante = nroNuevoComprobante + 1;
    		formatoComprobante = UIUtils.formato8NroComprobante(documento.getSerienotacreditoboleta(),nroNuevoComprobante);
    		
    		nroComprobante = comprobanteRepository.findNroByComprobante(
    				formatoComprobante,plantaRepository.findOneByKey(planta).getEmpresa().getKey());
    		
		}while(nroComprobante!=null);
		
		/*documento.setNroactualNotaCreditoBoleta(nroNuevoComprobante);
		seriedocumentoBaseRepository.save(documento);*/
		
		String hqldet = "UPDATE SeriedocumentoBase SET nroactualNotaCreditoBoleta = :nroactualnotacreditoboleta WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactualnotacreditoboleta",nroNuevoComprobante	);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();
		
		return nroNuevoComprobante;    
	}
    
    
    public synchronized String nextIdInspeccion(String planta)
    {
    		SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
		Long nroNuevo = documento.getNroidInspeccion();
		
		String nroinspeccion = "";
		do {
			
			nroNuevo = (long) (nroNuevo + 1);
			nroinspeccion = UIUtils.formatoNroInspeccion(planta,nroNuevo);
			
			
		}while(inspeccionRepository.findIdByNroInspeccion(nroinspeccion)!=null);
		
		/*documento.setNroidInspeccion(nroNuevo);
		seriedocumentoBaseRepository.save(documento);*/
		String hqldet = "UPDATE SeriedocumentoBase SET nroidInspeccion = :nroidinspeccion"
	 			+ " WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroidinspeccion",nroNuevo);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();
		
		
		return nroinspeccion;
		
    }
    
 
    
    public synchronized Long nextNumeroBoletaManual(String planta)
    {
    	SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		
		Long nroNuevo = documento.getNroactualBoletaManual() +1;
		
		/*documento.setNroactualBoletaManual(nroNuevo);
		seriedocumentoBaseRepository.save(documento);*/
		
		String hqldet = "UPDATE SeriedocumentoBase SET nroactualBoletaManual = :nroactualboletamanual WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactualboletamanual",nroNuevo);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();
		
		return nroNuevo;
    }
    
    public synchronized Long nextNumeroFacturaManual(String planta)
    {
    	SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
    	Long nroNuevo = documento.getNroactualFacturaManual() +1;
    	
    	/*
		documento.setNroactualFacturaManual(nroNuevo);
		seriedocumentoBaseRepository.save(documento);*/
    	
	 	String hqldet = "UPDATE SeriedocumentoBase SET nroactualFacturaManual = :nroactualfacturamanual WHERE id = :id";
        Query query1 = entityManager.createQuery(hqldet);
        query1.setParameter("nroactualfacturamanual",nroNuevo);
        query1.setParameter("id", documento.getId());
        query1.executeUpdate();

		return nroNuevo;
    }
    
    public Long getNroNuevoBoletaManual(String planta) {
		SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		Long nroNuevo = documento.getNroactualBoletaManual()+1;
		
		return nroNuevo;
    }
    
    public Long getNroNuevoFacturaManual(String planta) {
		SeriedocumentoBase documento = seriedocumentoBaseRepository.findOneByPlanta(planta);
		Long nroNuevo = documento.getNroactualFacturaManual()+1;
		
		return nroNuevo;
    }
    
    public Tipodocumentoidentidad getTipodocuementoIdentidadByKey(String key) {
    	return tipodocumentoidentidadRepository.findOneByKey(key);
    }
    
    public List<TipoPagoDescuento> listarTipoPagoDescuento() {
        return tipoPagoDescuentoRepository.findAll();
    }
    
    public Seriedocumento getSerieDocumentoByPlantaAndLinea(String planta_key, String linea_key) {
    	return seriedocumentoRepository.findOneByPlantaAndLinea(planta_key, linea_key);
    }
    
    public List<Seriedocumento> getSerieDocumentoByPlanta(String planta){
    	return seriedocumentoRepository.findAllByPlanta(planta);
    }
    
    public Seriedocumento getSerieDocumentoByid(String id) {
    	return seriedocumentoRepository.findOneById(Long.valueOf(id));
    }
    
    public void saveSerieDocumento(Seriedocumento seriedocumento) {
    	seriedocumentoRepository.save(seriedocumento);
    }
    
    public void saveSeriedocumentoBase(SeriedocumentoBase seriedocumentoBase) {
    	seriedocumentoBaseRepository.save(seriedocumentoBase);
    }
    
    public SeriedocumentoBase getSeriedocumentoBaseByPlanta(String planta) {
        SeriedocumentoBase bean = seriedocumentoBaseRepository.findOneByPlanta(planta);
    	return bean;
    }

    public List<Maquina> listarMaquinasByPlanta(Planta planta) {
        return maquinaRepository.findByPlanta(planta);
    }

    @Cacheable("etapas")
    public List<Etapa> listarEtapas() {
        return etapaRepository.findAll();
    }

    public List<LineaEtapa> listarLineasEtapasByPlanta(Planta planta) {
        return lineaEtapaRepository.findByPlanta(planta);
    }

    @Cacheable("normas")
    public List<Norma> listarNormas() {
        return normaRepository.findEstado(true);
    }

    @Cacheable("subnormas")
    public List<Subnorma> listarSubnormas() {
        return subnormaRepository.findAll();
    }

    @Cacheable("tipoMaquinas")
    public List<Tipomaquina> listarTipomaquinas() {
        return tipomaquinaRepository.findAll();
    }

    @Cacheable("reglasFreno")
    public List<Reglafreno> listarReglafrenos() {
        return reglafrenoRepository.findAll();
    }

    @Cacheable("reglasGases")
    public List<Reglagas> listarReglagases() {
        return reglagasRepository.findAll();
    }

    @Cacheable("reglasOpacidad")
    public List<Reglaopacidad> listarReglaopacidades() {
        return reglaopacidadRepository.findAll();
    }

    @Cacheable("reglasProfundidad")
    public List<Reglaprofundidad> listarReglaprofundidades() {
        return reglaprofundidadRepository.findAll();
    }

    @Cacheable("reglasSonora")
    public List<Reglasonora> listarReglasonoras() {
        return reglasonoraRepository.findAll();
    }

    @Cacheable("reglasSuspencion")
    public List<Reglasuspension> listarReglasuspenciones() {
        return reglasuspencionRepository.findAll();
    }

    @Cacheable("reglasAlineacion")
    public List<Reglaalineacion> listarReglaalineacion() {
        return reglaalineacionRepository.findAll();
    }

    @Cacheable("reglasLuces")
    public List<Reglaluces> listarReglaluces() {
        return reglalucesRepository.findAll();
    }

    @Cacheable("tipoInspecciones")
    public List<Tipoinspeccion> listarTipoinspecciones() {
        return tipoinspeccionRepository.findAll();
    }

    @Cacheable("tipoCertificados")
    public List<Tipocertificado> listarTipocertificados() {
        return tipocertificadoRepository.findAll();
    }

    @Cacheable("tipoAutorizaciones")
    public List<Tipoautorizacion> listarTipoautorizaciones() {
        return tipoautorizacionRepository.findAll();
    }

    @Cacheable("comprobanteestado")
    public List<Comprobanteestado> listarComprobanteestados() {
        return comprobanteestadoRepository.findAll();
    }

    @Cacheable("inspeccionestado")
    public List<Inspeccionestado> listarInspeccionestados() {
        return inspeccionestadoRepository.findAll();
    }

    @Cacheable("perfiles")
    public List<Perfil> listarPerfiles() {
        return perfilRepository.findAll();
    }
    
    public Inspeccionestado getInspeccionEstadoByKey(String key) {
    	return inspeccionestadoRepository.findOneByKey(key);
    }
    
    public Comprobanteestado getOneComprobanteestado(String key) {
    	List<Comprobanteestado> list = comprobanteestadoRepository.findAll();
    	for(Comprobanteestado bean : list) {
    		if(bean.getKey().equals(key))
    			return bean;
    	}
    	return null;
    }

    public List<Maestro> getAllMaestros() {
        List<Maestro> maestros = new ArrayList();

        List<Tarjeta> tarjetas = listarTarjetas();
        List<Categoria> categorias = listarCategorias();
        List<Marca> marcas = listarMarcas();
        List<Modelo> modelos = listarModelos();
        List<Color> colores = listarColores();
        List<Carroceria> carrocerias = listarCarrocerias();
        List<Tipodocumentoidentidad> tipodocumentoidentidads = listarTipodocumentoidentidad();
        List<Aseguradora> aseguradoras = listarAseguradoras();
        List<Pais> paises = listarPaises();
        List<Departamento> departamentos = listarDepartamentos();
        List<Provincia> provincias = listarProvincias();
        List<Distrito> distritos = listarDistritos();
        List<Comprobanteestado> comprobanteestados = listarComprobanteestados();
        List<Inspeccionestado> inspeccionestados = listarInspeccionestados();

        for (Tarjeta tarjeta : tarjetas) {
            Maestro maestro = new Maestro();
            maestro.setKey(tarjeta.getKey());
            maestro.setNombre(tarjeta.getNombre());
            maestro.setTipo("Tarjeta");
            maestros.add(maestro);
        }
        for (Categoria categoria : categorias) {
            Maestro maestro = new Maestro();
            maestro.setKey(categoria.getKey());
            maestro.setNombre(categoria.getNombre());
            maestro.setTipo("Categoría");
            maestros.add(maestro);
        }
        for (Marca marca : marcas) {
            Maestro maestro = new Maestro();
            maestro.setKey(marca.getKey());
            maestro.setNombre(marca.getNombre());
            maestro.setTipo("Marca");
            maestros.add(maestro);
        }
        for (Modelo modelo : modelos) {
            Maestro maestro = new Maestro();
            maestro.setKey(modelo.getKey());
            maestro.setNombre(modelo.getNombre());
            maestro.setTipo("Modelo");
            maestros.add(maestro);
        }
        for (Color color : colores) {
            Maestro maestro = new Maestro();
            maestro.setKey(color.getKey());
            maestro.setNombre(color.getNombre());
            maestro.setTipo("Color");
            maestros.add(maestro);
        }
        for (Carroceria carroceria : carrocerias) {
            Maestro maestro = new Maestro();
            maestro.setKey(carroceria.getKey());
            maestro.setNombre(carroceria.getNombre());
            maestro.setTipo("Carrocería");
            maestros.add(maestro);
        }
        for (Tipodocumentoidentidad tipodocumentoidentidad : tipodocumentoidentidads) {
            Maestro maestro = new Maestro();
            maestro.setKey(tipodocumentoidentidad.getKey());
            maestro.setNombre(tipodocumentoidentidad.getNombre());
            maestro.setTipo("Tipo de Documento de Identidad");
            maestros.add(maestro);
        }
        for (Aseguradora aseguradora : aseguradoras) {
            Maestro maestro = new Maestro();
            maestro.setKey(aseguradora.getKey());
            maestro.setNombre(aseguradora.getNombre());
            maestro.setTipo("Aseguradora");
            maestros.add(maestro);
        }
        for (Pais pais : paises) {
            Maestro maestro = new Maestro();
            maestro.setKey(pais.getKey());
            maestro.setNombre(pais.getNombre());
            maestro.setTipo("País");
            maestros.add(maestro);
        }
        for (Departamento departamento : departamentos) {
            Maestro maestro = new Maestro();
            maestro.setKey(departamento.getKey());
            maestro.setNombre(departamento.getNombre());
            maestro.setTipo("Departamento");
            maestros.add(maestro);
        }
        for (Provincia provincia : provincias) {
            Maestro maestro = new Maestro();
            maestro.setKey(provincia.getKey());
            maestro.setNombre(provincia.getNombre());
            maestro.setTipo("Provincia");
            maestros.add(maestro);
        }
        for (Distrito distrito : distritos) {
            Maestro maestro = new Maestro();
            maestro.setKey(distrito.getKey());
            maestro.setNombre(distrito.getNombre());
            maestro.setTipo("Distrito");
            maestros.add(maestro);
        }
        for (Comprobanteestado comprobanteestado : comprobanteestados) {
            Maestro maestro = new Maestro();
            maestro.setKey(comprobanteestado.getKey());
            maestro.setNombre(comprobanteestado.getNombre());
            maestro.setTipo("Estado Comprobante");
            maestros.add(maestro);
        }
        for (Inspeccionestado inspeccionestado : inspeccionestados) {
            Maestro maestro = new Maestro();
            maestro.setKey(inspeccionestado.getKey());
            maestro.setNombre(inspeccionestado.getNombre());
            maestro.setTipo("Estado Inspección");
            maestros.add(maestro);
        }
        return maestros;
    }

    public ResultPageBean<Maestro> listarMaestros(PageBean pageBean) {

        List<Maestro> maestros = getAllMaestros();
        List<Maestro> maestro2 = maestros.subList((pageBean.numPag - 1) * pageBean.totalFilasPorPagina,
                (pageBean.numPag) * pageBean.totalFilasPorPagina);

        ResultPageBean<Maestro> result = new ResultPageBean<>(maestros.size() / pageBean.totalFilasPorPagina,
                maestros.size(), maestro2);
        return result;
    }

    public ResultPageBean<Maestro> get(String field, PageBean pageBean) throws UnsupportedEncodingException {
        Map<String, String> mapField = QueryUtil.splitQuery(field);
        List<Maestro> maestros = getAllMaestros();
        List<Maestro> maestroFiltrado = new ArrayList();
        for (Maestro maestro : maestros) {
            if (maestro.getNombre().toLowerCase().contains(mapField.get("field").toLowerCase())) {
                maestroFiltrado.add(maestro);
            }
        }
        int maxResult = (pageBean.numPag) * pageBean.totalFilasPorPagina;
        if (maestroFiltrado.size() <= maxResult) {
            maxResult = maestroFiltrado.size();
        }
        List<Maestro> maestroPaginado = maestroFiltrado.subList((pageBean.numPag - 1) * pageBean.totalFilasPorPagina,
                maxResult);
        ResultPageBean<Maestro> result = new ResultPageBean<>(maestroFiltrado.size() / pageBean.totalFilasPorPagina,
                maestroFiltrado.size(), maestroPaginado);
        return result;
    }

    public Maestro getByKey(String key) {
        Maestro maestro = new Maestro();
        List<Maestro> maestros = getAllMaestros();
        for (Maestro maestrov : maestros) {
            if (maestrov.getKey().toLowerCase().equals(key.toLowerCase())) {
                maestro = maestrov;
                break;
            }
        }
        return maestro;
    }

    
    public void saveColor(Color color)
    {
    	 Color colorBD = colorRepository.findOneByKey(color.getKey());
         if (colorBD == null) {
        	 colorBD = new Color();
         }
         colorBD.setKey(color.getKey());
         colorBD.setNombre(color.getNombre());
         colorRepository.save(colorBD);
         
    }
    
    
    public void saveMarca(Marca marca)
    {
    	 
         Marca marcaBD = marcaRepository.findOneByKey(marca.getKey());
         if (marcaBD == null) {
             marcaBD = new Marca();
         }
         marcaBD.setKey(marca.getKey());
         marcaBD.setNombre(marca.getNombre());
         marcaRepository.save(marcaBD);
    }
    
    public void saveCarroceria(Carroceria carroceria)
    {
    	 
    	Carroceria carroceriaBD = carroceriaRepository.findOneByKey(carroceria.getKey());
        if (carroceriaBD == null) {
            carroceriaBD = new Carroceria();
        }
        carroceriaBD.setKey(carroceria.getKey());
        carroceriaBD.setNombre(carroceria.getNombre());
        carroceriaRepository.save(carroceriaBD);
    }
    
    
    public void saveModelo(Modelo modelo)
    {
    	 
    	  Modelo modeloDB = modeloRepository.findOneByKey(modelo.getKey());
          if (modeloDB == null) {
              modeloDB = new Modelo();
          }
          modeloDB.setKey(modelo.getKey());
          modeloDB.setNombre(modelo.getNombre());
          modeloRepository.save(modeloDB);
    }
    
    public void saveClase(Vehiculoclase clase)
    {
    	 
    	Vehiculoclase claseDB = vehiculoclaseRepository.findOneByKey(clase.getKey());
          if (claseDB == null) {
        	  claseDB = new Vehiculoclase();
          }
          claseDB.setKey(clase.getKey());
          claseDB.setNombre(clase.getNombre());
          vehiculoclaseRepository.save(claseDB);
    }


    public Pais getPaisByKey(String key)
    {
        for(Pais p :listarPaises())
        {
            if(p.getKey().equals(key))
                return p;
        }

        return null;
    }


    public Provincia getProvinciaByKey(String key)
    {
        for(Provincia p :listarProvincias())
        {
            if(p.getKey().equals(key))
                return p;
        }

        return null;
    }

    public Distrito getDistritoByKey(String key)
    {
        for(Distrito d :listarDistritos())
        {
            if(d.getKey().equals(key))
                return d;
        }

        return null;
    }

    public Departamento getDepartamentoByKey(String key)
    {
        for(Departamento d :listarDepartamentos())
        {
            if(d.getKey().equals(key))
                return d;
        }

        return null;
    }

    public Planta getPlantaByKeyMtc(String key)
    {
        for(Planta p :listarPlantas())
        {
            if(p.getKeyMtc()!=null && p.getKeyMtc().equals(key))
                return p;
        }

        return null;
    }

    public Conceptoinspeccion getConceptoByKey(String key)
    {
        for(Conceptoinspeccion c :listarConceptoinspecciones(true))
        {
            if(c.getKey().equals(key))
                return c;
        }

        return null;
    }

    public void InsertSistEvents(SistEventsBean bean)throws Exception{
        Date fechaActual= new Date();
        //bean.setFecha(fechaActual);

        SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA);

        String hql ="Insert into sisteventos (fechmovimiento, inspeccion_nrodocumentoinspeccion,usuarioevento_username,nombreevento) values"
                +"('"+dtfPeru.format(fechaActual)+"',"
                + "'"+bean.getNroinspeccion()+"',"
                +"'"+bean.getUsuario()+"',"
                +"'"+bean.getNombreevento()+"')";

        Query query = entityManager.createNativeQuery(hql);

        query.executeUpdate();
    }



	public void insertarCITVInfo(CitvInfoBean bean) throws Exception {
			String hql="INSERT INTO citv_info (citv_nombre,nroinspeccion) values "
					+"('"+bean.getNombre()
					+ "' ,'"+bean.getNroinspeccion()+"')";
			Query query = entityManager.createNativeQuery(hql);
			query.executeUpdate();
	}

	public List<NormaBean> getNormaByCategoria(String categoria_key) throws Exception {
        List<NormaBean> result = new ArrayList<>();
        String hql="Select distinct norma.codigovalor,norma.nombrevalor,s.grave,s.leve, s.muygrave,s.manual,norma.id, norma.estado from norma " +
                " inner join norma_subnorma ns on norma.id = ns.norma_id " +
                " inner join subnorma s on ns.subnormas_id = s.id " +
                " inner join subnorma_categoria sc on s.id = sc.subnorma_id " +
                " where sc.categorias_key='"+categoria_key+"' and anio=2015 and norma.estado=trues order by norma.id asc";
        Query query= entityManager.createNativeQuery(hql.toString());
        List<Object> resultado= (List<Object>) query.getResultList();
        Iterator<Object> itr= resultado.iterator();
        while (itr.hasNext()){
            NormaBean bean= new NormaBean();
            Object[] obj= (Object[]) itr.next();
            bean.setCodigovalor(obj[0]!=null? obj[0].toString():"");
            bean.setNombrevalor(obj[1]!=null? obj[1].toString():"");
            bean.setGrave(Boolean.valueOf(obj[2]!=null? obj[2].toString():""));
            bean.setLeve(Boolean.valueOf(obj[3]!=null? obj[3].toString():""));
            bean.setMuygrave(Boolean.valueOf(obj[4]!=null? obj[4].toString():""));
            bean.setManual(Boolean.valueOf(obj[5]!=null? obj[5].toString():""));
            bean.setId(Integer.valueOf(obj[6]!=null? obj[6].toString():""));
            result.add(bean);
        }
        return result;
    }

    public List<DetCajaBean> getDetelleCajaAll() throws Exception {
        List<DetCajaBean> result = new ArrayList<>();
        String hql = "select categoria_key, concepto_key, tipoautorizacion_key, tipocertificado_key, tipoinspeccion_key from detcaja";
        Query query= entityManager.createNativeQuery(hql.toString());
        List<Object> resultado= (List<Object>) query.getResultList();
        Iterator<Object> itr= resultado.iterator();
        while (itr.hasNext()){
            DetCajaBean bean= new DetCajaBean();
            Object[] obj= (Object[]) itr.next();
            bean.setCategoria_key(obj[0]!=null? obj[0].toString():"");
            bean.setConcepto_key(obj[1]!=null? obj[1].toString():"");
            bean.setTipoautorizacion_key(obj[2]!=null? obj[2].toString():"");
            bean.setTipocertificado_key(obj[3]!=null? obj[3].toString():"");
            bean.setTipoinspeccion_key(obj[4]!=null? obj[4].toString():"");
            result.add(bean);
        }
        return result;
    }

    public Categoria GetCategoriaByKey(String key) throws Exception{
        Categoria results = new Categoria();
        String hql = " select key, nombre, keymtc from categoria where key='"+key+"'";
        Query query= entityManager.createNativeQuery(hql.toString());
        System.out.println(hql.toString());
        List<Object> resultado = (List<Object>) query.getResultList();
        Iterator<Object> itr = resultado.iterator();
        while (itr.hasNext()) {
            Categoria bean = new Categoria();
            Object[] obj = (Object[]) itr.next();
            bean.setKey(obj[0] != null ? obj[0].toString() : "");
            bean.setNombre(obj[1] != null ? obj[1].toString() : "");
            bean.setKeyMTC(obj[2] != null ? Integer.parseInt(obj[2].toString()) : 0);
            results=bean;
        }
        return results;
    }
    public List<DetCajaBean> getDetelleCajabyConcepto(String abreviatura) throws Exception {
        List<DetCajaBean> result = new ArrayList<>();
        String hql = "select categoria_key, concepto_key, tipoautorizacion_key, tipocertificado_key, tipoinspeccion_key from detcaja dc"
                + " inner join categoria c on dc.categoria_key=c.key"
                + " inner join conceptoinspeccion ci on dc.concepto_key=ci.key"
                + " inner join tipoautorizacion ta on dc.tipoautorizacion_key=ta.key"
                + " inner join tipocertificado tc on dc.tipocertificado_key=tc.key"
                + " inner join tipoinspeccion ti on dc.tipoinspeccion_key=ti.key"
                + " where ci.abreviatura LIKE '%" + abreviatura + "%'";
        Query query = entityManager.createNativeQuery(hql.toString());
        List<Object> resultado = (List<Object>) query.getResultList();
        Iterator<Object> itr = resultado.iterator();
        while (itr.hasNext()) {
            DetCajaBean bean = new DetCajaBean();
            Object[] obj = (Object[]) itr.next();
            bean.setCategoria_key(obj[0] != null ? obj[0].toString() : "");
            bean.setConcepto_key(obj[1] != null ? obj[1].toString() : "");
            bean.setTipoautorizacion_key(obj[2] != null ? obj[2].toString() : "");
            bean.setTipocertificado_key(obj[3] != null ? obj[3].toString() : "");
            bean.setTipoinspeccion_key(obj[4] != null ? obj[4].toString() : "");
            result.add(bean);
        }
        return result;
    }
    public List<DetCajaBean> getDetelleCajabyConceptoAndCategoria(String abreviatura, String categoria) throws Exception {
        List<DetCajaBean> result = new ArrayList<>();
        String hql = "select categoria_key, concepto_key, tipoautorizacion_key, tipocertificado_key, tipoinspeccion_key from detcaja dc"
                + " inner join categoria c on dc.categoria_key=c.key"
                + " inner join conceptoinspeccion ci on dc.concepto_key=ci.key"
                + " inner join tipoautorizacion ta on dc.tipoautorizacion_key=ta.key"
                + " inner join tipocertificado tc on dc.tipocertificado_key=tc.key"
                + " inner join tipoinspeccion ti on dc.tipoinspeccion_key=ti.key"
                + " where ci.abreviatura LIKE '%" + abreviatura + "%' and c.key='"+categoria+"'";
        Query query = entityManager.createNativeQuery(hql.toString());
        List<Object> resultado = (List<Object>) query.getResultList();
        Iterator<Object> itr = resultado.iterator();
        while (itr.hasNext()) {
            DetCajaBean bean = new DetCajaBean();
            Object[] obj = (Object[]) itr.next();
            bean.setCategoria_key(obj[0] != null ? obj[0].toString() : "");
            bean.setConcepto_key(obj[1] != null ? obj[1].toString() : "");
            bean.setTipoautorizacion_key(obj[2] != null ? obj[2].toString() : "");
            bean.setTipocertificado_key(obj[3] != null ? obj[3].toString() : "");
            bean.setTipoinspeccion_key(obj[4] != null ? obj[4].toString() : "");
            result.add(bean);
        }
        return result;
    }
    public List<DetCajaBean> getDetelleCajaTipoinspeccionbyConceptoAndCategoria(String abreviatura, String categoria,String tipoinspeccion) throws Exception {
        List<DetCajaBean> result = new ArrayList<>();
        String hql = "select categoria_key, concepto_key, tipoautorizacion_key, tipocertificado_key, tipoinspeccion_key from detcaja dc"
                + " inner join categoria c on dc.categoria_key=c.key"
                + " inner join conceptoinspeccion ci on dc.concepto_key=ci.key"
                + " inner join tipoautorizacion ta on dc.tipoautorizacion_key=ta.key"
                + " inner join tipocertificado tc on dc.tipocertificado_key=tc.key"
                + " inner join tipoinspeccion ti on dc.tipoinspeccion_key=ti.key"
                + " where ci.abreviatura LIKE '%" + abreviatura + "%' and c.key='"+categoria+"'"
                +" and ti.nombre='"+tipoinspeccion+"'";
        Query query = entityManager.createNativeQuery(hql.toString());
        List<Object> resultado = (List<Object>) query.getResultList();
        Iterator<Object> itr = resultado.iterator();
        while (itr.hasNext()) {
            DetCajaBean bean = new DetCajaBean();
            Object[] obj = (Object[]) itr.next();
            bean.setCategoria_key(obj[0] != null ? obj[0].toString() : "");
            bean.setConcepto_key(obj[1] != null ? obj[1].toString() : "");
            bean.setTipoautorizacion_key(obj[2] != null ? obj[2].toString() : "");
            bean.setTipocertificado_key(obj[3] != null ? obj[3].toString() : "");
            bean.setTipoinspeccion_key(obj[4] != null ? obj[4].toString() : "");
            result.add(bean);
        }
        return result;
    }
    public List<DetCajaBean> getDetelleCajaTipoautorizacionbyConceptoAndCategoria(String abreviatura, String categoria,String tipoinspeccion,String tipocertificado) throws Exception {
        List<DetCajaBean> result = new ArrayList<>();
        String hql = "select categoria_key, concepto_key, tipoautorizacion_key, tipocertificado_key, tipoinspeccion_key from detcaja dc"
                + " inner join categoria c on dc.categoria_key=c.key"
                + " inner join conceptoinspeccion ci on dc.concepto_key=ci.key"
                + " inner join tipoautorizacion ta on dc.tipoautorizacion_key=ta.key"
                + " inner join tipocertificado tc on dc.tipocertificado_key=tc.key"
                + " inner join tipoinspeccion ti on dc.tipoinspeccion_key=ti.key"
                + " where ci.abreviatura LIKE '%" + abreviatura + "%' and c.key='"+categoria+"'"
                +" and ti.nombre='"+tipoinspeccion+"' and tc.abreviacion='"+tipocertificado+"'";
        System.out.println(hql.toString());
        Query query = entityManager.createNativeQuery(hql.toString());
        List<Object> resultado = (List<Object>) query.getResultList();
        Iterator<Object> itr = resultado.iterator();
        while (itr.hasNext()) {
            DetCajaBean bean = new DetCajaBean();
            Object[] obj = (Object[]) itr.next();
            bean.setCategoria_key(obj[0] != null ? obj[0].toString() : "");
            bean.setConcepto_key(obj[1] != null ? obj[1].toString() : "");
            bean.setTipoautorizacion_key(obj[2] != null ? obj[2].toString() : "");
            bean.setTipocertificado_key(obj[3] != null ? obj[3].toString() : "");
            bean.setTipoinspeccion_key(obj[4] != null ? obj[4].toString() : "");
            result.add(bean);
        }
        return result;
    }

    public void save(Maestro maestro) {

        if (maestro.getTipo().equals("Tarjeta")) {
            Tarjeta tarjeta = tarjetaRepository.findOneByKey(maestro.getKey());
            if (tarjeta == null) {
                tarjeta = new Tarjeta();
            }
            tarjeta.setKey(maestro.getKey());
            tarjeta.setNombre(maestro.getNombre());
            tarjetaRepository.save(tarjeta);
            return;

        }

        if (maestro.getTipo().equals("Categoría")) {
            Categoria categoria = categoriaRepository.findOneByKey(maestro.getKey());
            if (categoria == null) {
                categoria = new Categoria();
            }
            categoria.setKey(maestro.getKey());
            categoria.setNombre(maestro.getNombre());
            categoriaRepository.save(categoria);
            return;

        }

        if (maestro.getTipo().equals("Marca")) {
            Marca marca = marcaRepository.findOneByKey(maestro.getKey());
            if (marca == null) {
                marca = new Marca();
            }
            marca.setKey(maestro.getKey());
            marca.setNombre(maestro.getNombre());
            marcaRepository.save(marca);
            return;

        }

        if (maestro.getTipo().equals("Modelo")) {
            Modelo modelo = modeloRepository.findOneByKey(maestro.getKey());
            if (modelo == null) {
                modelo = new Modelo();
            }
            modelo.setKey(maestro.getKey());
            modelo.setNombre(maestro.getNombre());
            modeloRepository.save(modelo);
            return;
        }

        if (maestro.getTipo().equals("Color")) {
            Color color = colorRepository.findOneByKey(maestro.getKey());
            if (color == null) {
                color = new Color();
            }
            color.setKey(maestro.getKey());
            color.setNombre(maestro.getNombre());
            colorRepository.save(color);
            return;
        }

        if (maestro.getTipo().equals("Carrocería")) {
            Carroceria carroceria = carroceriaRepository.findOneByKey(maestro.getKey());
            if (carroceria == null) {
                carroceria = new Carroceria();
            }
            carroceria.setKey(maestro.getKey());
            carroceria.setNombre(maestro.getNombre());
            carroceriaRepository.save(carroceria);
            return;
        }

        if (maestro.getTipo().equals("Tipo de Documento de Identidad")) {
            Tipodocumentoidentidad tipodocumentoidentidad = tipodocumentoidentidadRepository
                    .findOneByKey(maestro.getKey());
            if (tipodocumentoidentidad == null) {
                tipodocumentoidentidad = new Tipodocumentoidentidad();
            }
            tipodocumentoidentidad.setKey(maestro.getKey());
            tipodocumentoidentidad.setNombre(maestro.getNombre());
            tipodocumentoidentidadRepository.save(tipodocumentoidentidad);
            return;
        }

        if (maestro.getTipo().equals("Aseguradora")) {
            Aseguradora aseguradora = aseguradoraRepository.findOneByKey(maestro.getKey());
            if (aseguradora == null) {
                aseguradora = new Aseguradora();
            }
            aseguradora.setKey(maestro.getKey());
            aseguradora.setNombre(maestro.getNombre());
            aseguradoraRepository.save(aseguradora);
            return;
        }
        if (maestro.getTipo().equals("País")) {
            Pais pais = paisRepository.findOneByKey(maestro.getKey());
            if (pais == null) {
                pais = new Pais();
            }
            pais.setKey(maestro.getKey());
            pais.setNombre(maestro.getNombre());
            paisRepository.save(pais);
            return;
        }

        if (maestro.getTipo().equals("Departamento")) {
            Departamento departamento = departamentoRepository.findOneByKey(maestro.getKey());
            if (departamento == null) {
                departamento = new Departamento();
            }
            departamento.setKey(maestro.getKey());
            departamento.setNombre(maestro.getNombre());
            departamentoRepository.save(departamento);
            return;
        }

        if (maestro.getTipo().equals("Provincia")) {
            Provincia provincia = provinciaRepository.findOneByKey(maestro.getKey());
            if (provincia == null) {
                provincia = new Provincia();
            }
            provincia.setKey(maestro.getKey());
            provincia.setNombre(maestro.getNombre());
            provinciaRepository.save(provincia);
            return;
        }

        if (maestro.getTipo().equals("Distrito")) {
            Distrito distrito = distritoRepository.findOneByKey(maestro.getKey());
            if (distrito == null) {
                distrito = new Distrito();
            }
            distrito.setKey(maestro.getKey());
            distrito.setNombre(maestro.getNombre());
            distritoRepository.save(distrito);
            return;
        }

        if (maestro.getTipo().equals("Estado Comprobante")) {
            Comprobanteestado comprobanteestado = comprobanteestadoRepository.findOneByKey(maestro.getKey());
            if (comprobanteestado == null) {
                comprobanteestado = new Comprobanteestado();
            }
            comprobanteestado.setKey(maestro.getKey());
            comprobanteestado.setNombre(maestro.getNombre());
            comprobanteestadoRepository.save(comprobanteestado);
            return;
        }

        if (maestro.getTipo().equals("Estado Inspección")) {
            Inspeccionestado inspeccionestado = inspeccionestadoRepository.findOneByKey(maestro.getKey());
            if (inspeccionestado == null) {
                inspeccionestado = new Inspeccionestado();
            }
            inspeccionestado.setKey(maestro.getKey());
            inspeccionestado.setNombre(maestro.getNombre());
            inspeccionestadoRepository.save(inspeccionestado);
            return;
        }
    }

    @Cacheable("condicionvigenciacertificado")
    public List<CondicionvigenciaCertificado> listarCondicionvigenciacertificado() {
        return condicionvigenciaCertificadoRepository.findAll();
    }
    
    public TipoPoliza getTipoPolizaByKey(String key) {
    	return tipoPolizaRepository.getOneByKey(key);
    }
    
    public List<TipoPoliza> getAllTipoPoliza(){
    	return tipoPolizaRepository.findAll();
    }
    
    public List<Norma> findNormaByTipoMaquina(String tipoMaquina){
		return normaRepository.findAllByTipoMaquina(tipoMaquina);
    	
    }
    
    public Inspeccionestado findOneByNroDocumentoinspeccion(String nrodocumentoinspeccion){
    	return inspeccionestadoRepository.findOneByNroInspeccion(nrodocumentoinspeccion);
    }
    
    public Usuario getOneByUsername(String username){
    	return usuarioService.getUsuario(username);
    }
    
    public List<Cuentacorriente> getCuentaCorrienteByEmpresa(String empresa){
    	return cuentacorrienteRepository.findAllByEmpresa(empresa);
    }
    
    public Ejecutivo getEjecutivoByDni(String dni) {
    	return ejecutivoRepository.findOneByDNI(dni);
    }
    
    public List<Maquina> getMaquinaByLinea(String linea){
    	return maquinaRepository.findMaquinaByLinea(linea);
    }

    public List<CertificadoraGas> getCertificadoraGas(){
        return  certificadoraGasRepository.findAll();
    }
}
