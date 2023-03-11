package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteDefectoBean {
	private String planta;
	private String placa;
	private Date fecha;
	private String codigoValor;
	private String nroDocumentoInspeccion;
	private String nivelPeligrosidad;
	private String resultado;
	private String estadoInspeccion;
	private String linea;
	private String observacion;
	public ReporteDefectoBean() {
		super();
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCodigoValor() {
		return codigoValor;
	}
	public void setCodigoValor(String codigoValor) {
		this.codigoValor = codigoValor;
	}
	public String getNroDocumentoInspeccion() {
		return nroDocumentoInspeccion;
	}
	public void setNroDocumentoInspeccion(String nroDocumentoInspeccion) {
		this.nroDocumentoInspeccion = nroDocumentoInspeccion;
	}
	public String getNivelPeligrosidad() {
		return nivelPeligrosidad;
	}
	public void setNivelPeligrosidad(String nivelPeligrosidad) {
		this.nivelPeligrosidad = nivelPeligrosidad;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getEstadoInspeccion() {
		return estadoInspeccion;
	}
	public void setEstadoInspeccion(String estadoInspeccion) {
		this.estadoInspeccion = estadoInspeccion;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
