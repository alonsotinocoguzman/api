package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Cuentacorriente;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface CuentacorrienteRepository extends JpaRepository<Cuentacorriente,Long>{
//	public List<Cuentacorriente> findAllByEstado(boolean estado);
	
	@Query(value = "SELECT c FROM Cuentacorriente c"
			+ " inner join c.empresa emp "
			+ " WHERE emp.key = :empresa")
	public List<Cuentacorriente> findAllByEmpresa(@Param("empresa") String empresa);
}
