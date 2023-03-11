
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tramo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tramo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinTramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ModalidadTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechInicioTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUCTranspor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRucTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazoTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CertVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PlacaVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tramo", propOrder = {
    "nroLinTramo",
    "modalidadTraslado",
    "fechInicioTraslado",
    "rucTranspor",
    "tipoRucTrans",
    "razoTrans",
    "certVehiculo",
    "placaVehiculo"
})
public class Tramo {

    @XmlElement(name = "NroLinTramo")
    protected String nroLinTramo;
    @XmlElement(name = "ModalidadTraslado")
    protected String modalidadTraslado;
    @XmlElement(name = "FechInicioTraslado")
    protected String fechInicioTraslado;
    @XmlElement(name = "RUCTranspor")
    protected String rucTranspor;
    @XmlElement(name = "TipoRucTrans")
    protected String tipoRucTrans;
    @XmlElement(name = "RazoTrans")
    protected String razoTrans;
    @XmlElement(name = "CertVehiculo")
    protected String certVehiculo;
    @XmlElement(name = "PlacaVehiculo")
    protected String placaVehiculo;

    /**
     * Obtiene el valor de la propiedad nroLinTramo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinTramo() {
        return nroLinTramo;
    }

    /**
     * Define el valor de la propiedad nroLinTramo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinTramo(String value) {
        this.nroLinTramo = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidadTraslado() {
        return modalidadTraslado;
    }

    /**
     * Define el valor de la propiedad modalidadTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidadTraslado(String value) {
        this.modalidadTraslado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechInicioTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechInicioTraslado() {
        return fechInicioTraslado;
    }

    /**
     * Define el valor de la propiedad fechInicioTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechInicioTraslado(String value) {
        this.fechInicioTraslado = value;
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

}
