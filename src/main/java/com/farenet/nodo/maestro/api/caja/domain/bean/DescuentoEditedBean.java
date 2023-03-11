package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.List;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoDetalle;

public class DescuentoEditedBean {
	
	private Long id;

	public Boolean estado;
	private List<DescuentoDetalle> descuentodetalles;
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<DescuentoDetalle> getDescuentodetalles() {
		return descuentodetalles;
	}
	public void setDescuentodetalles(List<DescuentoDetalle> descuentodetalles) {
		this.descuentodetalles = descuentodetalles;
	}
	
	

}
