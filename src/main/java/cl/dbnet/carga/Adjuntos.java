
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Adjuntos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Adjuntos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CamposAdjuntos" type="{http://www.dbnet.cl}CamposAdjuntos" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Adjuntos", propOrder = {
    "camposAdjuntos"
})
public class Adjuntos {

    @XmlElement(name = "CamposAdjuntos")
    protected CamposAdjuntos camposAdjuntos;

    /**
     * Obtiene el valor de la propiedad camposAdjuntos.
     * 
     * @return
     *     possible object is
     *     {@link CamposAdjuntos }
     *     
     */
    public CamposAdjuntos getCamposAdjuntos() {
        return camposAdjuntos;
    }

    /**
     * Define el valor de la propiedad camposAdjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposAdjuntos }
     *     
     */
    public void setCamposAdjuntos(CamposAdjuntos value) {
        this.camposAdjuntos = value;
    }

}
