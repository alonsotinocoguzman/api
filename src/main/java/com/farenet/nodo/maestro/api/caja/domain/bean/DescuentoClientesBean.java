package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.List;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoCliente;

public class DescuentoClientesBean {
	
	private Long id;
	private List<DescuentoCliente> descuentoclientes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<DescuentoCliente> getDescuentoclientes() {
		return descuentoclientes;
	}
	public void setDescuentoclientes(List<DescuentoCliente> descuentoclientes) {
		this.descuentoclientes = descuentoclientes;
	}
	
}
