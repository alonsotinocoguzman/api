
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
 *         &lt;element name="getDocumentoPDFResult" type="{http://www.dbnet.cl}ArrayOfString" minOccurs="0"/&gt;
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
    "getDocumentoPDFResult"
})
@XmlRootElement(name = "getDocumentoPDFResponse")
public class GetDocumentoPDFResponse {

    protected ArrayOfString getDocumentoPDFResult;

    /**
     * Obtiene el valor de la propiedad getDocumentoPDFResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetDocumentoPDFResult() {
        return getDocumentoPDFResult;
    }

    /**
     * Define el valor de la propiedad getDocumentoPDFResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetDocumentoPDFResult(ArrayOfString value) {
        this.getDocumentoPDFResult = value;
    }

}
