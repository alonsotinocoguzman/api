package com.farenet.nodo.maestro.api.caja.domain;

import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "conveniodetalle")
public class Conveniodetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	@JsonIgnore
	private Long id;
	
	@ManyToOne()
	private Conceptoinspeccion conceptoinspeccion;

	@Column(name = "flatactivado")
	private boolean flatActivado = false;

	@Column(name = "tipodescuento")
	private Campaniadetalle.TipoDescuento tipoDescuento;

	@Column(name = "valordescuento")
	private double valorDescuento;

	@Column(name = "montoflat")
	private double montoFlat;
	
	@ManyToOne()
	@JoinColumn(name = "formapago_key")
	private Formapago formaPago;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Convenio convenio;

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

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Formapago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Formapago formaPago) {
		this.formaPago = formaPago;
	}

	public boolean isFlatActivado() {
		return flatActivado;
	}

	public void setFlatActivado(boolean flatActivado) {
		this.flatActivado = flatActivado;
	}

	public double getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public double getMontoFlat() {
		return montoFlat;
	}

	public void setMontoFlat(double montoFlat) {
		this.montoFlat = montoFlat;
	}

	public Campaniadetalle.TipoDescuento getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(Campaniadetalle.TipoDescuento tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
}