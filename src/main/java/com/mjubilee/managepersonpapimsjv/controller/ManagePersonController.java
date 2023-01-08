package com.mjubilee.managepersonpapimsjv.controller;

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

import jakarta.ws.rs.core.GenericType;


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
	public ResponseEntity<Person> retrievePersonProfileAsync(@RequestParam String id) {

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- retrievePersonProfile -- Retrieve a person profile");
		
		
		return new ResponseEntity<Person>(new Person(),HttpStatus.OK);
	}
}
