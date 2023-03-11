package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.sql.Timestamp;
import java.util.Date;

public class ReciboBean {
	
	private String telefonoPlan;
	private String nroAutSunat;
	private String empresa;
	private String nrocomprobante;
	private String fechaPago;
	private String tipoDescuento;
	private String concepto;
	private String placa;
	private String nroMotor;
	private String marca;
	private String modelo;
	private String clase;
	private String clienteDni;
	private String clienteRuc;
	private String direccion;
	private String departamento;
	private String distrito;
	private String telefonoCli;
	private String nroDocumento;
	private String tipoDocu;
	private double importeTotal;
	private double base;
	private double igv;
	private String nomUsuCaja;
	private String direccionemp;
	private String direcplanta;
	private String nrodocInf;
	private String nroEmp;
	private double preCon;
	public ReciboBean(String telefonoPlan, String nroAutSunat, String empresa, String nrocomprobante, String fechaPago,
			String tipoDescuento, String concepto, String placa, String nroMotor, String marca, String modelo,
			String clase, String clienteDni, String clienteRuc, String direccion, String departamento, String distrito,
			String telefonoCli, String nroDocumento, String tipoDocu, double importeTotal, double base, double igv,
			String nomUsuCaja, String direccionemp, String direcplanta, String nrodocInf, String nroEmp,
			double preCon) {
		super();
		this.telefonoPlan = telefonoPlan;
		this.nroAutSunat = nroAutSunat;
		this.empresa = empresa;
		this.nrocomprobante = nrocomprobante;
		this.fechaPago = fechaPago;
		this.tipoDescuento = tipoDescuento;
		this.concepto = concepto;
		this.placa = placa;
		this.nroMotor = nroMotor;
		this.marca = marca;
		this.modelo = modelo;
		this.clase = clase;
		this.clienteDni = clienteDni;
		this.clienteRuc = clienteRuc;
		this.direccion = direccion;
		this.departamento = departamento;
		this.distrito = distrito;
		this.telefonoCli = telefonoCli;
		this.nroDocumento = nroDocumento;
		this.tipoDocu = tipoDocu;
		this.importeTotal = importeTotal;
		this.base = base;
		this.igv = igv;
		this.nomUsuCaja = nomUsuCaja;
		this.direccionemp = direccionemp;
		this.direcplanta = direcplanta;
		this.nrodocInf = nrodocInf;
		this.nroEmp = nroEmp;
		this.preCon = preCon;
	}
	public String getTelefonoPlan() {
		return telefonoPlan;
	}
	public void setTelefonoPlan(String telefonoPlan) {
		this.telefonoPlan = telefonoPlan;
	}
	public String getNroAutSunat() {
		return nroAutSunat;
	}
	public void setNroAutSunat(String nroAutSunat) {
		this.nroAutSunat = nroAutSunat;
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
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getTelefonoCli() {
		return telefonoCli;
	}
	public void setTelefonoCli(String telefonoCli) {
		this.telefonoCli = telefonoCli;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getTipoDocu() {
		return tipoDocu;
	}
	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
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
	public String getNomUsuCaja() {
		return nomUsuCaja;
	}
	public void setNomUsuCaja(String nomUsuCaja) {
		this.nomUsuCaja = nomUsuCaja;
	}
	public String getDireccionemp() {
		return direccionemp;
	}
	public void setDireccionemp(String direccionemp) {
		this.direccionemp = direccionemp;
	}
	public String getDirecplanta() {
		return direcplanta;
	}
	public void setDirecplanta(String direcplanta) {
		this.direcplanta = direcplanta;
	}
	public String getNrodocInf() {
		return nrodocInf;
	}
	public void setNrodocInf(String nrodocInf) {
		this.nrodocInf = nrodocInf;
	}
	public String getNroEmp() {
		return nroEmp;
	}
	public void setNroEmp(String nroEmp) {
		this.nroEmp = nroEmp;
	}
	public double getPreCon() {
		return preCon;
	}
	public void setPreCon(double preCon) {
		this.preCon = preCon;
	}
	
	
}
