package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteMtcBean {
	private String conceptoInspeccion;
	private int anio;
	private String mes;
	private Long totalInspecciones;
	private Long enPrimera;
	private Long enSegunda;
	private Long enTercera;
	private Long totalAprobados;
	private String obs1;
	private String obs2;
	private String obs3;
	private Long totalDesaprobados;
	public ReporteMtcBean(String conceptoInspeccion, int anio, String mes, Long totalInspecciones, Long enPrimera,
			Long enSegunda, Long enTercera, Long totalAprobados, String obs1, String obs2, String obs3,
			Long totalDesaprobados) {
		super();
		this.conceptoInspeccion = conceptoInspeccion;
		this.anio = anio;
		this.mes = mes;
		this.totalInspecciones = totalInspecciones;
		this.enPrimera = enPrimera;
		this.enSegunda = enSegunda;
		this.enTercera = enTercera;
		this.totalAprobados = totalAprobados;
		this.obs1 = obs1;
		this.obs2 = obs2;
		this.obs3 = obs3;
		this.totalDesaprobados = totalDesaprobados;
	}
	public String getConceptoInspeccion() {
		return conceptoInspeccion;
	}
	public void setConceptoInspeccion(String conceptoInspeccion) {
		this.conceptoInspeccion = conceptoInspeccion;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Long getTotalInspecciones() {
		return totalInspecciones;
	}
	public void setTotalInspecciones(Long totalInspecciones) {
		this.totalInspecciones = totalInspecciones;
	}
	public Long getEnPrimera() {
		return enPrimera;
	}
	public void setEnPrimera(Long enPrimera) {
		this.enPrimera = enPrimera;
	}
	public Long getEnSegunda() {
		return enSegunda;
	}
	public void setEnSegunda(Long enSegunda) {
		this.enSegunda = enSegunda;
	}
	public Long getEnTercera() {
		return enTercera;
	}
	public void setEnTercera(Long enTercera) {
		this.enTercera = enTercera;
	}
	public Long getTotalAprobados() {
		return totalAprobados;
	}
	public void setTotalAprobados(Long totalAprobados) {
		this.totalAprobados = totalAprobados;
	}
	public String getObs1() {
		return obs1;
	}
	public void setObs1(String obs1) {
		this.obs1 = obs1;
	}
	public String getObs2() {
		return obs2;
	}
	public void setObs2(String obs2) {
		this.obs2 = obs2;
	}
	public String getObs3() {
		return obs3;
	}
	public void setObs3(String obs3) {
		this.obs3 = obs3;
	}
	public Long getTotalDesaprobados() {
		return totalDesaprobados;
	}
	public void setTotalDesaprobados(Long totalDesaprobados) {
		this.totalDesaprobados = totalDesaprobados;
	}
	
	
}
