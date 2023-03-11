package com.farenet.nodo.maestro.api.reporte.bean;

public class ReporteInspeccionesConsolidadas {
    String planta;
    String nroinformecerti;
    String estadoinspeccion;
    String fecha;
    String hora;
    String tienereinspeccion;
    String linea;
    String placa;
    String esduplicado;
    String hojavalorada;
    String resultado;
    String anhio;
    String mes;
    String dia;

    public String getAnhio() {
        return anhio;
    }

    public void setAnhio(String anhio) {
        this.anhio = anhio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHojavalorada() {
        return hojavalorada;
    }
    public void setHojavalorada(String hojavalorada) {
        this.hojavalorada = hojavalorada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getPlanta() {
        return planta;
    }

    public ReporteInspeccionesConsolidadas setPlanta(String planta) {
        this.planta = planta;
        return this;
    }

    public String getNroinformecerti() {
        return nroinformecerti;
    }

    public ReporteInspeccionesConsolidadas setNroinformecerti(String nroinformecerti) {
        this.nroinformecerti = nroinformecerti;
        return this;
    }

    public String getEstadoinspeccion() {
        return estadoinspeccion;
    }

    public ReporteInspeccionesConsolidadas setEstadoinspeccion(String estadoinspeccion) {
        this.estadoinspeccion = estadoinspeccion;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public ReporteInspeccionesConsolidadas setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHora() {
        return hora;
    }

    public ReporteInspeccionesConsolidadas setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public String getTienereinspeccion() {
        return tienereinspeccion;
    }

    public ReporteInspeccionesConsolidadas setTienereinspeccion(String tienereinspeccion) {
        this.tienereinspeccion = tienereinspeccion;
        return this;
    }

    public String getLinea() {
        return linea;
    }

    public ReporteInspeccionesConsolidadas setLinea(String linea) {
        this.linea = linea;
        return this;
    }

    public String getPlaca() {
        return placa;
    }

    public ReporteInspeccionesConsolidadas setPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public String getEsduplicado() {
        return esduplicado;
    }

    public ReporteInspeccionesConsolidadas setEsduplicado(String esduplicado) {
        this.esduplicado = esduplicado;
        return this;
    }
}
