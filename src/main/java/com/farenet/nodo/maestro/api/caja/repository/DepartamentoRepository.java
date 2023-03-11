package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Departamento;
import com.farenet.nodo.maestro.api.caja.domain.Pais;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	public Departamento findOneByKey(String key);
	
	@Query(value = "SELECT d FROM Departamento d where d.nombre = :nombre" )
	public Departamento findOneByNombre(@Param("nombre") String nombre);
}
