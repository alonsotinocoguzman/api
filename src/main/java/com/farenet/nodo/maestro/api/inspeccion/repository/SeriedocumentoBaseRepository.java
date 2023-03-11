package com.farenet.nodo.maestro.api.inspeccion.repository;

import java.util.List;

import javax.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Seriedocumento;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;

@Repository
public interface SeriedocumentoBaseRepository extends JpaRepository<SeriedocumentoBase, Long> {
	
	@Query(value = "select sdb from SeriedocumentoBase sdb"
			+ " inner join sdb.planta pl"
			+ " where pl.key = :planta")
	SeriedocumentoBase findOneByPlanta(@Param("planta") String planta);
	
	@Query(value = "select sdb from SeriedocumentoBase sdb"
			+ " inner join sdb.planta pl"
			+ " inner join pl.empresa emp"
			+ " WHERE emp.ruc = :ruc and (sdb.serieboleta = :serie or sdb.seriefactura = :serie)")
	SeriedocumentoBase findOneByEmpresaAndSerie(@Param("ruc") String ruc, @Param("serie") String serie);

}
