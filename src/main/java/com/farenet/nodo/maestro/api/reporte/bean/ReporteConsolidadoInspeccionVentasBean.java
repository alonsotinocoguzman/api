package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

public class ReporteConsolidadoInspeccionVentasBean {
	private String sede;
	private int anioactual;
	private int anioant;
	private String sede_key;
	private int proc;
	private Long fact;
	private double ingresos_dia;
	private double p1_desaprobado;
	private int ea;
	private int p1_anioactual;
	private int ingresos_anioant;
	private int p2_anioactual;
	private double eaDinero;
	public ReporteConsolidadoInspeccionVentasBean(String sede, int anioactual, int anioant, String sede_key, int proc,
			Long fact, double ingresos_dia, double p1_desaprobado, int ea, int p1_anioactual, int ingresos_anioant,
			int p2_anioactual,double eaDinero) {
		super();
		this.sede = sede;
		this.anioactual = anioactual;
		this.anioant = anioant;
		this.sede_key = sede_key;
		this.proc = proc;
		this.fact = fact;
		this.ingresos_dia = ingresos_dia;
		this.p1_desaprobado = p1_desaprobado;
		this.ea = ea;
		this.p1_anioactual = p1_anioactual;
		this.ingresos_anioant = ingresos_anioant;
		this.p2_anioactual = p2_anioactual;
		this.eaDinero = eaDinero;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public int getAnioactual() {
		return anioactual;
	}
	public void setAnioactual(int anioactual) {
		this.anioactual = anioactual;
	}
	public int getAnioant() {
		return anioant;
	}
	public void setAnioant(int anioant) {
		this.anioant = anioant;
	}
	public String getSede_key() {
		return sede_key;
	}
	public void setSede_key(String sede_key) {
		this.sede_key = sede_key;
	}
	public int getProc() {
		return proc;
	}
	public void setProc(int proc) {
		this.proc = proc;
	}
	public Long getFact() {
		return fact;
	}
	public void setFact(Long fact) {
		this.fact = fact;
	}

	public double getIngresos_dia() {
		return ingresos_dia;
	}

	public void setIngresos_dia(double ingresos_dia) {
		this.ingresos_dia = ingresos_dia;
	}

	public double getP1_desaprobado() {
		return p1_desaprobado;
	}
	public void setP1_desaprobado(double p1_desaprobado) {
		this.p1_desaprobado = p1_desaprobado;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getP1_anioactual() {
		return p1_anioactual;
	}
	public void setP1_anioactual(int p1_anioactual) {
		this.p1_anioactual = p1_anioactual;
	}
	public int getIngresos_anioant() {
		return ingresos_anioant;
	}
	public void setIngresos_anioant(int ingresos_anioant) {
		this.ingresos_anioant = ingresos_anioant;
	}
	public int getP2_anioactual() {
		return p2_anioactual;
	}
	public void setP2_anioactual(int p2_anioactual) {
		this.p2_anioactual = p2_anioactual;
	}

	public double getEaDinero() {
		return eaDinero;
	}

	public void setEaDinero(double eaDinero) {
		this.eaDinero = eaDinero;
	}
}
