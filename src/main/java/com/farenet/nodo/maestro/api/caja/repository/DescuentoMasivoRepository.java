package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoMasivo;
import com.farenet.nodo.maestro.api.caja.domain.bean.DescuentoBean;

@Repository
public interface DescuentoMasivoRepository extends JpaRepository<DescuentoMasivo,Long> {
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.caja.domain.bean.DescuentoBean"
			+ "(dm.id,dm.nombre,td.nombre,dm.limiteMontoMaximo,emp.nombrerazonsocial,"
			+ "dm.fechInicio,dm.fechFin,dm.estado)"
			+ " FROM DescuentoMasivo dm"
			+ " left join dm.tipodescuento td"
			+ " left join dm.empresa emp")
	Page<DescuentoBean> getAllDescuentoMasivoBean(Pageable pag);
	
	DescuentoMasivo getOneById(Long id);
	
}
