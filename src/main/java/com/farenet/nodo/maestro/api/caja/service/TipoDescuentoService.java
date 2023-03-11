package com.farenet.nodo.maestro.api.caja.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farenet.nodo.maestro.api.caja.domain.Tipodescuento;
import com.farenet.nodo.maestro.api.caja.repository.TipodescuentoRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.ResultPageBean;

@Service
@Transactional
public class TipoDescuentoService {

	@Autowired
	private TipodescuentoRepository tipodescuentoRepository;

	public ResultPageBean<Tipodescuento> getAllTipodescuento(PageBean pageBean) {

		ResultPageBean<Tipodescuento> result = new ResultPageBean<Tipodescuento>(
				tipodescuentoRepository.findAll(pageBean.convertToPage()));

		return result;
	}
}
