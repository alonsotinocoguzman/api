package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface PlantaRepository extends JpaRepository<Planta,Long>{
	public Planta findOneByKey(String key);
	
	@Query("select pl.nombre from Planta as pl")
	public List<String> findNombre();



	@Query("select pl.nombre from Planta as pl where pl.key = :planta")
	public String findNombreById(@Param("planta") String planta);

}
