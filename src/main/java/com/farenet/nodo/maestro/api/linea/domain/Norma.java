package com.farenet.nodo.maestro.api.linea.domain;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;

@Entity
@Table(name = "norma")
public class Norma {

	@Id
	private Long id;

	private String codigovalor;

	private String nombrevalor;
	
	private boolean estado = true;

	//bi-directional many-to-one association to Tipomaquina
	@ManyToOne
	private Tipomaquina tipomaquina;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "norma_subnorma", joinColumns = @JoinColumn(name = "norma_id"), inverseJoinColumns = @JoinColumn(name = "subnormas_id"))
	private  List<Subnorma> subnormas;
	
	public Norma() {
	}


	public List<Subnorma> getSubnormas() {
		return subnormas;
	}


	public void setSubnormas(List<Subnorma> subnormas) {
		this.subnormas = subnormas;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigovalor() {
		return this.codigovalor;
	}

	public void setCodigovalor(String codigovalor) {
		this.codigovalor = codigovalor;
	}

	public String getNombrevalor() {
		return this.nombrevalor;
	}

	public void setNombrevalor(String nombrevalor) {
		this.nombrevalor = nombrevalor;
	}

	public Tipomaquina getTipomaquina() {
		return this.tipomaquina;
	}

	public void setTipomaquina(Tipomaquina tipomaquina) {
		this.tipomaquina = tipomaquina;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}