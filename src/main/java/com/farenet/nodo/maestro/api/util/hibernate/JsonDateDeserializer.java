package com.farenet.nodo.maestro.api.util.hibernate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Timestamp> {

	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public Timestamp deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));

		String date = p.getText();
		try {
			return new Timestamp(dateFormat.parse(date).getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
