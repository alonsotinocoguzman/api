package com.farenet.nodo.maestro.api.caja.domain;

import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "pago")
@Audited
public class Pago  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Cuentacorriente cuentacorriente;

	@Column(length = 4)
	private String digitotarjeta;


	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechdeposito;


	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechacreacion;


	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "tipocontado_key")
	private TipoContado tipoContado;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "entidadfinanciera_key")
	private Entidadfinanciera entidadFinanciera;


	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Moneda moneda;

	@Column(length = 75, nullable = true, name = "nrooperacionbanco")
	private String nrooperacionBanco;

	@Column(length = 75, nullable = true, name = "nrooperaciontarjeta")
	private String nrooperacionTarjeta;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tarjeta tarjeta;

	@ManyToOne(fetch= FetchType.LAZY )
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Comprobante comprobante;

	@Column()
	private double importe;
	
	@Column()
	private double baseimponible;

	@Column()
	private double igv;


	private Boolean sendedtooffisis = false;

	private String estado = "CAN";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cuentacorriente getCuentacorriente() {
		return cuentacorriente;
	}

	public void setCuentacorriente(Cuentacorriente cuentacorriente) {
		this.cuentacorriente = cuentacorriente;
	}

	public String getDigitotarjeta() {
		return digitotarjeta;
	}

	public void setDigitotarjeta(String digitotarjeta) {
		this.digitotarjeta = digitotarjeta;
	}

	public Timestamp getFechdeposito() {
		return fechdeposito;
	}

	public void setFechdeposito(Timestamp fechdeposito) {
		this.fechdeposito = fechdeposito;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public TipoContado getTipoContado() {
		return tipoContado;
	}

	public void setTipoContado(TipoContado tipoContado) {
		this.tipoContado = tipoContado;
	}

	public Entidadfinanciera getEntidadFinanciera() {
		return entidadFinanciera;
	}

	public void setEntidadFinanciera(Entidadfinanciera entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public String getNrooperacionBanco() {
		return nrooperacionBanco;
	}

	public void setNrooperacionBanco(String nrooperacionBanco) {
		this.nrooperacionBanco = nrooperacionBanco;
	}

	public String getNrooperacionTarjeta() {
		return nrooperacionTarjeta;
	}

	public void setNrooperacionTarjeta(String nrooperacionTarjeta) {
		this.nrooperacionTarjeta = nrooperacionTarjeta;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}

	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getBaseimponible() {
		return baseimponible;
	}

	public void setBaseimponible(double baseimponible) {
		this.baseimponible = baseimponible;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}
}