package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "descuentomasivocliente")
public class DescuentoMasivoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dmc_generator")
	@SequenceGenerator(name="dmc_generator", sequenceName = "descuentom_cliente_seq", allocationSize=1)
    @Column(name = "id", nullable = false)
	private Long id;
	
	private String placa;
	
	private String uuid;
	
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Timestamp fechInicio;
    
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Timestamp fechFin;
    
    private Boolean estado = true;
    
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private DescuentoMasivo descuentoMasivo;
	
    @ElementCollection
	private List<String> inspecciones;

	public DescuentoMasivoCliente() {
		super();
	}

	public DescuentoMasivoCliente(Long id, String placa, String uuid, Timestamp fechInicio, Timestamp fechFin,
			Boolean estado, DescuentoMasivo descuentoMasivo, List<String> inspecciones) {
		super();
		this.id = id;
		this.placa = placa;
		this.uuid = uuid;
		this.fechInicio = fechInicio;
		this.fechFin = fechFin;
		this.estado = estado;
		this.descuentoMasivo = descuentoMasivo;
		this.inspecciones = inspecciones;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public DescuentoMasivo getDescuentoMasivo() {
		return descuentoMasivo;
	}
	public void setDescuentoMasivo(DescuentoMasivo descuentoMasivo) {
		this.descuentoMasivo = descuentoMasivo;
	}
	public List<String> getInspecciones() {
		return inspecciones;
	}
	public void setInspecciones(List<String> inspecciones) {
		this.inspecciones = inspecciones;
	}
    
    
}
