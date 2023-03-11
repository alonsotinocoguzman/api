package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Entidadfinanciera;
import java.util.List;
import org.springframework.data.jpa.repository.*;

@Repository
public interface EntidadfinancieraRepository extends JpaRepository<Entidadfinanciera, Long> {
	public Entidadfinanciera findOneByKey(String key);
}
