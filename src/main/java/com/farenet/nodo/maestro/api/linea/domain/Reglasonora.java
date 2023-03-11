package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "reglasonora")
public class Reglasonora {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private float decibel;

	private Boolean estado;

	//bi-directional many-to-one association to Subnorma
	@ManyToOne
	private Norma norma;

	public Reglasonora() {
	}

	public float getDecibel() {
		return decibel;
	}

	public void setDecibel(float decibel) {
		this.decibel = decibel;
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



}