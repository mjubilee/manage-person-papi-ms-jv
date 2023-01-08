package com.mjubilee.managepersonpapimsjv.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.core.GenericType;

public interface ClientRequest {

	public Logger log = LoggerFactory.getLogger(ClientRequest.class);
	
	//instead of using Class<T>, this function consume GenericType<T> to resolve the List response
	public <T> T submitRequest (String endpoint, GenericType<T> returnClass);
	
//	public Observable<Person> submitRequestAsync (String endpoint);
	
	
	
}
