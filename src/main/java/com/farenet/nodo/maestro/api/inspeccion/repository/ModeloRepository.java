package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Modelo;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long>{
	public Modelo findOneByKey(String key);
}
