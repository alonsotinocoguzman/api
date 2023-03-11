package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ComprobanteestadoRepository extends JpaRepository<Comprobanteestado,Long>{
	public Comprobanteestado findOneByKey(String key);
}
