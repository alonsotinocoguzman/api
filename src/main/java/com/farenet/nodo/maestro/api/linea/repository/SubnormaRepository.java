package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.linea.domain.Norma;
import com.farenet.nodo.maestro.api.linea.domain.Subnorma;
import org.springframework.data.jpa.repository.*;

@Repository
public interface SubnormaRepository extends JpaRepository<Subnorma, Long> {
	public Subnorma findOneById(Long id);
}
