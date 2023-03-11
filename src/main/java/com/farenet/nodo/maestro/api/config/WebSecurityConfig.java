package com.farenet.nodo.maestro.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.farenet.nodo.maestro.api.seguridad.RestAuthenticationEntryPoint;
import com.farenet.nodo.maestro.api.seguridad.TokenAuthenticationProcessingFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
    
	@Autowired
	private RestAuthenticationEntryPoint  restAuthenticationEntryPoint;
	
    @Autowired
    TokenAuthenticationProcessingFilter filter;
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .userDetailsService(userDetailsService);
		 
	    }
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      
		http.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 
		 .and()
		 		.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
		 	
 		 .and()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
        	.antMatchers("/loginmaestro").permitAll()
        	.antMatchers("/capi/**").permitAll()
        	.antMatchers("/cierre/**").permitAll()
        	.antMatchers("/maestro/plantas").permitAll()
			.antMatchers("/maestro/seriesdocumentobase/planta/*").permitAll()
                .anyRequest().authenticated()
         .and()
         .addFilterBefore(filter,  UsernamePasswordAuthenticationFilter.class)
          .csrf().disable();
         
	      
	    }

	 	@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean()
	            throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
		@Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
		
		
	
	   
}
