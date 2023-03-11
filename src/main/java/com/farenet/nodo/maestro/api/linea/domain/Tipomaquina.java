package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tipomaquina")
public class Tipomaquina {

	@Id
	@Column(length=20)
	private String key;

	@Column(nullable = false, length=75)
	private String descripcion;
	
	@Column(name = "isfoto")
	private Boolean isFoto;

	public Tipomaquina() {
	}

	
	public Boolean getIsFoto() {
		return isFoto;
	}


	public void setIsFoto(Boolean isFoto) {
		this.isFoto = isFoto;
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


}