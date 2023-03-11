package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

public class inspeccionMtcBean {
	
	private String nroinspeccion;
	private String placa;
	private String resultado;
	private String nroCertificado;
	private Integer categoriaKeyMtc;
	private Integer tipocertifiadoKeyMtc;
	private Integer tipoautorizacionKeyMtc;
	private Integer tipoinspeciconKeyMtc;
	private Integer conceptoinspecionKeyMtc;
	private String tipopolizaKeyMtc;
	private Integer aseguradoraKeyMtc;
	private String fechaIniPoliza;
	private String fechaFinPoliza;
	private String nroPoliza;
	private String observaciones;
	private String fechaEmisionCert;
	private String fechaVencimiento;
	
	public String getNroCertificado() {
		return nroCertificado;
	}
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getNroinspeccion() {
		return nroinspeccion;
	}
	public void setNroinspeccion(String nroinspeccion) {
		this.nroinspeccion = nroinspeccion;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Integer getCategoriaKeyMtc() {
		return categoriaKeyMtc;
	}
	public void setCategoriaKeyMtc(Integer categoriaKeyMtc) {
		this.categoriaKeyMtc = categoriaKeyMtc;
	}
	public Integer getTipocertifiadoKeyMtc() {
		return tipocertifiadoKeyMtc;
	}
	public void setTipocertifiadoKeyMtc(Integer tipocertifiadoKeyMtc) {
		this.tipocertifiadoKeyMtc = tipocertifiadoKeyMtc;
	}
	public Integer getTipoautorizacionKeyMtc() {
		return tipoautorizacionKeyMtc;
	}
	public void setTipoautorizacionKeyMtc(Integer tipoautorizacionKeyMtc) {
		this.tipoautorizacionKeyMtc = tipoautorizacionKeyMtc;
	}
	public Integer getTipoinspeciconKeyMtc() {
		return tipoinspeciconKeyMtc;
	}
	public void setTipoinspeciconKeyMtc(Integer tipoinspeciconKeyMtc) {
		this.tipoinspeciconKeyMtc = tipoinspeciconKeyMtc;
	}
	public Integer getConceptoinspecionKeyMtc() {
		return conceptoinspecionKeyMtc;
	}
	public void setConceptoinspecionKeyMtc(Integer conceptoinspecionKeyMtc) {
		this.conceptoinspecionKeyMtc = conceptoinspecionKeyMtc;
	}
	public String getTipopolizaKeyMtc() {
		return tipopolizaKeyMtc;
	}
	public void setTipopolizaKeyMtc(String tipopolizaKeyMtc) {
		this.tipopolizaKeyMtc = tipopolizaKeyMtc;
	}
	public Integer getAseguradoraKeyMtc() {
		return aseguradoraKeyMtc;
	}
	public void setAseguradoraKeyMtc(Integer aseguradoraKeyMtc) {
		this.aseguradoraKeyMtc = aseguradoraKeyMtc;
	}
	public String getFechaIniPoliza() {
		return fechaIniPoliza;
	}
	public void setFechaIniPoliza(String fechaIniPoliza) {
		this.fechaIniPoliza = fechaIniPoliza;
	}
	public String getFechaFinPoliza() {
		return fechaFinPoliza;
	}
	public void setFechaFinPoliza(String fechaFinPoliza) {
		this.fechaFinPoliza = fechaFinPoliza;
	}
	public String getNroPoliza() {
		return nroPoliza;
	}
	public void setNroPoliza(String nroPoliza) {
		this.nroPoliza = nroPoliza;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFechaEmisionCert() {
		return fechaEmisionCert;
	}
	public void setFechaEmisionCert(String fechaEmisionCert) {
		this.fechaEmisionCert = fechaEmisionCert;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	
}
