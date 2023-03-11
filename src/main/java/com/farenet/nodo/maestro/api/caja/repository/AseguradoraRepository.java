package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Aseguradora;
import java.util.List;
import org.springframework.data.jpa.repository.*;

@Repository
public interface AseguradoraRepository extends JpaRepository<Aseguradora,Long>{
	public List<Aseguradora> findAll();
	public Aseguradora findOneByKey(String key);
}
