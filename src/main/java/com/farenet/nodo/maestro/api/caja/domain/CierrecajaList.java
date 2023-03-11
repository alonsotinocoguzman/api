package com.farenet.nodo.maestro.api.caja.domain;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//Esta clase se usa en un query de cierrecajarepository hay que tener cuidado al cambiar el nombre
public class CierrecajaList {
	public String id;
	public String nombreCierre;

	public double montoDepositadoTotal;
	public double montoTotal;
	public double montoSaldo;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date fechcreacion;

	public CierrecajaList(String id, String nombreCierre, double montoDepositadoTotal, double montoSaldo,
			double montoTotal, Date fechcreacion) {
		this.id = id;
		this.nombreCierre = nombreCierre;
		this.montoDepositadoTotal = montoDepositadoTotal;
		this.montoTotal = montoTotal;
		this.montoSaldo = montoSaldo;
		this.fechcreacion = fechcreacion;
	}

	public Date getFechcreacion() {
		return fechcreacion;
	}

	public void setFechcreacion(Date fechcreacion) {
		this.fechcreacion = fechcreacion;
	}

	public CierrecajaList() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCierre() {
		return nombreCierre;
	}

	public void setNombreCierre(String nombreCierre) {
		this.nombreCierre = nombreCierre;
	}

	public double getMontoDepositadoTotal() {
		return montoDepositadoTotal;
	}

	public void setMontoDepositadoTotal(double montoDepositadoTotal) {
		this.montoDepositadoTotal = montoDepositadoTotal;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public double getMontoSaldo() {
		return montoSaldo;
	}

	public void setMontoSaldo(double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}

}