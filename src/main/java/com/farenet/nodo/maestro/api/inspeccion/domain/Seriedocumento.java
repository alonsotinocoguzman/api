package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.util.Auditoria;

@Entity
@Table(name = "seriedocumento")
public class Seriedocumento extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne()
	private Planta planta;

	@ManyToOne()
	private Linea linea;

	private Long nroinicio;

	private Long nromaximo;

	private Long nroactual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Long getNroinicio() {
		return nroinicio;
	}

	public void setNroinicio(Long nroinicio) {
		this.nroinicio = nroinicio;
	}

	public Long getNromaximo() {
		return nromaximo;
	}

	public void setNromaximo(Long nromaximo) {
		this.nromaximo = nromaximo;
	}

	public Long getNroactual() {
		return nroactual;
	}

	public void setNroactual(Long nroactual) {
		this.nroactual = nroactual;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	
}