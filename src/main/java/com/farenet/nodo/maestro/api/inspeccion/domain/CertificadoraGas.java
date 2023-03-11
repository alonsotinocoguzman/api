package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "certificadoragas")
public class CertificadoraGas {
    @Id
    private Integer key;
    private String certificadora;
    private String direccion;
    private String region;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getCertificadora() {
        return certificadora;
    }

    public void setCertificadora(String certificadora) {
        this.certificadora = certificadora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
