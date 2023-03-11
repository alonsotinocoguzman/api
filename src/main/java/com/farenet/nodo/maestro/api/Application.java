package com.farenet.nodo.maestro.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@PropertySource(value = "classpath:/application.properties")
public class Application {

    public static void main(String[] args) {

		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
    	
    	TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
        SpringApplication.run(Application.class, args);
        
        
        
    }

    
    
}