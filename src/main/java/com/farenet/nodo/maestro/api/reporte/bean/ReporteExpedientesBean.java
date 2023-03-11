package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteExpedientesBean {

/**
 * Bean usado para el reporte de cierre de caja
*/
	
	private String planta;
	private int inspeccion;
	private Long comprobante;
	private int expediente;
	private int xml;
	private String sede_key;
	public ReporteExpedientesBean(String planta, int inspeccion, Long comprobante, int expediente, int xml,
			String sede_key) {
		super();
		this.planta = planta;
		this.inspeccion = inspeccion;
		this.comprobante = comprobante;
		this.expediente = expediente;
		this.xml = xml;
		this.sede_key = sede_key;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public int getInspeccion() {
		return inspeccion;
	}
	public void setInspeccion(int inspeccion) {
		this.inspeccion = inspeccion;
	}
	public Long getComprobante() {
		return comprobante;
	}
	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}
	public int getExpediente() {
		return expediente;
	}
	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}
	public int getXml() {
		return xml;
	}
	public void setXml(int xml) {
		this.xml = xml;
	}
	public String getSede_key() {
		return sede_key;
	}
	public void setSede_key(String sede_key) {
		this.sede_key = sede_key;
	}

	
}
