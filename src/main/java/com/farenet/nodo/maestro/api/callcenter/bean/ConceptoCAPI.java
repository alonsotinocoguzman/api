package com.farenet.nodo.maestro.api.callcenter.bean;

public class ConceptoCAPI {

	private String _id;
	private String nombre;
	
	public ConceptoCAPI()
	{
		
	}
	
	public ConceptoCAPI(String _id, String nombre) {
		super();
		this._id = _id;
		this.nombre = nombre;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
