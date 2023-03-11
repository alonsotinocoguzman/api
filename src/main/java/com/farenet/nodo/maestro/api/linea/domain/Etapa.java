package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

import java.util.List;

@Entity
@Table(name = "etapa")
public class Etapa {

	@Id
	private String key;

	private String nombre;

	public Etapa() {
	}

	public String getKey() {
		return this.key;
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