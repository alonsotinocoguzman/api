package com.farenet.nodo.maestro.api.caja.domain.bean;

public class ActualizaDescuentoBean {
    private Long id;
    private String ejecutivo;
    private Boolean estado;
    private String orden;
    private Boolean placa;
    private Boolean ruc;
    private String nroDias;
    private String nroCuotas;
    private String descripcion;
    private Integer key;

    public Integer getKey() {
        return key;
    }

    public ActualizaDescuentoBean setKey(Integer key) {
        this.key = key;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ActualizaDescuentoBean setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ActualizaDescuentoBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public ActualizaDescuentoBean setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
        return this;
    }

    public Boolean getEstado() {
        return estado;
    }

    public ActualizaDescuentoBean setEstado(Boolean estado) {
        this.estado = estado;
        return this;
    }

    public String getOrden() {
        return orden;
    }

    public ActualizaDescuentoBean setOrden(String orden) {
        this.orden = orden;
        return this;
    }

    public Boolean getPlaca() {
        return placa;
    }

    public ActualizaDescuentoBean setPlaca(Boolean placa) {
        this.placa = placa;
        return this;
    }

    public Boolean getRuc() {
        return ruc;
    }

    public ActualizaDescuentoBean setRuc(Boolean ruc) {
        this.ruc = ruc;
        return this;
    }

    public String getNroDias() {
        return nroDias;
    }

    public ActualizaDescuentoBean setNroDias(String nroDias) {
        this.nroDias = nroDias;
        return this;
    }

    public String getNroCuotas() {
        return nroCuotas;
    }

    public ActualizaDescuentoBean setNroCuotas(String nroCuotas) {
        this.nroCuotas = nroCuotas;
        return this;
    }

    @Override
    public String toString() {
        return "ActualizaDescuentoBean{" +
                "id=" + id +
                ", ejecutivo='" + ejecutivo + '\'' +
                ", estado=" + estado +
                ", orden='" + orden + '\'' +
                ", placa=" + placa +
                ", ruc=" + ruc +
                '}';
    }
}
