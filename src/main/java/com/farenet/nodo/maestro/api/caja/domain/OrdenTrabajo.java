package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.util.Auditoria;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "ordentrabajo")
@Audited
public class OrdenTrabajo extends Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	public OrdenTrabajo() {
		super();
	}

	public OrdenTrabajo(String nroOT, Timestamp fechaestado, Comprobanteestado comprobanteestado) {
		super();
		this.nroOT = nroOT;
		this.fechaestado = fechaestado;
		this.comprobanteestado = comprobanteestado;
	}

	@Column(name = "nroot")
	private String nroOT;
	
	@Column(nullable = true)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechaestado;
	
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Comprobanteestado comprobanteestado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroOT() {
		return nroOT;
	}

	public void setNroOT(String nroOT) {
		this.nroOT = nroOT;
	}

	public Timestamp getFechaestado() {
		return fechaestado;
	}

	public void setFechaestado(Timestamp fechaestado) {
		this.fechaestado = fechaestado;
	}

	public Comprobanteestado getComprobanteestado() {
		return comprobanteestado;
	}

	public void setComprobanteestado(Comprobanteestado comprobanteestado) {
		this.comprobanteestado = comprobanteestado;
	}
	
	
	
	
	

	
}
