package com.farenet.nodo.maestro.api.caja.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDeposito;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDetalle;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaList;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaDepositoRepository;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaDetalleRepository;
import com.farenet.nodo.maestro.api.caja.repository.CierrecajaRepository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.Planta;
import com.farenet.nodo.maestro.api.inspeccion.repository.LineaRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.LineaService;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.Util;

@Service
@Transactional
public class CierrecajaService {

    @Autowired
    private LineaService lineaService;
    
    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private MaestroService maestroService;
    @Autowired
    private CierrecajaRepository cierrecajaRepository;

    @Autowired
    private CierrecajaDetalleRepository cierrecajadetalleRepository;

    @Autowired
    private CierrecajaDepositoRepository cierrecajadepositoRepository;

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private EntityManager entityManager;

    public Cierrecaja save(Cierrecaja cierrecaja) {
        return cierrecajaRepository.saveAndFlush(cierrecaja);
    }

    public Cierrecaja update(Cierrecaja cierrecaja) {
        return cierrecajaRepository.saveAndFlush(cierrecaja);
    }

    public ResultPageBean<Cierrecaja> getAll(PageBean pageBean) {

        ResultPageBean<Cierrecaja> result = new ResultPageBean<Cierrecaja>(
                cierrecajaRepository.findAll(pageBean.convertToPage()));

        return result;
    }
    
    public List<Cierrecaja> getAll(String planta_key) {

    	 Planta planta = maestroService.getPlantaByKey(planta_key);
    	 
    	return   cierrecajaRepository.findByPlanta(planta);

    }

    public double getUltimoSaldoByLinea(Linea linea) {
        Timestamp initDay = Util.initDay();
        Timestamp finDay = Util.finDay();
        List<CierrecajaDetalle> cierrecajasdetalle = cierrecajadetalleRepository.findLastByLineaToday(linea, initDay,
                finDay);
        double montoSaldo = 0.00;
        if (cierrecajasdetalle != null) {
            if (cierrecajasdetalle.size() > 0) {
                montoSaldo = cierrecajasdetalle.get(0).getMontoSaldo();
            }
        }
        return montoSaldo;
    }

    public Cierrecaja getUltimoByPlantaToday(Planta planta) {
        Timestamp initDay = Util.initDay();
        Timestamp finDay = Util.finDay();
        List<Cierrecaja> cierrecajas = cierrecajaRepository.findLastByPlantaToday(planta, initDay, finDay);
        Cierrecaja cierrecaja = null;
        if (cierrecajas != null) {
            if (cierrecajas.size() > 0) {
                cierrecaja = cierrecajas.get(0);
            }
        }
        return cierrecaja;
    }

    public void grabarCierrrecajaYActualizarEstadocierreparcial(Cierrecaja cierrecaja) {
        
    	 	String hqldet = "DELETE FROM CierrecajaDetalle cjd WHERE cjd.cierrecaja.id = :cierrecaja";
         Query query1 = entityManager.createQuery(hqldet);
         query1.setParameter("cierrecaja", cierrecaja.getId());
         query1.executeUpdate();
         
         String hqlSum = "DELETE FROM CierrecajaDeposito cjd WHERE cjd.cierrecaja.id = :cierrecaja";
         Query query = entityManager.createQuery(hqlSum);
         query.setParameter("cierrecaja", cierrecaja.getId());
         query.executeUpdate();

         
	    	 for (CierrecajaDetalle cierrecajaDetalle : cierrecaja.getCierrecajadetalle()) {
	             cierrecajaDetalle.setCierrecaja(cierrecaja);
	         }
	    	 
	    	 if (cierrecaja.getCierrecajadeposito() != null) {
	             for (CierrecajaDeposito cierrecajaDeposito : cierrecaja.getCierrecajadeposito()) {
	                 cierrecajaDeposito.setCierrecaja(cierrecaja);
	             }
	         }

        this.save(cierrecaja);
        
    }

    
    public Cierrecaja getByCierreByPlantakeyLocal(Cierrecaja cierrecaja) {
        List<CierrecajaDetalle> cierrecajaDetalles = new ArrayList<>();
        // el cierre que corresponde
        Cierre cierre = cierrecaja.getCierre();
        List<Linea> lineas = lineaRepository.findByPlanta(cierre.getPlanta());
        for (Linea linea : lineas) {
            CierrecajaDetalle cierrecajaDetalle = new CierrecajaDetalle();

            if (cierre != null) {
                double montoTotal = comprobanteService.getMontoTotalByDateHourByLineaCanceladoLocal(cierrecaja, linea);
                
                //obtenemos los duplicados
                //montoTotal+=comprobanteService.getMontoTotalByDuplicadoLocal(cierrecaja, linea);
                
                //obtener los anulados en la misma fecha
                double anuladosMontoTotal   = comprobanteService.getMontoTotalByDateHourByLineaAnuladoLocal(cierrecaja, linea);

                cierrecajaDetalle.setLinea(linea);
                cierrecajaDetalle.setMontoTotal(montoTotal - anuladosMontoTotal);
            }
            cierrecajaDetalles.add(cierrecajaDetalle);
        }
        cierrecaja.setCierrecajadetalle(cierrecajaDetalles);
        return cierrecaja;
    }
    
    
    public ResultPageBean<CierrecajaList> getCierreCajalist(String planta_key, PageBean pageBean) {
        // Pageable pageable = pageBean.convertToPage();
        //
        // String hqlSum = "SELECT new
        // com.farenet.nodo.maestro.api.caja.domain.CierrecajaList(cj.id,c.nombre,sum(cjdeta.montoDepositoTotal),sum(cjdeta.montoSaldo),sum(cjdeta.montoTotal))"
        // + " FROM Cierrecaja cj" + " inner join cj.cierre c" + " inner join
        // cj.cierrecajadetalle cjdeta"
        // + " group by c.nombre, cj.id";
        // Query query = entityManager.createQuery(hqlSum);
        // query.setFirstResult(pageable.getPageNumber() *
        // pageable.getPageSize());
        // query.setMaxResults(pageable.getPageSize());
        // List<CierrecajaList> lists = query.getResultList();
        //
        // int totalPages = lists.size() / pageable.getPageNumber();
        // long totalElements = lists.size();
        //
        // ResultPageBean<CierrecajaList> result = new
        // ResultPageBean<CierrecajaList>(totalPages, totalElements, lists);

        ResultPageBean<CierrecajaList> result = new ResultPageBean<CierrecajaList>(
                cierrecajaRepository.findList(planta_key, pageBean.convertToPage()));
        return result;
    }
    
