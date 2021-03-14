package com.leskukie.dietgenerator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versions")
public class VersionController {
	@Value("${application.version}")
	private String applicationVersion;

	@GetMapping
	public String getVersion() {
		return this.applicationVersion;
	}
}
