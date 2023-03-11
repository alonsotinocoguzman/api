package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Combustible;
import java.util.List;
import org.springframework.data.jpa.repository.*;

@Repository
public interface CombustibleRepository extends JpaRepository<Combustible,Long>{
//	public List<Combustible> findAllByEstado(boolean estado);
}
