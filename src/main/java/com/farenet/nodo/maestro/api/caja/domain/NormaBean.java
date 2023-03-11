package com.farenet.nodo.maestro.api.caja.domain;

public class NormaBean {
String nombrevalor;
String codigovalor;
Boolean grave;
Boolean leve;
Boolean muygrave;
Boolean manual;
int id;

    public Boolean getManual() {
        return manual;
    }

    public NormaBean setManual(Boolean manual) {
        this.manual = manual;
        return this;
    }

    public int getId() {
        return id;
    }

    public NormaBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombrevalor() {
        return nombrevalor;
    }

    public NormaBean setNombrevalor(String nombrevalor) {
        this.nombrevalor = nombrevalor;
        return this;
    }

    public String getCodigovalor() {
        return codigovalor;
    }

    public NormaBean setCodigovalor(String codigovalor) {
        this.codigovalor = codigovalor;
        return this;
    }

    public Boolean getGrave() {
        return grave;
    }

    public NormaBean setGrave(Boolean grave) {
        this.grave = grave;
        return this;
    }

    public Boolean getLeve() {
        return leve;
    }

    public NormaBean setLeve(Boolean leve) {
        this.leve = leve;
        return this;
    }

    public Boolean getMuygrave() {
        return muygrave;
    }

    public NormaBean setMuygrave(Boolean muygrave) {
        this.muygrave = muygrave;
        return this;
    }
}
