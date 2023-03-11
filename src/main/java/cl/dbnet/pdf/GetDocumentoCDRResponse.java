
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
 *         &lt;element name="getDocumentoCDRResult" type="{http://www.dbnet.cl}ArrayOfString" minOccurs="0"/&gt;
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
    "getDocumentoCDRResult"
})
@XmlRootElement(name = "getDocumentoCDRResponse")
public class GetDocumentoCDRResponse {

    protected ArrayOfString getDocumentoCDRResult;

    /**
     * Obtiene el valor de la propiedad getDocumentoCDRResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetDocumentoCDRResult() {
        return getDocumentoCDRResult;
    }

    /**
     * Define el valor de la propiedad getDocumentoCDRResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetDocumentoCDRResult(ArrayOfString value) {
        this.getDocumentoCDRResult = value;
    }

}
