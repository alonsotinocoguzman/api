package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.linea.domain.Reglaalineacion;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglaalineacionRepository extends JpaRepository<Reglaalineacion, Long> {
}
