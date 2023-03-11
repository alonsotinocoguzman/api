package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaList;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface CierrecajaRepository extends JpaRepository<Cierrecaja, Long> {
    @Query(value = "SELECT cj FROM Cierrecaja cj where cj.planta = :planta "
            + "and cj.fechcreacion >= :initDay and cj.fechcreacion <= :finDay " + "order by cj.fechcreacion desc")
    public List<Cierrecaja> findLastByPlantaToday(@Param("planta") Planta planta, @Param("initDay") Timestamp initDay,
                                                  @Param("finDay") Timestamp finDay);

    @Query(value = "SELECT cj FROM Cierrecaja cj where cj.cierre = :cierre "
            + "and cj.fechcreacion >= :initDay and cj.fechcreacion <= :finDay")
    public List<Cierrecaja> findByCierreByDate(@Param("cierre") Cierre cierre, @Param("initDay") Timestamp initDay,
                                               @Param("finDay") Timestamp finDay);

    Page<Cierrecaja> findAll(Pageable pag);

    public Cierrecaja findOneById(String id);

    @Query(value = "SELECT new com.farenet.nodo.maestro.api.caja.domain.CierrecajaList(cj.id,c.nombre,sum(cjdeta.montoDepositoTotal),sum(cjdeta.montoSaldo),sum(cjdeta.montoTotal),cj.fechcreacion)"
            + " FROM Cierrecaja cj" + " inner join cj.cierre c" + " inner join cj.cierrecajadetalle cjdeta inner join cj.planta pl"
            + " where pl.key = :planta_key"
            + " group by c.nombre, cj.id")
    public Page<CierrecajaList> findList(@Param("planta_key") String planta_key, Pageable pag);
    
    @Query(value = "SELECT new com.farenet.nodo.maestro.api.caja.domain.CierrecajaList(cj.id,c.nombre,sum(cjdeta.montoDepositoTotal),sum(cjdeta.montoSaldo),sum(cjdeta.montoTotal),cj.fechcreacion)"
            + " FROM Cierrecaja cj" + " inner join cj.cierre c" + " inner join cj.cierrecajadetalle cjdeta inner join cj.planta pl"
            + " where pl.key = :planta_key and cj.fechcreacion >= :initDay and cj.fechcreacion <= :finDay"
            + " group by c.nombre, cj.id")
    public Page<CierrecajaList> findListByfechas(@Param("planta_key") String planta_key, @Param("initDay") Timestamp initDay,
            @Param("finDay") Timestamp finDay, Pageable pag);
    
    @Query(value = "SELECT new com.farenet.nodo.maestro.api.caja.domain.CierrecajaList(cj.id,c.nombre,sum(cjdeta.montoDepositoTotal),sum(cjdeta.montoSaldo),sum(cjdeta.montoTotal),cj.fechcreacion)"
            + " FROM Cierrecaja cj" + " inner join cj.cierre c" + " inner join cj.cierrecajadetalle cjdeta inner join cj.planta pl left join cj.cierrecajadeposito ccdp"
            + " where pl.key = :planta_key and cj.fechcreacion >= :initDay and cj.fechcreacion <= :finDay and ccdp.nroVoucher like :voucher"
            + " group by c.nombre, cj.id")
    public Page<CierrecajaList> findListByfechasAndNroVoucher(@Param("planta_key") String planta_key, @Param("initDay") Timestamp initDay,
            @Param("finDay") Timestamp finDay, @Param("voucher") String voucher,Pageable pag);

    public List<Cierrecaja> findByPlanta(Planta planta);
    
    public List<Cierrecaja> findBySendedtooffisis(Boolean send);
}
