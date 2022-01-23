package com.springsecurity.basics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

	// Available for everyone
	@GetMapping("/")
	public String helloWorld() {
		return "<h1>Hello World</h1>";
	}
	
	// Available only for USER and ADMIN roles
	@GetMapping("/user")
	public String helloUser() {
		return "<h1>Hello User</h1>";
	}
	
	// Available only for ADMIN roles
	@GetMapping("/admin")
	public String helloAdmin() {
		return "<h1>Hello Admin</h1>";
	}
}
