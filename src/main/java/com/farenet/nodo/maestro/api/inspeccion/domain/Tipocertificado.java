package com.farenet.nodo.maestro.api.inspeccion.domain;

import java.util.List;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "tipocertificado")
public class Tipocertificado {

	@Id
	@Column(length=20)
	private String key;
	
	@Column(nullable = false, length=300)
	private String nombre;
	
	@Column(nullable = false, length=150)
	private String abreviacion;
	
	@JsonView({ComprobanteViews.CrudComprobante.class,InspeccionViews.CrudInspecciones.class})
	@Column(columnDefinition = "TEXT")
	private String cuerpocertificado;
	
	private Integer mesesvigencia;

	@Column(name = "keymtc")
	private Integer keyMTC;
	
	public Integer getKeyMTC() {
		return keyMTC;
	}

	public void setKeyMTC(Integer keyMTC) {
		this.keyMTC = keyMTC;
	}

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

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	public String getCuerpocertificado() {
		return cuerpocertificado;
	}

	public void setCuerpocertificado(String cuerpocertificado) {
		this.cuerpocertificado = cuerpocertificado;
	}

	public Integer getMesesvigencia() {
		return mesesvigencia;
	}

	public void setMesesvigencia(Integer mesesvigencia) {
		this.mesesvigencia = mesesvigencia;
	}	
	
}