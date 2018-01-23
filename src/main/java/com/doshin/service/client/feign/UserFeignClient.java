package com.doshin.service.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface UserFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value= "/user/{id}")
	String getByUserId(@PathVariable("id") String id);

}
