package com.farenet.nodo.maestro.api.ofisis.bean;

import java.util.List;

public class SendDepositoBean {

	public List<DepositoBean> data;
	public Integer total; 
	
	public SendDepositoBean(List<DepositoBean> data)
	{
		this.data = data;
		this.total = data.size();
	}
}
