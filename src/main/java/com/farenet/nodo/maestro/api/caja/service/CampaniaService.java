package com.farenet.nodo.maestro.api.caja.service;

import com.farenet.nodo.maestro.api.caja.domain.*;
import com.farenet.nodo.maestro.api.caja.repository.*;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CampaniaService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CampaniaRepository campaniaRepository;

    @Autowired
    private CampaniadetalleRepository campaniadetalleRepository;

    @Autowired
    private VerificaciondescuentoRepository verificaciondescuentoRepository;
    
    public List<Campania> getCampaniaByPlantaAndDescAndConcepto(String planta_key, String tipodescuento,String conceptoinspeccion, String ruc) {
    	String hql1 = "SELECT cam from Campania cam"
    			+ " inner join cam.tipodescuento td"
    			+ " inner join cam.plantas pl"
    			+ " inner join cam.campaniadetalles cd"
    			+ " inner join cam.conceptoinspeccion ci"
    			+ " left join cam.empresas emp"
    			+ " WHERE pl.key = :planta_key and td.key = :tipodescuento and ci.key = :conceptoinspeccion and "
    			+ " cam.estado = true";
    			if(ruc != null && !ruc.equals("")) {
    				hql1 = hql1 +"and emp.nrodocumentoidentidad = :ruc";
    			}
    			
    	Query query = entityManager.createQuery(hql1);
    	query.setParameter("planta_key", planta_key);
    	query.setParameter("tipodescuento", tipodescuento);
    	query.setParameter("conceptoinspeccion", conceptoinspeccion);
    	if(ruc != null && !ruc.equals("")) {
    		query.setParameter("ruc", ruc);
    	}
    	return query.getResultList();
    }
    
    public List<Campania> getAllCampaniaByPlantaAndDescuento(String planta_key, String tipodescuento) {
    	String hql1 = "SELECT cam from Campania cam"
    			+ " inner join cam.tipodescuento td"
    			+ " inner join cam.plantas pl"
    			+ " WHERE pl.key = :planta_key and td.key = :tipodescuento and cam.estado = true"
    			+ " and cam.empresas is null ";
    	Query query = entityManager.createQuery(hql1);
    	query.setParameter("planta_key", planta_key);
    	query.setParameter("tipodescuento", tipodescuento);
    	return query.getResultList();
    }

    public void save(Campania campania) throws CloneNotSupportedException {
        Campania actual = null;
        if (campania != null && campania.getId() != null) {
            actual = (Campania) campaniaRepository.findOneById(campania.getId()).clone();
            if (actual != null) {
                for (Campaniadetalle campaniadetalle : actual.getCampaniadetalles()) {
                    campaniadetalleRepository.delete(campaniadetalle);
                }
                for (Verificaciondescuento verificaciondescuento : actual.getVerificaciondescuentos()) {
                    verificaciondescuentoRepository.delete(verificaciondescuento);
                }
            }
        }
        campaniaRepository.save(campania);
    }

    public ResultPageBean<Campania> get(String field, Tipodescuento tipodescuento, PageBean pageBean)
            throws UnsupportedEncodingException {
        Map<String, String> mapField = QueryUtil.splitQuery(field);
        String nombre = mapField.get("field");
        String status = mapField.get("status");
        String fecha = mapField.get("fecha");
        Boolean estado = true;
        ResultPageBean<Campania> result = null;
        
        String queryCount = "SELECT count(distinct c) FROM Campania c ";
        String query = "SELECT distinct c FROM Campania c ";
        
        String join = "left join c.empresas p ";
        
        String where = " where 1 = 1 ";
        
        if(!status.equals("todo") && status != null){
        	if(status.equals("false")){
        		estado = false;
        	}
        }
        
        if(tipodescuento != null){
        	where += "and c.tipodescuento = :tipodescuento ";
        }
        
        if(nombre!=null){
        	where += "and (UPPER(c.nombre) like UPPER(:nombre) or UPPER(p.nombres) like UPPER(:nombre) or UPPER(p.apellidos) like UPPER(:nombre) or UPPER(p.nrodocumentoidentidad) like UPPER(:nombre) or UPPER(p.nombrerazonsocial) like UPPER(:nombre)) ";
        }
        
        if(!status.equals("todo") && status != null){
        	where += "and c.estado = :estado "; 
        }
        
        if(mapField.get("fecha")!=null){
        	where += " and :fecha BETWEEN c.fechInicio AND c.fechFin ";
        }
        
        query += join + where;
        queryCount += join + where;
        
        TypedQuery<Long> typedQueryCount = entityManager.createQuery(queryCount, Long.class);
        TypedQuery<Campania> typedQuery = entityManager.createQuery(query, Campania.class);
        if(tipodescuento != null){
        	typedQuery.setParameter("tipodescuento", tipodescuento);
        	typedQueryCount.setParameter("tipodescuento", tipodescuento);
        }
        if(nombre!=null){
        	typedQuery.setParameter("nombre", "%"+nombre+"%");
        	typedQueryCount.setParameter("nombre", "%"+nombre+"%");
        }
        if(!status.equals("todo") && status != null){
        	typedQuery.setParameter("estado", estado);
        	typedQueryCount.setParameter("estado", estado);
        }
        if(mapField.get("fecha")!=null){
        	Date fechaActivo = new Date(new Long(mapField.get("fecha")));
        	typedQuery.setParameter("fecha", fechaActivo);
        	typedQueryCount.setParameter("fecha", fechaActivo);
        }
        
        Long numberRows = typedQueryCount.getSingleResult();

		List lstResult = typedQuery.setMaxResults(pageBean.totalFilasPorPagina)
				.setFirstResult((pageBean.numPag - 1) * pageBean.totalFilasPorPagina).getResultList();

		double before = (double) numberRows / pageBean.totalFilasPorPagina;
		result = new ResultPageBean<Campania>((int) Math.ceil(before), numberRows,
				lstResult);

		return result;
        
        
        /*
        
        if(!status.equals("todo") && status != null){
        	if(status.equals("false")){
        		estado = false;
        	}
        	if(value!=null){
        		return result = new ResultPageBean<Campania>(
                        campaniaRepository.findByLikeNombreOrEmpresaPersonaByTipodescuentoandEstado("%" + mapField.get("field") + "%",estado, tipodescuento, pageBean.convertToPage()));
        	}else{
        		return result = new ResultPageBean<Campania>(
        				campaniaRepository.findByTipodescuentoandEstado(tipodescuento,estado, pageBean.convertToPage()));
        	}
        }else{
        	if(value != null){
        		return result = new ResultPageBean<Campania>(
        				campaniaRepository.findByLikeNombreOrEmpresaPersonaByTipodescuento("%" + mapField.get("field") + "%",tipodescuento, pageBean.convertToPage()));
        	}else{
        		return result = new ResultPageBean<Campania>(
        				campaniaRepository.findByTipodescuento(tipodescuento,pageBean.convertToPage()));
        	}
        }
*/
    }
}
