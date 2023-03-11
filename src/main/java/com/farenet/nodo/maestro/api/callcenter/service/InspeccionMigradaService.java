package com.farenet.nodo.maestro.api.callcenter.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.callcenter.bean.ClienteCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.ConceptoCAPI;
import com.farenet.nodo.maestro.api.callcenter.bean.SoatCAPI;
import com.farenet.nodo.maestro.api.util.UIUtils;

@Service
@Transactional
public class InspeccionMigradaService {



	@Autowired
    private EntityManager entityManager;
	
	@Cacheable(value = "soat" )
	public SoatCAPI getSoatByNroMotor(String nromotor){
        
		SimpleDateFormat dtf= new SimpleDateFormat(UIUtils.FORMATO_FECHA_PERU);
		
		List<SoatCAPI> resultado;
        String hql="SELECT new "+SoatCAPI.class.getName()
        		+ "(aseg.nombre,veh.fechfintarjetapropiedad,'')"
        		+ " FROM Vehiculo veh"
        		+ " inner join veh.aseguradora aseg"
        		+ " WHERE veh.nromotor = :nromotor "
        		+ " and aseg.nombre is not null"
        		+ " and veh.fechfintarjetapropiedad is not null"; 
        		
        Query query = entityManager.createQuery(hql);
        query.setParameter("nromotor", nromotor);
        resultado = query.getResultList();
        if(resultado.size() != 0){
        	return resultado.get(0);
        }else{
        	return new SoatCAPI("NO TIENE", null, dtf.format(new Date()));
        }
        
	}
	
	@Cacheable(value = "concepto")
	public ConceptoCAPI getConceptoByKey(String idConcepto){
		ConceptoCAPI resultado;
        String hql="SELECT DISTINCT new "+ConceptoCAPI.class.getName()
        		+ "(ci.key,ci.abreviatura)"
        		+ " FROM Conceptoinspeccion ci"
        		+ " WHERE ci.key = :idConcepto"; 
        		
        Query query = entityManager.createQuery(hql);
        query.setParameter("idConcepto", idConcepto);
        resultado = (ConceptoCAPI) query.getSingleResult();
        
        return resultado;
	}
	
	@Cacheable(value = "cliente")
	public ClienteCAPI getClienteByDocumentoident(String idPersona){
        
        ClienteCAPI resultado;
        String hql="SELECT DISTINCT new "+ClienteCAPI.class.getName()
        		+ "(per.nombres,per.apellidos,per.nombrerazonsocial,per.telefono,per.email,"
        		+ "per.nrodocumentoidentidad,tdoc.nombre)"
        		+ " FROM Persona per"
        		+ " inner join per.tipodocumentoidentidad tdoc"
        		+ " WHERE per.nrodocumentoidentidad = :idPersona"; 
        		
        Query query = entityManager.createQuery(hql);
        query.setParameter("idPersona", idPersona);
        resultado = (ClienteCAPI) query.getSingleResult();
        
        return resultado;
	}
}
