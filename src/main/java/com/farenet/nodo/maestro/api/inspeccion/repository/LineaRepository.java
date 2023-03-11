package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface LineaRepository extends JpaRepository<Linea,Long>{
	public List<Linea> findAll();
	
	public List<Linea> findByPlanta(Planta planta);
	
	public Linea findOneByKey(String key);
}
