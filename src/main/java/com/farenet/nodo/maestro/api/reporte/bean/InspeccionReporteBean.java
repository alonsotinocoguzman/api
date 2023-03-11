package com.farenet.nodo.maestro.api.reporte.bean;

public class InspeccionReporteBean {

	public String nroDocIns;
	public String nroCert;
	public String nroInf;
	public String tipoInspeccion;
	public String tipoAutorizacion;
	public String estadoIns;
	public String obsAnulado;
	public String obsRetirado;
	public String fechaConsolidado;
	public String vigencia;
	public String fechaVencimiento;
	public String esReinspeccion;
	public String usuarioCaja;
	public String nroComprobante;
	public String concepto;
	public String clienteNom;
	public String clienteRuc;
	public String nrodocumentocliente;
	public String linea;
	public String estadoComprobante;
	public String formaPago;
	public String entidad;
	public String cuentaCorriente;
	public String moneda;
	public String fechaPago;
	public String tarjeta;
	public String nroOperacionBanco; 
	public String nroOperacionTarjeta; 
	public String descuento;
	public String tipoDescuentoConv;
	public String tipoDescuentoCamp;
	public String ejecutivoDescuento;
	public Double totalSinDescuento;
	public Double totalDescuento;
	public Double baseImponible;
	public Double igv;
	public Double total;
	public String placaMotor;
	public String placa;
	public String nroMotor;
	public String marca;
	public String modelo;
	public Integer anio;
	public String color;
	public String combustible;
	public String marcaCarroceria;
	public String nroSerie;
	public String categoria;
	public String categoriaExtra;
	public Integer nroAsientos;
	public Integer nroPasajeros;
	public Integer nroEjes;
	public Integer nroRuedas;
	public String nroCilindros;
	public Double nroPesoSeco;
	public Double nroPesoBruto;
	public Integer nroPuertas;
	public String nroSalidaEmergencia;
	public Double kilometraje;
	public Double alto;
	public Double largo;
	public Double ancho;
	public String nroSoat;
	public String aseguradora;
	public String fechaInicioTarjetaPropiedad;
	public String fechaFinTarjetaPropiedad;
	public String fechaAnulacion;
	public String resultado;
	public String insObs;
	public String fechImpre;
	public String usuAnuCer;
	public String usuAnuCom;
	public String obsAnu;
	public Boolean estadoInfCert;
	public String nrohojaValorada;
	public String usuAnuIns;
	public String observacion;
	public InspeccionReporteBean(String nroDocIns, String nroCert, String nroInf, String tipoInspeccion,
			String tipoAutorizacion, String estadoIns, String obsAnulado, String obsRetirado, String fechaConsolidado,
			String vigencia, String fechaVencimiento, String esReinspeccion, String usuarioCaja, String nroComprobante,
			String concepto, String clienteNom, String clienteRuc, String nrodocumentocliente, String linea,
			String estadoComprobante, String formaPago, String entidad, String cuentaCorriente, String moneda,
			String fechaPago, String tarjeta, String nroOperacionBanco, String nroOperacionTarjeta, 
			String descuento, String tipoDescuentoConv, String tipoDescuentoCamp, String ejecutivoDescuento,
			Double totalSinDescuento, Double totalDescuento, Double baseImponible, Double igv, Double total,
			String placaMotor, String placa, String nroMotor, String marca, String modelo, Integer anio, String color,
			String combustible, String marcaCarroceria, String nroSerie, String categoria, String categoriaExtra,
			Integer nroAsientos, Integer nroPasajeros, Integer nroEjes, Integer nroRuedas, String nroCilindros,
			Double nroPesoSeco, Double nroPesoBruto, Integer nroPuertas, String nroSalidaEmergencia, Double kilometraje,
			Double alto, Double largo, Double ancho, String nroSoat, String aseguradora,
			String fechaInicioTarjetaPropiedad, String fechaFinTarjetaPropiedad, String fechaAnulacion,
			String resultado, String insObs, String fechImpre, String usuAnuCer, String usuAnuCom, String obsAnu,
			Boolean estadoInfCert, String nrohojaValorada, String usuAnuIns, String observacion) {
		super();
		this.nroDocIns = nroDocIns;
		this.nroCert = nroCert;
		this.nroInf = nroInf;
		this.tipoInspeccion = tipoInspeccion;
		this.tipoAutorizacion = tipoAutorizacion;
		this.estadoIns = estadoIns;
		this.obsAnulado = obsAnulado;
		this.obsRetirado = obsRetirado;
		this.fechaConsolidado = fechaConsolidado;
		this.vigencia = vigencia;
		this.fechaVencimiento = fechaVencimiento;
		this.esReinspeccion = esReinspeccion;
		this.usuarioCaja = usuarioCaja;
		this.nroComprobante = nroComprobante;
		this.concepto = concepto;
		this.clienteNom = clienteNom;
		this.clienteRuc = clienteRuc;
		this.nrodocumentocliente = nrodocumentocliente;
		this.linea = linea;
		this.estadoComprobante = estadoComprobante;
		this.formaPago = formaPago;
		this.entidad = entidad;
		this.cuentaCorriente = cuentaCorriente;
		this.moneda = moneda;
		this.fechaPago = fechaPago;
		this.tarjeta = tarjeta;
		this.nroOperacionBanco = nroOperacionBanco;
		this.nroOperacionTarjeta = nroOperacionTarjeta;
		this.descuento = descuento;
		this.tipoDescuentoConv = tipoDescuentoConv;
		this.tipoDescuentoCamp = tipoDescuentoCamp;
		this.ejecutivoDescuento = ejecutivoDescuento;
		this.totalSinDescuento = totalSinDescuento;
		this.totalDescuento = totalDescuento;
		this.baseImponible = baseImponible;
		this.igv = igv;
		this.total = total;
		this.placaMotor = placaMotor;
		this.placa = placa;
		this.nroMotor = nroMotor;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.color = color;
		this.combustible = combustible;
		this.marcaCarroceria = marcaCarroceria;
		this.nroSerie = nroSerie;
		this.categoria = categoria;
		this.categoriaExtra = categoriaExtra;
		this.nroAsientos = nroAsientos;
		this.nroPasajeros = nroPasajeros;
		this.nroEjes = nroEjes;
		this.nroRuedas = nroRuedas;
		this.nroCilindros = nroCilindros;
		this.nroPesoSeco = nroPesoSeco;
		this.nroPesoBruto = nroPesoBruto;
		this.nroPuertas = nroPuertas;
		this.nroSalidaEmergencia = nroSalidaEmergencia;
		this.kilometraje = kilometraje;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
		this.nroSoat = nroSoat;
		this.aseguradora = aseguradora;
		this.fechaInicioTarjetaPropiedad = fechaInicioTarjetaPropiedad;
		this.fechaFinTarjetaPropiedad = fechaFinTarjetaPropiedad;
		this.fechaAnulacion = fechaAnulacion;
		this.resultado = resultado;
		this.insObs = insObs;
		this.fechImpre = fechImpre;
		this.usuAnuCer = usuAnuCer;
		this.usuAnuCom = usuAnuCom;
		this.obsAnu = obsAnu;
		this.estadoInfCert = estadoInfCert;
		this.nrohojaValorada = nrohojaValorada;
		this.usuAnuIns = usuAnuIns;
		this.observacion = observacion;
	}
	public String getNroDocIns() {
		return nroDocIns;
	}
	public void setNroDocIns(String nroDocIns) {
		this.nroDocIns = nroDocIns;
	}
	public String getNroCert() {
		return nroCert;
	}
	public void setNroCert(String nroCert) {
		this.nroCert = nroCert;
	}
	public String getNroInf() {
		return nroInf;
	}
	public void setNroInf(String nroInf) {
		this.nroInf = nroInf;
	}
	public String getTipoInspeccion() {
		return tipoInspeccion;
	}
	public void setTipoInspeccion(String tipoInspeccion) {
		this.tipoInspeccion = tipoInspeccion;
	}
	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}
	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}
	public String getEstadoIns() {
		return estadoIns;
	}
	public void setEstadoIns(String estadoIns) {
		this.estadoIns = estadoIns;
	}
	public String getObsAnulado() {
		return obsAnulado;
	}
	public void setObsAnulado(String obsAnulado) {
		this.obsAnulado = obsAnulado;
	}
	public String getObsRetirado() {
		return obsRetirado;
	}
	public void setObsRetirado(String obsRetirado) {
		this.obsRetirado = obsRetirado;
	}
	public String getFechaConsolidado() {
		return fechaConsolidado;
	}
	public void setFechaConsolidado(String fechaConsolidado) {
		this.fechaConsolidado = fechaConsolidado;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getEsReinspeccion() {
		return esReinspeccion;
	}
	public void setEsReinspeccion(String esReinspeccion) {
		this.esReinspeccion = esReinspeccion;
	}
	public String getUsuarioCaja() {
		return usuarioCaja;
	}
	public void setUsuarioCaja(String usuarioCaja) {
		this.usuarioCaja = usuarioCaja;
	}
	public String getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getClienteNom() {
		return clienteNom;
	}
	public void setClienteNom(String clienteNom) {
		this.clienteNom = clienteNom;
	}
	public String getClienteRuc() {
		return clienteRuc;
	}
	public void setClienteRuc(String clienteRuc) {
		this.clienteRuc = clienteRuc;
	}
	public String getNrodocumentocliente() {
		return nrodocumentocliente;
	}
	public void setNrodocumentocliente(String nrodocumentocliente) {
		this.nrodocumentocliente = nrodocumentocliente;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getEstadoComprobante() {
		return estadoComprobante;
	}
	public void setEstadoComprobante(String estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getNroOperacionBanco() {
		return nroOperacionBanco;
	}
	public void setNroOperacionBanco(String nroOperacionBanco) {
		this.nroOperacionBanco = nroOperacionBanco;
	}
	public String getNroOperacionTarjeta() {
		return nroOperacionTarjeta;
	}
	public void setNroOperacionTarjeta(String nroOperacionTarjeta) {
		this.nroOperacionTarjeta = nroOperacionTarjeta;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getTipoDescuentoConv() {
		return tipoDescuentoConv;
	}
	public void setTipoDescuentoConv(String tipoDescuentoConv) {
		this.tipoDescuentoConv = tipoDescuentoConv;
	}
	public String getTipoDescuentoCamp() {
		return tipoDescuentoCamp;
	}
	public void setTipoDescuentoCamp(String tipoDescuentoCamp) {
		this.tipoDescuentoCamp = tipoDescuentoCamp;
	}
	public String getEjecutivoDescuento() {
		return ejecutivoDescuento;
	}
	public void setEjecutivoDescuento(String ejecutivoDescuento) {
		this.ejecutivoDescuento = ejecutivoDescuento;
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getPlacaMotor() {
		return placaMotor;
	}
	public void setPlacaMotor(String placaMotor) {
		this.placaMotor = placaMotor;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public String getMarcaCarroceria() {
		return marcaCarroceria;
	}
	public void setMarcaCarroceria(String marcaCarroceria) {
		this.marcaCarroceria = marcaCarroceria;
	}
	public String getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoriaExtra() {
		return categoriaExtra;
	}
	public void setCategoriaExtra(String categoriaExtra) {
		this.categoriaExtra = categoriaExtra;
	}
	public Integer getNroAsientos() {
		return nroAsientos;
	}
	public void setNroAsientos(Integer nroAsientos) {
		this.nroAsientos = nroAsientos;
	}
	public Integer getNroPasajeros() {
		return nroPasajeros;
	}
	public void setNroPasajeros(Integer nroPasajeros) {
		this.nroPasajeros = nroPasajeros;
	}
	public Integer getNroEjes() {
		return nroEjes;
	}
	public void setNroEjes(Integer nroEjes) {
		this.nroEjes = nroEjes;
	}
	public Integer getNroRuedas() {
		return nroRuedas;
	}
	public void setNroRuedas(Integer nroRuedas) {
		this.nroRuedas = nroRuedas;
	}
	public String getNroCilindros() {
		return nroCilindros;
	}
	public void setNroCilindros(String nroCilindros) {
		this.nroCilindros = nroCilindros;
	}
	public Double getNroPesoSeco() {
		return nroPesoSeco;
	}
	public void setNroPesoSeco(Double nroPesoSeco) {
		this.nroPesoSeco = nroPesoSeco;
	}
	public Double getNroPesoBruto() {
		return nroPesoBruto;
	}
	public void setNroPesoBruto(Double nroPesoBruto) {
		this.nroPesoBruto = nroPesoBruto;
	}
	public Integer getNroPuertas() {
		return nroPuertas;
	}
	public void setNroPuertas(Integer nroPuertas) {
		this.nroPuertas = nroPuertas;
	}
	public String getNroSalidaEmergencia() {
		return nroSalidaEmergencia;
	}
	public void setNroSalidaEmergencia(String nroSalidaEmergencia) {
		this.nroSalidaEmergencia = nroSalidaEmergencia;
	}
	public Double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(Double kilometraje) {
		this.kilometraje = kilometraje;
	}
	public Double getAlto() {
		return alto;
	}
	public void setAlto(Double alto) {
		this.alto = alto;
	}
	public Double getLargo() {
		return largo;
	}
	public void setLargo(Double largo) {
		this.largo = largo;
	}
	public Double getAncho() {
		return ancho;
	}
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}
	public String getNroSoat() {
		return nroSoat;
	}
	public void setNroSoat(String nroSoat) {
		this.nroSoat = nroSoat;
	}
	public String getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}
	public String getFechaInicioTarjetaPropiedad() {
		return fechaInicioTarjetaPropiedad;
	}
	public void setFechaInicioTarjetaPropiedad(String fechaInicioTarjetaPropiedad) {
		this.fechaInicioTarjetaPropiedad = fechaInicioTarjetaPropiedad;
	}
	public String getFechaFinTarjetaPropiedad() {
		return fechaFinTarjetaPropiedad;
	}
	public void setFechaFinTarjetaPropiedad(String fechaFinTarjetaPropiedad) {
		this.fechaFinTarjetaPropiedad = fechaFinTarjetaPropiedad;
	}
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getInsObs() {
		return insObs;
	}
	public void setInsObs(String insObs) {
		this.insObs = insObs;
	}
	public String getFechImpre() {
		return fechImpre;
	}
	public void setFechImpre(String fechImpre) {
		this.fechImpre = fechImpre;
	}
	public String getUsuAnuCer() {
		return usuAnuCer;
	}
	public void setUsuAnuCer(String usuAnuCer) {
		this.usuAnuCer = usuAnuCer;
	}
	public String getUsuAnuCom() {
		return usuAnuCom;
	}
	public void setUsuAnuCom(String usuAnuCom) {
		this.usuAnuCom = usuAnuCom;
	}
	public String getObsAnu() {
		return obsAnu;
	}
	public void setObsAnu(String obsAnu) {
		this.obsAnu = obsAnu;
	}
	public Boolean getEstadoInfCert() {
		return estadoInfCert;
	}
	public void setEstadoInfCert(Boolean estadoInfCert) {
		this.estadoInfCert = estadoInfCert;
	}
	public String getNrohojaValorada() {
		return nrohojaValorada;
	}
	public void setNrohojaValorada(String nrohojaValorada) {
		this.nrohojaValorada = nrohojaValorada;
	}
	public String getUsuAnuIns() {
		return usuAnuIns;
	}
	public void setUsuAnuIns(String usuAnuIns) {
		this.usuAnuIns = usuAnuIns;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
