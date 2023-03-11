
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Conductor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Conductor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinConductor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ConductorNroDocId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ConductorTipoDocId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Conductor", propOrder = {
    "nroLinConductor",
    "conductorNroDocId",
    "conductorTipoDocId"
})
public class Conductor {

    @XmlElement(name = "NroLinConductor")
    protected String nroLinConductor;
    @XmlElement(name = "ConductorNroDocId")
    protected String conductorNroDocId;
    @XmlElement(name = "ConductorTipoDocId")
    protected String conductorTipoDocId;

    /**
     * Obtiene el valor de la propiedad nroLinConductor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinConductor() {
        return nroLinConductor;
    }

    /**
     * Define el valor de la propiedad nroLinConductor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinConductor(String value) {
        this.nroLinConductor = value;
    }

    /**
     * Obtiene el valor de la propiedad conductorNroDocId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConductorNroDocId() {
        return conductorNroDocId;
    }

    /**
     * Define el valor de la propiedad conductorNroDocId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConductorNroDocId(String value) {
        this.conductorNroDocId = value;
    }

    /**
     * Obtiene el valor de la propiedad conductorTipoDocId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConductorTipoDocId() {
        return conductorTipoDocId;
    }

    /**
     * Define el valor de la propiedad conductorTipoDocId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConductorTipoDocId(String value) {
        this.conductorTipoDocId = value;
    }

}
