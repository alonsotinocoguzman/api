package com.farenet.nodo.maestro.api.sunat.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Carroceria;
import com.farenet.nodo.maestro.api.linea.domain.Norma;
import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface ST_MensajeRepository extends JpaRepository<ST_Mensaje,Long>{
	

	@Query("SELECT mensaje FROM ST_Mensaje mensaje "
			+ "WHERE nrodocumentoinspeccion = :nrodocumentoinspeccion")
	public List<ST_Mensaje> findByInspeccion(@Param("nrodocumentoinspeccion") String nrodocumentoinspeccion);
	
	@Query("SELECT mensaje FROM ST_Mensaje mensaje "
			+ "WHERE nrocomprobante = :nrocomprobante")
	public ST_Mensaje findByNroComprobante(@Param("nrocomprobante") String nrocomprobante);
	
	@Query("SELECT mensaje FROM ST_Mensaje mensaje "
			+ "WHERE nrodocumentoinspeccion = :nrodocumentoinspeccion and tipo = :tipo" )
	public ST_Mensaje findByInspeccionAndTipo(@Param("nrodocumentoinspeccion") String nrodocumentoinspeccion, @Param("tipo") String tipo);
	
	
}
