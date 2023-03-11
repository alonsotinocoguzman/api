package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.CondicionvigenciaCertificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondicionvigenciaCertificadoRepository extends JpaRepository<CondicionvigenciaCertificado,Long>{

}
