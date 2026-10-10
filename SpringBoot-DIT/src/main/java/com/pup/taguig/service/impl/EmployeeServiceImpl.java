package com.pup.taguig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pup.taguig.dto.EmployeeRequest;
import com.pup.taguig.dto.EmployeeResponse;
import com.pup.taguig.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private List<EmployeeResponse> employees = 
			//"new" creates object
			new ArrayList<EmployeeResponse>();
	
	public List<EmployeeResponse> getAllEmployees() {
		return employees;
	}

	@Override
	public EmployeeResponse addEmployee(EmployeeRequest request) {
		
		EmployeeResponse response = convertRequestToResponse(request);
		employees.add(response);
		
		return response;
	}
	
	private EmployeeResponse convertRequestToResponse(EmployeeRequest request) {
		EmployeeResponse response = new EmployeeResponse();
		
		response.setId(request.getId());
		response.setName(request.getName());
		response.setPosition(request.getPosition());
		response.setSalary(request.getSalary());
		
		return response;
	}
}
