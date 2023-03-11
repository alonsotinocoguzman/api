package com.farenet.nodo.maestro.api.callcenter.bean;

public class ClienteCAPI {
	
	private String nombre;
	private String apellido;
	private String razonsocial;
	private String telefono;
	private String correo;
	private String nrodocumento;
	private String tipodocumento;
	
	public ClienteCAPI()
	{
		
	}
	
	public ClienteCAPI(String nombre, String apellido, String razonsocial, String telefono, String correo,
			String nrodocumento, String tipodocumento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.razonsocial = razonsocial;
		this.telefono = telefono;
		this.correo = correo;
		this.nrodocumento = nrodocumento;
		this.tipodocumento = tipodocumento;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	
	

}
