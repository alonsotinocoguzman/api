package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "persona")
public class Persona extends com.farenet.nodo.maestro.api.util.Persona {
	@Id
	@Column(nullable = false, length = 20)
	private String nrodocumentoidentidad;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean sameSame = false;

		if (obj != null && obj instanceof Persona) {
			sameSame = nrodocumentoidentidad.equals(((Persona) obj).nrodocumentoidentidad);
		}

		return sameSame;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		if (nrodocumentoidentidad != null) {
			return nrodocumentoidentidad.hashCode();
		} else {
			return super.hashCode();
		}
	}

	public String getNrodocumentoidentidad() {
		return nrodocumentoidentidad;
	}

	public void setNrodocumentoidentidad(String nrodocumentoidentidad) {
		this.nrodocumentoidentidad = nrodocumentoidentidad;
	}
	
	
}
