package com.farenet.nodo.maestro.api.linea.domain.bean;

import java.util.ArrayList;
import java.util.List;

import com.farenet.nodo.maestro.api.util.UIUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.qos.logback.classic.pattern.Util;

public class VehiculoMaquina {

	public String nroinspeccion;
	public List<String> idMaquinas = new ArrayList<String>();
	public String placa;
	public String marca;
	public String combustible;
	public Integer anio;
	public String categoria;
	public Integer nroEjes;
	public String linea;
	public String conceptoinspeccion;
	public Boolean soloFotos;
	public String categoriaKey;
	public String combustibleKey;
	public double pesobruto;
	public double pesoseco;

	public double getPesobruto() {
		return pesobruto;
	}

	public void setPesobruto(double pesobruto) {
		this.pesobruto = pesobruto;
	}

	public double getPesoseco() {
		return pesoseco;
	}

	public void setPesoseco(double pesoseco) {
		this.pesoseco = pesoseco;
	}

	public VehiculoMaquina() {
		super();
	}



	public VehiculoMaquina(String nroinspeccion, String placa, String marca, String combustible, Integer anio, String categoria,
			Integer nroEjes, String linea, String conceptoinspeccion, Boolean soloFotos, String categoriaKey, String combustibleKey,
			double pesobruto,double pesoseco) {
		super();
		this.nroinspeccion = nroinspeccion;
		this.placa = placa;
		this.marca = marca;
		this.combustible = combustible;
		this.anio = anio;
		this.categoria = categoria;
		this.nroEjes = nroEjes;
		this.linea = linea;
		this.conceptoinspeccion = conceptoinspeccion;
		this.soloFotos = soloFotos;
		this.categoriaKey = categoriaKey;
		this.combustibleKey = combustibleKey;
		this.pesobruto = pesobruto;
		this.pesoseco= pesoseco;
	}
	
	 @JsonIgnore
	    public boolean esCarreta() {
	        boolean esCarreta = false;
	        for (String tipoCarreta : UIUtils.tipoCarreta()) {
	            esCarreta = (categoriaKey.toUpperCase().equals(tipoCarreta));
	            if (esCarreta) {
	                break;
	            }
	        }
	        return esCarreta;
	    }
	 
	 @JsonIgnore
	    public boolean esMoto() {
	        boolean esMoto = false;
	        for (String tipoMoto : UIUtils.tipoMoto()) {
	        	esMoto = (categoriaKey.toUpperCase().equals(tipoMoto));
	            if (esMoto) {
	                break;
	            }
	        }
	        return esMoto;
	    }
	 
	 @JsonIgnore
	    public boolean esMotoCarro() {
	        boolean esMotoCarro = false;
	        for (String tipoMotoCarro : UIUtils.tipoMotoCarro()) {
	        	esMotoCarro = (categoriaKey.toUpperCase().equals(tipoMotoCarro));
	            if (esMotoCarro) {
	                break;
	            }
	        }
	        return esMotoCarro;
	    }
	
	
}
