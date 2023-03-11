package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteControlAuditoriaBean {
	private String nroCertificado;
	private String linea;
	private Date fechconsolidado;
	private Date fechanulacion;
	private String placamotor;
	private String resultado;
	private String estadocertificado;
	private String inspeccionestado;
	private String nrodocumentoinspeccion;
	private Date fechcreacion;
	private String usuarioanulacioninspeccion;
	private String obsanucertificado;
	private String obsanulinspeccion;
	private String nrohojavalorada;
	private String usuarioConsolidado;
	private String usuarioCaja;
	private String hojaanulada;
	private String tipoerror;
	private String descrerror;
	public String getHojaanulada() {
		return hojaanulada;
	}
	public void setHojaanulada(String hojaanulada) {
		this.hojaanulada = hojaanulada;
	}
	public String getTipoerror() {
		return tipoerror;
	}
	public void setTipoerror(String tipoerror) {
		this.tipoerror = tipoerror;
	}
	public String getDescrerror() {
		return descrerror;
	}
	public void setDescrerror(String descrerror) {
		this.descrerror = descrerror;
	}
	public ReporteControlAuditoriaBean() {
		super();
	}
	public String getNroCertificado() {
		return nroCertificado;
	}
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public Date getFechconsolidado() {
		return fechconsolidado;
	}
	public void setFechconsolidado(Date fechconsolidado) {
		this.fechconsolidado = fechconsolidado;
	}
	public Date getFechanulacion() {
		return fechanulacion;
	}
	public void setFechanulacion(Date fechanulacion) {
		this.fechanulacion = fechanulacion;
	}
	public String getPlacamotor() {
		return placamotor;
	}
	public void setPlacamotor(String placamotor) {
		this.placamotor = placamotor;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getEstadocertificado() {
		return estadocertificado;
	}
	public void setEstadocertificado(String estadocertificado) {
		this.estadocertificado = estadocertificado;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public Date getFechcreacion() {
		return fechcreacion;
	}
	public void setFechcreacion(Date fechcreacion) {
		this.fechcreacion = fechcreacion;
	}
	public String getObsanucertificado() {
		return obsanucertificado;
	}
	public void setObsanucertificado(String obsanucertificado) {
		this.obsanucertificado = obsanucertificado;
	}
	public String getObsanulinspeccion() {
		return obsanulinspeccion;
	}
	public String getInspeccionestado() {
		return inspeccionestado;
	}
	public void setInspeccionestado(String inspeccionestado) {
		this.inspeccionestado = inspeccionestado;
	}
	public String getUsuarioanulacioninspeccion() {
		return usuarioanulacioninspeccion;
	}
	public void setUsuarioanulacioninspeccion(String usuarioanulacioninspeccion) {
		this.usuarioanulacioninspeccion = usuarioanulacioninspeccion;
	}
	public void setObsanulinspeccion(String obsanulinspeccion) {
		this.obsanulinspeccion = obsanulinspeccion;
	}
	public String getNrohojavalorada() {
		return nrohojavalorada;
	}
	public void setNrohojavalorada(String nrohojavalorada) {
		this.nrohojavalorada = nrohojavalorada;
	}
	public String getUsuarioConsolidado() {
		return usuarioConsolidado;
	}
	public void setUsuarioConsolidado(String usuarioConsolidado) {
		this.usuarioConsolidado = usuarioConsolidado;
	}
	public String getUsuarioCaja() {
		return usuarioCaja;
	}
	public void setUsuarioCaja(String usuarioCaja) {
		this.usuarioCaja = usuarioCaja;
	}
}
