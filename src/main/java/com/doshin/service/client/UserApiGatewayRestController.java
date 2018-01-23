package com.doshin.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.doshin.service.client.feign.UserApiGatewayFeignController;

@RestController
public class UserApiGatewayRestController {
	
	private final Logger logger = LoggerFactory.getLogger(UserApiGatewayRestController.class);

	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	public UserApiGatewayRestController(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}



	@RequestMapping(method = RequestMethod.GET, value= "/userapigateway/{id}")
	String getUserById(@PathVariable String id) {
		logger.info("Userapigateway User service started for : "  +id );

		String st = restTemplate.exchange("http://userservice/user/{id}", HttpMethod.GET, null, String.class, id).getBody();
		logger.info("Userapigateway User service response : "  + st);
		return st;

	}

}
