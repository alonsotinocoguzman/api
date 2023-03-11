
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosGuia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosGuia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MotivoTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndTrasbordo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalPesoTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NroBultos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdContenedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RucTercero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRucTercero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazonTercero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PlacaVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CertVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Marcavehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Licencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalPesoBruto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRucTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazoTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ModalidadTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUCTranspor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnidadMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumeroRegistroMTC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferencial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferencialMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndSubcontratacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosGuia", propOrder = {
    "observaciones",
    "motivoTraslado",
    "descTraslado",
    "indTrasbordo",
    "totalPesoTraslado",
    "nroBultos",
    "idContenedor",
    "rucTercero",
    "tipoRucTercero",
    "razonTercero",
    "dirParUbiGeo",
    "dirParDireccion",
    "dirLlegUbiGeo",
    "dirLlegDireccion",
    "dirParUrbaniza",
    "dirllegUrbaniza",
    "dirParDepart",
    "dirParDistrito",
    "dirParCodPais",
    "dirParProvincia",
    "dirLlegProvincia",
    "dirLlegDepart",
    "dirLlegDistrito",
    "dirLlegCodPais",
    "placaVehiculo",
    "certVehiculo",
    "marcavehiculo",
    "licencia",
    "totalPesoBruto",
    "tipoRucTrans",
    "razoTrans",
    "modalidadTransporte",
    "rucTranspor",
    "unidadMedida",
    "numeroRegistroMTC",
    "montoReferencial",
    "montoReferencialMoneda",
    "indSubcontratacion"
})
public class DatosGuia {

