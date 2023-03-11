
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CamposHead complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="CamposHead"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoDTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Correlativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUTEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRucEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RznSocEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NomComer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ComuEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UrbanizaEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProviEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DeparEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DistriEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisEmis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUTRecep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRutReceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RznSocRecep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepDepartamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sustento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoNotaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntNeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntExe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntExo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotGrat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotBoni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotAnticipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotDscNA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotCrgNA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntRedondeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndAgenciaViaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndicadorTransfSelva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndicadorServiciosSelva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndicadorContratosSelva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndVentaEmisItinerante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntImpRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ObsRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotalMenosRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntImpPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ObsPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotalMasPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechVencFact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaDepartamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirParCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirLlegDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PlacaVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CertVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Marcavehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Licencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUCTranspor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRucTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RazoTrans" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ModalidadTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalPesoBruto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLocalAnexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumPlacaVehi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ImprDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NombSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="COLA_IMPRESION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CantidadItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HoraEmision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoLeyenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CODI_EMPR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TpoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RUTEmisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodiComu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRUTRecep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepDpto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotalAnticipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirlleUbiGeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoOper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodiSucu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ColaImpresion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirRecepUbigeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirEntregaUbigeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegUrbaniza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirllegDistrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CicloFactIni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CicloFactFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoCanalFac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FormaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoNetoPendPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CamposHead", propOrder = {
        "tipoDTE",
        "serie",
        "correlativo",
        "fchEmis",
        "tipoMoneda",
        "rutEmis",
        "tipoRucEmis",
        "rznSocEmis",
        "nomComer",
        "dirEmis",
        "comuEmis",
        "urbanizaEmis",
        "proviEmis",
        "deparEmis",
        "distriEmis",
        "paisEmis",
        "rutRecep",
        "tipoRutReceptor",
        "rznSocRecep",
        "dirRecepUbiGeo",
        "dirRecep",
        "dirRecepUrbaniza",
        "dirRecepProvincia",
        "dirRecepDepartamento",
        "dirRecepDistrito",
        "dirRecepCodPais",
        "codigoAutorizacion",
        "sustento",
        "tipoNotaCredito",
        "mntNeto",
        "mntExe",
        "mntExo",
        "mntTotGrat",
        "mntTotBoni",
        "mntTotAnticipo",
        "mntTotal",
        "totDscNA",
        "totCrgNA",
        "mntRedondeo",
        "indAgenciaViaje",
        "indicadorTransfSelva",
        "indicadorServiciosSelva",
        "indicadorContratosSelva",
        "indVentaEmisItinerante",
        "codRetencion",
        "mntImpRetencion",
        "obsRetencion",
        "mntRetencion",
        "mntTotalMenosRetencion",
        "codPercepcion",
        "mntImpPercepcion",
        "obsPercepcion",
        "mntPercepcion",
        "mntTotalMasPercepcion",
        "tipoOperacion",
        "fechVencFact",
        "dirEntrega",
        "dirEntregaUbiGeo",
        "dirEntregaUrbaniza",
        "dirEntregaProvincia",
        "dirEntregaDepartamento",
        "dirEntregaDistrito",
        "dirEntregaCodPais",
        "dirParUbiGeo",
        "dirParDireccion",
        "dirParUrbaniza",
        "dirParProvincia",
        "dirParDepart",
        "dirParDistrito",
        "dirParCodPais",
        "dirLlegUbiGeo",
        "dirLlegDireccion",
        "dirLlegUrbaniza",
        "dirLlegProvincia",
        "dirLlegDepart",
        "dirLlegDistrito",
        "dirllegCodPais",
        "placaVehiculo",
        "certVehiculo",
        "marcavehiculo",
        "licencia",
        "rucTranspor",
        "tipoRucTrans",
        "razoTrans",
        "modalidadTransporte",
        "totalPesoBruto",
        "codigoLocalAnexo",
        "numPlacaVehi",
        "imprDest",
        "nombSucursal",
        "colaimpresion",
        "cantidadItem",
        "horaEmision",
        "codigoLeyenda",
        "codiempr",
        "estado",
        "mensaje",
        "tpoMoneda",
        "rutEmisor",
        "codiComu",
        "tipoRUTRecep",
        "dirRecepDpto",
        "mntTotalAnticipo",
        "dirlleUbiGeo",
        "tipoOper",
        "codiSucu",
        "colaImpresion",
        "dirRecepUbigeo",
        "dirEntregaUbigeo",
        "dirllegUrbaniza",
        "dirllegDireccion",
        "dirllegProvincia",
        "dirllegDepart",
        "dirllegDistrito",
        "cicloFactIni",
        "cicloFactFin",
        "tipoCanalFac",
        "formaPago",
        "montoNetoPendPago"
})
public class CamposHead {

