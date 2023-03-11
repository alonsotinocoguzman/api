package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoOT;

@Repository
public interface SeriedocumentoOTRepository extends JpaRepository<SeriedocumentoOT, Long>{
	
	@Query(value = "select sd from SeriedocumentoOT sd"
			+ " inner join sd.empresa em"
			+ " where em.key = :empresa")
	public SeriedocumentoOT findOneByEmpresa(@Param("empresa") String empresa);
	
}
