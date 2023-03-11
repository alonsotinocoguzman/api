package com.farenet.nodo.maestro.api.seguridad.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "perfil")
public class Perfil {

	@Column(name = "clave", nullable = false)
	@Id
	public String clave;

	@Column(name = "nombre", nullable = false)
	public String nombre;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "perfil_rol", joinColumns = @JoinColumn(name = "perfil_clave"), inverseJoinColumns = @JoinColumn( name = "roles_clave"))
	@JoinColumn(updatable = false)
	public List<Rol> roles;
	
	@Column(name = "visible")
	public boolean visible;
	
	

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
