package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Distrito;
import com.farenet.nodo.maestro.api.caja.domain.Provincia;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Long> {
	public Distrito findOneByKey(String key);
	
	@Query(value = "SELECT d FROM Distrito d where d.nombre = :nombre" )
	public Distrito findOneByNombre(@Param("nombre") String nombre);
}
