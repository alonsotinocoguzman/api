package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteMtcDefectoDesaprobadoBean {
	private Long cantidad;
	private String defectoValor;
	public ReporteMtcDefectoDesaprobadoBean(Long cantidad, String defectoValor) {
		super();
		this.cantidad = cantidad;
		this.defectoValor = defectoValor;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getDefectoValor() {
		return defectoValor;
	}
	public void setDefectoValor(String defectoValor) {
		this.defectoValor = defectoValor;
	}
}
