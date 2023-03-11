package com.farenet.nodo.maestro.api.seguridad.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("EJECUTIVO")
public class Ejecutivo extends BaseUsuario {
	public Ejecutivo(BaseUsuario baseusuario){
		super(baseusuario);
	}
	
	public Ejecutivo(){
		
	}
}
