package com.farenet.nodo.maestro.api.sunat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.sunat.domain.ST_Comprobantes;

@Repository
public interface ST_ComprobantesRepository extends JpaRepository<ST_Comprobantes, Long> {
	

	@Query("SELECT DISTINCT serie FROM ST_Comprobantes")
	public List<String> findAllSeries();
	
	@Query("SELECT cc FROM ST_Comprobantes cc "
			+ "where serie = :serie and correlativo = :correlativo")
	public ST_Comprobantes findOneBySerieAndCorrelativo(@Param("serie") String serie, @Param("correlativo") Long correlativo);
	
	
}
