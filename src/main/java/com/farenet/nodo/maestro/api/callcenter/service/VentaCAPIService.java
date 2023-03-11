package com.farenet.nodo.maestro.api.callcenter.service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Campania;
import com.farenet.nodo.maestro.api.caja.domain.Campaniadetalle;
import com.farenet.nodo.maestro.api.caja.repository.CampaniaRepository;
import com.farenet.nodo.maestro.api.callcenter.bean.SMSCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.VentaCAPIBean;
import com.farenet.nodo.maestro.api.callcenter.util.ConstantesURLCapi;
import com.farenet.nodo.maestro.api.http.HTTPJsonEngine;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Conceptoinspecciondetalle;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.ConceptoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.PlantaRepository;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;
import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@Service
@Transactional
public class VentaCAPIService {


	@Autowired
	private ConstantesURLCapi constantesURLCapi;

	@Autowired
	private CampaniaRepository campaniaRepository;

	@Autowired
	private PlantaRepository plantaRepository;

	@Autowired
	private TriggerTaskFactory trigger;

	@Autowired
	private ConceptoinspeccionRepository conceptoinspeccionRepository;

	public VentaCAPIBean getVentafindByPlaca(String placa,String planta_key){
		Boolean esVenta = false;
		VentaCAPIBean ventaCAPIBean = new VentaCAPIBean();
		try{
			ventaCAPIBean = findVentaByPlacaToCAPI(placa);
			if(ventaCAPIBean!=null){
				if(ventaCAPIBean.getIdcampana() != null || !ventaCAPIBean.getIdcampana().equals("") ||
						ventaCAPIBean.getIdconcepto() != null || !ventaCAPIBean.getIdconcepto().equals("")){
					//conceptoinspecciondetalle
					Conceptoinspeccion conceptoinspeccion = new Conceptoinspeccion();
					conceptoinspeccion = conceptoinspeccionRepository.findOneByKeyAndPlanta(ventaCAPIBean.getIdconcepto(),planta_key);
					//obtener camapania
					Campania campania = new Campania();
					Planta planta = plantaRepository.findOneByKey(planta_key);
					campania = campaniaRepository.findOneByPlantaAndById((long) Integer.parseInt(ventaCAPIBean.getIdcampana()),
							planta);
					Date today = new Date();
					if (campania.getFechFin() != null && campania.getFechInicio() != null) {
						Timestamp fechFin = UIUtils.finDay(campania.getFechFin());
						Timestamp fechInicio = UIUtils.initDay(campania.getFechInicio());
						if (today.after(fechInicio) && today.before(fechFin)) {
						}else{
							campania=null;
						}
					}
					double descuentoVal = 0.0;
					if (campania != null && conceptoinspeccion != null) {
						for (Campaniadetalle campaniadetalle : campania.getCampaniadetalles()) {
							if (campaniadetalle.getConceptoinspeccion().getKey().equals(conceptoinspeccion.getKey())) {
								if(!campaniadetalle.isFlatActivado()){
									switch (campaniadetalle.getTipoDescuento()) {
										case MONTO:
											descuentoVal = (campaniadetalle.getValorDescuento());
											break;
										case PORCENTAJE:
											descuentoVal = (conceptoinspeccion.getValor(planta_key) * campaniadetalle.getValorDescuento() / 100);
											break;
									}
									ventaCAPIBean.setConceptoInspeccion(conceptoinspeccion);
									ventaCAPIBean.setCampania(campania);
									ventaCAPIBean.setTotalSinDesc(conceptoinspeccion.getValor(planta_key));
									ventaCAPIBean.setTotalDesc(descuentoVal);
									ventaCAPIBean.setImporteTotal(UIUtils.round(conceptoinspeccion.getValor(planta_key) - descuentoVal, 2));
									ventaCAPIBean.setBaseImponible(UIUtils.calcularBaseImponible(ventaCAPIBean.getImporteTotal()));
									ventaCAPIBean.setIgv(UIUtils.calcularIgv(ventaCAPIBean.getBaseImponible() ,ventaCAPIBean.getImporteTotal()));
								}else{
									descuentoVal = conceptoinspeccion.getValor(planta_key) - campaniadetalle.getMontoFlat();
									ventaCAPIBean.setConceptoInspeccion(conceptoinspeccion);
									ventaCAPIBean.setCampania(campania);
									ventaCAPIBean.setTotalSinDesc(conceptoinspeccion.getValor(planta_key));
									ventaCAPIBean.setTotalDesc(descuentoVal);
									ventaCAPIBean.setImporteTotal(UIUtils.round(campaniadetalle.getMontoFlat(), 2));
									ventaCAPIBean.setBaseImponible(UIUtils.calcularBaseImponible(ventaCAPIBean.getImporteTotal()));
									ventaCAPIBean.setIgv(UIUtils.calcularIgv(ventaCAPIBean.getBaseImponible(), ventaCAPIBean.getImporteTotal()));
								}
								esVenta = true;
								trigger.launch("", "api.capi.venta.ok", "VENTA", placa + " Tiene venta Call Center");
							}
						}
					}
				}
			}
			if(!esVenta){
				trigger.launch("", "api.capi.venta.no", "VENTA", placa + " no tiene venta Call Center");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trigger.launch("", "api.capi.venta.error", "ERROR CAPI",Util.printError(e));
		}
		return ventaCAPIBean;
	}

	public VentaCAPIBean findVentaByPlacaToCAPI(String placa){

		VentaCAPIBean result = null;

		StringBuilder url = new StringBuilder();
		url.append(constantesURLCapi.VENTA_PLACA_CAPI_URL);
		url.append("/");
		url.append(placa);

		try {
			String bodyjson = HTTPJsonEngine.getJSONHttpGet(url.toString());
			Type listType = new TypeToken<VentaCAPIBean>() {
			}.getType();

			result = new GsonBuilder().setDateFormat(UIUtils.FORMATO_FECHA_MEDIUM).create().fromJson(bodyjson, listType);

		}  catch (Exception e) {
			e.printStackTrace();
			trigger.launch("", "api.capi.venta.error", "ERROR CAPI",Util.printError(e));
		}
		return result;
	}


	public List<SMSCAPI> findListSMS(Date fechaInicio, Date fechaFin){

		SimpleDateFormat dtfPeru = new SimpleDateFormat(UIUtils.FORMATO_FECHA);

		List<SMSCAPI> resultado = null;

		StringBuilder url = new StringBuilder();
		url.append(constantesURLCapi.SMS_CAPI_URL);
		url.append("/");
		url.append(dtfPeru.format(fechaInicio));
		url.append("/");
		url.append(dtfPeru.format(fechaFin));

		try {
			String bodyjson = HTTPJsonEngine.getJSONHttpGet(url.toString());
			Type listType = new TypeToken<List<SMSCAPI>>() {
			}.getType();

			GsonBuilder builder = new GsonBuilder();

			// Register an adapter to manage the date types as long values
			builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
					return new Date(json.getAsJsonPrimitive().getAsLong());
				}
			});

			Gson gson = builder.create();

			resultado = gson.fromJson(bodyjson, listType);

		}  catch (Exception e) {
			e.printStackTrace();
			trigger.launch("", "api.capi.venta.error", "ERROR CAPI",Util.printError(e));
		}
		return resultado;
	}
}
