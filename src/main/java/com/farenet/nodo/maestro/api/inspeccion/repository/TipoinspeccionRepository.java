package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoinspeccion;
import org.springframework.data.jpa.repository.*;

@Repository
public interface TipoinspeccionRepository extends JpaRepository<Tipoinspeccion,Long>{
	public Tipoinspeccion findOneByCodigosunat(String codigosunat);
	
	public Tipoinspeccion findOneBykey(String key);
}
