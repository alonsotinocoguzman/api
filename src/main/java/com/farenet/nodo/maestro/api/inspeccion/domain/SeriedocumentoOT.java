package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.farenet.nodo.maestro.api.linea.domain.Empresa;

@Entity
@Table(name = "seriedocumentoOT")
public class SeriedocumentoOT {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne()
	private Empresa empresa;
	
	private String seriedoc;

	@Column(name = "correlativodoc")
	private Long correlativoDoc;
	
	private String serieot;

	@Column(name = "correlativoot")
	private Long correlativoOT;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getSeriedoc() {
		return seriedoc;
	}
	public void setSeriedoc(String seriedoc) {
		this.seriedoc = seriedoc;
	}
	public Long getCorrelativoDoc() {
		return correlativoDoc;
	}
	public void setCorrelativoDoc(Long correlativoDoc) {
		this.correlativoDoc = correlativoDoc;
	}
	public String getSerieot() {
		return serieot;
	}
	public void setSerieot(String serieot) {
		this.serieot = serieot;
	}
	public Long getCorrelativoOT() {
		return correlativoOT;
	}
	public void setCorrelativoOT(Long correlativoOT) {
		this.correlativoOT = correlativoOT;
	}

	
	
}
