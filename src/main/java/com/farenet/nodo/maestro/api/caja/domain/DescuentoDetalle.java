package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "descuentodetalle")
public class DescuentoDetalle extends Auditoria{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dd_generator")
	@SequenceGenerator(name="dd_generator", sequenceName = "descuento_detalle_seq", allocationSize=1)
    
    @Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne()
	private Conceptoinspeccion conceptoinspeccion;
	
	@ManyToOne()
	private Planta planta;
	
	@Column(name = "diasreinspeccion")
	private Long diasReinspeccion;
	
	@ManyToOne()
	@JoinColumn(name = "tipopagodescuento_key")
	private TipoPagoDescuento tipoPagoDescuento;
	
	@Column(nullable = true, name = "montoactivacion")
	private Double montoActivacion;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Descuento descuento;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "descuentodetalle_descuentocliente", joinColumns = @JoinColumn(name = "descuentodetalle_id"), inverseJoinColumns = @JoinColumn( name = "descuentoclientes_id"))
    List<DescuentoCliente> descuentoclientes = new ArrayList();
    
    @ManyToOne()
	@JoinColumn(name = "formapago_key")
    private Formapago formaPago;
    
    
	@Column(nullable = true)
	private Double monto;
	
	private String regla;
	

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechinicio")
    private Timestamp fechInicio;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechfin")
    private Timestamp fechFin;

	public Long getDiasReinspeccion() {
		return diasReinspeccion;
	}


	public void setDiasReinspeccion(Long diasReinspeccion) {
		this.diasReinspeccion = diasReinspeccion;
	}
	
	public Formapago getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(Formapago formaPago) {
		this.formaPago = formaPago;
	}


	public Double getMontoActivacion() {
		return montoActivacion;
	}


	public void setMontoActivacion(Double montoActivacion) {
		this.montoActivacion = montoActivacion;
	}


	public List<DescuentoCliente> getDescuentoclientes() {
		return descuentoclientes;
	}


	public void setDescuentoclientes(List<DescuentoCliente> descuentoclientes) {
		this.descuentoclientes = descuentoclientes;
	}


	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public String getRegla() {
		return regla;
	}


	public void setRegla(String regla) {
		this.regla = regla;
	}


	public Timestamp getFechInicio() {
		return fechInicio;
	}


	public void setFechInicio(Timestamp fechInicio) {
		this.fechInicio = fechInicio;
	}


	public Timestamp getFechFin() {
		return fechFin;
	}


	public void setFechFin(Timestamp fechFin) {
		this.fechFin = fechFin;
	}


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


	public Planta getPlanta() {
		return planta;
	}


	public void setPlanta(Planta planta) {
		this.planta = planta;
	}



	public TipoPagoDescuento getTipoPagoDescuento() {
		return tipoPagoDescuento;
	}


	public void setTipoPagoDescuento(TipoPagoDescuento tipoPagoDescuento) {
		this.tipoPagoDescuento = tipoPagoDescuento;
	}


	public Descuento getDescuento() {
		return descuento;
	}


	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	

}
