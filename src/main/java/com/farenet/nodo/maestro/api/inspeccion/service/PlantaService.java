package com.farenet.nodo.maestro.api.inspeccion.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.repository.PersonaRepository;
import com.farenet.nodo.maestro.api.event.domain.Log;
import com.farenet.nodo.maestro.api.event.repository.LogRepository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.PlantaConexion;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.TarjetapropiedadRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.VehiculoRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class PlantaService {

	@Autowired
	private PlantaRepository plantaRepository;

	@Autowired
	private LogRepository eventRepository;

	public List<Planta> getAllPlantas()
	{
		return plantaRepository.findAll();
	}
	
	
	public List<PlantaConexion> getAllPlantasActivos() {

		List<PlantaConexion> lstPlantaConexion = new ArrayList<PlantaConexion>();
		
		List<Planta> lstPlanta = plantaRepository.findAll();
		
		for(Planta planta : lstPlanta)
		{
			PlantaConexion conexion = new PlantaConexion();
			conexion.planta = planta.getNombre();
			conexion.key = planta.getKey();
			
			/*Log log = eventRepository.getListLogLastest("sede.activity", planta.getKey());
			if(log!=null)
			{
				conexion.usuario = log.data.toString();
				conexion.fechaUltimaConexion = log.date;
			}*/
			
			lstPlantaConexion.add(conexion);
		}
		
		return lstPlantaConexion;
	}
	
	public Planta getOneByKey(String key) {
		return plantaRepository.findOneByKey(key);
	}

}
