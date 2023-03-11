package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "periodoreinspeccion")
public class Periodoreinspeccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne()
	@JoinColumn(updatable= false)
	private Planta planta;

	@Column(name = "tieneporcentajedescuento")
	private boolean tienePorcentajeDescuento;

	@Column(name = "porcentajedescuento")
	private int porcentajeDescuento;
	
	private int dias;

	// D o M
	@Column(length = 1, name = "tipodesaprobado")
	private String tipoDesaprobado;

	public Periodoreinspeccion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Planta getPlanta() {
		return planta;
	}

	
	
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public boolean isTienePorcentajeDescuento() {
		return tienePorcentajeDescuento;
	}

	public void setTienePorcentajeDescuento(boolean tienePorcentajeDescuento) {
		this.tienePorcentajeDescuento = tienePorcentajeDescuento;
	}

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	
	public String getTipoDesaprobado() {
		return tipoDesaprobado;
	}

	public void setTipoDesaprobado(String tipoDesaprobado) {
		this.tipoDesaprobado = tipoDesaprobado;
	}

}
