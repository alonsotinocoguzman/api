package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ReporteCierrecajaBean {
	private String formapago;
	private String entidad;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fecha;
	private String usuario;
	private String placamotor;
	private String nrodocumentoinspeccion;
	private String inspeccionestado;
	private String nrocomprobante;
	private String comprobanteestado;
	private String nrooperacion;
	private String vehiculoclase;
	private double neto;
	private double impuesto;
	private double importetotal;
	public ReporteCierrecajaBean(String formapago, String entidad, Date fecha, String usuario, String placamotor,
			String nrodocumentoinspeccion, String inspeccionestado, String nrocomprobante, String comprobanteestado,
			String nrooperacion, String vehiculoclase, double neto, double impuesto, double importetotal) {
		super();
		this.formapago = formapago;
		this.entidad = entidad;
		this.fecha = fecha;
		this.usuario = usuario;
		this.placamotor = placamotor;
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.inspeccionestado = inspeccionestado;
		this.nrocomprobante = nrocomprobante;
		this.comprobanteestado = comprobanteestado;
		this.nrooperacion = nrooperacion;
		this.vehiculoclase = vehiculoclase;
		this.neto = neto;
		this.impuesto = impuesto;
		this.importetotal = importetotal;
	}
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPlacamotor() {
		return placamotor;
	}
	public void setPlacamotor(String placamotor) {
		this.placamotor = placamotor;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getInspeccionestado() {
		return inspeccionestado;
	}
	public void setInspeccionestado(String inspeccionestado) {
		this.inspeccionestado = inspeccionestado;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getComprobanteestado() {
		return comprobanteestado;
	}
	public void setComprobanteestado(String comprobanteestado) {
		this.comprobanteestado = comprobanteestado;
	}
	public String getNrooperacion() {
		return nrooperacion;
	}
	public void setNrooperacion(String nrooperacion) {
		this.nrooperacion = nrooperacion;
	}
	public String getVehiculoclase() {
		return vehiculoclase;
	}
	public void setVehiculoclase(String vehiculoclase) {
		this.vehiculoclase = vehiculoclase;
	}
	public double getNeto() {
		return neto;
	}
	public void setNeto(double neto) {
		this.neto = neto;
	}
	public double getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	public double getImportetotal() {
		return importetotal;
	}
	public void setImportetotal(double importetotal) {
		this.importetotal = importetotal;
	}

}
