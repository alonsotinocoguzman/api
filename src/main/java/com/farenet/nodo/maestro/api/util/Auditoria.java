package com.farenet.nodo.maestro.api.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;

@MappedSuperclass
public class Auditoria  {
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Usuario usuariocreacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JoinColumn(name = "usuariomodi_id", nullable = true)
	private Usuario usuariomodi;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechcreacion = new Timestamp(new Date().getTime());

	@Column(nullable = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechmodi;

	private Boolean estado = true;

	public Usuario getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(Usuario usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public Usuario getUsuariomodi() {
		return usuariomodi;
	}

	public void setUsuariomodi(Usuario usuariomodi) {
		this.usuariomodi = usuariomodi;
	}

	public Timestamp getFechcreacion() {
		return fechcreacion;
	}

	public void setFechcreacion(Timestamp fechcreacion) {
		this.fechcreacion = fechcreacion;
	}

	public Timestamp getFechmodi() {
		return fechmodi;
	}

	public void setFechmodi(Timestamp fechmodi) {
		this.fechmodi = fechmodi;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
