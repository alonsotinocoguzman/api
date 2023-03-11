package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Immutable
@Table(name = "planta")
public class Planta {

	@Column(nullable = false, length=150)
	private String nombre;
	
	@Column(length=500)
	private String direccion;
	
	@Id
	@Column(length=20)
	private String key;
	
	@Column(length=20, name = "keymtc")
	private String keyMtc;
	
	@ManyToOne
	private Empresa empresa;
	
	@Column(length=20, name = "codigolocalmtc")
	private String codigoLocalMTC;
	
	@ManyToOne
	@JoinColumn(name = "empresacertificadora_key")
	private Empresa empresaCertificadora; 

	@Column(length=500, name = "codigoentidadcertificadoramtc")
	private String codigoEntidadCertificadoraMTC;
	
	//ofisis
	@Column(name = "cuentacajaefectivoofisis")
	private String cuentaCajaEfectivoOFISIS;
	
	private String telefono;

	private String iv;
	private String codlocalsunat;

	public String getCodlocalsunat() {
		return codlocalsunat;
	}

	public Planta setCodlocalsunat(String codlocalsunat) {
		this.codlocalsunat = codlocalsunat;
		return this;
	}

	public String getIv() {
			return iv;
		}

		public void setIv(String iv) {
			this.iv = iv;
		}

	

	public Empresa getEmpresaCertificadora() {
		return empresaCertificadora;
	}

	public void setEmpresaCertificadora(Empresa empresaCertificadora) {
		this.empresaCertificadora = empresaCertificadora;
	}

	public String getKeyMtc() {
		return keyMtc;
	}

	public void setKeyMtc(String keyMtc) {
		this.keyMtc = keyMtc;
	}

	public String getCodigoEntidadCertificadoraMTC() {
		return codigoEntidadCertificadoraMTC;
	}

	public void setCodigoEntidadCertificadoraMTC(String codigoEntidadCertificadoraMTC) {
		this.codigoEntidadCertificadoraMTC = codigoEntidadCertificadoraMTC;
	}

	public String getCuentaCajaEfectivoOFISIS() {
		return cuentaCajaEfectivoOFISIS;
	}

	public void setCuentaCajaEfectivoOFISIS(String cuentaCajaEfectivoOFISIS) {
		this.cuentaCajaEfectivoOFISIS = cuentaCajaEfectivoOFISIS;
	}
	
	public String getCodigoLocalMTC() {
		return codigoLocalMTC;
	}

	public void setCodigoLocalMTC(String codigoLocalMTC) {
		this.codigoLocalMTC = codigoLocalMTC;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}