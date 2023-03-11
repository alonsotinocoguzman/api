package cl.dbnet.bajas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-09-25T17:05:54.740-05:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://www.dbnet.cl", name = "WssCargaBajasSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface WssCargaBajasSoap {

    @WebMethod(action = "http://www.dbnet.cl/cargaBajas")
    @RequestWrapper(localName = "cargaBajas", targetNamespace = "http://www.dbnet.cl", className = "cl.dbnet.CargaBajas")
    @ResponseWrapper(localName = "cargaBajasResponse", targetNamespace = "http://www.dbnet.cl", className = "cl.dbnet.CargaBajasResponse")
    @WebResult(name = "cargaBajasResult", targetNamespace = "http://www.dbnet.cl")
    public Mensaje cargaBajas(
        @WebParam(name = "RUC", targetNamespace = "http://www.dbnet.cl")
        String ruc,
        @WebParam(name = "ArchivoBajas", targetNamespace = "http://www.dbnet.cl")
        ArrayOfBajas archivoBajas
    );
}
