package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.Campaniadetalle;
import com.farenet.nodo.maestro.api.caja.domain.Conveniodetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaniadetalleRepository extends JpaRepository<Campaniadetalle,Long>{
	
}