    public ResultPageBean<CierrecajaList> getCierreCajalistByfield(String planta_key, String field, PageBean pageBean) throws UnsupportedEncodingException {
    	ResultPageBean<CierrecajaList> result = null;
    	Map<String, String> mapField = QueryUtil.splitQuery(field);
    	
    	Timestamp fechaIni = new Timestamp(new Date(Long.valueOf(Long.valueOf(mapField.get("fechaIni").toString()))).getTime());
    	Timestamp fechaFin = new Timestamp(new Date(Long.valueOf(Long.valueOf(mapField.get("fechaFin").toString()))).getTime());
    	
    	if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null && mapField.get("field") == null) {
    		result = new ResultPageBean<CierrecajaList>(
                    cierrecajaRepository.findListByfechas(planta_key,fechaIni, fechaFin, pageBean.convertToPage()));
    		return result;
    	}
    	
    	if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null && mapField.get("field") != null) {
    		result = new ResultPageBean<CierrecajaList>(
                    cierrecajaRepository.findListByfechasAndNroVoucher(planta_key,fechaIni, fechaFin,"%"+mapField.get("field")+"%", pageBean.convertToPage()));
    		return result;
    	}
    	
    	
    	
        result = new ResultPageBean<CierrecajaList>(
                cierrecajaRepository.findList(planta_key, pageBean.convertToPage()));
        return result;
    }

    public List<Cierrecaja> getNotSendedToOffisis() {
    	
    	//evaluamos los cierre caja deposito que no han sido enviados a ofisis 
         String hql1 = "SELECT cj FROM Cierrecaja cj "
         		+ " inner join cj.cierrecajadeposito ccd where "
         		+ " ccd.sendedtooffisis  = 'false'";
         Query query = entityManager.createQuery(hql1);
         
    	//return cierrecajaRepository.findBySendedtooffisis(false);
         
         return  query.getResultList();
    }
    
    public List<Cierrecaja> getDetalleCierreCajaByDateRangeByPlanta(Date fecha, String planta) {
    	
    	Timestamp initDay = Util.initDay(fecha);
        Timestamp finDay = Util.finDay(fecha);
        
        List<Cierrecaja> resultado = new ArrayList();        
        String hql1 = "SELECT cj FROM Cierrecaja cj"
        		+ " where cj.planta.id = :planta and"
        		+ " cj.fechcreacion >= :fechaMin and cj.fechcreacion <= :fechaMax";
        Query query = entityManager.createQuery(hql1);
        
        query.setParameter("fechaMin", initDay);
        query.setParameter("fechaMax", finDay);
        query.setParameter("planta", planta);
        
        List<Cierrecaja> resultadoParcial = query.getResultList();
        
        return resultadoParcial;
    }
    
    public Cierrecaja getLastCierreCajaByPlanta(String planta_key) {
    	String hql1 = "SELECT cj FROM Cierrecaja cj"
    			+ " inner join cj.planta pl"
        		+ " where pl.key = :planta_key "
        		+ " order by cj.fechcreacion desc";
    	Query query = entityManager.createQuery(hql1);
    	query.setParameter("planta_key", planta_key);
    	query.setFirstResult(0);
    	query.setMaxResults(1);
    
	    	try {
	    		
	    		return (Cierrecaja) query.getSingleResult();
	    		
	    	}catch(NoResultException | NonUniqueResultException e)
	    	{
	    		return null;
	    	}
    
    }
    
}
