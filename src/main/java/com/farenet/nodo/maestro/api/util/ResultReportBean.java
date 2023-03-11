package com.farenet.nodo.maestro.api.util;

import java.util.List;

public class ResultReportBean<T> {
	
	public String planta;
	public List<T> data;
	
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
