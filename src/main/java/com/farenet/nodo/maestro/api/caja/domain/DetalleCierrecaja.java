package com.farenet.nodo.maestro.api.caja.domain;

//Esta clase se usa en un query de cierrecajarepository hay que tener cuidado al cambiar el nombre
public class DetalleCierrecaja {
	private double valor;
	private String key;

	public DetalleCierrecaja(String key, double valor) {
		this.key = key;
		this.valor = valor;
	}

	public DetalleCierrecaja() {

	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}