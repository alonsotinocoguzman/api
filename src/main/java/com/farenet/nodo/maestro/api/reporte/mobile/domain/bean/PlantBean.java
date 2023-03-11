package com.farenet.nodo.maestro.api.reporte.mobile.domain.bean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlantBean {

    public String id;
    public String name;
    public List<Line> lines;

    public PlantBean(String id, String name, String lineId, String lineName) {
        this.id = id;
        this.name = name;
        this.lines = Collections.singletonList(buildLine(lineId, lineName));
    }

    private Line buildLine(String lineId, String lineName) {
        Line line = new Line();
        line.id = lineId;
        line.name = lineName;
        return line;
    }

    public static PlantBean merge(List<PlantBean> list) {
        PlantBean plantBean = list.get(0);
        plantBean.lines = list.stream()
                .map(bean -> bean.lines.get(0))
                .collect(Collectors.toList());
        return plantBean;
    }

    public static class Line {

        public String id;
        public String name;
        public Double amount;
        public Integer carsQuantity;
        
        
		public Line() {
			super();
		}


		public Line(Double amount, Long carsQuantity) {
			super();
			this.amount = amount;
			this.carsQuantity = carsQuantity.intValue();
		}
        
        

    }

}
