package com.farenet.nodo.maestro.api.caja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoCliente;

@Repository
public interface DescuentoClienteRepository extends JpaRepository<DescuentoCliente, Long> {
	
	@Query(value = "select count(dc.inspecciones) from DescuentoCliente dc "
			+ "where dc.id = :id")
	Long getCountAllByPlaca(@Param("id") Long id);
	
	@Query(value = "select dc from DescuentoDetalle dd "
			+ "inner join dd.descuentoclientes dc "
			+ "where dd.id = :iddetalle")
	List<DescuentoCliente> getAllByDescuentoDetalle(@Param("iddetalle") Long iddetalle);
	
}
