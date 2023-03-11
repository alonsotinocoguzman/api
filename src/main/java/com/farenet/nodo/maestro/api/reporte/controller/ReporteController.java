package com.farenet.nodo.maestro.api.reporte.controller;

import java.util.Date;
import java.util.List;

import com.farenet.nodo.maestro.api.caja.domain.Cierrecaja;
import com.farenet.nodo.maestro.api.caja.domain.bean.DescuentoEditedBean;
import com.farenet.nodo.maestro.api.reporte.bean.*;
import com.farenet.nodo.maestro.api.util.CompleteResultReportSMSBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farenet.nodo.maestro.api.base.controller.BaseController;
import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.repository.CierreRepository;
import com.farenet.nodo.maestro.api.util.hibernate.JsonDateDeserializer;
import com.farenet.nodo.maestro.api.inspeccion.domain.Linea;
import com.farenet.nodo.maestro.api.inspeccion.repository.ConceptoinspeccionRepository;
import com.farenet.nodo.maestro.api.inspeccion.repository.LineaRepository;
import com.farenet.nodo.maestro.api.reporte.service.ReporteService;
import com.farenet.nodo.maestro.api.seguridad.domain.Usuario;
import com.farenet.nodo.maestro.api.seguridad.repository.UsuarioRepository;
import com.farenet.nodo.maestro.api.util.ResultReportBean;

@RestController
@RequestMapping("/reporte")
public class ReporteController extends BaseController{

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private CierreRepository cierreRepository;

    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConceptoinspeccionRepository conceptoinspeccionRepository;

    @RequestMapping(value = "/cierrecaja/{cierre_key}/{linea_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteCierrecajaBean> getReporteCierrecaja(@PathVariable String cierre_key,
                                                            @PathVariable String linea_key) {
        Cierre cierre = cierreRepository.findOneByKey(cierre_key);
        Linea linea = lineaRepository.findOneByKey(linea_key);
        return reporteService.getReporteCierrecaja(cierre, linea);
    }

    @RequestMapping(value = "/cierrecaja-usuario/{username}/{planta_key}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteCierrecajaBean> getReporteCierrecajaUsuario(@PathVariable String username,
                                                                   @PathVariable String planta_key) {
        Usuario usuario = usuarioRepository.findOneByUser(username);

        return reporteService.getReporteCierrecajaUsuario(usuario, planta_key);
    }

    @RequestMapping(value = "/cierrecaja", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierrecaja> getReporteCierrecajaUsuario(@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni, @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin, @Param("planta") String planta) {

        return reporteService.getReporteCierreCajaByDateRangeByPlanta(fechIni, fechFin, planta);
    }

    @RequestMapping(value = "/consolidado-inspeccion-ventas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteConsolidadoInspeccionVentasBean> getReporteconsolidadoInspeccionVentas(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fech") Date fech) throws Exception {

        return reporteService.getReporteconsolidadoInspeccionVentas(fech);

    }

    @RequestMapping(value = "/inspecciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<InspeccionReporteBean>> getAllInspeccionesByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta) throws Exception {

        return reporteService.getAllByDateRangeByPlanta(fechIni, fechFin, planta);

    }
    
    @RequestMapping(value = "/comprobantesAtrasados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ComprobantesAtrasadosReporteBean> getAllComprobantesRetrasadosByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta, @Param("hora") Integer hora) throws Exception {

        return reporteService.getAllComprobantesAtrasadosByDateRangeByPlanta(fechIni, fechFin, planta,hora);

    }
    
    @RequestMapping(value = "/certificados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<CertificadoReporteBean>> getCertificadoByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta) throws Exception {

        return reporteService.getCertificadoByDateRangeByPlanta(fechIni, fechFin, planta);

    }
    
    @RequestMapping(value = "/contabilidad", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ContabilidadReporteBean>> getContabilidadByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta) throws Exception {

        return reporteService.getContabilidadByDateRangeByPlanta(fechIni, fechFin, planta);

    }
    
    @RequestMapping(value = "/sunat", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<SunatReporteBean> getSunatByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta) throws Exception {

        return reporteService.getSunatByDateRangeByPlanta(fechIni, fechFin, planta);

    }
    
