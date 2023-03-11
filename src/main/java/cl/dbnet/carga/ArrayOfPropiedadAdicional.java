
package cl.dbnet.carga;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfPropiedadAdicional complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPropiedadAdicional"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PropiedadAdicional" type="{http://www.dbnet.cl}PropiedadAdicional" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPropiedadAdicional", propOrder = {
    "propiedadAdicional"
})
public class ArrayOfPropiedadAdicional {

    @XmlElement(name = "PropiedadAdicional", nillable = true)
    protected List<PropiedadAdicional> propiedadAdicional;

    /**
     * Gets the value of the propiedadAdicional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propiedadAdicional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropiedadAdicional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropiedadAdicional }
     * 
     * 
     */
    public List<PropiedadAdicional> getPropiedadAdicional() {
        if (propiedadAdicional == null) {
            propiedadAdicional = new ArrayList<PropiedadAdicional>();
        }
        return this.propiedadAdicional;
    }

}
