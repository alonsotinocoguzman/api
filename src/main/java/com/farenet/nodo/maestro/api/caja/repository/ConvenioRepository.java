package com.farenet.nodo.maestro.api.caja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.caja.domain.Convenio;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.*;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
    public List<Convenio> findAllByEstado(boolean estado);

    public Page<Convenio> findAllByEstado(boolean estado, Pageable page);

    public Convenio findOneById(Long id);
    
    @Query(value = "SELECT c FROM Convenio c " +
            "left join c.persona p "
            + "where UPPER(c.nombre) like UPPER(:nombre) or UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre)")
    public Page<Convenio> findByLikeNombreOrEmpresaPersona(@Param("nombre") String nombre, Pageable page);
    
    @Query(value = "SELECT c FROM Convenio c " +
            "left join c.persona p "
            + "where c.estado = :estado and UPPER(c.nombre) like UPPER(:nombre) or UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre)")
    public Page<Convenio> findByLikeNombreOrEmpresaPersonaandEstado(@Param("nombre") String nombre, @Param("estado") Boolean estado, Pageable page);
    
    @Query(value = "SELECT c FROM Convenio c " +
            "where c.fechmodi >= :fech or c.fechcreacion >= :fech")
    public List<Convenio> findByFechUpdated(@Param("fech") Timestamp fech);
    
    public Convenio findOneByNombre(String nombre);
    
    public Convenio findOneByKey(String key);
    
}
