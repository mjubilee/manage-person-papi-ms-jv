package com.mjubilee.managepersonpapimsjv.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.springframework.stereotype.Component;

import com.mjubilee.managepersonpapimsjv.filter.RequestResponseFilter;
import com.mjubilee.managepersonpapimsjv.model.Person;

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
import rx.Observable;

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

	@Override
	public Observable<Person> submitRequestAsync(String endpoint) {
		log.info( "ManagePersonClientImpl : submitRequestAsync -- Retrieve a person profile asynchronously");

		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		WebTarget webTarget = client.target(endpoint);
		
		RxObservableInvoker builder = webTarget.request()
				.rx(RxObservableInvoker.class);
		
		return builder.get()
				.map( res -> {
			return res.readEntity(Person.class);
			});
	}

}
