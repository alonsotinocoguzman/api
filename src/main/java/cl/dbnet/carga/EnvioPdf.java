
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnvioPdf complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EnvioPdf"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CamposEnvioPdf" type="{http://www.dbnet.cl}CamposEnvioPdf" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvioPdf", propOrder = {
    "camposEnvioPdf"
})
public class EnvioPdf {

    @XmlElement(name = "CamposEnvioPdf")
    protected CamposEnvioPdf camposEnvioPdf;

    /**
     * Obtiene el valor de la propiedad camposEnvioPdf.
     * 
     * @return
     *     possible object is
     *     {@link CamposEnvioPdf }
     *     
     */
    public CamposEnvioPdf getCamposEnvioPdf() {
        return camposEnvioPdf;
    }

    /**
     * Define el valor de la propiedad camposEnvioPdf.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposEnvioPdf }
     *     
     */
    public void setCamposEnvioPdf(CamposEnvioPdf value) {
        this.camposEnvioPdf = value;
    }

}
