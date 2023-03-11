package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@Entity
@Table(name = "distrito")
public class Distrito {

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(length=75)
	private String nombre;
	
	@ManyToOne()
	private Provincia provincia;
	
	@Id
	@Column(length=20)
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

	public Distrito() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
}