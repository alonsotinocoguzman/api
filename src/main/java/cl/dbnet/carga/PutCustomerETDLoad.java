
package cl.dbnet.carga;

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
 *         &lt;element name="Extras" type="{http://www.dbnet.cl}Extras" minOccurs="0"/&gt;
 *         &lt;element name="Encabezado" type="{http://www.dbnet.cl}Encabezado" minOccurs="0"/&gt;
 *         &lt;element name="Detalles" type="{http://www.dbnet.cl}ArrayOfDetalle" minOccurs="0"/&gt;
 *         &lt;element name="DescuentosRecargosyOtros" type="{http://www.dbnet.cl}DescuentosRecargosyOtros" minOccurs="0"/&gt;
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
    "extras",
    "encabezado",
    "detalles",
    "descuentosRecargosyOtros"
})
@XmlRootElement(name = "putCustomerETDLoad")
public class PutCustomerETDLoad {

    @XmlElement(name = "Extras")
    protected Extras extras;
    @XmlElement(name = "Encabezado")
    protected Encabezado encabezado;
    @XmlElement(name = "Detalles")
    protected ArrayOfDetalle detalles;
    @XmlElement(name = "DescuentosRecargosyOtros")
    protected DescuentosRecargosyOtros descuentosRecargosyOtros;

    /**
     * Obtiene el valor de la propiedad extras.
     * 
     * @return
     *     possible object is
     *     {@link Extras }
     *     
     */
    public Extras getExtras() {
        return extras;
    }

    /**
     * Define el valor de la propiedad extras.
     * 
     * @param value
     *     allowed object is
     *     {@link Extras }
     *     
     */
    public void setExtras(Extras value) {
        this.extras = value;
    }

    /**
     * Obtiene el valor de la propiedad encabezado.
     * 
     * @return
     *     possible object is
     *     {@link Encabezado }
     *     
     */
    public Encabezado getEncabezado() {
        return encabezado;
    }

    /**
     * Define el valor de la propiedad encabezado.
     * 
     * @param value
     *     allowed object is
     *     {@link Encabezado }
     *     
     */
    public void setEncabezado(Encabezado value) {
        this.encabezado = value;
    }

    /**
     * Obtiene el valor de la propiedad detalles.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetalle }
     *     
     */
    public ArrayOfDetalle getDetalles() {
        return detalles;
    }

    /**
     * Define el valor de la propiedad detalles.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetalle }
     *     
     */
    public void setDetalles(ArrayOfDetalle value) {
        this.detalles = value;
    }

    /**
     * Obtiene el valor de la propiedad descuentosRecargosyOtros.
     * 
     * @return
     *     possible object is
     *     {@link DescuentosRecargosyOtros }
     *     
     */
    public DescuentosRecargosyOtros getDescuentosRecargosyOtros() {
        return descuentosRecargosyOtros;
    }

    /**
     * Define el valor de la propiedad descuentosRecargosyOtros.
     * 
     * @param value
     *     allowed object is
     *     {@link DescuentosRecargosyOtros }
     *     
     */
    public void setDescuentosRecargosyOtros(DescuentosRecargosyOtros value) {
        this.descuentosRecargosyOtros = value;
    }

}
