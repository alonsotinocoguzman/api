package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Conveniodetalle;

import java.util.List;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ConveniodetalleRepository extends JpaRepository<Conveniodetalle,Long>{
	public List<Conveniodetalle> findAll();
}
