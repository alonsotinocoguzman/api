package com.farenet.nodo.maestro.api.linea.domain;

import java.util.List;
import javax.persistence.*;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;

@Entity
@Table(name = "linea_etapa")
public class LineaEtapa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="pc_cod")
	private String pcCod;

	//bi-directional many-to-one association to Etapa
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(updatable= false)
	private Etapa etapa;

	//bi-directional many-to-one association to Linea
	@ManyToOne
	@JoinColumn(updatable= false)
	private Linea linea;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(updatable= false)
	@JoinTable(name = "linea_etapa_maquina", joinColumns = @JoinColumn(name = "linea_etapa_id"), inverseJoinColumns = @JoinColumn( name = "maquinas_id"))
	private List<Maquina> maquinas;
	
	public List<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public LineaEtapa() {
	}

	public String getPcCod() {
		return this.pcCod;
	}

	public void setPcCod(String pcCod) {
		this.pcCod = pcCod;
	}

	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Linea getLinea() {
		return this.linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

}