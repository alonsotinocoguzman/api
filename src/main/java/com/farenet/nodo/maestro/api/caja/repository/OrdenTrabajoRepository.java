package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.OrdenTrabajo;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
	
	
	
}
