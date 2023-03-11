package com.farenet.nodo.maestro.api.callcenter.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PropietarioCAPI {

	private String id;
	private String nrodocumento;
	private String nombre;
	private String apellido;
	private String razon;
	private String telefono;
	private String tipo;
	private String direccion;
	private String email;
	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;
	private String placa;
	private String planta;
	private String fechaultimarevision;
	@JsonIgnore
	private Date fechDate;
	@JsonIgnore
	private Date fechDateUltimaRevision;
	private ClienteCAPI cliente;
	@JsonIgnore
	private String idcliente;
	@JsonIgnore
	private String idnromotor;
	@JsonIgnore
	private String idconcepto;
	private ConceptoCAPI concepto;
	private String fechavencimientocert;
	private SoatCAPI soat;
	private VehiculoCAPI vehiculo;
	
	public PropietarioCAPI()
	{
		
	}
	public PropietarioCAPI(String nrodocumento, String nombre, String apellido, String razon, String telefono,
			String tipo, String direccion, String email, String pais, String departamento, String provincia,
			String distrito, String placa, Date fechDate, String idcliente, String idnromotor, String idconcepto, String fechavencimientocert,
			String marca,String modelo, String anioFabricacion,String planta, Date fechaUltimaRevision) {
		super();
		this.nrodocumento = nrodocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.razon = razon!=null?razon:"";
		this.telefono = telefono;
		this.tipo = tipo;
		this.direccion = direccion!=null?direccion:"";
		this.email = email;
		this.pais = pais;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
		this.placa = placa;
		this.fechDate = fechDate;
		this.idcliente = idcliente;
		this.idnromotor = idnromotor;
		this.idconcepto = idconcepto;
		this.fechavencimientocert = fechavencimientocert;
		this.planta = planta;
		this.fechDateUltimaRevision = fechaUltimaRevision;
		
		vehiculo = new VehiculoCAPI();
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		vehiculo.setAnofabricacion(anioFabricacion);
		
	}
	
	public PropietarioCAPI(String nrodocumento, String nombre, String apellido, String razon, String telefono,
			String tipo, String direccion, String email, String pais, String departamento, String provincia,
			String distrito, String placa, Date fechDate, String idcliente, String idnromotor, String idconcepto, String fechavencimientocert,
			String marca,String modelo, Integer anioFabricacion,String planta, Date fechaUltimaRevision) {
		super();
		this.nrodocumento = nrodocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.razon = razon!=null?razon:"";
		this.telefono = telefono;
		this.tipo = tipo;
		this.direccion = direccion!=null?direccion:"";
		this.email = email;
		this.pais = pais;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
		this.placa = placa;
		this.fechDate = fechDate;
		this.idcliente = idcliente;
		this.idnromotor = idnromotor;
		this.idconcepto = idconcepto;
		this.fechavencimientocert = fechavencimientocert;
		this.planta = planta;
		this.fechDateUltimaRevision = fechaUltimaRevision;
		
		vehiculo = new VehiculoCAPI();
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		vehiculo.setAnofabricacion(anioFabricacion.toString());
		
	}
	
	
	
	public Date getFechDateUltimaRevision() {
		return fechDateUltimaRevision;
	}



	public void setFechDateUltimaRevision(Date fechDateUltimaRevision) {
		this.fechDateUltimaRevision = fechDateUltimaRevision;
	}



	public String getPlanta() {
		return planta;
	}



	public void setPlanta(String planta) {
		this.planta = planta;
	}



	public String getFechaultimarevision() {
		return fechaultimarevision;
	}



	public void setFechaultimarevision(String fechaultimarevision) {
		this.fechaultimarevision = fechaultimarevision;
	}



	public VehiculoCAPI getVehiculo() {
		return vehiculo;
	}



	public void setVehiculo(VehiculoCAPI vehiculo) {
		this.vehiculo = vehiculo;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getIdconcepto() {
		return idconcepto;
	}

	public void setIdconcepto(String idconcepto) {
		this.idconcepto = idconcepto;
	}

	public ConceptoCAPI getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoCAPI concepto) {
		this.concepto = concepto;
	}

	public SoatCAPI getSoat() {
		return soat;
	}
	public void setSoat(SoatCAPI soat) {
		this.soat = soat;
	}
	public String getIdnromotor() {
		return idnromotor;
	}
	public void setIdnromotor(String idnromotor) {
		this.idnromotor = idnromotor;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
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
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFechDate() {
		return fechDate;
	}
	public void setFechDate(Date fechDate) {
		this.fechDate = fechDate;
	}
	public ClienteCAPI getCliente() {
		return cliente;
	}
	public void setCliente(ClienteCAPI cliente) {
		this.cliente = cliente;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public String getFechavencimientocert() {
		return fechavencimientocert;
	}
	public void setFechavencimientocert(String fechavencimientocert) {
		this.fechavencimientocert = fechavencimientocert;
	}
	

		
}