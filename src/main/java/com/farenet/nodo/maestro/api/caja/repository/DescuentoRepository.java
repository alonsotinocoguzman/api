package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.Descuento;
import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.caja.domain.bean.DescuentoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.caja.domain.bean.DescuentoBean"
			+ "(des.id,des.nombre,td.nombre,des.limiteMontoMaximo,emp.nombrerazonsocial,"
			+ "des.fechInicio,des.fechFin,des.estado)"
			+ " FROM Descuento des"
			+ " left join des.tipodescuento td"
			+ " left join des.empresa emp")
	Page<DescuentoBean> getAllDescuentoBean(Pageable pag);
	
	Descuento getOneById(Long id);
	
	Descuento getOneByEmpresa(Persona empresa);
   
}
