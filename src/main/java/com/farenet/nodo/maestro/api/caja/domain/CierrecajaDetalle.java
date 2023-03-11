package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cierrecajadetalle")
@Audited
public class CierrecajaDetalle extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Linea linea;

	@ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Cierrecaja cierrecaja;

	@Column(name = "montototal")
	private double montoTotal;

	@Column(name = "montosaldo")
	private double montoSaldo;

	@Column(name = "montodepositototal")
	private double montoDepositoTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public double getMontoSaldo() {
		return montoSaldo;
	}

	public void setMontoSaldo(double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}

	public double getMontoDepositoTotal() {
		return montoDepositoTotal;
	}

	public void setMontoDepositoTotal(double montoDepositoTotal) {
		this.montoDepositoTotal = montoDepositoTotal;
	}

	public Cierrecaja getCierrecaja() {
		return cierrecaja;
	}

	public void setCierrecaja(Cierrecaja cierrecaja) {
		this.cierrecaja = cierrecaja;
	}
}
