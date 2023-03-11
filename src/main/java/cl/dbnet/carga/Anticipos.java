
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Anticipos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Anticipos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinAnti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoAnti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoDocAnti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SerieAnti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FolioAnti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RucAnticipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Anticipos", propOrder = {
    "nroLinAnti",
    "montoAnti",
    "tipoDocAnti",
    "serieAnti",
    "folioAnti",
    "rucAnticipo"
})
public class Anticipos {

    @XmlElement(name = "NroLinAnti")
    protected String nroLinAnti;
    @XmlElement(name = "MontoAnti")
    protected String montoAnti;
    @XmlElement(name = "TipoDocAnti")
    protected String tipoDocAnti;
    @XmlElement(name = "SerieAnti")
    protected String serieAnti;
    @XmlElement(name = "FolioAnti")
    protected String folioAnti;
    @XmlElement(name = "RucAnticipo")
    protected String rucAnticipo;

    /**
     * Obtiene el valor de la propiedad nroLinAnti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinAnti() {
        return nroLinAnti;
    }

    /**
     * Define el valor de la propiedad nroLinAnti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinAnti(String value) {
        this.nroLinAnti = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAnti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoAnti() {
        return montoAnti;
    }

    /**
     * Define el valor de la propiedad montoAnti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoAnti(String value) {
        this.montoAnti = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocAnti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocAnti() {
        return tipoDocAnti;
    }

    /**
     * Define el valor de la propiedad tipoDocAnti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocAnti(String value) {
        this.tipoDocAnti = value;
    }

    /**
     * Obtiene el valor de la propiedad serieAnti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieAnti() {
        return serieAnti;
    }

    /**
     * Define el valor de la propiedad serieAnti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieAnti(String value) {
        this.serieAnti = value;
    }

    /**
     * Obtiene el valor de la propiedad folioAnti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioAnti() {
        return folioAnti;
    }

    /**
     * Define el valor de la propiedad folioAnti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioAnti(String value) {
        this.folioAnti = value;
    }

    /**
     * Obtiene el valor de la propiedad rucAnticipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRucAnticipo() {
        return rucAnticipo;
    }

    /**
     * Define el valor de la propiedad rucAnticipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRucAnticipo(String value) {
        this.rucAnticipo = value;
    }

}