    @RequestMapping(value = "/operaciones", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<OperacionesReporteBean>> getReporteOperaciones(
    		@DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key) throws Exception {

        return reporteService.getReporteOperaciones(fechIni, fechFin, planta_key);

    }

    @RequestMapping(value = "/gerencia", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteGerenciaBean>> getReporteGerencia(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("concepto_key") String concepto_key, @Param("planta_key") String planta_key) throws Exception {

        return reporteService.getReporteGerenciaByDateRangeByConcepto(fechIni, fechFin, concepto_key, planta_key);

    }
    
    @RequestMapping(value = "/comprobantes", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteComprobanteBean>> getReporteComprobantes(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key) throws Exception {

        return reporteService.getReporteComprobantes(fechIni, fechFin, planta_key);

    }

    @RequestMapping(value = "/comercial", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ComercialReporteBean>> getReporteComercial(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key) throws Exception {

        return reporteService.getReporteComercialByRangeDate(fechIni, fechFin, planta_key);

    }

    @RequestMapping(value = "/mtc", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteMtcBean>> getReporteMTC(
    	     @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
             @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,

            @Param("planta_key") String planta_key) throws Exception {

        System.out.println("Entro al controlador");
        return reporteService.getReporteMtcByDateBy(fechIni,fechFin, planta_key);

    }
    @RequestMapping(value = "/mtcnew", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteMtcNewBean> getReporteMTCNew(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin) throws Exception {
        return reporteService.getReporteMtcByRange(fechIni,fechFin);

    }
    
    @RequestMapping(value = "/operatividad", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteOperatividadBean>> getReporteOperatividad(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta") String planta) throws Exception {

        return reporteService.getOperatividadByDateRangeByPlanta(fechIni, fechFin, planta);

    }
    
    @RequestMapping(value = "/visitas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteVisitasBean>> getReporteVisitas(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("concepto_key") String concepto_key, @Param("planta") String planta) throws Exception {

        return reporteService.getReporteVisitasByDateRangeByConcepto(fechIni, fechFin, concepto_key, planta);

    }
    
    @RequestMapping(value = "/expedientes", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteExpedientesBean> getReporteVisitas(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin) throws Exception {

        return reporteService.getReporteExpedientes(fechIni, fechFin);
    }
    
    @RequestMapping(value = "/convenios", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteConvenioBean> getReporteConvenio() throws Exception {

        return reporteService.getReporteConvenio();
    }
    
    @RequestMapping(value = "/finanzas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteFinanzasBean>> getReporteAllFinanzas(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key) throws Exception {
    
        return reporteService.getReporteAllFinanzas(fechIni, fechFin,planta_key);

    }
    
    @RequestMapping(value = "/finanzas/cuponidad", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteFinanzasBean>> getReporteAllFinanzasCuponidad(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key) throws Exception {
    
        return reporteService.getReporteAllFinanzasCuponidad(fechIni, fechFin,planta_key);

    }
    
    @RequestMapping(value = "/caja-detalle", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<Cierrecaja> getCierreCajaDetalleByDateRangeByPlanta(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fech") Date fech) throws Exception {
        return reporteService.getReporteCierreCaja(fech);

    }
    
    @RequestMapping(value = "/resumen-ingresos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteResumenIngresosBean>> getReporteResumenIngresos(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin)
            		throws Exception {
    
        return reporteService.getReporteResumenIngresos(fechIni, fechFin);

    }
    
    @RequestMapping(value = "/recibos-manuales", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteRecibosManualesOfisis> getReporteRecibosManualesOfisis(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteRecibosManualesOfisis(fechIni, fechFin,planta_key);

    }
    
    @RequestMapping(value = "/notascredito", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteNotasCreditoOfisis> getReporteNotasCreditoOfisis(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {    
        return reporteService.getReporteNotasCreditoOfisis(fechIni, fechFin,planta_key);

    }

    @RequestMapping(value = "/inspeccionesconsolidadas", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteInspeccionesConsolidadas> getReporteInspeccionconsolidadas(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin)
            throws Exception {
        return reporteService.getReporteinspeccionesConsolidadas(fechIni, fechFin);

    }

    @RequestMapping(value = "/efectividad-sms", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public CompleteResultReportSMSBean getReporteEfectividadSMS(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIniSms") Date fechIniSms,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFinSms") Date fechFinSms,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIniIns") Date fechIniIns,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFinIns") Date fechFinIns,
            @Param("planta_key") String planta_key)
            throws Exception {

        return reporteService.getReporteEfectividadSMS(fechIniSms, fechFinSms,fechIniIns,fechFinIns,planta_key);

    }

    @RequestMapping(value = "/cuentas-cobrar", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteCuentasPorCobrar> getReporteCuentasPorCobrar(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key, @Param("nrodocumento") String nrodocumento, @Param("formapago") String formapago) throws Exception {

        return reporteService.getReporteCuentasPorCobrar(fechIni, fechFin, planta_key, nrodocumento, formapago);

    }
    @RequestMapping(value = "/cxcconsolidado", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteCuentasPorCobrar> getReporteCxCConsolidado(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key, @Param("formapago") String formapago) throws Exception {

        return reporteService.getReporteCxCConsolidado(fechIni, fechFin, planta_key, formapago);

    }
    
    @RequestMapping(value = "/cortesias", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteCortesiaBean> getReporteCortesias(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin)
            		throws Exception {
    
        return reporteService.getReporteCortesias(fechIni, fechFin);

    }
    
    @RequestMapping(value = "/control-tiempo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteControlTiempoBean>> getReporteControlTiempo(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteControlBean(fechIni, fechFin, planta_key);

    }
    
    @RequestMapping(value = "/tiempo-estacion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteControlEstacionesBean>> getReporteTiempoEstaciones(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key,
            @Param("etapa") String etapa)
            		throws Exception {
    
        return reporteService.getReporteTiempoEstacion(fechIni, fechFin, planta_key, etapa);

    }
    
    @RequestMapping(value = "/reporte-ot", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteOT>  getReporteOT(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("empresa_key") String empresa_key)
            		throws Exception {
    
        return reporteService.getReporteOT(fechIni, fechFin, empresa_key);

    }
    
    @RequestMapping(value="/reporte-ot-pendiente",method=RequestMethod.GET,produces="application/json; charset=utf-8")
    public List<ReporteOT> getReporteOTPendiente(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("empresa_key") String empresa_key){
    	return reporteService.getReporteOTPendiente(fechIni, fechFin, empresa_key);
    }
    
    
    
    @RequestMapping(value = "/descuento", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteDescuento>> getReporteDescuento(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteDescuentoBean(fechIni, fechFin, planta_key);

    }

    
    @RequestMapping(value = "/control-auditoria", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ReporteControlAuditoriaBean> getReporteControAuditoria(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {    
        return reporteService.getReporteControlAuditoria(fechIni, fechFin,planta_key);

    }
    
    @RequestMapping(value = "/vehiculos_inspeccionados", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteVehiculosInspeccionadosBean>> getReporteVehiculosInspeccionados(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteVehiculosInspeccionados(fechIni, fechFin, planta_key);

    }
    
    @RequestMapping(value = "/descuento_consolidado", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteDescuentoConsolidadoBean>> getReporteDescuentosConsolidados(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteDescuentosConsolidado(fechIni, fechFin, planta_key);

    }
    
    @RequestMapping(value = "/defecto", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteDefectoBean>> getReporteDefecto(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
            @Param("planta_key") String planta_key)
            		throws Exception {
    
        return reporteService.getReporteDefectoBean(fechIni, fechFin, planta_key);

    }
    
    @RequestMapping(value = "/descuentoConsolidado", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteDescuentoTipoBean>> getReportesDescuentoConsolidado(
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("dateIni") Date dateIni,
            @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("dateFin") Date dateFin,
            @Param("planta_key") String planta_key, @Param("tipodescuento_key") String tipodescuento_key,
            @RequestBody String nombreDescuento)
            		throws Exception {
    
        return reporteService.getReporteDescuentoTipoBean(dateIni, dateFin, tipodescuento_key, planta_key, nombreDescuento);

    }
    
    @RequestMapping(value = "/inspeccionesmtc", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteInspeccionesMTCBean>> getContarInspecciones(
   	     @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
         @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
         @Param("planta_key") String planta_key) throws Exception{
    		return reporteService.getInspeccionesMTC(fechIni, fechFin, planta_key);
    }
    
    @RequestMapping(value = "/inspeccionesmtcdetalle", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteInpeccionesMTCDetalleBean>> getListarDetalle(
   	     @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
         @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
         @Param("planta_key") String planta_key) throws Exception{
    		return reporteService.getInpeccionesMTCDetalle(fechIni, fechFin, planta_key);
    }
    
    @RequestMapping(value = "/reinspeccionplacanueva", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<ResultReportBean<ReporteReinspeccionesPlacaNuevaBean>> getListarReinspecciones(
   	     @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechIni") Date fechIni,
         @DateTimeFormat(pattern = JsonDateDeserializer.DATE_FORMAT) @Param("fechFin") Date fechFin,
         @Param("planta_key") String planta_key) throws Exception{
    		return reporteService.getReinspeccionesPlacaNueva(fechIni, fechFin, planta_key);
    }
    
    
    
    
}
