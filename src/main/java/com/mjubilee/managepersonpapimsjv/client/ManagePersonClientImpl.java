package com.mjubilee.managepersonpapimsjv.client;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Component;

import com.mjubilee.managepersonpapimsjv.filter.RequestResponseFilter;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response.Status;
//import rx.Observable;
//
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.glassfish.jersey.apache.connector.ApacheClientProperties;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.rx.Rx;
//import org.glassfish.jersey.client.rx.RxClient;
//import org.glassfish.jersey.client.rx.rxjava.RxObservable;
//import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;

@Component
public class ManagePersonClientImpl implements ClientRequest{

	@Override
	public <T> T submitRequest(String endpoint, GenericType<T> returnClass) {		
		log.info( "ManagePersonClientImpl : submitRequest -- Retrieve a person profile");
		
		ClientConfig config = new ClientConfig();
		config.register(RequestResponseFilter.class);
		
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(endpoint);
		
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		
		return builder.get(returnClass);
	}

//	@Override	
//	public  Observable<Person> submitRequestAsync(String endpoint) {
//		final RxClient<RxObservableInvoker> rxClient;
//		ClientConfig config = new ClientConfig();
//
//	    config.property(ApacheClientProperties.CONNECTION_MANAGER, new PoolingHttpClientConnectionManager());
////		rxClient =  Rx.from(ClientBuilder.newClient(config), RxObservableInvoker.class);
//	    rxClient =  RxObservable.from((javax.ws.rs.client.Client) ClientBuilder.newClient(config));
//	    
//		return rxClient.target(endpoint)
//		        .request(MediaType.APPLICATION_JSON).rx().get().map(response -> {
//		            
//		            try {
//		              if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
//		                throw new Exception(response.readEntity(String.class));
//		              }
//		              return response.readEntity(Person.class);
//		            } catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		            finally {
//		              response.close();
//		            }
//					return null;
//		          });
//	}
//	
	
}
