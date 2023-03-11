package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.Date;

/**
 * Bean usado para el reporte de cierre de caja
 */
public class ReporteFinanzasBean implements Comparable<ReporteFinanzasBean> {
    private String formapago;
    private String tipocontado;
    private String entidad;
    private Date fecha;
    private String usuario;
    private String placamotor;
    private String nrodocumentocertificado;
    private String nrodocumentoinforme;
    private String inspeccionestado;
    private String nrocomprobante;
    private String comprobanteestado;
    private String nrooperacionBanco;
    private String nrooperacionTarjeta;
    private String vehiculoclase;
    private double neto;
    private double impuesto;
    private double importetotal;
    private String tipDesc;
    private Boolean doblemodalidad;
    private String nombreDescuento;
    private Long idPago;
    private String nroOt;
    public ReporteFinanzasBean(String formapago, String tipocontado, String entidad, Date fecha, String usuario, String placamotor,
                               String nrodocumentocertificado,String nrodocumentoinforme, String inspeccionestado, String nrocomprobante, String comprobanteestado,
                               String nrooperacionBanco,String nrooperacionTarjeta, String vehiculoclase, double neto, double impuesto, double importetotal,
                               String tipDesc, Boolean doblemodalidad, String nombreDescuento, Long idPago, String nroOt) {
        super();
        this.formapago = formapago;
        this.tipocontado = tipocontado;
        this.entidad = entidad;
        this.fecha = fecha;
        this.usuario = usuario;
        this.placamotor = placamotor;
        this.nrodocumentocertificado = nrodocumentocertificado;
        this.nrodocumentoinforme = nrodocumentoinforme;
        this.inspeccionestado = inspeccionestado;
        this.nrocomprobante = nrocomprobante;
        this.comprobanteestado = comprobanteestado;
        this.nrooperacionBanco = nrooperacionBanco;
        this.nrooperacionTarjeta = nrooperacionTarjeta;
        this.vehiculoclase = vehiculoclase;
        this.neto = neto;
        this.impuesto = impuesto;
        this.importetotal = importetotal;
        this.tipDesc = tipDesc;
        this.doblemodalidad = doblemodalidad;
        this.nombreDescuento=nombreDescuento;
        this.idPago = idPago;
        this.nroOt = nroOt;
    }
    public String getTipocontado() {
		return tipocontado;
	}
	public void setTipocontado(String tipocontado) {
		this.tipocontado = tipocontado;
	}
	public String getFormapago() {
        return formapago;
    }
    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }
    public String getEntidad() {
        return entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getNrodocumentoinforme() {
		return nrodocumentoinforme;
	}
	public void setNrodocumentoinforme(String nrodocumentoinforme) {
		this.nrodocumentoinforme = nrodocumentoinforme;
	}
	public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPlacamotor() {
        return placamotor;
    }
    public void setPlacamotor(String placamotor) {
        this.placamotor = placamotor;
    }
    public String getNrodocumentocertificado() {
		return nrodocumentocertificado;
	}
	public void setNrodocumentocertificado(String nrodocumentocertificado) {
		this.nrodocumentocertificado = nrodocumentocertificado;
	}
	public String getInspeccionestado() {
        return inspeccionestado;
    }
    public void setInspeccionestado(String inspeccionestado) {
        this.inspeccionestado = inspeccionestado;
    }
    public String getNrocomprobante() {
        return nrocomprobante;
    }
    public void setNrocomprobante(String nrocomprobante) {
        this.nrocomprobante = nrocomprobante;
    }
    public String getComprobanteestado() {
        return comprobanteestado;
    }
    public void setComprobanteestado(String comprobanteestado) {
        this.comprobanteestado = comprobanteestado;
    }
    public String getNrooperacionBanco() {
        return nrooperacionBanco;
    }
    public void setNrooperacionBanco(String nrooperacionBanco) {
        this.nrooperacionBanco = nrooperacionBanco;
    }
    public String getNrooperacionTarjeta() {
        return nrooperacionTarjeta;
    }
    public void setNrooperacionTarjeta(String nrooperacionTarjeta) {
        this.nrooperacionTarjeta = nrooperacionTarjeta;
    }
    public String getVehiculoclase() {
        return vehiculoclase;
    }
    public void setVehiculoclase(String vehiculoclase) {
        this.vehiculoclase = vehiculoclase;
    }
    public double getNeto() {
        return neto;
    }
    public void setNeto(double neto) {
        this.neto = neto;
    }
    public double getImpuesto() {
        return impuesto;
    }
    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }
    public double getImportetotal() {
        return importetotal;
    }
    public void setImportetotal(double importetotal) {
        this.importetotal = importetotal;
    }
	public String getTipDesc() {
		return tipDesc;
	}
	public void setTipDesc(String tipDesc) {
		this.tipDesc = tipDesc;
	}
	public Boolean getDoblemodalidad() {
		return doblemodalidad;
	}
	public void setDoblemodalidad(Boolean doblemodalidad) {
		this.doblemodalidad = doblemodalidad;
	}
	public String getNombreDescuento() {
		return nombreDescuento;
	}
	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	public ReporteFinanzasBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNroOt() {
		return nroOt;
	}
	public void setNroOt(String nroOt) {
		this.nroOt = nroOt;
	}
	@Override
	public int compareTo(ReporteFinanzasBean o) {
		// TODO Auto-generated method stub
		String a=new String(String.valueOf(this.getFormapago()));
        String b=new String(String.valueOf(o.getFormapago()));
        return a.compareTo(b);
	}
    
}
