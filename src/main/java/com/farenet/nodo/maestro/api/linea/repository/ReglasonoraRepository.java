package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglasonora;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglasonoraRepository extends JpaRepository<Reglasonora, Long> {
}
