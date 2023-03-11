package com.farenet.nodo.maestro.api.ofisis.bean;

import java.util.List;

public class SendComprobanteBean {

	public List<InsertBean> comprobante;
	public Integer total; 
	
	public SendComprobanteBean(List<InsertBean> comprobante)
	{
		this.comprobante = comprobante;
		this.total = comprobante.size();
	}
}
