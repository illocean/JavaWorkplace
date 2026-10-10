package com.pup.taguig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pup.taguig.dto.EmployeeRequest;
import com.pup.taguig.dto.EmployeeResponse;
import com.pup.taguig.model.Employee;
import com.pup.taguig.repository.EmployeeRepository;
import com.pup.taguig.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository repository;
	
	private List<EmployeeResponse> employees = 
							//"new" creates object
							new ArrayList<EmployeeResponse>();

	EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	public List<EmployeeResponse> getAllEmployees() {
		return employees;
	}

	@Override
	public EmployeeResponse addEmployee(EmployeeRequest request) {
		
		EmployeeResponse response = convertRequestToResponse(request);
		//employees.add(response);
		
		
		Employee employee = new Employee(request.getName(),
										request.getPosition(),
										request.getSalary());
		repository.save(employee);
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
