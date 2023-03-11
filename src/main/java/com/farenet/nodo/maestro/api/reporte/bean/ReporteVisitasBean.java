package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteVisitasBean {
	private String placa;
	private Long cantidad;
	
	public ReporteVisitasBean(String placa, Long cantidad) {
		super();
		this.placa = placa;
		this.cantidad = cantidad;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	
}
