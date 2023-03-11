package com.farenet.nodo.maestro.api.seguridad.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.base.AbstractService;
import com.farenet.nodo.maestro.api.caja.domain.Tipodescuento;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.seguridad.domain.BaseUsuario;
import com.farenet.nodo.maestro.api.seguridad.domain.Perfil;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.repository.BaseUsuarioRepository;
import com.farenet.nodo.maestro.api.seguridad.repository.PerfilRepository;
import com.farenet.nodo.maestro.api.seguridad.repository.UsuarioRepository;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class UsuarioService extends AbstractService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TriggerTaskFactory trigger;

	@Autowired
	private BaseUsuarioRepository baseusuarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EntityManager entityManager;

	public void saveUsuario(BaseUsuario baseusuario) {
		BaseUsuario usuariobase = null;
		if (baseusuario.getUser() != null) {
			if(baseusuario.getContrasenha() != null && !baseusuario.getContrasenha().equals("")){
				usuariobase = baseusuario;
				baseusuario.contrasenha = passwordEncoder.encode(usuariobase.contrasenha);
			}else{
				usuariobase = baseusuarioRepository.findOneByUser(baseusuario.getUser());
				baseusuario.contrasenha = usuariobase.contrasenha;
			}
		}
		baseusuarioRepository.save(baseusuario);
	}
	
	public void cambioContrasena(BaseUsuario baseusuario) {
		Usuario usuario = getUsuario(baseusuario.getUser());
		if(baseusuario.getContrasenha() != null && !baseusuario.getContrasenha().equals("")){
			usuario.contrasenha = passwordEncoder.encode(baseusuario.contrasenha);
		}
		usuarioRepository.save(usuario);
	}

	public Usuario getUsuario(String user) {
		return usuarioRepository.findOneByUser(user);
	}

	public BaseUsuario getBaseUsuario(String user) {
		return baseusuarioRepository.findOneByUser(user);
	}

	public Usuario getUser(String user, String contrasenha) {
		Usuario usuario = userRepository.findOneByUser(user);

		if (usuario == null)
			return null;

		if (passwordEncoder.matches(contrasenha, usuario.contrasenha))
			return usuario;

		return null;

	}

	public List<Usuario> getAll() {
		return userRepository.findAll();
	}

	public List<Usuario> getUsuarioIngCertificador(String planta) {

		Perfil perfil = perfilRepository.findByClave("ing_certificador");
		return userRepository.findAllIngenieroAndPlanta(perfil.getClave(), planta);
	}

	public ResultPageBean<BaseUsuario> getAllPaged(PageBean pageBean) {
		ResultPageBean<BaseUsuario> result = new ResultPageBean<BaseUsuario>(
				baseusuarioRepository.findAll(pageBean.convertToPage()));

		return result;
	}

	
	public ResultPageBean<BaseUsuario> get(String field, PageBean pageBean)
			throws UnsupportedEncodingException {
		Map<String, String> mapField = QueryUtil.splitQuery(field);

		String hql = "SELECT bu from BaseUsuario bu inner join bu.persona p where (upper(p.nombres) like upper(:nombre) or upper(p.apellidos) like upper(:nombre))";
		Query query = entityManager.createQuery(hql);

		query.setParameter("nombre", "%" + mapField.get("field") + "%");

		List<BaseUsuario> usuarios = query.getResultList();

		ResultPageBean<BaseUsuario> result = new ResultPageBean<BaseUsuario>(
				usuarios.size() / pageBean.totalFilasPorPagina, usuarios.size(),
				usuarios);

		return result;

	}
	
	public List<Planta> getPlantasByUsuario(String usuario){
		String hql = "SELECT bu.plantas FROM BaseUsuario bu"
				+ " where bu.user = :usuario";
		Query query = entityManager.createQuery(hql);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
}
