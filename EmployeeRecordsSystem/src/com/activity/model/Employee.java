package com.activity.model;

//							@comparable interface to SORT the object
public class Employee implements Comparable<Employee> {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String department;
	private Double salary;

	public Employee(int employeeId, String firstName, String lastName, String department, Double salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", salary=" + salary + "]";
	}
	
	public int compareTo(Employee other) {
		return Double.compare(this.salary, other.salary);
	}
}
