package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface ConceptoinspeccionRepository extends JpaRepository<Conceptoinspeccion, Long> {
	public Conceptoinspeccion findOneByKey(String key);

	public Conceptoinspeccion findOneByAbreviatura(String abreviatura);
	public List<Conceptoinspeccion> findByEstado(boolean estado);
	
	@Query(value = "select ci from Conceptoinspeccion ci"
			+ " inner join ci.conceptoinspecciondetalles coid"
			+ " inner join coid.planta pl"
			+ " where ci.key = :key and pl.key = :planta")
	public Conceptoinspeccion findOneByKeyAndPlanta(@Param("key") String key,@Param("planta") String planta);
	
	
	@Query(value = "select coid,ci from Conceptoinspeccion ci"
			+ " inner join ci.conceptoinspecciondetalles coid"
			+ " inner join coid.planta pl"
			+ " where pl.key = :planta")
	public List<Conceptoinspeccion> findListByPlanta(@Param("planta") String planta);
	
	
	
}
