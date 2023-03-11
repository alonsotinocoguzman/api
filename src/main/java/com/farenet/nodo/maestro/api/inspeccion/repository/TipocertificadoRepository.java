package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipocertificado;
import org.springframework.data.jpa.repository.*;

@Repository
public interface TipocertificadoRepository extends JpaRepository<Tipocertificado,Long>{
	
	public Tipocertificado findOneByKey(String key);

}
