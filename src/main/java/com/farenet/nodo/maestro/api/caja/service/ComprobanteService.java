package com.farenet.nodo.maestro.api.caja.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.farenet.nodo.maestro.api.caja.domain.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.CierrecajaDetalle;
import com.farenet.nodo.maestro.api.caja.domain.Comprobante;
import com.farenet.nodo.maestro.api.caja.domain.Comprobanteestado;
import com.farenet.nodo.maestro.api.caja.domain.DetalleCierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.Formapago;
import com.farenet.nodo.maestro.api.caja.domain.Tipodocumento;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteRepository;
import com.farenet.nodo.maestro.api.caja.repository.ComprobanteestadoRepository;
import com.farenet.nodo.maestro.api.caja.repository.FormapagoRepository;
import com.farenet.nodo.maestro.api.caja.repository.TipodocumentoRepository;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccionestado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.domain.bean.ComprobanteListBean;
import com.farenet.nodo.maestro.api.inspeccion.repository.InspeccionestadoRepository;
import com.farenet.nodo.maestro.api.inspeccion.service.InspeccionService;
import com.farenet.nodo.maestro.api.sunat.services.ST_Service;
import com.farenet.nodo.maestro.api.task.TriggerTaskFactory;
import com.farenet.nodo.maestro.api.util.PageBean;
import com.farenet.nodo.maestro.api.util.QueryUtil;
import com.farenet.nodo.maestro.api.util.ResultPageBean;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.farenet.nodo.maestro.api.util.Util;
import com.farenet.nodo.maestro.api.caja.domain.bean.ComprobanteOfisisBean;
@Service
@Transactional
public class ComprobanteService {

	@Autowired
	private ComprobanteRepository comprobanteRepository;

	@Autowired
	private ComprobanteestadoRepository comprobanteEstadoRepository;
	
	@Autowired
	private TipodocumentoRepository tipodocumentoRepository;


	@Autowired
	private ST_Service st_Service;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CierreRepository cierreRepository;
	
	@Autowired
	private InspeccionService inspeccionService;

	@Autowired
	private CierreService cierreService;

	@Autowired
	private FormapagoRepository formapagoRepository;

	@Autowired
	private InspeccionestadoRepository inspeccionEstadoRepository;


    @Autowired
    private TriggerTaskFactory trigger;
	public void guardar(Comprobante comprobante) {
		comprobanteRepository.save(comprobante);
	}
	public void update(Comprobante comprobante) {
		comprobanteRepository.saveAndFlush(comprobante);
	}
	public List<Comprobante> listar() {
		return comprobanteRepository.findAllByEstado(true);
	}

	public Comprobante mostrar(String nrocomprobante) {
		return comprobanteRepository.findOneByNrocomprobante(nrocomprobante);
	}
	
	public Comprobante getOneByNrocomprobanteAndPlanta(String nrocomprobante, String planta){
		return comprobanteRepository.findOneByNrocomprobanteByPlanta(nrocomprobante, planta);
	}
	
	public ResultPageBean<ComprobanteListBean> getAllComprobanteList(String planta_key, PageBean pageBean){
		ResultPageBean<ComprobanteListBean> result = new ResultPageBean<ComprobanteListBean>(
				comprobanteRepository.findList(planta_key, pageBean.convertToPage()));
		result.getContent().forEach(bean -> {
			if(!(bean.nrocomprobante != null)) {
				bean.nrocomprobante = bean.nroOt;
			}
		});
		
        return result;
	}
	
	public List<ConsolidacionOrdenBean> getConsolidacionBeanList(Date fechIni, Date fechFin, String ruc, String empresa){
		List<ConsolidacionOrdenBean> result = new ArrayList<>();
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
		Timestamp fechaMax = new Timestamp(fechFin.getTime());
		
		String hql = "SELECT DISTINCT new "+ ConsolidacionOrdenBean.class.getName()
				+ "(ot.id,ot.nroOT,com.placaMotor,cert.nrodocumentoCertificado,cli.nrodocumentoidentidad,pl.nombre,to_char(com.fechapago, 'DD-MM-YYYY'),com.importetotal)"
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join com.ordenTrabajo ot"
				+ " inner join com.linea li"
				+ " inner join li.planta pl"
				+ " inner join ot.comprobanteestado coe"
				+ " left join ins.certificados cert"
				+ " inner join com.empresa emp"
				+ " inner join com.cliente cli"
				+ " where com.importetotal > 0 and coe.key = 'PEN' and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechaMin and :fechaMax"
				+ " and cli.nrodocumentoidentidad = :ruc and emp.key = :empresa order by ot.nroOT asc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("fechaMin", fechaMin);
		query.setParameter("fechaMax", fechaMax);
		query.setParameter("ruc", ruc);
		query.setParameter("empresa", empresa);
		result = query.getResultList();
		
		return result;
	}
	
	public List<Comprobante> getAllComprobanteByIds(List<Long> ids){
		String hql = "SELECT DISTINCT com FROM Comprobante com"
				+ " inner join com.ordenTrabajo ot"
				+ " where ot.id in (:ids)";
		Query query = entityManager.createQuery(hql);
		query.setParameter("ids", ids);
		return query.getResultList();
	}

	
	public List<ReciboSunatBean> getRecibosSunat(Date fechIni,Date fechFin, String planta, String formaPago,String ruc){
		  Timestamp fechaMin = new Timestamp(fechIni.getTime());
	        Timestamp fechaMax = new Timestamp(fechFin.getTime());

			Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");   
	        Tipodocumento tipoDocumentoBoleta = tipodocumentoRepository.findById("4").orElse(null);
	        Tipodocumento tipoDocumentoFactura = tipodocumentoRepository.findById("5").orElse(null);
		
		String hql1 = "SELECT DISTINCT new "+ ReciboSunatBean.class.getName()
				+ "(coem.nombre,com.nrocomprobante,com.placaMotor,"
				+ " CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,tidoc.nombre,message.pdf,emp.ruc,tipodocu.sunatTipo,com.fechapago,com.importetotal)"
				+ "FROM Comprobante com,ST_Mensaje message "
				+ " inner join com.empresa coem" 
				+ " left join com.tipodescuento tde"
				+ " inner join com.tipodocumento tipodocu"
				+ " inner join com.conceptoInspeccion coin"
				+ " inner join coin.conceptoinspecciondetalles codes"
				+ " inner join com.linea li"
				+ " left join com.cliente cli"
				+ " left join cli.departamento dep"
				+ " left join cli.distrito dis"
				+ " left join cli.tipodocumentoidentidad tidoc"
				+ " left join com.inspeccion ins"
				+ " left join ins.usuariocaja usca"
				+ " left join usca.persona perca"
				+ " left join ins.vehiculo v"
				+ " left join v.marca ma"
				+ " left join v.modelo mo"
				+ " left join v.vehiculoclase vc"
              + " inner join li.planta pl"
              + " inner join pl.empresa emp";
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
			hql1 += " inner join com.formaPago cfp ";
		}
		
