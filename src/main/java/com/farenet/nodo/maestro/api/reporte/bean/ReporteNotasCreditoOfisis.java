package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteNotasCreditoOfisis {
	private Integer cuentaContable;
	private Date fechaDocumento;
	private String rucDni;
	private String razonSocialNombre;
	private String tipoDocumento;
	private String serieDocumento;
	private String numeroDocumento;
	private String tipoMoneda;
	private Double montoAfecto;
	private Double igvAfecto;
	private Double montoTotal;
	private String glosario;
	private String centroCosto;
	private String estadoDocumento;
	private Date fechaDocReferencia;
	private String tipoDocReferencia;
	private String serieDocReferencia;
	private Long numeroDocReferencia;
	private String montoDocReferencia;
	private Long id;
	private String nrocomprobante;
	private String planta;
	
	public ReporteNotasCreditoOfisis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNrocomprobante() {
		return nrocomprobante;
	}

	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public Integer getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(Integer cuentaContable) {
		this.cuentaContable = cuentaContable;
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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public Date getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public Date getFechaDocReferencia() {
		return fechaDocReferencia;
	}
	public void setFechaDocReferencia(Date fechaDocReferencia) {
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

	public ReporteNotasCreditoOfisis(String serieRef, Long correlativoRef, Date fechaRef, String nroRucEmpresa, String sustento, String tipo, Double importeNC, String nrodocumentoinspeccion, Date fechcreacion, Long id,String nrocomprobante,String planta,Integer cuentaContable ) {
		super();
		this.serieDocumento=serieRef;
		this.numeroDocReferencia=correlativoRef;
		this.fechaDocReferencia=fechaRef;
		this.rucDni=nroRucEmpresa;
		this.glosario=sustento;
		this.tipoDocumento=tipo;
		this.montoTotal=importeNC;
		this.numeroDocumento=nrodocumentoinspeccion;		
		this.id=id;
		this.fechaDocumento=fechcreacion;
		this.nrocomprobante=nrocomprobante;
		this.planta=planta;
		this.cuentaContable=cuentaContable;
		// TODO Auto-generated constructor stub
	}
	
}
