package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.List;

public class ReporteOperatividadBean {

	private String inspeccion;
	private String reins;
	private String result;
	private String maquina;
	private Boolean operado;
	private String lineanom;
	public ReporteOperatividadBean(String inspeccion, String reins, String result, String maquina, Boolean operado,
			String lineanom) {
		super();
		this.inspeccion = inspeccion;
		this.reins = reins;
		this.result = result;
		this.maquina = maquina;
		this.operado = operado;
		this.lineanom = lineanom;
	}
	public String getInspeccion() {
		return inspeccion;
	}
	public void setInspeccion(String inspeccion) {
		this.inspeccion = inspeccion;
	}
	public String getReins() {
		return reins;
	}
	public void setReins(String reins) {
		this.reins = reins;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMaquina() {
		return maquina;
	}
	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}
	public Boolean getOperado() {
		return operado;
	}
	public void setOperado(Boolean operado) {
		this.operado = operado;
	}
	public String getLineanom() {
		return lineanom;
	}
	public void setLineanom(String lineanom) {
		this.lineanom = lineanom;
	}
	
}
