package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "comprobante")
@Audited
public class Comprobante extends Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(length = 50)
	private String autorizacionsunat;

	@ManyToOne(cascade = { CascadeType.ALL })
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "ordentrabajo_id")
	private OrdenTrabajo ordenTrabajo;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Comprobanteestado comprobanteestado;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipodocumento tipodocumento;

	@Column(nullable = true)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechanulacion;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechapago;



	@NotNull
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "formapago_key")
	private Formapago formaPago;
	


	private Boolean impreso;

	@NotNull
	@Column(length = 150, name = "placamotor")
	private String placaMotor;



	@Column(length = 75)
	private String nrocomprobante;



	@Column(nullable = true, columnDefinition = "TEXT")
	private String observacion;

	@Column(nullable = true, columnDefinition = "TEXT", name = "observaciondevolucion")
	private String observacionDevolucion;

	@ManyToOne(cascade= CascadeType.ALL)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "descuentocomprobante_id")
	private DescuentoComprobante descuentoComprobante;
	
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipodescuento tipodescuento;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Convenio convenio;
	
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Campania campania;

	@NotNull
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(name = "conceptoinspeccion_key")
	private Conceptoinspeccion conceptoInspeccion;

	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Empresa empresa;

	@NotNull
	@ManyToOne(fetch= FetchType.LAZY,optional = false)
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	
	private Inspeccion inspeccion;

	// subtotal
	private double totalsindscto;

	// descuento
	private double totaldscto;

	// total
	private double importetotal;

	@Column(name = "nroruc")
	private String nroRUC;

	@Column(name = "codigoverificacion")
	private String codigoVerificacion;

	@Column()
	private double baseimponible;

	@Column()
	private double igv;
	
    @Column(length = 50, nullable = true)
	private String ordenservicio;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Usuario usuarioanulacion;

	@ManyToOne()
	private Persona cliente;

	// bi-directional many-to-one association to Linea
	@ManyToOne()
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Linea linea;

	private Boolean sendedtooffisis = false;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "comprobante", orphanRemoval= true)
	private List<Pago> pagos;


	public Boolean duplicado;
	

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public DescuentoComprobante getDescuentoComprobante() {
		return descuentoComprobante;
	}

	public void setDescuentoComprobante(DescuentoComprobante descuentoComprobante) {
		this.descuentoComprobante = descuentoComprobante;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getDuplicado() {
		return duplicado;
	}

	public void setDuplicado(Boolean duplicado) {
		this.duplicado = duplicado;
	}

	public Comprobante() {
	}
	
	public Inspeccion getInspeccion() {
		return inspeccion;
	}

	public void setInspeccion(Inspeccion inspeccion) {
		this.inspeccion = inspeccion;
	}

	public Conceptoinspeccion getConceptoInspeccion() {
		return conceptoInspeccion;
	}

	public void setConceptoInspeccion(Conceptoinspeccion conceptoInspeccion) {
		this.conceptoInspeccion = conceptoInspeccion;
	}
	public Long getId() {
		return this.id;
	}

	public String getPlacaMotor() {
		return placaMotor;
	}

	public void setPlacaMotor(String placaMotor) {
		this.placaMotor = placaMotor;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Persona getCliente() {
		return cliente;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public String getAutorizacionsunat() {
		return this.autorizacionsunat;
	}

	public void setAutorizacionsunat(String autorizacionsunat) {
		this.autorizacionsunat = autorizacionsunat;
	}

	public double getBaseimponible() {
		return baseimponible;
	}

	public void setBaseimponible(double baseimponible) {
		this.baseimponible = baseimponible;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}


	public Comprobanteestado getComprobanteestado() {
		return this.comprobanteestado;
	}

	public void setComprobanteestado(Comprobanteestado comprobanteestado) {
		this.comprobanteestado = comprobanteestado;
	}

	public Timestamp getFechanulacion() {
		return this.fechanulacion;
	}

	public void setFechanulacion(Timestamp fechanulacion) {
		this.fechanulacion = fechanulacion;
	}

	public Timestamp getFechapago() {
		return this.fechapago;
	}

	public void setFechapago(Timestamp fechapago) {
		this.fechapago = fechapago;
	}


	public double getImportetotal() {
		return this.importetotal;
	}

	public void setImportetotal(double importetotal) {
		this.importetotal = importetotal;
	}

	public Boolean getImpreso() {
		return this.impreso;
	}

	public void setImpreso(Boolean impreso) {
		this.impreso = impreso;
	}

	public String getNrocomprobante() {
		return this.nrocomprobante;
	}

	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public double getTotaldscto() {
		return this.totaldscto;
	}

	public void setTotaldscto(double totaldscto) {
		this.totaldscto = totaldscto;
	}

	public double getTotalsindscto() {
		return this.totalsindscto;
	}

	public void setTotalsindscto(double totalsindscto) {
		this.totalsindscto = totalsindscto;
	}

	public Usuario getUsuarioanulacion() {
		return this.usuarioanulacion;
	}

	public void setUsuarioanulacion(Usuario usuarioanulacion) {
		this.usuarioanulacion = usuarioanulacion;
	}

	public Linea getLinea() {
		return this.linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	


	public Formapago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Formapago formaPago) {
		this.formaPago = formaPago;
	}


	public Tipodescuento getTipodescuento() {
		return tipodescuento;
	}

	public void setTipodescuento(Tipodescuento tipodescuento) {
		this.tipodescuento = tipodescuento;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Campania getCampania() {
		return campania;
	}

	public void setCampania(Campania campania) {
		this.campania = campania;
	}

	

	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getObservacionDevolucion() {
		return observacionDevolucion;
	}

	public void setObservacionDevolucion(String observacionDevolucion) {
		this.observacionDevolucion = observacionDevolucion;
	}

	public String getNroRUC() {
		return nroRUC;
	}

	public void setNroRUC(String nroRUC) {
		this.nroRUC = nroRUC;
	}

	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}

	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}

	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}

	public String getOrdenservicio() {
		return ordenservicio;
	}

	public void setOrdenservicio(String ordenservicio) {
		this.ordenservicio = ordenservicio;
	}
	
	
}