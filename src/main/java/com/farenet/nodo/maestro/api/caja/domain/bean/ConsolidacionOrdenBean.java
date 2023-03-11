package com.farenet.nodo.maestro.api.caja.domain.bean;

public class ConsolidacionOrdenBean {
	public Long id;
	public String nroOt;
	public String placa;
	public String nroCertificado;
	public String cliente;
	public Double importe;
	public String planta;
	public String fecha;
	public boolean afecta = false;
	public ConsolidacionOrdenBean(Long id, String nroOt, String placa, String nroCertificado, String cliente,
			String planta, String fecha, Double importe) {
		super();
		this.id = id;
		this.nroOt = nroOt;
		this.placa = placa;
		this.nroCertificado = nroCertificado;
		this.cliente = cliente;
		this.planta = planta;
		this.fecha = fecha;
		this.importe = importe;
	}
	public ConsolidacionOrdenBean() {
		super();
	}
	
	
}
