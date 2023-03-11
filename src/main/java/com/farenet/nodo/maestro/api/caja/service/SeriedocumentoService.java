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

import com.farenet.nodo.maestro.api.inspeccion.domain.Seriedocumento;
import com.farenet.nodo.maestro.api.inspeccion.repository.SeriedocumentoRepository;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import com.farenet.nodo.maestro.api.linea.repository.EmpresaRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class SeriedocumentoService {

	@Autowired
	private SeriedocumentoRepository seriedocumentoRepository;

	@Autowired
	private EntityManager entityManager;

	public ResultPageBean<Seriedocumento> getAll(PageBean pageBean) {

		ResultPageBean<Seriedocumento> result = new ResultPageBean<Seriedocumento>(
				seriedocumentoRepository.findAll(pageBean.convertToPage()));

		return result;
	}

	public Seriedocumento getOne(Long id) {
		return seriedocumentoRepository.findOneById(id);
	}

	public ResultPageBean<Seriedocumento> get(String field, PageBean pageBean) throws UnsupportedEncodingException {
		Map<String, String> mapField = QueryUtil.splitQuery(field);

		String criteria = mapField.get("criteria");
		String planta_key = mapField.get("planta_key");
		String linea_key = mapField.get("linea_key");
		String strField = (mapField.get("field") != null) ? mapField.get("field") : "";

		String hql = "SELECT sd from Seriedocumento sd inner join sd.planta pl"
				+ " where upper(pl.nombre) like upper(:planta_nombre)";
		Query query = entityManager.createQuery(hql);

		query.setParameter("planta_nombre", "%" + strField + "%");

		if (criteria != null) {
			if (criteria.equals("findByPlanta")) {
				hql = "SELECT sd from Seriedocumento sd inner join sd.planta pl"
						+ " where upper(pl.key) = upper(:planta_key)";
				query = entityManager.createQuery(hql);
				query.setParameter("planta_key", planta_key);
			}
		}

		if (criteria != null) {
			if (criteria.equals("findByLinea")) {
				hql = "SELECT sd from Seriedocumento sd " + " where upper(sd.linea) like upper(:linea_key)";
				query = entityManager.createQuery(hql);
				query.setParameter("linea_key", "%" + linea_key + "%");
			}
		}

		query.setMaxResults(pageBean.totalFilasPorPagina)
				.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

		List<Seriedocumento> seriedocumentos = query.getResultList();

		ResultPageBean<Seriedocumento> result = new ResultPageBean<Seriedocumento>(
				seriedocumentos.size() / pageBean.totalFilasPorPagina, seriedocumentos.size(), seriedocumentos);

		return result;

	}
}
