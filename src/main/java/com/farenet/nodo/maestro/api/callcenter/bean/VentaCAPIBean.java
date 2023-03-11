package com.farenet.nodo.maestro.api.callcenter.bean;

import com.farenet.nodo.maestro.api.caja.domain.Campania;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;

public class VentaCAPIBean {

	private String placa;
	private String idcampana;
	private String idconcepto;
	private double totalSinDesc;
	private double totalDesc;
	private double baseImponible;
	private double igv;
	private double importeTotal;
	private Conceptoinspeccion conceptoInspeccion;
	private Campania campania;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getIdcampana() {
		return idcampana;
	}
	public void setIdcampana(String idcampana) {
		this.idcampana = idcampana;
	}
	public String getIdconcepto() {
		return idconcepto;
	}
	public void setIdconcepto(String idconcepto) {
		this.idconcepto = idconcepto;
	}
	public double getTotalSinDesc() {
		return totalSinDesc;
	}
	public void setTotalSinDesc(double totalSinDesc) {
		this.totalSinDesc = totalSinDesc;
	}
	public double getTotalDesc() {
		return totalDesc;
	}
	public void setTotalDesc(double totalDesc) {
		this.totalDesc = totalDesc;
	}
	public double getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(double baseImponible) {
		this.baseImponible = baseImponible;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public Conceptoinspeccion getConceptoInspeccion() {
		return conceptoInspeccion;
	}
	public void setConceptoInspeccion(Conceptoinspeccion conceptoInspeccion) {
		this.conceptoInspeccion = conceptoInspeccion;
	}
	public Campania getCampania() {
		return campania;
	}
	public void setCampania(Campania campania) {
		this.campania = campania;
	}
	
}
