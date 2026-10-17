package com.pup.taguig.service;

import java.util.List;

import com.pup.taguig.dto.EmployeeRequestDTO;
import com.pup.taguig.dto.EmployeeResponseDTO;

/*
 * abstraction: kung ano nailagay sa rito,
 * then it must be implemented in EmployeeServiceImpl\
 * 
 * interface is an Abstractions
*/
public interface EmployeeService {
	
	public List<EmployeeResponseDTO> getAllEmployees();
	
	public EmployeeResponseDTO addEmployee(EmployeeRequestDTO request);
	
	//mandatory in implementation if declared here
	public EmployeeResponseDTO getEmployeeById(Long id);

}
