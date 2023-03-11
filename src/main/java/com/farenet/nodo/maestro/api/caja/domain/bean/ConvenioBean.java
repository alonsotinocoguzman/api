package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis sanchez on 1/03/2017.
 */
public class ConvenioBean {
    public Long id;

    private String key;

    public String tipodescuento;

    public String nombre;

    public String ejecutivo;

    public String observaciones;

    public int condicionLimitePago;

    public double lineaCreditoMaxima;

    public double lineaCreditoDisponible;

    public boolean lineaCreditoDeshabilitada;

    public List<ConveniodetalleBean> conveniodetalles = new ArrayList();

    public String persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipodescuento() {
        return tipodescuento;
    }

    public void setTipodescuento(String tipodescuento) {
        this.tipodescuento = tipodescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCondicionLimitePago() {
        return condicionLimitePago;
    }

    public void setCondicionLimitePago(int condicionLimitePago) {
        this.condicionLimitePago = condicionLimitePago;
    }

    public double getLineaCreditoMaxima() {
        return lineaCreditoMaxima;
    }

    public void setLineaCreditoMaxima(double lineaCreditoMaxima) {
        this.lineaCreditoMaxima = lineaCreditoMaxima;
    }

    public double getLineaCreditoDisponible() {
        return lineaCreditoDisponible;
    }

    public void setLineaCreditoDisponible(double lineaCreditoDisponible) {
        this.lineaCreditoDisponible = lineaCreditoDisponible;
    }

    public boolean isLineaCreditoDeshabilitada() {
        return lineaCreditoDeshabilitada;
    }

    public void setLineaCreditoDeshabilitada(boolean lineaCreditoDeshabilitada) {
        this.lineaCreditoDeshabilitada = lineaCreditoDeshabilitada;
    }

    public List<ConveniodetalleBean> getConveniodetalles() {
        return conveniodetalles;
    }

    public void setConveniodetalles(List<ConveniodetalleBean> conveniodetalles) {
        this.conveniodetalles = conveniodetalles;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
