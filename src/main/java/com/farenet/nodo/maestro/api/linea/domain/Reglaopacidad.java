package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Combustible;

@Entity
@Table(name = "reglaopacidad")
public class Reglaopacidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private int aniofabricacion;

	@Column(name="condicion_anio")
	private String condicionAnio;

	private Boolean estado;

	private float opacidad;

	@Column(name = "opacidadf")
	private float opacidadF;

	//bi-directional many-to-one association to Subnorma
	@ManyToOne
	private Norma norma;

	//bi-directional many-to-one association to Combustible
	@ManyToOne
	private Combustible combustible;

	
	
	public float getOpacidadF() {
		return opacidadF;
	}

	public void setOpacidadF(float opacidadF) {
		this.opacidadF = opacidadF;
	}

	public Reglaopacidad() {
	}

	public int getAniofabricacion() {
		return this.aniofabricacion;
	}

	public void setAniofabricacion(int aniofabricacion) {
		this.aniofabricacion = aniofabricacion;
	}

	public String getCondicionAnio() {
		return this.condicionAnio;
	}

	public void setCondicionAnio(String condicionAnio) {
		this.condicionAnio = condicionAnio;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Norma getNorma() {
		return norma;
	}

	public void setNorma(Norma norma) {
		this.norma = norma;
	}

	public Combustible getCombustible() {
		return this.combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	public float getOpacidad() {
		return opacidad;
	}

	public void setOpacidad(float opacidad) {
		this.opacidad = opacidad;
	}

}