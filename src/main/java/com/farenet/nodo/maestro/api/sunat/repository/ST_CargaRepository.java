package com.farenet.nodo.maestro.api.sunat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.sunat.domain.ST_Carga;

@Repository
public interface ST_CargaRepository  extends JpaRepository<ST_Carga, Long>{

}
