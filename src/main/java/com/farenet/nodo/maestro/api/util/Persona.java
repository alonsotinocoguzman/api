package com.farenet.nodo.maestro.api.util;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.farenet.nodo.maestro.api.caja.domain.Departamento;
import com.farenet.nodo.maestro.api.caja.domain.Distrito;
import com.farenet.nodo.maestro.api.caja.domain.Pais;
import com.farenet.nodo.maestro.api.caja.domain.Provincia;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumentoidentidad;
import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;

@Audited
@MappedSuperclass
public class Persona extends Auditoria {

	@Column(nullable = true, length = 500)
	public String nombrerazonsocial;

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(nullable = true, length = 200)
	private String nombres;

	@Column(nullable = true, length = 200)
	private String apellidos;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipodocumentoidentidad tipodocumentoidentidad;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Pais pais;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Departamento departamento;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Provincia provincia;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Distrito distrito;

	@Column(nullable = true, length = 500)
	public String direccion;

	@Column(nullable = true, length = 150)
	public String email;

	@Column(nullable = true, length = 100)
	public String telefono;

	@Column(nullable = true, length = 250)
	public String personaContacto;

	@Override
	public String toString() {
		return getNombres() + " " + getApellidos();
	}

	@JsonIgnore
	 public String getNombrecompleto() {
	        String nombrecompleto = (esEmpresa()) ? nombrerazonsocial : nombres + " " + apellidos;
	        return nombrecompleto;
	    }

	public String getPersonaContacto() {
		return personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getNombrerazonsocial() {
		return nombrerazonsocial;
	}

	public void setNombrerazonsocial(String nombrerazonsocial) {
		this.nombrerazonsocial = nombrerazonsocial;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Tipodocumentoidentidad getTipodocumentoidentidad() {
		return tipodocumentoidentidad;
	}

	public void setTipodocumentoidentidad(Tipodocumentoidentidad tipodocumentoidentidad) {
		this.tipodocumentoidentidad = tipodocumentoidentidad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@JsonIgnore
	public Boolean esEmpresa() {
		boolean esEmpresa = false;
		if (getTipodocumentoidentidad() != null) {
			esEmpresa = (getTipodocumentoidentidad().getKey().toLowerCase().equals("ruc")) ? true : false;

		}
		return esEmpresa;
	}
}
