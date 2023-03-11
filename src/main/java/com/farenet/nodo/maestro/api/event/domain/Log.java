package com.farenet.nodo.maestro.api.event.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.farenet.nodo.maestro.api.task.domain.DataTask;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Log {

	@Id
	@JsonIgnore
	public String _id;
	
	public String user;
	public String target;
	public Date date;
	public String message;

	public Object data;
	
	public void migrate(DataTask dataTask)
	{
		this.user = dataTask.user;
		this.date = new Date();
		this.target = dataTask.target;
		this.message = dataTask.message;
		this.data = dataTask.data;
				
	}
}
