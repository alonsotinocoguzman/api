package com.farenet.nodo.maestro.api.seguridad.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.farenet.nodo.maestro.api.caja.domain.Persona;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance
@DiscriminatorColumn(name = "USER_TYPE")
@Table(name = "usuario")
public class BaseUsuario {

	public BaseUsuario(BaseUsuario baseusuario) {
		this.user = baseusuario.getUser();
		this.perfil = baseusuario.getPerfil();
		this.USER_TYPE = baseusuario.getUSER_TYPE();
		this.persona = baseusuario.getPersona();
		this.contrasenha = baseusuario.getContrasenha();
		this.firmaCertificador = baseusuario.getFirmaCertificador();
		this.estado = baseusuario.getEstado();
		this.plantas = baseusuario.getPlantas();
		this.foto = baseusuario.getFoto();
	}

	public BaseUsuario() {

	}

	@Id
	@Column(name = "username", nullable = false)
	public String user;

	@Column(length = 65535,columnDefinition = "Text", name = "firmacertificador")
	public String firmaCertificador;

	@Column(length = 100000,columnDefinition = "Text")
	public String foto;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public Boolean estado;

	@ManyToOne
	@JoinColumn(name = "perfil_id")
	public Perfil perfil;

	@Column(insertable = false, updatable = false)
	public String USER_TYPE;

	@Column(name = "contrasenha", nullable = false)
	public String contrasenha;

	@OneToOne(cascade = CascadeType.ALL)
	public Persona persona;
	
	@ManyToMany
	@JoinTable(name = "usuario_planta", joinColumns = @JoinColumn(name = "usuario_username"), inverseJoinColumns = @JoinColumn( name = "plantas_key"))
	@JoinColumn(updatable = false)
	public List<Planta> plantas;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	@JsonIgnore
	public List<GrantedAuthority> getGrantedAuthority() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		if (perfil.clave.equals("admin")) {
			GrantedAuthority auth = new SimpleGrantedAuthority(perfil.clave);
			authList.add(auth);
		} else {

			for (Rol rol : perfil.roles) {
				GrantedAuthority auth = new SimpleGrantedAuthority(rol.clave);
				authList.add(auth);
			}
		}

		return authList;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(String uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getFirmaCertificador() {
		return firmaCertificador;
	}

	public void setFirmaCertificador(String firmaCertificador) {
		this.firmaCertificador = firmaCertificador;
	}

}
