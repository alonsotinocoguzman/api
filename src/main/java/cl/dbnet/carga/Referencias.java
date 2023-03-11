
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Referencias complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Referencias"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroLinRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TpoDocRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SerieRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FolioRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RucRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechEmisDocRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntTotalDocRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MonedaDocRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NroOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ImporteOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MonedaOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ImporteMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MonedaMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MonedaReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MonedaObjetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechTipoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Referencias", propOrder = {
    "nroLinRef",
    "tpoDocRef",
    "serieRef",
    "folioRef",
    "tipoRef",
    "rucRef",
    "fechEmisDocRef",
    "mntTotalDocRef",
    "monedaDocRef",
    "fechOperacion",
    "nroOperacion",
    "importeOperacion",
    "monedaOperacion",
    "importeMovimiento",
    "monedaMovimiento",
    "fechaMovimiento",
    "totalMovimiento",
    "moneda",
    "monedaReferencia",
    "monedaObjetivo",
    "tipoCambio",
    "fechTipoCambio"
})
public class Referencias {

    @XmlElement(name = "NroLinRef")
    protected String nroLinRef;
    @XmlElement(name = "TpoDocRef")
    protected String tpoDocRef;
    @XmlElement(name = "SerieRef")
    protected String serieRef;
    @XmlElement(name = "FolioRef")
    protected String folioRef;
    @XmlElement(name = "TipoRef")
    protected String tipoRef;
    @XmlElement(name = "RucRef")
    protected String rucRef;
    @XmlElement(name = "FechEmisDocRef")
    protected String fechEmisDocRef;
    @XmlElement(name = "MntTotalDocRef")
    protected String mntTotalDocRef;
    @XmlElement(name = "MonedaDocRef")
    protected String monedaDocRef;
    @XmlElement(name = "FechOperacion")
    protected String fechOperacion;
    @XmlElement(name = "NroOperacion")
    protected String nroOperacion;
    @XmlElement(name = "ImporteOperacion")
    protected String importeOperacion;
    @XmlElement(name = "MonedaOperacion")
    protected String monedaOperacion;
    @XmlElement(name = "ImporteMovimiento")
    protected String importeMovimiento;
    @XmlElement(name = "MonedaMovimiento")
    protected String monedaMovimiento;
    @XmlElement(name = "FechaMovimiento")
    protected String fechaMovimiento;
    @XmlElement(name = "TotalMovimiento")
    protected String totalMovimiento;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "MonedaReferencia")
    protected String monedaReferencia;
    @XmlElement(name = "MonedaObjetivo")
    protected String monedaObjetivo;
    @XmlElement(name = "TipoCambio")
    protected String tipoCambio;
    @XmlElement(name = "FechTipoCambio")
    protected String fechTipoCambio;

    /**
     * Obtiene el valor de la propiedad nroLinRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroLinRef() {
        return nroLinRef;
    }

    /**
     * Define el valor de la propiedad nroLinRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroLinRef(String value) {
        this.nroLinRef = value;
    }

    /**
     * Obtiene el valor de la propiedad tpoDocRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpoDocRef() {
        return tpoDocRef;
    }

    /**
     * Define el valor de la propiedad tpoDocRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpoDocRef(String value) {
        this.tpoDocRef = value;
    }

    /**
     * Obtiene el valor de la propiedad serieRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieRef() {
        return serieRef;
    }

    /**
     * Define el valor de la propiedad serieRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieRef(String value) {
        this.serieRef = value;
    }

    /**
     * Obtiene el valor de la propiedad folioRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioRef() {
        return folioRef;
    }

    /**
     * Define el valor de la propiedad folioRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioRef(String value) {
        this.folioRef = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRef() {
        return tipoRef;
    }

    /**
     * Define el valor de la propiedad tipoRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRef(String value) {
        this.tipoRef = value;
    }

    /**
     * Obtiene el valor de la propiedad rucRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRucRef() {
        return rucRef;
    }

    /**
     * Define el valor de la propiedad rucRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRucRef(String value) {
        this.rucRef = value;
    }

    /**
     * Obtiene el valor de la propiedad fechEmisDocRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechEmisDocRef() {
        return fechEmisDocRef;
    }

    /**
     * Define el valor de la propiedad fechEmisDocRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechEmisDocRef(String value) {
        this.fechEmisDocRef = value;
    }

    /**
     * Obtiene el valor de la propiedad mntTotalDocRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMntTotalDocRef() {
        return mntTotalDocRef;
    }

    /**
     * Define el valor de la propiedad mntTotalDocRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMntTotalDocRef(String value) {
        this.mntTotalDocRef = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaDocRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaDocRef() {
        return monedaDocRef;
    }

    /**
     * Define el valor de la propiedad monedaDocRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaDocRef(String value) {
        this.monedaDocRef = value;
    }

    /**
     * Obtiene el valor de la propiedad fechOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechOperacion() {
        return fechOperacion;
    }

    /**
     * Define el valor de la propiedad fechOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechOperacion(String value) {
        this.fechOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nroOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroOperacion() {
        return nroOperacion;
    }

    /**
     * Define el valor de la propiedad nroOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroOperacion(String value) {
        this.nroOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad importeOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporteOperacion() {
        return importeOperacion;
    }

    /**
     * Define el valor de la propiedad importeOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporteOperacion(String value) {
        this.importeOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaOperacion() {
        return monedaOperacion;
    }

    /**
     * Define el valor de la propiedad monedaOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaOperacion(String value) {
        this.monedaOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad importeMovimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporteMovimiento() {
        return importeMovimiento;
    }

    /**
     * Define el valor de la propiedad importeMovimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporteMovimiento(String value) {
        this.importeMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaMovimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaMovimiento() {
        return monedaMovimiento;
    }

    /**
     * Define el valor de la propiedad monedaMovimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaMovimiento(String value) {
        this.monedaMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaMovimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * Define el valor de la propiedad fechaMovimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaMovimiento(String value) {
        this.fechaMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad totalMovimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalMovimiento() {
        return totalMovimiento;
    }

    /**
     * Define el valor de la propiedad totalMovimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalMovimiento(String value) {
        this.totalMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaReferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaReferencia() {
        return monedaReferencia;
    }

    /**
     * Define el valor de la propiedad monedaReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaReferencia(String value) {
        this.monedaReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaObjetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    /**
     * Define el valor de la propiedad monedaObjetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaObjetivo(String value) {
        this.monedaObjetivo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCambio() {
        return tipoCambio;
    }

    /**
     * Define el valor de la propiedad tipoCambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCambio(String value) {
        this.tipoCambio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechTipoCambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechTipoCambio() {
        return fechTipoCambio;
    }

    /**
     * Define el valor de la propiedad fechTipoCambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechTipoCambio(String value) {
        this.fechTipoCambio = value;
    }

}
