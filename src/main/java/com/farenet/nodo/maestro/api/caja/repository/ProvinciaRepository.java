package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Departamento;
import com.farenet.nodo.maestro.api.caja.domain.Provincia;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
	public Provincia findOneByKey(String key);
	
	@Query(value = "SELECT p FROM Provincia p where p.nombre = :nombre" )
	public Provincia findOneByNombre(@Param("nombre") String nombre);
}
