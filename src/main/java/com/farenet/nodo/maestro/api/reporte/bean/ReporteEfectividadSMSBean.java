package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteEfectividadSMSBean {
	
	private String placa;
	private String telefono;
	private Double monto;
	private Date fechaEnvioSMS;
	private Date fechaInspeccion;
	
	public ReporteEfectividadSMSBean(String placa, String telefono, Double monto, Date fechaInspeccion)
	{
		this.placa = placa;
		this.telefono = telefono;
		this.fechaInspeccion = fechaInspeccion;
		this.monto = monto;
	}
	
	




	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFechaEnvioSMS() {
		return fechaEnvioSMS;
	}
	public void setFechaEnvioSMS(Date fechaEnvioSMS) {
		this.fechaEnvioSMS = fechaEnvioSMS;
	}
	public Date getFechaInspeccion() {
		return fechaInspeccion;
	}
	public void setFechaInspeccion(Date fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}
	

}
