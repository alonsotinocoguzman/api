package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteMtcIndiceAprobadoBean {
	private Long cantidad;
	private int indiceDesaprobado;
	public ReporteMtcIndiceAprobadoBean(Long cantidad, int indiceDesaprobado) {
		super();
		this.cantidad = cantidad;
		this.indiceDesaprobado = indiceDesaprobado;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public int getIndiceDesaprobado() {
		return indiceDesaprobado;
	}
	public void setIndiceDesaprobado(int indiceDesaprobado) {
		this.indiceDesaprobado = indiceDesaprobado;
	}
	
}
