package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteReinspeccionesPlacaNuevaBean {

	public String planta;
	public Date fecha;
	public String placa_antigua;
	public String tiposervicio;
	public String nrodocumentoinforme;
	public String nrocomprobante;
	public Double importetotal;
	public Date fechmodificacion;
	public String placa_nueva;
	public String numcertificado;
	
	public ReporteReinspeccionesPlacaNuevaBean(String planta, Date fecha, String placa_antigua, String tiposervicio,
			String nrodocumentoinforme, String nrocomprobante, Double importetotal, Date fechmodificacion,
			String placa_nueva, String numcertificado) {
		super();
		this.planta = planta;
		this.fecha = fecha;
		this.placa_antigua = placa_antigua;
		this.tiposervicio = tiposervicio;
		this.nrodocumentoinforme = nrodocumentoinforme;
		this.nrocomprobante = nrocomprobante;
		this.importetotal = importetotal;
		this.fechmodificacion = fechmodificacion;
		this.placa_nueva = placa_nueva;
		this.numcertificado = numcertificado;
	}
	public String getTiposervicio() {
		return tiposervicio;
	}
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	public String getNrodocumentoinforme() {
		return nrodocumentoinforme;
	}
	public void setNrodocumentoinforme(String nrodocumentoinforme) {
		this.nrodocumentoinforme = nrodocumentoinforme;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public Double getImportetotal() {
		return importetotal;
	}
	public void setImportetotal(Double importetotal) {
		this.importetotal = importetotal;
	}
	public Date getFechmodificacion() {
		return fechmodificacion;
	}
	public void setFechmodificacion(Date fechmodificacion) {
		this.fechmodificacion = fechmodificacion;
	}
	public String getNumcertificado() {
		return numcertificado;
	}
	public void setNumcertificado(String numcertificado) {
		this.numcertificado = numcertificado;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPlaca_antigua() {
		return placa_antigua;
	}
	public void setPlaca_antigua(String placa_antigua) {
		this.placa_antigua = placa_antigua;
	}
	public String getPlaca_nueva() {
		return placa_nueva;
	}
	public void setPlaca_nueva(String placa_nueva) {
		this.placa_nueva = placa_nueva;
	}
	public ReporteReinspeccionesPlacaNuevaBean() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
