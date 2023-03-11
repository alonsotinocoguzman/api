package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.farenet.nodo.maestro.api.caja.domain.Tarjeta;
import org.springframework.data.jpa.repository.*;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long>{
	public Tarjeta findOneByKey(String key);
}
