package com.cdac.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://web-xi-orcin.vercel.app")
public class HealthcheckController {
	
	@GetMapping("/healthcheck")
	public String healthcheck() {
		return "Ok";
	}

}
