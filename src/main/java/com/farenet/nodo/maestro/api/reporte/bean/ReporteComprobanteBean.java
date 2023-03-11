package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteComprobanteBean {
    private String formapago;
    private String fecha;
    private String clienteDni;
    private String clienteRuc;
    private String usuario;
    private String placamotor;
    private String inspeccionestado;
    private String nrocomprobante;
    private String comprobanteestado;
    private String vehiculoclase;
    private double totalsindescuento;
    private double descuento;
    private double base;
    private double igv;
    private double importetotal;
    private String tipDesc;
    private String nomDescuento;
    //pago
    private String tipocontado;
    private double igvPago;
    private double basePago;
    private double totalPago;
    private String nroot;
    public ReporteComprobanteBean() {
		super();
	}
	public ReporteComprobanteBean(String formapago, String fecha, String clienteDni, String clienteRuc, String usuario,
			String placamotor, String inspeccionestado, String nrocomprobante, String comprobanteestado,
			String vehiculoclase, double totalsindescuento, double descuento, double base, double igv,
			double importetotal, String tipDesc, String nomDescuento, String tipocontado, 
			double igvPago, double basePago, double totalPago, String nroot) {
		super();
		this.formapago = formapago;
		this.fecha = fecha;
		this.clienteDni = clienteDni;
		this.clienteRuc = clienteRuc;
		this.usuario = usuario;
		this.placamotor = placamotor;
		this.inspeccionestado = inspeccionestado;
		this.nrocomprobante = nrocomprobante;
		this.comprobanteestado = comprobanteestado;
		this.vehiculoclase = vehiculoclase;
		this.totalsindescuento = totalsindescuento;
		this.descuento = descuento;
		this.base = base;
		this.igv = igv;
		this.importetotal = importetotal;
		this.tipDesc = tipDesc;
		this.nomDescuento = nomDescuento;
		this.tipocontado = tipocontado;
		this.igvPago = igvPago;
		this.basePago = basePago;
		this.totalPago = totalPago;
		this.nroot = nroot;
	}
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getClienteDni() {
		return clienteDni;
	}
	public void setClienteDni(String clienteDni) {
		this.clienteDni = clienteDni;
	}
	public String getClienteRuc() {
		return clienteRuc;
	}
	public void setClienteRuc(String clienteRuc) {
		this.clienteRuc = clienteRuc;
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
	public String getVehiculoclase() {
		return vehiculoclase;
	}
	public void setVehiculoclase(String vehiculoclase) {
		this.vehiculoclase = vehiculoclase;
	}
	public double getTotalsindescuento() {
		return totalsindescuento;
	}
	public void setTotalsindescuento(double totalsindescuento) {
		this.totalsindescuento = totalsindescuento;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
	public double getImportetotal() {
		return importetotal;
	}
	public void setImportetotal(double importetotal) {
		this.importetotal = importetotal;
	}
	public String getTipDesc() {
		return tipDesc;
	}
	public void setTipDesc(String tipDesc) {
		this.tipDesc = tipDesc;
	}
	public String getNomDescuento() {
		return nomDescuento;
	}
	public void setNomDescuento(String nomDescuento) {
		this.nomDescuento = nomDescuento;
	}
	public String getTipocontado() {
		return tipocontado;
	}
	public void setTipocontado(String tipocontado) {
		this.tipocontado = tipocontado;
	}
	public double getIgvPago() {
		return igvPago;
	}
	public void setIgvPago(double igvPago) {
		this.igvPago = igvPago;
	}
	public double getBasePago() {
		return basePago;
	}
	public void setBasePago(double basePago) {
		this.basePago = basePago;
	}
	public double getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}
	public String getNroot() {
		return nroot;
	}
	public void setNroot(String nroot) {
		this.nroot = nroot;
	}
}