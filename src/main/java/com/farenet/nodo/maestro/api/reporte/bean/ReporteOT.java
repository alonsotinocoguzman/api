package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteOT {

	private Date fechapago;
	private String usuario;
	private String placamotor;
	private String nrodocumentocertificado;
	private String estadocomprobante;
	private String estadoinspeccion;
	private String nrocomprobante;
	private Date fechaestado;
	private String nroot;
	private String estadoot;
	private String nombredescuento;
	private double totalsindscto;
	private String conceptoinspeccion;
	private String tipodsctokey;
	private String nombreplanta;
	private String nrodocumentoidentidad;
	public String getNombreplanta() {
		return nombreplanta;
	}
	public void setNombreplanta(String nombreplanta) {
		this.nombreplanta = nombreplanta;
	}
	public String getNrodocumentoidentidad() {
		return nrodocumentoidentidad;
	}
	public void setNrodocumentoidentidad(String nrodocumentoidentidad) {
		this.nrodocumentoidentidad = nrodocumentoidentidad;
	}
	public Date getFechapago() {
		return fechapago;
	}
	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}
	public Date getFechaestado() {
		return fechaestado;
	}
	public void setFechaestado(Date fechaestado) {
		this.fechaestado = fechaestado;
	}
	public String getTipodsctokey() {
		return tipodsctokey;
	}
	public void setTipodsctokey(String tipodsctokey) {
		this.tipodsctokey = tipodsctokey;
	}
	public String getConceptoinspeccion() {
		return conceptoinspeccion;
	}
	public void setConceptoinspeccion(String conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
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
	public String getNrodocumentocertificado() {
		return nrodocumentocertificado;
	}
	public void setNrodocumentocertificado(String nrodocumentocertificado) {
		this.nrodocumentocertificado = nrodocumentocertificado;
	}
	public String getEstadocomprobante() {
		return estadocomprobante;
	}
	public void setEstadocomprobante(String estadocomprobante) {
		this.estadocomprobante = estadocomprobante;
	}
	public String getEstadoinspeccion() {
		return estadoinspeccion;
	}
	public void setEstadoinspeccion(String estadoinspeccion) {
		this.estadoinspeccion = estadoinspeccion;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getNroot() {
		return nroot;
	}
	public void setNroot(String nroot) {
		this.nroot = nroot;
	}
	public String getEstadoot() {
		return estadoot;
	}
	public void setEstadoot(String estadoot) {
		this.estadoot = estadoot;
	}
	public String getNombredescuento() {
		return nombredescuento;
	}
	public void setNombredescuento(String nombredescuento) {
		this.nombredescuento = nombredescuento;
	}
	public double getTotalsindscto() {
		return totalsindscto;
	}
	public void setTotalsindscto(double totalsindscto) {
		this.totalsindscto = totalsindscto;
	}
	
	
}
