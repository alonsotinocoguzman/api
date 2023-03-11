
package cl.dbnet.carga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DetaDetraccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DetaDetraccion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiDetraccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValorDetraccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MntDetraccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PorcentajeDetraccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MatriculaHidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NomEmbaHidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescEspecieHidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarDescHidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaDescHidro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NumPlacaMTC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValorReferPreliminar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferencial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferPreViaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FactorRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PuntoOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PuntoDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CargaEfectiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferVehiculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ConfigVehicular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CargaUtil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MontoReferTMViaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetaDetraccion", propOrder = {
    "codiDetraccion",
    "valorDetraccion",
    "mntDetraccion",
    "porcentajeDetraccion",
    "matriculaHidro",
    "nomEmbaHidro",
    "descEspecieHidro",
    "lugarDescHidro",
    "fechaDescHidro",
    "numPlacaMTC",
    "valorReferPreliminar",
    "montoReferencial",
    "montoReferPreViaje",
    "factorRetorno",
    "puntoOrigen",
    "puntoDestino",
    "cargaEfectiva",
    "montoReferVehiculo",
    "configVehicular",
    "cargaUtil",
    "montoReferTMViaje"
})
public class DetaDetraccion {

    protected String codiDetraccion;
    @XmlElement(name = "ValorDetraccion")
    protected String valorDetraccion;
    @XmlElement(name = "MntDetraccion")
    protected String mntDetraccion;
    @XmlElement(name = "PorcentajeDetraccion")
    protected String porcentajeDetraccion;
    @XmlElement(name = "MatriculaHidro")
    protected String matriculaHidro;
    @XmlElement(name = "NomEmbaHidro")
    protected String nomEmbaHidro;
    @XmlElement(name = "DescEspecieHidro")
    protected String descEspecieHidro;
    @XmlElement(name = "LugarDescHidro")
    protected String lugarDescHidro;
    @XmlElement(name = "FechaDescHidro")
    protected String fechaDescHidro;
    @XmlElement(name = "NumPlacaMTC")
    protected String numPlacaMTC;
    @XmlElement(name = "ValorReferPreliminar")
    protected String valorReferPreliminar;
    @XmlElement(name = "MontoReferencial")
    protected String montoReferencial;
    @XmlElement(name = "MontoReferPreViaje")
    protected String montoReferPreViaje;
    @XmlElement(name = "FactorRetorno")
    protected String factorRetorno;
    @XmlElement(name = "PuntoOrigen")
    protected String puntoOrigen;
    @XmlElement(name = "PuntoDestino")
    protected String puntoDestino;
    @XmlElement(name = "CargaEfectiva")
    protected String cargaEfectiva;
    @XmlElement(name = "MontoReferVehiculo")
    protected String montoReferVehiculo;
    @XmlElement(name = "ConfigVehicular")
    protected String configVehicular;
    @XmlElement(name = "CargaUtil")
    protected String cargaUtil;
    @XmlElement(name = "MontoReferTMViaje")
    protected String montoReferTMViaje;

