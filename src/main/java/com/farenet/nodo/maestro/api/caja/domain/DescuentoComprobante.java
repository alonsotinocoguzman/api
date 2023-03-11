package com.farenet.nodo.maestro.api.caja.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.TipoPagoDescuento;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "descuentocomprobante")
public class DescuentoComprobante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "iddescuentodetalle")
	private String idDescuentoDetalle;

	@Column(name = "nombredescuento")
	private String nombreDescuento;
	
	@ManyToOne
	@JoinColumn(name = "tipopagodescuento_key")
	private TipoPagoDescuento tipoPagoDescuento;
	
	@ManyToOne
	private Tipodescuento tipodescuento;
	
	@ManyToOne
	@JoinColumn(name = "formapago_key")
	private Formapago formaPago;

	private Double monto;
	private String ruc;
	private String uuid;
	private Boolean sello;
	private String ordenservicio;

	@Column(name = "facturaconsolidado")
	private Boolean facturaConsolidado; 
	
	
	//constructor para retornar servicio descuento
	public DescuentoComprobante(String idDescuentoDetalle, String nombreDescuento, TipoPagoDescuento tipoPagoDescuento, Formapago formaPago,
			Double monto, String ruc, String uuid, Tipodescuento tipodescuento,Boolean sello, String ordenservicio, Boolean facturaConsolidado) {
		super();
		this.idDescuentoDetalle = idDescuentoDetalle;
		this.nombreDescuento = nombreDescuento;
		this.tipoPagoDescuento = tipoPagoDescuento;
		this.formaPago = formaPago;
		this.monto = monto;
		this.ruc = ruc;
		this.uuid = uuid;
		this.tipodescuento = tipodescuento; 
		this.sello = sello;
		this.ordenservicio = ordenservicio;
		this.facturaConsolidado = facturaConsolidado;
	}
	
	public DescuentoComprobante(String idDescuentoDetalle, String nombreDescuento,
			TipoPagoDescuento tipoPagoDescuento, Formapago formaPago, Double monto, String ruc, String uuid, Tipodescuento tipodescuento,Boolean sello) {
		super();
		this.idDescuentoDetalle = idDescuentoDetalle;
		this.nombreDescuento = nombreDescuento;
		this.tipoPagoDescuento = tipoPagoDescuento;
		this.formaPago = formaPago;
		this.monto = monto;
		this.ruc = ruc;
		this.uuid = uuid;
		this.tipodescuento = tipodescuento; 
		this.sello = sello;
	}
	
	public DescuentoComprobante(String idDescuentoDetalle, String nombreDescuento,
			TipoPagoDescuento tipoPagoDescuento, Formapago formaPago, Double monto, String ruc, String uuid, Tipodescuento tipodescuento,Boolean sello,
			String ordenservicio) {
		super();
		this.idDescuentoDetalle = idDescuentoDetalle;
		this.nombreDescuento = nombreDescuento;
		this.tipoPagoDescuento = tipoPagoDescuento;
		this.formaPago = formaPago;
		this.monto = monto;
		this.ruc = ruc;
		this.uuid = uuid;
		this.tipodescuento = tipodescuento; 
		this.sello = sello;
		this.ordenservicio = ordenservicio;
	}

	public DescuentoComprobante(){}

	

	public Boolean getSello() {
		return sello;
	}

	public void setSello(Boolean sello) {
		this.sello = sello;
	}

	public Tipodescuento getTipodescuento() {
		return tipodescuento;
	}

	public void setTipodescuento(Tipodescuento tipodescuento) {
		this.tipodescuento = tipodescuento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdDescuentoDetalle() {
		return idDescuentoDetalle;
	}

	public void setIdDescuentoDetalle(String idDescuentoDetalle) {
		this.idDescuentoDetalle = idDescuentoDetalle;
	}

	public String getNombreDescuento() {
		return nombreDescuento;
	}

	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}

	public TipoPagoDescuento getTipoPagoDescuento() {
		return tipoPagoDescuento;
	}

	public void setTipoPagoDescuento(TipoPagoDescuento tipoPagoDescuento) {
		this.tipoPagoDescuento = tipoPagoDescuento;
	}

	

	public Formapago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Formapago formaPago) {
		this.formaPago = formaPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOrdenservicio() {
		return ordenservicio;
	}

	public void setOrdenservicio(String ordenservicio) {
		this.ordenservicio = ordenservicio;
	}

	public Boolean getFacturaConsolidado() {
		return facturaConsolidado;
	}

	public void setFacturaConsolidado(Boolean facturaConsolidado) {
		this.facturaConsolidado = facturaConsolidado;
	}
	
	
}
