package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteConsolidadoInspeccionVentasEABean {
	private String sede_key;
	private int year;
	private int month;
	private Long cant_ins;
	public ReporteConsolidadoInspeccionVentasEABean(String sede_key, int year, int month, Long cant_ins) {
		super();
		this.sede_key = sede_key;
		this.year = year;
		this.month = month;
		this.cant_ins = cant_ins;
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
	public Long getCant_ins() {
		return cant_ins;
	}
	public void setCant_ins(Long cant_ins) {
		this.cant_ins = cant_ins;
	}
	
}
