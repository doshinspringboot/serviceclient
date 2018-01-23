package com.doshin.service.client.feign;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserApiGatewayFeignController {
	
	UserFeignClient feignClient;

	public UserApiGatewayFeignController(UserFeignClient feignClient) {
		super();
		this.feignClient = feignClient;
	}
	
	@HystrixCommand(fallbackMethod = "badResponse")
	@RequestMapping(method = RequestMethod.GET, value= "/feignuserapigateway/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	String getUserById(@PathVariable String id) {
		return feignClient.getByUserId(id);
	}
	
	String badResponse(@PathVariable String id) {
		return "ko";
	}

}
