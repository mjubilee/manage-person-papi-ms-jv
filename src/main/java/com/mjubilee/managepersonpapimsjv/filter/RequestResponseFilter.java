package com.mjubilee.managepersonpapimsjv.filter;

//import java.io.IOException;
//
//import jakarta.ws.rs.container.ContainerRequestContext;
//import jakarta.ws.rs.container.ContainerResponseContext;
//import jakarta.ws.rs.container.ContainerResponseFilter;
//import jakarta.ws.rs.ext.Provider;
//

import java.io.IOException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RequestResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("X-Powered-By", "Jersey :-)");
		System.out.println("inside response filter");
		
	}

//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		System.out.println("inside request filter");
//		
//	}

}
