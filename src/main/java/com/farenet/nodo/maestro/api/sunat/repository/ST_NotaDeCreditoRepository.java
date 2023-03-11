package com.farenet.nodo.maestro.api.sunat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.sunat.domain.ST_NotaDeCredito;

@Repository
public interface ST_NotaDeCreditoRepository extends JpaRepository<ST_NotaDeCredito, Long> {

	@Query("SELECT cc FROM ST_NotaDeCredito cc "
			+ "where serieRef = :serie and correlativoRef = :correlativo")
	public ST_NotaDeCredito findOneBySerieAndCorrelativo(@Param("serie") String serie, @Param("correlativo") Long correlativo);
	
}
