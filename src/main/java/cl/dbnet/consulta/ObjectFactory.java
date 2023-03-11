
package cl.dbnet.consulta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.dbnet package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://www.dbnet.cl", "string");
    private final static QName _Int_QNAME = new QName("http://www.dbnet.cl", "int");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.dbnet
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaEstado }
     * 
     */
    public ConsultaEstado createConsultaEstado() {
        return new ConsultaEstado();
    }

    /**
     * Create an instance of {@link ConsultaEstadoResponse }
     * 
     */
    public ConsultaEstadoResponse createConsultaEstadoResponse() {
        return new ConsultaEstadoResponse();
    }

    /**
     * Create an instance of {@link ActualizaEstadoDoc }
     * 
     */
    public ActualizaEstadoDoc createActualizaEstadoDoc() {
        return new ActualizaEstadoDoc();
    }

    /**
     * Create an instance of {@link ActualizaEstadoDocResponse }
     * 
     */
    public ActualizaEstadoDocResponse createActualizaEstadoDocResponse() {
        return new ActualizaEstadoDocResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.dbnet.cl", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.dbnet.cl", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

}
