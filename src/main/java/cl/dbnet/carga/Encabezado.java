
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Encabezado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Encabezado"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="camposEncabezado" type="{http://www.dbnet.cl}CamposHead" minOccurs="0"/&gt;
 *         &lt;element name="ImptoReten" type="{http://www.dbnet.cl}ArrayOfImptoReten" minOccurs="0"/&gt;
 *         &lt;element name="DetaDetraccion" type="{http://www.dbnet.cl}ArrayOfDetaDetraccion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Encabezado", propOrder = {
    "camposEncabezado",
    "imptoReten",
    "detaDetraccion"
})
public class Encabezado {

    protected CamposHead camposEncabezado;
    @XmlElement(name = "ImptoReten")
    protected ArrayOfImptoReten imptoReten;
    @XmlElement(name = "DetaDetraccion")
    protected ArrayOfDetaDetraccion detaDetraccion;

    /**
     * Obtiene el valor de la propiedad camposEncabezado.
     * 
     * @return
     *     possible object is
     *     {@link CamposHead }
     *     
     */
    public CamposHead getCamposEncabezado() {
        return camposEncabezado;
    }

    /**
     * Define el valor de la propiedad camposEncabezado.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposHead }
     *     
     */
    public void setCamposEncabezado(CamposHead value) {
        this.camposEncabezado = value;
    }

    /**
     * Obtiene el valor de la propiedad imptoReten.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfImptoReten }
     *     
     */
    public ArrayOfImptoReten getImptoReten() {
        return imptoReten;
    }

    /**
     * Define el valor de la propiedad imptoReten.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfImptoReten }
     *     
     */
    public void setImptoReten(ArrayOfImptoReten value) {
        this.imptoReten = value;
    }

    /**
     * Obtiene el valor de la propiedad detaDetraccion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetaDetraccion }
     *     
     */
    public ArrayOfDetaDetraccion getDetaDetraccion() {
        return detaDetraccion;
    }

    /**
     * Define el valor de la propiedad detaDetraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetaDetraccion }
     *     
     */
    public void setDetaDetraccion(ArrayOfDetaDetraccion value) {
        this.detaDetraccion = value;
    }

}
