package com.farenet.nodo.maestro.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;


@Configuration
@EnableReactor
public class EventConfiguration {

	 @Bean
	  public Reactor r(Environment env) {
	    // implicit Environment is injected into bean def method
	    return Reactors.reactor().env(env).get();
	  }
}
