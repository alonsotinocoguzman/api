package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;

@Entity
@Table(name = "tipodocumentoidentidad")
public class Tipodocumentoidentidad {

	@JsonView({ ComprobanteViews.CrudComprobante.class, InspeccionViews.CrudInspecciones.class })
	@Column(nullable = false, length=75)
	private String nombre;
	
	@Column(length=1)
	private String codigosunat;
	
	@Id
	@Column(length=20)
	private String key;
	
	
	
	public String getCodigosunat() {
		return codigosunat;
	}

	public void setCodigosunat(String codigosunat) {
		this.codigosunat = codigosunat;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Tipodocumentoidentidad() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}