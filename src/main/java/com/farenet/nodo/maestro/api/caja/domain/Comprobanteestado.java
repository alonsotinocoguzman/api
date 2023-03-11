package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@Entity
@Table(name = "comprobanteestado")
public class Comprobanteestado  {

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(length=75)
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


	public Comprobanteestado() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}