package com.farenet.nodo.maestro.api.seguridad.service;

import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.seguridad.JwtTokenUtil;
import com.farenet.nodo.maestro.api.seguridad.bean.UserAuth;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public UserAuth login(String user, String password, String host, String userAgent)
			throws UsernameNotFoundException {
		Usuario userbean = userService.getUser(user, password);
		if (userbean == null)
			throw new UsernameNotFoundException("Usuario/contrasenha no valida");
		
		if (!userbean.getEstado())
			throw new SecurityException("Usuario no existe");

		String tokenStr = jwtTokenUtil.generateToken(userbean);

		UserAuth auth = new UserAuth();
		if (userbean.getPersona() != null) {
			auth.nombre = userbean.getPersona().getNombres();
		} else {
			auth.nombre = "";
		}
		auth.user = userbean.user;
		auth.token = tokenStr;
		auth.perfil = userbean.perfil;
		int i = 0;
		auth.plantas = new String[userbean.plantas.size()];
		for(Planta plantas : userbean.getPlantas()) {
			auth.plantas[i++] = plantas.getKey();
		}
		
		return auth;
	}

	
	public UserAuth loginSede(String user, String password, String host, String userAgent)
			throws UsernameNotFoundException {
		Usuario userbean = userService.getUser(user, password);
		if (userbean == null)
			throw new UsernameNotFoundException("Usuario/contrasenha no valida");
		
		if (!userbean.getEstado())
			throw new SecurityException("Usuario no existe");

		String tokenStr = jwtTokenUtil.generateToken(userbean);

		UserAuth auth = new UserAuth();
		if (userbean.getPersona() != null) {
			auth.nombre = userbean.getPersona().getNombres();
			auth.apellido = userbean.getPersona().getApellidos();
			auth.dni = userbean.getPersona().getNrodocumentoidentidad();
		} else {
			auth.nombre = "";
			auth.apellido = "";
			auth.dni = "";
		}
		auth.user = userbean.user;
		auth.token = tokenStr;
		auth.perfil = userbean.perfil;
		auth.foto = userbean.getFoto();
		int i = 0;
		auth.plantas = new String[userbean.plantas.size()];
		for(Planta plantas : userbean.getPlantas()) {
			auth.plantas[i++] = plantas.getKey();
		}
		
		return auth;
	}

	public String validateUserFromToken(String token) {
		if (!jwtTokenUtil.isTokenExpired(token))
			return jwtTokenUtil.getUsernameFromToken(token);
		else
			return null;

	}

	public String getPlantaFromToken(String token) {
		if (!jwtTokenUtil.isTokenExpired(token))
			return jwtTokenUtil.getPlantaFromToken(token);
		else
			return null;

	}
}
