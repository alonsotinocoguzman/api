package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglasuspension;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglasuspencionRepository extends JpaRepository<Reglasuspension, Long> {
}
