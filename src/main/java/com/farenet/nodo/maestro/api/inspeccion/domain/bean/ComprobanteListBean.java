package com.farenet.nodo.maestro.api.inspeccion.domain.bean;

import java.util.Date;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Pago;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;

public class ComprobanteListBean {
	public String usuariocaja;
	public String nrocomprobante;
	public String fechapago;
	public String formapago;
	public String placa;
	public double subtotal;
	public double igv;
	public double total;
	public String comprobanteestado;
	public String linea;
	public String nroOt;
	public String nombreDescuento;
	public String tipoDescuento;
	public ComprobanteListBean(String usuariocaja, String nrocomprobante, String fechapago, String formapago, String placa, double subtotal, 
			double igv, double total, String comprobanteestado, String linea, String nroOt, String nombreDescuento, String tipoDescuento) {
		super();
		this.usuariocaja = usuariocaja;
		this.nrocomprobante = nrocomprobante;
		this.fechapago = fechapago;
		this.formapago = formapago;
		this.placa = placa;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.comprobanteestado = comprobanteestado;
		this.linea = linea;
		this.nroOt = nroOt;
		this.nombreDescuento = nombreDescuento;
		this.tipoDescuento = tipoDescuento;
	}
	
	
	
	
}
