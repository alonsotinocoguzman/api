package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipopagodescuento")
public class TipoPagoDescuento {


	@Column(length=20)
	@Id
	private String key;
	

	@Column(length=150)
	private String nombre;


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
