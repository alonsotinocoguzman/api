package com.farenet.nodo.maestro.api.seguridad.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.domain.Rol;

@Repository
@Transactional
public interface RolRepository extends JpaRepository<Rol,Long>{

	Rol findOneByClave(String clave);
}