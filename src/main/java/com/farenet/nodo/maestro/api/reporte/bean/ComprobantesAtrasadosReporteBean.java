package com.farenet.nodo.maestro.api.reporte.bean;

public class ComprobantesAtrasadosReporteBean {
	
	public String nrodocumentoinspeccion;
	public String planta;
	public String nro_placa;
	public String horaInicio;
	public String horaFin;
	public String tiempo;
	public String inspeccionEstado;
	public ComprobantesAtrasadosReporteBean(String nrodocumentoinspeccion, String planta, String nro_placa,
			String horaInicio, String horaFin, String tiempo, String inspeccionEstado) {
		super();
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.planta = planta;
		this.nro_placa = nro_placa;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.tiempo = tiempo;
		this.inspeccionEstado = inspeccionEstado;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getNro_placa() {
		return nro_placa;
	}
	public void setNro_placa(String nro_placa) {
		this.nro_placa = nro_placa;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getInspeccionEstado() {
		return inspeccionEstado;
	}
	public void setInspeccionEstado(String inspeccionEstado) {
		this.inspeccionEstado = inspeccionEstado;
	}
	
	
	
}
