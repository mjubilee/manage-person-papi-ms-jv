package com.mjubilee.managepersonpapimsjv.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "endpoint")
@Component
public class EndpointConfiguration {
	
	private String personSapi;

	public String getPersonSapi() {
		return personSapi;
	}

	public void setPersonSapi(String personSapi) {
		this.personSapi = personSapi;
	}
	
	
}
