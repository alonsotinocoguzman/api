package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;

@Entity
@Table(name = "tipoinspeccion")
public class Tipoinspeccion {

	@Id
	@Column(length=20)
	private String key;

	@JsonView({ ComprobanteViews.CrudComprobante.class, InspeccionViews.CrudInspecciones.class })
	@Column(nullable = false, length=2)
	private String codigosunat;
	
	@JsonView({ ComprobanteViews.CrudComprobante.class, InspeccionViews.CrudInspecciones.class })
	@Column(nullable = false, length=150)
	private String nombre;

	@Column(name = "keymtc")
	private String keyMTC;

	
	public String getKeyMTC() {
		return keyMTC;
	}

	public void setKeyMTC(String keyMTC) {
		this.keyMTC = keyMTC;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Tipoinspeccion() {
	}

	public String getCodigosunat() {
		return this.codigosunat;
	}

	public void setCodigosunat(String codigosunat) {
		this.codigosunat = codigosunat;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}