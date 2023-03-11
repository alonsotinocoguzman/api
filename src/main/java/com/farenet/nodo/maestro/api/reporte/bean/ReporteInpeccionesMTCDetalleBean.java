package com.farenet.nodo.maestro.api.reporte.bean;
import java.util.Date;
public class ReporteInpeccionesMTCDetalleBean {
	public String placamotor;
	public Date fechcreacion;
	public String planta;
	public String conceptoinspeccion;
	public String tipoinspeccion;
	public String tipocertificado;	
	public String tipoautorizacion;
	
	
	public String getPlacamotor() {
		return placamotor;
	}

	public void setPlacamotor(String placamotor) {
		this.placamotor = placamotor;
	}

	public Date getFechcreacion() {
		return fechcreacion;
	}

	public void setFechcreacion(Date fechcreacion) {
		this.fechcreacion = fechcreacion;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	

	public String getConceptoinspeccion() {
		return conceptoinspeccion;
	}

	public void setConceptoinspeccion(String conceptoinspeccion) {
		this.conceptoinspeccion = conceptoinspeccion;
	}

	public String getTipoinspeccion() {
		return tipoinspeccion;
	}

	public void setTipoinspeccion(String tipoinspeccion) {
		this.tipoinspeccion = tipoinspeccion;
	}
	public String getTipocertificado() {
		return tipocertificado;
	}

	public void setTipocertificado(String tipocertificado) {
		this.tipocertificado = tipocertificado;
	}

	public String getTipoautorizacion() {
		return tipoautorizacion;
	}

	public void setTipoautorizacion(String tipoautorizacion) {
		this.tipoautorizacion = tipoautorizacion;
	}

	
	

	public ReporteInpeccionesMTCDetalleBean(String placamotor, Date fechcreacion, String planta, String conceptoinspeccion,
			String tipoinspeccion, String tipocertificado, String tipoautorizacion) {
		super();
		this.placamotor = placamotor;
		this.fechcreacion = fechcreacion;
		this.planta = planta;
		this.conceptoinspeccion = conceptoinspeccion;
		this.tipoinspeccion = tipoinspeccion;
		this.tipocertificado = tipocertificado;
		this.tipoautorizacion = tipoautorizacion;
	}

	public ReporteInpeccionesMTCDetalleBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
