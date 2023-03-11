package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Moneda;

import org.springframework.data.jpa.repository.*;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda,Long>{
	
	public Moneda getOneByKey(String key);
	
}
