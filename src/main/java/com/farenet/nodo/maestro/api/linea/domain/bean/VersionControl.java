package com.farenet.nodo.maestro.api.linea.domain.bean;

public class VersionControl {
    public String nomversion;
    public String cambios;
    public String numero;
    public String ruta;
    public int idversion;
    public int estado;

    public String getNomversion() {
        return nomversion;
    }

    public void setNomversion(String nomversion) {
        this.nomversion = nomversion;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getIdversion() {
        return idversion;
    }

    public void setIdversion(int idversion) {
        this.idversion = idversion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
