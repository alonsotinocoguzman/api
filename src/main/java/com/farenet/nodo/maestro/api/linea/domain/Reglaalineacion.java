package com.farenet.nodo.maestro.api.linea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reglaalineacion")
public class Reglaalineacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private String condicion1;

	private String condicion2;

	private Boolean estado;

	private float valor1;

	private float valor2;
	
	//valores farenet

	private float valor1F;

	private float valor2F;
	

	//bi-directional many-to-one association to Subnorma
	@ManyToOne
	private Norma norma;

	public Reglaalineacion() {
	}

	
	public float getValor1F() {
		return valor1F;
	}


	public void setValor1F(float valor1f) {
		valor1F = valor1f;
	}


	public float getValor2F() {
		return valor2F;
	}


	public void setValor2F(float valor2f) {
		valor2F = valor2f;
	}


	public String getCondicion1() {
		return this.condicion1;
	}

	public void setCondicion1(String condicion1) {
		this.condicion1 = condicion1;
	}

	public String getCondicion2() {
		return this.condicion2;
	}

	public void setCondicion2(String condicion2) {
		this.condicion2 = condicion2;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public float getValor1() {
		return this.valor1;
	}

	public void setValor1(float valor1) {
		this.valor1 = valor1;
	}

	public float getValor2() {
		return this.valor2;
	}

	public void setValor2(float valor2) {
		this.valor2 = valor2;
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