package com.farenet.nodo.maestro.api.callcenter.bean;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SoatCAPI {
	
	private String aseguradora;
	@JsonIgnore
	private Date fcvs;
	private String fechavencimientosoat;
	
	public SoatCAPI()
	{
		
	}
	
	public SoatCAPI(String aseguradora, Date fcvs, String fechavencimientosoat) {
		super();
		this.aseguradora = aseguradora;
		this.fcvs = fcvs;
		this.fechavencimientosoat = fechavencimientosoat;
	}
	
	public String getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}
	public Date getFcvs() {
		return fcvs;
	}
	public void setFcvs(Date fcvs) {
		this.fcvs = fcvs;
	}
	public String getFechavencimientosoat() {
		return fechavencimientosoat;
	}
	public void setFechavencimientosoat(String fechavencimientosoat) {
		this.fechavencimientosoat = fechavencimientosoat;
	}
	
	
		
}
