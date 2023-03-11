package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteCortesiaBean {

	public String nrocomprobante;
	public double importe;
	public double base;
	public double igv;
	public String placa;
	public Date fechapago;
	public String planta;
	
	public ReporteCortesiaBean(String nrocomprobante, double importe, double base, double igv, String placa,
			Date fechapago, String planta) {
		super();
		this.nrocomprobante = nrocomprobante;
		this.importe = importe;
		this.base = base;
		this.igv = igv;
		this.placa = placa;
		this.fechapago = fechapago;
		this.planta = planta;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFechapago() {
		return fechapago;
	}
	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	
}
