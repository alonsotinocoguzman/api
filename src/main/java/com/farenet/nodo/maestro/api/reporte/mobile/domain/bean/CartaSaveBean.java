package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

public class CartaSaveBean {
	private String conceptoinspeccion;
    private String planta;
    private String tipoPagoDescuento;
    private String placa;
    private double monto;
	public String getConceptoinspeccion() {
		return conceptoinspeccion;
	}
	public void setConceptoinspeccion(String conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getTipoPagoDescuento() {
		return tipoPagoDescuento;
	}
	public void setTipoPagoDescuento(String tipoPagoDescuento) {
		this.tipoPagoDescuento = tipoPagoDescuento;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
    
    
}
