
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CamposEnvioPdf complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CamposEnvioPdf"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LineaMail" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="MailEnvi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailCC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailCCO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CamposEnvioPdf", propOrder = {
    "lineaMail",
    "mailEnvi",
    "mailCC",
    "mailCCO"
})
public class CamposEnvioPdf {

    @XmlElement(name = "LineaMail")
    protected int lineaMail;
    @XmlElement(name = "MailEnvi")
    protected String mailEnvi;
    @XmlElement(name = "MailCC")
    protected String mailCC;
    @XmlElement(name = "MailCCO")
    protected String mailCCO;

    /**
     * Obtiene el valor de la propiedad lineaMail.
     * 
     */
    public int getLineaMail() {
        return lineaMail;
    }

    /**
     * Define el valor de la propiedad lineaMail.
     * 
     */
    public void setLineaMail(int value) {
        this.lineaMail = value;
    }

    /**
     * Obtiene el valor de la propiedad mailEnvi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailEnvi() {
        return mailEnvi;
    }

    /**
     * Define el valor de la propiedad mailEnvi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailEnvi(String value) {
        this.mailEnvi = value;
    }

    /**
     * Obtiene el valor de la propiedad mailCC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailCC() {
        return mailCC;
    }

    /**
     * Define el valor de la propiedad mailCC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailCC(String value) {
        this.mailCC = value;
    }

    /**
     * Obtiene el valor de la propiedad mailCCO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailCCO() {
        return mailCCO;
    }

    /**
     * Define el valor de la propiedad mailCCO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailCCO(String value) {
        this.mailCCO = value;
    }

}
