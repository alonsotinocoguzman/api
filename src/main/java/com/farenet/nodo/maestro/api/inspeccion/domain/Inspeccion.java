package com.farenet.nodo.maestro.api.inspeccion.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.linea.domain.ResultadoMaquina;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "inspeccion")
@Audited
public class Inspeccion extends Auditoria {

	 public enum POSICION {
	        CAJA, PAGO, VEHICULO, CLIENTE, VERIFICACION, GASES, OPACIMETRO, LUCES, INSPECCION_VISUAL, SONOMETRO, PROFUNDIMETRO, FRENOMETRO, ALINEACION, SUSPENSION, CONSOLIDACION, CONSOLIDADO, FOTO, SERVICIO, DUPLICADO
	    }
	 
	 private  POSICION posicion;
	  
	// D o M
	@Column(length = 1, name = "tipodesaprobado")
	private String tipoDesaprobado;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipocertificado tipocertificado;


	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Timestamp fechaenlinea;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(nullable = true)
	private Timestamp fechanulacion;


	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(nullable = true)
	private Timestamp fechconsolidado;
	

	@Column(length = 50, name = "nrodocumentoinforme")
	private String nrodocumentoInforme;
	
	@Column(length = 50)
	private String nrodocumentoreinspeccion;
	

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(nullable = true)
	private Timestamp fechiniciovigencia;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@Column(nullable = true)
	private Timestamp fechvencimiento;

