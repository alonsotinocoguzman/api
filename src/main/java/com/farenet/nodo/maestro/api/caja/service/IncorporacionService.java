package com.farenet.nodo.maestro.api.caja.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IncorporacionService {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void updateNroIncorporacion(Long nroactualIncorporacion, Long id) {
		String hqlSum = "update SeriedocumentoBase "
				+ "set nroactualIncorporacion = :nroactualIncorporacion WHERE id = :id ";
		Query querySum = entityManager.createQuery(hqlSum);
		querySum.setParameter("nroactualIncorporacion", nroactualIncorporacion);
		querySum.setParameter("id", id);
		querySum.executeUpdate();
	}
}
