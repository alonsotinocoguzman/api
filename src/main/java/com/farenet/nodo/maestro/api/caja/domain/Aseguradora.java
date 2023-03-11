package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.inspeccion.domain.TipoPoliza;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "aseguradora")
public class Aseguradora {

	@Column(length=75)
	private String nombre;

	@Id
	@Column(length=20)
	private String key;
	
	@ManyToOne()
	@JoinColumn(name = "tipopoliza_key")
	private TipoPoliza tipoPoliza;

	@Column(name = "keymtc")
	private Integer keyMTC;
	
	public Integer getKeyMTC() {
		return keyMTC;
	}

	public void setKeyMTC(Integer keyMTC) {
		this.keyMTC = keyMTC;
	}

	public TipoPoliza getTipoPoliza() {
		return tipoPoliza;
	}

	public void setTipoPoliza(TipoPoliza tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Aseguradora() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	

}