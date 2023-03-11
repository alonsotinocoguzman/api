package com.farenet.nodo.maestro.api.reporte.bean;


public class ReporteControlTiempoBean {

	public String inicioCaja;
	public String finCaja;
	public String entregaCert;
	public String tiempoOperativo;
	public String tiempoCaja;
	public String tiempoCert;
	public String placa;
	public String linea;
	public String estacionUno;
	public String estacionDos;
	public String estacionTres;
	public String intervalo;
	public String categoria;
	public String clase;
	public String tipoDescuento;
	public String nombreDescuento;
	public ReporteControlTiempoBean(String inicioCaja, String finCaja, String entregaCert, String tiempoOperativo,
			String tiempoCaja, String tiempoCert, String placa, String linea, String estacionUno, String estacionDos,
			String estacionTres, String intervalo, String categoria, String clase, String tipoDescuento, String nombreDescuento) {
		super();
		this.inicioCaja = inicioCaja;
		this.finCaja = finCaja;
		this.entregaCert = entregaCert;
		this.tiempoOperativo = tiempoOperativo;
		this.tiempoCaja = tiempoCaja;
		this.tiempoCert = tiempoCert;
		this.placa = placa;
		this.linea = linea;
		this.estacionUno = estacionUno;
		this.estacionDos = estacionDos;
		this.estacionTres = estacionTres;
		this.intervalo = intervalo;
		this.categoria = categoria;
		this.clase = clase;
		this.tipoDescuento = tipoDescuento;
		this.nombreDescuento = nombreDescuento;
	}
	public ReporteControlTiempoBean() {
		// TODO Auto-generated constructor stub
	}
	public String getInicioCaja() {
		return inicioCaja;
	}
	public void setInicioCaja(String inicioCaja) {
		this.inicioCaja = inicioCaja;
	}
	public String getFinCaja() {
		return finCaja;
	}
	public void setFinCaja(String finCaja) {
		this.finCaja = finCaja;
	}
	public String getEntregaCert() {
		return entregaCert;
	}
	public void setEntregaCert(String entregaCert) {
		this.entregaCert = entregaCert;
	}
	public String getTiempoOperativo() {
		return tiempoOperativo;
	}
	public void setTiempoOperativo(String tiempoOperativo) {
		this.tiempoOperativo = tiempoOperativo;
	}
	public String getTiempoCaja() {
		return tiempoCaja;
	}
	public void setTiempoCaja(String tiempoCaja) {
		this.tiempoCaja = tiempoCaja;
	}
	public String getTiempoCert() {
		return tiempoCert;
	}
	public void setTiempoCert(String tiempoCert) {
		this.tiempoCert = tiempoCert;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getEstacionUno() {
		return estacionUno;
	}
	public void setEstacionUno(String estacionUno) {
		this.estacionUno = estacionUno;
	}
	public String getEstacionDos() {
		return estacionDos;
	}
	public void setEstacionDos(String estacionDos) {
		this.estacionDos = estacionDos;
	}
	public String getEstacionTres() {
		return estacionTres;
	}
	public void setEstacionTres(String estacionTres) {
		this.estacionTres = estacionTres;
	}
	public String getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	public String getNombreDescuento() {
		return nombreDescuento;
	}
	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}
}
