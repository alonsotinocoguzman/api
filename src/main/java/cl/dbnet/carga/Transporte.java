
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Transporte complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Transporte"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroDetalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HoraSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ManifiestoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumAsiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumDocPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrigenPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoDocPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transporte", propOrder = {
    "nroDetalle",
    "destinoPasajero",
    "fechaSalida",
    "horaSalida",
    "manifiestoPasajero",
    "nombPasajero",
    "numAsiento",
    "numDocPasajero",
    "origenPasajero",
    "tipoDocPasajero"
})
public class Transporte {

    @XmlElement(name = "NroDetalle")
    protected String nroDetalle;
    @XmlElement(name = "DestinoPasajero")
    protected String destinoPasajero;
    @XmlElement(name = "FechaSalida")
    protected String fechaSalida;
    @XmlElement(name = "HoraSalida")
    protected String horaSalida;
    @XmlElement(name = "ManifiestoPasajero")
    protected String manifiestoPasajero;
    @XmlElement(name = "NombPasajero")
    protected String nombPasajero;
    @XmlElement(name = "NumAsiento")
    protected String numAsiento;
    @XmlElement(name = "NumDocPasajero")
    protected String numDocPasajero;
    @XmlElement(name = "OrigenPasajero")
    protected String origenPasajero;
    @XmlElement(name = "TipoDocPasajero")
    protected String tipoDocPasajero;

    /**
     * Obtiene el valor de la propiedad nroDetalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroDetalle() {
        return nroDetalle;
    }

    /**
     * Define el valor de la propiedad nroDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroDetalle(String value) {
        this.nroDetalle = value;
    }

    /**
     * Obtiene el valor de la propiedad destinoPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinoPasajero() {
        return destinoPasajero;
    }

    /**
     * Define el valor de la propiedad destinoPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinoPasajero(String value) {
        this.destinoPasajero = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Define el valor de la propiedad fechaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSalida(String value) {
        this.fechaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad horaSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * Define el valor de la propiedad horaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraSalida(String value) {
        this.horaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad manifiestoPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManifiestoPasajero() {
        return manifiestoPasajero;
    }

    /**
     * Define el valor de la propiedad manifiestoPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManifiestoPasajero(String value) {
        this.manifiestoPasajero = value;
    }

    /**
     * Obtiene el valor de la propiedad nombPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombPasajero() {
        return nombPasajero;
    }

    /**
     * Define el valor de la propiedad nombPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombPasajero(String value) {
        this.nombPasajero = value;
    }

    /**
     * Obtiene el valor de la propiedad numAsiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumAsiento() {
        return numAsiento;
    }

    /**
     * Define el valor de la propiedad numAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumAsiento(String value) {
        this.numAsiento = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocPasajero() {
        return numDocPasajero;
    }

    /**
     * Define el valor de la propiedad numDocPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocPasajero(String value) {
        this.numDocPasajero = value;
    }

    /**
     * Obtiene el valor de la propiedad origenPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenPasajero() {
        return origenPasajero;
    }

    /**
     * Define el valor de la propiedad origenPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenPasajero(String value) {
        this.origenPasajero = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocPasajero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocPasajero() {
        return tipoDocPasajero;
    }

    /**
     * Define el valor de la propiedad tipoDocPasajero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocPasajero(String value) {
        this.tipoDocPasajero = value;
    }

}
