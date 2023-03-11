
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Descuentos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Descuentos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinDR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TpoMov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValorDR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MBaseCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FactorCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NroLinDet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Descuentos", propOrder = {
    "nroLinDR",
    "tpoMov",
    "valorDR",
    "indCargoDescuento",
    "mBaseCargoDescuento",
    "codigoCargoDescuento",
    "factorCargoDescuento",
    "montoCargoDescuento",
    "nroLinDet"
})
public class Descuentos {

    @XmlElement(name = "NroLinDR")
    protected String nroLinDR;
    @XmlElement(name = "TpoMov")
    protected String tpoMov;
    @XmlElement(name = "ValorDR")
    protected String valorDR;
    @XmlElement(name = "IndCargoDescuento")
    protected String indCargoDescuento;
    @XmlElement(name = "MBaseCargoDescuento")
    protected String mBaseCargoDescuento;
    @XmlElement(name = "CodigoCargoDescuento")
    protected String codigoCargoDescuento;
    @XmlElement(name = "FactorCargoDescuento")
    protected String factorCargoDescuento;
    @XmlElement(name = "MontoCargoDescuento")
    protected String montoCargoDescuento;
    @XmlElement(name = "NroLinDet")
    protected String nroLinDet;

    /**
     * Obtiene el valor de la propiedad nroLinDR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinDR() {
        return nroLinDR;
    }

    /**
     * Define el valor de la propiedad nroLinDR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinDR(String value) {
        this.nroLinDR = value;
    }

    /**
     * Obtiene el valor de la propiedad tpoMov.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpoMov() {
        return tpoMov;
    }

    /**
     * Define el valor de la propiedad tpoMov.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpoMov(String value) {
        this.tpoMov = value;
    }

    /**
     * Obtiene el valor de la propiedad valorDR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorDR() {
        return valorDR;
    }

    /**
     * Define el valor de la propiedad valorDR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorDR(String value) {
        this.valorDR = value;
    }

    /**
     * Obtiene el valor de la propiedad indCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndCargoDescuento() {
        return indCargoDescuento;
    }

    /**
     * Define el valor de la propiedad indCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndCargoDescuento(String value) {
        this.indCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad mBaseCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMBaseCargoDescuento() {
        return mBaseCargoDescuento;
    }

    /**
     * Define el valor de la propiedad mBaseCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMBaseCargoDescuento(String value) {
        this.mBaseCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCargoDescuento() {
        return codigoCargoDescuento;
    }

    /**
     * Define el valor de la propiedad codigoCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCargoDescuento(String value) {
        this.codigoCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad factorCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactorCargoDescuento() {
        return factorCargoDescuento;
    }

    /**
     * Define el valor de la propiedad factorCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactorCargoDescuento(String value) {
        this.factorCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad montoCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoCargoDescuento() {
        return montoCargoDescuento;
    }

    /**
     * Define el valor de la propiedad montoCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoCargoDescuento(String value) {
        this.montoCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad nroLinDet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinDet() {
        return nroLinDet;
    }

    /**
     * Define el valor de la propiedad nroLinDet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinDet(String value) {
        this.nroLinDet = value;
    }

}
