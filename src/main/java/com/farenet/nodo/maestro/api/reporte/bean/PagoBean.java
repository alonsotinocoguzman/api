package com.farenet.nodo.maestro.api.reporte.bean;

public class PagoBean {
	
	private String cuentacorriente;
	private String digitotarjeta;
	private String fechdeposito;
	private String fechacreacion;
	private String tipoContado;
	private String entidadFinanciera;
	private String nrooperacionBanco;
	private String nrooperacionTarjeta;
	private String tarjeta;
	private double importe;
	private double baseimponible;
	private double igv;
	
	
	public String getCuentacorriente() {
		return cuentacorriente;
	}
	public void setCuentacorriente(String cuentacorriente) {
		this.cuentacorriente = cuentacorriente;
	}
	public String getDigitotarjeta() {
		return digitotarjeta;
	}
	public void setDigitotarjeta(String digitotarjeta) {
		this.digitotarjeta = digitotarjeta;
	}
	public String getFechdeposito() {
		return fechdeposito;
	}
	public void setFechdeposito(String fechdeposito) {
		this.fechdeposito = fechdeposito;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getTipoContado() {
		return tipoContado;
	}
	public void setTipoContado(String tipoContado) {
		this.tipoContado = tipoContado;
	}
	public String getEntidadFinanciera() {
		return entidadFinanciera;
	}
	public void setEntidadFinanciera(String entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}
	public String getNrooperacionBanco() {
		return nrooperacionBanco;
	}
	public void setNrooperacionBanco(String nrooperacionBanco) {
		this.nrooperacionBanco = nrooperacionBanco;
	}
	public String getNrooperacionTarjeta() {
		return nrooperacionTarjeta;
	}
	public void setNrooperacionTarjeta(String nrooperacionTarjeta) {
		this.nrooperacionTarjeta = nrooperacionTarjeta;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getBaseimponible() {
		return baseimponible;
	}
	public void setBaseimponible(double baseimponible) {
		this.baseimponible = baseimponible;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
}
