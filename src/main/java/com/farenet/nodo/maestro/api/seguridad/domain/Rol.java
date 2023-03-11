package com.farenet.nodo.maestro.api.seguridad.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

@Entity
@Table(name = "rol")
public class Rol {
	
	@Column(name = "nombre", nullable = false)
	public String nombre;
	
	@Column(name = "clave", nullable = false)
	@Id
	public String clave;
	
	
}
