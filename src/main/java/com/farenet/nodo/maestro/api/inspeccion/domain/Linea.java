package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;

@Entity
@Table(name = "linea")
public class Linea  {

	public enum PESO_LINEA {
		LIGERO, PESADO, MIXTA
	}

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(nullable = true, length=100)
	private String nombre;

	@ManyToOne()
	@JoinColumn(updatable= false)
	private Planta planta;

	
	@Column
	private PESO_LINEA tipo;

	//bi-directional many-to-one association to Comprobante
	@OneToMany(mappedBy="linea")
	private List<Comprobante> comprobantes;
	
	@Id
	@Column(length=40)
	private String key;
	
	

	public PESO_LINEA getTipo() {
		return tipo;
	}

	public void setTipo(PESO_LINEA tipo) {
		this.tipo = tipo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Linea() {
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return this.planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public List<Comprobante> getComprobantes() {
		return this.comprobantes;
	}

	public void setComprobantes(List<Comprobante> comprobantes) {
		this.comprobantes = comprobantes;
	}

	public Comprobante addComprobante(Comprobante comprobante) {
		getComprobantes().add(comprobante);
		comprobante.setLinea(this);

		return comprobante;
	}

	public Comprobante removeComprobante(Comprobante comprobante) {
		getComprobantes().remove(comprobante);
		comprobante.setLinea(null);

		return comprobante;
	}

}