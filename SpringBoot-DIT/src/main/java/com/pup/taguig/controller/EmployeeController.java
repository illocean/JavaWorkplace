package com.pup.taguig.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pup.taguig.dto.EmployeeRequest;
import com.pup.taguig.dto.EmployeeResponse;
import com.pup.taguig.service.EmployeeService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired //yung container don nilalagay lahat ng object na naipon niya na may AutoWire
	private EmployeeService employeeService;
	
	/*
	//list of objects
	private List<EmployeeResponse> employees = 
			//"new" creates object
			new ArrayList<EmployeeResponse>(); */
	
	
	/*public EmployeeController() {
		employees.add(new EmployeeResponse(
							1, 
							"Phillip", 
							"Developer", 
							10000));
		
		employees.add(new EmployeeResponse(
							2, 
							"Kim", 
							"Developer", 
							10000));
	}*/

	@GetMapping
	public List<EmployeeResponse> getAllEmployees(){
		//calls the interface
		return employeeService.getAllEmployees();
	}
	
	
	/*
	//response type - object
	@GetMapping("/{studentNumber}/{name}")
	public EmployeeResponse getEmployeeById(
								@PathVariable String name, 
								@PathVariable("studentNumber") int id) {
		
		//loops the list of employees 
		for (EmployeeResponse employee: employees) {
			if(employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}*/
	
	
	@PostMapping
	public EmployeeResponse addEmployee(@RequestBody EmployeeRequest request) {

		return employeeService.addEmployee(request);
	}
}
