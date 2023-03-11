package com.farenet.nodo.maestro.api.caja.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.util.Auditoria;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "convenio")
public class Convenio extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = true)
    private String key;

    @ManyToOne()
    private Tipodescuento tipodescuento;

    private String nombre;

    @ManyToOne()
    @Immutable
    private Ejecutivo ejecutivo;

    @Column(nullable = true)
    private String observaciones;

    @Column(name = "condicionlimitepago")
    private int condicionLimitePago;

    @Column(name = "lineacreditomaxima")
    private double lineaCreditoMaxima;

    @Column(name = "lineacreditodisponible")
    private double lineaCreditoDisponible;

    @Column(name = "lineacreditodeshabilitada")
    private boolean lineaCreditoDeshabilitada;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convenio")
    private List<Conveniodetalle> conveniodetalles = new ArrayList();

    @ManyToOne()
    private Persona persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ejecutivo getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(Ejecutivo ejecutivo) {
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

    public Tipodescuento getTipodescuento() {
        return tipodescuento;
    }

    public void setTipodescuento(Tipodescuento tipodescuento) {
        this.tipodescuento = tipodescuento;
    }

    public List<Conveniodetalle> getConveniodetalles() {
        return conveniodetalles;
    }

    public void setConveniodetalles(List<Conveniodetalle> conveniodetalles) {
        for (Conveniodetalle conveniodetalle : conveniodetalles) {
            conveniodetalle.setConvenio(this);
            this.conveniodetalles.add(conveniodetalle);
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}