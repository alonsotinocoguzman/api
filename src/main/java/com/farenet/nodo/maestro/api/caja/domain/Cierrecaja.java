package com.farenet.nodo.maestro.api.caja.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "cierrecaja")
public class Cierrecaja extends Auditoria {

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@ManyToOne()
	private Cierre cierre;

	
	private String observacion;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cierrecaja", cascade= {CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval= true)
	private List<CierrecajaDetalle> cierrecajadetalle;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cierrecaja", cascade= {CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval= true)
	private List<CierrecajaDeposito> cierrecajadeposito;

	@ManyToOne()
	private Planta planta;

	private Boolean sendedtooffisis = false;


	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}

	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public List<CierrecajaDeposito> getCierrecajadeposito() {
		return cierrecajadeposito;
	}

	public void setCierrecajadeposito(List<CierrecajaDeposito> cierrecajadeposito) {
		this.cierrecajadeposito = cierrecajadeposito;
	}

	public List<CierrecajaDetalle> getCierrecajadetalle() {
		return cierrecajadetalle;
	}

	public void setCierrecajadetalle(List<CierrecajaDetalle> cierrecajadetalle) {
		this.cierrecajadetalle = cierrecajadetalle;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cierre getCierre() {
		return cierre;
	}

	public void setCierre(Cierre cierre) {
		this.cierre = cierre;
	}

	@JsonIgnore
	public Double getMontoTransferido() {
		Double monto = 0d;

		for (CierrecajaDeposito deposito : cierrecajadeposito) {
			monto += deposito.getMonto();
		}

		return monto;

	}

}
