package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

public class CorrelativosBean {
	
	private Long id;
    private Long nroinicio;
    private Long nromaximo;
    private Long nroactual;
    private Linea linea;
    
    private class Linea {
        public String nombre;
        public String key;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNroinicio() {
		return nroinicio;
	}
	public void setNroinicio(Long nroinicio) {
		this.nroinicio = nroinicio;
	}
	public Long getNromaximo() {
		return nromaximo;
	}
	public void setNromaximo(Long nromaximo) {
		this.nromaximo = nromaximo;
	}
	public Long getNroactual() {
		return nroactual;
	}
	public void setNroactual(Long nroactual) {
		this.nroactual = nroactual;
	}
}
