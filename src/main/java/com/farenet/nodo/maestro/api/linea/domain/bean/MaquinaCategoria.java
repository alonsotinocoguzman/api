package com.farenet.nodo.maestro.api.linea.domain.bean;

import java.util.List;

import com.farenet.nodo.maestro.api.inspeccion.domain.Categoria;
import com.farenet.nodo.maestro.api.linea.domain.MaquinaParametro;

public class MaquinaCategoria {
	
	public String idMaquina;
	public String descripcionMaquina;
	public String modeloMaquina;
	public String tipoMaquina;
	public List<MaquinaParametro> parametrosMaquina;
	public List<Categoria> categorias;
	
	public Boolean isFoto;

}
