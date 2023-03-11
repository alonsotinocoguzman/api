package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Color;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoautorizacion;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface TipoautorizacionRepository extends JpaRepository<Tipoautorizacion,Long>{
	public Tipoautorizacion findOneByKey(String key);
}
