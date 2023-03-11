package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

import java.util.List;

public class EstadisticaBean {
	
	public String id;
	public String key;
	public String nombre;
	public double montoDesc;
	public long cantidadReins;
	public long cantidadDupli;
	public long cantidadReimpr;
	public long cantidadAnu;
	public long cantidadErrorImpr;
	public String fecha;
	
	public double montoAlianza;
	public double montoCotizacion;
	public double montoPromocion;
	public double montoCortesia;
	
	public EstadisticaBean() {
		super();
	}
}
