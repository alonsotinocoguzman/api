package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

import java.util.List;

public class ReportBean {

    public String id;
    public Double totalIncome = 0.0;
    public Long totalInvoiced = 0L;
    public Double totalDisapproved = 0.0;
    //public Double totalPercentage = 0.0;
    public Long date;
    public List<PlantReport> plantReports;

	public static class PlantReport {

        public String id;
        public String name;
        public Double income;
        public Integer processed;
        public Long invoiced;
        public Double disapproved;
        public Double percentage = 0.0;
        
    }
}
