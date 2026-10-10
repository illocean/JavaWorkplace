package com.pup.taguig.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //handle web request or (API) [REST WEB SERVICE - nagrereturn ng JSON
@RequestMapping("/user") //equivalent to route group
public class UserController {
	
	@GetMapping //in url will execute the getUser func (http://localhost:8080/user)
	public String getUser() {
		return "Sample Return";
	}
	
	@GetMapping("/status")
	public String getUserStatus() {
		return "User Status";
	}
}
