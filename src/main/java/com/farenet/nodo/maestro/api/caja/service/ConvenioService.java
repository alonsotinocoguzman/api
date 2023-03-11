package com.farenet.nodo.maestro.api.caja.service;

import com.farenet.nodo.maestro.api.caja.domain.Campania;
import com.farenet.nodo.maestro.api.caja.domain.Convenio;
import com.farenet.nodo.maestro.api.caja.domain.Conveniodetalle;
import com.farenet.nodo.maestro.api.caja.domain.Tipodescuento;
import com.farenet.nodo.maestro.api.caja.repository.ConvenioRepository;
import com.farenet.nodo.maestro.api.caja.repository.ConveniodetalleRepository;
import com.farenet.nodo.maestro.api.caja.repository.TipodescuentoRepository;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
@Transactional
public class ConvenioService {

    @Autowired
    private TipodescuentoRepository tipodescuentoRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ConvenioRepository convenioRepository;

    @Autowired
    private ConveniodetalleRepository conveniodetalleRepository;

    public void save(Convenio convenio) {
        Convenio actual = convenioRepository.findOneById(convenio.getId());
        if (actual != null) {
            for (Conveniodetalle conveniodetalle : actual.getConveniodetalles()) {
                conveniodetalleRepository.delete(conveniodetalle);
            }
        }
        convenioRepository.save(convenio);
    }

    public ResultPageBean<Convenio> get(String field, PageBean pageBean)
            throws UnsupportedEncodingException {
        Map<String, String> mapField = QueryUtil.splitQuery(field);
        String value = mapField.get("field");
        String status = mapField.get("status");
        Boolean estado = true;
        ResultPageBean<Convenio> result = null;
        if(!status.equals("todo") && status != null){
        	if(status.equals("false")){
        		estado = false;
        	}
        	if(value!=null){
        		result = new ResultPageBean<Convenio>(
                        convenioRepository.findByLikeNombreOrEmpresaPersonaandEstado("%" + value + "%",estado, pageBean.convertToPage()));
        	}else{
        		result = new ResultPageBean<Convenio>(
                        convenioRepository.findAllByEstado(estado, pageBean.convertToPage()));
        	}
        }else{
        	if(value != null){
        		result = new ResultPageBean<Convenio>(
                        convenioRepository.findByLikeNombreOrEmpresaPersona("%" + value + "%", pageBean.convertToPage()));
        	}else{
        		result = new ResultPageBean<Convenio>(
                        convenioRepository.findAll(pageBean.convertToPage()));
        	}
        }
        return result;

    }
    
    public ResultPageBean<Convenio> get(String field, String estado, PageBean pageBean)
            throws UnsupportedEncodingException {
        Map<String, String> mapField = QueryUtil.splitQuery(field);
        String value = mapField.get("field");
        String status = mapField.get("estado");
        Boolean activo = false;
        ResultPageBean<Convenio> result;
        if(status!=null){
        	if(status.equals("true")){
        		activo = true;
            }
        	result = new ResultPageBean<Convenio>(
                    convenioRepository.findByLikeNombreOrEmpresaPersonaandEstado("%" + value + "%",activo, pageBean.convertToPage()));
        }else{
        	result = new ResultPageBean<Convenio>(
                    convenioRepository.findByLikeNombreOrEmpresaPersona("%" + mapField.get("field") + "%", pageBean.convertToPage()));
        }
        
        
		return result;

    }
}
