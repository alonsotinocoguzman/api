package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.List;

public class CotizacionMigradorBean {
	
	private String nombre;
	private String nroruc;
	private String empresa;
	private Double creditooMaximo;
	private List<ConceptoPrecioBean> conceptoPrecioBeans;
	private String[] plantas;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNroruc() {
		return nroruc;
	}
	public void setNroruc(String nroruc) {
		this.nroruc = nroruc;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Double getCreditooMaximo() {
		return creditooMaximo;
	}
	public void setCreditooMaximo(Double creditooMaximo) {
		this.creditooMaximo = creditooMaximo;
	}
	public List<ConceptoPrecioBean> getConceptoPrecioBeans() {
		return conceptoPrecioBeans;
	}
	public void setConceptoPrecioBeans(List<ConceptoPrecioBean> conceptoPrecioBeans) {
		this.conceptoPrecioBeans = conceptoPrecioBeans;
	}
	public String[] getPlantas() {
		return plantas;
	}
	public void setPlantas(String[] plantas) {
		this.plantas = plantas;
	}
	
	

}
