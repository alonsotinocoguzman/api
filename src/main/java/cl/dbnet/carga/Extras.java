
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Extras complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Extras"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EnvioPdf" type="{http://www.dbnet.cl}ArrayOfEnvioPdf" minOccurs="0"/&gt;
 *         &lt;element name="Adjuntos" type="{http://www.dbnet.cl}ArrayOfAdjuntos" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extras", propOrder = {
    "envioPdf",
    "adjuntos"
})
public class Extras {

    @XmlElement(name = "EnvioPdf")
    protected ArrayOfEnvioPdf envioPdf;
    @XmlElement(name = "Adjuntos")
    protected ArrayOfAdjuntos adjuntos;

    /**
     * Obtiene el valor de la propiedad envioPdf.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnvioPdf }
     *     
     */
    public ArrayOfEnvioPdf getEnvioPdf() {
        return envioPdf;
    }

    /**
     * Define el valor de la propiedad envioPdf.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnvioPdf }
     *     
     */
    public void setEnvioPdf(ArrayOfEnvioPdf value) {
        this.envioPdf = value;
    }

    /**
     * Obtiene el valor de la propiedad adjuntos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdjuntos }
     *     
     */
    public ArrayOfAdjuntos getAdjuntos() {
        return adjuntos;
    }

    /**
     * Define el valor de la propiedad adjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdjuntos }
     *     
     */
    public void setAdjuntos(ArrayOfAdjuntos value) {
        this.adjuntos = value;
    }

}
