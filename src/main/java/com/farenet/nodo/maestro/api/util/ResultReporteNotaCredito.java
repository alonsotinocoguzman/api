package com.farenet.nodo.maestro.api.util;

import java.util.Date;

public class ResultReporteNotaCredito {
	private Integer cuentaContable;
	private Date fechaDocumento;
	private String rucDni;
	private String razonSocialNombre;
	private String tipoDocumento;
	private String serieDocumento;
	private String nrodocumento;
	private String tipoMoneda;
	private Double montoAfecto;
	private Double igvAfecto;
	private Double montoTotal;
	private String glosario;
	private String centroCosto;
	private String estadoDocumento;
	private String fechaDocReferencia;
	private String tipoDocReferencia;
	private String serieDocReferencia;
	private Long numeroDocReferencia;
	private String montoDocReferencia;
	public Integer getCuentaContable() {
		return cuentaContable;
	}
	public void setCuentaContable(Integer cuentaContable) {
		this.cuentaContable = cuentaContable;
	}
	public Date getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public String getRucDni() {
		return rucDni;
	}
	public void setRucDni(String rucDni) {
		this.rucDni = rucDni;
	}
	public String getRazonSocialNombre() {
		return razonSocialNombre;
	}
	public void setRazonSocialNombre(String razonSocialNombre) {
		this.razonSocialNombre = razonSocialNombre;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getSerieDocumento() {
		return serieDocumento;
	}
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	public Double getMontoAfecto() {
		return montoAfecto;
	}
	public void setMontoAfecto(Double montoAfecto) {
		this.montoAfecto = montoAfecto;
	}
	public Double getIgvAfecto() {
		return igvAfecto;
	}
	public void setIgvAfecto(Double igvAfecto) {
		this.igvAfecto = igvAfecto;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getGlosario() {
		return glosario;
	}
	public void setGlosario(String glosario) {
		this.glosario = glosario;
	}
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public String getEstadoDocumento() {
		return estadoDocumento;
	}
	public void setEstadoDocumento(String estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}
	public String getFechaDocReferencia() {
		return fechaDocReferencia;
	}
	public void setFechaDocReferencia(String fechaDocReferencia) {
		this.fechaDocReferencia = fechaDocReferencia;
	}
	public String getTipoDocReferencia() {
		return tipoDocReferencia;
	}
	public void setTipoDocReferencia(String tipoDocReferencia) {
		this.tipoDocReferencia = tipoDocReferencia;
	}
	public String getSerieDocReferencia() {
		return serieDocReferencia;
	}
	public void setSerieDocReferencia(String serieDocReferencia) {
		this.serieDocReferencia = serieDocReferencia;
	}
	public Long getNumeroDocReferencia() {
		return numeroDocReferencia;
	}
	public void setNumeroDocReferencia(Long numeroDocReferencia) {
		this.numeroDocReferencia = numeroDocReferencia;
	}
	public String getMontoDocReferencia() {
		return montoDocReferencia;
	}
	public void setMontoDocReferencia(String montoDocReferencia) {
		this.montoDocReferencia = montoDocReferencia;
	}
	public ResultReporteNotaCredito(String serieRef, Long correlativoRef, String fechaRef, String nroRucEmpresa, String sustento, String tipo, Double importeNC, String nrodocumentoinspeccion, Date fechcreacion, String nrodocumento) {
		super();
		this.serieDocumento=serieRef;
		this.numeroDocReferencia=correlativoRef;
		this.fechaDocReferencia=fechaRef;
		this.rucDni=nroRucEmpresa;
		this.glosario=sustento;
		this.tipoDocumento=tipo;
		this.montoTotal=importeNC;
		this.nrodocumento=nrodocumento;
		this.fechaDocumento=fechcreacion;
	}
	public ResultReporteNotaCredito() {
		super();
	}

}