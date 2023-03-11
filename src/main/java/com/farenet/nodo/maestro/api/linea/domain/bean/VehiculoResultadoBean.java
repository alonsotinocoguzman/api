package com.farenet.nodo.maestro.api.linea.domain.bean;

import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import com.farenet.nodo.maestro.api.inspeccion.domain.Combustible;

public class VehiculoResultadoBean {
	
	public String placa;
	public Combustible combustible;
	public Integer anio;
	public Categoria categoria;
	public Integer nroEjes;
	
	public VehiculoResultadoBean(String placa, Combustible combustible, Integer anio, Categoria categoria,
			Integer nroEjes) {
		super();
		this.placa = placa;
		this.combustible = combustible;
		this.anio = anio;
		this.categoria = categoria;
		this.nroEjes = nroEjes;
	}

	public VehiculoResultadoBean() {
		super();
	}
	
	

}
