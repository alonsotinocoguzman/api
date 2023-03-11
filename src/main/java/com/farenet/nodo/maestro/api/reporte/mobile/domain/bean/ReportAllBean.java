package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

public class ReportAllBean {
	
	private String plantakey;
	private Double importe;
	private Long facturados;
	private Long inspecciones;
	private Double desaprobados;
	
	
	
	
	public ReportAllBean(String plantakey, Double importe, Long facturados, Long inspecciones, Double desaprobados) {
		super();
		this.plantakey = plantakey;
		this.importe = importe;
		this.facturados = facturados;
		this.inspecciones = inspecciones;
		this.desaprobados = desaprobados;
	}
	public String getPlantakey() {
		return plantakey;
	}
	public void setPlantakey(String plantakey) {
		this.plantakey = plantakey;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Long getFacturados() {
		return facturados;
	}
	public void setFacturados(Long facturados) {
		this.facturados = facturados;
	}
	public Long getInspecciones() {
		return inspecciones;
	}
	public void setInspecciones(Long inspecciones) {
		this.inspecciones = inspecciones;
	}
	public Double getDesaprobados() {
		return desaprobados;
	}
	public void setDesaprobados(Double desaprobados) {
		this.desaprobados = desaprobados;
	}
	public ReportAllBean() {
		super();
	}
}