    @XmlElement(name = "TipoDTE")
    protected String tipoDTE;
    @XmlElement(name = "Serie")
    protected String serie;
    @XmlElement(name = "Correlativo")
    protected String correlativo;
    @XmlElement(name = "FchEmis")
    protected String fchEmis;
    @XmlElement(name = "TipoMoneda")
    protected String tipoMoneda;
    @XmlElement(name = "RUTEmis")
    protected String rutEmis;
    @XmlElement(name = "TipoRucEmis")
    protected String tipoRucEmis;
    @XmlElement(name = "RznSocEmis")
    protected String rznSocEmis;
    @XmlElement(name = "NomComer")
    protected String nomComer;
    @XmlElement(name = "DirEmis")
    protected String dirEmis;
    @XmlElement(name = "ComuEmis")
    protected String comuEmis;
    @XmlElement(name = "UrbanizaEmis")
    protected String urbanizaEmis;
    @XmlElement(name = "ProviEmis")
    protected String proviEmis;
    @XmlElement(name = "DeparEmis")
    protected String deparEmis;
    @XmlElement(name = "DistriEmis")
    protected String distriEmis;
    @XmlElement(name = "PaisEmis")
    protected String paisEmis;
    @XmlElement(name = "RUTRecep")
    protected String rutRecep;
    @XmlElement(name = "TipoRutReceptor")
    protected String tipoRutReceptor;
    @XmlElement(name = "RznSocRecep")
    protected String rznSocRecep;
    @XmlElement(name = "DirRecepUbiGeo")
    protected String dirRecepUbiGeo;
    @XmlElement(name = "DirRecep")
    protected String dirRecep;
    @XmlElement(name = "DirRecepUrbaniza")
    protected String dirRecepUrbaniza;
    @XmlElement(name = "DirRecepProvincia")
    protected String dirRecepProvincia;
    @XmlElement(name = "DirRecepDepartamento")
    protected String dirRecepDepartamento;
    @XmlElement(name = "DirRecepDistrito")
    protected String dirRecepDistrito;
    @XmlElement(name = "DirRecepCodPais")
    protected String dirRecepCodPais;
    @XmlElement(name = "CodigoAutorizacion")
    protected String codigoAutorizacion;
    @XmlElement(name = "Sustento")
    protected String sustento;
    @XmlElement(name = "TipoNotaCredito")
    protected String tipoNotaCredito;
    @XmlElement(name = "MntNeto")
    protected String mntNeto;
    @XmlElement(name = "MntExe")
    protected String mntExe;
    @XmlElement(name = "MntExo")
    protected String mntExo;
    @XmlElement(name = "MntTotGrat")
    protected String mntTotGrat;
    @XmlElement(name = "MntTotBoni")
    protected String mntTotBoni;
    @XmlElement(name = "MntTotAnticipo")
    protected String mntTotAnticipo;
    @XmlElement(name = "MntTotal")
    protected String mntTotal;
    @XmlElement(name = "TotDscNA")
    protected String totDscNA;
    @XmlElement(name = "TotCrgNA")
    protected String totCrgNA;
    @XmlElement(name = "MntRedondeo")
    protected String mntRedondeo;
    @XmlElement(name = "IndAgenciaViaje")
    protected String indAgenciaViaje;
    @XmlElement(name = "IndicadorTransfSelva")
    protected String indicadorTransfSelva;
    @XmlElement(name = "IndicadorServiciosSelva")
    protected String indicadorServiciosSelva;
    @XmlElement(name = "IndicadorContratosSelva")
    protected String indicadorContratosSelva;
    @XmlElement(name = "IndVentaEmisItinerante")
    protected String indVentaEmisItinerante;
    @XmlElement(name = "CodRetencion")
    protected String codRetencion;
    @XmlElement(name = "MntImpRetencion")
    protected String mntImpRetencion;
    @XmlElement(name = "ObsRetencion")
    protected String obsRetencion;
    @XmlElement(name = "MntRetencion")
    protected String mntRetencion;
    @XmlElement(name = "MntTotalMenosRetencion")
    protected String mntTotalMenosRetencion;
    @XmlElement(name = "CodPercepcion")
    protected String codPercepcion;
    @XmlElement(name = "MntImpPercepcion")
    protected String mntImpPercepcion;
    @XmlElement(name = "ObsPercepcion")
    protected String obsPercepcion;
    @XmlElement(name = "MntPercepcion")
    protected String mntPercepcion;
    @XmlElement(name = "MntTotalMasPercepcion")
    protected String mntTotalMasPercepcion;
    @XmlElement(name = "TipoOperacion")
    protected String tipoOperacion;
    @XmlElement(name = "FechVencFact")
    protected String fechVencFact;
    @XmlElement(name = "DirEntrega")
    protected String dirEntrega;
    @XmlElement(name = "DirEntregaUbiGeo")
    protected String dirEntregaUbiGeo;
    @XmlElement(name = "DirEntregaUrbaniza")
    protected String dirEntregaUrbaniza;
    @XmlElement(name = "DirEntregaProvincia")
    protected String dirEntregaProvincia;
    @XmlElement(name = "DirEntregaDepartamento")
    protected String dirEntregaDepartamento;
    @XmlElement(name = "DirEntregaDistrito")
    protected String dirEntregaDistrito;
    @XmlElement(name = "DirEntregaCodPais")
    protected String dirEntregaCodPais;
    @XmlElement(name = "DirParUbiGeo")
    protected String dirParUbiGeo;
    @XmlElement(name = "DirParDireccion")
    protected String dirParDireccion;
    @XmlElement(name = "DirParUrbaniza")
    protected String dirParUrbaniza;
    @XmlElement(name = "DirParProvincia")
    protected String dirParProvincia;
    @XmlElement(name = "DirParDepart")
    protected String dirParDepart;
    @XmlElement(name = "DirParDistrito")
    protected String dirParDistrito;
    @XmlElement(name = "DirParCodPais")
    protected String dirParCodPais;
    @XmlElement(name = "DirLlegUbiGeo")
    protected String dirLlegUbiGeo;
    @XmlElement(name = "DirLlegDireccion")
    protected String dirLlegDireccion;
    @XmlElement(name = "DirLlegUrbaniza")
    protected String dirLlegUrbaniza;
    @XmlElement(name = "DirLlegProvincia")
    protected String dirLlegProvincia;
    @XmlElement(name = "DirLlegDepart")
    protected String dirLlegDepart;
    @XmlElement(name = "DirLlegDistrito")
    protected String dirLlegDistrito;
    @XmlElement(name = "DirllegCodPais")
    protected String dirllegCodPais;
    @XmlElement(name = "PlacaVehiculo")
    protected String placaVehiculo;
    @XmlElement(name = "CertVehiculo")
    protected String certVehiculo;
    @XmlElement(name = "Marcavehiculo")
    protected String marcavehiculo;
    @XmlElement(name = "Licencia")
    protected String licencia;
    @XmlElement(name = "RUCTranspor")
    protected String rucTranspor;
    @XmlElement(name = "TipoRucTrans")
    protected String tipoRucTrans;
    @XmlElement(name = "RazoTrans")
    protected String razoTrans;
    @XmlElement(name = "ModalidadTransporte")
    protected String modalidadTransporte;
    @XmlElement(name = "TotalPesoBruto")
    protected String totalPesoBruto;
    @XmlElement(name = "CodigoLocalAnexo")
    protected String codigoLocalAnexo;
    @XmlElement(name = "NumPlacaVehi")
    protected String numPlacaVehi;
    @XmlElement(name = "ImprDest")
    protected String imprDest;
    @XmlElement(name = "NombSucursal")
    protected String nombSucursal;
    @XmlElement(name = "COLA_IMPRESION")
    protected String colaimpresion;
    @XmlElement(name = "CantidadItem")
    protected String cantidadItem;
    @XmlElement(name = "HoraEmision")
    protected String horaEmision;
    @XmlElement(name = "CodigoLeyenda")
    protected String codigoLeyenda;
    @XmlElement(name = "CODI_EMPR")
    protected String codiempr;
    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "Mensaje")
    protected String mensaje;
    @XmlElement(name = "TpoMoneda")
    protected String tpoMoneda;
    @XmlElement(name = "RUTEmisor")
    protected String rutEmisor;
    @XmlElement(name = "CodiComu")
    protected String codiComu;
    @XmlElement(name = "TipoRUTRecep")
    protected String tipoRUTRecep;
    @XmlElement(name = "DirRecepDpto")
    protected String dirRecepDpto;
    @XmlElement(name = "MntTotalAnticipo")
    protected String mntTotalAnticipo;
    @XmlElement(name = "DirlleUbiGeo")
    protected String dirlleUbiGeo;
    @XmlElement(name = "TipoOper")
    protected String tipoOper;
    @XmlElement(name = "CodiSucu")
    protected String codiSucu;
    @XmlElement(name = "ColaImpresion")
    protected String colaImpresion;
    @XmlElement(name = "DirRecepUbigeo")
    protected String dirRecepUbigeo;
    @XmlElement(name = "DirEntregaUbigeo")
    protected String dirEntregaUbigeo;
    @XmlElement(name = "DirllegUrbaniza")
    protected String dirllegUrbaniza;
    @XmlElement(name = "DirllegDireccion")
    protected String dirllegDireccion;
    @XmlElement(name = "DirllegProvincia")
    protected String dirllegProvincia;
    @XmlElement(name = "DirllegDepart")
    protected String dirllegDepart;
    @XmlElement(name = "DirllegDistrito")
    protected String dirllegDistrito;
    @XmlElement(name = "CicloFactIni")
    protected String cicloFactIni;
    @XmlElement(name = "CicloFactFin")
    protected String cicloFactFin;
    @XmlElement(name = "TipoCanalFac")
    protected String tipoCanalFac;
    @XmlElement(name = "FormaPago")
    protected String formaPago;
    @XmlElement(name = "MontoNetoPendPago")
    protected String montoNetoPendPago;

    /**
     * Obtiene el valor de la propiedad tipoDTE.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoDTE() {
        return tipoDTE;
    }

    /**
     * Define el valor de la propiedad tipoDTE.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoDTE(String value) {
        this.tipoDTE = value;
    }

    /**
     * Obtiene el valor de la propiedad serie.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Define el valor de la propiedad serie.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSerie(String value) {
        this.serie = value;
    }

    /**
     * Obtiene el valor de la propiedad correlativo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * Define el valor de la propiedad correlativo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCorrelativo(String value) {
        this.correlativo = value;
    }

    /**
     * Obtiene el valor de la propiedad fchEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFchEmis() {
        return fchEmis;
    }

    /**
     * Define el valor de la propiedad fchEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFchEmis(String value) {
        this.fchEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoMoneda.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * Define el valor de la propiedad tipoMoneda.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoMoneda(String value) {
        this.tipoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad rutEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRUTEmis() {
        return rutEmis;
    }

    /**
     * Define el valor de la propiedad rutEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRUTEmis(String value) {
        this.rutEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRucEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoRucEmis() {
        return tipoRucEmis;
    }

    /**
     * Define el valor de la propiedad tipoRucEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoRucEmis(String value) {
        this.tipoRucEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad rznSocEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRznSocEmis() {
        return rznSocEmis;
    }

    /**
     * Define el valor de la propiedad rznSocEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRznSocEmis(String value) {
        this.rznSocEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad nomComer.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNomComer() {
        return nomComer;
    }

    /**
     * Define el valor de la propiedad nomComer.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNomComer(String value) {
        this.nomComer = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEmis() {
        return dirEmis;
    }

    /**
     * Define el valor de la propiedad dirEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEmis(String value) {
        this.dirEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad comuEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getComuEmis() {
        return comuEmis;
    }

    /**
     * Define el valor de la propiedad comuEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setComuEmis(String value) {
        this.comuEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad urbanizaEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUrbanizaEmis() {
        return urbanizaEmis;
    }

    /**
     * Define el valor de la propiedad urbanizaEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUrbanizaEmis(String value) {
        this.urbanizaEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad proviEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProviEmis() {
        return proviEmis;
    }

    /**
     * Define el valor de la propiedad proviEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProviEmis(String value) {
        this.proviEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad deparEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDeparEmis() {
        return deparEmis;
    }

    /**
     * Define el valor de la propiedad deparEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDeparEmis(String value) {
        this.deparEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad distriEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDistriEmis() {
        return distriEmis;
    }

    /**
     * Define el valor de la propiedad distriEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDistriEmis(String value) {
        this.distriEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad paisEmis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPaisEmis() {
        return paisEmis;
    }

    /**
     * Define el valor de la propiedad paisEmis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPaisEmis(String value) {
        this.paisEmis = value;
    }

    /**
     * Obtiene el valor de la propiedad rutRecep.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRUTRecep() {
        return rutRecep;
    }

    /**
     * Define el valor de la propiedad rutRecep.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRUTRecep(String value) {
        this.rutRecep = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRutReceptor.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoRutReceptor() {
        return tipoRutReceptor;
    }

    /**
     * Define el valor de la propiedad tipoRutReceptor.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoRutReceptor(String value) {
        this.tipoRutReceptor = value;
    }

    /**
     * Obtiene el valor de la propiedad rznSocRecep.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRznSocRecep() {
        return rznSocRecep;
    }

    /**
     * Define el valor de la propiedad rznSocRecep.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRznSocRecep(String value) {
        this.rznSocRecep = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepUbiGeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepUbiGeo() {
        return dirRecepUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirRecepUbiGeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepUbiGeo(String value) {
        this.dirRecepUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecep.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecep() {
        return dirRecep;
    }

    /**
     * Define el valor de la propiedad dirRecep.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecep(String value) {
        this.dirRecep = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepUrbaniza.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepUrbaniza() {
        return dirRecepUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirRecepUrbaniza.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepUrbaniza(String value) {
        this.dirRecepUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepProvincia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepProvincia() {
        return dirRecepProvincia;
    }

    /**
     * Define el valor de la propiedad dirRecepProvincia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepProvincia(String value) {
        this.dirRecepProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepDepartamento.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepDepartamento() {
        return dirRecepDepartamento;
    }

    /**
     * Define el valor de la propiedad dirRecepDepartamento.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepDepartamento(String value) {
        this.dirRecepDepartamento = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepDistrito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepDistrito() {
        return dirRecepDistrito;
    }

    /**
     * Define el valor de la propiedad dirRecepDistrito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepDistrito(String value) {
        this.dirRecepDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepCodPais.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepCodPais() {
        return dirRecepCodPais;
    }

    /**
     * Define el valor de la propiedad dirRecepCodPais.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepCodPais(String value) {
        this.dirRecepCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoAutorizacion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    /**
     * Define el valor de la propiedad codigoAutorizacion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodigoAutorizacion(String value) {
        this.codigoAutorizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad sustento.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSustento() {
        return sustento;
    }

    /**
     * Define el valor de la propiedad sustento.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSustento(String value) {
        this.sustento = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoNotaCredito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoNotaCredito() {
        return tipoNotaCredito;
    }

    /**
     * Define el valor de la propiedad tipoNotaCredito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoNotaCredito(String value) {
        this.tipoNotaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad mntNeto.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntNeto() {
        return mntNeto;
    }

    /**
     * Define el valor de la propiedad mntNeto.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntNeto(String value) {
        this.mntNeto = value;
    }

    /**
     * Obtiene el valor de la propiedad mntExe.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntExe() {
        return mntExe;
    }

    /**
     * Define el valor de la propiedad mntExe.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntExe(String value) {
        this.mntExe = value;
    }

    /**
     * Obtiene el valor de la propiedad mntExo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntExo() {
        return mntExo;
    }

    /**
     * Define el valor de la propiedad mntExo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntExo(String value) {
        this.mntExo = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotGrat.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotGrat() {
        return mntTotGrat;
    }

    /**
     * Define el valor de la propiedad mntTotGrat.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotGrat(String value) {
        this.mntTotGrat = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotBoni.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotBoni() {
        return mntTotBoni;
    }

    /**
     * Define el valor de la propiedad mntTotBoni.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotBoni(String value) {
        this.mntTotBoni = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotAnticipo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotAnticipo() {
        return mntTotAnticipo;
    }

    /**
     * Define el valor de la propiedad mntTotAnticipo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotAnticipo(String value) {
        this.mntTotAnticipo = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotal.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotal() {
        return mntTotal;
    }

    /**
     * Define el valor de la propiedad mntTotal.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotal(String value) {
        this.mntTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad totDscNA.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTotDscNA() {
        return totDscNA;
    }

    /**
     * Define el valor de la propiedad totDscNA.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotDscNA(String value) {
        this.totDscNA = value;
    }

    /**
     * Obtiene el valor de la propiedad totCrgNA.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTotCrgNA() {
        return totCrgNA;
    }

    /**
     * Define el valor de la propiedad totCrgNA.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotCrgNA(String value) {
        this.totCrgNA = value;
    }

    /**
     * Obtiene el valor de la propiedad mntRedondeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntRedondeo() {
        return mntRedondeo;
    }

    /**
     * Define el valor de la propiedad mntRedondeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntRedondeo(String value) {
        this.mntRedondeo = value;
    }

    /**
     * Obtiene el valor de la propiedad indAgenciaViaje.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIndAgenciaViaje() {
        return indAgenciaViaje;
    }

    /**
     * Define el valor de la propiedad indAgenciaViaje.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIndAgenciaViaje(String value) {
        this.indAgenciaViaje = value;
    }

    /**
     * Obtiene el valor de la propiedad indicadorTransfSelva.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIndicadorTransfSelva() {
        return indicadorTransfSelva;
    }

    /**
     * Define el valor de la propiedad indicadorTransfSelva.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIndicadorTransfSelva(String value) {
        this.indicadorTransfSelva = value;
    }

    /**
     * Obtiene el valor de la propiedad indicadorServiciosSelva.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIndicadorServiciosSelva() {
        return indicadorServiciosSelva;
    }

    /**
     * Define el valor de la propiedad indicadorServiciosSelva.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIndicadorServiciosSelva(String value) {
        this.indicadorServiciosSelva = value;
    }

    /**
     * Obtiene el valor de la propiedad indicadorContratosSelva.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIndicadorContratosSelva() {
        return indicadorContratosSelva;
    }

    /**
     * Define el valor de la propiedad indicadorContratosSelva.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIndicadorContratosSelva(String value) {
        this.indicadorContratosSelva = value;
    }

    /**
     * Obtiene el valor de la propiedad indVentaEmisItinerante.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIndVentaEmisItinerante() {
        return indVentaEmisItinerante;
    }

    /**
     * Define el valor de la propiedad indVentaEmisItinerante.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIndVentaEmisItinerante(String value) {
        this.indVentaEmisItinerante = value;
    }

    /**
     * Obtiene el valor de la propiedad codRetencion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodRetencion() {
        return codRetencion;
    }

    /**
     * Define el valor de la propiedad codRetencion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodRetencion(String value) {
        this.codRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntImpRetencion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntImpRetencion() {
        return mntImpRetencion;
    }

    /**
     * Define el valor de la propiedad mntImpRetencion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntImpRetencion(String value) {
        this.mntImpRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad obsRetencion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getObsRetencion() {
        return obsRetencion;
    }

    /**
     * Define el valor de la propiedad obsRetencion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setObsRetencion(String value) {
        this.obsRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntRetencion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntRetencion() {
        return mntRetencion;
    }

    /**
     * Define el valor de la propiedad mntRetencion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntRetencion(String value) {
        this.mntRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotalMenosRetencion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotalMenosRetencion() {
        return mntTotalMenosRetencion;
    }

    /**
     * Define el valor de la propiedad mntTotalMenosRetencion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotalMenosRetencion(String value) {
        this.mntTotalMenosRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad codPercepcion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodPercepcion() {
        return codPercepcion;
    }

    /**
     * Define el valor de la propiedad codPercepcion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodPercepcion(String value) {
        this.codPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntImpPercepcion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntImpPercepcion() {
        return mntImpPercepcion;
    }

    /**
     * Define el valor de la propiedad mntImpPercepcion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntImpPercepcion(String value) {
        this.mntImpPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad obsPercepcion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getObsPercepcion() {
        return obsPercepcion;
    }

    /**
     * Define el valor de la propiedad obsPercepcion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setObsPercepcion(String value) {
        this.obsPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntPercepcion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntPercepcion() {
        return mntPercepcion;
    }

    /**
     * Define el valor de la propiedad mntPercepcion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntPercepcion(String value) {
        this.mntPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotalMasPercepcion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotalMasPercepcion() {
        return mntTotalMasPercepcion;
    }

    /**
     * Define el valor de la propiedad mntTotalMasPercepcion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotalMasPercepcion(String value) {
        this.mntTotalMasPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOperacion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Define el valor de la propiedad tipoOperacion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechVencFact.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechVencFact() {
        return fechVencFact;
    }

    /**
     * Define el valor de la propiedad fechVencFact.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechVencFact(String value) {
        this.fechVencFact = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntrega.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntrega() {
        return dirEntrega;
    }

    /**
     * Define el valor de la propiedad dirEntrega.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntrega(String value) {
        this.dirEntrega = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaUbiGeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaUbiGeo() {
        return dirEntregaUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirEntregaUbiGeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaUbiGeo(String value) {
        this.dirEntregaUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaUrbaniza.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaUrbaniza() {
        return dirEntregaUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirEntregaUrbaniza.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaUrbaniza(String value) {
        this.dirEntregaUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaProvincia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaProvincia() {
        return dirEntregaProvincia;
    }

    /**
     * Define el valor de la propiedad dirEntregaProvincia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaProvincia(String value) {
        this.dirEntregaProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaDepartamento.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaDepartamento() {
        return dirEntregaDepartamento;
    }

    /**
     * Define el valor de la propiedad dirEntregaDepartamento.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaDepartamento(String value) {
        this.dirEntregaDepartamento = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaDistrito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaDistrito() {
        return dirEntregaDistrito;
    }

    /**
     * Define el valor de la propiedad dirEntregaDistrito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaDistrito(String value) {
        this.dirEntregaDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaCodPais.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaCodPais() {
        return dirEntregaCodPais;
    }

    /**
     * Define el valor de la propiedad dirEntregaCodPais.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaCodPais(String value) {
        this.dirEntregaCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParUbiGeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParUbiGeo() {
        return dirParUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirParUbiGeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParUbiGeo(String value) {
        this.dirParUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDireccion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParDireccion() {
        return dirParDireccion;
    }

    /**
     * Define el valor de la propiedad dirParDireccion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParDireccion(String value) {
        this.dirParDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParUrbaniza.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParUrbaniza() {
        return dirParUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirParUrbaniza.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParUrbaniza(String value) {
        this.dirParUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParProvincia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParProvincia() {
        return dirParProvincia;
    }

    /**
     * Define el valor de la propiedad dirParProvincia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParProvincia(String value) {
        this.dirParProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDepart.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParDepart() {
        return dirParDepart;
    }

    /**
     * Define el valor de la propiedad dirParDepart.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParDepart(String value) {
        this.dirParDepart = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParDistrito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParDistrito() {
        return dirParDistrito;
    }

    /**
     * Define el valor de la propiedad dirParDistrito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParDistrito(String value) {
        this.dirParDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirParCodPais.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirParCodPais() {
        return dirParCodPais;
    }

    /**
     * Define el valor de la propiedad dirParCodPais.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirParCodPais(String value) {
        this.dirParCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegUbiGeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegUbiGeo() {
        return dirLlegUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirLlegUbiGeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegUbiGeo(String value) {
        this.dirLlegUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDireccion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegDireccion() {
        return dirLlegDireccion;
    }

    /**
     * Define el valor de la propiedad dirLlegDireccion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegDireccion(String value) {
        this.dirLlegDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegUrbaniza.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegUrbaniza() {
        return dirLlegUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirLlegUrbaniza.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegUrbaniza(String value) {
        this.dirLlegUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegProvincia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegProvincia() {
        return dirLlegProvincia;
    }

    /**
     * Define el valor de la propiedad dirLlegProvincia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegProvincia(String value) {
        this.dirLlegProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDepart.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegDepart() {
        return dirLlegDepart;
    }

    /**
     * Define el valor de la propiedad dirLlegDepart.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegDepart(String value) {
        this.dirLlegDepart = value;
    }

    /**
     * Obtiene el valor de la propiedad dirLlegDistrito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirLlegDistrito() {
        return dirLlegDistrito;
    }

    /**
     * Define el valor de la propiedad dirLlegDistrito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirLlegDistrito(String value) {
        this.dirLlegDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegCodPais.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegCodPais() {
        return dirllegCodPais;
    }

    /**
     * Define el valor de la propiedad dirllegCodPais.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegCodPais(String value) {
        this.dirllegCodPais = value;
    }

    /**
     * Obtiene el valor de la propiedad placaVehiculo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    /**
     * Define el valor de la propiedad placaVehiculo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPlacaVehiculo(String value) {
        this.placaVehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad certVehiculo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCertVehiculo() {
        return certVehiculo;
    }

    /**
     * Define el valor de la propiedad certVehiculo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCertVehiculo(String value) {
        this.certVehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad marcavehiculo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMarcavehiculo() {
        return marcavehiculo;
    }

    /**
     * Define el valor de la propiedad marcavehiculo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMarcavehiculo(String value) {
        this.marcavehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad licencia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * Define el valor de la propiedad licencia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLicencia(String value) {
        this.licencia = value;
    }

    /**
     * Obtiene el valor de la propiedad rucTranspor.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRUCTranspor() {
        return rucTranspor;
    }

    /**
     * Define el valor de la propiedad rucTranspor.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRUCTranspor(String value) {
        this.rucTranspor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRucTrans.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoRucTrans() {
        return tipoRucTrans;
    }

    /**
     * Define el valor de la propiedad tipoRucTrans.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoRucTrans(String value) {
        this.tipoRucTrans = value;
    }

    /**
     * Obtiene el valor de la propiedad razoTrans.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRazoTrans() {
        return razoTrans;
    }

    /**
     * Define el valor de la propiedad razoTrans.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRazoTrans(String value) {
        this.razoTrans = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadTransporte.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModalidadTransporte() {
        return modalidadTransporte;
    }

    /**
     * Define el valor de la propiedad modalidadTransporte.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModalidadTransporte(String value) {
        this.modalidadTransporte = value;
    }

    /**
     * Obtiene el valor de la propiedad totalPesoBruto.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTotalPesoBruto() {
        return totalPesoBruto;
    }

    /**
     * Define el valor de la propiedad totalPesoBruto.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotalPesoBruto(String value) {
        this.totalPesoBruto = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoLocalAnexo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodigoLocalAnexo() {
        return codigoLocalAnexo;
    }

    /**
     * Define el valor de la propiedad codigoLocalAnexo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodigoLocalAnexo(String value) {
        this.codigoLocalAnexo = value;
    }

    /**
     * Obtiene el valor de la propiedad numPlacaVehi.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumPlacaVehi() {
        return numPlacaVehi;
    }

    /**
     * Define el valor de la propiedad numPlacaVehi.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumPlacaVehi(String value) {
        this.numPlacaVehi = value;
    }

    /**
     * Obtiene el valor de la propiedad imprDest.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getImprDest() {
        return imprDest;
    }

    /**
     * Define el valor de la propiedad imprDest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setImprDest(String value) {
        this.imprDest = value;
    }

    /**
     * Obtiene el valor de la propiedad nombSucursal.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNombSucursal() {
        return nombSucursal;
    }

    /**
     * Define el valor de la propiedad nombSucursal.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNombSucursal(String value) {
        this.nombSucursal = value;
    }

    /**
     * Obtiene el valor de la propiedad colaimpresion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCOLAIMPRESION() {
        return colaimpresion;
    }

    /**
     * Define el valor de la propiedad colaimpresion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCOLAIMPRESION(String value) {
        this.colaimpresion = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadItem.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCantidadItem() {
        return cantidadItem;
    }

    /**
     * Define el valor de la propiedad cantidadItem.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCantidadItem(String value) {
        this.cantidadItem = value;
    }

    /**
     * Obtiene el valor de la propiedad horaEmision.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHoraEmision() {
        return horaEmision;
    }

    /**
     * Define el valor de la propiedad horaEmision.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHoraEmision(String value) {
        this.horaEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoLeyenda.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodigoLeyenda() {
        return codigoLeyenda;
    }

    /**
     * Define el valor de la propiedad codigoLeyenda.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodigoLeyenda(String value) {
        this.codigoLeyenda = value;
    }

    /**
     * Obtiene el valor de la propiedad codiempr.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCODIEMPR() {
        return codiempr;
    }

    /**
     * Define el valor de la propiedad codiempr.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCODIEMPR(String value) {
        this.codiempr = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad tpoMoneda.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTpoMoneda() {
        return tpoMoneda;
    }

    /**
     * Define el valor de la propiedad tpoMoneda.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTpoMoneda(String value) {
        this.tpoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad rutEmisor.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRUTEmisor() {
        return rutEmisor;
    }

    /**
     * Define el valor de la propiedad rutEmisor.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRUTEmisor(String value) {
        this.rutEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad codiComu.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodiComu() {
        return codiComu;
    }

    /**
     * Define el valor de la propiedad codiComu.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodiComu(String value) {
        this.codiComu = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRUTRecep.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoRUTRecep() {
        return tipoRUTRecep;
    }

    /**
     * Define el valor de la propiedad tipoRUTRecep.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoRUTRecep(String value) {
        this.tipoRUTRecep = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepDpto.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepDpto() {
        return dirRecepDpto;
    }

    /**
     * Define el valor de la propiedad dirRecepDpto.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepDpto(String value) {
        this.dirRecepDpto = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotalAnticipo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMntTotalAnticipo() {
        return mntTotalAnticipo;
    }

    /**
     * Define el valor de la propiedad mntTotalAnticipo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMntTotalAnticipo(String value) {
        this.mntTotalAnticipo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirlleUbiGeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirlleUbiGeo() {
        return dirlleUbiGeo;
    }

    /**
     * Define el valor de la propiedad dirlleUbiGeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirlleUbiGeo(String value) {
        this.dirlleUbiGeo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOper.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoOper() {
        return tipoOper;
    }

    /**
     * Define el valor de la propiedad tipoOper.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoOper(String value) {
        this.tipoOper = value;
    }

    /**
     * Obtiene el valor de la propiedad codiSucu.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodiSucu() {
        return codiSucu;
    }

    /**
     * Define el valor de la propiedad codiSucu.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodiSucu(String value) {
        this.codiSucu = value;
    }

    /**
     * Obtiene el valor de la propiedad colaImpresion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getColaImpresion() {
        return colaImpresion;
    }

    /**
     * Define el valor de la propiedad colaImpresion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setColaImpresion(String value) {
        this.colaImpresion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirRecepUbigeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirRecepUbigeo() {
        return dirRecepUbigeo;
    }

    /**
     * Define el valor de la propiedad dirRecepUbigeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirRecepUbigeo(String value) {
        this.dirRecepUbigeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirEntregaUbigeo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirEntregaUbigeo() {
        return dirEntregaUbigeo;
    }

    /**
     * Define el valor de la propiedad dirEntregaUbigeo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirEntregaUbigeo(String value) {
        this.dirEntregaUbigeo = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegUrbaniza.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegUrbaniza() {
        return dirllegUrbaniza;
    }

    /**
     * Define el valor de la propiedad dirllegUrbaniza.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegUrbaniza(String value) {
        this.dirllegUrbaniza = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegDireccion.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegDireccion() {
        return dirllegDireccion;
    }

    /**
     * Define el valor de la propiedad dirllegDireccion.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegDireccion(String value) {
        this.dirllegDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegProvincia.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegProvincia() {
        return dirllegProvincia;
    }

    /**
     * Define el valor de la propiedad dirllegProvincia.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegProvincia(String value) {
        this.dirllegProvincia = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegDepart.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegDepart() {
        return dirllegDepart;
    }

    /**
     * Define el valor de la propiedad dirllegDepart.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegDepart(String value) {
        this.dirllegDepart = value;
    }

    /**
     * Obtiene el valor de la propiedad dirllegDistrito.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirllegDistrito() {
        return dirllegDistrito;
    }

    /**
     * Define el valor de la propiedad dirllegDistrito.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirllegDistrito(String value) {
        this.dirllegDistrito = value;
    }

    /**
     * Obtiene el valor de la propiedad cicloFactIni.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCicloFactIni() {
        return cicloFactIni;
    }

    /**
     * Define el valor de la propiedad cicloFactIni.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCicloFactIni(String value) {
        this.cicloFactIni = value;
    }

    /**
     * Obtiene el valor de la propiedad cicloFactFin.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCicloFactFin() {
        return cicloFactFin;
    }

    /**
     * Define el valor de la propiedad cicloFactFin.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCicloFactFin(String value) {
        this.cicloFactFin = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCanalFac.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoCanalFac() {
        return tipoCanalFac;
    }

    /**
     * Define el valor de la propiedad tipoCanalFac.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoCanalFac(String value) {
        this.tipoCanalFac = value;
    }

    /**
     * Obtiene el valor de la propiedad formaPago.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * Define el valor de la propiedad formaPago.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFormaPago(String value) {
        this.formaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad montoNetoPendPago.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMontoNetoPendPago() {
        return montoNetoPendPago;
    }

    /**
     * Define el valor de la propiedad montoNetoPendPago.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMontoNetoPendPago(String value) {
        this.montoNetoPendPago = value;
    }

}
