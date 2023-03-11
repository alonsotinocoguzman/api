package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.Formapago;

import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface FormapagoRepository extends JpaRepository<Formapago, Long> {
	public Formapago findOneByKey(String key);
}
