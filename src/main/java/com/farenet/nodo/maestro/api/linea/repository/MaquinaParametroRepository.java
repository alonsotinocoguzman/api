package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.linea.domain.Maquina;
import com.farenet.nodo.maestro.api.linea.domain.MaquinaParametro;
import com.farenet.nodo.maestro.api.linea.domain.Tipomaquina;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface MaquinaParametroRepository extends JpaRepository<MaquinaParametro, Long> {
	
}
