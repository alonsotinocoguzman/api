
package cl.dbnet.bajas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cargaBajasResult" type="{http://www.dbnet.cl}Mensaje" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cargaBajasResult"
})
@XmlRootElement(name = "cargaBajasResponse")
public class CargaBajasResponse {

    protected Mensaje cargaBajasResult;

    /**
     * Obtiene el valor de la propiedad cargaBajasResult.
     * 
     * @return
     *     possible object is
     *     {@link Mensaje }
     *     
     */
    public Mensaje getCargaBajasResult() {
        return cargaBajasResult;
    }

    /**
     * Define el valor de la propiedad cargaBajasResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Mensaje }
     *     
     */
    public void setCargaBajasResult(Mensaje value) {
        this.cargaBajasResult = value;
    }

}
