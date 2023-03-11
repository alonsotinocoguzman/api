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

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "descuentomasivo")
public class DescuentoMasivo extends Auditoria{

	@Column(length=150)
	private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dm_generator")
	@SequenceGenerator(name="dm_generator", sequenceName = "descuentom_seq", allocationSize=1)
    
    @Column(name = "id", nullable = false)
    private Long id;
    

    @Column(length = 255, nullable = true)
    private String key;

    @Column(name = "limitemontomaximo")
    private Double limiteMontoMaximo; 
	
    @ManyToOne
    private Persona empresa;

    @ManyToOne()
    private Tipodescuento tipodescuento;
    
    @ManyToOne
    private Persona ejecutivo;

    private Boolean sello =  true;


    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechinicio")
    private Timestamp fechInicio;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechfin")
    private Timestamp fechFin;
    


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "descuentoMasivo")
    List<DescuentoMasivoDetalle> descuentomasivodetalles = new ArrayList();
    


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "descuentoMasivo")
    List<DescuentoMasivoCliente> descuentomasivoclientes = new ArrayList();
    
    
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

	public Persona getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(Persona ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Persona empresa) {
		this.empresa = empresa;
	}

	

	public List<DescuentoMasivoDetalle> getDescuentomasivodetalles() {
		return descuentomasivodetalles;
	}

	public void setDescuentomasivodetalles(List<DescuentoMasivoDetalle> descuentomasivodetalles) {
		this.descuentomasivodetalles = descuentomasivodetalles;
	}

	public List<DescuentoMasivoCliente> getDescuentomasivoclientes() {
		return descuentomasivoclientes;
	}

	public void setDescuentomasivoclientes(List<DescuentoMasivoCliente> descuentomasivoclientes) {
		this.descuentomasivoclientes = descuentomasivoclientes;
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
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
