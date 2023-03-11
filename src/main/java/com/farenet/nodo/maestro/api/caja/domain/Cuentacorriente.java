package com.farenet.nodo.maestro.api.caja.domain;

import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cuentacorriente")
public class Cuentacorriente {

	
	@Id
	@Column(length = 20)
	private String key;
	
	
	@Column(length = 50)
	private String nroctacorriente;

	// bi-directional many-to-one association to Entidadfinanciera
	@ManyToOne(fetch = FetchType.EAGER)
	private Entidadfinanciera entidadfinanciera;

	// bi-directional many-to-one association to Moneda
	@ManyToOne()
	private Moneda moneda;
	
	
	private Boolean enable;

	@Column(name = "ctacontableofisis")
	private String ctaContableOFISIS;

	@Column(name = "ctacteofisis")
	private String ctaCteOFISIS;

	@Column(name = "codcptoofisis")
	private String codCptoOFISIS;
	
	@ManyToOne
	private Empresa empresa;

	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public void setCodCptoOFISIS(String codCptoOFISIS) {
		this.codCptoOFISIS = codCptoOFISIS;
	}
	public String getCodCptoOFISIS() {
		return codCptoOFISIS;
	}
	public String getCtaContableOFISIS() {
		return ctaContableOFISIS;
	}
	public void setCtaContableOFISIS(String ctaContableOFISIS) {
		this.ctaContableOFISIS = ctaContableOFISIS;
	}
	public String getCtaCteOFISIS() {
		return ctaCteOFISIS;
	}
	public void setCtaCteOFISIS(String ctaCteOFISIS) {
		this.ctaCteOFISIS = ctaCteOFISIS;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Cuentacorriente() {
	}
	public String getNroctacorriente() {
		return this.nroctacorriente;
	}
	public void setNroctacorriente(String nroctacorriente) {
		this.nroctacorriente = nroctacorriente;
	}
	public Moneda getMoneda() {
		return this.moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public Entidadfinanciera getEntidadfinanciera() {
		return entidadfinanciera;
	}
	public void setEntidadfinanciera(Entidadfinanciera entidadfinanciera) {
		this.entidadfinanciera = entidadfinanciera;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}