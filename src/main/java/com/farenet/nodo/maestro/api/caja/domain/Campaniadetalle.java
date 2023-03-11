package com.farenet.nodo.maestro.api.caja.domain;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "campaniadetalle")
public class Campaniadetalle {

	public enum TipoDescuento {
		MONTO, PORCENTAJE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	@JsonIgnore
	private Long id;
	
	@ManyToOne
	private Conceptoinspeccion conceptoinspeccion;

	@Column(name = "tipodescuento")
	private TipoDescuento tipoDescuento;
	
	@Column(name = "montoflat")
	private double montoFlat;
	
	@Column(name = "flatactivado")
	private boolean flatActivado = false;

	@Column(name = "valordescuento")
	private double valorDescuento;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Campania campania;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conceptoinspeccion getConceptoinspeccion() {
		return conceptoinspeccion;
	}

	public void setConceptoinspeccion(Conceptoinspeccion conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}

	public Campania getCampania() {
		return campania;
	}

	public void setCampania(Campania campania) {
		this.campania = campania;
	}

	public double getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public TipoDescuento getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(TipoDescuento tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public double getMontoFlat() {
		return montoFlat;
	}

	public void setMontoFlat(double montoFlat) {
		this.montoFlat = montoFlat;
	}

	public boolean isFlatActivado() {
		return flatActivado;
	}

	public void setFlatActivado(boolean flatActivado) {
		this.flatActivado = flatActivado;
	}
}