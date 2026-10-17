package com.pup.taguig.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pup.taguig.dto.EmployeeRequestDTO;
import com.pup.taguig.dto.EmployeeResponseDTO;
import com.pup.taguig.model.Employee;
import com.pup.taguig.repository.EmployeeRepository;
import com.pup.taguig.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository repository;
	
	private List<EmployeeResponseDTO> employees = 
							//"new" creates object
							new ArrayList<EmployeeResponseDTO>();

	EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	public List<EmployeeResponseDTO> getAllEmployees() {
		return employees;
	}

	@Override
	public EmployeeResponseDTO addEmployee(EmployeeRequestDTO request) {
		
		EmployeeResponseDTO response = convertRequestToResponse(request);
		//employees.add(response);
		
		
		Employee employee = new Employee(request.getName(),
										request.getPosition(),
										request.getSalary());
		repository.save(employee);
		return response;
	}
	
	private EmployeeResponseDTO convertRequestToResponse(EmployeeRequestDTO request) {
		EmployeeResponseDTO response = new EmployeeResponseDTO();
		
		response.setId(request.getId());
		response.setName(request.getName());
		response.setPosition(request.getPosition());
		response.setSalary(request.getSalary());
		
		return response;
	}
	

	@Override
	public EmployeeResponseDTO getEmployeeById(Long id) {
		
		//it retrieves data from database   
		//                          select * from employees where id = ?
		Employee employee = repository.findById(id).orElseThrow(()->
													new RuntimeException("Employee not Found " + id)); 
		
		//converts object 'employee' to EmployeeResponseDTO'
		EmployeeResponseDTO response = convertEmployeeToResponse(employee);
		return response;
	}
	
	private EmployeeResponseDTO convertEmployeeToResponse(Employee employee) {

		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		System.out.println(employee.toString());
		
		return null;
	}
}
