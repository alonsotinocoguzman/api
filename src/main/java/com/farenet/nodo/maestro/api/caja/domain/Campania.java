package com.farenet.nodo.maestro.api.caja.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "campania")
public class Campania extends Auditoria implements Cloneable {

    public enum TipoVerificacion {
        NINGUNO, CUPON, VOLANTE
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = true)
    private String key;

    @ManyToOne()
    private Tipodescuento tipodescuento;

    private String nombre;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "fechinicio")
    private Timestamp fechInicio;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "fechfin")
    private Timestamp fechFin;

    @Column(nullable = true)
    private String observaciones;

    @Column(name = "tipoverificacion")
    private TipoVerificacion tipoVerificacion;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "campanias_personas", joinColumns = @JoinColumn(name = "campania_id"), inverseJoinColumns = @JoinColumn(name = "persona_id"))
    List<Persona> empresas;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campania")
    List<Campaniadetalle> campaniadetalles = new ArrayList();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campania")
    List<Verificaciondescuento> verificaciondescuentos = new ArrayList();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "campanias_plantas", joinColumns = @JoinColumn(name = "campania_id"), inverseJoinColumns = @JoinColumn(name = "planta_key"))
    List<Planta> plantas = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tipodescuento getTipodescuento() {
        return tipodescuento;
    }

    public void setTipodescuento(Tipodescuento tipodescuento) {
        this.tipodescuento = tipodescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFechInicio() {
        return fechInicio;
    }

    public void setFechInicio(Timestamp fechInicio) {
        this.fechInicio = fechInicio;
    }

    public Timestamp getFechFin() {
        return fechFin;
    }

    public void setFechFin(Timestamp fechFin) {
        this.fechFin = fechFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Persona> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Persona> empresas) {
        this.empresas = empresas;
    }

    public List<Campaniadetalle> getCampaniadetalles() {
        return campaniadetalles;
    }

    public void setCampaniadetalles(List<Campaniadetalle> campaniadetalles) {
        this.campaniadetalles = new ArrayList<>();
        for (Campaniadetalle campaniadetalle : campaniadetalles) {
            campaniadetalle.setCampania(this);
            this.campaniadetalles.add(campaniadetalle);
        }
    }

    public List<Verificaciondescuento> getVerificaciondescuentos() {
        return verificaciondescuentos;
    }

    public void setVerificaciondescuentos(List<Verificaciondescuento> verificaciondescuentos) {
        for (Verificaciondescuento verificaciondescuento : verificaciondescuentos) {
            verificaciondescuento.setCampania(this);
            this.verificaciondescuentos.add(verificaciondescuento);
        }
    }

    public TipoVerificacion getTipoVerificacion() {
        return tipoVerificacion;
    }

    public void setTipoVerificacion(TipoVerificacion tipoVerificacion) {
        this.tipoVerificacion = tipoVerificacion;
    }

    public List<Planta> getPlantas() {
        return plantas;
    }

    public void setPlantas(List<Planta> plantas) {
        this.plantas = plantas;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}