package com.farenet.nodo.maestro.api.linea.service;

import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;

import javax.management.relation.InvalidRelationServiceException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.farenet.nodo.maestro.api.linea.domain.*;
import com.farenet.nodo.maestro.api.linea.domain.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea.PESO_LINEA;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.repository.CategoriaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.linea.repository.LineaetapaRepository;
import com.farenet.nodo.maestro.api.linea.repository.MaquinaRepository;
import com.farenet.nodo.maestro.api.linea.repository.NormaRepository;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;

import javassist.NotFoundException;

@Service
@Transactional
public class AppLineaService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private LineaetapaRepository lineaetapaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private NormaRepository normaRepository;
	
	@Autowired
	private MaquinaRepository maquinaRepository;
	
	@Autowired 
	private InspeccionService inspeccionService;
	
	@Autowired
	private MaestroService maestroService;
	
	@Autowired
    private TriggerTaskFactory trigger;
	
	
	public Map<String, VehiculoMaquina>  getPlacas(String linea, String planta, String pc) throws Exception{
		Map<String, VehiculoMaquina> result = new HashMap<String, VehiculoMaquina>();
		List<VehiculoMaquina> lstVhMq = getlstVehiculomaquina(linea, planta);
		
		LineaEtapa lineaEtapa = lineaetapaRepository.findOneByPlantaandPc(planta, pc);
		List<MaquinaCategoria> lstMaquinaCategoria = getMaquinaCategoria(lineaEtapa);

		if (lineaEtapa == null) {
			throw new NotFoundException("No existe la pc");
		}
		
		
		for (VehiculoMaquina bean : lstVhMq) {

			VehiculoMaquina vehiculoMaquina = new VehiculoMaquina();
			// 5 y 3, diesel y petroleo respectivamente
			for (MaquinaCategoria maquina : lstMaquinaCategoria) {
				boolean isPresentCategoria = false;
				for (Categoria categoria : maquina.categorias) {
					
					if(bean.soloFotos!=null 
							&& bean.soloFotos)
					{
						if(!maquina.isFoto)
						{
							isPresentCategoria = false;
							break;
						}
					}
					
					if (bean.categoriaKey.equals(categoria.getKey())) {
						if (maquina.tipoMaquina.equals("4")) {
							if (bean.combustibleKey.equals("34") || bean.combustibleKey.equals("10") || 
									bean.combustibleKey.equals("22") || bean.combustibleKey.equals("23")) 
								break;

							if (!bean.combustibleKey.equals("5")
									&& !bean.combustibleKey.equals("3")
									&& !bean.combustibleKey.equals("8")
									&& !bean.combustibleKey.equals("16")
									&& !bean.combustibleKey.equals("19")
									&& !bean.combustibleKey.equals("20")) {
								isPresentCategoria = true;
								break;
							}
						} else if (maquina.tipoMaquina.equals("5")) {

							if (bean.combustibleKey.equals("34") || bean.combustibleKey.equals("10") || bean.combustibleKey.equals("22") 
									|| bean.combustibleKey.equals("23"))
								break;
							if (bean.combustibleKey.equals("5") || bean.combustibleKey.equals("3") || bean.combustibleKey.equals("8") 
									|| bean.combustibleKey.equals("16") || bean.combustibleKey.equals("19") || bean.combustibleKey.equals("20")) {
								isPresentCategoria = true;
								break;
							}
						} else if (maquina.tipoMaquina.equals("2")) {
							if (bean.pesobruto <= 3500 && !bean.esMoto() && !bean.esMotoCarro()) {
								isPresentCategoria = true;
								break;
							}
						
						//si es carreta no deberia mostrar luces
						}else if (maquina.tipoMaquina.equals("7")) {

							if(!bean.esCarreta())
							{
								isPresentCategoria = true;
								break;
							}
							
						//si es carreta, no deberia mostrar sonometro
						}else if (maquina.tipoMaquina.equals("6")) {

							if(!bean.esCarreta())
							{
								isPresentCategoria = true;
								break;
							}
							
						//si es moto o motocarro no mostrar alineamiento
						}else if (maquina.tipoMaquina.equals("1")) {

							if(!bean.esMoto() && !bean.esMotoCarro())
							{
								isPresentCategoria = true;
								break;
							}
						}else {
							isPresentCategoria = true;
							break;
						}

					}
				}

				if (!isPresentCategoria)
					continue;

				boolean isPresent = false;
				for (String resultado : bean.idMaquinas) {
					if (maquina.idMaquina.equals(resultado)) {
						isPresent = true;
						break;
					}
				}

				if (!isPresent) {
					vehiculoMaquina.idMaquinas.add(maquina.idMaquina);

				}

			}
			

			Inspeccion reinspeccion = inspeccionService.getReinspeccionByInspeccion(bean.nroinspeccion);
			boolean removeTestLine = false;
			if(reinspeccion != null && reinspeccion.getNrodocumentoreinspeccion() != null) {
				removeTestLine = UIUtils.validaSiRemueveTestLine(reinspeccion);
				for(ResultadoMaquina resultadoMaquina : reinspeccion.getResultadosMaquina()) {
					if(resultadoMaquina.getResultado().equals("A") && !resultadoMaquina.getMaquina().getTipomaquina().getIsFoto()) {
						for (MaquinaCategoria maquina : lstMaquinaCategoria) {
							if(resultadoMaquina.getMaquina().getTipomaquina().getKey().equals(maquina.tipoMaquina) ) {
								vehiculoMaquina.idMaquinas.remove(maquina.idMaquina);
							}
						}
					}
				}
			}

			if (vehiculoMaquina.idMaquinas.size() > 0) {
				vehiculoMaquina.placa = bean.placa;
				vehiculoMaquina.combustible = bean.combustible;
				vehiculoMaquina.anio = bean.anio;
				vehiculoMaquina.categoria = bean.categoria;
				vehiculoMaquina.linea = bean.linea;
				vehiculoMaquina.nroEjes = bean.nroEjes;
				vehiculoMaquina.marca = bean.marca;
				vehiculoMaquina.conceptoinspeccion = bean.conceptoinspeccion;
				vehiculoMaquina.pesobruto=bean.pesobruto;
				vehiculoMaquina.pesoseco=bean.pesoseco;
				result.put(bean.nroinspeccion, vehiculoMaquina);
			}

		}
		
		
		
		
		
		
		return result;
	}
	
	public boolean isTestLine(ResultadoMaquina resultadoMaquina) {
        boolean isTestLine = false;
        for (String tipomaquinaId : UIUtils.tipoMaquinasTestLine()) {
            if (resultadoMaquina.getMaquina().getTipomaquina().getKey().equals(tipomaquinaId)) {
                isTestLine = true;
            }
        }
        return isTestLine;
    }

	public VehiculoResultadoBean getVehiculomaquina(String nroinspeccion){
		
		VehiculoResultadoBean vehiculoMaquina = new VehiculoResultadoBean();
		
		String hql = "SELECT new "+VehiculoResultadoBean.class.getName()
				+ " (com.placaMotor,veh.combustible, veh.aniofabricacion, veh.categoria, veh.nroejes)"
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join ins.vehiculo veh"
				+ " WHERE ins.nrodocumentoinspeccion = :nroinspeccion";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nroinspeccion", nroinspeccion);
		vehiculoMaquina = (VehiculoResultadoBean) query.getSingleResult();
		return vehiculoMaquina;
	}
	
	public List<VehiculoMaquina> getlstVehiculomaquina(String linea, String planta){
		
		List<VehiculoMaquina> lstVehMaquinas = new ArrayList<>();
		
		String hql = "SELECT new "+VehiculoMaquina.class.getName()
				+ " (ins.nrodocumentoinspeccion, com.placaMotor, mar.nombre, cbs.nombre, veh.aniofabricacion,"
				+ " cat.nombre, veh.nroejes, li.nombre, ci.abreviatura, ci.soloFotos, cat.key, cbs.key, veh.pesobruto,veh.pesoseco)"
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join ins.vehiculo veh"
				+ " inner join veh.combustible cbs"
				+ " inner join veh.marca mar"
				+ " inner join veh.categoria cat"
				+ " inner join com.linea li"
				+ " inner join com.conceptoInspeccion ci"
				+ " inner join ins.inspeccionestado ie"
				+ " WHERE ie.key = 'PROCESO' and"
				+ " ins.posicion <> :consolidado and"
				+ " ins.posicion <> :caja and"
				+ " ins.posicion <> :pago and"
				+ " ins.posicion <> :vehiculo and"
				+ " ins.posicion <> :cliente and"
				+ " ins.nrodocumentoinspeccion like :planta and"
				+ " li.key = :linea";
		Query query = entityManager.createQuery(hql);
		query.setParameter("consolidado", POSICION.CONSOLIDADO);
		query.setParameter("caja", POSICION.CAJA);
		query.setParameter("pago", POSICION.PAGO);
		query.setParameter("vehiculo", POSICION.VEHICULO);
		query.setParameter("cliente", POSICION.CLIENTE);
		query.setParameter("planta", "INS-"+planta+"-%");
		query.setParameter("linea", linea);
		lstVehMaquinas = query.getResultList();
		
		for(VehiculoMaquina vehiculoMaquina : lstVehMaquinas) {
			vehiculoMaquina.idMaquinas = getIdMaquinaByInspeccion(vehiculoMaquina.nroinspeccion);
		}
		return lstVehMaquinas;
	}
	public String updateOservacionManual(ObservacionManualBean bean) {
		String hql ="Update inspeccion set observacion='"+bean.getObs()+"' where nrodocumentoinspeccion='"+bean.getNrodocumentoinspeccion()+"'";
		Query query = entityManager.createNativeQuery(hql);
		query.executeUpdate();
		return "ok";
	}
	public List<String> getIdMaquinaByInspeccion(String nrodocumentoinspeccion){
		List<Long> idMaquinas = new ArrayList<>();
		List<String> result = new ArrayList<>();
		String hql = "SELECT maq.id from Inspeccion ins"
				+ " inner join ins.resultadosMaquina rtm"
				+ " inner join rtm.maquina maq"
				+ " WHERE ins.nrodocumentoinspeccion = :nrodocumentoinspeccion";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nrodocumentoinspeccion", nrodocumentoinspeccion);
		idMaquinas =  query.getResultList();
		for(Long bean : idMaquinas) {
			result.add(bean.toString());
		}
		return result;
	}
	
	public List<MaquinaCategoria> getMaquinaCategoria(LineaEtapa lineaEtapa) {
		List<Categoria> lstCategorias = categoriaRepository.findAll();
		List<MaquinaCategoria> lstMaquinaCategoria = new ArrayList<MaquinaCategoria>();
		
		for(Maquina m : lineaEtapa.getMaquinas())
		{
			MaquinaCategoria maquinaCategoria = new MaquinaCategoria();
			maquinaCategoria.idMaquina = m.getId().toString();
			maquinaCategoria.descripcionMaquina = m.getTipomaquina().getDescripcion();
			maquinaCategoria.tipoMaquina = m.getTipomaquina().getKey();
			maquinaCategoria.parametrosMaquina = m.getParametros();
			maquinaCategoria.modeloMaquina = m.getModelo();
			maquinaCategoria.isFoto = false;
			
			if(m.getTipomaquina().getKey().equals("9"))//es inspeccion visual
			{
				maquinaCategoria.categorias = lstCategorias;
			}else if(m.getTipomaquina().getIsFoto())
			{
				maquinaCategoria.isFoto = true;
				maquinaCategoria.categorias = lstCategorias;
			}else{
				List<Norma> lstNormas = normaRepository.findAllByTipoMaquina(m.getTipomaquina().getKey());
				
				for(Norma norma : lstNormas)
				{
					for(Subnorma subnorma : norma.getSubnormas())
					{
						if(subnorma.getCondicionAnio().equals(">=") && subnorma.getAnio()==2015)
						{
							if(maquinaCategoria.categorias!=null)
							{
								
								List<Categoria> lstAux = new ArrayList<Categoria>();
								
								for(Categoria catSubNorma : subnorma.getCategorias())
								{
									boolean isPresent = false;
									for(Categoria categoria : maquinaCategoria.categorias)
									{
										if(categoria.getKey().equals(catSubNorma.getKey()))
										{
											isPresent = true;
											break;
										}
									}
									
									if(!isPresent)
										lstAux.add(catSubNorma);
								}
								
								maquinaCategoria.categorias.addAll(lstAux);
								
								
							}else{
								maquinaCategoria.categorias = subnorma.getCategorias();
								break;
							}
							
						}
					}
				}
			}
			
			
			
			if(maquinaCategoria.categorias!=null && maquinaCategoria.categorias.size()>0)
			{
				lstMaquinaCategoria.add(maquinaCategoria);
			}
			
		}
		
		
		return lstMaquinaCategoria;
	}
	
	private void cargarResultado(Inspeccion inspeccion, ResultadoMaquina resultado) {
		
		if(inspeccion.getResultadosMaquina()!=null && inspeccion.getResultadosMaquina().size()>0) {
			
			boolean found = false;
			for(ResultadoMaquina resultadoMaquina: inspeccion.getResultadosMaquina()) {
				if(resultadoMaquina.getMaquina().getId().equals(resultado.getMaquina().getId())) {
					found = true;
					inspeccion.getResultadosMaquina().set(inspeccion.getResultadosMaquina().indexOf(resultadoMaquina), resultado);
					break;
				}
			}
			
			if(!found) {
				resultado.setInspeccion(inspeccion);
				inspeccion.getResultadosMaquina().add(resultado);
			}
				
		}else{
			if(inspeccion.getResultadosMaquina() == null) {
				List<ResultadoMaquina> lst = new ArrayList<>();
				resultado.setInspeccion(inspeccion);
				lst.add(resultado);
				inspeccion.setResultadosMaquina(lst);
			}else {
				resultado.setInspeccion(inspeccion);
				inspeccion.getResultadosMaquina().add(resultado);
			}
		}
	}
	
	public void guardarResultado(ResultadoLineaBean lineaBean) throws Exception {
		Inspeccion inspeccion = inspeccionService.get(lineaBean.nroInspeccion);
		cargarResultado(inspeccion, lineaBean.resultadoMaquina);
		inspeccion.setPosicion(lineaBean.posicion);
		inspeccionService.guardar(inspeccion);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					trigger.launch( Util.getUserNameContext(), "recalculo.ini",  inspeccion.getNrodocumentoinspeccion(), "");
					recalcularInspeccion(inspeccion);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					trigger.launch( Util.getUserNameContext(), "error.recalculo",  inspeccion.getNrodocumentoinspeccion(), UIUtils.printError(e));
				}
			}
		}).start();
	}
	
	private void recalcularInspeccion(Inspeccion inspeccion) throws Exception {
		//validamos la etapa
		boolean validaEtapa =  (inspeccion.getLastComprobante().getConceptoInspeccion().getSoloFotos()!=null &&
				inspeccion.getLastComprobante().getConceptoInspeccion().getSoloFotos())
				?validarEtapaSoloFotos(inspeccion)
				:validarEtapa(inspeccion);
						
		if(validaEtapa) {
			inspeccion.setPosicion(POSICION.CONSOLIDACION);
			inspeccion.setResultado("A");
			for(ResultadoMaquina rm : inspeccion.getResultadosMaquina()) {
				if(rm.getResultado().equals("D")) {
					inspeccion.setResultado("D");
					break;
				}
			}
			
		}
		inspeccionService.guardarProceso(inspeccion);
	}
	
	public boolean validarEtapaSoloFotos(Inspeccion inspeccion) {
		//FOTOS GASES
		boolean isPresent = false;
		for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
		{
			if(resultado.getMaquina().getTipomaquina().getKey().equals("11"))
			{
				isPresent = true;
			}
		}
		
		if(!isPresent)
			return false;
		
		//FOTOS LUCES
		isPresent = false;
		for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
		{
			if(resultado.getMaquina().getTipomaquina().getKey().equals("12"))
			{
				isPresent = true;
			}
		}
		
		if(!isPresent)
			return false;
		
		if(inspeccion.getLastComprobante().getLinea().getTipo() == PESO_LINEA.MIXTA)
		{
			//FOTOS FRENOS
			isPresent = false;
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				if(resultado.getMaquina().getTipomaquina().getKey().equals("15"))
				{
					isPresent = true;
				}
			}
			
			if(!isPresent)
				return false;
		
		}else{
			//FOTOS TESTLINE
			isPresent = false;
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				if(resultado.getMaquina().getTipomaquina().getKey().equals("13"))
				{
					isPresent = true;
				}
			}
			
			if(!isPresent)
				return false;
		}
	
	
	
		return true;
	}
	
	//VERIFICANDO EN QUE ETAPA SE ENCUENTRA (Y ASIGNARLE UN RESULTADO)
	//TAMBIEN PASAR A VERIFICADO SI TODO ESTA OK
	public boolean validarEtapa(Inspeccion inspeccion){
		
		Vehiculo vehiculo = inspeccion.getVehiculo();
				
				
		
			//GASES
			if(vehiculo.getCombustible().getKey().equals("5") ||
					vehiculo.getCombustible().getKey().equals("3"))
			{
				List<Norma> lstNormas = maestroService.findNormaByTipoMaquina("5");
				if( checkNorma(vehiculo, lstNormas))
				{
					boolean isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("5"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
				}
				
				
			}else if(!vehiculo.getCombustible().getKey().equals("34")  && !vehiculo.getCombustible().getKey().equals("10"))
			{
				List<Norma> lstNormas = maestroService.findNormaByTipoMaquina("4");
				if( checkNorma(vehiculo, lstNormas))
				{
					boolean isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("4"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
				}
				
			}
			
			//si es carreta, no hace luces ni sonometro
			if(!vehiculo.getCategoria().esCarreta())
			{
				//LUCES
				List<Norma> lstNormas = maestroService.findNormaByTipoMaquina("7");
				if( checkNorma(vehiculo, lstNormas))
				{
					boolean isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("7"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
				}
				
				//sonometro
				lstNormas = maestroService.findNormaByTipoMaquina("6");
				if( checkNorma(vehiculo, lstNormas))
				{
					boolean isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("6"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
				}
			}
			
			
			//profundimetro
			List<Norma> lstNormas = maestroService.findNormaByTipoMaquina("10");
			if( checkNorma(vehiculo, lstNormas))
			{
				boolean isPresent = false;
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					if(resultado.getMaquina().getTipomaquina().getKey().equals("10"))
					{
						isPresent = true;
					}
				}
				
				if(!isPresent)
					return false;
			}
			
			
			//frenos
			lstNormas = maestroService.findNormaByTipoMaquina("3");
			if( checkNorma(vehiculo, lstNormas))
			{
				boolean isPresent = false;
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					if(resultado.getMaquina().getTipomaquina().getKey().equals("3"))
					{
						isPresent = true;
					}
				}
				
				if(!isPresent)
					return false;
			}
			
			if( !vehiculo.getCategoria().esMoto() && !vehiculo.getCategoria().esMotoCarro())
			{
				//alineacion
				lstNormas = maestroService.findNormaByTipoMaquina("1");
				if( checkNorma(vehiculo, lstNormas))
				{
					boolean isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("1"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
				}
			}
			
			
			
			//INSPECCION VISUAL
			boolean isPresent = false;
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				if(resultado.getMaquina().getTipomaquina().getKey().equals("9"))
				{
					isPresent = true;
				}
			}
			
			if(!isPresent)
				return false;
			
			
			//FOTOS GASES
			isPresent = false;
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				if(resultado.getMaquina().getTipomaquina().getKey().equals("11"))
				{
					isPresent = true;
				}
			}
			
			if(!isPresent)
				return false;
			
			//FOTOS LUCES
			isPresent = false;
			for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
			{
				if(resultado.getMaquina().getTipomaquina().getKey().equals("12"))
				{
					isPresent = true;
				}
			}
			
			if(!isPresent)
				return false;
				
			//suspension
			if(vehiculo.getPesobruto() <= 3500 && !vehiculo.getCategoria().esMoto() && !vehiculo.getCategoria().esMotoCarro())
			{
				lstNormas = maestroService.findNormaByTipoMaquina("2");
				if( checkNorma(vehiculo, lstNormas))
				{
					isPresent = false;
					for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
					{
						if(resultado.getMaquina().getTipomaquina().getKey().equals("2"))
						{
							isPresent = true;
						}
					}
					
					if(!isPresent)
						return false;
						
				}
			
				
			}
			
			if(inspeccion.getLastComprobante().getLinea().getTipo() == PESO_LINEA.MIXTA)
			{
				//FOTOS FRENOS
				isPresent = false;
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					if(resultado.getMaquina().getTipomaquina().getKey().equals("15"))
					{
						isPresent = true;
					}
				}
				
				if(!isPresent)
					return false;
			
			}else{
				//FOTOS TESTLINE
				isPresent = false;
				for(ResultadoMaquina resultado : inspeccion.getResultadosMaquina())
				{
					if(resultado.getMaquina().getTipomaquina().getKey().equals("13"))
					{
						isPresent = true;
					}
				}
				
				if(!isPresent)
					return false;
			}
		
		
		
		return true;
	}
	
	private Boolean checkNorma(Vehiculo vehiculo, List<Norma> lstNormas) {
		
		for(Norma norma : lstNormas) {
			for(Subnorma subnorma : norma.getSubnormas()) {
				if(subnorma.getCondicionAnio().equals(">=") && subnorma.getAnio()==2015) {
					for(Categoria categoria :  subnorma.getCategorias()) {
						if(vehiculo.getCategoria().getKey().equals(categoria.getKey())) {
							return true;
							
						}
					}
					
				}
			}
		}
		
		return false;
	}

	public List<VersionControl> getVersionControl(){
		List<VersionControl> results = new ArrayList();

		String hql= "select nomversion,cambios,numero,ruta,idversion FROM versionapp WHERE estado=1 ";
		Query query= entityManager.createNativeQuery(hql.toString());
		List<Object> result=(List<Object>) query.getResultList();
		Iterator<Object> itr= result.iterator();
		while(itr.hasNext()){
			Object[] obj = (Object[]) itr.next();
			VersionControl bean= new VersionControl();
			bean.setNomversion(obj[0].toString().trim());
			bean.setCambios(obj[1].toString().trim());
			bean.setNumero(obj[2].toString().trim());
			bean.setRuta(obj[3].toString().trim());
			bean.setIdversion(Integer.valueOf(obj[4]!=null?obj[4].toString():"0"));
			results.add(bean);
		}
		return results;
	}
	public List<TiempoTranscurrido> getTiempoTranscurrido( String nroinspeccion){
		List<TiempoTranscurrido> results = new ArrayList();

		String hql ="select current_timestamp AT TIME ZONE 'America/Bogota' as tiempo, " +
				" fechcreacion," +
				" extract( hour from current_timestamp AT TIME ZONE 'America/Bogota'-fechcreacion) as hora ," +
				" extract( day from current_timestamp AT TIME ZONE 'America/Bogota'-fechcreacion) as dias" +
				" from inspeccion" +
				" where nrodocumentoinspeccion='"+nroinspeccion+"'";
		System.out.println(hql.toString());
		Query query= entityManager.createNativeQuery(hql.toString());
		List<Object> result=(List<Object>) query.getResultList();
		Iterator<Object> itr= result.iterator();
		while(itr.hasNext()){
			Object[] obj = (Object[]) itr.next();
			TiempoTranscurrido bean = new TiempoTranscurrido();
			bean.setTiemposerver(obj[0].toString().trim());
			bean.setFechacreacion(obj[1].toString().trim());
			bean.setHoratranscurrida(Double.valueOf(obj[2]!=null?obj[2].toString():"0.0"));
			bean.setDiastranscurridos(Double.valueOf(obj[3]!=null?obj[3].toString():"0.0"));
			results.add(bean);
		}
		return results;
	}

}
