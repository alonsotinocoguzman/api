package com.farenet.nodo.maestro.api.linea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "defecto")
public class Defecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column()
	private String codigovalor;
	
	@Column()
	private String nombrevalor;
	
	@Column()
	private String nivelpeligro;

	public Defecto()
	{
		
	}
	public Defecto(String codigoValor, String nombreValor, String nivelPeligro)
	{
		this.codigovalor = codigoValor;
		this.nombrevalor = nombreValor;
		this.nivelpeligro = nivelPeligro;
	}
	
	public String getCodigovalor() {
		return codigovalor;
	}

	public void setCodigovalor(String codigovalor) {
		this.codigovalor = codigovalor;
	}

	public String getNombrevalor() {
		return nombrevalor;
	}

	public void setNombrevalor(String nombrevalor) {
		this.nombrevalor = nombrevalor;
	}

	public String getNivelpeligro() {
		return nivelpeligro;
	}

	public void setNivelpeligro(String nivelpeligro) {
		this.nivelpeligro = nivelpeligro;
	}
	
	
}
