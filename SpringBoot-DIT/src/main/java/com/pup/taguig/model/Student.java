package com.pup.taguig.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Student {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false, length = 100, unique = true)
	private String lastName;
	
	@Column(nullable = false)
	private float midtermGrade;
	
	@Column(nullable = false)
	private float finalGrade;
	
	public Student(Long id, String firstName, String lastName, float midtermGrade, float finalGrade) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midtermGrade = midtermGrade;
		this.finalGrade = finalGrade;
	}
	
	public Student() {
		
	}

}
