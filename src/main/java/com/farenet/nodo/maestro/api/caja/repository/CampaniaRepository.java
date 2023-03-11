package com.farenet.nodo.maestro.api.caja.repository;

import com.farenet.nodo.maestro.api.caja.domain.Tipodescuento;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Campania;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface CampaniaRepository extends JpaRepository<Campania, Long> {
    public List<Campania> findAllByEstado(boolean estado);

    @Query(value = "SELECT c FROM Campania c " +
            "inner join c.plantas pl "
            + "where pl = :planta and c.fechmodi >= :fech or c.fechcreacion >= :fech")
    public List<Campania> findAllByPlantaUpdated(@Param("planta") Planta planta, @Param("fech") Timestamp fech);

    @Query(value = "SELECT c FROM Campania c " +
            "inner join c.plantas pl "
            + "where pl = :planta")
    public List<Campania> findAllByPlanta(@Param("planta") Planta planta);

    @Query(value = "SELECT c FROM Campania c " +
            "inner join c.plantas pl "
            + "where c.id = :id and pl = :planta")
    public Campania findOneByPlantaAndById(@Param("id") Long id, @Param("planta") Planta planta);
    
    public Campania findOneById(Long id);

    public Page<Campania> findByTipodescuento(Tipodescuento tipodescuento, Pageable pag);

    @Query(value = "SELECT c FROM Campania c " +
            "left join c.empresas p "
            + "where c.estado = :estado and c.tipodescuento = :tipodescuento ")
    public Page<Campania> findByTipodescuentoandEstado(@Param("tipodescuento") Tipodescuento tipodescuento,@Param("estado") Boolean estado, Pageable pag);

    @Query(value = "SELECT c FROM Campania c " +
            "left join c.empresas p "
            + "where c.tipodescuento = :tipodescuento and (UPPER(c.nombre) like UPPER(:nombre) or UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre)) ")
    public Page<Campania> findByLikeNombreOrEmpresaPersonaByTipodescuento(@Param("nombre") String nombre, @Param("tipodescuento") Tipodescuento tipodescuento, Pageable page);
    
    @Query(value = "SELECT c FROM Campania c " +
            "left join c.empresas p "
            + "where c.estado = :estado and c.tipodescuento = :tipodescuento and (UPPER(c.nombre) like UPPER(:nombre) or UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre)) ")
    public Page<Campania> findByLikeNombreOrEmpresaPersonaByTipodescuentoandEstado(@Param("nombre") String nombre,@Param("estado") Boolean estado, @Param("tipodescuento") Tipodescuento tipodescuento, Pageable page);

    public Campania findOneByKey(String key);
}
