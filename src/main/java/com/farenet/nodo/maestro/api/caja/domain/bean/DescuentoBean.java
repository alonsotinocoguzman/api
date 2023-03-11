package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DescuentoBean {
	
	private Long id;
    private String nombre;
    private String tipodescuento;
    private Double limiteMontoMaximo;
	private String empresa;
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fechInicio;
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fechFin;
	private Boolean estado;
	
	public DescuentoBean(Long id, String nombre, String tipodescuento, Double limiteMontoMaximo, 
			String empresa, Date fechInicio, Date fechFin, Boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipodescuento = tipodescuento;
		this.limiteMontoMaximo = limiteMontoMaximo;
		this.empresa = empresa;
		this.fechInicio = fechInicio;
		this.fechFin = fechFin;
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipodescuento() {
		return tipodescuento;
	}
	public void setTipodescuento(String tipodescuento) {
		this.tipodescuento = tipodescuento;
	}
	
	public Double getLimiteMontoMaximo() {
		return limiteMontoMaximo;
	}
	public void setLimiteMontoMaximo(Double limiteMontoMaximo) {
		this.limiteMontoMaximo = limiteMontoMaximo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Date getFechInicio() {
		return fechInicio;
	}
	public void setFechInicio(Date fechInicio) {
		this.fechInicio = fechInicio;
	}
	public Date getFechFin() {
		return fechFin;
	}
	public void setFechFin(Date fechFin) {
		this.fechFin = fechFin;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
