package cl.dbnet.carga;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Pago complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="Pago"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaVencCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pago", propOrder = {
        "cuota",
        "montoCuota",
        "fechaVencCuota"
})
public class Pago {

    @XmlElement(name = "Cuota")
    protected String cuota;
    @XmlElement(name = "MontoCuota")
    protected String montoCuota;
    @XmlElement(name = "FechaVencCuota")
    protected String fechaVencCuota;

    /**
     * Obtiene el valor de la propiedad cuota.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCuota() {
        return cuota;
    }

    /**
     * Define el valor de la propiedad cuota.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCuota(String value) {
        this.cuota = value;
    }

    /**
     * Obtiene el valor de la propiedad montoCuota.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMontoCuota() {
        return montoCuota;
    }

    /**
     * Define el valor de la propiedad montoCuota.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMontoCuota(String value) {
        this.montoCuota = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencCuota.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaVencCuota() {
        return fechaVencCuota;
    }

    /**
     * Define el valor de la propiedad fechaVencCuota.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaVencCuota(String value) {
        this.fechaVencCuota = value;
    }

}

