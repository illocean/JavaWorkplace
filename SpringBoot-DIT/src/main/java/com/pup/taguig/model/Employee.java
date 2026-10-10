package com.pup.taguig.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Employee {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 100, unique = true)
	private String position;
	
	@Column(nullable = false)
	private float salary;
	
	public Employee(String name, String position, float salary) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public Employee() {
		
	}

}
