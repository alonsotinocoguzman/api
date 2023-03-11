package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.caja.domain.Aseguradora;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateSerializer;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

@Entity
@Audited
@Table(name = "vehiculo")
public class Vehiculo extends Auditoria {

    @Column(nullable = true)
    public String categoriaextra;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Categoria categoria;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Vehiculoclase vehiculoclase;

    @Column(name = "distanciaeje1")
    public float distanciaEje1;

    public String getNroplacaantigua() {
        return nroplacaantigua;
    }

    public void setNroplacaantigua(String nroplacaantigua) {
        this.nroplacaantigua = nroplacaantigua;
    }

    @Column(name = "distanciaeje2")
    public float distanciaEje2;

    @Column(name = "distanciaeje3")
    public float distanciaEje3;

    @Column(name = "distanciaeje4")
    public float distanciaEje4;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Marca marca;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Carroceria carroceria;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Modelo modelo;

    @Column(nullable = true)
    private String nroplacaantigua;

    private Integer aniofabricacion;

    @ManyToOne()
    @JoinColumn(name = "marcacarroceria")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Marca marcacarroceria;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Combustible combustible;

    private String nroserie;

    @Id
    private String nromotor;

    
    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Color color;

    private Integer nroasientos;

    private Integer nropasajeros;

    private Integer nropisos;

    private double longitud;

    private double ancho;

    private double alto;

    private Integer nroejes;

    private Integer nroruedas;

    private Integer nrocilindros;

    private double pesoseco;

    private double cargautil;

    private double pesobruto;

    private Integer nropuertas;

    private Integer nrosalidaemergencia;

    private double kilometraje;

    private String nrosoat;

    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Aseguradora aseguradora;
    
    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "tipopoliza_key")
    private TipoPoliza tipoPoliza;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Timestamp fechfintarjetapropiedad;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Timestamp fechiniciotarjetapropiedad;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne()
    private Tarjetapropiedad tarjetapropiedad;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Timestamp fechemision;

