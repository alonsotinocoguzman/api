package com.farenet.nodo.maestro.api.linea.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;

@Entity
@Audited
@Table(name = "maquina")
public class Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private String descripcion;

	private Boolean generaarchivo;

	private String nombreequipo;
	
	private String modelo;
	
	private String serie;

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinColumn(updatable= false)
	private Linea linea;

	@OneToMany(fetch=FetchType.EAGER)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JoinTable(name = "maquina_maquina_parametros", joinColumns = @JoinColumn(name = "maquina_id"), inverseJoinColumns = @JoinColumn( name = "parametros_id"))
	private List<MaquinaParametro> parametros;

	//bi-directional many-to-one association to Tipomaquina
	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Tipomaquina tipomaquina;


    @OneToMany(mappedBy = "maquina")
    private Set<ResultadoMaquina> resultados;
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public List<MaquinaParametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<MaquinaParametro> parametros) {
		this.parametros = parametros;
	}

	public Maquina() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getGeneraarchivo() {
		return this.generaarchivo;
	}

	public void setGeneraarchivo(Boolean generaarchivo) {
		this.generaarchivo = generaarchivo;
	}

	public String getNombreequipo() {
		return this.nombreequipo;
	}

	public void setNombreequipo(String nombreequipo) {
		this.nombreequipo = nombreequipo;
	}


	public Tipomaquina getTipomaquina() {
		return this.tipomaquina;
	}

	public void setTipomaquina(Tipomaquina tipomaquina) {
		this.tipomaquina = tipomaquina;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

}