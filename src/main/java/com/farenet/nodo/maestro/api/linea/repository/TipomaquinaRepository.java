package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Tipomaquina;

import org.springframework.data.jpa.repository.*;

@Repository
public interface TipomaquinaRepository extends JpaRepository<Tipomaquina, Long> {
	public Tipomaquina findOneByKey(String key);
}
