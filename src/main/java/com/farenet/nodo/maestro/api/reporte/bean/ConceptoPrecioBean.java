package com.farenet.nodo.maestro.api.reporte.bean;

public class ConceptoPrecioBean {
	
	private String monto;
	private String concepto;
	private String formapago;
	
	public ConceptoPrecioBean(String monto, String concepto, String formapago) {
		super();
		this.monto = monto;
		this.concepto = concepto;
		this.formapago = formapago;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getFormapago() {
		return formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	
	
	
	

}
