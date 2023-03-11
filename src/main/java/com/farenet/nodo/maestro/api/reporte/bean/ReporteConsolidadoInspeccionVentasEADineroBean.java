package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteConsolidadoInspeccionVentasEADineroBean {
	private String sede_key;
	private int year;
	private int month;
	private double ammountImporte;
	public ReporteConsolidadoInspeccionVentasEADineroBean(String sede_key, int year, int month,double ammountImporte) {
		super();
		this.sede_key = sede_key;
		this.year = year;
		this.month = month;
		this.ammountImporte = ammountImporte;
	}
	public String getSede_key() {
		return sede_key;
	}
	public void setSede_key(String sede_key) {
		this.sede_key = sede_key;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getAmmountImporte() {
		return ammountImporte;
	}

	public void setAmmountImporte(double ammountImporte) {
		this.ammountImporte = ammountImporte;
	}
}
