package com.farenet.nodo.maestro.api.linea.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.linea.domain.Empresa;
import org.springframework.data.jpa.repository.*;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long>{
	
	public Empresa findOneByKey(String key);
	
	public Empresa findOneByRuc(String ruc);
}
