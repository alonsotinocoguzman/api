package com.farenet.nodo.maestro.api.sunat.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "st_notadecredito")
public class ST_NotaDeCredito {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "st_generator")
	@SequenceGenerator(name="st_generator", sequenceName = "st_seq", allocationSize=1)
    private Long id;
	@ManyToOne
	@JsonIgnore
	private ST_Carga st_carga;

	@Column(name = "serieref")
	private String serieRef;

	@Column(name = "correlativoref")
	private Long correlativoRef;

	@Column(name = "fecharef")
	private String fechaRef;

	@Column(name = "nrorucempresa")
	private String nroRucEmpresa;

	private String sustento;
	private String tipo;

	@Column(name = "importenc")
	private Double importeNC;

	private String nrodocumentoinspeccion;
	private Date fechcreacion = new Date();
	private Boolean sendedtooffisis = false;
	private String nrocomprobante;

	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ST_Carga getSt_carga() {
		return st_carga;
	}
	public void setSt_carga(ST_Carga st_carga) {
		this.st_carga = st_carga;
	}
	public String getSerieRef() {
		return serieRef;
	}
	public void setSerieRef(String serieRef) {
		this.serieRef = serieRef;
	}
	public Long getCorrelativoRef() {
		return correlativoRef;
	}
	public void setCorrelativoRef(Long correlativoRef) {
		this.correlativoRef = correlativoRef;
	}
	public String getFechaRef() {
		return fechaRef;
	}
	public void setFechaRef(String fechaRef) {
		this.fechaRef = fechaRef;
	}
	public String getNroRucEmpresa() {
		return nroRucEmpresa;
	}
	public void setNroRucEmpresa(String nroRucEmpresa) {
		this.nroRucEmpresa = nroRucEmpresa;
	}
	public String getSustento() {
		return sustento;
	}
	public void setSustento(String sustento) {
		this.sustento = sustento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getImporteNC() {
		return importeNC;
	}
	public void setImporteNC(Double importeNC) {
		this.importeNC = importeNC;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public Date getFechcreacion() {
		return fechcreacion;
	}
	public void setFechcreacion(Date fechcreacion) {
		this.fechcreacion = fechcreacion;
	}
	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}
	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}
	public ST_NotaDeCredito() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ST_NotaDeCredito(Long id, ST_Carga st_carga, String serieRef, Long correlativoRef, String fechaRef,
			String nroRucEmpresa, String sustento, String tipo, Double importeNC, String nrodocumentoinspeccion,
			Date fechcreacion, Boolean sendedtooffisis) {
		super();
		this.id = id;
		this.st_carga = st_carga;
		this.serieRef = serieRef;
		this.correlativoRef = correlativoRef;
		this.fechaRef = fechaRef;
		this.nroRucEmpresa = nroRucEmpresa;
		this.sustento = sustento;
		this.tipo = tipo;
		this.importeNC = importeNC;
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.fechcreacion = fechcreacion;
		this.sendedtooffisis = sendedtooffisis;
	}
	public ST_NotaDeCredito(String serieRef,Long correlativoRef,String fechaRef, String nroRucEmpresa, String sustento, String tipo, Double importeNC,String nrodocumentoinspeccion, Date fechcreacion, String nrocomprobante) {
		super();
		this.serieRef=serieRef;
		this.correlativoRef=correlativoRef;
		this.fechaRef=fechaRef;
		this.nroRucEmpresa=nroRucEmpresa;
		this.sustento=sustento;
		this.tipo=tipo;
		this.importeNC=importeNC;
		this.nrodocumentoinspeccion=nrodocumentoinspeccion;
		this.fechcreacion=fechcreacion;
		this.nrocomprobante=nrocomprobante;
		// TODO Auto-generated constructor stub
	}
}
