package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.farenet.nodo.maestro.api.inspeccion.domain.TipoConvenio;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "descuento")
public class Descuento extends Auditoria{

	@Column(length=150)
	private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "d_generator")
	@SequenceGenerator(name="d_generator", sequenceName = "descuento_seq", allocationSize=1)
    
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "limitemontomaximo")
    private Double limiteMontoMaximo; 
	
    @ManyToOne
    private Persona empresa;

    @ManyToOne()
    private Tipodescuento tipodescuento;
    
    @ManyToOne
    private Ejecutivo ejecutivo;
    
    private Boolean sello =  true;

	@Column(name = "porruc")
	private Boolean porRuc;

	@Column(name = "porplaca")
	private Boolean porPlaca;

    @Column(name = "facturaconsolidado")
    private Boolean facturaConsolidado = false;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechinicio")
    private Timestamp fechInicio;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechfin")
    private Timestamp fechFin;
    


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "descuento")
    List<DescuentoDetalle> descuentodetalles = new ArrayList();
    
    @Column(length = 50, nullable = true)
    private String ordenservicio;

	@Column(length=15, nullable = true)
	private String diascredito;
	@Column(length=15, nullable = true)
	private String nrocuotas;

	@Column(length=500, nullable = true)
	private String descripcion;

	@ManyToOne()
	private TipoConvenio tipoConvenio;

	public TipoConvenio getTipoConvenio() {
		return tipoConvenio;
	}

	public void setTipoConvenio(TipoConvenio tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}

	public Boolean getPorRuc() {
		return porRuc;
	}

	public Descuento setPorRuc(Boolean porRuc) {
		this.porRuc = porRuc;
		return this;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getPorPlaca() {
		return porPlaca;
	}

	public Descuento setPorPlaca(Boolean porPlaca) {
		this.porPlaca = porPlaca;
		return this;
	}

	public String getDiascredito() {
		return diascredito;
	}

	public Descuento setDiascredito(String diascredito) {
		this.diascredito = diascredito;
		return this;
	}

	public String getNrocuotas() {
		return nrocuotas;
	}

	public Descuento setNrocuotas(String nrocuotas) {
		this.nrocuotas = nrocuotas;
		return this;
	}

	public Boolean getFacturaConsolidado() {
		return facturaConsolidado;
	}

	public void setFacturaConsolidado(Boolean facturaConsolidado) {
		this.facturaConsolidado = facturaConsolidado;
	}

	public String getOrdenservicio() {
		return ordenservicio;
	}

	public void setOrdenservicio(String ordenservicio) {
		this.ordenservicio = ordenservicio;
	}

	public Boolean getSello() {
		return sello;
	}

	public void setSello(Boolean sello) {
		this.sello = sello;
	}

	public Double getLimiteMontoMaximo() {
		return limiteMontoMaximo;
	}

	public void setLimiteMontoMaximo(Double limiteMontoMaximo) {
		this.limiteMontoMaximo = limiteMontoMaximo;
	}
	
	public Ejecutivo getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(Ejecutivo ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Persona empresa) {
		this.empresa = empresa;
	}

	public List<DescuentoDetalle> getDescuentodetalles() {
		return descuentodetalles;
	}

	public void setDescuentodetalles(List<DescuentoDetalle> descuentodetalles) {
		this.descuentodetalles = descuentodetalles;
	}

	public Tipodescuento getTipodescuento() {
		return tipodescuento;
	}

	public void setTipodescuento(Tipodescuento tipodescuento) {
		this.tipodescuento = tipodescuento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
    
    
	
}
