package com.farenet.nodo.maestro.api.caja.domain.bean;

import com.farenet.nodo.maestro.api.caja.domain.Campaniadetalle;

/**
 * Created by luis sanchez on 2/03/2017.
 */
public class CampaniadetalleBean {

    public Long id;

    public String conceptoinspeccion;
    
    private boolean flatActivado = false;

    public Campaniadetalle.TipoDescuento tipoDescuento;

    public double valorDescuento;

    public String campania;
    
    private double montoFlat;

    public String planta;

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

    public String getCampania() {
        return campania;
    }

    public void setCampania(String campania) {
        this.campania = campania;
    }


    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

	public boolean isFlatActivado() {
		return flatActivado;
	}

	public void setFlatActivado(boolean flatActivado) {
		this.flatActivado = flatActivado;
	}

	public double getMontoFlat() {
		return montoFlat;
	}

	public void setMontoFlat(double montoFlat) {
		this.montoFlat = montoFlat;
	}
}
