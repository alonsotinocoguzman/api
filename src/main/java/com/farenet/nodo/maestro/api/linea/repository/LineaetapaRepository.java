package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.linea.domain.LineaEtapa;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface LineaetapaRepository extends JpaRepository<LineaEtapa, Long> {
	
	@Query("SELECT le FROM LineaEtapa le "
			+ "join le.linea l "
			+ "WHERE l.planta = :planta")
	public List<LineaEtapa> findByPlanta(@Param("planta") Planta planta);
	
	@Query("SELECT le FROM LineaEtapa le "
			+ "join le.linea l "
			+ "WHERE l.planta.key = :planta and le.pcCod = :pc")
	public LineaEtapa findOneByPlantaandPc(@Param("planta") String planta, @Param("pc") String pc);
}
