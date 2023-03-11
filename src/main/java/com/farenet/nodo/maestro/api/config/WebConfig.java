package com.farenet.nodo.maestro.api.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new HibernateAwareObjectMapper());
		
		converters.add(converter);
 
        super.configureMessageConverters(converters);
    }
	
	class HibernateAwareObjectMapper extends ObjectMapper {

		private static final long serialVersionUID = 1L;

		public HibernateAwareObjectMapper() {
	        registerModule(new Hibernate4Module());
	    }
	}
}
