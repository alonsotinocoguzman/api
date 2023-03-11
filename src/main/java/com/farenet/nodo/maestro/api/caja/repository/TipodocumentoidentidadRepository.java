package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Tipodocumentoidentidad;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface TipodocumentoidentidadRepository extends JpaRepository<Tipodocumentoidentidad,Long>{
	public Tipodocumentoidentidad findOneByKey(String key);
}
