package com.farenet.nodo.maestro.api.caja.domain.bean;

import com.farenet.nodo.maestro.api.caja.domain.Campaniadetalle;

/**
 * Created by luis sanchez on 1/03/2017.
 */
public class ConveniodetalleBean {
    private Long id;

    private String conceptoinspeccion;

    private boolean flatActivado = false;

    private Campaniadetalle.TipoDescuento tipoDescuento;

    private double valorDescuento;

    private double montoFlat;

    private String formaPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConceptoinspeccion() {
        return conceptoinspeccion;
    }

    public void setConceptoinspeccion(String conceptoinspeccion) {
        this.conceptoinspeccion = conceptoinspeccion;
    }

    public boolean isFlatActivado() {
        return flatActivado;
    }

    public void setFlatActivado(boolean flatActivado) {
        this.flatActivado = flatActivado;
    }

    public Campaniadetalle.TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(Campaniadetalle.TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public double getMontoFlat() {
        return montoFlat;
    }

    public void setMontoFlat(double montoFlat) {
        this.montoFlat = montoFlat;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}
