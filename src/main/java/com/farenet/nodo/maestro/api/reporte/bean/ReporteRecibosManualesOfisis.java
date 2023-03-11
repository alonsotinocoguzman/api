package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ReporteRecibosManualesOfisis {

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fechaEmision;
	private String rucDni;
	private String razonSocial;
	private String tipo;
	private String serie;
	private Long numeroDocumento;
	private String tipoMoneda;
	private Double montoAfecto;
	private Double igv;
	private Double montoTotal;
	private String centroCosto;
	private String estado;
	private Integer cuentaContable;
	private Integer cuentaContablePago;
	private String planta;
	private Long correlativo;
	private String formapago;
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public Long getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(Long correlativo) {
		this.correlativo = correlativo;
	}
	public ReporteRecibosManualesOfisis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReporteRecibosManualesOfisis(Date fechaEmision, String rucDni, String razonSocial, String tipo, String serie,
			Long numeroDocumento, String tipoMoneda, Double montoAfecto, Double igv, Double montoTotal,
			String centroCosto, String estado, Integer cuentaContable, Integer cuentaContablePago, String planta,
			Long correlativo, String formapago) {
		super();
		this.fechaEmision = fechaEmision;
		this.rucDni = rucDni;
		this.razonSocial = razonSocial;
		this.tipo = tipo;
		this.serie = serie;
		this.numeroDocumento = numeroDocumento;
		this.tipoMoneda = tipoMoneda;
		this.montoAfecto = montoAfecto;
		this.igv = igv;
		this.montoTotal = montoTotal;
		this.centroCosto = centroCosto;
		this.estado = estado;
		this.cuentaContable = cuentaContable;
		this.cuentaContablePago = cuentaContablePago;
		this.planta = planta;
		this.correlativo = correlativo;
		this.formapago = formapago;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getRucDni() {
		return rucDni;
	}
	public void setRucDni(String rucDni) {
		this.rucDni = rucDni;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	public Double getMontoAfecto() {
		return montoAfecto;
	}
	public void setMontoAfecto(Double montoAfecto) {
		this.montoAfecto = montoAfecto;
	}
	public Double getIgv() {
		return igv;
	}
	public void setIgv(Double igv) {
		this.igv = igv;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCuentaContable() {
		return cuentaContable;
	}
	public void setCuentaContable(Integer cuentaContable) {
		this.cuentaContable = cuentaContable;
	}
	public Integer getCuentaContablePago() {
		return cuentaContablePago;
	}
	public void setCuentaContablePago(Integer cuentaContablePago) {
		this.cuentaContablePago = cuentaContablePago;
	}

}
