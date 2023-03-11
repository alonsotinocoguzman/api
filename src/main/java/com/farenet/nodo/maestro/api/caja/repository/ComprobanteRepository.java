package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.CierrecajaList;
import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

	@Query("SELECT com FROM Comprobante com WHERE com.estado = :estado")
	public List<Comprobante> findAllByEstado(@Param("estado") Boolean estado);
	
	@Query(value = "SELECT count(c) FROM Comprobante c"
			+ " inner join c.descuentoComprobante dc"
			+ " where dc.idDescuentoDetalle = :descid")
	public Long findCountComprobanteDescuentoId(@Param("descid") String descid);

	public Comprobante findOneByNrocomprobante(String nrocomprobante);
	
	@Query(value = "SELECT c FROM Comprobante c"
			+ " inner join c.linea l"
			+ " inner join l.planta pl"
			+ " where pl.key = :planta and c.nrocomprobante = :nrocomprobante")
	Comprobante findOneByNrocomprobanteByPlanta(String nrocomprobante, String planta);
		
	@Query(value = "SELECT c.nrocomprobante FROM Comprobante c "
			+ "where c.nrocomprobante = :nrocomprobante and c.empresa.key = :empresa" )
	String findNroByComprobante(@Param("nrocomprobante") String nrocomprobante,@Param("empresa") String empresa);
	
	
	@Query(value = "SELECT c FROM Comprobante c"
			+ " WHERE c.sendedtooffisis = :sendedtooffisis "
			+ " and c.comprobanteestado is not null "
			+ " order by c.nrocomprobante asc")
	List<Comprobante> findBySendedtooffisis(@Param("sendedtooffisis") Boolean sendedtooffisis);
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean(uca.user,c.nrocomprobante,concat('',c.fechapago),fp.nombre,c.placaMotor,"
			+ "c.baseimponible,c.igv,c.importetotal,ce.key,l.nombre,ot.nroOT,dc.nombreDescuento,td.nombre as desc)"
            + " FROM Comprobante c"
            + " left join c.comprobanteestado ce"
            + " left join c.ordenTrabajo ot"
            + " left join c.descuentoComprobante dc"
            + " left join dc.tipodescuento td"
            + " inner join c.formaPago fp"
			+ " inner join c.inspeccion i " 
			+ " left join i.usuariocaja uca" 
            + " inner join c.linea l "
            + " inner join l.planta pl"
            + " where pl.key = :planta_key and c.comprobanteestado is not null and c.totalsindscto > 0 "
            + " order by c.nrocomprobante desc")
    public Page<ComprobanteListBean> findList(@Param("planta_key") String planta_key, Pageable pag);
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean(uca.user,c.nrocomprobante,concat('',c.fechapago),fp.nombre,c.placaMotor,"
			+ "c.baseimponible,c.igv,c.importetotal,ce.key,l.nombre,ot.nroOT,dc.nombreDescuento,td.nombre as desc)"
            + " FROM Comprobante c"
            + " left join c.comprobanteestado ce"
            + " inner join c.formaPago fp"
            + " left join c.descuentoComprobante dc"
            + " left join dc.tipodescuento td"
            + " left join c.ordenTrabajo ot"
			+ " inner join c.inspeccion i " 
			+ " left join i.usuariocaja uca" 
            + " inner join c.linea l "
            + " inner join l.planta pl"
            + " where pl.key = :planta_key and c.comprobanteestado is not null and c.totalsindscto > 0 "
            + " and c.fechapago >= :initDay and c.fechapago <= :finDay "
            + " order by c.nrocomprobante desc")
    public Page<ComprobanteListBean> findListByfecha(@Param("planta_key") String planta_key,@Param("initDay") Timestamp initDay,
            @Param("finDay") Timestamp finDay, Pageable pag);
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean(uca.user,c.nrocomprobante,concat('',c.fechapago),fp.nombre,c.placaMotor,"
			+ "c.baseimponible,c.igv,c.importetotal,ce.key,l.nombre,ot.nroOT,dc.nombreDescuento,td.nombre as desc)"
            + " FROM Comprobante c"
            + " left join c.comprobanteestado ce"
            + " inner join c.formaPago fp"
            + " left join c.ordenTrabajo ot"
            + " left join c.descuentoComprobante dc"
            + " left join dc.tipodescuento td"
			+ " inner join c.inspeccion i " 
			+ " left join i.usuariocaja uca" 
            + " inner join c.linea l "
            + " inner join l.planta pl"
            + " where pl.key = :planta_key and c.comprobanteestado is not null and c.totalsindscto > 0 "
            + " and c.fechapago >= :initDay and c.fechapago <= :finDay and td.key = :tipodescuento"
            + " order by c.nrocomprobante desc")
    public Page<ComprobanteListBean> findListByfechaAndDescuento(@Param("planta_key") String planta_key,@Param("initDay") Timestamp initDay,
            @Param("finDay") Timestamp finDay, @Param("tipodescuento") String tipodescuento, Pageable pag);
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean(uca.user,c.nrocomprobante,concat('',c.fechapago),fp.nombre,c.placaMotor,"
			+ "c.baseimponible,c.igv,c.importetotal,ce.key,l.nombre,ot.nroOT,dc.nombreDescuento,td.nombre as desc)"
            + " FROM Comprobante c"
            + " left join c.comprobanteestado ce"
            + " left join c.ordenTrabajo ot"
            + " left join c.descuentoComprobante dc"
            + " left join dc.tipodescuento td"
            + " inner join c.formaPago fp"
			+ " inner join c.inspeccion i "
			+ " left join i.usuariocaja uca" 
            + " inner join c.linea l "
            + " inner join l.planta pl"
            + " where pl.key = :planta_key and c.comprobanteestado is not null and c.totalsindscto > 0 "
            + " and c.fechapago >= :initDay and c.fechapago <= :finDay and c.placaMotor like :placa"
            + " order by c.nrocomprobante desc")
    public Page<ComprobanteListBean> findListByfechaByplaca(@Param("planta_key") String planta_key,@Param("initDay") Timestamp initDay,
            @Param("finDay") Timestamp finDay,@Param("placa") String placa, Pageable pag);
	
	@Query(value = "SELECT new com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean(uca.user,c.nrocomprobante,concat('',c.fechapago),fp.nombre,c.placaMotor,"
			+ "c.baseimponible,c.igv,c.importetotal,ce.key,l.nombre,ot.nroOT,dc.nombreDescuento,td.nombre as desc)"
            + " FROM Comprobante c"
            + " left join c.comprobanteestado ce"
            + " left join c.ordenTrabajo ot"
            + " left join c.descuentoComprobante dc"
            + " left join dc.tipodescuento td"
            + " inner join c.formaPago fp"
			+ " inner join c.inspeccion i " 
			+ " left join i.usuariocaja uca" 
			+ " inner join c.linea l "
            + " inner join l.planta pl"
            + " where pl.key = :planta_key and c.comprobanteestado is not null and c.totalsindscto > 0 "
            + " and c.placaMotor like :placa"
            + " order by c.nrocomprobante desc")
    public Page<ComprobanteListBean> findListByplaca(@Param("planta_key") String planta_key,@Param("placa") String placa, Pageable pag);
}
