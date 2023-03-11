package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglafreno;
import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglafrenoRepository extends JpaRepository<Reglafreno, Long> {
}
