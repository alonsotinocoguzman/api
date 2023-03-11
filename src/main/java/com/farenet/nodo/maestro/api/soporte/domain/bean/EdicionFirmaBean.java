package com.farenet.nodo.maestro.api.soporte.domain.bean;

public class EdicionFirmaBean {
	
	private String nrodocumentoinspeccion;
	private String usuarioing;
	
	public EdicionFirmaBean() {
		super();
	}
	public EdicionFirmaBean(String nrodocumentoinspeccion, String usuarioing) {
		super();
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.usuarioing = usuarioing;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getUsuarioing() {
		return usuarioing;
	}
	public void setUsuarioing(String usuarioing) {
		this.usuarioing = usuarioing;
	}
	
	

}
