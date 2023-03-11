
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CamposAdjuntos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CamposAdjuntos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LineaAdjunto" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="lineaReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombreAdjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescripcionAdjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CamposAdjuntos", propOrder = {
    "lineaAdjunto",
    "lineaReferencia",
    "nombreAdjunto",
    "descripcionAdjunto",
    "tipo"
})
public class CamposAdjuntos {

    @XmlElement(name = "LineaAdjunto")
    protected int lineaAdjunto;
    protected String lineaReferencia;
    @XmlElement(name = "NombreAdjunto")
    protected String nombreAdjunto;
    @XmlElement(name = "DescripcionAdjunto")
    protected String descripcionAdjunto;
    @XmlElement(name = "Tipo")
    protected String tipo;

    /**
     * Obtiene el valor de la propiedad lineaAdjunto.
     * 
     */
    public int getLineaAdjunto() {
        return lineaAdjunto;
    }

    /**
     * Define el valor de la propiedad lineaAdjunto.
     * 
     */
    public void setLineaAdjunto(int value) {
        this.lineaAdjunto = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaReferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaReferencia() {
        return lineaReferencia;
    }

    /**
     * Define el valor de la propiedad lineaReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaReferencia(String value) {
        this.lineaReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreAdjunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    /**
     * Define el valor de la propiedad nombreAdjunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAdjunto(String value) {
        this.nombreAdjunto = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionAdjunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionAdjunto() {
        return descripcionAdjunto;
    }

    /**
     * Define el valor de la propiedad descripcionAdjunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionAdjunto(String value) {
        this.descripcionAdjunto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
