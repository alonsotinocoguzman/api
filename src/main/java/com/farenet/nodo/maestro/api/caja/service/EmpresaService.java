package com.farenet.nodo.maestro.api.caja.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.linea.repository.EmpresaRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EntityManager entityManager;

	public List<Empresa> getAll()
	{
		return empresaRepository.findAll();
	}
	
	public ResultPageBean<Empresa> getAll(PageBean pageBean) {

		ResultPageBean<Empresa> result = new ResultPageBean<Empresa>(
				empresaRepository.findAll(pageBean.convertToPage()));

		return result;
	}

	public Empresa getOne(String key) {
		return empresaRepository.findOneByKey(key);
	}

	public ResultPageBean<Empresa> get(String field, PageBean pageBean) throws UnsupportedEncodingException {
		Map<String, String> mapField = QueryUtil.splitQuery(field);

		String hql = "SELECT emp from Empresa emp where upper(emp.nombre) like upper(:nombre) or upper(emp.key) like upper(:key)"
				+ " or upper(emp.ruc) like upper(:ruc)";
		Query query = entityManager.createQuery(hql);

		query.setParameter("nombre", "%" + mapField.get("field") + "%");
		query.setParameter("key", "%" + mapField.get("field") + "%");
		query.setParameter("ruc", "%" + mapField.get("field") + "%");

		List<Empresa> empresas = query.getResultList();

		ResultPageBean<Empresa> result = new ResultPageBean<Empresa>(
				empresas.size() / pageBean.totalFilasPorPagina, empresas.size(),
				empresas);

		return result;

	}
	
	public String getOneByPlanta(String planta){
		String hql = "SELECT emp.nombre from Planta pl"
				+ " inner join pl.empresa emp"
				+ " where pl.key = :planta";
		Query query = entityManager.createQuery(hql);
		query.setParameter("planta", planta);
		return query.getSingleResult().toString();
	}
}
