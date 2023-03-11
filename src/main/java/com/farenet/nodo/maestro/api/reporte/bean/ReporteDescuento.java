package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteDescuento {
	private String fecha;
	private String tipodescuento;
	private double montoDescuento;
	private double montoTotal;
	private double porcentaje;
	public ReporteDescuento(String fecha, String tipodescuento, double montoDescuento, double montoTotal,
			double porcentaje) {
		super();
		this.fecha = fecha;
		this.tipodescuento = tipodescuento;
		this.montoDescuento = montoDescuento;
		this.montoTotal = montoTotal;
		this.porcentaje = porcentaje;
	}
	public ReporteDescuento(String fecha, String tipodescuento, double montoDescuento, double montoTotal) {
		super();
		this.fecha = fecha;
		this.tipodescuento = tipodescuento;
		this.montoDescuento = montoDescuento;
		this.montoTotal = montoTotal;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipodescuento() {
		return tipodescuento;
	}
	public void setTipodescuento(String tipodescuento) {
		this.tipodescuento = tipodescuento;
	}
	public double getMontoDescuento() {
		return montoDescuento;
	}
	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
