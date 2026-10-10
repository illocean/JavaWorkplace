package com.pup.taguig.dto;

//dot = data transfer object

public class EmployeeResponse {

	//encapsulation OOP
	private Long id;
	private String name;
	private String position;
	private double salary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public EmployeeResponse(Long id, String name, String position, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public EmployeeResponse() {
		// TODO Auto-generated constructor stub
	}
}
