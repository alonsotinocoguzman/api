package com.farenet.nodo.maestro.api.inspeccion.domain;

import java.sql.Timestamp;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "certificado_error")
public class CertificadoError {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(nullable = true)
	@JsonSerialize(using = JsonDateSerializer.class) 
	@JsonDeserialize(using = JsonDateDeserializer.class) 
	private Timestamp fechacreacion; 
	
	@Column(length = 50, name = "nrohojavalorada")
	private String nrohojaValorada;

	@Column(name = "tipoerror")
	private String tipoError;

	@Column(name = "descripcionerror")
	private String descripcionError;
	
	@ManyToOne()
	private Usuario usuarioanulacion;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "certificado_nrodocumentocertificado")
	private Certificado certificado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getNrohojaValorada() {
		return nrohojaValorada;
	}

	public void setNrohojaValorada(String nrohojaValorada) {
		this.nrohojaValorada = nrohojaValorada;
	}

	public String getTipoError() {
		return tipoError;
	}

	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public Usuario getUsuarioanulacion() {
		return usuarioanulacion;
	}

	public void setUsuarioanulacion(Usuario usuarioanulacion) {
		this.usuarioanulacion = usuarioanulacion;
	}

	public Certificado getCertificado() {
		return certificado;
	}

	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}
	
	
}
