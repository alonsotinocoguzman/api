
package cl.dbnet.carga;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfAnticipos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAnticipos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Anticipos" type="{http://www.dbnet.cl}Anticipos" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAnticipos", propOrder = {
    "anticipos"
})
public class ArrayOfAnticipos {

    @XmlElement(name = "Anticipos", nillable = true)
    protected List<Anticipos> anticipos;

    /**
     * Gets the value of the anticipos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anticipos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnticipos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Anticipos }
     * 
     * 
     */
    public List<Anticipos> getAnticipos() {
        if (anticipos == null) {
            anticipos = new ArrayList<Anticipos>();
        }
        return this.anticipos;
    }

}