    @XmlElement(name = "Observaciones")
    protected String observaciones;
    @XmlElement(name = "MotivoTraslado")
    protected String motivoTraslado;
    @XmlElement(name = "DescTraslado")
    protected String descTraslado;
    @XmlElement(name = "IndTrasbordo")
    protected String indTrasbordo;
    @XmlElement(name = "TotalPesoTraslado")
    protected String totalPesoTraslado;
    @XmlElement(name = "NroBultos")
    protected String nroBultos;
    @XmlElement(name = "IdContenedor")
    protected String idContenedor;
    @XmlElement(name = "RucTercero")
    protected String rucTercero;
    @XmlElement(name = "TipoRucTercero")
    protected String tipoRucTercero;
    @XmlElement(name = "RazonTercero")
    protected String razonTercero;
    @XmlElement(name = "DirParUbiGeo")
    protected String dirParUbiGeo;
    @XmlElement(name = "DirParDireccion")
    protected String dirParDireccion;
    @XmlElement(name = "DirLlegUbiGeo")
    protected String dirLlegUbiGeo;
    @XmlElement(name = "DirLlegDireccion")
    protected String dirLlegDireccion;
    @XmlElement(name = "DirParUrbaniza")
    protected String dirParUrbaniza;
    @XmlElement(name = "DirllegUrbaniza")
    protected String dirllegUrbaniza;
    @XmlElement(name = "DirParDepart")
    protected String dirParDepart;
    @XmlElement(name = "DirParDistrito")
    protected String dirParDistrito;
    @XmlElement(name = "DirParCodPais")
    protected String dirParCodPais;
    @XmlElement(name = "DirParProvincia")
    protected String dirParProvincia;
    @XmlElement(name = "DirLlegProvincia")
    protected String dirLlegProvincia;
    @XmlElement(name = "DirLlegDepart")
    protected String dirLlegDepart;
    @XmlElement(name = "DirLlegDistrito")
    protected String dirLlegDistrito;
    @XmlElement(name = "DirLlegCodPais")
    protected String dirLlegCodPais;
    @XmlElement(name = "PlacaVehiculo")
    protected String placaVehiculo;
    @XmlElement(name = "CertVehiculo")
    protected String certVehiculo;
    @XmlElement(name = "Marcavehiculo")
    protected String marcavehiculo;
    @XmlElement(name = "Licencia")
    protected String licencia;
    @XmlElement(name = "TotalPesoBruto")
    protected String totalPesoBruto;
    @XmlElement(name = "TipoRucTrans")
    protected String tipoRucTrans;
    @XmlElement(name = "RazoTrans")
    protected String razoTrans;
    @XmlElement(name = "ModalidadTransporte")
    protected String modalidadTransporte;
    @XmlElement(name = "RUCTranspor")
    protected String rucTranspor;
    @XmlElement(name = "UnidadMedida")
    protected String unidadMedida;
    @XmlElement(name = "NumeroRegistroMTC")
    protected String numeroRegistroMTC;
    @XmlElement(name = "MontoReferencial")
    protected String montoReferencial;
    @XmlElement(name = "MontoReferencialMoneda")
    protected String montoReferencialMoneda;
    @XmlElement(name = "IndSubcontratacion")
    protected String indSubcontratacion;

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad motivoTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    /**
     * Define el valor de la propiedad motivoTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoTraslado(String value) {
        this.motivoTraslado = value;
    }

    /**
     * Obtiene el valor de la propiedad descTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTraslado() {
        return descTraslado;
    }

    /**
     * Define el valor de la propiedad descTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTraslado(String value) {
        this.descTraslado = value;
    }

    /**
     * Obtiene el valor de la propiedad indTrasbordo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndTrasbordo() {
        return indTrasbordo;
    }

    /**
     * Define el valor de la propiedad indTrasbordo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndTrasbordo(String value) {
        this.indTrasbordo = value;
    }

    /**
     * Obtiene el valor de la propiedad totalPesoTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPesoTraslado() {
        return totalPesoTraslado;
    }

    /**
     * Define el valor de la propiedad totalPesoTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPesoTraslado(String value) {
        this.totalPesoTraslado = value;
    }

    /**
     * Obtiene el valor de la propiedad nroBultos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroBultos() {
        return nroBultos;
    }

    /**
     * Define el valor de la propiedad nroBultos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroBultos(String value) {
        this.nroBultos = value;
    }

    /**
     * Obtiene el valor de la propiedad idContenedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdContenedor() {
        return idContenedor;
    }

    /**
     * Define el valor de la propiedad idContenedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdContenedor(String value) {
        this.idContenedor = value;
    }

    /**
     * Obtiene el valor de la propiedad rucTercero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRucTercero() {
        return rucTercero;
    }

    /**
     * Define el valor de la propiedad rucTercero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRucTercero(String value) {
        this.rucTercero = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRucTercero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRucTercero() {
        return tipoRucTercero;
    }

    /**
     * Define el valor de la propiedad tipoRucTercero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRucTercero(String value) {
        this.tipoRucTercero = value;
    }

    /**
     * Obtiene el valor de la propiedad razonTercero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonTercero() {
        return razonTercero;
    }

    /**
     * Define el valor de la propiedad razonTercero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonTercero(String value) {
        this.razonTercero = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParUbiGeo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParUbiGeo() {
        return dirParUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirParUbiGeo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParUbiGeo(String value) {
        this.dirParUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDireccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParDireccion() {
        return dirParDireccion;
    }

    /**
     * Define el valor de la propiedad dirParDireccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParDireccion(String value) {
        this.dirParDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegUbiGeo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegUbiGeo() {
        return dirLlegUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirLlegUbiGeo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegUbiGeo(String value) {
        this.dirLlegUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDireccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegDireccion() {
        return dirLlegDireccion;
    }

    /**
     * Define el valor de la propiedad dirLlegDireccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegDireccion(String value) {
        this.dirLlegDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParUrbaniza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParUrbaniza() {
        return dirParUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirParUrbaniza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParUrbaniza(String value) {
        this.dirParUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegUrbaniza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirllegUrbaniza() {
        return dirllegUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirllegUrbaniza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirllegUrbaniza(String value) {
        this.dirllegUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDepart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParDepart() {
        return dirParDepart;
    }

    /**
     * Define el valor de la propiedad dirParDepart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParDepart(String value) {
        this.dirParDepart = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDistrito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParDistrito() {
        return dirParDistrito;
    }

    /**
     * Define el valor de la propiedad dirParDistrito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParDistrito(String value) {
        this.dirParDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParCodPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParCodPais() {
        return dirParCodPais;
    }

    /**
     * Define el valor de la propiedad dirParCodPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParCodPais(String value) {
        this.dirParCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParProvincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirParProvincia() {
        return dirParProvincia;
    }

    /**
     * Define el valor de la propiedad dirParProvincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirParProvincia(String value) {
        this.dirParProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegProvincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegProvincia() {
        return dirLlegProvincia;
    }

    /**
     * Define el valor de la propiedad dirLlegProvincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegProvincia(String value) {
        this.dirLlegProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDepart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegDepart() {
        return dirLlegDepart;
    }

    /**
     * Define el valor de la propiedad dirLlegDepart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegDepart(String value) {
        this.dirLlegDepart = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDistrito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegDistrito() {
        return dirLlegDistrito;
    }

    /**
     * Define el valor de la propiedad dirLlegDistrito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegDistrito(String value) {
        this.dirLlegDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegCodPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirLlegCodPais() {
        return dirLlegCodPais;
    }

    /**
     * Define el valor de la propiedad dirLlegCodPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirLlegCodPais(String value) {
        this.dirLlegCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad placaVehiculo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    /**
     * Define el valor de la propiedad placaVehiculo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacaVehiculo(String value) {
        this.placaVehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad certVehiculo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertVehiculo() {
        return certVehiculo;
    }

    /**
     * Define el valor de la propiedad certVehiculo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertVehiculo(String value) {
        this.certVehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad marcavehiculo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcavehiculo() {
        return marcavehiculo;
    }

    /**
     * Define el valor de la propiedad marcavehiculo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcavehiculo(String value) {
        this.marcavehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad licencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * Define el valor de la propiedad licencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicencia(String value) {
        this.licencia = value;
    }

    /**
     * Obtiene el valor de la propiedad totalPesoBruto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPesoBruto() {
        return totalPesoBruto;
    }

    /**
     * Define el valor de la propiedad totalPesoBruto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPesoBruto(String value) {
        this.totalPesoBruto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRucTrans.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRucTrans() {
        return tipoRucTrans;
    }

    /**
     * Define el valor de la propiedad tipoRucTrans.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRucTrans(String value) {
        this.tipoRucTrans = value;
    }

    /**
     * Obtiene el valor de la propiedad razoTrans.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazoTrans() {
        return razoTrans;
    }

    /**
     * Define el valor de la propiedad razoTrans.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazoTrans(String value) {
        this.razoTrans = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadTransporte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidadTransporte() {
        return modalidadTransporte;
    }

    /**
     * Define el valor de la propiedad modalidadTransporte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidadTransporte(String value) {
        this.modalidadTransporte = value;
    }

    /**
     * Obtiene el valor de la propiedad rucTranspor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUCTranspor() {
        return rucTranspor;
    }

    /**
     * Define el valor de la propiedad rucTranspor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUCTranspor(String value) {
        this.rucTranspor = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadMedida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Define el valor de la propiedad unidadMedida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadMedida(String value) {
        this.unidadMedida = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroRegistroMTC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRegistroMTC() {
        return numeroRegistroMTC;
    }

    /**
     * Define el valor de la propiedad numeroRegistroMTC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRegistroMTC(String value) {
        this.numeroRegistroMTC = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferencial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferencial() {
        return montoReferencial;
    }

    /**
     * Define el valor de la propiedad montoReferencial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferencial(String value) {
        this.montoReferencial = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferencialMoneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferencialMoneda() {
        return montoReferencialMoneda;
    }

    /**
     * Define el valor de la propiedad montoReferencialMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferencialMoneda(String value) {
        this.montoReferencialMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad indSubcontratacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndSubcontratacion() {
        return indSubcontratacion;
    }

    /**
     * Define el valor de la propiedad indSubcontratacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndSubcontratacion(String value) {
        this.indSubcontratacion = value;
    }

}