    /**
     * Obtiene el valor de la propiedad codiDetraccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiDetraccion() {
        return codiDetraccion;
    }

    /**
     * Define el valor de la propiedad codiDetraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiDetraccion(String value) {
        this.codiDetraccion = value;
    }

    /**
     * Obtiene el valor de la propiedad valorDetraccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorDetraccion() {
        return valorDetraccion;
    }

    /**
     * Define el valor de la propiedad valorDetraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorDetraccion(String value) {
        this.valorDetraccion = value;
    }

    /**
     * Obtiene el valor de la propiedad mntDetraccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMntDetraccion() {
        return mntDetraccion;
    }

    /**
     * Define el valor de la propiedad mntDetraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMntDetraccion(String value) {
        this.mntDetraccion = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeDetraccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPorcentajeDetraccion() {
        return porcentajeDetraccion;
    }

    /**
     * Define el valor de la propiedad porcentajeDetraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPorcentajeDetraccion(String value) {
        this.porcentajeDetraccion = value;
    }

    /**
     * Obtiene el valor de la propiedad matriculaHidro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatriculaHidro() {
        return matriculaHidro;
    }

    /**
     * Define el valor de la propiedad matriculaHidro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatriculaHidro(String value) {
        this.matriculaHidro = value;
    }

    /**
     * Obtiene el valor de la propiedad nomEmbaHidro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEmbaHidro() {
        return nomEmbaHidro;
    }

    /**
     * Define el valor de la propiedad nomEmbaHidro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEmbaHidro(String value) {
        this.nomEmbaHidro = value;
    }

    /**
     * Obtiene el valor de la propiedad descEspecieHidro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEspecieHidro() {
        return descEspecieHidro;
    }

    /**
     * Define el valor de la propiedad descEspecieHidro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEspecieHidro(String value) {
        this.descEspecieHidro = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarDescHidro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDescHidro() {
        return lugarDescHidro;
    }

    /**
     * Define el valor de la propiedad lugarDescHidro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDescHidro(String value) {
        this.lugarDescHidro = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaDescHidro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDescHidro() {
        return fechaDescHidro;
    }

    /**
     * Define el valor de la propiedad fechaDescHidro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDescHidro(String value) {
        this.fechaDescHidro = value;
    }

    /**
     * Obtiene el valor de la propiedad numPlacaMTC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumPlacaMTC() {
        return numPlacaMTC;
    }

    /**
     * Define el valor de la propiedad numPlacaMTC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumPlacaMTC(String value) {
        this.numPlacaMTC = value;
    }

    /**
     * Obtiene el valor de la propiedad valorReferPreliminar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorReferPreliminar() {
        return valorReferPreliminar;
    }

    /**
     * Define el valor de la propiedad valorReferPreliminar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorReferPreliminar(String value) {
        this.valorReferPreliminar = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferencial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferencial() {
        return montoReferencial;
    }

    /**
     * Define el valor de la propiedad montoReferencial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferencial(String value) {
        this.montoReferencial = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferPreViaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferPreViaje() {
        return montoReferPreViaje;
    }

    /**
     * Define el valor de la propiedad montoReferPreViaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferPreViaje(String value) {
        this.montoReferPreViaje = value;
    }

    /**
     * Obtiene el valor de la propiedad factorRetorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactorRetorno() {
        return factorRetorno;
    }

    /**
     * Define el valor de la propiedad factorRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactorRetorno(String value) {
        this.factorRetorno = value;
    }

    /**
     * Obtiene el valor de la propiedad puntoOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntoOrigen() {
        return puntoOrigen;
    }

    /**
     * Define el valor de la propiedad puntoOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntoOrigen(String value) {
        this.puntoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad puntoDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntoDestino() {
        return puntoDestino;
    }

    /**
     * Define el valor de la propiedad puntoDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntoDestino(String value) {
        this.puntoDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad cargaEfectiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargaEfectiva() {
        return cargaEfectiva;
    }

    /**
     * Define el valor de la propiedad cargaEfectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargaEfectiva(String value) {
        this.cargaEfectiva = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferVehiculo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferVehiculo() {
        return montoReferVehiculo;
    }

    /**
     * Define el valor de la propiedad montoReferVehiculo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferVehiculo(String value) {
        this.montoReferVehiculo = value;
    }

    /**
     * Obtiene el valor de la propiedad configVehicular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigVehicular() {
        return configVehicular;
    }

    /**
     * Define el valor de la propiedad configVehicular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigVehicular(String value) {
        this.configVehicular = value;
    }

    /**
     * Obtiene el valor de la propiedad cargaUtil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargaUtil() {
        return cargaUtil;
    }

    /**
     * Define el valor de la propiedad cargaUtil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargaUtil(String value) {
        this.cargaUtil = value;
    }

    /**
     * Obtiene el valor de la propiedad montoReferTMViaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoReferTMViaje() {
        return montoReferTMViaje;
    }

    /**
     * Define el valor de la propiedad montoReferTMViaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoReferTMViaje(String value) {
        this.montoReferTMViaje = value;
    }

}
