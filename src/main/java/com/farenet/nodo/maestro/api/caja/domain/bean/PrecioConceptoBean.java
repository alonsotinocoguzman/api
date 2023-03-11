package com.farenet.nodo.maestro.api.caja.domain.bean;

public class PrecioConceptoBean {
	private String key;
	private String abreviatura;
	private Double valor;
	private String planta;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public PrecioConceptoBean(String key, String abreviatura, Double valor, String planta, Long id) {
		super();
		this.key = key;
		this.abreviatura = abreviatura;
		this.valor = valor;
		this.planta = planta;
		this.id = id;
	}
	public PrecioConceptoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
