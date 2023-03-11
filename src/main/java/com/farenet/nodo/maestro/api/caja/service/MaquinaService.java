package com.farenet.nodo.maestro.api.caja.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.bean.MaquinaEditedBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class MaquinaService {

    @Autowired
    private EntityManager entityManager;
	
	
	@Transactional
	public void actualizarDatosMaquina(MaquinaEditedBean bean) throws Exception {
			String hql="UPDATE Maquina SET modelo= :modelo, nombreequipo= :nombreequipo, serie= :serie WHERE id= :id";
        	Query query =  entityManager.createQuery(hql);
        	query.setParameter("modelo", bean.getModelo());
        	query.setParameter("nombreequipo", bean.getNombreequipo());
        	query.setParameter("serie", bean.getSerie());
        	query.setParameter("id", bean.getId());
        	query.executeUpdate();
		
	}
	@Transactional
	public void insertarDatosMaquina(MaquinaEditedBean bean) throws Exception {
			String hql="INSERT INTO Maquina (id,descripcion,modelo,nombreequipo,serie,linea_key,tipomaquina_key) values "
					+"("+bean.getId()
					+ " ,'"+bean.getDescripcion()+"'"
					+" ,'"+bean.getModelo()+"'"
					+" ,'"+bean.getNombreequipo()+"'"
					+" ,'"+bean.getSerie()+"'"
					+" ,'"+bean.getLineaKey()+"'"
					+" ,'"+bean.getTipomaquina()+"'"
					+" )";
			Query query = entityManager.createNativeQuery(hql);
			query.executeUpdate();
	}
	@Transactional
	public List<MaquinaEditedBean> devuelveIdmaquina()throws Exception{
		List<MaquinaEditedBean> maquina = new ArrayList<>();
		
		String hql="select Max(id) as id from Maquina";
		Query query = entityManager.createNativeQuery(hql);
		List<Object> result = (List<Object>) query.getResultList();		
        MaquinaEditedBean bean = new MaquinaEditedBean();
        bean.setId(result.get(0)!=null? Long.parseLong(result.get(0).toString()):null);
        maquina.add(bean);
		return maquina;
	}
	@Transactional
	public List<MaquinaEditedBean> devuelveIdLineaEtapa()throws Exception{
		List<MaquinaEditedBean> maquina = new ArrayList<>();
		
		String hql="select Max(id) as id from linea_etapa";
		Query query = entityManager.createNativeQuery(hql);
		List<Object> result = (List<Object>) query.getResultList();		
        MaquinaEditedBean bean = new MaquinaEditedBean();
        bean.setIdlineaetapa(result.get(0)!=null? Long.parseLong(result.get(0).toString()):null);
        maquina.add(bean);
		return maquina;
	}
	@Transactional
	public void insertarLineaEtapa(MaquinaEditedBean bean) throws Exception {
			String hql="INSERT INTO linea_etapa (id,pc_cod,etapa_key,linea_key) values "
					+"("+bean.getIdlineaetapa()
					+ " ,'"+bean.getPccod()+"'"
					+" ,'"+bean.getEtapakey()+"'"
					+" ,'"+bean.getLineaKey()+"'"
							+ " )";
			Query query = entityManager.createNativeQuery(hql);
			query.executeUpdate();
	}
	@Transactional
	public void insertarLineaEtapaMaquina(MaquinaEditedBean bean) throws Exception {
			String hql="INSERT INTO linea_etapa_maquina (linea_etapa_id,maquinas_id) values "
					+"("+bean.getIdlineaetapa()
					+ " ,"+bean.getId()+")";
			Query query = entityManager.createNativeQuery(hql);
			query.executeUpdate();
	}
}
