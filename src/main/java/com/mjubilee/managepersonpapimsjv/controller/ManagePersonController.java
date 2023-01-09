package com.mjubilee.managepersonpapimsjv.controller;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mjubilee.managepersonpapimsjv.client.ManagePersonClientImpl;
import com.mjubilee.managepersonpapimsjv.configuration.EndpointConfiguration;
import com.mjubilee.managepersonpapimsjv.model.Person;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import rx.Observable;

import org.springframework.web.context.request.async.DeferredResult;
import java.time.Duration;


@RestController
public class ManagePersonController {
	Logger log = LoggerFactory.getLogger(ManagePersonController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private EndpointConfiguration endpointConfiguration;
	
	@Autowired
	ManagePersonClientImpl managePersonClient;
	
	@GetMapping(path = "/persons")
	public ResponseEntity<Person> retrievePersonProfile(@RequestParam String id) {

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- retrievePersonProfile -- Retrieve a person profile");
		
		String endpoint = endpointConfiguration.getPersonSapi() + "?id=" + id;	
		return new ResponseEntity<>(managePersonClient.submitRequest(endpoint, new GenericType<Person>() {}), HttpStatus.OK);
	}
	
	@GetMapping(path = "/persons/async")
	public DeferredResult< ResponseEntity<Person> > retrievePersonProfileAsync(@RequestParam String id) {

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- retrievePersonProfileAsync -- Retrieve a person profile");
		
		Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
		DeferredResult< ResponseEntity<Person> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		String endpoint = endpointConfiguration.getPersonSapi() + "?id=" + id;	
		
		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		
		Observable<Person> response = client.target(endpoint)
		.request()
		.rx(RxObservableInvoker.class)
		.get().map( res -> {
			return res.readEntity(Person.class);
			});
		
		response.subscribe( (apiResponse) ->{
			System.out.println(apiResponse);
			
			ResponseEntity<Person> responseData = new ResponseEntity<Person>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
			deferredResult::setErrorResult //OnError
		);

		return deferredResult; //new ResponseEntity<Person>(person,HttpStatus.OK);
	}
}
