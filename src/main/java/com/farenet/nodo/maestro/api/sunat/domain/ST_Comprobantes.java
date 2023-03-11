package com.farenet.nodo.maestro.api.sunat.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "st_comprobantes")
public class ST_Comprobantes {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "st_generator")
	@SequenceGenerator(name="st_generator", sequenceName = "st_seq", allocationSize=1)
    private Long id;
	@ManyToOne
	@JsonIgnore
	private ST_Carga st_carga;

	private String tipo;

	@Column(name = "rucempresa")
	private String rucEmpresa;

	private String planta;
	private String serie;
	private Long correlativo;

	@Column(name = "descripcionserv")
	private String descripcionServ;

	private Double total;
	private String nrodocumentoidentidad;

	@Column(name = "nombrecompleto")
	private String nombreCompleto;

	private String direccion;
	private String telefono;
	private String email;
	private String placa;
	private String marca;

	@Column(name = "tipovehiculo")
	private String tipoVehiculo;

	@Column(name = "nromotor")
	private String nroMotor;

	private String modelo;
	private Date fechcreacion = new Date(); 
	private Boolean sendedtooffisis = false;
	private String formapago;
	private String comprobanteestado;
	public ST_Comprobantes() {
		super();
	}
	
	public ST_Comprobantes(Long id, ST_Carga st_carga, String tipo, String rucEmpresa, String planta, String serie,
			Long correlativo, String descripcionServ, Double total, String nrodocumentoidentidad, String nombreCompleto,
			String direccion, String telefono, String email, String placa, String marca, String tipoVehiculo,
			String nroMotor, String modelo, Date fechcreacion, Boolean sendedtooffisis, String formapago,
			String comprobanteestado) {
		super();
		this.id = id;
		this.st_carga = st_carga;
		this.tipo = tipo;
		this.rucEmpresa = rucEmpresa;
		this.planta = planta;
		this.serie = serie;
		this.correlativo = correlativo;
		this.descripcionServ = descripcionServ;
		this.total = total;
		this.nrodocumentoidentidad = nrodocumentoidentidad;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.placa = placa;
		this.marca = marca;
		this.tipoVehiculo = tipoVehiculo;
		this.nroMotor = nroMotor;
		this.modelo = modelo;
		this.fechcreacion = fechcreacion;
		this.sendedtooffisis = sendedtooffisis;
		this.formapago = formapago;
		this.comprobanteestado = comprobanteestado;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ST_Carga getSt_carga() {
		return st_carga;
	}
	public void setSt_carga(ST_Carga st_carga) {
		this.st_carga = st_carga;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getDescripcionServ() {
		return descripcionServ;
	}
	public void setDescripcionServ(String descripcionServ) {
		this.descripcionServ = descripcionServ;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getNrodocumentoidentidad() {
		return nrodocumentoidentidad;
	}
	public void setNrodocumentoidentidad(String nrodocumentoidentidad) {
		this.nrodocumentoidentidad = nrodocumentoidentidad;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getFechcreacion() {
		return fechcreacion;
	}
	public void setFechcreacion(Date fechcreacion) {
		this.fechcreacion = fechcreacion;
	}
	public Boolean getSendedtooffisis() {
		return sendedtooffisis;
	}
	public void setSendedtooffisis(Boolean sendedtooffisis) {
		this.sendedtooffisis = sendedtooffisis;
	}
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public String getComprobanteestado() {
		return comprobanteestado;
	}
	public void setComprobanteestado(String comprobanteestado) {
		this.comprobanteestado = comprobanteestado;
	}
	public Long getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(Long correlativo) {
		this.correlativo = correlativo;
	}
}
