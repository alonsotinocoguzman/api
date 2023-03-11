package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class InspeccionListBean {
	
	public String nrocomprobante;
	public String concepto;
	public String linea;
	public String idins;
	public String nroins;
	public String numCert;
	
	
	
	public InspeccionListBean(String nrocomprobante, String concepto, String linea, 
			String idins, String nroins, String numCert, Date fecha, String placa, String estdCert, String estins,
			String inseskey, String resul, String nroOt) {
		super();
		this.nrocomprobante = nrocomprobante;
		this.concepto = concepto;
		this.linea = linea;
		this.idins = idins;
		this.nroins = nroins;
		this.numCert = numCert;
		this.fecha = fecha;
		this.placa = placa;
		this.estdCert = estdCert;
		this.estins = estins;
		this.inseskey = inseskey;
		this.resul = resul;
		this.nroOt = nroOt;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date fecha;
	public String placa;
	public String estdCert;
	public String estins;
	public String inseskey;
	public String resul;
	public String nroOt;
	
	

	public String getNroOt() {
		return nroOt;
	}

	public void setNroOt(String nroOt) {
		this.nroOt = nroOt;
	}

	public String getNrocomprobante() {
		return nrocomprobante;
	}

	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getIdins() {
		return idins;
	}

	public void setIdins(String idins) {
		this.idins = idins;
	}

	public String getNroins() {
		return nroins;
	}

	public void setNroins(String nroins) {
		this.nroins = nroins;
	}

	public String getNumCert() {
		return numCert;
	}

	public void setNumCert(String numCert) {
		this.numCert = numCert;
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

	public String getEstdCert() {
		return estdCert;
	}

	public void setEstdCert(String estdCert) {
		this.estdCert = estdCert;
	}

	public String getEstins() {
		return estins;
	}

	public void setEstins(String estins) {
		this.estins = estins;
	}

	public String getInseskey() {
		return inseskey;
	}

	public void setInseskey(String inseskey) {
		this.inseskey = inseskey;
	}

	public String getResul() {
		return resul;
	}

	public void setResul(String resul) {
		this.resul = resul;
	}
	
	
	
}
