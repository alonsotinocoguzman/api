package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.List;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoMasivoDetalle;

public class DescuentoMasivoDetalleEditBean {
	
	private Long id;
	private Boolean estado;
	private List<DescuentoMasivoDetalle> masivoDetalles;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public List<DescuentoMasivoDetalle> getMasivoDetalles() {
		return masivoDetalles;
	}
	public void setMasivoDetalles(List<DescuentoMasivoDetalle> masivoDetalles) {
		this.masivoDetalles = masivoDetalles;
	}
	
	

}
