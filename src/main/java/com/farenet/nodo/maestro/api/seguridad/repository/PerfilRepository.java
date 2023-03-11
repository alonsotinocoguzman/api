package com.farenet.nodo.maestro.api.seguridad.repository;

import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PerfilRepository extends JpaRepository<Perfil,Long>{

	Perfil findByClave(String clave);
}