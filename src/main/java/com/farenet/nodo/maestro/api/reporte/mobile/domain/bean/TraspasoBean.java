package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

public class TraspasoBean {
	
	private String nroinspeccionAnu;
    private String placaAnu;
    private String nroinspeccionApasar;
    private String placaApasar;
    private String motivo;
	
	public String getNroinspeccionAnu() {
		return nroinspeccionAnu;
	}
	public void setNroinspeccionAnu(String nroinspeccionAnu) {
		this.nroinspeccionAnu = nroinspeccionAnu;
	}
	public String getPlacaAnu() {
		return placaAnu;
	}
	public void setPlacaAnu(String placaAnu) {
		this.placaAnu = placaAnu;
	}
	public String getNroinspeccionApasar() {
		return nroinspeccionApasar;
	}
	public void setNroinspeccionApasar(String nroinspeccionApasar) {
		this.nroinspeccionApasar = nroinspeccionApasar;
	}
	public String getPlacaApasar() {
		return placaApasar;
	}
	public void setPlacaApasar(String placaApasar) {
		this.placaApasar = placaApasar;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
