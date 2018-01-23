package com.doshin.service.client.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserApiGatewayFeignController {
	
	private final Logger logger = LoggerFactory.getLogger(UserApiGatewayFeignController.class);

	
	UserFeignClient feignClient;

	public UserApiGatewayFeignController(UserFeignClient feignClient) {
		super();
		this.feignClient = feignClient;
	}
	
	@HystrixCommand(fallbackMethod = "badResponse")
	@RequestMapping(method = RequestMethod.GET, value= "/feignuserapigateway/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	String getUserById(@PathVariable String id) {
		logger.info("Feignuserapigateway User service started for : "  +id );
		String st = feignClient.getByUserId(id);
		logger.info("Feignuserapigateway User service response : "  + st);
		return st;
	}
	
	String badResponse(@PathVariable String id) {
		return "ko";
	}

}
