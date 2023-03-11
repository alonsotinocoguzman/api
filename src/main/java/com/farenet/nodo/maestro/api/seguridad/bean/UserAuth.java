package com.farenet.nodo.maestro.api.seguridad.bean;

import java.util.ArrayList;
import java.util.List;

import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;

public class UserAuth {


	public String user;
	public String nombre;
	public String apellido;
	public String dni;
	public String token;
	public String foto;
	public Perfil perfil;
	
	public String[] plantas;
}
