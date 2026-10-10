package com.pup.taguig.dto;

public class StudentResult {

	private String name;
	private double grade;
	private String remarks;
	private String status;

	public StudentResult(String name, double grade, String remarks, String status) {
		super();
		this.name = name;
		this.grade = grade;
		this.remarks = remarks;
		this.status = status;
	}

	public StudentResult() {
		// default constructor
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
