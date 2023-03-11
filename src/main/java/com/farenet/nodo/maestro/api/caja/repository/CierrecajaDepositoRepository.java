package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDeposito;
import org.springframework.data.jpa.repository.*;

import java.util.List;

@Repository
public interface CierrecajaDepositoRepository extends JpaRepository<CierrecajaDeposito, Long> {
    public List<CierrecajaDeposito> findBySendedtooffisis(Boolean send);
}