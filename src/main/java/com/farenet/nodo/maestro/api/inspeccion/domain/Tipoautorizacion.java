package com.farenet.nodo.maestro.api.inspeccion.domain;

import java.util.List;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "tipoautorizacion")
public class Tipoautorizacion {

	@Id
	@Column(length=20)
	private String key;
	
	@Column(nullable = false, length=300)
	private String ambito;

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

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	
	
}