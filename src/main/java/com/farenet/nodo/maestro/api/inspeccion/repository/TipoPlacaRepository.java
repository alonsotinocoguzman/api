package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.TipoPlaca;
@Repository
public interface TipoPlacaRepository extends JpaRepository<TipoPlaca, Long> {
	//public TipoPlaca findOneByKey(Long id);
}
