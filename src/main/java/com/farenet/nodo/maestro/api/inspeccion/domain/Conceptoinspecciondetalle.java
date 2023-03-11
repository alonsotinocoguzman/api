package com.farenet.nodo.maestro.api.inspeccion.domain;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "conceptoinspecciondetalle")
public class Conceptoinspecciondetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private double valor;

	@ManyToOne()
	private Planta planta;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Conceptoinspeccion conceptoinspeccion;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Conceptoinspeccion getConceptoinspeccion() {
		return conceptoinspeccion;
	}

	public void setConceptoinspeccion(Conceptoinspeccion conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}