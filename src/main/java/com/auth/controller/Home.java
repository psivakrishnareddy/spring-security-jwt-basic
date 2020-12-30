package com.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("")
public class Home {

	@GetMapping("/welcome")
	public String welcome() {
		
		return "This is a Private Route";
	}
}
