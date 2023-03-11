package com.farenet.nodo.maestro.api.seguridad.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Maestro;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.seguridad.domain.BaseUsuario;
import com.farenet.nodo.maestro.api.seguridad.domain.Ejecutivo;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.repository.BaseUsuarioRepository;
import com.farenet.nodo.maestro.api.seguridad.repository.EjecutivoRepository;
import com.farenet.nodo.maestro.api.seguridad.service.UsuarioService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.PageBean.Ordenar;

@RestController
public class UsuarioController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EjecutivoRepository ejecutivoRepository;

	@RequestMapping(value = "/usuario/{username:.+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public BaseUsuario getUsuario(@PathVariable String username) throws Exception {
		return usuarioService.getBaseUsuario(username);

	}

	@RequestMapping(value = "/usuario/ingeniero-certifica/{planta}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Usuario> getUsuarioIngCertificador(@PathVariable String planta) throws Exception {
		return usuarioService.getUsuarioIngCertificador(planta);

	}

	@RequestMapping(value = "/ejecutivos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Ejecutivo> getEjecutivoventas() throws Exception {
		return ejecutivoRepository.findAll();
	}

	@RequestMapping(value = "/usuarios/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<BaseUsuario> getUsuarios(@PathVariable int numPage, @PathVariable int totalFilasPorPagina,
			@PathVariable Ordenar sort, @PathVariable String column) throws Exception {

		return usuarioService.getAllPaged(new PageBean(numPage, totalFilasPorPagina, sort, column));
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveSite(@RequestBody BaseUsuario baseusuario) {
		if (baseusuario.getUSER_TYPE().toLowerCase().equals("user")) {
			Usuario usuario = new Usuario(baseusuario);
			usuarioService.saveUsuario(usuario);
		}
		if (baseusuario.getUSER_TYPE().toLowerCase().equals("ejecutivo")) {
			Ejecutivo ejecutivo = new Ejecutivo(baseusuario);
			usuarioService.saveUsuario(ejecutivo);
		}
		return "ok";
	}
	
	 @RequestMapping(value = "/usuario/save", method = RequestMethod.POST, produces = "application/json; charset=utf-8") 
	  public @ResponseBody String saveUsuario(@RequestBody BaseUsuario baseusuario) { 
	    Usuario usuario = new Usuario(baseusuario); 
	    usuarioService.cambioContrasena(usuario); 
	    return "ok"; 
	  } 
	
	@RequestMapping(value = "/usuario/{field}/{numPage}/{totalFilasPorPagina}/{sort}/{column}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultPageBean<BaseUsuario> getTipodescuentodetalle(@PathVariable String field,
			@PathVariable int numPage, @PathVariable int totalFilasPorPagina, @PathVariable Ordenar sort,
			@PathVariable String column) throws Exception {

		return usuarioService.get(field, new PageBean(numPage, totalFilasPorPagina, sort, column));
	}
	
	@RequestMapping(value = "/usuario/plantas/{usuario}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<Planta> listarPlantasByUsuario(@PathVariable String usuario) {
		return usuarioService.getPlantasByUsuario(usuario);
	}
}