/*    @ManyToOne()
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CertificadoraGas certificadoraGas;*/

    private String certificadoraGas;

    private String codigocertificado;;

    private Boolean sindctos;

    public Boolean getSindctos() {
        return sindctos;
    }

    public void setSindctos(Boolean sindctos) {
        this.sindctos = sindctos;
    }

    public Vehiculo() {
    }


    public Timestamp getFechemision() {
        return fechemision;
    }

    public void setFechemision(Timestamp fechemision) {
        this.fechemision = fechemision;
    }

    public String getCodigocertificado() {
        return codigocertificado;
    }

    public void setCodigocertificado(String codigocertificado) {
        this.codigocertificado = codigocertificado;
    }

    public TipoPoliza getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(TipoPoliza tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public double getAlto() {
        return this.alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public Integer getAniofabricacion() {
        return this.aniofabricacion;
    }

    public void setAniofabricacion(Integer aniofabricacion) {
        this.aniofabricacion = aniofabricacion;
    }

    public double getCargautil() {
        return this.cargautil;
    }

    public void setCargautil(double cargautil) {
        this.cargautil = cargautil;
    }

    public Carroceria getCarroceria() {
        return this.carroceria;
    }

    public void setCarroceria(Carroceria carroceria) {
        this.carroceria = carroceria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Vehiculoclase getClasevehiculo() {
        return this.vehiculoclase;
    }

    public void setClasevehiculo(Vehiculoclase vehiculoclase) {
        this.vehiculoclase = vehiculoclase;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Combustible getCombustible() {
        return this.combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public Timestamp getFechfintarjetapropiedad() {
        return this.fechfintarjetapropiedad;
    }

    public void setFechfintarjetapropiedad(Timestamp fechfintarjetapropiedad) {
        this.fechfintarjetapropiedad = fechfintarjetapropiedad;
    }

    public Timestamp getFechiniciotarjetapropiedad() {
        return this.fechiniciotarjetapropiedad;
    }

    public void setFechiniciotarjetapropiedad(Timestamp fechiniciotarjetapropiedad) {
        this.fechiniciotarjetapropiedad = fechiniciotarjetapropiedad;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModeloId() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getNroasientos() {
        return this.nroasientos;
    }

    public void setNroasientos(Integer nroasientos) {
        this.nroasientos = nroasientos;
    }

    public Integer getNrocilindros() {
        return this.nrocilindros;
    }

    public void setNrocilindros(Integer nrocilindros) {
        this.nrocilindros = nrocilindros;
    }

    public Integer getNroejes() {
        return this.nroejes;
    }

    public void setNroejes(Integer nroejes) {
        this.nroejes = nroejes;
    }

    public String getNromotor() {
        return this.nromotor;
    }

    public void setNromotor(String nromotor) {
        this.nromotor = nromotor;
    }

    public Integer getNropasajeros() {
        return this.nropasajeros;
    }

    public void setNropasajeros(Integer nropasajeros) {
        this.nropasajeros = nropasajeros;
    }

    public Integer getNroruedas() {
        return this.nroruedas;
    }

    public void setNroruedas(Integer nroruedas) {
        this.nroruedas = nroruedas;
    }

    public String getNroserie() {
        return this.nroserie;
    }

    public void setNroserie(String nroserie) {
        this.nroserie = nroserie;
    }

    public double getPesobruto() {
        return this.pesobruto;
    }

    public void setPesobruto(double pesobruto) {
        this.pesobruto = pesobruto;
    }

    public double getPesoseco() {
        return this.pesoseco;
    }

    public void setPesoseco(double pesoseco) {
        this.pesoseco = pesoseco;
    }
    
    public Tarjetapropiedad getTarjetapropiedad() {
		return tarjetapropiedad;
	}

	public void setTarjetapropiedad(Tarjetapropiedad tarjetapropiedad) {
		this.tarjetapropiedad = tarjetapropiedad;
	}

	public Vehiculoclase getVehiculoclase() {
        return vehiculoclase;
    }

    public void setVehiculoclase(Vehiculoclase vehiculoclase) {
        this.vehiculoclase = vehiculoclase;
    }

    public Marca getMarcacarroceria() {
        return marcacarroceria;
    }

    public void setMarcacarroceria(Marca marcacarroceria) {
        this.marcacarroceria = marcacarroceria;
    }

    public Integer getNropisos() {
        return nropisos;
    }

    public void setNropisos(Integer nropisos) {
        this.nropisos = nropisos;
    }

    public Integer getNropuertas() {
        return nropuertas;
    }

    public void setNropuertas(Integer nropuertas) {
        this.nropuertas = nropuertas;
    }

    public Integer getNrosalidaemergencia() {
        return nrosalidaemergencia;
    }

    public void setNrosalidaemergencia(Integer nrosalidaemergencia) {
        this.nrosalidaemergencia = nrosalidaemergencia;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getNrosoat() {
        return nrosoat;
    }

    public void setNrosoat(String nrosoat) {
        this.nrosoat = nrosoat;
    }

    public Aseguradora getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(Aseguradora aseguradora) {
        this.aseguradora = aseguradora;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public String getCategoriaextra() {
        return categoriaextra;
    }

    public void setCategoriaextra(String categoriaextra) {
        this.categoriaextra = categoriaextra;
    }

    public float getDistanciaEje1() {
        return distanciaEje1;
    }

    public void setDistanciaEje1(float distanciaEje1) {
        this.distanciaEje1 = distanciaEje1;
    }

    public float getDistanciaEje2() {
        return distanciaEje2;
    }

    public void setDistanciaEje2(float distanciaEje2) {
        this.distanciaEje2 = distanciaEje2;
    }

    public float getDistanciaEje3() {
        return distanciaEje3;
    }

    public void setDistanciaEje3(float distanciaEje3) {
        this.distanciaEje3 = distanciaEje3;
    }

    public float getDistanciaEje4() {
        return distanciaEje4;
    }

    public void setDistanciaEje4(float distanciaEje4) {
        this.distanciaEje4 = distanciaEje4;
    }
    public boolean isLiviano() {
        return (getPesobruto() <= 3500) ? true : false;
    }

}