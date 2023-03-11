package com.farenet.nodo.maestro.api.seguridad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;

@Repository
public interface EjecutivoRepository  extends JpaRepository<Ejecutivo,Long>{

    @Query(value = "SELECT e FROM Ejecutivo e " +
            "inner join e.persona p "
            + "where UPPER(p.nrodocumentoidentidad) like UPPER(:nrodocumentoidentidad)")
    public Ejecutivo findOneByDNI(@Param("nrodocumentoidentidad") String nrodocumentoidentidad);
    
    public Ejecutivo findOneByUser(String nombre);
}
