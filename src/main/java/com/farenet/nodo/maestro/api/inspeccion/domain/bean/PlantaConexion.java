package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PlantaConexion {

	public String planta;
	public String key;

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date fechaUltimaConexion = new Date();
	public String usuario = "admin";
}
