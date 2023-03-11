package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Pais;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface PaisRepository extends JpaRepository<Pais,Long>{
	public Pais findOneByKey(String key);
	
	@Query(value = "SELECT p FROM Pais p where p.nombre = :nombre" )
	public Pais findOneByNombre(@Param("nombre") String nombre);
	
}
