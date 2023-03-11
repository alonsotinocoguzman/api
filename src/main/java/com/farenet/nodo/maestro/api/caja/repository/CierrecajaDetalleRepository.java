package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDetalle;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface CierrecajaDetalleRepository extends JpaRepository<CierrecajaDetalle, Long> {
	@Query(value = "SELECT cjd FROM CierrecajaDetalle cjd where cjd.linea = :linea "
			+ "and cjd.fechcreacion >= :initDay and cjd.fechcreacion <= :finDay " + "order by cjd.fechcreacion desc")
	public List<CierrecajaDetalle> findLastByLineaToday(@Param("linea") Linea linea, @Param("initDay") Timestamp initDay,
			@Param("finDay") Timestamp finDay);
}
