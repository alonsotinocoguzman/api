package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.inspeccion.domain.view.InspeccionViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conceptoinspeccion")
public class Conceptoinspeccion extends Auditoria{

    @JsonView({ComprobanteViews.CrudComprobante.class, InspeccionViews.CrudInspecciones.class})
    @Column(nullable = true, length = 150)
    private String abreviatura;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = true, name = "pasaporlinea")
    private Boolean pasaPorLinea;

    @Column(nullable = true, name = "solofotos")
    private Boolean soloFotos;

    @Id
    @Column(length = 20)
    private String key;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "conceptoinspeccion")
    private List<Conceptoinspecciondetalle> conceptoinspecciondetalles = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Conceptoinspeccion() {
    }

    
    public Boolean getSoloFotos() {
		return soloFotos;
	}

	public void setSoloFotos(Boolean soloFotos) {
		this.soloFotos = soloFotos;
	}

	public String getAbreviatura() {
        return this.abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean isPasaPorLinea() {
        return pasaPorLinea;
    }

    public void setPasaPorLinea(Boolean pasaPorLinea) {
        this.pasaPorLinea = pasaPorLinea;
    }

    public Boolean getPasaPorLinea() {
        return pasaPorLinea;
    }

    public List<Conceptoinspecciondetalle> getConceptoinspecciondetalles() {
        return conceptoinspecciondetalles;
    }

    public void setConceptoinspecciondetalles(List<Conceptoinspecciondetalle> conceptoinspecciondetalles) {
        this.conceptoinspecciondetalles = new ArrayList<>();
        this.conceptoinspecciondetalles = conceptoinspecciondetalles;
    }

    public void addConceptoinspecciondetalle(Conceptoinspecciondetalle conceptoinspecciondetalle) {
        conceptoinspecciondetalle.setConceptoinspeccion(this);
        this.conceptoinspecciondetalles.add(conceptoinspecciondetalle);
    }
    
    public double getValor(String planta) {
        double valor = 0;
        for (Conceptoinspecciondetalle conceptoinspecciondetalle : this.conceptoinspecciondetalles) {
            if (conceptoinspecciondetalle.getPlanta().getKey().equals(planta)) {
                valor = conceptoinspecciondetalle.getValor();
                break;
            }
        }
        return valor;
    }
}