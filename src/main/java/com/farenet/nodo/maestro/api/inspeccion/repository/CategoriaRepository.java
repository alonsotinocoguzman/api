package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public Categoria findOneByKey(String key);
}
