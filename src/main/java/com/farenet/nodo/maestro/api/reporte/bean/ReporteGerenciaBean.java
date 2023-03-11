package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteGerenciaBean {
	private String concepto;
	private Long cantidad;
	private double total;

	public ReporteGerenciaBean(String concepto, Long cantidad, double total) {
		super();
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.total = total;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
