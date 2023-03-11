package com.farenet.nodo.maestro.api.inspeccion.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.envers.NotAudited;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "certificado")
public class Certificado extends Auditoria {

	@Id
	@Column(length = 50, name = "nrodocumentocertificado")
	private String nrodocumentoCertificado;

	@Column(length = 50, name = "nrohojavalorada")
	private String nrohojaValorada;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(nullable = true)
	private Timestamp fechanulacion;

	@ManyToOne()
	@JoinColumn(name = "empresacertificadora_key")
	private Empresa empresaCertificadora;

	@Column(nullable = true)
	private Boolean anulado;

	@Column(nullable = true, columnDefinition = "TEXT", name = "observacionanulado")
	private String observacionAnulado;

	@ManyToOne()
	private Usuario usuarioanulacion;

	@ManyToOne
	@JsonIgnore
	private Inspeccion inspeccion;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "certificado", orphanRemoval = true)
	@NotAudited
	private List<CertificadoError> certificadoErrors;

	@Column(nullable = true, columnDefinition = "TEXT")
	private String observacion;

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Empresa getEmpresaCertificadora() {
		return empresaCertificadora;
	}

	public void setEmpresaCertificadora(Empresa empresaCertificadora) {
		this.empresaCertificadora = empresaCertificadora;
	}

	public String getNrodocumentoCertificado() {
		return nrodocumentoCertificado;
	}

	public void setNrodocumentoCertificado(String nrodocumentoCertificado) {
		this.nrodocumentoCertificado = nrodocumentoCertificado;
	}

	public Timestamp getFechanulacion() {
		return fechanulacion;
	}

	public void setFechanulacion(Timestamp fechanulacion) {
		this.fechanulacion = fechanulacion;
	}

	public Usuario getUsuarioanulacion() {
		return usuarioanulacion;
	}

	public void setUsuarioanulacion(Usuario usuarioanulacion) {
		this.usuarioanulacion = usuarioanulacion;
	}

	public Boolean getAnulado() {
		return anulado;
	}

	public void setAnulado(Boolean anulado) {
		this.anulado = anulado;
	}

	public String getObservacionAnulado() {
		return observacionAnulado;
	}

	public void setObservacionAnulado(String observacionAnulado) {
		this.observacionAnulado = observacionAnulado;
	}

	public Inspeccion getInspeccion() {
		return inspeccion;
	}

	public void setInspeccion(Inspeccion inspeccion) {
		this.inspeccion = inspeccion;
	}

	public String getNrohojaValorada() {
		return nrohojaValorada;
	}

	public void setNrohojaValorada(String nrohojaValorada) {
		this.nrohojaValorada = nrohojaValorada;
	}

	public List<CertificadoError> getCertificadoErrors() {
		return certificadoErrors;
	}

	public void setCertificadoErrors(List<CertificadoError> certificadoErrors) {
		this.certificadoErrors = certificadoErrors;
	}

}
