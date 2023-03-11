package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.TipoPoliza;

@Repository
public interface TipoPolizaRepository extends JpaRepository<TipoPoliza, Long>{
	public TipoPoliza getOneByKey(String key);
}
