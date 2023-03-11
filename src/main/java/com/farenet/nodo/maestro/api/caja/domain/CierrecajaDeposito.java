package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;

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

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cierrecajadeposito")
@Audited
public class CierrecajaDeposito extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Cierrecaja cierrecaja;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Cuentacorriente cuentacorriente;

	private Boolean sendedtooffisis = false;

	@Column(nullable = true, name = "fechadeposito")
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechaDeposito;

	private double monto;

	@Column(length = 30, name = "nrovoucher")
	private String nroVoucher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroVoucher() {
		return nroVoucher;
	}

	public void setNroVoucher(String nroVoucher) {
		this.nroVoucher = nroVoucher;
	}

	public Cierrecaja getCierrecaja() {
		return cierrecaja;
	}

	public void setCierrecaja(Cierrecaja cierrecaja) {
		this.cierrecaja = cierrecaja;
	}

	public Cuentacorriente getCuentacorriente() {
		return cuentacorriente;
	}

	public void setCuentacorriente(Cuentacorriente cuentacorriente) {
		this.cuentacorriente = cuentacorriente;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}

	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}

	public Timestamp getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(Timestamp fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
}
