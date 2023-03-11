package com.farenet.nodo.maestro.api.inspeccion.repository;

import org.springframework.stereotype.Repository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Carroceria;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


@Repository
public interface TarjetapropiedadRepository extends JpaRepository<Tarjetapropiedad,Long>{

	@Query(
			value = "select t1 from Tarjetapropiedad t1 "
					+ "join t1.propietario p1 "
					+ "where UPPER(p1.nombres) like UPPER(:nombres) order by t1.fechcreacion desc"
		)
	List<Tarjetapropiedad> searchPropietarioByNombre(@Param("nombres") String nombres);
	
	@Query(
			value = "select t1 from Tarjetapropiedad t1 "
					+ "join t1.propietario p1 "
					+ "where UPPER(p1.nrodocumentoidentidad) like UPPER(:nrodocumentoidentidad) order by t1.fechcreacion desc"
		)
	List<Tarjetapropiedad> searchPropietarioByDNI(@Param("nrodocumentoidentidad") String nombres);
}
