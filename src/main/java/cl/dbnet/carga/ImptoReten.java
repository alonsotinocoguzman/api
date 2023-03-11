
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ImptoReten complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ImptoReten"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodigoImpuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TasaImpuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoImpuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoImpuestoBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImptoReten", propOrder = {
    "codigoImpuesto",
    "tasaImpuesto",
    "montoImpuesto",
    "montoImpuestoBase"
})
public class ImptoReten {

    @XmlElement(name = "CodigoImpuesto")
    protected String codigoImpuesto;
    @XmlElement(name = "TasaImpuesto")
    protected String tasaImpuesto;
    @XmlElement(name = "MontoImpuesto")
    protected String montoImpuesto;
    @XmlElement(name = "MontoImpuestoBase")
    protected String montoImpuestoBase;

    /**
     * Obtiene el valor de la propiedad codigoImpuesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    /**
     * Define el valor de la propiedad codigoImpuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoImpuesto(String value) {
        this.codigoImpuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaImpuesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTasaImpuesto() {
        return tasaImpuesto;
    }

    /**
     * Define el valor de la propiedad tasaImpuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTasaImpuesto(String value) {
        this.tasaImpuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad montoImpuesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoImpuesto() {
        return montoImpuesto;
    }

    /**
     * Define el valor de la propiedad montoImpuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoImpuesto(String value) {
        this.montoImpuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad montoImpuestoBase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoImpuestoBase() {
        return montoImpuestoBase;
    }

    /**
     * Define el valor de la propiedad montoImpuestoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoImpuestoBase(String value) {
        this.montoImpuestoBase = value;
    }

}
