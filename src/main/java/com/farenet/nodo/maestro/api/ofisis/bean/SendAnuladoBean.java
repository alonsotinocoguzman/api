package com.farenet.nodo.maestro.api.ofisis.bean;

import java.util.List;

public class SendAnuladoBean {

	public List<AnuladoBean> anulado;
	public Integer total; 
	
	public SendAnuladoBean(List<AnuladoBean> anulado)
	{
		this.anulado = anulado;
		this.total = anulado.size();
	}
}
