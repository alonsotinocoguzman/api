package com.farenet.nodo.maestro.api.base.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class URLListener {

	@EventListener
	  public void handleEvent (RequestHandledEvent e) {
	      System.out.println(e);
	  }
}
