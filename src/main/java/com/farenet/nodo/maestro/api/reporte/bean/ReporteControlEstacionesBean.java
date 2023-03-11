package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteControlEstacionesBean {

	private Date fechapago;
	private String fechIni;
	private String fechFin;
	private String prueba;
	private String codInspeccion;
	private Boolean manual;
	private String placa;
	private Date fechConsolidacion;
	public ReporteControlEstacionesBean(Date fechapago, String fechIni, String fechFin, String prueba, String codInspeccion,
			Boolean manual, String placa, Date fechConsolidacion) {
		super();
		this.fechapago = fechapago;
		this.fechIni = fechIni;
		this.fechFin = fechFin;
		this.prueba = prueba;
		this.codInspeccion = codInspeccion;
		this.manual = manual;
		this.placa = placa;
		this.fechConsolidacion = fechConsolidacion;
	}
	public Date getFechapago() {
		return fechapago;
	}
	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}
	public String getFechIni() {
		return fechIni;
	}
	public void setFechIni(String fechIni) {
		this.fechIni = fechIni;
	}
	public String getFechFin() {
		return fechFin;
	}
	public void setFechFin(String fechFin) {
		this.fechFin = fechFin;
	}
	public String getPrueba() {
		return prueba;
	}
	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}
	public String getCodInspeccion() {
		return codInspeccion;
	}
	public void setCodInspeccion(String codInspeccion) {
		this.codInspeccion = codInspeccion;
	}
	public Boolean getManual() {
		return manual;
	}
	public void setManual(Boolean manual) {
		this.manual = manual;
	}
	public Date getFechConsolidacion() {
		return fechConsolidacion;
	}
	public void setFechConsolidacion(Date fechConsolidacion) {
		this.fechConsolidacion = fechConsolidacion;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
