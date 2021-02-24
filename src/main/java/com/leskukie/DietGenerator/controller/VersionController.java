package com.leskukie.DietGenerator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/version")
public class VersionController {
	@Value("${application.version}")
	private String applicationVersion;

	@GetMapping
	public String getVersion() {
		return this.applicationVersion;
	}
}
