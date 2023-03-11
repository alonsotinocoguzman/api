package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglagas;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglagasRepository extends JpaRepository<Reglagas, Long> {
}
