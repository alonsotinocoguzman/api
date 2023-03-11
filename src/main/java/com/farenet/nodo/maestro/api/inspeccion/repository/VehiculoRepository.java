package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long>{

	@Query(
			value = "SELECT v FROM Vehiculo v "
					+ "JOIN v.tarjetapropiedad t "
					+ "where UPPER(t.nroplaca) LIKE UPPER(:nroplaca) "
					+ "order by t.fechmodi desc"
		)
	public Page<Vehiculo> findByLikeNroplaca(@Param("nroplaca") String nroplaca,Pageable pag);
	
	@Query(
			value = "SELECT v FROM Vehiculo v "
					+ "JOIN v.tarjetapropiedad t "
					+ "where UPPER(t.nroplaca) = UPPER(:nroplaca) "
					+ "order by t.fechmodi desc"
		)
	public Page<Vehiculo> findAllByPlaca(@Param("nroplaca") String nroplaca, Pageable pag);
	
	@Query(
			value = "SELECT v FROM Vehiculo v "
					+ "where UPPER(v.nromotor) = UPPER(:nromotor)"
		)
	public Page<Vehiculo> findByNromotor(@Param("nromotor") String nromotor,Pageable pag);
	
	@Query(
			value = "SELECT v FROM Vehiculo v "
					+ "where v.nromotor = :nromotor"
		)
	public Vehiculo findByNromotor(@Param("nromotor") String nromotor);
	
	@Query(
			value = "SELECT v FROM Vehiculo v "
					+ "JOIN v.tarjetapropiedad t "
					+ "where t.nroplaca = :nroplaca ")
	public Vehiculo findByNroPlaca(@Param("nroplaca") String nroplaca);
	

}
