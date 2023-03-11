package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

public class InspeccionSupportSedeBean {
	
	private String nroinspeccion;
	private String placa;
	private String nromotor;
	private String nroserie;
	private String codTipoInspeccion;
	private String codTipoCertificado;
	private String codTipoAutorizacion;
	public InspeccionSupportSedeBean(String nroinspeccion, String placa, String nromotor, String nroserie,
			String codTipoInspeccion, String codTipoCertificado, String codTipoAutorizacion) {
		super();
		this.nroinspeccion = nroinspeccion;
		this.placa = placa;
		this.nromotor = nromotor;
		this.nroserie = nroserie;
		this.codTipoInspeccion = codTipoInspeccion;
		this.codTipoCertificado = codTipoCertificado;
		this.codTipoAutorizacion = codTipoAutorizacion;
	}
	public String getNroinspeccion() {
		return nroinspeccion;
	}
	public void setNroinspeccion(String nroinspeccion) {
		this.nroinspeccion = nroinspeccion;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNromotor() {
		return nromotor;
	}
	public void setNromotor(String nromotor) {
		this.nromotor = nromotor;
	}
	public String getNroserie() {
		return nroserie;
	}
	public void setNroserie(String nroserie) {
		this.nroserie = nroserie;
	}
	public String getCodTipoInspeccion() {
		return codTipoInspeccion;
	}
	public void setCodTipoInspeccion(String codTipoInspeccion) {
		this.codTipoInspeccion = codTipoInspeccion;
	}
	public String getCodTipoCertificado() {
		return codTipoCertificado;
	}
	public void setCodTipoCertificado(String codTipoCertificado) {
		this.codTipoCertificado = codTipoCertificado;
	}
	public String getCodTipoAutorizacion() {
		return codTipoAutorizacion;
	}
	public void setCodTipoAutorizacion(String codTipoAutorizacion) {
		this.codTipoAutorizacion = codTipoAutorizacion;
	}
}
