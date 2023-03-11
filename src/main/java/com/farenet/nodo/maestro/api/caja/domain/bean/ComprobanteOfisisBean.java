package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.Date;

public class ComprobanteOfisisBean implements Cloneable{

	public Long id;
	public String bdname;
	public String sucursal;
	public String abrevEmpresa;
	public String ubigeo;
    public String zona;
    public String empresa ;
    public String cuentaCajaEfectivo ;
	public String codigoCaja;
	public String comprobanteEstado;
	public String tipoDocumento;
	public String nroComprobante;
	public String codigoCondicionPago;
	public String codigoVendedor ;
    public String situacionJuridica;
    public Date fechaPago;
    public String CodPos;
    public String Municp;
    public String documentoValidado ;
	public String nombreCliente;
	public String nombres;
	public String direccion;
	public String telefono;
	public String email;
	public String apellidos;
	public String distrito;
	public Double importeTotal;
	public Double baseImponible;
	public Double igv;
	public Double totalSinDescuento;
	public Double totalDescuento;
	public Integer diasCredito;
	public String rucConvenio;
	public String nombreConvenio;
	public String codigoPlanta;
	public String nombrePlanta;
	public String nromotor;
	public Date fechaAnulacion;
	public String tipodescuento;
	public String desConvenio;
	public String desCampania;
	public String EstadoComprobanteSunat;
	public String nombredescuento;
	public String tipocontado;
	public Boolean PagoMixto;
	public ComprobanteOfisisBean(Long id, String bdname, String sucursal, String abrevEmpresa, String ubigeo,
			String zona, String empresa, String cuentaCajaEfectivo, String codigoCaja, String comprobanteEstado,
			String tipoDocumento, String nroComprobante, String codigoCondicionPago, String codigoVendedor,
			String situacionJuridica, Date fechaPago, String codPos, String municp, String documentoValidado,
			String nombreCliente, String nombres, String direccion, String telefono, String email, String apellidos,
			String distrito, Double importeTotal, Double baseImponible, Double igv, Double totalSinDescuento,
			Double totalDescuento, Integer diasCredito, String rucConvenio, String nombreConvenio, String codigoPlanta,
			String nombrePlanta, String nromotor, Date fechaAnulacion, String tipodescuento, String desConvenio,
			String desCampania, String estadoComprobanteSunat, String nombredescuento,String tipocontado, Boolean pagomixto) {
		super();
		this.id = id;
		this.bdname = bdname;
		this.sucursal = sucursal;
		this.abrevEmpresa = abrevEmpresa;
		this.ubigeo = ubigeo;
		this.zona = zona;
		this.empresa = empresa;
		this.cuentaCajaEfectivo = cuentaCajaEfectivo;
		this.codigoCaja = codigoCaja;
		this.comprobanteEstado = comprobanteEstado;
		this.tipoDocumento = tipoDocumento;
		this.nroComprobante = nroComprobante;
		this.codigoCondicionPago = codigoCondicionPago;
		this.codigoVendedor = codigoVendedor;
		this.situacionJuridica = situacionJuridica;
		this.fechaPago = fechaPago;
		CodPos = codPos;
		Municp = municp;
		this.documentoValidado = documentoValidado;
		this.nombreCliente = nombreCliente;
		this.nombres = nombres;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.apellidos = apellidos;
		this.distrito = distrito;
		this.importeTotal = importeTotal;
		this.baseImponible = baseImponible;
		this.igv = igv;
		this.totalSinDescuento = totalSinDescuento;
		this.totalDescuento = totalDescuento;
		this.diasCredito = diasCredito;
		this.rucConvenio = rucConvenio;
		this.nombreConvenio = nombreConvenio;
		this.codigoPlanta = codigoPlanta;
		this.nombrePlanta = nombrePlanta;
		this.nromotor = nromotor;
		this.fechaAnulacion = fechaAnulacion;
		this.tipodescuento = tipodescuento;
		this.desConvenio = desConvenio;
		this.desCampania = desCampania;
		EstadoComprobanteSunat = estadoComprobanteSunat;
		this.nombredescuento=nombredescuento;
		this.tipocontado=tipocontado;
		this.PagoMixto=pagomixto;
	}
	public Boolean getPagoMixto() {
		return PagoMixto;
	}
	public void setPagoMixto(Boolean pagoMixto) {
		PagoMixto = pagoMixto;
	}
	public ComprobanteOfisisBean() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBdname() {
		return bdname;
	}
	public void setBdname(String bdname) {
		this.bdname = bdname;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getAbrevEmpresa() {
		return abrevEmpresa;
	}
	public void setAbrevEmpresa(String abrevEmpresa) {
		this.abrevEmpresa = abrevEmpresa;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCuentaCajaEfectivo() {
		return cuentaCajaEfectivo;
	}
	public void setCuentaCajaEfectivo(String cuentaCajaEfectivo) {
		this.cuentaCajaEfectivo = cuentaCajaEfectivo;
	}
	public String getCodigoCaja() {
		return codigoCaja;
	}
	public void setCodigoCaja(String codigoCaja) {
		this.codigoCaja = codigoCaja;
	}
	public String getComprobanteEstado() {
		return comprobanteEstado;
	}
	public void setComprobanteEstado(String comprobanteEstado) {
		this.comprobanteEstado = comprobanteEstado;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getCodigoCondicionPago() {
		return codigoCondicionPago;
	}
	public void setCodigoCondicionPago(String codigoCondicionPago) {
		this.codigoCondicionPago = codigoCondicionPago;
	}
	public String getCodigoVendedor() {
		return codigoVendedor;
	}
	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
	public String getSituacionJuridica() {
		return situacionJuridica;
	}
	public void setSituacionJuridica(String situacionJuridica) {
		this.situacionJuridica = situacionJuridica;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getCodPos() {
		return CodPos;
	}
	public void setCodPos(String codPos) {
		CodPos = codPos;
	}
	public String getMunicp() {
		return Municp;
	}
	public void setMunicp(String municp) {
		Municp = municp;
	}
	public String getDocumentoValidado() {
		return documentoValidado;
	}
	public void setDocumentoValidado(String documentoValidado) {
		this.documentoValidado = documentoValidado;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public Double getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}
	public Double getIgv() {
		return igv;
	}
	public void setIgv(Double igv) {
		this.igv = igv;
	}
	public Double getTotalSinDescuento() {
		return totalSinDescuento;
	}
	public void setTotalSinDescuento(Double totalSinDescuento) {
		this.totalSinDescuento = totalSinDescuento;
	}
	public Double getTotalDescuento() {
		return totalDescuento;
	}
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
	public Integer getDiasCredito() {
		return diasCredito;
	}
	public void setDiasCredito(Integer diasCredito) {
		this.diasCredito = diasCredito;
	}
	public String getRucConvenio() {
		return rucConvenio;
	}
	public void setRucConvenio(String rucConvenio) {
		this.rucConvenio = rucConvenio;
	}
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	public String getCodigoPlanta() {
		return codigoPlanta;
	}
	public void setCodigoPlanta(String codigoPlanta) {
		this.codigoPlanta = codigoPlanta;
	}
	public String getNombrePlanta() {
		return nombrePlanta;
	}
	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}
	public String getNromotor() {
		return nromotor;
	}
	public void setNromotor(String nromotor) {
		this.nromotor = nromotor;
	}
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	public String getTipodescuento() {
		return tipodescuento;
	}
	public void setTipodescuento(String tipodescuento) {
		this.tipodescuento = tipodescuento;
	}
	public String getDesConvenio() {
		return desConvenio;
	}
	public void setDesConvenio(String desConvenio) {
		this.desConvenio = desConvenio;
	}
	public String getDesCampania() {
		return desCampania;
	}
	public void setDesCampania(String desCampania) {
		this.desCampania = desCampania;
	}
	public String getEstadoComprobanteSunat() {
		return EstadoComprobanteSunat;
	}
	public void setEstadoComprobanteSunat(String estadoComprobanteSunat) {
		EstadoComprobanteSunat = estadoComprobanteSunat;
	}
	public String getNombredescuento() {
		return nombredescuento;
	}
	public void setNombredescuento(String nombredescuento) {
		this.nombredescuento = nombredescuento;
	}
	public String getTipocontado() {
		return tipocontado;
	}
	public void setTipocontado(String tipocontado) {
		this.tipocontado = tipocontado;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
   
   
}
