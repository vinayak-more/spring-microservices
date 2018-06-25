package com.vinayak.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinayak.microservices.limitsservice.bean.LimitsConfiguration;
import com.vinayak.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimistConfiguration() {
		return new LimitsConfiguration(configuration.getMaxmimum(), configuration.getMinimum());
	}

}
