package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

@Entity
@Table(name = "tipopoliza")
public class TipoPoliza {
	
	@Id
	@Column(length=20)
	private String key;
	
	@Column(length=75)
	private String nombre;

	public TipoPoliza() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
