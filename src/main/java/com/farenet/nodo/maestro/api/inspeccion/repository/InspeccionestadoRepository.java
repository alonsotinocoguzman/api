package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface InspeccionestadoRepository extends JpaRepository<Inspeccionestado, Long> {
	public Inspeccionestado findOneByKey(String key);
	
	@Query(value = "SELECT i.inspeccionestado FROM Inspeccion i "
			+ "WHERE i.nrodocumentoinspeccion = :nroinspeccion")
	public Inspeccionestado findOneByNroInspeccion(@Param("nroinspeccion") String nroinspeccion);
	
}
