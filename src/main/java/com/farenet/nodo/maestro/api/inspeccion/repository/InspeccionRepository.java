package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.InspeccionListBean;
import com.farenet.nodo.maestro.api.util.PageBean;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

//@Repository
public interface InspeccionRepository extends JpaRepository<Inspeccion, Long> {
	List<Inspeccion> findAllByEstado(Boolean estado);

	Inspeccion findByNrodocumentoinspeccion(String nrodocumentoinspeccion);
	
	Inspeccion findBynrodocumentoreinspeccion(String nrodocumentoreinspeccion);
	
	@Query(value = "SELECT i FROM Inspeccion i "
			+ "wHERE i.nrodocumentoreinspeccion = :nroreinspeccion")
	List<Inspeccion> findByReinspeccion(@Param("nroreinspeccion") String nroreinspeccion);
	
	@Query(value = "SELECT i.nrodocumentoinspeccion FROM Inspeccion i "
			+ "WHERE i.nrodocumentoinspeccion = :nroinspeccion")
	String findIdByNroInspeccion(@Param("nroinspeccion") String nroinspeccion);
	
	@Query(value = "SELECT i.nrodocumentoreinspeccion FROM Inspeccion i "
			+ "WHERE i.nrodocumentoinspeccion = :nroinspeccion")
	String findReinspeccionbyInpeccion(@Param("nroinspeccion") String nroinspeccion);
	
	@Query("SELECT li.nombre FROM Comprobante c"
			+ " inner join c.inspeccion i"
			+ " inner join c.linea li"
			+ " where i.nrodocumentoinspeccion = :nroinspeccionid")
	String findLineaByNroInspeccion(@Param("nroinspeccionid") String nroinspeccionid);
	
	@Query(value = "SELECT i FROM Comprobante c "
			+ "inner join c.inspeccion i "
			+ "where c.nrocomprobante = :nrocomprobante and c.empresa.key = :empresa" )
	Inspeccion findByComprobante(@Param("nrocomprobante") String nrocomprobante,@Param("empresa") String empresa);
	
	@Query(value = "SELECT i FROM Comprobante c "
			+ " inner join c.inspeccion i"
			+ " inner join c.linea li"
			+ " inner join c.ordenTrabajo ot"
			+ " where ot.nroOT = :nroOT and c.empresa.key = :empresa" )
	Inspeccion findByNroOtAndEmpresa(@Param("nroOT") String nroOT, @Param("empresa") String empresa);
	
	@Query(value = "SELECT (count(i)/500)+1 FROM Inspeccion i" )
	Long findCountInspeccion();
	
	@Query(value = "SELECT count(i) FROM Inspeccion i " +  "JOIN i.vehiculo v "
			+ "join v.tarjetapropiedad t "
			+ "where UPPER(t.nroplaca) like UPPER(:nroplaca)")
	Long findCountInspeccionByPlaca(@Param("nroplaca") String placa);
/*
	@Query(value = "SELECT i FROM Inspeccion i "
			 + "JOIN i.comprobante c "
			+ "WHERE i.sendedtooffisis = :sendedtooffisis and c.importetotal != 0 order by c.nrocomprobante asc")
	List<Inspeccion> findBySendedtooffisis(@Param("sendedtooffisis") Boolean sendedtooffisis);

	Page<Inspeccion> findAll(Pageable pag);

	@Query(value = "SELECT i FROM Inspeccion i " + "JOIN i.comprobante c " + "JOIN c.vehiculo v "
			+ "join v.tarjetapropiedad t " + "join t.propietario p " + "WHERE p.id = :personaid")
	Page<Inspeccion> searchByPropietario(@Param("personaid") Long personaid, Pageable pag);

	
	@Query(value = "SELECT i FROM Inspeccion i " + "JOIN i.comprobante c " + "JOIN c.vehiculo v "
			+ "join v.tarjetapropiedad t "
			+ "where UPPER(t.nroplaca) like UPPER(:nroplaca) order by i.fechconsolidado desc")
	Page<Inspeccion> findReporteCredito(@Param("nroplaca") String placa, Pageable pag);
	*/
	
	@Query(value = "SELECT i FROM Inspeccion i " +  "JOIN i.vehiculo v "
			+ "join v.tarjetapropiedad t "
			+ "where UPPER(t.nroplaca) like UPPER(:nroplaca) order by i.fechcreacion desc")
	List<Inspeccion> searchByPlaca(@Param("nroplaca") String placa);
	
	@Query(value = "SELECT i FROM Comprobante c " +  "JOIN c.inspeccion i "
			+ "where UPPER(c.placaMotor) like UPPER(:nroplaca) order by i.fechcreacion desc")
	List<Inspeccion> searchByPlacaComprobante(@Param("nroplaca") String placa);
		
	@Query(value = "SELECT i FROM Comprobante c " + "JOIN c.conceptoInspeccion ci " + "JOIN c.inspeccion i " + "JOIN i.inspeccionestado ie " + "JOIN c.linea l " + "JOIN l.planta pl "
			+ "where UPPER(c.placaMotor) like UPPER(:nroplaca) and pl.key = :planta and ci.key = :concepto "
			+ "and ie.key != 'ANU'  and (i.resultado='D' or i.resultado='') order by i.nrodocumentoinspeccion desc")
	List<Inspeccion> searchByPlacaComprobanteandConceptoandPlanta(@Param("nroplaca") String placa, @Param("concepto") String concepto, @Param("planta") String planta);
		
	@Query(value = "SELECT i FROM Comprobante c " + "JOIN c.conceptoInspeccion ci " + "JOIN c.inspeccion i " + "JOIN i.inspeccionestado ie " + "JOIN c.linea l " + "JOIN l.planta pl "
			+ "where UPPER(c.placaMotor) like UPPER(:nroplaca) and pl.key = :planta and ci.key = :concepto "
			+ "and ie.key != 'ANU'  and (i.resultado='D' or i.resultado='') order by i.nrodocumentoinspeccion asc")
	List<Inspeccion> searchByPlacaComprobanteandConceptoandPlantaAntigua(@Param("nroplaca") String placa, @Param("concepto") String concepto, @Param("planta") String planta);

}
