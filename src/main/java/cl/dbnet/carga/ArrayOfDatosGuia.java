
package cl.dbnet.carga;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfDatosGuia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDatosGuia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DatosGuia" type="{http://www.dbnet.cl}DatosGuia" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDatosGuia", propOrder = {
    "datosGuia"
})
public class ArrayOfDatosGuia {

    @XmlElement(name = "DatosGuia", nillable = true)
    protected List<DatosGuia> datosGuia;

    /**
     * Gets the value of the datosGuia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datosGuia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatosGuia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosGuia }
     * 
     * 
     */
    public List<DatosGuia> getDatosGuia() {
        if (datosGuia == null) {
            datosGuia = new ArrayList<DatosGuia>();
        }
        return this.datosGuia;
    }

}
