package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

public class ErrorImpresionBean {
	
	private String tipoError;
	private String descripcion;
	private String nroinspeccion;
	
	public String getTipoError() {
		return tipoError;
	}
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNroinspeccion() {
		return nroinspeccion;
	}
	public void setNroinspeccion(String nroinspeccion) {
		this.nroinspeccion = nroinspeccion;
	}
	
}
