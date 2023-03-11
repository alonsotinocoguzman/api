package com.farenet.nodo.maestro.api.caja.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.farenet.nodo.maestro.api.caja.domain.bean.PrecioConceptoBean;

@Service
@Transactional

public class ConceptoPrecioService {

    @Autowired
    private EntityManager entityManager;
    public  List<PrecioConceptoBean> ListarConceptoPlanta(String planta) throws Exception {
    	List<PrecioConceptoBean>conceptoinspecciondetalle  = new ArrayList<>();
    		String hql= "select ci.key as key,ci.abreviatura as abreviatura,cid.valor as valor,cid.planta_key as plantakey,cid.id as id from conceptoinspecciondetalle cid "
    				+ " inner join conceptoinspeccion ci on cid.conceptoinspeccion_key = ci.key"
    				+ " where cid.planta_key="+"'"+planta+"'"
    				+ " group by ci.key,ci.nombre,ci.abreviatura,cid.valor,cid.planta_key,cid.id"
    				+ " order by cid.planta_key asc";
    		Query query = entityManager.createNativeQuery(hql.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
				PrecioConceptoBean bean= new PrecioConceptoBean();
                bean.setKey(obj[0] != null ? obj[0].toString():null);
				bean.setAbreviatura(obj[1]!=null? obj[1].toString():null);
				bean.setValor(obj[2]!=null? Double.parseDouble(obj[2].toString()):null);
				bean.setPlanta(obj[3]!=null? obj[3].toString():null);
				bean.setId(obj[4]!=null? Long.parseLong(obj[4].toString()):null);
				conceptoinspecciondetalle.add(bean);
			}
    	return conceptoinspecciondetalle;
    }
    
    public void ActualizarPrecioConcepto(PrecioConceptoBean precio) throws Exception {
    		String hql = " UPDATE conceptoinspecciondetalle SET valor="+precio.getValor()
    				+ " WHERE id="+precio.getId()
    				+ " and planta_key= "+"'"+ precio.getPlanta()+"'"
    				+ " and conceptoinspeccion_key= "+"'"+ precio.getKey()+"'";
    		Query  query = entityManager.createNativeQuery(hql);
    		query.executeUpdate();
    	
    }
    
    public List<PrecioConceptoBean> listarPreciosByConceptoAndPlanta(Long id,String key,String planta)throws Exception{

    	List<PrecioConceptoBean>conceptoinspecciondetalle  = new ArrayList<>();
			String hql="select id as id, valor as valor, conceptoinspeccion_key as conceptokey,planta_key as planta, c.abreviatura as abreviatura from conceptoinspecciondetalle"
					+ " inner join conceptoinspeccion c on conceptoinspecciondetalle.conceptoinspeccion_key = c.key  "
					+ "where id=" + id
					+" and planta_key=" +"'" + planta + "'"
					+" and conceptoinspeccion_key=" + "'" + key + "'";
    		Query query = entityManager.createNativeQuery(hql.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
				PrecioConceptoBean bean= new PrecioConceptoBean();
				bean.setId(obj[0]!=null? Long.parseLong(obj[0].toString()):null);
				bean.setValor(obj[1]!=null? Double.parseDouble(obj[1].toString()):null);
                bean.setKey(obj[2] != null ? obj[2].toString():null);
				bean.setPlanta(obj[3]!=null? obj[3].toString():null);
				bean.setAbreviatura(obj[4]!=null? obj[4].toString():null);
				conceptoinspecciondetalle.add(bean);
				 }
			return conceptoinspecciondetalle; 
			}
    

}
