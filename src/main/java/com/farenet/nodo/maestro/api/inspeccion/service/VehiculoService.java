package com.farenet.nodo.maestro.api.inspeccion.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.repository.PersonaRepository;
import com.farenet.nodo.maestro.api.caja.service.MaestroService;
import com.farenet.nodo.maestro.api.caja.service.PersonaService;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculoclase;
import com.farenet.nodo.maestro.api.inspeccion.repository.TarjetapropiedadRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.VehiculoRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class VehiculoService {

    private static final Logger logger = LoggerFactory.getLogger(VehiculoService.class);

    @Autowired
    private TarjetapropiedadRepository tarjetaPropiedadRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired 
    private MaestroService maestroService;

    public ResultPageBean<Vehiculo> getAll(PageBean pageBean) {

        ResultPageBean<Vehiculo> result = new ResultPageBean<Vehiculo>(
                vehiculoRepository.findAll(pageBean.convertToPage()));
        return result;
    }
    
    public ResultPageBean<Vehiculo> getVehiculosByPlaca(String field, PageBean pageBean) throws UnsupportedEncodingException {
    	Map<String, String> mapField = QueryUtil.splitQuery(field);
        String placa = mapField.get("field");
        
        ResultPageBean<Vehiculo> result = new ResultPageBean<Vehiculo>(
                vehiculoRepository.findAllByPlaca(placa,pageBean.convertToPage()));
    	return result;
    }

    public ResultPageBean<Vehiculo> get(String field, PageBean pageBean) throws UnsupportedEncodingException {
        Map<String, String> mapField = QueryUtil.splitQuery(field);
        String strField = mapField.get("field");
        ResultPageBean<Vehiculo> result = new ResultPageBean<Vehiculo>(vehiculoRepository
                .findByLikeNroplaca("%" + strField.toUpperCase() + "%", pageBean.convertToPage()));

        if (!result.getContent().isEmpty())
            return result;

        List<Tarjetapropiedad> lstSearch = tarjetaPropiedadRepository
                .searchPropietarioByNombre("%" + strField.toUpperCase() + "%");

        if (lstSearch.isEmpty()) {
            lstSearch = tarjetaPropiedadRepository
                    .searchPropietarioByDNI("%" + strField.toUpperCase() + "%");
        }

        if (!result.getContent().isEmpty())
            return result;

        result = new ResultPageBean<Vehiculo>(
                vehiculoRepository.findByNromotor(strField, pageBean.convertToPage()));

        if (!result.getContent().isEmpty())
            return result;

        return result;

    }
    
    public Persona getLastPropietario(String nromotor)
    {
    	Vehiculo vehiculo = vehiculoRepository.findByNromotor(nromotor);
    	
    	if(vehiculo==null)
    		return null;
    	
		if (vehiculo.getTarjetapropiedad() != null) {
			return vehiculo.getTarjetapropiedad().getPropietario();
		}
		
		return null;
    }
    
    public void sync(Vehiculo vehiculo) {
    	if(vehiculo.getModelo() != null) maestroService.saveModelo(vehiculo.getModelo());
    	if(vehiculo.getCarroceria() != null) maestroService.saveCarroceria(vehiculo.getCarroceria());
    	if(vehiculo.getMarca() != null) maestroService.saveMarca(vehiculo.getMarca());
    	if(vehiculo.getColor() != null) maestroService.saveColor(vehiculo.getColor());
    	if(vehiculo.getClasevehiculo() != null) maestroService.saveClase(vehiculo.getClasevehiculo());
    	if(vehiculo.getTarjetapropiedad() != null && vehiculo.getTarjetapropiedad().getPropietario() != null) personaRepository.saveAndFlush(vehiculo.getTarjetapropiedad().getPropietario());
    	if(vehiculo.getTarjetapropiedad() != null) {
    		vehiculo.getTarjetapropiedad().setId(null);
    		tarjetaPropiedadRepository.save(vehiculo.getTarjetapropiedad());
    	}
    	vehiculoRepository.save(vehiculo);
    }

    public void save(Vehiculo vehiculo) {
    	
    	if(vehiculo.getTarjetapropiedad() != null){
    		if(vehiculo.getTarjetapropiedad().getPropietario() != null) personaRepository.saveAndFlush(vehiculo.getTarjetapropiedad().getPropietario());
    		tarjetaPropiedadRepository.save(vehiculo.getTarjetapropiedad());
    	}
        vehiculoRepository.save(vehiculo);
    }
    
    public Vehiculo getOneByNroMotor(String nroMotor) {
    	return vehiculoRepository.findByNromotor(nroMotor);
    }

    public void deleteByPlaca(String nroplaca) throws Exception {
    	Vehiculo vehiculo = vehiculoRepository.findByNroPlaca(nroplaca);
		vehiculoRepository.delete(vehiculo);
	}
}
