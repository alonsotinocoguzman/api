package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Carroceria;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface CarroceriaRepository extends JpaRepository<Carroceria,Long>{
	public Carroceria findOneByKey(String key);
}
