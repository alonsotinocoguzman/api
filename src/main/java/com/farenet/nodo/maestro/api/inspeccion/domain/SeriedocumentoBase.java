package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;
import com.farenet.nodo.maestro.api.util.Auditoria;

@Entity
@Table(name = "seriedocumentobase")
public class SeriedocumentoBase extends Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@OneToOne()
	private Planta planta;

	@Column(name = "nroactualinforme")
	public Long nroactualInforme;

	@Column(name = "nroactualinspeccion")
	public Long nroactualInspeccion;

	@Column(name = "nroidinspeccion")
	public Long nroidInspeccion;
	
	public String autorizacionsunat;
	
	public String codigoproveedor;
	
	//nuevos campos
	@Column(length = 150) 
	public String serieboleta;
	
	@Column(length = 150) 
	public String seriefactura;
	
	@Column(length = 150) 
	public String serienotacreditoboleta;
	public String serienotacreditofactura;

	@Column(name = "nroactualboleta")
	public Long nroactualBoleta;

	@Column(name = "nroactualfactura")
	public Long nroactualFactura;

	@Column(name = "nroactualnotacreditoboleta")
	public Long nroactualNotaCreditoBoleta;

	@Column(name = "nroactualnotacreditofactura")
	public Long nroactualNotaCreditoFactura;

	@Column(name = "nroactualboletamanual")
	public Long nroactualBoletaManual;

	@Column(name = "nroactualfacturamanual")
	public Long nroactualFacturaManual;
	
	
	public String getSerienotacreditoboleta() {
		return serienotacreditoboleta;
	}
	public void setSerienotacreditoboleta(String serienotacreditoboleta) {
		this.serienotacreditoboleta = serienotacreditoboleta;
	}
	public String getSerienotacreditofactura() {
		return serienotacreditofactura;
	}
	public void setSerienotacreditofactura(String serienotacreditofactura) {
		this.serienotacreditofactura = serienotacreditofactura;
	}
	public Long getNroactualNotaCreditoBoleta() {
		return nroactualNotaCreditoBoleta;
	}
	public void setNroactualNotaCreditoBoleta(Long nroactualNotaCreditoBoleta) {
		this.nroactualNotaCreditoBoleta = nroactualNotaCreditoBoleta;
	}
	public Long getNroactualNotaCreditoFactura() {
		return nroactualNotaCreditoFactura;
	}
	public void setNroactualNotaCreditoFactura(Long nroactualNotaCreditoFactura) {
		this.nroactualNotaCreditoFactura = nroactualNotaCreditoFactura;
	}
	public String getSerieboleta() {
		return serieboleta;
	}
	public void setSerieboleta(String serieboleta) {
		this.serieboleta = serieboleta;
	}
	public String getSeriefactura() {
		return seriefactura;
	}
	public void setSeriefactura(String seriefactura) {
		this.seriefactura = seriefactura;
	}
	public Long getNroactualBoleta() {
		return nroactualBoleta;
	}
	public void setNroactualBoleta(Long nroactualBoleta) {
		this.nroactualBoleta = nroactualBoleta;
	}
	public Long getNroactualFactura() {
		return nroactualFactura;
	}
	public void setNroactualFactura(Long nroactualFactura) {
		this.nroactualFactura = nroactualFactura;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	public Long getNroactualInforme() {
		return nroactualInforme;
	}
	public void setNroactualInforme(Long nroactualInforme) {
		this.nroactualInforme = nroactualInforme;
	}
	public Long getNroactualInspeccion() {
		return nroactualInspeccion;
	}
	public void setNroactualInspeccion(Long nroactualInspeccion) {
		this.nroactualInspeccion = nroactualInspeccion;
	}
	public Long getNroidInspeccion() {
		return nroidInspeccion;
	}
	public void setNroidInspeccion(Long nroidInspeccion) {
		this.nroidInspeccion = nroidInspeccion;
	}
	public String getAutorizacionsunat() {
		return autorizacionsunat;
	}
	public void setAutorizacionsunat(String autorizacionsunat) {
		this.autorizacionsunat = autorizacionsunat;
	}
	public String getCodigoproveedor() {
		return codigoproveedor;
	}
	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}
	public Long getNroactualBoletaManual() {
		return nroactualBoletaManual;
	}
	public void setNroactualBoletaManual(Long nroactualBoletaManual) {
		this.nroactualBoletaManual = nroactualBoletaManual;
	}
	public Long getNroactualFacturaManual() {
		return nroactualFacturaManual;
	}
	public void setNroactualFacturaManual(Long nroactualFacturaManual) {
		this.nroactualFacturaManual = nroactualFacturaManual;
	}
}
