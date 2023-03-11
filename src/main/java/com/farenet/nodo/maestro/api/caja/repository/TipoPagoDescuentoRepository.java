package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Formapago;
import com.farenet.nodo.maestro.api.caja.domain.TipoPagoDescuento;

@Repository 
public interface TipoPagoDescuentoRepository extends JpaRepository<TipoPagoDescuento, Long> {
	public TipoPagoDescuento findOneByKey(String key);

}
