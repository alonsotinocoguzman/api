
package cl.dbnet.carga;

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
 *         &lt;element name="putCustomerETDLoadResult" type="{http://www.dbnet.cl}Mensaje" minOccurs="0"/&gt;
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
    "putCustomerETDLoadResult"
})
@XmlRootElement(name = "putCustomerETDLoadResponse")
public class PutCustomerETDLoadResponse {

    protected Mensaje putCustomerETDLoadResult;

    /**
     * Obtiene el valor de la propiedad putCustomerETDLoadResult.
     * 
     * @return
     *     possible object is
     *     {@link Mensaje }
     *     
     */
    public Mensaje getPutCustomerETDLoadResult() {
        return putCustomerETDLoadResult;
    }

    /**
     * Define el valor de la propiedad putCustomerETDLoadResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Mensaje }
     *     
     */
    public void setPutCustomerETDLoadResult(Mensaje value) {
        this.putCustomerETDLoadResult = value;
    }

}
