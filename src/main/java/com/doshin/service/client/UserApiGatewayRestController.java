package com.doshin.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserApiGatewayRestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	public UserApiGatewayRestController(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}



	@RequestMapping(method = RequestMethod.GET, value= "/userapigateway/{id}")
	String getUserById(@PathVariable String id) {
		return restTemplate.exchange("http://userservice/user/{id}", HttpMethod.GET, null, String.class, id).getBody();
	}

}
