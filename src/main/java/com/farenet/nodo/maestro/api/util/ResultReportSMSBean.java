package com.farenet.nodo.maestro.api.util;

import java.util.List;

public class ResultReportSMSBean<T> {
	
	public String planta;
	public List<T> data;
	public Long cantidadInspecciones;

	public Long getCantidadInspecciones() {
		return cantidadInspecciones;
	}
	public void setCantidadInspecciones(Long cantidadInspecciones) {
		this.cantidadInspecciones = cantidadInspecciones;
	}
	
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
	

}
