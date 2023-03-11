package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteResumenIngresosBean {
	
	private String formaPago;
	private String tipoContado;
	private double monto;
	
	public ReporteResumenIngresosBean() {
		super();
	}

	public ReporteResumenIngresosBean(String formaPago, String tipoContado, double monto) {
		super();
		this.formaPago = formaPago;
		this.tipoContado = tipoContado;
		this.monto = monto;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getTipoContado() {
		return tipoContado;
	}
	public void setTipoContado(String tipoContado) {
		this.tipoContado = tipoContado;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
		
}
