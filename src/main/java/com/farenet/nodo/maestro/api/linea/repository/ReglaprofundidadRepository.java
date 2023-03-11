package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Reglafreno;
import com.farenet.nodo.maestro.api.linea.domain.Reglaprofundidad;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ReglaprofundidadRepository extends JpaRepository<Reglaprofundidad, Long> {
}
