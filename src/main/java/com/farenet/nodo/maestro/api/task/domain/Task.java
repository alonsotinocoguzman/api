package com.farenet.nodo.maestro.api.task.domain;

import org.springframework.context.ApplicationContext;

public abstract class Task implements Runnable{

	protected DataTask data;
	protected ApplicationContext appContext ;
	public Task(DataTask data, ApplicationContext appContext )
	{
		this.data = data;
		this.appContext = appContext;
	}
}
