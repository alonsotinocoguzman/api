
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PropiedadAdicional complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PropiedadAdicional"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinDet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodConTrib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValConTrib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropiedadAdicional", propOrder = {
    "nroLinDet",
    "codConTrib",
    "valConTrib"
})
public class PropiedadAdicional {

    @XmlElement(name = "NroLinDet")
    protected String nroLinDet;
    @XmlElement(name = "CodConTrib")
    protected String codConTrib;
    @XmlElement(name = "ValConTrib")
    protected String valConTrib;

    /**
     * Obtiene el valor de la propiedad nroLinDet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinDet() {
        return nroLinDet;
    }

    /**
     * Define el valor de la propiedad nroLinDet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinDet(String value) {
        this.nroLinDet = value;
    }

    /**
     * Obtiene el valor de la propiedad codConTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodConTrib() {
        return codConTrib;
    }

    /**
     * Define el valor de la propiedad codConTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodConTrib(String value) {
        this.codConTrib = value;
    }

    /**
     * Obtiene el valor de la propiedad valConTrib.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValConTrib() {
        return valConTrib;
    }

    /**
     * Define el valor de la propiedad valConTrib.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValConTrib(String value) {
        this.valConTrib = value;
    }

}
