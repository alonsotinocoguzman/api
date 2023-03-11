package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "formapago")
public class Formapago {
	
	@Column(length=200)
	private String nombre;

	@Id
	@Column(length=20)
	private String key;
	
	@JsonIgnore
	private String codigoOFISIS;
	
	
	
	public String getCodigoOFISIS() {
		return codigoOFISIS;
	}

	public void setCodigoOFISIS(String codigoOFISIS) {
		this.codigoOFISIS = codigoOFISIS;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Formapago() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}