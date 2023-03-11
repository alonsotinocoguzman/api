package com.farenet.nodo.maestro.api.sunat.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.farenet.nodo.maestro.api.sunat.domain.ST_Mensaje;
import com.farenet.nodo.maestro.api.sunat.domain.bean.ConsultaSunatBean;
import com.farenet.nodo.maestro.api.sunat.repository.ST_MensajeRepository;
import com.farenet.nodo.maestro.api.util.UIUtils;

@Service
@Transactional
public class ST_MensajeService {

	@Autowired
	private ST_MensajeRepository stMensajeRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public  List<ST_Mensaje> getMensaje(String nrodocumentoinspeccion) {
		
		return stMensajeRepository.findByInspeccion(nrodocumentoinspeccion);
		
	}
	
	public void guardar(ST_Mensaje st_Mensaje) {
		stMensajeRepository.save(st_Mensaje);
	}
	
	public ST_Mensaje findByInspeccionAndTipo(String nrodocumentoinspeccion){
		return stMensajeRepository.findByInspeccionAndTipo(nrodocumentoinspeccion, "NOTA_MASIVO");
	}
	
	public String getRucEmpresaByNroComprobante(String nrocomprobante){
		String resultado = "";
		try{
			String hql = "select emp.ruc from Comprobante com"
					+ " inner join com.empresa emp"
					+ " where com.nrocomprobante = :nrocomprobante";
			Query query = entityManager.createQuery(hql);
			query.setParameter("nrocomprobante", nrocomprobante);
			query.setMaxResults(1);
			resultado = query.getSingleResult().toString();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return resultado;
	}
	
	public String getTipoDocumentoClienteByComprobante(String nrocomprobante){
		String resultado = "";
		try{
			String hql = "select tdi.key from Comprobante com"
					+ " inner join com.cliente cli"
					+ " inner join cli.tipodocumentoidentidad tdi"
					+ " where com.nrocomprobante = :nrocomprobante";
			Query query = entityManager.createQuery(hql);
			query.setParameter("nrocomprobante", nrocomprobante);
			query.setMaxResults(1);
			resultado = query.getSingleResult().toString();
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		return resultado;
	}
	
	public String getRucNotaCreditoByNroInspeccion(String nrodocumentoinspeccion){
		String resultado = "";
		try{
			String hql = "select nc.nroRucEmpresa from ST_NotaDeCredito nc"
					+ " where nc.nrodocumentoinspeccion = :nrodocumentoinspeccion";
			Query query = entityManager.createQuery(hql);
			query.setParameter("nrodocumentoinspeccion", nrodocumentoinspeccion);
			query.setMaxResults(1);
			resultado = query.getSingleResult().toString();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
	
	public String getRucComprobanteManualByNroComprobante(String serie, Long correlativo){
		String resultado = "";
		try{
			String hql = "select com.rucEmpresa "
					+ " FROM ST_Comprobantes com"
					+ " WHERE com.serie = :serie and com.correlativo = :correlativo ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("serie", serie);
			query.setParameter("correlativo", correlativo);
			query.setMaxResults(1);
			resultado = query.getSingleResult().toString();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
	
	public String getTipodocComprobanteManualByNroComprobante(String serie, Long correlativo){
		String resultado = "";
		try{
			String hql = "select com.tipo "
					+ " FROM ST_Comprobantes com"
					+ " WHERE com.serie = :serie and com.correlativo = :correlativo ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("serie", serie);
			query.setParameter("correlativo", correlativo);
			query.setMaxResults(1);
			resultado = query.getSingleResult().toString();
		}catch (Exception e) {
			
		}
		return resultado;
	}
	
	public List<ConsultaSunatBean> getAllSinSt_mensaje(){
		List<ConsultaSunatBean> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(UIUtils.FORMATO_FECHA_SHORT);
		String fecha = dateFormat.format(calendar.getTime()) ;
		String hql = "select com.nrocomprobante, i.nrodocumentoinspeccion from comprobante as com " + 
				"inner join inspeccion i on com.inspeccion_nrodocumentoinspeccion = i.nrodocumentoinspeccion " + 
				"where to_date(to_char(com.fechapago, 'YYYY-MM-DD'), 'YYYY-MM-DD') between '"+fecha+"' and '"+fecha+"' " + 
				"and com.importetotal > 0";
		Query query = entityManager.createNativeQuery(hql);
		List<Object> result = (List<Object>) query.getResultList();
        Iterator<Object> itr = result.iterator();
        while(itr.hasNext()){
        	ConsultaSunatBean bean = new ConsultaSunatBean();
        	Object[] obj = (Object[]) itr.next();
        	bean.setNrocomprobante(obj[0] != null ? obj[0].toString():null);
        	bean.setNrodocumentoinspeccion(obj[1] != null ? obj[1].toString():null);
        	list.add(bean);
        }
            
		return list;
	}
	
	public List<ConsultaSunatBean> getAllSunatSinEnviar(){
		List<ConsultaSunatBean> sunatBeans = new ArrayList<>();
		
		String hql = "select DISTINCT new "+ConsultaSunatBean.class.getName()
				+ "(st.id, st.nrodocumentoinspeccion, st.nrocomprobante, st.tipo, st.estado)"
				+ " FROM ST_Mensaje st"
				+ " WHERE st.estado is null or st.estado != 'ANU' and st.estado != 'DOK'";
		Query query = entityManager.createQuery(hql);
		sunatBeans = query.getResultList();
		
		for(ConsultaSunatBean bean : sunatBeans){
			String comprobante[] = bean.getNrocomprobante().split("-");
			if(bean.getTipo().equals("ALTA") || bean.getTipo().equals("BAJA")) {
				bean.setRucempresa(getRucEmpresaByNroComprobante(bean.getNrocomprobante()));
				String tipodocumento = getTipoDocumentoClienteByComprobante(bean.getNrocomprobante());
				 if(tipodocumento.equals("ruc")){
					 bean.setTipodocumento("01");
				 }else {
					 bean.setTipodocumento("03");
				 }
			}
			if(bean.getTipo().equals("NOTA_MASIVO")){
				bean.setTipodocumento("07");
				bean.setRucempresa(getRucNotaCreditoByNroInspeccion(bean.getNrodocumentoinspeccion()));
				
			}
			if(bean.getTipo().equals("ALTA_MANUAL")){
				bean.setRucempresa(getRucComprobanteManualByNroComprobante(comprobante[0], Long.valueOf(comprobante[1])));
				bean.setTipodocumento(getTipodocComprobanteManualByNroComprobante(comprobante[0], Long.valueOf(comprobante[1])).split("-")[0]); 
			}
		}
		
		return sunatBeans;
	}
	
	public void actualizarEstadoStMensaje(String estado, Long id){
		try{
			String hql = "update ST_Mensaje "
					+ "set estado = :estado WHERE id = :id ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("estado", estado);
			query.setParameter("id", id);
			query.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
