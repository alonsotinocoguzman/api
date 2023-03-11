package com.farenet.nodo.maestro.api.seguridad.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Long>{

	Usuario findOneByUser(String user);
	
	Usuario findByUserAndContrasenha(String user, String contrasenha);
	

	List<Usuario> findByPerfil(Perfil perfil);
	
	@Query(value = "SELECT u FROM Usuario u " 
			+ "inner join u.plantas pl "
			+ "inner join u.perfil pe "
            + "where pe.clave = :perfil and pl.key = :planta and u.estado = true")
    public List<Usuario> findAllIngenieroAndPlanta(@Param("perfil") String perfil, @Param("planta") String planta);
}
