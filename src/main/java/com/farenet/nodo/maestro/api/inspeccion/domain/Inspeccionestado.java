package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@Entity
@Table(name = "inspeccionestado")
public class Inspeccionestado {

	@Id
	@Column(length=20)
	private String key;

	@Column(nullable = true, length=50)
	private String nombre;

	//bi-directional many-to-one association to Inspeccion
	@OneToMany(mappedBy="inspeccionestado")
	private List<Inspeccion> inspeccions;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Inspeccionestado() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inspeccion> getInspeccions() {
		return this.inspeccions;
	}

	public void setInspeccions(List<Inspeccion> inspeccions) {
		this.inspeccions = inspeccions;
	}

	public Inspeccion addInspeccion(Inspeccion inspeccion) {
		getInspeccions().add(inspeccion);
		inspeccion.setInspeccionestado(this);

		return inspeccion;
	}

	public Inspeccion removeInspeccion(Inspeccion inspeccion) {
		getInspeccions().remove(inspeccion);
		inspeccion.setInspeccionestado(null);

		return inspeccion;
	}

}