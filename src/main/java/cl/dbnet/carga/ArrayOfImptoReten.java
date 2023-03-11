
package cl.dbnet.carga;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfImptoReten complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfImptoReten"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ImptoReten" type="{http://www.dbnet.cl}ImptoReten" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfImptoReten", propOrder = {
    "imptoReten"
})
public class ArrayOfImptoReten {

    @XmlElement(name = "ImptoReten", nillable = true)
    protected List<ImptoReten> imptoReten;

    /**
     * Gets the value of the imptoReten property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imptoReten property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImptoReten().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImptoReten }
     * 
     * 
     */
    public List<ImptoReten> getImptoReten() {
        if (imptoReten == null) {
            imptoReten = new ArrayList<ImptoReten>();
        }
        return this.imptoReten;
    }

}
