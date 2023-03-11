package com.farenet.nodo.maestro.api.inspeccion.service;

import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.repository.LineaRepository;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlantBean;
import com.farenet.nodo.maestro.api.reporte.mobile.domain.bean.PlantBean.Line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LineaService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LineaRepository lineaRepository;

    public Linea getOneByKey(String key) {
        return lineaRepository.findOneByKey(key);
    }

    public List<PlantBean> getPlantas() {

        String hql1 = "SELECT new " + PlantBean.class.getName()
                + "(p.key, p.nombre, l.key, l.nombre)"
                + " FROM Linea l "
                + " INNER JOIN l.planta p ";

        Query query1 = entityManager.createQuery(hql1);

        //noinspection unchecked
        List<PlantBean> list1 = (List<PlantBean>) query1.getResultList();

        List<PlantBean> list2 = list1.stream().collect(Collectors.groupingBy(o -> o.id))
                .values().stream().map(PlantBean::merge).collect(Collectors.toList());

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        list2.forEach(plantBean -> plantBean.lines.forEach(line -> {
        	
        	PlantBean.Line lineBean ;

            String hql2 = "SELECT new " +PlantBean.Line.class.getName()
            		+ "(SUM(t.importetotal), count(ins))"+
                    " FROM Comprobante t"+
                    " INNER JOIN t.inspeccion ins" +
                    " INNER JOIN t.linea l" +
                    " WHERE l.key = :key" +
                    " AND to_date(to_char(t.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') = :today";

            Query query2 = entityManager.createQuery(hql2);
            query2.setParameter("key", line.id);
            query2.setParameter("today", new Timestamp(today.getTimeInMillis()));

            lineBean = (Line) query2.getSingleResult();
            
            line.amount = lineBean.amount;
            line.carsQuantity = lineBean.carsQuantity;

            if (line.amount == null) line.amount = 0.0;
            if (line.carsQuantity == null) line.carsQuantity = 0;

        }));

        return list2;
    }

}
