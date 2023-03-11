package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Modelo;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculoclase;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface VehiculoclaseRepository extends JpaRepository<Vehiculoclase,Long>{
//	public List<Vehiculoclase> findAllByEstado(boolean estado);
	public Vehiculoclase findOneByKey(String key);
}
