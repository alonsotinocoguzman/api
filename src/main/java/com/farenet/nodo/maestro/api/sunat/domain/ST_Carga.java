package com.farenet.nodo.maestro.api.sunat.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "st_carga")
public class ST_Carga {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "st_generator")
	@SequenceGenerator(name="st_generator", sequenceName = "st_seq", allocationSize=1)
    private Long id;
	
	private Date fechaCreacion = new Date();
	
	private String usuario;
	
	private String planta;

	@Column(name = "tipocarga")
	private String tipoCarga;
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "st_carga_st_mensaje", joinColumns = @JoinColumn(name = "st_carga_id"), inverseJoinColumns = @JoinColumn( name = "st_mensajes_id"))
	private List<ST_Mensaje> st_Mensajes;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "st_carga")
	private List<ST_Comprobantes> st_Comprobantes;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "st_carga")
	private List<ST_NotaDeCredito> st_NotaDeCreditos;
	
	public ST_Carga() {
	}
	public List<ST_Comprobantes> getSt_Comprobantes() {
		return st_Comprobantes;
	}
	public void setSt_Comprobantes(List<ST_Comprobantes> st_Comprobantes) {
		this.st_Comprobantes = st_Comprobantes;
	}
	public List<ST_NotaDeCredito> getSt_NotaDeCreditos() {
		return st_NotaDeCreditos;
	}
	public void setSt_NotaDeCreditos(List<ST_NotaDeCredito> st_NotaDeCreditos) {
		this.st_NotaDeCreditos = st_NotaDeCreditos;
	}
	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public List<ST_Mensaje> getSt_Mensajes() {
		return st_Mensajes;
	}
	public void setSt_Mensajes(List<ST_Mensaje> st_Mensajes) {
		this.st_Mensajes = st_Mensajes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	
}
