package com.farenet.nodo.maestro.api.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farenet.nodo.maestro.api.caja.domain.Descuento;
import com.farenet.nodo.maestro.api.caja.domain.DescuentoDetalle;
import com.farenet.nodo.maestro.api.caja.domain.DescuentoMasivoDetalle;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiffDescuentoLog implements Runnable {

	private String target;
	private Descuento descuento1;
	private Descuento descuento2;
	private TriggerTaskFactory trigger;
	private String username;
	
	

	public DiffDescuentoLog(String target,  Descuento descuento1,Descuento descuento2, String username, TriggerTaskFactory trigger) {
		this.target = target;
		this.descuento1 = descuento1;
		this.descuento2 = descuento2;
		this.trigger = trigger;
		this.username = username;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		SimpleDateFormat df = new SimpleDateFormat(UIUtils.FORMATO_FECHA);
		ObjectMapper mapper = new ObjectMapper().setDateFormat(df);

		Map<String, Object> diff = new HashMap<>();

		if(descuento1==null)
		{
			diff.put("limiteMontoMaximo", descuento2.getLimiteMontoMaximo());
			diff.put("empresa", descuento2.getEmpresa().getNrodocumentoidentidad());
			diff.put("tipoDescuento", descuento2.getTipodescuento().getKey());
			diff.put("fechinicio", descuento2.getFechInicio());
			diff.put("fechfin", descuento2.getFechFin());

			List<Map<String,Object>> detallesDiff = new ArrayList<>();
			for(DescuentoDetalle dd2 : descuento2.getDescuentodetalles())
			{
				Map<String,Object> detallediff = new HashMap<>();
				detallediff.put("conceptoinspeccion", dd2.getConceptoinspeccion().getKey());
				detallediff.put("planta", dd2.getPlanta().getKey());
				detallediff.put("tipopagodescuento", dd2.getTipoPagoDescuento().getKey());
				detallediff.put("montoactivacion", dd2.getMontoActivacion());
				detallediff.put("formapago", dd2.getFormaPago().getKey());
				detallediff.put("monto", dd2.getMonto());
				detallediff.put("fechinicio", dd2.getFechInicio());
				detallediff.put("fechfin", dd2.getFechFin());
				detallesDiff.add(detallediff);

			}

			diff.put("detalles", detallesDiff);
		}else{


			//validamos diferencias
			if(descuento1.getLimiteMontoMaximo()!=descuento2.getLimiteMontoMaximo())
				diff.put("limiteMontoMaximo", descuento2.getLimiteMontoMaximo());

			if(descuento1.getEmpresa().getNrodocumentoidentidad()!=descuento2.getEmpresa().getNrodocumentoidentidad())
				diff.put("empresa", descuento2.getEmpresa().getNrodocumentoidentidad());

			if(descuento1.getTipodescuento().getKey()!=descuento2.getTipodescuento().getKey())
				diff.put("tipoDescuento", descuento2.getTipodescuento().getKey());

			if(descuento1.getFechInicio().after(descuento2.getFechInicio()) || descuento1.getFechInicio().before(descuento2.getFechInicio()))
				diff.put("fechinicio", descuento2.getFechInicio());

			if(descuento1.getFechFin().after(descuento2.getFechFin()) || descuento1.getFechFin().before(descuento2.getFechFin()))
				diff.put("fechfin", descuento2.getFechFin());

			List<Map<String,Object>> detallesDiff = new ArrayList<>();
			//verificamos la lista de descuentos detalles
			for(DescuentoDetalle dd2 : descuento2.getDescuentodetalles())
			{
				Map<String,Object> detallediff = new HashMap<>();
				boolean found = false;

				for(DescuentoDetalle dd1 : descuento1.getDescuentodetalles())
				{
					if(dd1.getId()==dd2.getId())
					{
						detallediff.put("id", dd2.getId());
						if(dd1.getConceptoinspeccion().getKey()!=dd2.getConceptoinspeccion().getKey())
							detallediff.put("conceptoinspeccion", dd2.getConceptoinspeccion().getKey());

						if(dd1.getPlanta().getKey()!=dd2.getPlanta().getKey())
							detallediff.put("planta", dd2.getPlanta().getKey());

						if(dd1.getTipoPagoDescuento().getKey()!=dd2.getTipoPagoDescuento().getKey())
							detallediff.put("tipopagodescuento", dd2.getTipoPagoDescuento().getKey());

						if(dd1.getMontoActivacion()!=dd2.getMontoActivacion())
							detallediff.put("montoactivacion", dd2.getMontoActivacion());

						if(dd1.getFormaPago().getKey()!=dd2.getFormaPago().getKey())
							detallediff.put("formapago", dd2.getFormaPago().getKey());

						if(dd1.getMonto()!=dd2.getMonto())
							detallediff.put("monto", dd2.getMonto());

						if(dd1.getFechInicio().after(dd2.getFechInicio()) || dd1.getFechInicio().before(dd2.getFechInicio()))
							detallediff.put("fechinicio", dd2.getFechInicio());

						if(dd1.getFechFin().after(dd2.getFechFin()) || dd1.getFechFin().before(dd2.getFechFin()))
							detallediff.put("fechfin", dd2.getFechFin());

						if(detallediff.keySet().size()>1)
							detallesDiff.add(detallediff);

						found = true;
					}
				}

				if(!found)
				{
					detallediff.put("conceptoinspeccion", dd2.getConceptoinspeccion().getKey());
					detallediff.put("planta", dd2.getPlanta().getKey());
					detallediff.put("tipopagodescuento", dd2.getTipoPagoDescuento().getKey());
					detallediff.put("montoactivacion", dd2.getMontoActivacion());
					detallediff.put("formapago", dd2.getFormaPago().getKey());
					detallediff.put("monto", dd2.getMonto());
					detallediff.put("fechinicio", dd2.getFechInicio());
					detallediff.put("fechfin", dd2.getFechFin());
					detallesDiff.add(detallediff);

				}
			}

			diff.put("detalles", detallesDiff);
		}




		try {
			trigger.launch( username, target, 	"DIFERENCIAS DESCUENTO", mapper.writeValueAsString(diff));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