	@Id
	@Column(length = 50)
	private String nrodocumentoinspeccion;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL }, mappedBy = "inspeccion")
	@NotAudited
	private List<Certificado> certificados;
	
	@Column(nullable = true, columnDefinition = "TEXT", name = "tipoerror")
	private String tipoError;

	@Column(nullable = true, columnDefinition = "TEXT")
	private String observacion;
	
	@Column(nullable = true, columnDefinition = "TEXT", name = "observacionretirado")
	private String observacionRetirado;

	@Column(nullable = true, columnDefinition = "TEXT", name = "observacionanulado")
	private String observacionAnulado;


	@Column(nullable = true, length = 1)
	private String resultado;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Usuario usuarioanulacion;

	@Column(name = "indicedesaprobado")
	private int indiceDesaprobado = 1;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Usuario usuarioconsolidado;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Usuario usuariocaja;

	@ManyToOne()
	@JoinColumn(nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Usuario usuarioingcertificador;

	@Column(nullable = true, length = 20)
	private String vigencia;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "inspeccion", orphanRemoval= true)
	private List<ResultadoMaquina> resultadosMaquina;

	@NotNull
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "inspeccion", orphanRemoval= true)
	private List<Comprobante> comprobantes;

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Inspeccionestado inspeccionestado;

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipoinspeccion tipoinspeccion;

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipoautorizacion tipoautorizacion;

	public Inspeccion() {
		
	}
	

	// bi-directional many-to-one association to Vehiculo
	@ManyToOne()
	@Audited
	private Vehiculo vehiculo;

	
	
	public POSICION getPosicion() {
		return posicion;
	}


	public void setPosicion(POSICION posicion) {
		this.posicion = posicion;
	}


	public Timestamp getFechconsolidado() {
		return fechconsolidado;
	}


	public void setFechconsolidado(Timestamp fechconsolidado) {
		this.fechconsolidado = fechconsolidado;
	}
	
	public String getNrodocumentoreinspeccion() {
		return nrodocumentoreinspeccion;
	}

	public void setNrodocumentoreinspeccion(String nrodocumentoreinspeccion) {
		this.nrodocumentoreinspeccion = nrodocumentoreinspeccion;
	}

	public String getNrodocumentoInforme() {
		return nrodocumentoInforme;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	
	public void setNrodocumentoInforme(String nrodocumentoInforme) {
		this.nrodocumentoInforme = nrodocumentoInforme;
	}

	@JsonIgnore
	public Comprobante getLastComprobante()
	{
		Comprobante mayor = null;
		if(comprobantes!=null && comprobantes.size() > 0)
		{
			mayor = comprobantes.get(0);
			for(Comprobante comprobante : comprobantes)
			{
				if(comprobante.getFechcreacion().after(mayor.getFechcreacion()))
				{
					mayor = comprobante;
				}
			}
		}else{
			return null;
		}
		
		return mayor;
		
	    
	}
	public Comprobante getComprobante(String nrocomprobante){
		Comprobante comprobante = null;
		for(Comprobante bean : comprobantes){
			if(bean.getNrocomprobante() != null){
				if(bean.getNrocomprobante().equals(nrocomprobante)){
					comprobante = bean;
				}
			}
		}
		return comprobante;
	}
	
	@JsonIgnore
	public Certificado getLastCertificado()
	{
		Certificado mayor = null;
		if(certificados!=null && certificados.size() > 0)
		{
			mayor = certificados.get(0);
			for(Certificado certificado : certificados)
			{
				if(certificado.getFechcreacion().after(mayor.getFechcreacion()))
				{
					mayor = certificado;
				}
			}
		}else{
			return null;
		}
		
		return mayor;
		
	    
	}

	public Timestamp getFechiniciovigencia() {
		return fechiniciovigencia;
	}



	public void setFechiniciovigencia(Timestamp fechiniciovigencia) {
		this.fechiniciovigencia = fechiniciovigencia;
	}

	public Tipoautorizacion getTipoautorizacion() {
		return tipoautorizacion;
	}

	public void setTipoautorizacion(Tipoautorizacion tipoautorizacion) {
		this.tipoautorizacion = tipoautorizacion;
	}

	

	public List<ResultadoMaquina> getResultadosMaquina() {
		return resultadosMaquina;
	}

	public void setResultadosMaquina(List<ResultadoMaquina> resultadosMaquina) {
		this.resultadosMaquina = resultadosMaquina;
	}

	public String getTipoDesaprobado() {
		return tipoDesaprobado;
	}

	public void setTipoDesaprobado(String tipoDesaprobado) {
		this.tipoDesaprobado = tipoDesaprobado;
	}

	public Tipocertificado getTipocertificado() {
		return tipocertificado;
	}

	public void setTipocertificado(Tipocertificado tipocertificado) {
		this.tipocertificado = tipocertificado;
	}

	public Timestamp getFechaenlinea() {
		return this.fechaenlinea;
	}

	public void setFechaenlinea(Timestamp fechaenlinea) {
		this.fechaenlinea = fechaenlinea;
	}

	public Timestamp getFechanulacion() {
		return this.fechanulacion;
	}

	public void setFechanulacion(Timestamp fechanulacion) {
		this.fechanulacion = fechanulacion;
	}


	public Timestamp getFechvencimiento() {
		return this.fechvencimiento;
	}

	public void setFechvencimiento(Timestamp fechvencimiento) {
		this.fechvencimiento = fechvencimiento;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Usuario getUsuarioingcertificador() {
		return this.usuarioingcertificador;
	}

	public void setUsuarioingcertificador(Usuario usuarioingcertificador) {
		this.usuarioingcertificador = usuarioingcertificador;
	}

	public String getVigencia() {
		return this.vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	

	public List<Comprobante> getComprobantes() {
		return comprobantes;
	}



	public void setComprobantes(List<Comprobante> comprobantes) {
		this.comprobantes = comprobantes;
	}



	public Inspeccionestado getInspeccionestado() {
		return this.inspeccionestado;
	}

	public void setInspeccionestado(Inspeccionestado inspeccionestado) {
		this.inspeccionestado = inspeccionestado;
	}

	public Tipoinspeccion getTipoinspeccion() {
		return this.tipoinspeccion;
	}

	public void setTipoinspeccion(Tipoinspeccion tipoinspeccion) {
		this.tipoinspeccion = tipoinspeccion;
	}

	public String getNrodocumentoinspeccion() {
		return nrodocumentoinspeccion;
	}

	public void setNrodocumentoinspeccion(String nrodocumentoinspeccion) {
		this.nrodocumentoinspeccion = nrodocumentoinspeccion;
	}

	public Usuario getUsuarioanulacion() {
		return usuarioanulacion;
	}

	public void setUsuarioanulacion(Usuario usuarioanulacion) {
		this.usuarioanulacion = usuarioanulacion;
	}

	public Usuario getUsuarioconsolidado() {
		return usuarioconsolidado;
	}

	public void setUsuarioconsolidado(Usuario usuarioconsolidado) {
		this.usuarioconsolidado = usuarioconsolidado;
	}

	public Usuario getUsuariocaja() {
		return usuariocaja;
	}

	public void setUsuariocaja(Usuario usuariocaja) {
		this.usuariocaja = usuariocaja;
	}

	public int getIndiceDesaprobado() {
		return indiceDesaprobado;
	}

	public void setIndiceDesaprobado(int indiceDesaprobado) {
		this.indiceDesaprobado = indiceDesaprobado;
	}

	

	public List<Certificado> getCertificados() {
		return certificados;
	}


	public void setCertificados(List<Certificado> certificados) {
		this.certificados = certificados;
	}


	public String getObservacionRetirado() {
		return observacionRetirado;
	}

	public void setObservacionRetirado(String observacionRetirado) {
		this.observacionRetirado = observacionRetirado;
	}

	public String getObservacionAnulado() {
		return observacionAnulado;
	}

	public void setObservacionAnulado(String observacionAnulado) {
		this.observacionAnulado = observacionAnulado;
	}
	public String getTipoError() {
		return tipoError;
	}
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}
}
