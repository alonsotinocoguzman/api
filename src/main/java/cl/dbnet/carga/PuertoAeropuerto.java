
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PuertoAeropuerto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PuertoAeropuerto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinPuertoAerop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdPuertoAerop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NmbPuertoAerop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoPuertoAerop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PuertoAeropuerto", propOrder = {
    "nroLinPuertoAerop",
    "idPuertoAerop",
    "nmbPuertoAerop",
    "tipoPuertoAerop"
})
public class PuertoAeropuerto {

    @XmlElement(name = "NroLinPuertoAerop")
    protected String nroLinPuertoAerop;
    @XmlElement(name = "IdPuertoAerop")
    protected String idPuertoAerop;
    @XmlElement(name = "NmbPuertoAerop")
    protected String nmbPuertoAerop;
    @XmlElement(name = "TipoPuertoAerop")
    protected String tipoPuertoAerop;

    /**
     * Obtiene el valor de la propiedad nroLinPuertoAerop.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinPuertoAerop() {
        return nroLinPuertoAerop;
    }

    /**
     * Define el valor de la propiedad nroLinPuertoAerop.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinPuertoAerop(String value) {
        this.nroLinPuertoAerop = value;
    }

    /**
     * Obtiene el valor de la propiedad idPuertoAerop.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPuertoAerop() {
        return idPuertoAerop;
    }

    /**
     * Define el valor de la propiedad idPuertoAerop.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPuertoAerop(String value) {
        this.idPuertoAerop = value;
    }

    /**
     * Obtiene el valor de la propiedad nmbPuertoAerop.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmbPuertoAerop() {
        return nmbPuertoAerop;
    }

    /**
     * Define el valor de la propiedad nmbPuertoAerop.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmbPuertoAerop(String value) {
        this.nmbPuertoAerop = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoPuertoAerop.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPuertoAerop() {
        return tipoPuertoAerop;
    }

    /**
     * Define el valor de la propiedad tipoPuertoAerop.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPuertoAerop(String value) {
        this.tipoPuertoAerop = value;
    }

}
