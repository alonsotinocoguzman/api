package com.farenet.nodo.maestro.api.task.cron;

import com.farenet.nodo.maestro.api.caja.domain.bean.ComprobanteBean;
import com.farenet.nodo.maestro.api.caja.service.ComprobanteService;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.task.logic.CargaComprobantesSUNAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendCompNotGeneratedTask {
    @Autowired
    public ComprobanteService comprobanteService;
    @Autowired
    public InspeccionService inspeccion;
    @Autowired
    public TriggerTaskFactory trigger;
    @Async
    @Scheduled(fixedDelay=1800000)
    public void execute(){
        List<ComprobanteBean> lstnotgenerated= comprobanteService.getComprobanteNotGenerated();
        trigger.launch("", "comprobante.begin", "CANTIDAD COMPROBANTES ", lstnotgenerated.size());
        for(ComprobanteBean bean: lstnotgenerated){
            try{
                comprobanteService.UpdateComprobanteNotGenerated(bean.getNroinspeccion(), bean.getAutorizacionsunat());
                inspeccion.generarComprobante(bean.getNroinspeccion());
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
