package com.farenet.nodo.maestro.api.caja.domain.bean;

import java.security.Timestamp;
import java.util.Date;

public class SistEventsBean {
    String nroinspeccion;
    String usuario;
    String nombreevento;
    //Date fecha;

    public String getNroinspeccion() {
        return nroinspeccion;
    }

    public void setNroinspeccion(String nroinspeccion) {
        this.nroinspeccion = nroinspeccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }
}
