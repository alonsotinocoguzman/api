package com.farenet.nodo.maestro.api.util;

import java.util.List;

import com.farenet.nodo.maestro.api.reporte.bean.ReporteEfectividadSMSBean;

public class CompleteResultReportSMSBean {
	
	
	public List<ResultReportSMSBean<ReporteEfectividadSMSBean>> data;
	public Integer totalSMS;
	public List<ResultReportSMSBean<ReporteEfectividadSMSBean>> getData() {
		return data;
	}
	public void setData(List<ResultReportSMSBean<ReporteEfectividadSMSBean>> data) {
		this.data = data;
	}
	public Integer getTotalSMS() {
		return totalSMS;
	}
	public void setTotalSMS(Integer totalSMS) {
		this.totalSMS = totalSMS;
	}
	
	


	
	
	
	

}
