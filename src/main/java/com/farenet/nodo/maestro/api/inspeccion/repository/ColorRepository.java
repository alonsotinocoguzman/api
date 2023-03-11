package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Color;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long>{
	public Color findOneByKey(String key);
}
