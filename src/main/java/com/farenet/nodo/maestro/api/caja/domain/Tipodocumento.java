package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

@Entity
@Table(name = "tipodocumento")
public class Tipodocumento {

	@Column(length = 150)
	private String nombre;
	
	@Column(name = "sunattipo", length = 2)
	private String sunatTipo;


	
	@Id
	@Column(length = 20)
	private String key;

	

	public String getSunatTipo() {
		return sunatTipo;
	}

	public void setSunatTipo(String sunatTipo) {
		this.sunatTipo = sunatTipo;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}