package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglaopacidad;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglaopacidadRepository extends JpaRepository<Reglaopacidad, Long> {
}
