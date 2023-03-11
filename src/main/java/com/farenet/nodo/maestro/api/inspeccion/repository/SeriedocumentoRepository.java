package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Seriedocumento;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface SeriedocumentoRepository extends JpaRepository<Seriedocumento,Long>{
	public Seriedocumento findOneById(Long id);
	
	@Query(value = "select sd from Seriedocumento sd"
			+ " inner join sd.planta pl"
			+ " where pl.key = :planta")
	public List<Seriedocumento> findAllByPlanta(@Param("planta") String planta);
	
	@Query(value = "select sd from Seriedocumento sd"
			+ " inner join sd.planta pl"
			+ " inner join sd.linea li"
			+ " where pl.key = :planta and li.key = :linea")
	Seriedocumento findOneByPlantaAndLinea(@Param("planta") String planta, @Param("linea") String linea);
}
