package com.farenet.nodo.maestro.api.task.cron;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaRepository;
import com.farenet.nodo.maestro.api.caja.service.CierreService;
import com.farenet.nodo.maestro.api.caja.service.CierrecajaService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.inspeccion.service.PlantaService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CierreCajaTask{
    @Autowired
    private InspeccionService inspeccionService;

	@Autowired
    private CierrecajaService cierrecajaService;

	@Autowired
	private CierrecajaRepository cierrecajaRepository;

	@Autowired
	private TaskScheduler taskExecutor;
	
	@Autowired
	private PlantaService plantaService;
	
    @Autowired
    private CierreService cierreService;
    
    @Autowired
    private TriggerTaskFactory trigger;

    @Async
    @Scheduled(fixedDelay=300000)
    public void execute() {
    	runCierre();
	}

	private void runCierre() {
		List<Planta> lstPlantas = plantaService.getAllPlantas();
				
				Map<Planta,List<Cierre>> mapPlantaCierre = new HashMap<>();
				
				List<Cierre> listCierre = cierreService.findAllOrderPlanta();
				
				for(Planta planta :lstPlantas)
				{
					List<Cierre> listCierrePlanta = listCierre.stream().filter(p -> p.getPlanta().getKey().equals(planta.getKey()))
							.collect(Collectors.toList());
					
					//ordenamos
					List<Cierre> list = listCierrePlanta.stream().
			    			sorted(Comparator.comparing(Cierre::getHoraMax))
			    			.collect(Collectors.toList());
					
					mapPlantaCierre.put(planta, list);
				}
		       	
				for(Map.Entry<Planta , List<Cierre>> entry: mapPlantaCierre.entrySet())
				{
					final List<Cierre> lstCierre = entry.getValue();
					
					for(Cierre cierre : lstCierre)
					{
			    			if(cierre.getCronHora()!=null ){
			    				try{
			    					//validar cierre de cajas pasados
			    					       
			    					aplicarCierre(lstCierre, cierre);
			    						
			    					
			    				} catch (Exception e) {
			    					// TODO Auto-generated catch block
			    					e.printStackTrace();
			    					trigger.launch("", "nodo.sede.cierrecaja.task.error", UIUtils.printError(e), null);
							        
			    				}
			    			}
					}
		    		
		    		}
	}

	
	public void executeTask(Map<Planta,List<Cierre>> mapa)
	{
		
		for(Map.Entry<Planta , List<Cierre>> entry: mapa.entrySet())
		{
			final List<Cierre> listCierre = entry.getValue();
			
			for(Cierre cierre : listCierre)
			{
				if(cierre.getCronHora()!=null)
				taskExecutor.schedule(
		                new Runnable() {
		                    @Override public void run() {

			                      try {
			                    	  
			//                    	  aplicarCierre(listCierre, cierre);
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									trigger.launch("", "nodo.sede.cierrecaja.task.error", UIUtils.printError(e), null);
							        

								}
		                    }
		                    
		                    

							
		                },
		                new CronTrigger(cierre.getCronHora())
		        );
			}
		}
		
	}
	
	

	
	private void aplicarCierre(List<Cierre> list, Cierre cierre) {
		Cierrecaja cc = cierrecajaService.getLastCierreCajaByPlanta(cierre.getPlanta().getKey());
		if(cc!=null){
			Calendar c = Calendar.getInstance();
			c.setTime(cc.getFechcreacion());
			Calendar ctoday = Calendar.getInstance();
			int days = UIUtils.daysBetween(c, ctoday);
			while(days > 0){
				for(Cierre cierreBack : list){
					String keyTest = cierreBack.getKey()
							+"_"
							+c.get(Calendar.DAY_OF_MONTH)
							+"_"
							+(c.get(Calendar.MONTH) + 1)
							+"_"
							+c.get(Calendar.YEAR);
		  
					Cierrecaja cierreAnterior = cierrecajaRepository.findOneById(keyTest);
		  
					if(cierreAnterior == null){
						String[] time = cierreBack.getHoraMax().split(":");
						Calendar cclone = (Calendar) c.clone();
						cclone.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time[0]));
		  
						try {
							createCierreCaja(cierreBack, cclone.getTime());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							StringWriter sw = new StringWriter(); 
					        PrintWriter pw = new PrintWriter(sw); 
					        e.printStackTrace(pw); 
					        
					        trigger.launch("", "nodo.sede.error.cierrecaja", UIUtils.printError(e), null);
					        
				            return; 
						}
					}
		  
				}
		  
				//aumentamos el siguiente dia y calculamos la diferencia de dias
				c.add(Calendar.DAY_OF_YEAR, 1);
				days = days - 1;
			}
		  
			//validamos de la fecha en curso
			c.setTime(cc.getFechcreacion());
		  
			
			//corremos los cierres del dia  (si es que se ha pasado alguna hora de cierre)
			if(UIUtils.daysBetween(c, ctoday) > 0){
				for(Cierre cierreBack : list){
					String[] time = cierreBack.getHoraMax().split(":");
					Calendar clocal = Calendar.getInstance();
		  
					clocal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time[0]));
					clocal.set(Calendar.MINUTE,0);
					clocal.set(Calendar.SECOND,0);
		  
					if(Calendar.getInstance().after(clocal)){
						clocal.set(Calendar.MINUTE, 1);
						try {
							createCierreCaja(cierreBack, clocal.getTime());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							StringWriter sw = new StringWriter(); 
					        PrintWriter pw = new PrintWriter(sw); 
					        e.printStackTrace(pw); 
					        
					        trigger.launch("", "nodo.sede.error.cierrecaja", UIUtils.printError(e), null);
					        
				            return; 
						}
					}
		  
				}
			}else{
				//verificamos que no falten cierres del dia en curso 
				for(Cierre cierreBack : list){
					String[] time = cierreBack.getHoraMax().split(":");
					Calendar clocal = Calendar.getInstance();
		  
					clocal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time[0]));
					clocal.set(Calendar.MINUTE,0);
					clocal.set(Calendar.SECOND,0);
					
					if(Calendar.getInstance().after(clocal)){
		  
						String keyTest = cierreBack.getKey()
								+"_"
								+c.get(Calendar.DAY_OF_MONTH)
								+"_"
								+(c.get(Calendar.MONTH) + 1)
								+"_"
								+c.get(Calendar.YEAR);
		  
						Cierrecaja cierreAnterior =cierrecajaRepository.findOneById(keyTest);
		  
						if(cierreAnterior == null){

							clocal.set(Calendar.MINUTE, 1);
							try {
								createCierreCaja(cierreBack, clocal.getTime());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								StringWriter sw = new StringWriter(); 
						        PrintWriter pw = new PrintWriter(sw); 
						        e.printStackTrace(pw); 
						        
						        trigger.launch("", "nodo.sede.error.cierrecaja", UIUtils.printError(e), null);
							       
					            return; 
							}
						}
					}
				}
			}
		}else{
			try {
				createCierreCaja(cierre, new Date());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				StringWriter sw = new StringWriter(); 
		        PrintWriter pw = new PrintWriter(sw); 
		        e.printStackTrace(pw); 
		        
		        trigger.launch("", "nodo.sede.error.cierrecaja", UIUtils.printError(e), null);
			       
	            return; 
			}
		}
	}
	
	private void createCierreCaja(Cierre cierre, Date date) throws Exception {
		Cierrecaja cierrecaja = new Cierrecaja();
		  
		  cierrecaja.setFechcreacion(new Timestamp(date.getTime()));
		  Calendar c = Calendar.getInstance();
		  c.setTime(cierrecaja.getFechcreacion());
		  
		  cierrecaja.setId(cierre.getKey()
				  +"_"
				  +c.get(Calendar.DAY_OF_MONTH)
				  +"_"
				  +(c.get(Calendar.MONTH) + 1)
				  +"_"
				   +c.get(Calendar.YEAR));
				  
		  cierrecaja.setCierre(cierre);
		  cierrecaja.setPlanta(cierre.getPlanta());
		   
		  Cierrecaja cierrecajaLocal = cierrecajaService.getByCierreByPlantakeyLocal(cierrecaja);
		  
		  cierrecajaService.grabarCierrrecajaYActualizarEstadocierreparcial(cierrecajaLocal);
		  
			//verificamos// Si es cierre final se retiran las inspecciones que estan
                // en estadoLocal incompleted.
                if (cierre.getCierrefinal()) {
                    Date fechIni =UIUtils.initDay(cierrecajaLocal.getFechcreacion()) ;
                    Date fechFin =UIUtils.finDay(cierrecajaLocal.getFechcreacion());
                    List<Inspeccion> inspeccionsARetirar = inspeccionService.getAllByFecha(fechIni, fechFin,cierre.getPlanta().getKey());
                    for (Inspeccion inspeccion : inspeccionsARetirar) {
                    	Inspeccion insProc = new Inspeccion();
                    	if(inspeccion.getPosicion().equals(POSICION.CAJA) || inspeccion.getPosicion().equals(POSICION.PAGO) ||
                    			inspeccion.getPosicion().equals(POSICION.VEHICULO) || inspeccion.getPosicion().equals(POSICION.CLIENTE) ||
                    			inspeccion.getLastComprobante().getLinea() == null){
                    		insProc = inspeccionService.anularInspeccion( inspeccion,
                    				"Cerrando el día");
                    	}else{
                    		insProc = inspeccionService.retirarInspeccion(cierre.getPlanta().getKey(),inspeccion,
                                    "Cerrando el día");
                    	}
                        inspeccionService.guardar(insProc);
                    }
                } 
		    
            
            
	}
}