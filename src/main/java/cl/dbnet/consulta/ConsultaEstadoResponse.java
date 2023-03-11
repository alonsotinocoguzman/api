
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
 *         &lt;element name="ConsultaEstadoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "consultaEstadoResult"
})
@XmlRootElement(name = "ConsultaEstadoResponse")
public class ConsultaEstadoResponse {

    @XmlElement(name = "ConsultaEstadoResult")
    protected String consultaEstadoResult;

    /**
     * Obtiene el valor de la propiedad consultaEstadoResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsultaEstadoResult() {
        return consultaEstadoResult;
    }

    /**
     * Define el valor de la propiedad consultaEstadoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsultaEstadoResult(String value) {
        this.consultaEstadoResult = value;
    }

}
