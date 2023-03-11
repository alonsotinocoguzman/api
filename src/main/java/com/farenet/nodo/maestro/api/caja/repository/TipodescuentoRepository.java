package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Tipodescuento;
import org.springframework.data.jpa.repository.*;

@Repository
public interface TipodescuentoRepository extends JpaRepository<Tipodescuento,Long>{
	public Tipodescuento findOneByKey(String key);
}
