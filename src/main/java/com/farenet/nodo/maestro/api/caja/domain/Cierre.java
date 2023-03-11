package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

@Entity
@Table(name = "cierre")
public class Cierre {

	private String nombre;
	
	//24 horas
	@Column(name = "horamax")
	private String horaMax;

	@Column(name = "cronhora")
	private String cronHora;
	
	@Id
	@Column(length=20)
	private String key;
	
	@ManyToOne()
	private Planta planta;
	
	private Boolean cierrefinal;
	
	private Boolean estado;
	
	
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getCronHora() {
		return cronHora;
	}

	public void setCronHora(String cronHora) {
		this.cronHora = cronHora;
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

	public String getHoraMax() {
		return horaMax;
	}

	public void setHoraMax(String horaMax) {
		this.horaMax = horaMax;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Boolean getCierrefinal() {
		return cierrefinal;
	}

	public void setCierrefinal(Boolean cierrefinal) {
		this.cierrefinal = cierrefinal;
	}

}
