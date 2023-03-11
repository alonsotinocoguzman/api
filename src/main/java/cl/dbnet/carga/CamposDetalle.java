
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CamposDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CamposDetalle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinea" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="NroLinDet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QtyItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnmdItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VlrCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NmbItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PrcItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PrcItemSinIgv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescuentoMonto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IndExe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoTipoIgv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TasaIgv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ImpuestoIgv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoIsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoTipoIsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoIsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TasaIsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodigoProductoSunat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumDocHuesped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoDocHuesped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodPaisEmisPas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NomHuesped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodPaisResHuesped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaqTurDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaqTurTipoDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaqTurNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DetIndCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DetFactorCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DetCodigoCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DetMontoCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DetMBaseCargoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CamposDetalle", propOrder = {
    "nroLinea",
    "nroLinDet",
    "qtyItem",
    "unmdItem",
    "vlrCodigo",
    "nmbItem",
    "prcItem",
    "prcItemSinIgv",
    "montoItem",
    "descuentoMonto",
    "indExe",
    "codigoTipoIgv",
    "tasaIgv",
    "impuestoIgv",
    "codigoIsc",
    "codigoTipoIsc",
    "montoIsc",
    "tasaIsc",
    "codigoProductoSunat",
    "numDocHuesped",
    "tipoDocHuesped",
    "codPaisEmisPas",
    "nomHuesped",
    "codPaisResHuesped",
    "paqTurDocumento",
    "paqTurTipoDoc",
    "paqTurNombre",
    "detIndCargoDescuento",
    "detFactorCargoDescuento",
    "detCodigoCargoDescuento",
    "detMontoCargoDescuento",
    "detMBaseCargoDescuento"
})
public class CamposDetalle {

    @XmlElement(name = "NroLinea")
    protected int nroLinea;
    @XmlElement(name = "NroLinDet")
    protected String nroLinDet;
    @XmlElement(name = "QtyItem")
    protected String qtyItem;
    @XmlElement(name = "UnmdItem")
    protected String unmdItem;
    @XmlElement(name = "VlrCodigo")
    protected String vlrCodigo;
    @XmlElement(name = "NmbItem")
    protected String nmbItem;
    @XmlElement(name = "PrcItem")
    protected String prcItem;
    @XmlElement(name = "PrcItemSinIgv")
    protected String prcItemSinIgv;
    @XmlElement(name = "MontoItem")
    protected String montoItem;
    @XmlElement(name = "DescuentoMonto")
    protected String descuentoMonto;
    @XmlElement(name = "IndExe")
    protected String indExe;
    @XmlElement(name = "CodigoTipoIgv")
    protected String codigoTipoIgv;
    @XmlElement(name = "TasaIgv")
    protected String tasaIgv;
    @XmlElement(name = "ImpuestoIgv")
    protected String impuestoIgv;
    @XmlElement(name = "CodigoIsc")
    protected String codigoIsc;
    @XmlElement(name = "CodigoTipoIsc")
    protected String codigoTipoIsc;
    @XmlElement(name = "MontoIsc")
    protected String montoIsc;
    @XmlElement(name = "TasaIsc")
    protected String tasaIsc;
    @XmlElement(name = "CodigoProductoSunat")
    protected String codigoProductoSunat;
    @XmlElement(name = "NumDocHuesped")
    protected String numDocHuesped;
    @XmlElement(name = "TipoDocHuesped")
    protected String tipoDocHuesped;
    @XmlElement(name = "CodPaisEmisPas")
    protected String codPaisEmisPas;
    @XmlElement(name = "NomHuesped")
    protected String nomHuesped;
    @XmlElement(name = "CodPaisResHuesped")
    protected String codPaisResHuesped;
    @XmlElement(name = "PaqTurDocumento")
    protected String paqTurDocumento;
    @XmlElement(name = "PaqTurTipoDoc")
    protected String paqTurTipoDoc;
    @XmlElement(name = "PaqTurNombre")
    protected String paqTurNombre;
    @XmlElement(name = "DetIndCargoDescuento")
    protected String detIndCargoDescuento;
    @XmlElement(name = "DetFactorCargoDescuento")
    protected String detFactorCargoDescuento;
    @XmlElement(name = "DetCodigoCargoDescuento")
    protected String detCodigoCargoDescuento;
    @XmlElement(name = "DetMontoCargoDescuento")
    protected String detMontoCargoDescuento;
    @XmlElement(name = "DetMBaseCargoDescuento")
    protected String detMBaseCargoDescuento;

