package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoDetalle;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface DescuentoDetalleRepository extends JpaRepository<DescuentoDetalle, Long> {
	
	DescuentoDetalle getOneById(Long id);
	
	@Query(value = "SELECT td.key"
			+ " FROM DescuentoDetalle dd"
			+ " inner join dd.descuento de"
			+ " left join de.tipodescuento td"
			+ " where dd.id = :id")
	String getTipoDescuentoById(@Param("id") Long id);
   
}
