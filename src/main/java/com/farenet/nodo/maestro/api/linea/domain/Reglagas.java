package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Combustible;

@Entity
@Table(name = "reglagas")
public class Reglagas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private int aniofabricacion;

	@Column(name="condicion_anio")
	private String condicionAnio;

	private Boolean estado;

	private String key;

	private float valor;

	@Column(name="valorf")
	private float valorF;

	//bi-directional many-to-one association to Combustible
	@ManyToOne
	private Combustible combustible;

	//bi-directional many-to-one association to Subnorma
	@ManyToOne
	private Norma norma;

	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public float getValorF() {
		return valorF;
	}

	public void setValorF(float valorF) {
		this.valorF = valorF;
	}

	public Reglagas() {
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

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Combustible getCombustible() {
		return this.combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
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