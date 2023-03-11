package com.farenet.nodo.maestro.api.linea.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import java.util.List;

@Entity
@Table(name = "subnorma")
public class Subnorma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private int anio;

	@Column(name = "condicion_anio")
	private String condicionAnio;

	private Boolean grave;

	private Boolean leve;

	private Boolean manual;

	private Boolean muygrave;

	// bi-directional many-to-one association to Categoria
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "subnorma_categoria", joinColumns = @JoinColumn(name = "subnorma_id"), inverseJoinColumns = @JoinColumn(name = "categorias_key"))
	private List<Categoria> categorias;

	public Subnorma() {
	}

	public String getPeligro() {
		String peligro = "";
		if (leve) {
			peligro = "LEVE";
		} else if (grave) {
			peligro = "GRAVE";
		} else if(muygrave) {
			peligro = "MUY GRAVE";
		}
		return peligro;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getCondicionAnio() {
		return this.condicionAnio;
	}

	public void setCondicionAnio(String condicionAnio) {
		this.condicionAnio = condicionAnio;
	}

	public Boolean getGrave() {
		return this.grave;
	}

	public void setGrave(Boolean grave) {
		this.grave = grave;
	}

	public Boolean getLeve() {
		return this.leve;
	}

	public void setLeve(Boolean leve) {
		this.leve = leve;
	}

	public Boolean getManual() {
		return this.manual;
	}

	public void setManual(Boolean manual) {
		this.manual = manual;
	}

	public Boolean getMuygrave() {
		return this.muygrave;
	}

	public void setMuygrave(Boolean muygrave) {
		this.muygrave = muygrave;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}