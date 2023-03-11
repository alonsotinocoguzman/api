package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface CierreRepository extends JpaRepository<Cierre,Long>{
	public Cierre findOneByKey(String key);
	public List<Cierre> findAll();

	
	@Query("select c from Cierre c where c.planta = :planta order by c.horaMax")
	public List<Cierre> findAllByPlantaOrderByHoraMax(@Param("planta") Planta planta);
	
	@Query("select c from Cierre c where c.estado = true order by c.planta, c.horaMax asc")
	List<Cierre> findAllOrderPlanta();
}
