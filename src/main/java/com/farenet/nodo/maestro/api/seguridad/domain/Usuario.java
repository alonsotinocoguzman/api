package com.farenet.nodo.maestro.api.seguridad.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("USER")
public class Usuario extends BaseUsuario {
	
	public Usuario(BaseUsuario baseusuario){
		super(baseusuario);
	}
	
	public Usuario(){
		
	}
}
