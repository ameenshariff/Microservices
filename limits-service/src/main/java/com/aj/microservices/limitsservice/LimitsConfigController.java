package com.aj.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aj.microservices.limitsservice.bean.LimitConfig;

@RestController
public class LimitsConfigController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitConfig retrieveLimitsConfig() {
		return new LimitConfig(config.getMaximum(), config.getMinimum());
	}

}
