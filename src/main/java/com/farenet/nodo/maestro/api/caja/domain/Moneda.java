package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "moneda")
public class Moneda {

	@Column(length=2)
	private String codigosunat;

	@Column(length=75)
	private String nombre;

	@Column(length=5)
	private String simbolo;

	@Column(length=1)
	private String txtcontabilidad;

	private double valorcambio;

	//bi-directional many-to-one association to Cuentacorriente
	@OneToMany(mappedBy="moneda")
	private List<Cuentacorriente> cuentacorrientes;
	
	@Column(length=20)
	@Id
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Moneda() {
	}

	public String getCodigosunat() {
		return this.codigosunat;
	}

	public void setCodigosunat(String codigosunat) {
		this.codigosunat = codigosunat;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getTxtcontabilidad() {
		return this.txtcontabilidad;
	}

	public void setTxtcontabilidad(String txtcontabilidad) {
		this.txtcontabilidad = txtcontabilidad;
	}

	public double getValorcambio() {
		return this.valorcambio;
	}

	public void setValorcambio(double valorcambio) {
		this.valorcambio = valorcambio;
	}

	public List<Cuentacorriente> getCuentacorrientes() {
		return this.cuentacorrientes;
	}

	public void setCuentacorrientes(List<Cuentacorriente> cuentacorrientes) {
		this.cuentacorrientes = cuentacorrientes;
	}

	public Cuentacorriente addCuentacorriente(Cuentacorriente cuentacorriente) {
		getCuentacorrientes().add(cuentacorriente);
		cuentacorriente.setMoneda(this);

		return cuentacorriente;
	}

	public Cuentacorriente removeCuentacorriente(Cuentacorriente cuentacorriente) {
		getCuentacorrientes().remove(cuentacorriente);
		cuentacorriente.setMoneda(null);

		return cuentacorriente;
	}

}