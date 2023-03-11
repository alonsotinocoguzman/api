package com.farenet.nodo.maestro.api.reporte.bean;


public class ContabilidadReporteBean {
	private String tipoDoc;
	private String nrocomprobante;
	private String fecha;
	private String dniOruc;
	private String clienteDNI;
	private String clineteRUC;
	private String moneda;
	private double totalBase;
	private double totalIgv;
	private double total;
	private String estadoComprobante;
	private String tipodescuento;
	private String nombreDescuento;
	private String formaPago;
	private String tipoContado;
	private double basePago;
	private double igvPago;
	private double importePago;
	private String tarjeta;
	private String nroOperacionTarjeta;
	private String entidadFinanciera;
	private String nroOperacionBanco;
	public ContabilidadReporteBean() {
		super();
	}
	public ContabilidadReporteBean(String tipoDoc, String nrocomprobante, String fecha, String dniOruc,
			String clienteDNI, String clineteRUC, String moneda, double totalBase, double totalIgv, double total,
			String estadoComprobante, String tipodescuento, String nombreDescuento, String formaPago,
			String tipoContado, double basePago, double igvPago, double importePago, String tarjeta,
			String nroOperacionTarjeta, String entidadFinanciera, String nroOperacionBanco) {
		super();
		this.tipoDoc = tipoDoc;
		this.nrocomprobante = nrocomprobante;
		this.fecha = fecha;
		this.dniOruc = dniOruc;
		this.clienteDNI = clienteDNI;
		this.clineteRUC = clineteRUC;
		this.moneda = moneda;
		this.totalBase = totalBase;
		this.totalIgv = totalIgv;
		this.total = total;
		this.estadoComprobante = estadoComprobante;
		this.tipodescuento = tipodescuento;
		this.nombreDescuento = nombreDescuento;
		this.formaPago = formaPago;
		this.tipoContado = tipoContado;
		this.basePago = basePago;
		this.igvPago = igvPago;
		this.importePago = importePago;
		this.tarjeta = tarjeta;
		this.nroOperacionTarjeta = nroOperacionTarjeta;
		this.entidadFinanciera = entidadFinanciera;
		this.nroOperacionBanco = nroOperacionBanco;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDniOruc() {
		return dniOruc;
	}
	public void setDniOruc(String dniOruc) {
		this.dniOruc = dniOruc;
	}
	public String getClienteDNI() {
		return clienteDNI;
	}
	public void setClienteDNI(String clienteDNI) {
		this.clienteDNI = clienteDNI;
	}
	public String getClineteRUC() {
		return clineteRUC;
	}
	public void setClineteRUC(String clineteRUC) {
		this.clineteRUC = clineteRUC;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public double getTotalBase() {
		return totalBase;
	}
	public void setTotalBase(double totalBase) {
		this.totalBase = totalBase;
	}
	public double getTotalIgv() {
		return totalIgv;
	}
	public void setTotalIgv(double totalIgv) {
		this.totalIgv = totalIgv;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getEstadoComprobante() {
		return estadoComprobante;
	}
	public void setEstadoComprobante(String estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}
	public String getTipodescuento() {
		return tipodescuento;
	}
	public void setTipodescuento(String tipodescuento) {
		this.tipodescuento = tipodescuento;
	}
	public String getNombreDescuento() {
		return nombreDescuento;
	}
	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getTipoContado() {
		return tipoContado;
	}
	public void setTipoContado(String tipoContado) {
		this.tipoContado = tipoContado;
	}
	public double getBasePago() {
		return basePago;
	}
	public void setBasePago(double basePago) {
		this.basePago = basePago;
	}
	public double getIgvPago() {
		return igvPago;
	}
	public void setIgvPago(double igvPago) {
		this.igvPago = igvPago;
	}
	public double getImportePago() {
		return importePago;
	}
	public void setImportePago(double importePago) {
		this.importePago = importePago;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getNroOperacionTarjeta() {
		return nroOperacionTarjeta;
	}
	public void setNroOperacionTarjeta(String nroOperacionTarjeta) {
		this.nroOperacionTarjeta = nroOperacionTarjeta;
	}
	public String getEntidadFinanciera() {
		return entidadFinanciera;
	}
	public void setEntidadFinanciera(String entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}
	public String getNroOperacionBanco() {
		return nroOperacionBanco;
	}
	public void setNroOperacionBanco(String nroOperacionBanco) {
		this.nroOperacionBanco = nroOperacionBanco;
	}
	
}
