package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entidadfinanciera")
public class Entidadfinanciera {

	@Column(length = 75)
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidadfinanciera")
	private List<Cuentacorriente> cuentacorrientes;
	@Id
	@Column(length = 20)
	private String key;

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Entidadfinanciera() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuentacorriente> getCuentacorrientes() {
		return cuentacorrientes;
	}

	public void setCuentacorrientes(List<Cuentacorriente> cuentacorrientes) {
		this.cuentacorrientes = cuentacorrientes;
	}

}