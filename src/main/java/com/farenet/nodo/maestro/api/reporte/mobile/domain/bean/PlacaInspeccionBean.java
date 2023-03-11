package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

import java.sql.Timestamp;
import java.util.Date;

public class PlacaInspeccionBean {

	public String nroinspeccion;
	public String planta;
	public Timestamp fechaRevision;
	public String placa;
	public String nrocomprobante;
	public String conceptoInspeccion;
	public String linea;
	public String estadoInspeccion;
	public String resultado;
	public String marca;
	public String modelo;
	public String clienteDni;
	public String clienteRuc;
	public Timestamp fechaVencimiento;
	public String certificado;
	public Date fechaInspeccion;
	public String tipoinspeccion_key;
	public String tipoautorizacion_key;
	public String tipocertificado_key;
	public String firmaing;
	public String nroHojaValorada;
	
	
	
	public PlacaInspeccionBean() {
		super();
	}
	
	public PlacaInspeccionBean(String nroinspeccion, String planta, Date fechaInspeccion, String placa, String nrocomprobante,
			String conceptoInspeccion, String linea, String estadoInspeccion, String resultado, String marca, String modelo,
			String clienteDni, String clienteRuc, String certificado, String tipoinspeccion_key, String tipoautorizacion_key,
			String tipocertificado_key, String firmaing, String nroHojaValorada) {
		super();
		this.nroinspeccion = nroinspeccion;
		this.planta = planta;
		this.fechaInspeccion = fechaInspeccion;
		this.placa = placa;
		this.nrocomprobante = nrocomprobante;
		this.conceptoInspeccion = conceptoInspeccion;
		this.linea = linea;
		this.estadoInspeccion = estadoInspeccion;
		this.resultado = resultado;
		this.marca = marca;
		this.modelo = modelo;
		this.clienteDni = clienteDni;
		this.clienteRuc = clienteRuc;
		this.certificado = certificado;
		this.tipoinspeccion_key = tipoinspeccion_key;
		this.tipoautorizacion_key = tipoautorizacion_key;
		this.tipocertificado_key = tipocertificado_key;
		this.firmaing = firmaing;
		this.nroHojaValorada = nroHojaValorada;
	}

	public PlacaInspeccionBean(String planta, Timestamp fechaRevision, String placa, String nrocomprobante,
			String conceptoInspeccion, String linea, String estadoInspeccion, String resultado, String marca,
			String modelo, String clienteDni, String clienteRuc, Timestamp fechaVencimiento, String certificado) {
		super();
		this.planta = planta;
		this.fechaRevision = fechaRevision;
		this.placa = placa;
		this.nrocomprobante = nrocomprobante;
		this.conceptoInspeccion = conceptoInspeccion;
		this.linea = linea;
		this.estadoInspeccion = estadoInspeccion;
		this.resultado = resultado;
		this.marca = marca;
		this.modelo = modelo;
		this.clienteDni = clienteDni;
		this.clienteRuc = clienteRuc;
		this.fechaVencimiento = fechaVencimiento;
		this.certificado = certificado;
	}
}
