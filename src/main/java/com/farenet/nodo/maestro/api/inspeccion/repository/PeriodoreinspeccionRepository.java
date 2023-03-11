package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Periodoreinspeccion;

import org.springframework.data.jpa.repository.*;

@Repository
public interface PeriodoreinspeccionRepository extends JpaRepository<Periodoreinspeccion,Long>{
}
