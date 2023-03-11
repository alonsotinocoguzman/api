package com.farenet.nodo.maestro.api.task.logic;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.namespace.QName;

import org.springframework.context.ApplicationContext;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoBaseRepository;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
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

@Execute(target= "enviar.baja.sunat")
public class BajaComprobantesSUNAT extends Task {

	private static final QName SERVICE_NAME = new QName("http://www.dbnet.cl", "WssCargaBajas");

	private EntityManager em;
	
	
	 private TriggerTaskFactory trigger;
	
	private Inspeccion inspeccion;

    
    private WssCargaBajasSoap port ;
    
	public BajaComprobantesSUNAT(DataTask data, ApplicationContext appContext) {
		super(data, appContext);
		// TODO Auto-generated constructor stub
		
		try {
			new Thread().sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		}
		
		EntityManagerFactory emf = (EntityManagerFactory)appContext.getBean("entityManagerFactory");
		em=emf.createEntityManager();
		
		//trigger service
		trigger = appContext.getBean(TriggerTaskFactory.class);
		
		
		
		//obtenemos el nro documento inspeccion
		String nrodocumentoinspeccion = (String)data.data;
		
		//buscamos la inspeccion
		
		try{
			
			String hql1 = "SELECT ins FROM Inspeccion ins "
					+ " WHERE nrodocumentoinspeccion = :nrodocumentoinspeccion";
			Query query = em.createQuery(hql1);
			query.setParameter("nrodocumentoinspeccion", nrodocumentoinspeccion);
			
			inspeccion = (Inspeccion)query.getSingleResult();
			
			
			//parametro de wsdl
			 URL wsdlURL = WssCargaBajas.WSDL_LOCATION;
			 WssCargaBajas ss = new WssCargaBajas(wsdlURL, SERVICE_NAME);
		     port = ss.getWssCargaBajasSoap();  
		        
			
	        
	        
		}catch(NoResultException e)
        {
			trigger.launch("", "enviar.baja.sunat.error", "No existe inspeccion " + nrodocumentoinspeccion,  Util.printError(e));
	        e.printStackTrace();
			em.close();
	        
        }catch(Exception e)
        {
        		trigger.launch("", "enviar.baja.sunat.error", "Error inspeccion " + nrodocumentoinspeccion ,  Util.printError(e));
	        e.printStackTrace();
			em.close();
        
        }
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//enviar a digiflow
		
		if(inspeccion==null)
		{
			em.close();
			return;
		}

		
		try {
			
				Comprobante comprobante = inspeccion.getLastComprobante();
				
				if(comprobante.getComprobanteestado()==null 
						|| !comprobante.getComprobanteestado().getKey().equals("ANU"))
				{
					em.close();
					trigger.launch("", "enviar.baja.sunat.error",inspeccion.getNrodocumentoinspeccion() , "No esta anulado :" +  comprobante.getNrocomprobante());
					 return;
				}
				
				SimpleDateFormat dtf = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
				
				System.out.println("Invoking cargaBajas...");
		        
				
				cl.dbnet.bajas.Bajas baja = new cl.dbnet.bajas.Bajas();
				baja.setFechEmis(dtf.format(comprobante.getFechapago()));
				
				String[] parseComprobante = comprobante.getNrocomprobante().split("-");
				baja.setFoliInte(parseComprobante[1]);
				baja.setSerieInte(parseComprobante[0]);
				baja.setMotivAnul(inspeccion.getObservacionAnulado());
				baja.setTipoDocu(comprobante.getCliente().getTipodocumentoidentidad().getKey().equals("ruc")?"1":"3");
				
				System.out.println("MOTIVO ANULACION " + inspeccion.getObservacionAnulado());
				baja.setMotivAnul("Error de digitación u Omisión");
				
				String _cargaBajas_ruc = comprobante.getEmpresa().getRuc();
		        cl.dbnet.bajas.ArrayOfBajas _cargaBajas_archivoBajas = new ArrayOfBajas();
		        
		        _cargaBajas_archivoBajas.getBajas().add(baja);
		        
		        cl.dbnet.bajas.Mensaje _cargaBajas__return = port.cargaBajas(
		        		_cargaBajas_ruc, 
		        		_cargaBajas_archivoBajas);
		        
		        
		         
		         //grabando en el st_mensaje 
		         em.getTransaction().begin();
		         em.persist(new ST_Mensaje("BAJA",_cargaBajas__return, inspeccion.getNrodocumentoinspeccion(), comprobante.getNrocomprobante()));
		         em.getTransaction().commit();

		        
		}catch(Exception e)
		{
			trigger.launch("", "enviar.baja.sunat.error", inspeccion.getNrodocumentoinspeccion() ,  Util.printError(e));
			 e.printStackTrace();
		}finally {
			em.close();
		}


	}

	
}
