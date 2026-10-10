package com.pup.taguig.service;

import java.util.List;

import com.pup.taguig.dto.EmployeeRequest;
import com.pup.taguig.dto.EmployeeResponse;

/*
 * abstraction: kung ano nailagay sa rito,
 * then it must be implemented in EmployeeServiceImpl\
 * 
 * interface is an Abstractions
*/
public interface EmployeeService {
	
	public List<EmployeeResponse> getAllEmployees();
	
	public EmployeeResponse addEmployee(EmployeeRequest request);

}
