
package cl.dbnet.consulta;

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
 *         &lt;element name="ruc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo_docu" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="foli_inte" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="serie_inte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "tipoDocu",
    "foliInte",
    "serieInte"
})
@XmlRootElement(name = "ConsultaEstado")
public class ConsultaEstado {

    protected String ruc;
    @XmlElement(name = "tipo_docu")
    protected int tipoDocu;
    @XmlElement(name = "foli_inte")
    protected int foliInte;
    @XmlElement(name = "serie_inte")
    protected String serieInte;

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
     * Obtiene el valor de la propiedad tipoDocu.
     * 
     */
    public int getTipoDocu() {
        return tipoDocu;
    }

    /**
     * Define el valor de la propiedad tipoDocu.
     * 
     */
    public void setTipoDocu(int value) {
        this.tipoDocu = value;
    }

    /**
     * Obtiene el valor de la propiedad foliInte.
     * 
     */
    public int getFoliInte() {
        return foliInte;
    }

    /**
     * Define el valor de la propiedad foliInte.
     * 
     */
    public void setFoliInte(int value) {
        this.foliInte = value;
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

}
