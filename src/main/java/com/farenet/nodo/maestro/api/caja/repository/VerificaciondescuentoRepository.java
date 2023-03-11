package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.Verificaciondescuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificaciondescuentoRepository extends JpaRepository<Verificaciondescuento,Long>{
	public List<Verificaciondescuento> findAllByEstado(boolean estado);
}
