package com.farenet.nodo.maestro.api.soporte.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.CondicionvigenciaCertificado;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.caja.service.SeriedocumentoService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Certificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.CertificadoError;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Seriedocumento;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoautorizacion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipocertificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoBaseRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipoautorizacionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipocertificadoRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TipoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.linea.domain.Defecto;
import com.farenet.nodo.maestro.api.linea.domain.ResultadoMaquina;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.AnulacionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.CorrelativosBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.ErrorImpresionBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlantBean.Line;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.TraspasoBean;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.soporte.domain.bean.DatosCertificadoBean;
import com.farenet.nodo.maestro.api.soporte.domain.bean.EdicionFirmaBean;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

@Service
@Transactional
public class EdicionService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private TipocertificadoRepository tipocertificadoRepository;
	
	@Autowired
	private TipoinspeccionRepository tipoinspeccionRepository;
	
	@Autowired
	private TipoautorizacionRepository tipoautorizacionRepository;
	
	@Autowired
	private SeriedocumentoService seriedocumentoService;
	
	@Autowired
	private InspeccionRepository inspeccionRepository;
	
	@Autowired
	private SeriedocumentoRepository seriedocumentoRepository;
	
	@Autowired
	private SeriedocumentoBaseRepository seriedocumentoBaseRepository;
	
	@Autowired
	private TriggerTaskFactory trigger;
	
	public void cambiarFirma(EdicionFirmaBean firmaBean){
		Usuario usuario = maestroService.getOneByUsername(firmaBean.getUsuarioing());
		String hql = "update Inspeccion "
				+ "set usuarioingcertificador = :usuarioingcertificador where nrodocumentoinspeccion = :nrodocumentoinspeccion";
		Query query = entityManager.createQuery(hql);
		query.setParameter("usuarioingcertificador", usuario);
		query.setParameter("nrodocumentoinspeccion", firmaBean.getNrodocumentoinspeccion());
		query.executeUpdate();
	}
	
	public void cambiarDatosdeCertificado(DatosCertificadoBean datosCertificadoBean) throws Exception{
		Usuario usuario = maestroService.getOneByUsername(Util.getUserNameContext());
		Tipocertificado tipocertificado = tipocertificadoRepository.findOneByKey(datosCertificadoBean.getTipocertificado());
		Tipoinspeccion tipoinspeccion = tipoinspeccionRepository.findOneBykey(datosCertificadoBean.getTipoinspeccion());
		Tipoautorizacion tipoautorizacion = tipoautorizacionRepository.findOneByKey(datosCertificadoBean.getTipoautorizacion());
		Inspeccion inspeccion = inspeccionService.get(datosCertificadoBean.getNrodocumentoinspeccion());
		inspeccion.setTipocertificado(tipocertificado);
		inspeccion.setTipoautorizacion(tipoautorizacion);
		inspeccion.setTipoinspeccion(tipoinspeccion);
		List<CondicionvigenciaCertificado> condicionvigenciaCertificadoService = maestroService.listarCondicionvigenciacertificado();
		
		int mesesVigencia = UIUtils.mesesVigencia(inspeccion, condicionvigenciaCertificadoService);
        inspeccion.setVigencia(mesesVigencia + " MESES");

        long milisecondsMesesVigencia = UIUtils.monthToMiliseconds(mesesVigencia);
        long milisecondsToday = new Date().getTime();
        Date fechInicioVigencia = new Date();
       
        if (inspeccion.getFechiniciovigencia()!=null) {
        	fechInicioVigencia = inspeccion.getFechiniciovigencia();
            milisecondsToday = fechInicioVigencia.getTime();
        }
        
        Timestamp fechVencimiento1 = new Timestamp(milisecondsToday + milisecondsMesesVigencia);
        inspeccion.setFechvencimiento(fechVencimiento1);
        
        trigger.launch(usuario.getUser(), "edicion.datocertificado", "Tipo Certificado: " + tipocertificado.getAbreviacion() + " | Tipo Inspeccion: "+
        tipoinspeccion.getNombre() + " |  Tipo Autorizacion: " + tipoautorizacion.getAmbito()
        		,  null);
        
		inspeccionService.guardarInspeccionEstadoCon(inspeccion);
		
	}
	
	public void actualizarCorrelativos(CorrelativosBean bean) {
		Seriedocumento seriedocumento = seriedocumentoService.getOne(bean.getId());
		seriedocumento.setNroinicio(bean.getNroinicio());
		seriedocumento.setNroactual(bean.getNroactual());
		seriedocumento.setNromaximo(bean.getNromaximo());
		maestroService.saveSerieDocumento(seriedocumento);
	}
	
	@Transactional
	public void generarErrorImpresion(ErrorImpresionBean bean) {
		
		
		Usuario usuario = maestroService.getOneByUsername(Util.getUserNameContext());
		Inspeccion inspeccion = inspeccionService.get(bean.getNroinspeccion());
		Linea linea = inspeccion.getLastComprobante().getLinea();
    	Seriedocumento seriedocumento = seriedocumentoRepository.findOneByPlantaAndLinea(linea.getPlanta().getKey(), linea.getKey());
    	SeriedocumentoBase base = seriedocumentoBaseRepository.findOneByPlanta(linea.getPlanta().getKey());
    	Long nroNuevoHojaValorada = (seriedocumento.getNroactual() +1);
		
		Certificado certificado = inspeccion.getLastCertificado();
		
		CertificadoError error = new CertificadoError();
		error.setTipoError(bean.getTipoError());
		error.setNrohojaValorada(certificado.getNrohojaValorada());
		error.setDescripcionError("(APP) "+bean.getDescripcion());
		error.setCertificado(certificado);
		error.setFechacreacion(new Timestamp(new Date().getTime()));
		error.setUsuarioanulacion(usuario);
		
		String nrohojaValorada = UIUtils.formatoNroCertificadoByPlanta(base, nroNuevoHojaValorada);
		
		inspeccion.getLastCertificado().getCertificadoErrors().add(error);
		inspeccion.getLastCertificado().setNrohojaValorada(nrohojaValorada);
		
		//recorremos el acceso del certificado
		if(inspeccion.getCertificados()!=null) {
			for(Certificado certificado2 : inspeccion.getCertificados()) {
				certificado2.setInspeccion(inspeccion);
				
				if(certificado2.getCertificadoErrors() != null && !certificado2.getCertificadoErrors().isEmpty()) {
					for(CertificadoError certificadoError : certificado2.getCertificadoErrors()) {
						certificadoError.setCertificado(certificado2);
					}
				}
			}
		
		}
			
		inspeccionRepository.save(inspeccion);
		seriedocumentoRepository.save(seriedocumento);
		
	}
	
	public void traspasarResultado(TraspasoBean traspasoBean) throws Exception {
		Inspeccion inspeccionAnu = inspeccionService.get(traspasoBean.getNroinspeccionAnu());
		Inspeccion inspeccionApasar = inspeccionService.get(traspasoBean.getNroinspeccionApasar());
		
		if(traspasoBean.getPlacaAnu().equalsIgnoreCase(traspasoBean.getPlacaApasar())) {
			inspeccionApasar.setResultado(inspeccionAnu.getResultado());
			List<ResultadoMaquina> list = new ArrayList<>();
			for(ResultadoMaquina resultadoMaquina : inspeccionAnu.getResultadosMaquina()) {
				ResultadoMaquina bean = new ResultadoMaquina();
				bean.setData(resultadoMaquina.getData());
				bean.setFechainicio(resultadoMaquina.getFechainicio());
				bean.setFechafin(resultadoMaquina.getFechafin());
				bean.setInspVisual(resultadoMaquina.getInspVisual());
				bean.setManual(resultadoMaquina.getManual());
				bean.setPostdata(resultadoMaquina.getPostdata());
				bean.setResultado(resultadoMaquina.getResultado());
				
				List<Defecto> defectos = new ArrayList<>();
				for(Defecto defecto : resultadoMaquina.getDefectos()) {
					Defecto defecto2 = new Defecto();
					defecto2.setCodigovalor(defecto.getCodigovalor());
					defecto2.setNivelpeligro(defecto.getNivelpeligro());
					defecto2.setNombrevalor(defecto.getNombrevalor());
					defectos.add(defecto2);
				}
				
				bean.setDefectos(defectos);
				
				list.add(bean);
			}
			inspeccionApasar.getResultadosMaquina().addAll(list);
			inspeccionApasar.setPosicion(inspeccionAnu.getPosicion());
		}else {
			List<ResultadoMaquina> result = UIUtils.pasarResultadosSinFoto(inspeccionAnu.getResultadosMaquina());
			inspeccionApasar.setResultado(inspeccionAnu.getResultado());
			inspeccionApasar.getResultadosMaquina().addAll(result);
			inspeccionApasar.setPosicion(POSICION.FOTO);
		}
		
		inspeccionService.guardar(inspeccionApasar);
		
	}
	
	public void anularInspeccion(AnulacionBean anulacionBean) throws Exception {
		Inspeccion inspeccion = inspeccionService.get(anulacionBean.getNroinspeccion());
		Inspeccionestado inspeccionestadoANU = maestroService.getInspeccionEstadoByKey("ANU");
		Comprobanteestado comprobanteestadoANU = maestroService.getOneComprobanteestado("ANU");
		Usuario usuario = maestroService.getOneByUsername(Util.getUserNameContext());
		
		//solo los comprobantes mayores a cero tienen estado, y al anular se le setea a cero
        if(inspeccion.getLastComprobante().getImportetotal()>0 || 
        		(inspeccion.getLastComprobante().getTipodescuento()!=null && inspeccion.getLastComprobante().getTipodescuento().getKey().toLowerCase().equals("corte")))
        {
             inspeccion.getLastComprobante().setComprobanteestado(comprobanteestadoANU);
             inspeccion.getLastComprobante().setImportetotal(0);
             inspeccion.getLastComprobante().setIgv(0);
             inspeccion.getLastComprobante().setBaseimponible(0);
        }else{
        	inspeccion.getLastComprobante().setComprobanteestado(null);
        }
		
      //anular los certificados
        for(Certificado certificado : inspeccion.getCertificados())
        {
        	certificado.setAnulado(true);
        	certificado.setFechanulacion(new Timestamp(new Date().getTime()));
        	certificado.setObservacionAnulado("(APP) "+anulacionBean.getDescripcion());
        	certificado.setUsuarioanulacion(usuario);
        }
        
        inspeccion.getLastComprobante().setFechanulacion(new Timestamp(new Date().getTime()));
        inspeccion.getLastComprobante().setUsuarioanulacion(usuario);
        inspeccion.getLastComprobante().setObservacionDevolucion("(APP) "+anulacionBean.getDescripcion());
        
        inspeccion.setInspeccionestado(inspeccionestadoANU);
        
        if(inspeccion.getFechconsolidado()==null)
        	inspeccion.setFechconsolidado(new Timestamp(new Date().getTime()));
        
        inspeccion.setFechanulacion(new Timestamp(new Date().getTime()));
        inspeccion.setUsuarioanulacion(usuario);
        inspeccion.setObservacionAnulado("(APP) "+anulacionBean.getDescripcion());
        inspeccion.setTipoError(anulacionBean.getTipoError());
        
        inspeccionService.saveAndAnular(inspeccion);
        
	}
	
}
