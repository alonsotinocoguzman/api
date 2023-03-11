package com.farenet.nodo.maestro.api.caja.domain.bean;

import com.farenet.nodo.maestro.api.caja.domain.Campania;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luis sanchez on 3/03/2017.
 */
public class CampaniaBean {

    public Long id;

    private String key;

    public String tipodescuento;

    public String nombre;

    public Date fechInicio;

    public Date fechFin;

    public String observaciones;

    public Campania.TipoVerificacion tipoVerificacion;

    public List<PersonaBean> empresas = new ArrayList<>();

    public List<CampaniadetalleBean> campaniadetalles = new ArrayList();

    public List<PlantaBean> plantas = new ArrayList<>();

    public List<String> verificaciondescuentos = new ArrayList();

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

    public Date getFechInicio() {
        return fechInicio;
    }

    public void setFechInicio(Date fechInicio) {
        this.fechInicio = fechInicio;
    }

    public Date getFechFin() {
        return fechFin;
    }

    public void setFechFin(Date fechFin) {
        this.fechFin = fechFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Campania.TipoVerificacion getTipoVerificacion() {
        return tipoVerificacion;
    }

    public void setTipoVerificacion(Campania.TipoVerificacion tipoVerificacion) {
        this.tipoVerificacion = tipoVerificacion;
    }

    public List<PersonaBean> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<PersonaBean> empresas) {
        this.empresas = empresas;
    }


    public List<CampaniadetalleBean> getCampaniadetalles() {
        return campaniadetalles;
    }

    public void setCampaniadetalles(List<CampaniadetalleBean> campaniadetalles) {
        this.campaniadetalles = campaniadetalles;
    }

    public List<String> getVerificaciondescuentos() {
        return verificaciondescuentos;
    }

    public void setVerificaciondescuentos(List<String> verificaciondescuentos) {
        this.verificaciondescuentos = verificaciondescuentos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<PlantaBean> getPlantas() {
        return plantas;
    }

    public void setPlantas(List<PlantaBean> plantas) {
        this.plantas = plantas;
    }
}
