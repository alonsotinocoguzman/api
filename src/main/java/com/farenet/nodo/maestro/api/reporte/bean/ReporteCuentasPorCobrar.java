package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteCuentasPorCobrar {
	private String razonfarenet;
	private String nroRucFarenet;
	private String tipodocumento;
	private String nroruc;
	private String planta;
	private String formaPago;
	private String entidadNombre;
	private String entidadApellido;
	private String entidadRazon;
	private Date fechaPago;
	private String placa;
	private String certificado;
	private String nroinf;
	private String conceptoinspeccion;
	private String tipoVehiculo;
	private String nrocomprobante;
	private String tipocomprobante;
	private Double total;
	private String ordenservicio;
	private Double kilometraje;
	private String nombreejecutivo;

	public ReporteCuentasPorCobrar() {
		super();
	}

	public ReporteCuentasPorCobrar(String razonfarenet, String nroRucFarenet, String tipodocumento, String nroruc, String planta, String formaPago, String entidadNombre, String entidadApellido, String entidadRazon, Date fechaPago, String placa, String certificado, String nroinf, String conceptoinspeccion, String tipoVehiculo, String nrocomprobante, String tipocomprobante, Double total, String ordenservicio, Double kilometraje, String nombreejecutivo) {
		super();
		this.razonfarenet = razonfarenet;
		this.nroRucFarenet = nroRucFarenet;
		this.tipodocumento = tipodocumento;
		this.nroruc = nroruc;
		this.planta = planta;
		this.formaPago = formaPago;
		this.entidadNombre = entidadNombre;
		this.entidadApellido = entidadApellido;
		this.entidadRazon = entidadRazon;
		this.fechaPago = fechaPago;
		this.placa = placa;
		this.certificado = certificado;
		this.nroinf = nroinf;
		this.conceptoinspeccion = conceptoinspeccion;
		this.tipoVehiculo = tipoVehiculo;
		this.nrocomprobante = nrocomprobante;
		this.tipocomprobante = tipocomprobante;
		this.total = total;
		this.ordenservicio = ordenservicio;
		this.kilometraje = kilometraje;
		this.nombreejecutivo = nombreejecutivo;
	}

	public String getNombreejecutivo() {
		return nombreejecutivo;
	}

	public void setNombreejecutivo(String nombreejecutivo) {
		this.nombreejecutivo = nombreejecutivo;
	}

	public String getRazonfarenet() {
		return razonfarenet;
	}
	public void setRazonfarenet(String razonfarenet) {
		this.razonfarenet = razonfarenet;
	}
	public String getNroRucFarenet() {
		return nroRucFarenet;
	}
	public void setNroRucFarenet(String nroRucFarenet) {
		this.nroRucFarenet = nroRucFarenet;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getNroruc() {
		return nroruc;
	}
	public void setNroruc(String nroruc) {
		this.nroruc = nroruc;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEntidadNombre() {
		return entidadNombre;
	}
	public void setEntidadNombre(String entidadNombre) {
		this.entidadNombre = entidadNombre;
	}
	public String getEntidadApellido() {
		return entidadApellido;
	}
	public void setEntidadApellido(String entidadApellido) {
		this.entidadApellido = entidadApellido;
	}
	public String getEntidadRazon() {
		return entidadRazon;
	}
	public void setEntidadRazon(String entidadRazon) {
		this.entidadRazon = entidadRazon;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getNroinf() {
		return nroinf;
	}
	public void setNroinf(String nroinf) {
		this.nroinf = nroinf;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getTipocomprobante() {
		return tipocomprobante;
	}
	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getConceptoinspeccion() {
		return conceptoinspeccion;
	}
	public void setConceptoinspeccion(String conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}
	public String getOrdenservicio() {
		return ordenservicio;
	}
	public void setOrdenservicio(String ordenservicio) {
		this.ordenservicio = ordenservicio;
	}
	public Double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(Double kilometraje) {
		this.kilometraje = kilometraje;
	}
	
}
