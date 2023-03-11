package com.farenet.nodo.maestro.api.caja.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.caja.domain.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Page<Persona> findAll(Pageable pag);

    Page<Persona> findByNrodocumentoidentidadLikeIgnoreCase(String field, Pageable pag);

    Page<Persona> findByApellidosLikeIgnoreCase(String field, Pageable pag);

    Page<Persona> findByNombrerazonsocialLikeIgnoreCase(String field, Pageable pag);

    Page<Persona> findByNombresLikeIgnoreCase(String field, Pageable pag);

    Persona findOneByNrodocumentoidentidad(String nrodocumentoidentidad);

    @Query(value = "SELECT p FROM Persona p "
            + "WHERE (p.nombrerazonsocial = null or p.nombrerazonsocial = '') and p.estado = :estado")
    List<Persona> findPersonasNaturalesByEstado(@Param("estado") boolean estado);

    @Query(value = "SELECT p FROM Persona p "
            + "WHERE (UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre)) and p.estado = :estado")
    Page<Persona> findAllByCriteriaByEstado(@Param("nombre") String nombre, @Param("estado") boolean estado,
                                            Pageable pag);

    @Query(value = "SELECT p FROM Persona p "
            + "WHERE (UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre)) and p.estado = :estado")
    List<Persona> findAllByCriteriaByEstadoNoPaged(@Param("nombre") String nombre, @Param("estado") boolean estado);

    @Query(value = "SELECT p FROM Persona p "
            + "WHERE (p.nombrerazonsocial != null and p.nombrerazonsocial != '') and UPPER(p.nombrerazonsocial) like UPPER(:nombre) or UPPER(nrodocumentoidentidad) = UPPER(:nombre2) and p.estado = :estado")
    Page<Persona> findPersonasEmpresaByEstado(@Param("estado") boolean estado, @Param("nombre") String nombre,
                                              @Param("nombre2") String nombre2, Pageable pag);
}
