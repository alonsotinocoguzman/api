package com.farenet.nodo.maestro.api.sunat.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "st_mensaje")
public class ST_Mensaje {


	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "st_generator")
		@SequenceGenerator(name="st_generator", sequenceName = "st_seq", allocationSize=1)
	    private Long id;
	
	    private String codigo;
	 
		@Column(length = 10000)
	    private String mensajes;

		@Column(name = "trackid")
	    private String trackId;
	    
	    private String nrodocumentoinspeccion;
	    
	    private String nrocomprobante;

	    @Column(name = "fechacreacion")
	    private Date fechaCreacion = new Date();
	    
	    @Column(length = 150000)
	    private String pdf;
	    
	    //ALTA, BAJA
	    private String tipo ;
	    
	    private String estado;
	    
	    public ST_Mensaje()
	    {}
		public ST_Mensaje(String tipo, cl.dbnet.carga.Mensaje mensaje, String nrodocumentoinspeccion, String nrocomprobante, String pdf)
	    {
	    		this.codigo = mensaje.getCodigo();
	    		this.mensajes = mensaje.getMensajes();
	    		this.trackId = mensaje.getTrackId();
	    		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	    		this.nrocomprobante = nrocomprobante;
	    		this.tipo = tipo;
	    		this.pdf = pdf;
	    }
		
		public ST_Mensaje(String tipo, cl.dbnet.carga.Mensaje mensaje, String nrodocumentoinspeccion, String nrocomprobante)
	    {
	    		this.codigo = mensaje.getCodigo();
	    		this.mensajes = mensaje.getMensajes();
	    		this.trackId = mensaje.getTrackId();
	    		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	    		this.nrocomprobante = nrocomprobante;
	    		this.tipo = tipo;
	    }
	    
		public ST_Mensaje(String tipo, cl.dbnet.bajas.Mensaje mensaje, String nrodocumentoinspeccion, String nrocomprobante)
	    {
	    		this.codigo = mensaje.getCodigo();
	    		this.mensajes = mensaje.getMensajes();
	    		this.trackId = mensaje.getTrackId();
	    		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	    		this.nrocomprobante = nrocomprobante;
	    		this.tipo = tipo;
	    }
	    
		
		public ST_Mensaje(Long id, String codigo, String mensajes, String trackId, String nrodocumentoinspeccion,
				String nrocomprobante, Date fechaCreacion, String pdf, String tipo, String estado) {
			super();
			this.id = id;
			this.codigo = codigo;
			this.mensajes = mensajes;
			this.trackId = trackId;
			this.nrodocumentoinspeccion = nrodocumentoinspeccion;
			this.nrocomprobante = nrocomprobante;
			this.fechaCreacion = fechaCreacion;
			this.pdf = pdf;
			this.tipo = tipo;
			this.estado = estado;
		}
		public String getPdf() {
			return pdf;
		}
		public void setPdf(String pdf) {
			this.pdf = pdf;
		}
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNrocomprobante() {
			return nrocomprobante;
		}

		public void setNrocomprobante(String nrocomprobante) {
			this.nrocomprobante = nrocomprobante;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public Date getFechaCreacion() {
			return fechaCreacion;
		}

		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getMensajes() {
			return mensajes;
		}

		public void setMensajes(String mensajes) {
			this.mensajes = mensajes;
		}

		public String getTrackId() {
			return trackId;
		}

		public void setTrackId(String trackId) {
			this.trackId = trackId;
		}

		public String getNrodocumentoinspeccion() {
			return nrodocumentoinspeccion;
		}

		public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
			this.nrodocumentoinspeccion = nrodocumentoinspeccion;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
	   

}
