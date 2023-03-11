package com.farenet.nodo.maestro.api.sunat.services;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteestadoRepository;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoBaseRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.repository.UsuarioRepository;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.services.ST_MensajeService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.task.domain.DataTask;
import com.farenet.nodo.maestro.api.task.domain.Execute;
import com.farenet.nodo.maestro.api.task.domain.Task;
import com.farenet.nodo.maestro.api.util.NumberToLetter;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

import cl.dbnet.bajas.ArrayOfBajas;
import cl.dbnet.carga.ArrayOfDatosAdicionales;
import cl.dbnet.carga.ArrayOfDetalle;
import cl.dbnet.carga.ArrayOfImptoReten;
import cl.dbnet.carga.CamposDetalle;
import cl.dbnet.carga.CamposHead;
import cl.dbnet.carga.CustomerETDLoadASP;
import cl.dbnet.carga.CustomerETDLoadASPSoap;
import cl.dbnet.carga.DatosAdicionales;
import cl.dbnet.carga.DescuentosRecargosyOtros;
import cl.dbnet.carga.Detalle;
import cl.dbnet.carga.Encabezado;
import cl.dbnet.carga.Extras;
import cl.dbnet.carga.ImptoReten;
import cl.dbnet.bajas.WssCargaBajas;
import cl.dbnet.bajas.WssCargaBajasSoap;

@Service
@Transactional
public class BajaComprobantesManualSUNATService {
	
	private static final QName SERVICE_NAME = new QName("http://www.dbnet.cl", "WssCargaBajas");
	
	@Autowired
	private TriggerTaskFactory trigger;
	
	private Inspeccion inspeccion;
	private WssCargaBajasSoap port ;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private ST_MensajeService st_MensajeService;
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComprobanteestadoRepository comprobanteestadoRepository;
	
	public String enviar(String nrocomprobante, String planta, String observacion) {
		// TODO Auto-generated method stub
		try {
			 URL wsdlURL = WssCargaBajas.WSDL_LOCATION;
			 WssCargaBajas ss = new WssCargaBajas(wsdlURL, SERVICE_NAME);
		     port = ss.getWssCargaBajasSoap();  
			
			inspeccion = inspeccionService.getOneByNroComprobanteAndPlanta(nrocomprobante, planta);
			
			Comprobante comprobante = inspeccion.getComprobante(nrocomprobante);
			
			if(comprobante == null ) {
				trigger.launch("", "enviar.baja.sunat.error",inspeccion.getNrodocumentoinspeccion() , "No existe :" +  nrocomprobante);
				throw new Exception("Error");
			}
			
			//anulacion de comprobante
			Comprobanteestado comprobanteestado = comprobanteestadoRepository.findOneByKey("ANU");
			comprobante.setComprobanteestado(comprobanteestado);
			comprobante.setImportetotal(0);
			comprobante.setBaseimponible(0);
			comprobante.setIgv(0);
			comprobante.setFechanulacion(new Timestamp(System.currentTimeMillis()));
			comprobante.setObservacionDevolucion(observacion);
			Usuario usuarioAnu = usuarioRepository.findOneByUser(Util.getUserNameContext());
			comprobante.setUsuarioanulacion(usuarioAnu);
			comprobante.setSendedtooffisis(false);
			
			SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
			
			
			cl.dbnet.bajas.Bajas baja = new cl.dbnet.bajas.Bajas();
			baja.setFechEmis(dtf.format(comprobante.getFechapago()));
			
			String[] parseComprobante = comprobante.getNrocomprobante().split("-");
			baja.setFoliInte(parseComprobante[1]);
			baja.setSerieInte(parseComprobante[0]);
			baja.setMotivAnul(inspeccion.getObservacionAnulado());
			baja.setTipoDocu(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")?"1":"3");
			
			baja.setMotivAnul(observacion);
			
			String _cargaBajas_ruc = comprobante.getEmpresa().getRuc();
		    cl.dbnet.bajas.ArrayOfBajas _cargaBajas_archivoBajas = new ArrayOfBajas();
		    
		    _cargaBajas_archivoBajas.getBajas().add(baja);
		    
		    cl.dbnet.bajas.Mensaje _cargaBajas__return = port.cargaBajas(
		    		_cargaBajas_ruc,
		    		_cargaBajas_archivoBajas);
		    
		    //actualizamos comprobante
		    comprobanteService.guardar(comprobante);
		    
		    //grabando en el st_mensaje 
		    st_MensajeService.guardar(new ST_Mensaje("BAJA",_cargaBajas__return, inspeccion.getNrodocumentoinspeccion(), comprobante.getNrocomprobante()));
		    trigger.launch("", "enviar.baja.sunat.ok", inspeccion.getNrodocumentoinspeccion() , usuarioAnu.user + " - " + comprobante.getNrocomprobante() );
		    if(_cargaBajas__return.getMensajes().indexOf("OK") != -1){
		    	return "Se anulo de manera correcta";
		    }else{
		    	return "Error al anular";
		    }
		    
		}catch(Exception e) {
			e.printStackTrace();
			trigger.launch("", "enviar.baja.sunat.error", inspeccion.getNrodocumentoinspeccion() ,  Util.printError(e));
			return "Error al anular";
		}


	}

	
}
