package com.farenet.nodo.maestro.api.callcenter.util;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantesURLCapi {

	private
	@Value("${callcenter.url}")
	String sigella;

	public String VENTA_PLACA_CAPI_URL;
	public String SMS_CAPI_URL;


	@PostConstruct
	public void init(){
		VENTA_PLACA_CAPI_URL = sigella + "/venta/placa";
		SMS_CAPI_URL= sigella + "/enviados";
	}

}
