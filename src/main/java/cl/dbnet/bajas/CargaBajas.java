
package cl.dbnet.bajas;

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
 *         &lt;element name="RUC" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ArchivoBajas" type="{http://www.dbnet.cl}ArrayOfBajas" minOccurs="0"/&gt;
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
    "ruc",
    "archivoBajas"
})
@XmlRootElement(name = "cargaBajas")
public class CargaBajas {

    @XmlElement(name = "RUC")
    protected String ruc;
    @XmlElement(name = "ArchivoBajas")
    protected ArrayOfBajas archivoBajas;

    /**
     * Obtiene el valor de la propiedad ruc.
     * 
     */
    public String getRUC() {
        return ruc;
    }

    /**
     * Define el valor de la propiedad ruc.
     * 
     */
    public void setRUC(String value) {
        this.ruc = value;
    }

    /**
     * Obtiene el valor de la propiedad archivoBajas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBajas }
     *     
     */
    public ArrayOfBajas getArchivoBajas() {
        return archivoBajas;
    }

    /**
     * Define el valor de la propiedad archivoBajas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBajas }
     *     
     */
    public void setArchivoBajas(ArrayOfBajas value) {
        this.archivoBajas = value;
    }

}
