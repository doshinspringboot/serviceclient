package com.doshin.service.client;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RateLimitingZuulFilter extends ZuulFilter {
	
	private final RateLimiter rateLimiter = RateLimiter.create(11.0/1.0);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 100;
	}
	
	@Override
	public Object run() {
		try {
			RequestContext requestContext = RequestContext.getCurrentContext();
			HttpServletResponse httpServletResponse = requestContext.getResponse();
			if(!this.rateLimiter.tryAcquire()) {
				httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				httpServletResponse.getWriter().append(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				requestContext.setSendZuulResponse(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
