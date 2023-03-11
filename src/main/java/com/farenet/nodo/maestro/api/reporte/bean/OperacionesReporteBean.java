package com.farenet.nodo.maestro.api.reporte.bean;

public class OperacionesReporteBean {
	
	public String cantIns;
	public String nrodocInsImp;
	public Boolean estado;
	public String fechCrea;
	public String resultado;
	public String obsAnu;
	
	public OperacionesReporteBean(String cantIns, String nrodocInsImp, Boolean estado, String fechCrea, String resultado,
			String obsAnu) {
		super();
		this.cantIns = cantIns;
		this.nrodocInsImp = nrodocInsImp;
		this.estado = estado;
		this.fechCrea = fechCrea;
		this.resultado = resultado;
		this.obsAnu = obsAnu;
	}

	public String getCantIns() {
		return cantIns;
	}

	public void setCantIns(String cantIns) {
		this.cantIns = cantIns;
	}

	public String getNrodocInsImp() {
		return nrodocInsImp;
	}

	public void setNrodocInsImp(String nrodocInsImp) {
		this.nrodocInsImp = nrodocInsImp;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getFechCrea() {
		return fechCrea;
	}

	public void setFechCrea(String fechCrea) {
		this.fechCrea = fechCrea;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObsAnu() {
		return obsAnu;
	}

	public void setObsAnu(String obsAnu) {
		this.obsAnu = obsAnu;
	}
	
	
	
	
}