    /**
     * Obtiene el valor de la propiedad nroLinea.
     * 
     */
    public int getNroLinea() {
        return nroLinea;
    }

    /**
     * Define el valor de la propiedad nroLinea.
     * 
     */
    public void setNroLinea(int value) {
        this.nroLinea = value;
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

    /**
     * Obtiene el valor de la propiedad qtyItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtyItem() {
        return qtyItem;
    }

    /**
     * Define el valor de la propiedad qtyItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtyItem(String value) {
        this.qtyItem = value;
    }

    /**
     * Obtiene el valor de la propiedad unmdItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnmdItem() {
        return unmdItem;
    }

    /**
     * Define el valor de la propiedad unmdItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnmdItem(String value) {
        this.unmdItem = value;
    }

    /**
     * Obtiene el valor de la propiedad vlrCodigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVlrCodigo() {
        return vlrCodigo;
    }

    /**
     * Define el valor de la propiedad vlrCodigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVlrCodigo(String value) {
        this.vlrCodigo = value;
    }

    /**
     * Obtiene el valor de la propiedad nmbItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmbItem() {
        return nmbItem;
    }

    /**
     * Define el valor de la propiedad nmbItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmbItem(String value) {
        this.nmbItem = value;
    }

    /**
     * Obtiene el valor de la propiedad prcItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrcItem() {
        return prcItem;
    }

    /**
     * Define el valor de la propiedad prcItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrcItem(String value) {
        this.prcItem = value;
    }

    /**
     * Obtiene el valor de la propiedad prcItemSinIgv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrcItemSinIgv() {
        return prcItemSinIgv;
    }

    /**
     * Define el valor de la propiedad prcItemSinIgv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrcItemSinIgv(String value) {
        this.prcItemSinIgv = value;
    }

    /**
     * Obtiene el valor de la propiedad montoItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoItem() {
        return montoItem;
    }

    /**
     * Define el valor de la propiedad montoItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoItem(String value) {
        this.montoItem = value;
    }

    /**
     * Obtiene el valor de la propiedad descuentoMonto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescuentoMonto() {
        return descuentoMonto;
    }

    /**
     * Define el valor de la propiedad descuentoMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescuentoMonto(String value) {
        this.descuentoMonto = value;
    }

    /**
     * Obtiene el valor de la propiedad indExe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndExe() {
        return indExe;
    }

    /**
     * Define el valor de la propiedad indExe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndExe(String value) {
        this.indExe = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoIgv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoIgv() {
        return codigoTipoIgv;
    }

    /**
     * Define el valor de la propiedad codigoTipoIgv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoIgv(String value) {
        this.codigoTipoIgv = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaIgv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTasaIgv() {
        return tasaIgv;
    }

    /**
     * Define el valor de la propiedad tasaIgv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTasaIgv(String value) {
        this.tasaIgv = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestoIgv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpuestoIgv() {
        return impuestoIgv;
    }

    /**
     * Define el valor de la propiedad impuestoIgv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpuestoIgv(String value) {
        this.impuestoIgv = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoIsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoIsc() {
        return codigoIsc;
    }

    /**
     * Define el valor de la propiedad codigoIsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoIsc(String value) {
        this.codigoIsc = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoIsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoIsc() {
        return codigoTipoIsc;
    }

    /**
     * Define el valor de la propiedad codigoTipoIsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoIsc(String value) {
        this.codigoTipoIsc = value;
    }

    /**
     * Obtiene el valor de la propiedad montoIsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoIsc() {
        return montoIsc;
    }

    /**
     * Define el valor de la propiedad montoIsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoIsc(String value) {
        this.montoIsc = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaIsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTasaIsc() {
        return tasaIsc;
    }

    /**
     * Define el valor de la propiedad tasaIsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTasaIsc(String value) {
        this.tasaIsc = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoProductoSunat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProductoSunat() {
        return codigoProductoSunat;
    }

    /**
     * Define el valor de la propiedad codigoProductoSunat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProductoSunat(String value) {
        this.codigoProductoSunat = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocHuesped.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocHuesped() {
        return numDocHuesped;
    }

    /**
     * Define el valor de la propiedad numDocHuesped.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocHuesped(String value) {
        this.numDocHuesped = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocHuesped.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocHuesped() {
        return tipoDocHuesped;
    }

    /**
     * Define el valor de la propiedad tipoDocHuesped.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocHuesped(String value) {
        this.tipoDocHuesped = value;
    }

    /**
     * Obtiene el valor de la propiedad codPaisEmisPas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPaisEmisPas() {
        return codPaisEmisPas;
    }

    /**
     * Define el valor de la propiedad codPaisEmisPas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPaisEmisPas(String value) {
        this.codPaisEmisPas = value;
    }

    /**
     * Obtiene el valor de la propiedad nomHuesped.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomHuesped() {
        return nomHuesped;
    }

    /**
     * Define el valor de la propiedad nomHuesped.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomHuesped(String value) {
        this.nomHuesped = value;
    }

    /**
     * Obtiene el valor de la propiedad codPaisResHuesped.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPaisResHuesped() {
        return codPaisResHuesped;
    }

    /**
     * Define el valor de la propiedad codPaisResHuesped.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPaisResHuesped(String value) {
        this.codPaisResHuesped = value;
    }

    /**
     * Obtiene el valor de la propiedad paqTurDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaqTurDocumento() {
        return paqTurDocumento;
    }

    /**
     * Define el valor de la propiedad paqTurDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaqTurDocumento(String value) {
        this.paqTurDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad paqTurTipoDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaqTurTipoDoc() {
        return paqTurTipoDoc;
    }

    /**
     * Define el valor de la propiedad paqTurTipoDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaqTurTipoDoc(String value) {
        this.paqTurTipoDoc = value;
    }

    /**
     * Obtiene el valor de la propiedad paqTurNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaqTurNombre() {
        return paqTurNombre;
    }

    /**
     * Define el valor de la propiedad paqTurNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaqTurNombre(String value) {
        this.paqTurNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad detIndCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetIndCargoDescuento() {
        return detIndCargoDescuento;
    }

    /**
     * Define el valor de la propiedad detIndCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetIndCargoDescuento(String value) {
        this.detIndCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad detFactorCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetFactorCargoDescuento() {
        return detFactorCargoDescuento;
    }

    /**
     * Define el valor de la propiedad detFactorCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetFactorCargoDescuento(String value) {
        this.detFactorCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad detCodigoCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetCodigoCargoDescuento() {
        return detCodigoCargoDescuento;
    }

    /**
     * Define el valor de la propiedad detCodigoCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetCodigoCargoDescuento(String value) {
        this.detCodigoCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad detMontoCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetMontoCargoDescuento() {
        return detMontoCargoDescuento;
    }

    /**
     * Define el valor de la propiedad detMontoCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetMontoCargoDescuento(String value) {
        this.detMontoCargoDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad detMBaseCargoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetMBaseCargoDescuento() {
        return detMBaseCargoDescuento;
    }

    /**
     * Define el valor de la propiedad detMBaseCargoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetMBaseCargoDescuento(String value) {
        this.detMBaseCargoDescuento = value;
    }

}
