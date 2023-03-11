package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.linea.domain.Norma;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface NormaRepository extends JpaRepository<Norma, Long> {
	
	@Query(value = "SELECT nor FROM Norma as nor "
			+ " WHERE nor.estado = :estado ")
	public List<Norma>
	findEstado(@Param("estado") boolean estado);
	
	public Norma findOneById(Long id);
	
	@Query("SELECT nor FROM Norma nor "
			+ "WHERE nor.tipomaquina.key = :key")
	public List<Norma> findAllByTipoMaquina(@Param("key") String key);
}
