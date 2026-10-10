package com.pup.taguig.controller;

//or just use import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
	
	@GetMapping //query param
	public int add(@RequestParam int num1, 
								 int num2, 
								 @RequestParam String operation) {
								 //add, subtract, multiply, divide 
		
		//do not use "operation == "add""
		//to compare string must be:
		if (operation.equalsIgnoreCase("add")) {
			return num1 + num2;
		} else if (operation.equalsIgnoreCase("subtract")) {
			return num1 -  num2;
		} else if (operation.equalsIgnoreCase("multiply")) {
			return num1 * num2;
		} else if (operation.equalsIgnoreCase("divide")) {
			return num1 / num2;
		}
		
		return 0;
	}
}
