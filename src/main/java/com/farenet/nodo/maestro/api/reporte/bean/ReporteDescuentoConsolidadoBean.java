package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteDescuentoConsolidadoBean {
	private String nombre;
	private String tipo;
	private double totalDesc;
	private double totalFact;
	private Long cantIns;
	public ReporteDescuentoConsolidadoBean(String nombre, String tipo, double totalDesc, double totalFact,
			Long cantIns) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.totalDesc = totalDesc;
		this.totalFact = totalFact;
		this.cantIns = cantIns;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getTotalDesc() {
		return totalDesc;
	}
	public void setTotalDesc(double totalDesc) {
		this.totalDesc = totalDesc;
	}
	public double getTotalFact() {
		return totalFact;
	}
	public void setTotalFact(double totalFact) {
		this.totalFact = totalFact;
	}
	public Long getCantIns() {
		return cantIns;
	}
	public void setCantIns(Long cantIns) {
		this.cantIns = cantIns;
	}
}
