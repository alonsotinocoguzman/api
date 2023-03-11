
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosAdicionales complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosAdicionales"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoAdicSunat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NmrLineasDetalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NmrLineasAdicSunat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescripcionAdicsunat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosAdicionales", propOrder = {
    "tipoAdicSunat",
    "nmrLineasDetalle",
    "nmrLineasAdicSunat",
    "descripcionAdicsunat"
})
public class DatosAdicionales {

    @XmlElement(name = "TipoAdicSunat")
    protected String tipoAdicSunat;
    @XmlElement(name = "NmrLineasDetalle")
    protected String nmrLineasDetalle;
    @XmlElement(name = "NmrLineasAdicSunat")
    protected String nmrLineasAdicSunat;
    @XmlElement(name = "DescripcionAdicsunat")
    protected String descripcionAdicsunat;

    /**
     * Obtiene el valor de la propiedad tipoAdicSunat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAdicSunat() {
        return tipoAdicSunat;
    }

    /**
     * Define el valor de la propiedad tipoAdicSunat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAdicSunat(String value) {
        this.tipoAdicSunat = value;
    }

    /**
     * Obtiene el valor de la propiedad nmrLineasDetalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmrLineasDetalle() {
        return nmrLineasDetalle;
    }

    /**
     * Define el valor de la propiedad nmrLineasDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmrLineasDetalle(String value) {
        this.nmrLineasDetalle = value;
    }

    /**
     * Obtiene el valor de la propiedad nmrLineasAdicSunat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmrLineasAdicSunat() {
        return nmrLineasAdicSunat;
    }

    /**
     * Define el valor de la propiedad nmrLineasAdicSunat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmrLineasAdicSunat(String value) {
        this.nmrLineasAdicSunat = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionAdicsunat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionAdicsunat() {
        return descripcionAdicsunat;
    }

    /**
     * Define el valor de la propiedad descripcionAdicsunat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionAdicsunat(String value) {
        this.descripcionAdicsunat = value;
    }

}
