
package cl.dbnet.carga;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DescuentosRecargosyOtros complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="DescuentosRecargosyOtros"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Descuentos" type="{http://www.dbnet.cl}ArrayOfDescuentos" minOccurs="0"/&gt;
 *         &lt;element name="CargosDescuentosLinea" type="{http://www.dbnet.cl}ArrayOfDescuentos" minOccurs="0"/&gt;
 *         &lt;element name="DatosAdicionales" type="{http://www.dbnet.cl}ArrayOfDatosAdicionales" minOccurs="0"/&gt;
 *         &lt;element name="Referencias" type="{http://www.dbnet.cl}ArrayOfReferencias" minOccurs="0"/&gt;
 *         &lt;element name="Anticipos" type="{http://www.dbnet.cl}ArrayOfAnticipos" minOccurs="0"/&gt;
 *         &lt;element name="DatosGuia" type="{http://www.dbnet.cl}ArrayOfDatosGuia" minOccurs="0"/&gt;
 *         &lt;element name="Tramo" type="{http://www.dbnet.cl}ArrayOfTramo" minOccurs="0"/&gt;
 *         &lt;element name="Conductor" type="{http://www.dbnet.cl}ArrayOfConductor" minOccurs="0"/&gt;
 *         &lt;element name="PuertoAeropuerto" type="{http://www.dbnet.cl}ArrayOfPuertoAeropuerto" minOccurs="0"/&gt;
 *         &lt;element name="Transportes" type="{http://www.dbnet.cl}ArrayOfTransporte" minOccurs="0"/&gt;
 *         &lt;element name="PropiedadesAdicionales" type="{http://www.dbnet.cl}ArrayOfPropiedadAdicional" minOccurs="0"/&gt;
 *         &lt;element name="Pagos" type="{http://www.dbnet.cl}ArrayOfPago" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescuentosRecargosyOtros", propOrder = {
        "descuentos",
        "cargosDescuentosLinea",
        "datosAdicionales",
        "referencias",
        "anticipos",
        "datosGuia",
        "tramo",
        "conductor",
        "puertoAeropuerto",
        "transportes",
        "propiedadesAdicionales",
        "pagos"
})
public class DescuentosRecargosyOtros {

    @XmlElement(name = "Descuentos")
    protected ArrayOfDescuentos descuentos;
    @XmlElement(name = "CargosDescuentosLinea")
    protected ArrayOfDescuentos cargosDescuentosLinea;
    @XmlElement(name = "DatosAdicionales")
    protected ArrayOfDatosAdicionales datosAdicionales;
    @XmlElement(name = "Referencias")
    protected ArrayOfReferencias referencias;
    @XmlElement(name = "Anticipos")
    protected ArrayOfAnticipos anticipos;
    @XmlElement(name = "DatosGuia")
    protected ArrayOfDatosGuia datosGuia;
    @XmlElement(name = "Tramo")
    protected ArrayOfTramo tramo;
    @XmlElement(name = "Conductor")
    protected ArrayOfConductor conductor;
    @XmlElement(name = "PuertoAeropuerto")
    protected ArrayOfPuertoAeropuerto puertoAeropuerto;
    @XmlElement(name = "Transportes")
    protected ArrayOfTransporte transportes;
    @XmlElement(name = "PropiedadesAdicionales")
    protected ArrayOfPropiedadAdicional propiedadesAdicionales;
    @XmlElement(name = "Pagos")
    protected ArrayOfPago pagos;

    /**
     * Obtiene el valor de la propiedad descuentos.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfDescuentos }
     *
     */
    public ArrayOfDescuentos getDescuentos() {
        return descuentos;
    }

    /**
     * Define el valor de la propiedad descuentos.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfDescuentos }
     *
     */
    public void setDescuentos(ArrayOfDescuentos value) {
        this.descuentos = value;
    }

    /**
     * Obtiene el valor de la propiedad cargosDescuentosLinea.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfDescuentos }
     *
     */
    public ArrayOfDescuentos getCargosDescuentosLinea() {
        return cargosDescuentosLinea;
    }

