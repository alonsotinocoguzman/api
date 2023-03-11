package com.farenet.nodo.maestro.api.linea.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.farenet.nodo.maestro.api.util.Auditoria;
import com.farenet.nodo.maestro.api.util.hibernate.user.types.JSONUserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.RelationTargetAuditMode;

@TypeDef(name = "json", typeClass = JSONUserType.class, parameters = {
		@org.hibernate.annotations.Parameter(name = JSONUserType.CLASS, value = "java.util.Map") })
@Entity
@Table(name = "resultado_maquina")
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class ResultadoMaquina extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Type(type = "json")
	private Map<String, Object> data;

	private String fechainicio;

	private String fechafin;

	@Column(name = "insp_visual")
	private int inspVisual;


	private Boolean manual;

	@Type(type = "json")
	private Map<String, Object> postdata;

	private String resultado;


	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL })
	@NotAudited
	@JoinTable(name = "resultado_maquina_defecto", joinColumns = @JoinColumn(name = "resultado_maquina_id"), inverseJoinColumns = @JoinColumn(name = "defectos_id"))
	private List<Defecto> defectos = new ArrayList<Defecto>();

	

	public List<Defecto> getDefectos() {
		return defectos;
	}

	public void setDefectos(List<Defecto> defectos) {
		this.defectos = defectos;
	}

	// bi-directional many-to-one association to Maquina
	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Maquina maquina;


	@ManyToOne(fetch= FetchType.LAZY )
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Inspeccion inspeccion;

	public Inspeccion getInspeccion() {
		return inspeccion;
	}

	public void setInspeccion(Inspeccion inspeccion) {
		this.inspeccion = inspeccion;
	}

	public ResultadoMaquina() {
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, Object> getPostdata() {
		return postdata;
	}

	public void setPostdata(Map<String, Object> postdata) {
		this.postdata = postdata;
	}

	public int getInspVisual() {
		return this.inspVisual;
	}

	public void setInspVisual(int inspVisual) {
		this.inspVisual = inspVisual;
	}


	public Boolean getManual() {
		return this.manual;
	}

	public void setManual(Boolean manual) {
		this.manual = manual;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}