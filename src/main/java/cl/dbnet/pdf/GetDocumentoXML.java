
package cl.dbnet.pdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="_ruttEmpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="_folioDTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="_tipoDTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="_serieInte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="_fechaDTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="_monTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "ruttEmpr",
    "folioDTE",
    "tipoDTE",
    "serieInte",
    "fechaDTE",
    "monTotal"
})
@XmlRootElement(name = "getDocumentoXML")
public class GetDocumentoXML {

    @XmlElement(name = "_ruttEmpr")
    protected String ruttEmpr;
    @XmlElement(name = "_folioDTE")
    protected String folioDTE;
    @XmlElement(name = "_tipoDTE")
    protected String tipoDTE;
    @XmlElement(name = "_serieInte")
    protected String serieInte;
    @XmlElement(name = "_fechaDTE")
    protected String fechaDTE;
    @XmlElement(name = "_monTotal")
    protected String monTotal;

    /**
     * Obtiene el valor de la propiedad ruttEmpr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuttEmpr() {
        return ruttEmpr;
    }

    /**
     * Define el valor de la propiedad ruttEmpr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuttEmpr(String value) {
        this.ruttEmpr = value;
    }

    /**
     * Obtiene el valor de la propiedad folioDTE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioDTE() {
        return folioDTE;
    }

    /**
     * Define el valor de la propiedad folioDTE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioDTE(String value) {
        this.folioDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDTE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDTE() {
        return tipoDTE;
    }

    /**
     * Define el valor de la propiedad tipoDTE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDTE(String value) {
        this.tipoDTE = value;
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
     * Obtiene el valor de la propiedad fechaDTE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDTE() {
        return fechaDTE;
    }

    /**
     * Define el valor de la propiedad fechaDTE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDTE(String value) {
        this.fechaDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad monTotal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonTotal() {
        return monTotal;
    }

    /**
     * Define el valor de la propiedad monTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonTotal(String value) {
        this.monTotal = value;
    }

}
