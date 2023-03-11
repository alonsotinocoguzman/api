package com.farenet.nodo.maestro.api.linea.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "empresa")
public class Empresa {

	@Column(nullable = false, length=150)
	private String nombre;
	
	@Column(length=500)
	private String direccion;
	
	@Id
	@Column(length=10)
	private String key;
	
	private String ruc;

	private String telefono;

	@Column(name = "ctabanconacion")
	private String ctaBancoNacion;
	
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCtaBancoNacion() {
		return ctaBancoNacion;
	}

	public void setCtaBancoNacion(String ctaBancoNacion) {
		this.ctaBancoNacion = ctaBancoNacion;
	}

	
}