package com.farenet.nodo.maestro.api.caja.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;

@Service
@Transactional
public class CierreService {

	@Autowired
	private CierreRepository cierreRepository;

	@Autowired
	private CierrecajaService cierrecajaService;

	public Cierre getOneByKey(String key) {
		return cierreRepository.findOneByKey(key);
	}

	
	public List<Cierre> findAllOrderPlanta() {
		return cierreRepository.findAllOrderPlanta();
	}
	
	public List<Cierre> getAll() {
		return cierreRepository.findAll();
	}

	public List<Cierre> getAllCierreOrderByHoraMax(Planta planta) {
		
		List<Cierre> lstCierre =  findAllOrderPlanta();
		List<Cierre> lstOrdenadaCierre =  new ArrayList<>();
		
		for(Cierre c: lstCierre)
		{
			if(c.getPlanta().getKey().equals(planta.getKey()))
			{
				lstOrdenadaCierre.add(c);
			}
		}
		
		
		java.util.Collections.sort(lstOrdenadaCierre, new Comparator<Cierre>() {
			
			 @Override
			    public int compare(Cierre c1, Cierre c2) {
			        return c1.getHoraMax().compareTo(
			                c2.getHoraMax());
			    }
		});
		
		return lstOrdenadaCierre;
	}
	public Cierre getCurrentCierreInCierrecajaByPlantaToday(Planta planta) {
		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(planta);
		Cierre cierre = null;
		if (cierresOrdenados.size() > 0) {
			cierre = cierresOrdenados.get(0);
		}
		Cierrecaja cierrecaja = cierrecajaService.getUltimoByPlantaToday(planta);
		// Como los cierres estan ordenados por horario, en este algoritmo
		// verificamos que cierre pertecene al cierre de caja ya calculado por
		// la linea, si se compara y ambos cierres son iguales, el cierre actual
		// sera el siguiente de lo contrario nulo
		if (cierrecaja != null) {
			for (int i = 0; i < cierresOrdenados.size(); i++) {
				if (cierresOrdenados.get(i) == cierrecaja.getCierre()) {
					if ((i + 1) < cierresOrdenados.size()) {
						cierre = cierresOrdenados.get(i + 1);
					} else {
						cierre = null;
					}
					break;
				}
			}
		}
		return cierre;
	}
}
