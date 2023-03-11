package com.farenet.nodo.maestro.api.callcenter.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClienteSMSCAPI {
	
	private String nombre;
	private String apellido;
	private String fechaVencimiento;

	@JsonIgnore
	private Date fecha;
	private String placa;
	private String telefono;
	
	public ClienteSMSCAPI(String nombre,String apellido,Date fecha,String placa, String telefono )
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha = fecha;
		this.placa = placa;
		this.telefono = telefono;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

}
