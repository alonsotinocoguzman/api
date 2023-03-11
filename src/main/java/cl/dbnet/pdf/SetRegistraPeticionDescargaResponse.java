
package cl.dbnet.pdf;

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
 *         &lt;element name="setRegistraPeticionDescargaResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "setRegistraPeticionDescargaResult"
})
@XmlRootElement(name = "setRegistraPeticionDescargaResponse")
public class SetRegistraPeticionDescargaResponse {

    protected boolean setRegistraPeticionDescargaResult;

    /**
     * Obtiene el valor de la propiedad setRegistraPeticionDescargaResult.
     * 
     */
    public boolean isSetRegistraPeticionDescargaResult() {
        return setRegistraPeticionDescargaResult;
    }

    /**
     * Define el valor de la propiedad setRegistraPeticionDescargaResult.
     * 
     */
    public void setSetRegistraPeticionDescargaResult(boolean value) {
        this.setRegistraPeticionDescargaResult = value;
    }

}
