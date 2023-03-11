package com.farenet.nodo.maestro.api.seguridad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.seguridad.domain.BaseUsuario;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;

@Repository
public interface BaseUsuarioRepository  extends JpaRepository<BaseUsuario,Long>{

	BaseUsuario findOneByUser(String user);
	
	BaseUsuario findByUserAndContrasenha(String user, String contrasenha);
	

	List<BaseUsuario> findByPerfil(Perfil perfil);
}
