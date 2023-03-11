package com.farenet.nodo.maestro.api.caja.service;

import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDeposito;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaDepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by luis sanchez on 19/04/2017.
 */
@Service
@Transactional
public class CierrecajaDepositoService {

    @Autowired
    private CierrecajaDepositoRepository cierrecajaDepositoRepository;


    public List<CierrecajaDeposito> getNotSendedToOffisis() {
        return cierrecajaDepositoRepository.findBySendedtooffisis(false);
    }

    public CierrecajaDeposito update(CierrecajaDeposito deposito) {
        return cierrecajaDepositoRepository.saveAndFlush(deposito);
    }
}
