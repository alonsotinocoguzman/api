
package cl.dbnet.bajas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Mensaje complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Mensaje"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Mensajes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrackId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mensaje", propOrder = {
    "codigo",
    "mensajes",
    "trackId"
})
public class Mensaje {

    @XmlElement(name = "Codigo")
    protected String codigo;
    @XmlElement(name = "Mensajes")
    protected String mensajes;
    @XmlElement(name = "TrackId")
    protected String trackId;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajes(String value) {
        this.mensajes = value;
    }

    /**
     * Obtiene el valor de la propiedad trackId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackId() {
        return trackId;
    }

    /**
     * Define el valor de la propiedad trackId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackId(String value) {
        this.trackId = value;
    }

}
