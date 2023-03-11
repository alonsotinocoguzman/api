package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.linea.domain.Maquina;
import com.farenet.nodo.maestro.api.linea.domain.Tipomaquina;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
	
	@Query("SELECT m FROM Maquina m "
			+ "join m.linea l "
			+ "WHERE l.planta = :planta")
	public List<Maquina> findByPlanta(@Param("planta") Planta planta);
	
	@Query("SELECT m FROM Maquina m "
			+ "WHERE m.linea = :linea")
	public List<Maquina> findByLinea(@Param("linea") Linea linea);
	
	public Maquina findOneById(Long id);
	

	public List<Maquina> findByTipomaquina(Tipomaquina tipoMaquina);
	

	public Maquina findOneByLineaAndTipomaquina(Linea linea, Tipomaquina tipoMaquina);
	
	@Query("select mq FROM LineaEtapa le"
			+ " inner join le.maquinas mq"
			+ " inner join le.etapa et"
			+ " inner join le.linea li"
			+ " WHERE li.key = :linea")
	public List<Maquina> findMaquinaByLinea(@Param("linea") String linea);
}
