package com.farenet.nodo.maestro.api.util;

import java.util.List;

import org.springframework.data.domain.Page;

public class ResultPageBean<T> {

	private int totalPages;
	private List<T> content;
	private long totalElements;
	
	public ResultPageBean(int totalPages,long totalElements,List<T> content)
	{
		this.totalPages = totalPages;
		this.content = content;
			this.totalElements = totalElements;
	}
	
	public ResultPageBean(Page<T> page)
	{
		this.totalPages = page.getTotalPages();
		this.content = page.getContent();
		this.totalElements = page.getTotalElements();
	}
	
	public int getTotalPages()
	{
		return totalPages;
	}
	
	public List<T> getContent()
	{
		return content;
	}
	
	public long getTotalElements()
	{
		return totalElements;
	}
	
}
