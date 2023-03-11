package com.farenet.nodo.maestro.api.soporte.domain.bean;

public class DatosCertificadoBean {
	
	private String nrodocumentoinspeccion;
	private String tipocertificado;
	private String tipoinspeccion;
	private String tipoautorizacion;
	public DatosCertificadoBean() {
		super();
	}
	public DatosCertificadoBean(String nrodocumentoinspeccion, String tipocertificado, String tipoinspeccion,
			String tipoautorizacion) {
		super();
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.tipocertificado = tipocertificado;
		this.tipoinspeccion = tipoinspeccion;
		this.tipoautorizacion = tipoautorizacion;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getTipocertificado() {
		return tipocertificado;
	}
	public void setTipocertificado(String tipocertificado) {
		this.tipocertificado = tipocertificado;
	}
	public String getTipoinspeccion() {
		return tipoinspeccion;
	}
	public void setTipoinspeccion(String tipoinspeccion) {
		this.tipoinspeccion = tipoinspeccion;
	}
	public String getTipoautorizacion() {
		return tipoautorizacion;
	}
	public void setTipoautorizacion(String tipoautorizacion) {
		this.tipoautorizacion = tipoautorizacion;
	}
	
	
	
}
