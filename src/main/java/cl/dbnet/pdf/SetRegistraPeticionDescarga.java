
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
 *         &lt;element name="lstDocs" type="{http://www.dbnet.cl}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="codiEmpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ruc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rutaWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "lstDocs",
    "codiEmpr",
    "ruc",
    "user",
    "guid",
    "ruta",
    "correo",
    "rutaWeb"
})
@XmlRootElement(name = "setRegistraPeticionDescarga")
public class SetRegistraPeticionDescarga {

    protected ArrayOfString lstDocs;
    protected String codiEmpr;
    protected String ruc;
    protected String user;
    protected String guid;
    protected String ruta;
    protected String correo;
    protected String rutaWeb;

    /**
     * Obtiene el valor de la propiedad lstDocs.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getLstDocs() {
        return lstDocs;
    }

    /**
     * Define el valor de la propiedad lstDocs.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setLstDocs(ArrayOfString value) {
        this.lstDocs = value;
    }

    /**
     * Obtiene el valor de la propiedad codiEmpr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiEmpr() {
        return codiEmpr;
    }

    /**
     * Define el valor de la propiedad codiEmpr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiEmpr(String value) {
        this.codiEmpr = value;
    }

    /**
     * Obtiene el valor de la propiedad ruc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * Define el valor de la propiedad ruc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuc(String value) {
        this.ruc = value;
    }

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad guid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Define el valor de la propiedad guid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuta(String value) {
        this.ruta = value;
    }

    /**
     * Obtiene el valor de la propiedad correo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Define el valor de la propiedad correo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
    }

    /**
     * Obtiene el valor de la propiedad rutaWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaWeb() {
        return rutaWeb;
    }

    /**
     * Define el valor de la propiedad rutaWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaWeb(String value) {
        this.rutaWeb = value;
    }

}
