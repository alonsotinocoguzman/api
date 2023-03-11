package com.farenet.nodo.maestro.api.caja.domain;

public class Maestro {

	private String tipo;

	private String key;

	private String nombre;

	@Override
	public String toString() {
		return getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}