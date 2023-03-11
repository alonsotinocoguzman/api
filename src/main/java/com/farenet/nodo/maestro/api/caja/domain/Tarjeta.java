package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tarjeta")
public class Tarjeta {

	@Column(nullable = false, length=150)
	private String nombre;
	
	@Id
	@Column(length=20)
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Tarjeta() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}