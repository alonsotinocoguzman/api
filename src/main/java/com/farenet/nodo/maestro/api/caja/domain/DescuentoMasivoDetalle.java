package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;

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
@Table(name = "descuentomasivodetalle")
public class DescuentoMasivoDetalle extends Auditoria{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ddm_generator")
	@SequenceGenerator(name="ddm_generator", sequenceName = "descuentom_detalle_seq", allocationSize=1)
    
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
	@JoinColumn(name = "descuentomasivo_id")
	private DescuentoMasivo descuentoMasivo;
    
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

    public DescuentoMasivo getDescuentoMasivo() {
		return descuentoMasivo;
	}
	public void setDescuentoMasivo(DescuentoMasivo descuentoMasivo) {
		this.descuentoMasivo = descuentoMasivo;
	}
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
}
