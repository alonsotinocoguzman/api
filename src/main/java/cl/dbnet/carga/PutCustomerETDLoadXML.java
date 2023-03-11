
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
 *         &lt;element name="lsXML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "lsXML"
})
@XmlRootElement(name = "putCustomerETDLoadXML")
public class PutCustomerETDLoadXML {

    protected String lsXML;

    /**
     * Obtiene el valor de la propiedad lsXML.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLsXML() {
        return lsXML;
    }

    /**
     * Define el valor de la propiedad lsXML.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLsXML(String value) {
        this.lsXML = value;
    }

}
