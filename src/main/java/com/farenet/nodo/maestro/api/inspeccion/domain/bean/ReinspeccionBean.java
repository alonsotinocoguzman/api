package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoautorizacion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipocertificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipoinspeccion;

public class ReinspeccionBean {
	
	private Conceptoinspeccion conceptoinspeccion;
	private Categoria categoria;
	private Tipoinspeccion tipoinspeccion;
	private Tipocertificado tipocertificado;
	private Tipoautorizacion tipoautorizacion;
	private Date fechConsolidado;
	private String resultado;
	private Inspeccionestado inspeccionestado;
	public ReinspeccionBean(Conceptoinspeccion conceptoinspeccion, Categoria categoria, Tipoinspeccion tipoinspeccion,
			Tipocertificado tipocertificado, Tipoautorizacion tipoautorizacion, Date fechConsolidado, String resultado,
			Inspeccionestado inspeccionestado) {
		super();
		this.conceptoinspeccion = conceptoinspeccion;
		this.categoria = categoria;
		this.tipoinspeccion = tipoinspeccion;
		this.tipocertificado = tipocertificado;
		this.tipoautorizacion = tipoautorizacion;
		this.fechConsolidado = fechConsolidado;
		this.resultado = resultado;
		this.inspeccionestado = inspeccionestado;
	}
	public Conceptoinspeccion getConceptoinspeccion() {
		return conceptoinspeccion;
	}
	public void setConceptoinspeccion(Conceptoinspeccion conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Tipoinspeccion getTipoinspeccion() {
		return tipoinspeccion;
	}
	public void setTipoinspeccion(Tipoinspeccion tipoinspeccion) {
		this.tipoinspeccion = tipoinspeccion;
	}
	public Tipocertificado getTipocertificado() {
		return tipocertificado;
	}
	public void setTipocertificado(Tipocertificado tipocertificado) {
		this.tipocertificado = tipocertificado;
	}
	public Tipoautorizacion getTipoautorizacion() {
		return tipoautorizacion;
	}
	public void setTipoautorizacion(Tipoautorizacion tipoautorizacion) {
		this.tipoautorizacion = tipoautorizacion;
	}
	public Date getFechConsolidado() {
		return fechConsolidado;
	}
	public void setFechConsolidado(Date fechConsolidado) {
		this.fechConsolidado = fechConsolidado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Inspeccionestado getInspeccionestado() {
		return inspeccionestado;
	}
	public void setInspeccionestado(Inspeccionestado inspeccionestado) {
		this.inspeccionestado = inspeccionestado;
	}
	
}
