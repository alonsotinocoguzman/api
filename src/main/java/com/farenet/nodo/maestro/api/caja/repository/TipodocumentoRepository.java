package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumento;
import org.springframework.data.jpa.repository.*;

@Repository
public interface TipodocumentoRepository extends JpaRepository<Tipodocumento, String> {
//	public List<Tipodescuento> findAllByEstado(boolean estado);
}
