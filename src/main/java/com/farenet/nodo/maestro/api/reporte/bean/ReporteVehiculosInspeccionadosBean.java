package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteVehiculosInspeccionadosBean {

	private String nroCert;
	private String nroInf;
	private String tipoInspeccion;
	private String tipoAutorizacion;
	private String estadoIns;
	private String observacion;
	private Date fechConsolidado;
	private	String vigencia;
	private String tieneReins;
	private String conceptoInspeccion;
	private String linea;
	private String placa;
	private int anho;
	private String combustible;
	private String categoria;
	private String categoriaExtra;
	private String resultado;
	public ReporteVehiculosInspeccionadosBean(String nroCert, String nroInf, String tipoInspeccion,
			String tipoAutorizacion, String estadoIns, String observacion, Date fechConsolidado, String vigencia,
			String tieneReins, String conceptoInspeccion, String linea, String placa, int anho, String combustible,
			String categoria, String categoriaExtra, String resultado) {
		super();
		this.nroCert = nroCert;
		this.nroInf = nroInf;
		this.tipoInspeccion = tipoInspeccion;
		this.tipoAutorizacion = tipoAutorizacion;
		this.estadoIns = estadoIns;
		this.observacion = observacion;
		this.fechConsolidado = fechConsolidado;
		this.vigencia = vigencia;
		this.tieneReins = tieneReins;
		this.conceptoInspeccion = conceptoInspeccion;
		this.linea = linea;
		this.placa = placa;
		this.anho = anho;
		this.combustible = combustible;
		this.categoria = categoria;
		this.categoriaExtra = categoriaExtra;
		this.resultado = resultado;
	}
	public String getNroCert() {
		return nroCert;
	}
	public void setNroCert(String nroCert) {
		this.nroCert = nroCert;
	}
	public String getNroInf() {
		return nroInf;
	}
	public void setNroInf(String nroInf) {
		this.nroInf = nroInf;
	}
	public String getTipoInspeccion() {
		return tipoInspeccion;
	}
	public void setTipoInspeccion(String tipoInspeccion) {
		this.tipoInspeccion = tipoInspeccion;
	}
	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}
	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}
	public String getEstadoIns() {
		return estadoIns;
	}
	public void setEstadoIns(String estadoIns) {
		this.estadoIns = estadoIns;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFechConsolidado() {
		return fechConsolidado;
	}
	public void setFechConsolidado(Date fechConsolidado) {
		this.fechConsolidado = fechConsolidado;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getTieneReins() {
		return tieneReins;
	}
	public void setTieneReins(String tieneReins) {
		this.tieneReins = tieneReins;
	}
	public String getConceptoInspeccion() {
		return conceptoInspeccion;
	}
	public void setConceptoInspeccion(String conceptoInspeccion) {
		this.conceptoInspeccion = conceptoInspeccion;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public int getAnho() {
		return anho;
	}
	public void setAnho(int anho) {
		this.anho = anho;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoriaExtra() {
		return categoriaExtra;
	}
	public void setCategoriaExtra(String categoriaExtra) {
		this.categoriaExtra = categoriaExtra;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
