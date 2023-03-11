package com.farenet.nodo.maestro.api.event.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import com.farenet.nodo.maestro.api.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.farenet.nodo.maestro.api.event.domain.Log;

@Repository
public class LogRepository {

	@Autowired
	MongoOperations mongoOperations;
	
	@PostConstruct
	private void init()
	{
		if (!mongoOperations.collectionExists(Log.class)) {
			mongoOperations.createCollection(Log.class);
		}
	}
	
	public void saveLog(Object log)
	{
		mongoOperations.insert(log);
	}
	
	public Log getListLogLastest(String target,String message)
	{
		BasicQuery query = new BasicQuery("{target : '"+ target +"' , message  : '"+ message +"' }");
		query.limit(1);

		query.with(Sort.by("date").descending());
		
		return mongoOperations.findOne(query, Log.class);
	}
}
