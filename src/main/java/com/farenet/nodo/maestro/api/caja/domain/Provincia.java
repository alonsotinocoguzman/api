package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@Entity
@Table(name = "provincia")
public class Provincia {

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(length=200)
	private String nombre;

	@ManyToOne()
	private Departamento departamento;
	
	@Id
	@Column(length=20)
	private String key;
	
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
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	
	
}