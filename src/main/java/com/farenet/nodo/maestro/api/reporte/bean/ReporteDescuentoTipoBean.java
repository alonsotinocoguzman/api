package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteDescuentoTipoBean {

	private String placa;
	private String fecha;
	private String comprobante;
	private String plantaKey;
	private String dni;
	private double total;
	private double importeTotal;
	private String nombreDescuento;
	
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public String getPlantaKey() {
		return plantaKey;
	}
	public void setPlantaKey(String plantaKey) {
		this.plantaKey = plantaKey;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getNombreDescuento() {
		return nombreDescuento;
	}
	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}
	public ReporteDescuentoTipoBean(String placa, String fecha, String comprobante, String plantaKey, String dni,
			double total, double importeTotal, String nombreDescuento) {
		super();
		this.placa = placa;
		this.fecha = fecha;
		this.comprobante = comprobante;
		this.plantaKey = plantaKey;
		this.dni = dni;
		this.total = total;
		this.importeTotal = importeTotal;
		this.nombreDescuento = nombreDescuento;
	}
	public ReporteDescuentoTipoBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
