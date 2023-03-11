package com.farenet.nodo.maestro.api.sunat.domain.bean;

public class ConsultaSunatBean {
	
	private Long idst;
	private String nrodocumentoinspeccion;
	private String nrocomprobante;
	private String estado;
	private String tipo;
	private String rucempresa;
	private String tipodocumento;
	
	
	
	public ConsultaSunatBean() {
		super();
	}

	public ConsultaSunatBean(Long idst, String nrodocumentoinspeccion, String nrocomprobante, String tipo, String estado) {
		super();
		this.idst = idst;
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.nrocomprobante = nrocomprobante;
		this.tipo = tipo;
		this.estado = estado;
	}

	public ConsultaSunatBean(Long idst, String nrodocumentoinspeccion, String nrocomprobante, String tipo, String estado,
			String rucempresa, String tipodocumento) {
		super();
		this.idst = idst;
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		this.nrocomprobante = nrocomprobante;
		this.estado = estado;
		this.tipo = tipo;
		this.rucempresa = rucempresa;
		this.tipodocumento = tipodocumento;
	}
	
	public Long getIdst() {
		return idst;
	}
	public void setIdst(Long idst) {
		this.idst = idst;
	}
	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}
	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}
	public String getNrocomprobante() {
		return nrocomprobante;
	}
	public void setNrocomprobante(String nrocomprobante) {
		this.nrocomprobante = nrocomprobante;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRucempresa() {
		return rucempresa;
	}
	public void setRucempresa(String rucempresa) {
		this.rucempresa = rucempresa;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
