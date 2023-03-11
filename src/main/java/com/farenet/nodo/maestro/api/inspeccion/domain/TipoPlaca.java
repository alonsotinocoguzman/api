package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;
@Entity
@Immutable
@Table(name="tipoplaca")
public class TipoPlaca {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(nullable=false, length=150)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
