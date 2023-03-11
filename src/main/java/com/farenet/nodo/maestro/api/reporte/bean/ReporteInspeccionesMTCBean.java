package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteInspeccionesMTCBean {

	public String plantas;
	public int inspecciones;
	public int aprobados;
	public int desaprobados;
	public int anulados;
	public int duplicados;
	public int servicios;
	public int retirados_proceso;
	private String fecha;
	private String linea;
	private int totalmtc;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public int getTotalmtc() {
		return totalmtc;
	}

	public void setTotalmtc(int totalmtc) {
		this.totalmtc = totalmtc;
	}

	public String getPlantas() {
		return plantas;
	}


	public void setPlantas(String plantas) {
		this.plantas = plantas;
	}


	public int getInspecciones() {
		return inspecciones;
	}


	public void setInspecciones(int inspecciones) {
		this.inspecciones = inspecciones;
	}


	public int getAprobados() {
		return aprobados;
	}


	public void setAprobados(int aprobados) {
		this.aprobados = aprobados;
	}


	public int getDesaprobados() {
		return desaprobados;
	}


	public void setDesaprobados(int desaprobados) {
		this.desaprobados = desaprobados;
	}


	public int getAnulados() {
		return anulados;
	}


	public void setAnulados(int anulados) {
		this.anulados = anulados;
	}


	public int getDuplicados() {
		return duplicados;
	}


	public void setDuplicados(int duplicados) {
		this.duplicados = duplicados;
	}


	public int getServicios() {
		return servicios;
	}


	public void setServicios(int servicios) {
		this.servicios = servicios;
	}


	public int getRetirados_proceso() {
		return retirados_proceso;
	}


	public void setRetirados_proceso(int retirados_proceso) {
		this.retirados_proceso = retirados_proceso;
	}


	public ReporteInspeccionesMTCBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReporteInspeccionesMTCBean(String plantas, int inspecciones, int aprobados, int desaprobados,
			int anulados, int duplicados, int servicios, int retirados_proceso) {
		super();
		this.plantas = plantas;
		this.inspecciones = inspecciones;
		this.aprobados = aprobados;
		this.desaprobados = desaprobados;
		this.anulados = anulados;
		this.duplicados = duplicados;
		this.servicios = servicios;
		this.retirados_proceso = retirados_proceso;
	}
	
	
	
	
	
	
}
