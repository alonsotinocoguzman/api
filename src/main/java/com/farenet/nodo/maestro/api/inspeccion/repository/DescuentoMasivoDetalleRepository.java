package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoMasivoDetalle;

@Repository
public interface DescuentoMasivoDetalleRepository extends JpaRepository<DescuentoMasivoDetalle, Long> {

}
