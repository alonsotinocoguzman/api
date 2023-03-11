package com.farenet.nodo.maestro.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageBean {

	public enum Ordenar{
		DESC,  ASC
	}
	
	public int numPag;
	public int totalFilasPorPagina;
	public Ordenar sort;
	public String column;
	
	public PageBean(int numPage, int totalFilasPorPagina,Ordenar sort,String column)
	{
		this.numPag = numPage;
		this.totalFilasPorPagina = totalFilasPorPagina;
		this.sort = sort;
		this.column = column;
		
	}
	
	public Pageable convertToPage()
	{
		Sort sort2 = sort.equals(Ordenar.ASC) ? Sort.by(column).ascending() : Sort.by(column).descending();

		return PageRequest.of(numPag-1,
				totalFilasPorPagina,
				sort2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+numPag+totalFilasPorPagina+sort+column;
	}
	
	
}