		//where
		hql1 +=  " WHERE message.nrodocumentoinspeccion = ins.nrodocumentoinspeccion and message.nrocomprobante = com.nrocomprobante and  com.comprobanteestado is not null and com.nrocomprobante is not null  and com.comprobanteestado != :comprobanteestadoanulado"
				+ " and (com.tipodocumento = :tipoDocumentoBoleta or  com.tipodocumento = :tipoDocumentoFactura) and message.pdf is not null ";
		
		if(fechaMin !=null && fechaMax!=null) {
			hql1 += " and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax";
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			hql1 += " and cli.nrodocumentoidentidad = :ruc";
		}
		
		if(! (planta.equals("todos")  || planta.equals("") || planta.equals("null")) ) {
			hql1 += " and pl.key = :planta_key";
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
			hql1 += " and cfp.key = :forma_pago ";
		}
				
		
		//invocar la query
		Query query = entityManager.createQuery(hql1);
		
		query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
		query.setParameter("tipoDocumentoBoleta", tipoDocumentoBoleta);
		query.setParameter("tipoDocumentoFactura", tipoDocumentoFactura);
		
		if(fechaMin !=null && fechaMax!=null) {
			query.setParameter("fechapagoMin", fechaMin);
	        query.setParameter("fechapagoMax", fechaMax);
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			query.setParameter("ruc", ruc);
		}
		
