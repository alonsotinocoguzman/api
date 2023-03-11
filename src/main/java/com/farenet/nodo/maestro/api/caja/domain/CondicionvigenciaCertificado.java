package com.farenet.nodo.maestro.api.caja.domain;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipocertificado;

import javax.persistence.*;

@Entity
@Table(name = "condicionvigenciacertificado")
public class CondicionvigenciaCertificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	private Tipocertificado tipocertificado;

	private String condicion;

	private int anio;

	@Column(name = "mesvigencia")
	private int mesVigencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipocertificado getTipocertificado() {
		return tipocertificado;
	}

	public void setTipocertificado(Tipocertificado tipocertificado) {
		this.tipocertificado = tipocertificado;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMesVigencia() {
		return mesVigencia;
	}

	public void setMesVigencia(int mesVigencia) {
		this.mesVigencia = mesVigencia;
	}
}
