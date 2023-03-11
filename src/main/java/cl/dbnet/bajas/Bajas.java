
package cl.dbnet.bajas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para bajas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="bajas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo_Docu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Serie_Inte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Foli_Inte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Fech_Emis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Motiv_Anul" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tipo_REEM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Serie_REEM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Foli_REEM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bajas", propOrder = {
    "tipoDocu",
    "serieInte",
    "foliInte",
    "fechEmis",
    "motivAnul",
    "tipoREEM",
    "serieREEM",
    "foliREEM"
})
public class Bajas {

    @XmlElement(name = "Tipo_Docu")
    protected String tipoDocu;
    @XmlElement(name = "Serie_Inte")
    protected String serieInte;
    @XmlElement(name = "Foli_Inte")
    protected String foliInte;
    @XmlElement(name = "Fech_Emis")
    protected String fechEmis;
    @XmlElement(name = "Motiv_Anul")
    protected String motivAnul;
    @XmlElement(name = "Tipo_REEM")
    protected String tipoREEM;
    @XmlElement(name = "Serie_REEM")
    protected String serieREEM;
    @XmlElement(name = "Foli_REEM")
    protected String foliREEM;

    /**
     * Obtiene el valor de la propiedad tipoDocu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocu() {
        return tipoDocu;
    }

    /**
     * Define el valor de la propiedad tipoDocu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocu(String value) {
        this.tipoDocu = value;
    }

    /**
     * Obtiene el valor de la propiedad serieInte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieInte() {
        return serieInte;
    }

    /**
     * Define el valor de la propiedad serieInte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieInte(String value) {
        this.serieInte = value;
    }

    /**
     * Obtiene el valor de la propiedad foliInte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoliInte() {
        return foliInte;
    }

    /**
     * Define el valor de la propiedad foliInte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoliInte(String value) {
        this.foliInte = value;
    }

    /**
     * Obtiene el valor de la propiedad fechEmis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechEmis() {
        return fechEmis;
    }

    /**
     * Define el valor de la propiedad fechEmis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechEmis(String value) {
        this.fechEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad motivAnul.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivAnul() {
        return motivAnul;
    }

    /**
     * Define el valor de la propiedad motivAnul.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivAnul(String value) {
        this.motivAnul = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoREEM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoREEM() {
        return tipoREEM;
    }

    /**
     * Define el valor de la propiedad tipoREEM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoREEM(String value) {
        this.tipoREEM = value;
    }

    /**
     * Obtiene el valor de la propiedad serieREEM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieREEM() {
        return serieREEM;
    }

    /**
     * Define el valor de la propiedad serieREEM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieREEM(String value) {
        this.serieREEM = value;
    }

    /**
     * Obtiene el valor de la propiedad foliREEM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoliREEM() {
        return foliREEM;
    }

    /**
     * Define el valor de la propiedad foliREEM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoliREEM(String value) {
        this.foliREEM = value;
    }

}
