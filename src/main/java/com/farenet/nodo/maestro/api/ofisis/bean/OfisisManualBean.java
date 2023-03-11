package com.farenet.nodo.maestro.api.ofisis.bean;

import java.util.Date;

public class OfisisManualBean {
	
	private Date fechMin;
	private Date fechMax;
	private String planta;
	public Date getFechMin() {
		return fechMin;
	}
	public void setFechMin(Date fechMin) {
		this.fechMin = fechMin;
	}
	public Date getFechMax() {
		return fechMax;
	}
	public void setFechMax(Date fechMax) {
		this.fechMax = fechMax;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	
	
	
}
