package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;

@Entity
@Table(name = "reglaprofundidad")
public class Reglaprofundidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	private Boolean estado;

	private float profundidad;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	private Categoria categoria;

	//bi-directional many-to-one association to Subnorma
	@ManyToOne
	private Norma norma;

	public Reglaprofundidad() {
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public float getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(float profundidad) {
		this.profundidad = profundidad;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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