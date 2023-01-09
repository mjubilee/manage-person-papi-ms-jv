package com.mjubilee.managepersonpapimsjv.deferred;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.mjubilee.managepersonpapimsjv.controller.ManagePersonController;
import com.mjubilee.managepersonpapimsjv.model.Person;

import rx.Observable;

public class ManagePersonDeferred {
	static Logger log = LoggerFactory.getLogger(ManagePersonController.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public static DeferredResult< ResponseEntity<Person> > deferResponse( Observable <Person> observable ) {
		log.info( "ManagePersonDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<Person> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<Person> responseData = new ResponseEntity<Person>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
}
