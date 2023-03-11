package com.farenet.nodo.maestro.api.callcenter.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SMSCAPI {
	
	private String telefono;
	private Date fecha;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	
	

}
