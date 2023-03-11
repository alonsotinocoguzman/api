package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Marca;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long>{
	public Marca findOneByKey(String key);
}