		if(! (planta.equals("todos")|| planta.equals("")  || planta.equals("null")) ) {
			query.setParameter("planta_key", planta);
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("")  || formaPago.equals("null")) ) {
			query.setParameter("forma_pago", formaPago);
		}
		
		List<ReciboSunatBean> list = query.getResultList();
		list.forEach(bean -> {
			if(bean.getPdf() == null || bean.getPdf().isEmpty()) {
				bean.setPdf(st_Service.getPdf(bean.getRucEmp(), bean.getNrocomprobante(), bean.getTipoDocumento(), bean.getFecha(), bean.getMonto(), false));
			}
		});
		
		return  list;
	}


	
	public ResultPageBean<ComprobanteListBean> getAllComprobanteListAndField(String planta_key,String field, PageBean pageBean) throws UnsupportedEncodingException{
		ResultPageBean<ComprobanteListBean> result ;
		Map<String, String> mapField = QueryUtil.splitQuery(field);
		Timestamp fechaIni = null, fechaFin = null;
		if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null) {
			fechaIni = new Timestamp(new Date(Long.valueOf(Long.valueOf(mapField.get("fechaIni").toString()))).getTime());
	    	fechaFin = new Timestamp(new Date(Long.valueOf(Long.valueOf(mapField.get("fechaFin").toString()))).getTime());
		}
		
		if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null && mapField.get("field") == null
    			&& mapField.get("tipodescuento") == null) {
    		result = new ResultPageBean<ComprobanteListBean>(
    				comprobanteRepository.findListByfecha(planta_key,fechaIni, fechaFin,pageBean.convertToPage()));
    		result.getContent().forEach(bean -> {
    			if(!(bean.nrocomprobante != null)) {
    				bean.nrocomprobante = bean.nroOt;
    			}
    		});
    		return result;
    	}
    	
    	if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null && mapField.get("field") == null
    			&& mapField.get("tipodescuento") != null) {
    		result = new ResultPageBean<ComprobanteListBean>(
    				comprobanteRepository.findListByfechaAndDescuento(planta_key,fechaIni, fechaFin,mapField.get("tipodescuento"), pageBean.convertToPage()));
    		result.getContent().forEach(bean -> {
    			if(!(bean.nrocomprobante != null)) {
    				bean.nrocomprobante = bean.nroOt;
    			}
    		});
    		return result;
    	}
    	
    	if (mapField.get("fechaIni") != null && mapField.get("fechaFin") != null && mapField.get("field") != null) {
    		result = new ResultPageBean<ComprobanteListBean>(
    				comprobanteRepository.findListByfechaByplaca(planta_key,fechaIni, fechaFin,"%"+mapField.get("field")+"%", pageBean.convertToPage()));
    		result.getContent().forEach(bean -> {
    			if(!(bean.nrocomprobante != null)) {
    				bean.nrocomprobante = bean.nroOt;
    			}
    		});
    		return result;
    	}
    	
    	if (mapField.get("fechaIni") == null && mapField.get("fechaFin") == null && mapField.get("field") != null) {
    		result = new ResultPageBean<ComprobanteListBean>(
    				comprobanteRepository.findListByplaca(planta_key,"%"+mapField.get("field")+"%", pageBean.convertToPage()));
    		result.getContent().forEach(bean -> {
    			if(!(bean.nrocomprobante != null)) {
    				bean.nrocomprobante = bean.nroOt;
    			}
    		});
    		return result;
    	}
    	
		result = new ResultPageBean<ComprobanteListBean>(
				comprobanteRepository.findList(planta_key, pageBean.convertToPage()));
		result.getContent().forEach(bean -> {
			if(!(bean.nrocomprobante != null)) {
				bean.nrocomprobante = bean.nroOt;
			}
		});
        return result;
	}

	
    public Double getMontoTotalByDateHourByLineaCanceladoLocal(Cierrecaja cierrecaja, Linea linea) {
        Cierre cierre = cierrecaja.getCierre();
        String log = "CANCELADO cierre : " + cierrecaja.getCierre().getNombre() + " - Planta "+cierrecaja.getPlanta().getNombre()+ " - ";
        Comprobanteestado comprobanteestado = (Comprobanteestado) comprobanteEstadoRepository
                .findOneByKey("CAN");

        Formapago formapago = (Formapago) formapagoRepository.findOneByKey("contado");

        List<Cierre> cierresOrdenados = cierreService.getAllCierreOrderByHoraMax(linea.getPlanta());

        Date fechaMax = new Date(UIUtils.getFechMaxCierre(cierre, cierrecaja.getFechcreacion()).getTime());
        Date fechaMin = new Date(
                UIUtils.getFechMinCierre(cierresOrdenados, cierre, cierrecaja.getFechcreacion()).getTime());

        Double importetotal = inspeccionService.getAllSumByDate(fechaMin, fechaMax,cierre.getPlanta().getKey(), linea.getKey());
       /* List<Inspeccion> inspecciones = inspeccionService.getAllByDate(fechaMin, fechaMax,cierre.getPlanta().getKey());
        double importetotal = 0;
        if(inspecciones != null && inspecciones.size()>0)
        for (Inspeccion inspeccion : inspecciones) {
        	
        	for(Comprobante comprobante : inspeccion.getComprobantes())
        	{
        		
        		if(comprobante.getTipodescuento() != null) {
        			if(comprobante.getTipodescuento().getKey().equals("corte") ) {
        				continue;
        			}
        		}
        		if(comprobante.getNrocomprobante() != null && comprobante.getComprobanteestado()!=null && !comprobante.getComprobanteestado().getKey().equals("ANU") 
        				&& (comprobante.getFechapago().after(fechaMin) && comprobante.getFechapago().before(fechaMax))
        				&& (comprobante.getDuplicado()==null  || !comprobante.getDuplicado()))
        		{
        			if (comprobante.getLinea() != null) {
                        if (comprobante.getFormaPago().getKey().equals(formapago.getKey()) && comprobante.getTipoContado() != null && comprobante.getTipoContado().getKey().equals("efectivo")
                                && comprobante.getComprobanteestado().getKey().equals(comprobanteestado.getKey())
                                && comprobante.getLinea() != null && comprobante.getLinea().getKey().equals(linea.getKey())) {
                        	log += comprobante.getNrocomprobante() + " - " ;
                            importetotal += comprobante.getImportetotal();
                        }
                    }
        		}
        	}
        	
        }*/

        trigger.launch("", "nodo.sede.test.validar.cierrecaja", log,"");
	    
        return importetotal;
    }
    
    
    public double getMontoTotalByDuplicadoLocal(Cierrecaja cierrecaja, Linea linea) {
        Cierre cierre = cierrecaja.getCierre();
        String log = "DUPLICADO cierre : " + cierrecaja.getCierre().getNombre() + " - Planta "+cierrecaja.getPlanta().getNombre()+ " - ";
        Comprobanteestado comprobanteestado = (Comprobanteestado) comprobanteEstadoRepository
                .findOneByKey("CAN");

        Formapago formapago = (Formapago) formapagoRepository.findOneByKey("contado");

        List<Cierre> cierresOrdenados = cierreService.getAllCierreOrderByHoraMax(linea.getPlanta());

        Date fechaMax = new Date(UIUtils.getFechMaxCierre(cierre, cierrecaja.getFechcreacion()).getTime());
        Date fechaMin = new Date(
                UIUtils.getFechMinCierre(cierresOrdenados, cierre, cierrecaja.getFechcreacion()).getTime());

        Double importetotal = inspeccionService.getAllSumDuplicado(fechaMin, fechaMax,cierre.getPlanta().getKey(),linea.getKey());
        /*List<Inspeccion> inspecciones = inspeccionService.getAllDuplicado(fechaMin, fechaMax,cierre.getPlanta().getKey());
        double importetotal = 0;
        for (Inspeccion inspeccion : inspecciones) {
        	
        	for(Comprobante comprobante : inspeccion.getComprobantes())
        	{
        		
        		if(comprobante.getTipodescuento() != null) {
        			if(comprobante.getTipodescuento().getKey().equals("corte") ) {
        				continue;
        			}
        		}
        		
        		if(comprobante.getNrocomprobante() != null && comprobante.getComprobanteestado()!=null && !comprobante.getComprobanteestado().getKey().equals("ANU") 
        				&& (comprobante.getFechapago().after(fechaMin) && comprobante.getFechapago().before(fechaMax))
        				&& (comprobante.getDuplicado()!=null && comprobante.getDuplicado()))
        		{
        			if (comprobante.getLinea() != null) {
                        if (comprobante.getFormaPago().getKey().equals(formapago.getKey()) && comprobante.getTipoContado() != null && comprobante.getTipoContado().getKey().equals("efectivo")
                                && comprobante.getComprobanteestado().getKey().equals(comprobanteestado.getKey())
                                && comprobante.getLinea() != null && comprobante.getLinea().getKey().equals(linea.getKey())) {
                        	log += comprobante.getNrocomprobante() + " - ";
                            importetotal += comprobante.getImportetotal();
                        }
                    }
        		}
        	}
        	
        }*/
        trigger.launch("", "nodo.sede.test.validar.cierrecaja", log,"");
        return importetotal;
    }
    
    public double getMontoTotalByDateHourByLineaAnuladoLocal(Cierrecaja cierrecaja, Linea linea) {
        Cierre cierre = cierrecaja.getCierre();
        Comprobanteestado comprobanteestado = (Comprobanteestado) comprobanteEstadoRepository
                .findOneByKey("ANU");
        String log = "ANULADO cierre : " + cierrecaja.getCierre().getNombre() + " - Planta "+cierrecaja.getPlanta().getNombre()+ " - ";
        Formapago formapago = (Formapago) formapagoRepository.findOneByKey("contado");

        List<Cierre> cierresOrdenados = cierreService.getAllCierreOrderByHoraMax(linea.getPlanta());

        Date fechaMax = new Date(UIUtils.getFechMaxCierre(cierre, cierrecaja.getFechcreacion()).getTime());
        Date fechaMin = new Date(
                UIUtils.getFechMinCierre(cierresOrdenados, cierre, cierrecaja.getFechcreacion()).getTime());

        Double importetotal = inspeccionService.getSumByFormapagoByAnuladoByLinea(fechaMin, fechaMax, cierre.getPlanta().getKey(),linea.getKey());
       /* List<Inspeccion> inspecciones = inspeccionService.getByFormapagoByAnuladoByLinea(fechaMin, fechaMax, cierre.getPlanta().getKey());
        double importetotal = 0;
        for (Inspeccion inspeccion : inspecciones) {
        	
        	for(Comprobante comprobante : inspeccion.getComprobantes())
        	{
        		
        		if(comprobante.getTipodescuento() != null) {
        			if(comprobante.getTipodescuento().getKey().equals("corte") ) {
        				continue;
        			}
        		}
        		
        		if(comprobante.getNrocomprobante() != null && comprobante.getComprobanteestado()!=null && comprobante.getComprobanteestado().getKey().equals("ANU")
        				&& (comprobante.getFechanulacion().after(fechaMin) && comprobante.getFechanulacion().before(fechaMax))
        				&& comprobante.getFechapago().before(fechaMin) )
        		{
        			if (comprobante.getLinea() != null) {
                        if (comprobante.getFormaPago().getKey().equals(formapago.getKey()) && comprobante.getTipoContado() != null && comprobante.getTipoContado().getKey().equals("efectivo")
                                && comprobante.getComprobanteestado().getKey().equals(comprobanteestado.getKey())
                                && comprobante.getLinea() != null && comprobante.getLinea().getKey().equals(linea.getKey())) {
                        	log += comprobante.getNrocomprobante() +" - ";
                            importetotal += comprobante.getTotalsindscto()-comprobante.getTotaldscto();
                            break;
                        }
                    }
        		}
        	}
            
        }*/
        trigger.launch("", "nodo.sede.test.validar.cierrecaja", log,"");
        return importetotal;
    }
    
	public List<DetalleCierrecaja> getDetallesMontosByLinea(Cierre cierre, Linea linea) {
		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierre.getPlanta());

		Timestamp fechaMax = Util.getFechMaxCierre(cierre);
		Timestamp fechaMin = Util.getFechMinCierre(cierresOrdenados, cierre);

		Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
		Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

		String hqlSum = "SELECT new com.farenet.nodo.maestro.api.caja.domain.DetalleCierrecaja(fp.key , sum(com.importetotal)) "
				+ " FROM Inspeccion ins" + " inner join ins.comprobante com" + " inner join com.formaPago fp"
				+ " WHERE :fechapagoMin < com.fechapago and com.fechapago < :fechapagoMax"
				+ " and com.linea = :linea and com.comprobanteestado != :comprobanteestadoanulado and ins.inspeccionestado != :inspeccionestadoanulado"
				+ " group by fp.key order by fp.key";
		Query query = entityManager.createQuery(hqlSum);

		query.setParameter("fechapagoMin", fechaMin);
		query.setParameter("fechapagoMax", fechaMax);
		query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
		query.setParameter("inspeccionestadoanulado", inspeccionestadoAnulado);
		query.setParameter("linea", linea);

		List<DetalleCierrecaja> montos = query.getResultList();
		return montos;
	}

	public List<DetalleCierrecaja> getDetallesMontosByCierrecajadetalle(CierrecajaDetalle cierrecajaDetalle) {
		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierrecajaDetalle.getCierrecaja().getCierre().getPlanta());
		Linea linea = cierrecajaDetalle.getLinea();

		Timestamp fechaMax = Util.getFechMaxCierre(cierrecajaDetalle.getCierrecaja().getCierre(),
				cierrecajaDetalle.getFechcreacion());
		Timestamp fechaMin = Util.getFechMinCierre(cierresOrdenados, cierrecajaDetalle.getCierrecaja().getCierre(),
				cierrecajaDetalle.getCierrecaja().getFechcreacion());
		Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");
		Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

		String hqlSum = "SELECT new com.farenet.nodo.maestro.api.caja.domain.DetalleCierrecaja(fp.key , sum(com.importetotal)) "
				+ " FROM Inspeccion ins" + " inner join ins.comprobante com" + " inner join com.formaPago fp"
				+ " WHERE :fechapagoMin < com.fechapago and com.fechapago < :fechapagoMax" + " and com.linea = :linea"
				+ " and com.comprobanteestado != :comprobanteestadoanulado and ins.inspeccionestado != :inspeccionestadoanulado"
				+ " group by fp.key order by fp.key";
		Query query = entityManager.createQuery(hqlSum);

		query.setParameter("fechapagoMin", fechaMin);
		query.setParameter("fechapagoMax", fechaMax);
		query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
		query.setParameter("inspeccionestadoanulado", inspeccionestadoAnulado);
		query.setParameter("linea", linea);

		List<DetalleCierrecaja> montos = query.getResultList();
		return montos;
	}

	public double getMontoTotalByDateHourByLineaCancelado(Cierre cierre, Linea linea) {
		Comprobanteestado comprobanteestado = comprobanteEstadoRepository.findOneByKey("CAN");
		Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

		Formapago formapago = formapagoRepository.findOneByKey("contado");
		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierre.getPlanta());

		Timestamp fechaMax = Util.getFechMaxCierre(cierre);
		Timestamp fechaMin = Util.getFechMinCierre(cierresOrdenados, cierre);

		String hqlSum = "SELECT sum(com.importetotal) " + " FROM Inspeccion ins" + " inner join ins.comprobante com"
				+ " WHERE :fechapagoMin < com.fechapago and com.fechapago < :fechapagoMax"
				+ " and com.comprobanteestado = :comprobanteestado and ins.inspeccionestado != :inspeccionestadoanulado"
				+ " and com.linea = :linea and com.formaPago = :formapago";
		TypedQuery<Double> querySum = entityManager.createQuery(hqlSum, Double.class);
		querySum.setParameter("fechapagoMin", fechaMin);
		querySum.setParameter("fechapagoMax", fechaMax);
		querySum.setParameter("comprobanteestado", comprobanteestado);
		querySum.setParameter("inspeccionestadoanulado", inspeccionestadoAnulado);
		querySum.setParameter("linea", linea);
		querySum.setParameter("formapago", formapago);
		Double resultado = querySum.getSingleResult();
		return (resultado != null) ? resultado : 0.00;
	}

	public double getMontoTotalByDateHourByLineaPendiete(Cierre cierre, Linea linea) {
		Comprobanteestado comprobanteestado = comprobanteEstadoRepository.findOneByKey("PEN");
		Inspeccionestado inspeccionestadoAnulado = inspeccionEstadoRepository.findOneByKey("ANU");

		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierre.getPlanta());

		Timestamp fechaMax = Util.getFechMaxCierre(cierre);
		Timestamp fechaMin = Util.getFechMinCierre(cierresOrdenados, cierre);

		String hqlSum = "SELECT sum(com.importetotal) " + " FROM Inspeccion ins" + " inner join ins.comprobante com"
				+ " WHERE :fechapagoMin < com.fechapago and com.fechapago < :fechapagoMax"
				+ " and com.comprobanteestado = :comprobanteestado and ins.inspeccionestado != :inspeccionestadoanulado"
				+ " and com.linea = :linea";
		TypedQuery<Double> querySum = entityManager.createQuery(hqlSum, Double.class);
		querySum.setParameter("fechapagoMin", fechaMin);
		querySum.setParameter("fechapagoMax", fechaMax);
		querySum.setParameter("comprobanteestado", comprobanteestado);
		querySum.setParameter("inspeccionestadoanulado", inspeccionestadoAnulado);
		querySum.setParameter("linea", linea);
		Double resultado = querySum.getSingleResult();
		return (resultado != null) ? resultado : 0.00;
	}
	
	public List<String> getAllComprobante (String placa){
		String hql1 = "SELECT com.nrocomprobante"
				+ " FROM Comprobante com"
				+ " WHERE com.nrocomprobante is not null and com.placaMotor = :placa";
		
		Query query = entityManager.createQuery(hql1);
        query.setParameter("placa", placa);
		return query.getResultList();
	}
	
	public String getComprobanteInspeccion (String nroComprobante, String empresa){
		
		try{
			 
			String hql1 = "SELECT ins.nrodocumentoinspeccion "
				+ " FROM Comprobante com"
				+ " inner join com.inspeccion ins"
				+ " inner join com.empresa emp"
				+ " WHERE com.nrocomprobante = :nrocomprobante and emp.ruc = :empresa";
			
			Query query = entityManager.createQuery(hql1);
			query.setParameter("nrocomprobante", nroComprobante);
			query.setParameter("empresa", empresa);
			
            return (String)query.getSingleResult();	
            
        }catch(NoResultException e)
        {
        	return null;
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
        
	}
	
	public Boolean getOfisisValue (Long idComprobante){
		String hql1 = "SELECT com.sendedtooffisis "
				+ " FROM Comprobante com"
				+ " WHERE com.id = :idComprobante";
		
		Query query = entityManager.createQuery(hql1);
        query.setParameter("idComprobante", idComprobante);
        
        try{
            return (Boolean)query.getSingleResult();	
            
        }catch(NoResultException e)
        {
        	return null;
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
        
	}
	
	public ReciboBean getoneRecibo(String nrocomprobante){

		String hql1 = "SELECT DISTINCT new "+ ReciboBean.class.getName()
				+ "(pl.telefono,com.autorizacionsunat,coem.nombre,com.nrocomprobante,CONCAT(com.fechapago,''),"
				+ "tde.key,coin.nombre,com.placaMotor,v.nromotor,ma.nombre,mo.nombre,vc.nombre,"
				+ "CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,cli.direccion,dep.nombre,"
				+ "dis.nombre,cli.telefono,cli.nrodocumentoidentidad,tidoc.nombre,com.importetotal,"
				+ "com.baseimponible,com.igv,CONCAT(perca.nombres,' ',perca.apellidos),emp.direccion,"
				+ "pl.direccion,ins.nrodocumentoInforme,emp.ruc,com.totalsindscto)"
				+ "FROM Comprobante com"
				+ " inner join com.empresa coem" 
				+ " left join com.tipodescuento tde"
				+ " inner join com.conceptoInspeccion coin"
				+ " inner join coin.conceptoinspecciondetalles codes"
				+ " inner join com.linea li"
				+ " left join com.cliente cli"
				+ " left join cli.departamento dep"
				+ " left join cli.distrito dis"
				+ " left join cli.tipodocumentoidentidad tidoc"
				+ " left join com.inspeccion ins"
				+ " left join ins.usuariocaja usca"
				+ " left join usca.persona perca"
				+ " left join ins.vehiculo v"
				+ " left join v.marca ma"
				+ " left join v.modelo mo"
				+ " left join v.vehiculoclase vc"
                + " inner join li.planta pl"
                + " inner join pl.empresa emp"
				+ " WHERE com.nrocomprobante = :nrocomprobante";
		Query query = entityManager.createQuery(hql1);
        query.setParameter("nrocomprobante", nrocomprobante);
		return (ReciboBean) query.getSingleResult();
	}
	
	public ReciboBean getoneRecibo(String nrocomprobante,String placa){

		String hql1 = "SELECT DISTINCT new "+ ReciboBean.class.getName()
				+ "(pl.telefono,com.autorizacionsunat,coem.nombre,com.nrocomprobante,CONCAT(com.fechapago,''),"
				+ "tde.key,coin.nombre,com.placaMotor,v.nromotor,ma.nombre,mo.nombre,vc.nombre,"
				+ "CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,cli.direccion,dep.nombre,"
				+ "dis.nombre,cli.telefono,cli.nrodocumentoidentidad,tidoc.nombre,com.importetotal,"
				+ "com.baseimponible,com.igv,CONCAT(perca.nombres,' ',perca.apellidos),emp.direccion,"
				+ "pl.direccion,ins.nrodocumentoInforme,emp.ruc,com.totalsindscto)"
				+ "FROM Comprobante com"
				+ " inner join com.empresa coem" 
				+ " left join com.tipodescuento tde"
				+ " inner join com.conceptoInspeccion coin"
				+ " inner join coin.conceptoinspecciondetalles codes"
				+ " inner join com.linea li"
				+ " left join com.cliente cli"
				+ " left join cli.departamento dep"
				+ " left join cli.distrito dis"
				+ " left join cli.tipodocumentoidentidad tidoc"
				+ " left join com.inspeccion ins"
				+ " left join ins.usuariocaja usca"
				+ " left join usca.persona perca"
				+ " left join ins.vehiculo v"
				+ " left join v.marca ma"
				+ " left join v.modelo mo"
				+ " left join v.vehiculoclase vc"
                + " inner join li.planta pl"
                + " inner join pl.empresa emp"
				+ " WHERE com.nrocomprobante = :nrocomprobante and com.placaMotor = :placa ";
		Query query = entityManager.createQuery(hql1);
        query.setParameter("nrocomprobante", nrocomprobante);
        query.setParameter("placa", placa);
		return (ReciboBean) query.getSingleResult();
	}
	
	public List<ReciboBean> getRecibos(Date fechIni,Date fechFin, String planta, String formaPago,String ruc){
		
		  Timestamp fechaMin = new Timestamp(fechIni.getTime());
	        Timestamp fechaMax = new Timestamp(fechFin.getTime());

			Comprobanteestado comprobanteestadoAnulado = comprobanteEstadoRepository.findOneByKey("ANU");   
	        Tipodocumento tipoDocumentoManual = tipodocumentoRepository.getOne("2");
		
		String hql1 = "SELECT DISTINCT new "+ ReciboBean.class.getName()
				+ "(pl.telefono,com.autorizacionsunat,coem.nombre,com.nrocomprobante,CONCAT(com.fechapago,''),"
				+ "tde.key,coin.nombre,com.placaMotor,v.nromotor,ma.nombre,mo.nombre,vc.nombre,"
				+ "CONCAT(cli.nombres,' ',cli.apellidos),cli.nombrerazonsocial,cli.direccion,dep.nombre,"
				+ "dis.nombre,cli.telefono,cli.nrodocumentoidentidad,tidoc.nombre,com.importetotal,"
				+ "com.baseimponible,com.igv,CONCAT(perca.nombres,' ',perca.apellidos),emp.direccion,"
				+ "pl.direccion,ins.nrodocumentoInforme,emp.ruc,com.totalsindscto)"
				+ "FROM Comprobante com"
				+ " inner join com.empresa coem" 
				+ " left join com.tipodescuento tde"
				+ " inner join com.conceptoInspeccion coin"
				+ " inner join coin.conceptoinspecciondetalles codes"
				+ " inner join com.linea li"
				+ " left join com.cliente cli"
				+ " left join cli.departamento dep"
				+ " left join cli.distrito dis"
				+ " left join cli.tipodocumentoidentidad tidoc"
				+ " left join com.inspeccion ins"
				+ " left join ins.usuariocaja usca"
				+ " left join usca.persona perca"
				+ " left join ins.vehiculo v"
				+ " left join v.marca ma"
				+ " left join v.modelo mo"
				+ " left join v.vehiculoclase vc"
                + " inner join li.planta pl"
                + " inner join pl.empresa emp";
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
			hql1 += " inner join com.formaPago cfp ";
		}
		
		//where
		hql1 +=  " WHERE  com.comprobanteestado is not null and com.nrocomprobante is not null  and com.comprobanteestado != :comprobanteestadoanulado"
				+ " and com.tipodocumento = :tipoDocumentoManual ";
		
		if(fechaMin !=null && fechaMax!=null) {
			hql1 += " and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechapagoMin and :fechapagoMax";
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			hql1 += " and cli.nrodocumentoidentidad = :ruc";
		}
		
		if(! (planta.equals("todos")  || planta.equals("") || planta.equals("null")) ) {
			hql1 += " and pl.key = :planta_key";
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("") || formaPago.equals("null")) ) {
			hql1 += " and cfp.key = :forma_pago ";
		}
				
		
		//invocar la query
		Query query = entityManager.createQuery(hql1);
		
		query.setParameter("comprobanteestadoanulado", comprobanteestadoAnulado);
		query.setParameter("tipoDocumentoManual", tipoDocumentoManual);
		
		if(fechaMin !=null && fechaMax!=null) {
			query.setParameter("fechapagoMin", fechaMin);
	        query.setParameter("fechapagoMax", fechaMax);
		}
		
		if( !(ruc.equals("") || ruc.equals("null")) ) {
			query.setParameter("ruc", ruc);
		}
		
		if(! (planta.equals("todos")|| planta.equals("")  || planta.equals("null")) ) {
			query.setParameter("planta_key", planta);
		}
		
		if(! (formaPago.equals("todos") || formaPago.equals("")  || formaPago.equals("null")) ) {
			query.setParameter("forma_pago", formaPago);
		}
		
		return  query.getResultList();
	}

	public void actualizarEstadoCierrecajaparcial(Cierre cierre, Linea linea) {
		Comprobanteestado comprobanteestado = comprobanteEstadoRepository.findOneByKey("ANU");
		List<Cierre> cierresOrdenados = cierreRepository.findAllByPlantaOrderByHoraMax(cierre.getPlanta());

		Long milisecondsCierre = Util.hourToMiliseconds(cierre.getHoraMax());

		Long milisecondsCurrentDateSinHora = Util.milisecondsCurrentDateSinHora();

		Timestamp fechaMax = new Timestamp(milisecondsCurrentDateSinHora + milisecondsCierre);
		Timestamp fechaMin = new Timestamp(milisecondsCurrentDateSinHora);

		// Como los cierres estan ordenados por horario, en este algoritmo
		// verificamos que cierre pertecene al cierre de caja ya calculado por
		// la linea, si se compara y ambos cierres son iguales, para obtener la
		// fecha minima
		// debemos obtenerla con el cierre anterior
		for (int i = 0; i < cierresOrdenados.size(); i++) {
			if (cierresOrdenados.get(i) == cierre) {
				if (i != 0) {
					Long milisecondsFechaMin = milisecondsCurrentDateSinHora
							+ Util.hourToMiliseconds(cierresOrdenados.get(i - 1).getHoraMax());
					fechaMin = new Timestamp(milisecondsFechaMin);
					break;
				}
			}
		}

		String hqlSum = "update Comprobante "
				+ "set cierrecajaparcial = true WHERE :fechapagoMin < fechapago and fechapago < :fechapagoMax "
				+ "and comprobanteestado != :comprobanteestado" + " and linea = :linea";
		Query querySum = entityManager.createQuery(hqlSum);
		querySum.setParameter("fechapagoMin", fechaMin);
		querySum.setParameter("fechapagoMax", fechaMax);
		querySum.setParameter("comprobanteestado", comprobanteestado);
		querySum.setParameter("linea", linea);
		querySum.executeUpdate();
	}
	
	public void updateSendedtoofisis(Long comprobanteid)
	{
		String hqlSum = "update Comprobante "
				+ "set sendedtooffisis = true WHERE id = :id ";
		Query querySum = entityManager.createQuery(hqlSum);
		querySum.setParameter("id", comprobanteid);
		querySum.executeUpdate();
	}
	
	public void updateNotSendedtoofisis(Long comprobanteid)
	{
		String hqlSum = "update Comprobante "
				+ "set sendedtooffisis = false WHERE id = :id ";
		Query querySum = entityManager.createQuery(hqlSum);
		querySum.setParameter("id", comprobanteid);
		querySum.executeUpdate();
	}

	public List<ComprobanteBean> getComprobanteNotGenerated(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaIni= new Date();
		Date fechaFin= new Date();
		List<ComprobanteBean> resultado = new ArrayList();
		StringBuffer hql2 = new StringBuffer("select inspeccion_nrodocumentoinspeccion,s.autorizacionsunat")
				.append(" from comprobante c")
				.append(" inner join linea l on c.linea_key = l.key")
				.append(" inner join planta p on l.planta_key = p.key")
				.append(" inner join seriedocumentobase s on p.key = s.planta_key")
				.append(" where fechapago is null and comprobanteestado_key IN ('CAN','PEN') AND to_char(c.fechcreacion, 'yyyy-mm-dd') between '"+format.format(fechaIni)+"'"+" and '"+format.format(fechaFin)+"'")
				.append(" and cliente_nrodocumentoidentidad is not null and ordentrabajo_id is null");
		Query query = entityManager.createNativeQuery(hql2.toString());
		List<Object> result = (List<Object>) query.getResultList();
		Iterator<Object> itr = result.iterator();
		while(itr.hasNext()){
			ComprobanteBean bean = new ComprobanteBean();
			Object[] obj = (Object[]) itr.next();
			bean.setNroinspeccion(obj[0]!=null?obj[0].toString():"");
			bean.setAutorizacionsunat(obj[1]!=null?obj[1].toString():"");
			resultado.add(bean);
		}
		return resultado;
	}
	public void UpdateComprobanteNotGenerated(String nroinspeccion, String autorizacion){
		StringBuffer hql2 = new StringBuffer("update comprobante")
				.append(" set fechapago= (select fechcreacion from comprobante where inspeccion_nrodocumentoinspeccion='"+nroinspeccion+"'),")
				.append(" autorizacionsunat='"+autorizacion+ "'")
				.append(" where inspeccion_nrodocumentoinspeccion='"+nroinspeccion+"'");
		Query query = entityManager.createNativeQuery(hql2.toString());
		query.executeUpdate();
	}
	
	public List<ComprobanteOfisisBean> getNotSendedToOffisis() {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechapago= new Date();
		Date fechanulacion= new Date();
		List<ComprobanteOfisisBean> resultado = new ArrayList();
		StringBuffer hql2 = new StringBuffer("select distinct com.id as Id,veh.nromotor as NroMotor,pl.key as PlantaKey,")
				.append("coe.key as CompEsKey,td.sunattipo as TpDcmtoSunat,com.nrocomprobante as NroComp,fp.codigoofisis as CodOfisis,tdi.key NroDocIden,com.fechapago as FecPago,")
				.append("com.fechanulacion as FecAnu,com.cliente_nrodocumentoidentidad,per.nombrerazonsocial as NomRznScl,")
				.append("per.nombres as NombreClie,per.direccion as Direc,per.telefono as Tlf,per.email as Email,per.apellidos as Apell,dist.nombre as DistNomb,")
				.append("com.importetotal as Import,com.baseimponible as Subtotal,com.igv as Igv,com.totalsindscto as TotSDscto,")
				.append("com.totaldscto as TotDscto,con.condicionlimitepago as ConLimPago,con.nombre as NomConv,tdscto.key,")
				.append("cam.nombre as CamNombre,stm.estado as EstadoComp,com.descuentocomprobante_id as DsctoCompId, dsctcom.nombredescuento as NombreDscto,tc.key as TipoContado,com.estado as PagoMixto")
				.append(" from comprobante com")
				.append(" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion=ins.nrodocumentoinspeccion ")
				.append(" left join vehiculo veh on ins.vehiculo_nromotor=veh.nromotor ")
				.append(" left join tipodocumento td on com.tipodocumento_key=td.key ")
				.append(" left join formapago fp on com.formapago_key=fp.key ")
				.append(" left join convenio con on com.convenio_id=con.id ")
				.append(" left join campania cam on com.campania_id=cam.id ")
				.append(" left join usuario usr on con.ejecutivo_username=usr.username ")
				.append(" left join persona per on (com.cliente_nrodocumentoidentidad=per.nrodocumentoidentidad)or(usr.persona_nrodocumentoidentidad=per.nrodocumentoidentidad) ")
				.append(" left join distrito dist on per.distrito_key=dist.key ")
				.append(" left join comprobanteestado  coe on com.comprobanteestado_key=coe.key ")
				.append(" left join tipodocumentoidentidad tdi on per.tipodocumentoidentidad_key=tdi.key ")
				.append(" left join tipodescuento tdscto on com.tipodescuento_key=tdscto.key ")
				.append(" left join descuentocomprobante dsctcom on com.descuentocomprobante_id=dsctcom.id ")
				.append(" left join tipocontado tc on com.tipocontado_key = tc.key ")
				.append(" inner join linea li on com.linea_key=li.key ")
				.append(" inner join planta pl on li.planta_key=pl.key ")
				.append(" inner join st_mensaje stm on com.nrocomprobante=stm.nrocomprobante and com.inspeccion_nrodocumentoinspeccion=stm.nrodocumentoinspeccion ")
				.append(" where com.sendedtooffisis=false and ")
				.append(" com.comprobanteestado_key is not null and ")
				.append(" extract(year from com.fechapago)=2019 and  ")
				.append(" extract(month from com.fechapago)=5 and  ")
				.append(" com.nrocomprobante is not null and ")
				.append(" stm.trackid<>'' ")
				//.append(" li.planta_key= "+"'"+planta+"'")
				.append(" order by com.fechapago asc ");
			Query query = entityManager.createNativeQuery(hql2.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			while(itr.hasNext()){
			 ComprobanteOfisisBean bean = new ComprobanteOfisisBean();
				 Object[] obj = (Object[]) itr.next();
				 bean.setId(Long.valueOf(obj[0].toString()));
				 bean.setNromotor(obj[1].toString());
				 bean.setCodigoPlanta(obj[2].toString());
				 bean.setComprobanteEstado(obj[3]!=null?obj[3].toString():"");
				 bean.setTipoDocumento(obj[4]!=null?obj[4].toString():"");
				 bean.setNroComprobante(obj[5]!=null?obj[5].toString():"");
				 bean.setCodigoCondicionPago(obj[6]!=null?obj[6].toString():"");
				 bean.setCodigoVendedor("00");
				 bean.setDocumentoValidado(obj[10]!=null?obj[10].toString():"");
				 try {
					fechapago = format.parse(obj[8].toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				 bean.setFechaPago(fechapago);
				 if(obj[9]!=null) {
					try {
							fechanulacion=format.parse(obj[9].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 bean.setFechaAnulacion(fechanulacion);
				}
				 bean.setDocumentoValidado(obj[10].toString());
				 bean.setNombreCliente(obj[11]!=null?obj[11].toString():"");
				 bean.setNombres(obj[12]!=null?obj[12].toString():"");
				 bean.setDireccion(obj[13]!=null?obj[13].toString():"");
				 bean.setTelefono(obj[14]!=null?obj[14].toString():"");
				 bean.setEmail(obj[15]!=null?obj[15].toString():"");
				 bean.setApellidos(obj[16]!=null?obj[16].toString():"");
				 bean.setDistrito(obj[17]!=null?obj[17].toString():"");
				 bean.setImporteTotal(Double.valueOf(obj[18].toString()));
				 bean.setBaseImponible(Double.valueOf(obj[19].toString()));
				 bean.setIgv(Double.valueOf(obj[20].toString()));
				 bean.setTotalSinDescuento(Double.valueOf(obj[21].toString()));
				 bean.setTotalDescuento(Double.valueOf(obj[22].toString()));
				 bean.setDiasCredito(Integer.valueOf(obj[23]!=null ?obj[23].toString() : "0"));
				 bean.setDocumentoValidado(obj[10].toString());
				 bean.setDesConvenio(obj[24]!=null?obj[24].toString():"");
				 bean.setTipodescuento(obj[25]!=null?obj[25].toString():"");
				 bean.setDesConvenio(obj[24]!=null?obj[24].toString():"");
				 bean.setDesCampania(obj[26]!=null?obj[26].toString():"");
				 bean.setEstadoComprobanteSunat(obj[27]!=null?obj[27].toString():"");
				 bean.setSituacionJuridica(obj[7]!=null?obj[7].toString():"");
				 bean.setNombredescuento(obj[29]!=null?obj[29].toString():"");
				 bean.setTipocontado(obj[30]!=null?obj[30].toString():"");
				 bean.setPagoMixto(Boolean.valueOf(obj[31].toString()));
				 resultado.add(bean);
			}
		return resultado;
	}
	
	public List<ComprobanteOfisisBean> getNotSendedToOffisisByFechaAndPlanta(Date fechIni, Date fechFin, String planta ) {
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechapago= new Date();
		Date fechanulacion= new Date();
		Calendar calendarIni = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaMax);
		calendarIni.setTime(fechaMin);
		Integer mes[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int anioIni= calendarIni.get(Calendar.YEAR);
		int mesIni = mes[calendarIni.get(Calendar.MONTH)];
		int diaIni = calendarIni.get(Calendar.DAY_OF_MONTH);

		int anioFni= calendarFin.get(Calendar.YEAR);
		int mesFni = mes[calendarFin.get(Calendar.MONTH)];
		int diaFni = calendarFin.get(Calendar.DAY_OF_MONTH);
		
		List<ComprobanteOfisisBean> resultado = new ArrayList();
		StringBuffer hql2 = new StringBuffer("select distinct com.id as Id,veh.nromotor as NroMotor,pl.key as PlantaKey,")
				.append("coe.key as CompEsKey,td.sunattipo as TpDcmtoSunat,com.nrocomprobante as NroComp,fp.codigoofisis as CodOfisis,tdi.key NroDocIden,com.fechapago as FecPago,")
				.append("com.fechanulacion as FecAnu,com.cliente_nrodocumentoidentidad,per.nombrerazonsocial as NomRznScl,")
				.append("per.nombres as NombreClie,per.direccion as Direc,per.telefono as Tlf,per.email as Email,per.apellidos as Apell,dist.nombre as DistNomb,")
				.append("com.importetotal as Import,com.baseimponible as Subtotal,com.igv as Igv,com.totalsindscto as TotSDscto,")
				.append("com.totaldscto as TotDscto,con.condicionlimitepago as ConLimPago,con.nombre as NomConv,tdscto.key as TipDscto,")
				.append("cam.nombre as CamNombre,stm.estado as EstadoComp,com.descuentocomprobante_id as DsctoCompId, dsctcom.nombredescuento as NombreDscto,tc.key as TipoContado,com.estado as PagoMixto")
				.append(" from comprobante com")
				.append(" left join inspeccion ins on com.inspeccion_nrodocumentoinspeccion=ins.nrodocumentoinspeccion ")
				.append(" left join vehiculo veh on ins.vehiculo_nromotor=veh.nromotor ")
				.append(" left join tipodocumento td on com.tipodocumento_key=td.key ")
				.append(" left join formapago fp on com.formapago_key=fp.key ")
				.append(" left join convenio con on com.convenio_id=con.id ")
				.append(" left join campania cam on com.campania_id=cam.id ")
				.append(" left join usuario usr on con.ejecutivo_username=usr.username ")
				.append(" left join persona per on (com.cliente_nrodocumentoidentidad=per.nrodocumentoidentidad)or(usr.persona_nrodocumentoidentidad=per.nrodocumentoidentidad) ")
				.append(" left join distrito dist on per.distrito_key=dist.key ")
				.append(" left join comprobanteestado  coe on com.comprobanteestado_key=coe.key ")
				.append(" left join tipodocumentoidentidad tdi on per.tipodocumentoidentidad_key=tdi.key ")
				.append(" left join tipodescuento tdscto on com.tipodescuento_key=tdscto.key ")
				.append(" left join descuentocomprobante dsctcom on com.descuentocomprobante_id=dsctcom.id ")
				.append(" left join tipocontado tc on com.tipocontado_key = tc.key ")
				.append(" inner join linea li on com.linea_key=li.key ")
				.append(" inner join planta pl on li.planta_key=pl.key ")
				.append(" inner join st_mensaje stm on com.nrocomprobante=stm.nrocomprobante and com.inspeccion_nrodocumentoinspeccion=stm.nrodocumentoinspeccion ")
				.append(" where com.sendedtooffisis=false and ")
				.append(" com.comprobanteestado_key is not null and com.nrocomprobante is not null and ")
				//.append(" stm.trackid<>'' and ")
				.append(" li.planta_key= "+"'"+planta+"' and ")
				.append(" extract(year from com.fechapago) between "+anioIni+" and "+anioFni+" and ")
				.append(" extract(month from com.fechapago) between "+mesIni+" and "+mesFni+" and ")
				.append(" extract(day from com.fechapago) between "+diaIni+" and "+diaFni)
				.append(" order by com.fechapago asc ");
			Query query = entityManager.createNativeQuery(hql2.toString());
			List<Object> result = (List<Object>) query.getResultList();
			Iterator<Object> itr = result.iterator(); 
			while(itr.hasNext()){
			 ComprobanteOfisisBean bean = new ComprobanteOfisisBean();
				 Object[] obj = (Object[]) itr.next();
				 bean.setId(Long.valueOf(obj[0].toString()));
				 bean.setNromotor(obj[1].toString());
				 bean.setCodigoPlanta(obj[2].toString());
				 bean.setComprobanteEstado(obj[3]!=null?obj[3].toString():"");
				 bean.setTipoDocumento(obj[4]!=null?obj[4].toString():"");
				 bean.setNroComprobante(obj[5]!=null?obj[5].toString():"");
				 bean.setCodigoCondicionPago(obj[6]!=null?obj[6].toString():"");
				 bean.setCodigoVendedor("00");
				 bean.setDocumentoValidado(obj[10]!=null?obj[10].toString():"");
				 try {
					fechapago = format.parse(obj[8].toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				 bean.setFechaPago(fechapago);
				 if(obj[9]!=null) {
					try {
							fechanulacion=format.parse(obj[9].toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 bean.setFechaAnulacion(fechanulacion);
				}
				 bean.setDocumentoValidado(obj[10].toString());
				 bean.setNombreCliente(obj[11]!=null?obj[11].toString():"");
				 bean.setNombres(obj[12]!=null?obj[12].toString():"");
				 bean.setDireccion(obj[13]!=null?obj[13].toString():"");
				 bean.setTelefono(obj[14]!=null?obj[14].toString():"");
				 bean.setEmail(obj[15]!=null?obj[15].toString():"");
				 bean.setApellidos(obj[16]!=null?obj[16].toString():"");
				 bean.setDistrito(obj[17]!=null?obj[17].toString():"");
				 bean.setImporteTotal(Double.valueOf(obj[18].toString()));
				 bean.setBaseImponible(Double.valueOf(obj[19].toString()));
				 bean.setIgv(Double.valueOf(obj[20].toString()));
				 bean.setTotalSinDescuento(Double.valueOf(obj[21].toString()));
				 bean.setTotalDescuento(Double.valueOf(obj[22].toString()));
				 bean.setDiasCredito(Integer.valueOf(obj[23]!=null ?obj[23].toString() : "0"));
				 bean.setDocumentoValidado(obj[10].toString());
				 bean.setDesConvenio(obj[24]!=null?obj[24].toString():"");
				 bean.setTipodescuento(obj[25]!=null?obj[25].toString():"");
				 bean.setDesConvenio(obj[24]!=null?obj[24].toString():"");
				 bean.setDesCampania(obj[26]!=null?obj[26].toString():"");
				 bean.setEstadoComprobanteSunat(obj[27]!=null?obj[27].toString():"");
				 bean.setSituacionJuridica(obj[7]!=null?obj[7].toString():"");
				 bean.setNombredescuento(obj[29]!=null?obj[29].toString():"");
				 bean.setTipocontado(obj[30]!=null?obj[30].toString():"");
				 bean.setPagoMixto(Boolean.valueOf(obj[31].toString()));
				 resultado.add(bean);
			}
		return resultado;
	}
	
	public Long getCountComprobantesByFechaandPlanta(Date fechIni, Date fechFin, String planta) {
		Long result;
		Timestamp fechaMin = new Timestamp(fechIni.getTime());
        Timestamp fechaMax = new Timestamp(fechFin.getTime());
		String hql1 = "SELECT count(com)"
                + " FROM Comprobante com"
                + " inner join com.inspeccion ins"
                + " left join ins.vehiculo veh"
                + " inner join com.cliente cli"
        		+ " left join com.tipodocumento td"
                + " left join com.formaPago cfp"
                + " left join com.convenio con"
                + " left join com.campania camp"
                + " left join con.persona pco"
                + " left join con.ejecutivo coneje"
                + " left join coneje.persona conper"
                + " left join cli.distrito dis"
                + " inner join com.comprobanteestado coe"
				+ " left join cli.tipodocumentoidentidad tidocid"
                + " left join com.tipodescuento ctipd"
                + " left join cli.distrito cldist"
                + " inner join com.linea li"
                + " inner join li.planta pl"
                + " WHERE pl.key = :planta"
                + " and to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between :fechMin and :fechMax"
                + " and com.comprobanteestado is not null and com.nrocomprobante is not null";
        TypedQuery<Long> typeQueryCount = entityManager.createQuery(hql1,Long.class);
        typeQueryCount.setParameter("planta", planta);
        typeQueryCount.setParameter("fechMin", fechaMin);
        typeQueryCount.setParameter("fechMax", fechaMax);
        result = typeQueryCount.getSingleResult();
        
        return result;
	}
}
