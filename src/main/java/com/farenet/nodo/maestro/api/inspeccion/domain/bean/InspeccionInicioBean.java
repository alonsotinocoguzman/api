package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion.POSICION;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class InspeccionInicioBean {
	
	private String nrodocumentoinspeccion;
	private String concepto;
	private String placa;
	private String dni;
	private String propietario;
	private POSICION posicion;
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fechaInicio;
	private String resultado;
	private String linea;
	private String tipodocumento;
	private String razon;
	private String nombresapellidos;
	
	
	
	public InspeccionInicioBean(String nrodocumentoinspeccion, String concepto, String placa, String dni,
			POSICION posicion, Date fechaInicio, String resultado, String linea, String tipodocumento,
			String razon, String nombresapellidos) {
		super();
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.concepto = concepto;
		this.placa = placa;
		this.dni = dni;
		this.posicion = posicion;
		this.fechaInicio = fechaInicio;
		this.resultado = resultado;
		this.linea = linea;
		this.tipodocumento = tipodocumento;
		this.razon = razon;
		this.nombresapellidos = nombresapellidos;
	}
	public InspeccionInicioBean(String nrodocumentoinspeccion, String concepto, String placa,
			String dni, String propietario, POSICION posicion, Date fechaInicio, String resultado,
			String linea, String tipodocumento, String razon, String nombresapellidos) {
		super();
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.concepto = concepto;
		this.placa = placa;
		this.dni = dni;
		this.propietario = propietario;
		this.posicion = posicion;
		this.fechaInicio = fechaInicio;
		this.resultado = resultado;
		this.linea = linea;
		this.tipodocumento = tipodocumento;
		this.razon = razon;
		this.nombresapellidos = nombresapellidos;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public POSICION getPosicion() {
		return posicion;
	}
	public void setPosicion(POSICION posicion) {
		this.posicion = posicion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getNombresapellidos() {
		return nombresapellidos;
	}
	public void setNombresapellidos(String nombresapellidos) {
		this.nombresapellidos = nombresapellidos;
	}
	
}