    /**
     * Define el valor de la propiedad cargosDescuentosLinea.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfDescuentos }
     *
     */
    public void setCargosDescuentosLinea(ArrayOfDescuentos value) {
        this.cargosDescuentosLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad datosAdicionales.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfDatosAdicionales }
     *
     */
    public ArrayOfDatosAdicionales getDatosAdicionales() {
        return datosAdicionales;
    }

    /**
     * Define el valor de la propiedad datosAdicionales.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfDatosAdicionales }
     *
     */
    public void setDatosAdicionales(ArrayOfDatosAdicionales value) {
        this.datosAdicionales = value;
    }

    /**
     * Obtiene el valor de la propiedad referencias.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfReferencias }
     *
     */
    public ArrayOfReferencias getReferencias() {
        return referencias;
    }

    /**
     * Define el valor de la propiedad referencias.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfReferencias }
     *
     */
    public void setReferencias(ArrayOfReferencias value) {
        this.referencias = value;
    }

    /**
     * Obtiene el valor de la propiedad anticipos.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfAnticipos }
     *
     */
    public ArrayOfAnticipos getAnticipos() {
        return anticipos;
    }

    /**
     * Define el valor de la propiedad anticipos.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnticipos }
     *
     */
    public void setAnticipos(ArrayOfAnticipos value) {
        this.anticipos = value;
    }

    /**
     * Obtiene el valor de la propiedad datosGuia.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfDatosGuia }
     *
     */
    public ArrayOfDatosGuia getDatosGuia() {
        return datosGuia;
    }

    /**
     * Define el valor de la propiedad datosGuia.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfDatosGuia }
     *
     */
    public void setDatosGuia(ArrayOfDatosGuia value) {
        this.datosGuia = value;
    }

    /**
     * Obtiene el valor de la propiedad tramo.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfTramo }
     *
     */
    public ArrayOfTramo getTramo() {
        return tramo;
    }

    /**
     * Define el valor de la propiedad tramo.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfTramo }
     *
     */
    public void setTramo(ArrayOfTramo value) {
        this.tramo = value;
    }

    /**
     * Obtiene el valor de la propiedad conductor.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfConductor }
     *
     */
    public ArrayOfConductor getConductor() {
        return conductor;
    }

    /**
     * Define el valor de la propiedad conductor.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfConductor }
     *
     */
    public void setConductor(ArrayOfConductor value) {
        this.conductor = value;
    }

    /**
     * Obtiene el valor de la propiedad puertoAeropuerto.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfPuertoAeropuerto }
     *
     */
    public ArrayOfPuertoAeropuerto getPuertoAeropuerto() {
        return puertoAeropuerto;
    }

    /**
     * Define el valor de la propiedad puertoAeropuerto.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfPuertoAeropuerto }
     *
     */
    public void setPuertoAeropuerto(ArrayOfPuertoAeropuerto value) {
        this.puertoAeropuerto = value;
    }

    /**
     * Obtiene el valor de la propiedad transportes.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfTransporte }
     *
     */
    public ArrayOfTransporte getTransportes() {
        return transportes;
    }

    /**
     * Define el valor de la propiedad transportes.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransporte }
     *
     */
    public void setTransportes(ArrayOfTransporte value) {
        this.transportes = value;
    }

    /**
     * Obtiene el valor de la propiedad propiedadesAdicionales.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfPropiedadAdicional }
     *
     */
    public ArrayOfPropiedadAdicional getPropiedadesAdicionales() {
        return propiedadesAdicionales;
    }

    /**
     * Define el valor de la propiedad propiedadesAdicionales.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfPropiedadAdicional }
     *
     */
    public void setPropiedadesAdicionales(ArrayOfPropiedadAdicional value) {
        this.propiedadesAdicionales = value;
    }

    /**
     * Obtiene el valor de la propiedad pagos.
     *
     * @return
     *     possible object is
     *     {@link ArrayOfPago }
     *
     */
    public ArrayOfPago getPagos() {
        return pagos;
    }

    /**
     * Define el valor de la propiedad pagos.
     *
     * @param value
     *     allowed object is
     *     {@link ArrayOfPago }
     *
     */
    public void setPagos(ArrayOfPago value) {
        this.pagos = value;
    }

}
