package com.farenet.nodo.maestro.api.caja.domain.bean;

public class MaquinaEditedBean {
	
	private Long id;
	public String modelo;
	public String nombreequipo;
	public String descripcion;
	public String serie;
	public String lineaKey;
	public String tipomaquina;
	private Long idlineaetapa;
	private String pccod;
	private String etapakey;
	private Long idlineaetapamaquina;
	public Long getIdlineaetapa() {
		return idlineaetapa;
	}
	public void setIdlineaetapa(Long idlineaetapa) {
		this.idlineaetapa = idlineaetapa;
	}
	public String getPccod() {
		return pccod;
	}
	public void setPccod(String pccod) {
		this.pccod = pccod;
	}
	public String getEtapakey() {
		return etapakey;
	}
	public void setEtapakey(String etapakey) {
		this.etapakey = etapakey;
	}
	public Long getIdlineaetapamaquina() {
		return idlineaetapamaquina;
	}
	public void setIdlineaetapamaquina(Long idlineaetapamaquina) {
		this.idlineaetapamaquina = idlineaetapamaquina;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNombreequipo() {
		return nombreequipo;
	}
	public void setNombreequipo(String nombreequipo) {
		this.nombreequipo = nombreequipo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getLineaKey() {
		return lineaKey;
	}
	public void setLineaKey(String lineaKey) {
		this.lineaKey = lineaKey;
	}
	public String getTipomaquina() {
		return tipomaquina;
	}
	public void setTipomaquina(String tipomaquina) {
		this.tipomaquina = tipomaquina;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public MaquinaEditedBean() {
		super();
	}
}
