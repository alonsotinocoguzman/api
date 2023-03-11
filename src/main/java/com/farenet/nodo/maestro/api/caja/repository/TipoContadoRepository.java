package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.TipoContado;

@Repository
public interface TipoContadoRepository extends JpaRepository<TipoContado, Long> {

}
