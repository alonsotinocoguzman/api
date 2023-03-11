package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.List;

import com.farenet.nodo.maestro.api.caja.domain.DescuentoMasivoCliente;

public class DescuentoMasivoClientesEditBean {
	private Long id;
	private Boolean estado;
	private String correo;
	private List<DescuentoMasivoCliente> descuentoMasivoClientes;
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
	public List<DescuentoMasivoCliente> getDescuentoMasivoClientes() {
		return descuentoMasivoClientes;
	}
	public void setDescuentoMasivoClientes(List<DescuentoMasivoCliente> descuentoMasivoClientes) {
		this.descuentoMasivoClientes = descuentoMasivoClientes;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
