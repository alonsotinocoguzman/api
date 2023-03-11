
package cl.dbnet.bajas;

import javax.xml.bind.annotation.XmlRegistry;


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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.dbnet
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CargaBajas }
     * 
     */
    public CargaBajas createCargaBajas() {
        return new CargaBajas();
    }

    /**
     * Create an instance of {@link ArrayOfBajas }
     * 
     */
    public ArrayOfBajas createArrayOfBajas() {
        return new ArrayOfBajas();
    }

    /**
     * Create an instance of {@link CargaBajasResponse }
     * 
     */
    public CargaBajasResponse createCargaBajasResponse() {
        return new CargaBajasResponse();
    }

    /**
     * Create an instance of {@link Mensaje }
     * 
     */
    public Mensaje createMensaje() {
        return new Mensaje();
    }

    /**
     * Create an instance of {@link Bajas }
     * 
     */
    public Bajas createBajas() {
        return new Bajas();
    }

}
