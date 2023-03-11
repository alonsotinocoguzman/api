package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "descuentocliente")
public class DescuentoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dc_generator")
	@SequenceGenerator(name="dc_generator", sequenceName = "descuento_cliente_seq", allocationSize=1)
    @Column(name = "id", nullable = false)
	private Long id;
	
	private String placa;
	
	private String uuid;

	@Column(name = "diasempieza")
	private Long diasEmpieza;
	
	private Long maxinspecciones;
	
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(name = "fechinicio")
    private Timestamp fechInicio;

    private Boolean estado = true;
	
    @ElementCollection
	private List<String> inspecciones;
	
	
	
	public DescuentoCliente() {
		super();
	}

	public DescuentoCliente(Long id, String placa, String uuid, Long diasEmpieza, Long maxinspecciones,
			Timestamp fechInicio, Boolean estado, List<String> inspecciones) {
		super();
		this.id = id;
		this.placa = placa;
		this.uuid = uuid;
		this.diasEmpieza = diasEmpieza;
		this.maxinspecciones = maxinspecciones;
		this.fechInicio = fechInicio;
		this.estado = estado;
		this.inspecciones = inspecciones;
	}

	public List<String> getInspecciones() {
		return inspecciones;
	}

	public void setInspecciones(List<String> inspecciones) {
		this.inspecciones = inspecciones;
	}

	public Long getMaxinspecciones() {
		return maxinspecciones;
	}

	public void setMaxinspecciones(Long maxinspecciones) {
		this.maxinspecciones = maxinspecciones;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getDiasEmpieza() {
		return diasEmpieza;
	}

	public void setDiasEmpieza(Long diasEmpieza) {
		this.diasEmpieza = diasEmpieza;
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
    
    
    
}
