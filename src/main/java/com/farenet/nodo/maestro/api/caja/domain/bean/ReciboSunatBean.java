package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.Date;

public class ReciboSunatBean {
	
	private String empresa;
	private String nrocomprobante;
	private String placa;
	private String clienteDni;
	private String clienteRuc;
	private String tipoDocu;
	private String pdf;
	
	private String rucEmp;
	private String tipoDocumento;
	private Date fecha;
	private Double monto;
	
	
	
	
	public ReciboSunatBean(String empresa, String nrocomprobante, String placa, String clienteDni, String clienteRuc,
			String tipoDocu, String pdf, String rucEmp, String tipoDocumento, Date fecha, Double monto) {
		super();
		this.empresa = empresa;
		this.nrocomprobante = nrocomprobante;
		this.placa = placa;
		this.clienteDni = clienteDni;
		this.clienteRuc = clienteRuc;
		this.tipoDocu = tipoDocu;
		this.pdf = pdf;
		this.rucEmp = rucEmp;
		this.tipoDocumento = tipoDocumento;
		this.fecha = fecha;
		this.monto = monto;
	}


	public String getRucEmp() {
		return rucEmp;
	}


	public void setRucEmp(String rucEmp) {
		this.rucEmp = rucEmp;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public String getPdf() {
		return pdf;
	}


	public void setPdf(String pdf) {
		this.pdf = pdf;
	}


	public String getTipoDocu() {
		return tipoDocu;
	}
	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
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
	
	
	
}
