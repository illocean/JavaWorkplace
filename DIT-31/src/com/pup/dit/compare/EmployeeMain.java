package com.pup.dit.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeMain {
	
	/*
	 * comparable = inside the class, ONe natural order
	 * comparator = outside the class, multiple custom rules
	 */

	public static void main(String [] args) {
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee(2, "AName", 3);
		Employee e2 = new Employee(3, "BName", 2);
		Employee e3 = new Employee(1, "Cname", 1);
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		Comparator<Employee> sortByAgeThenName = Comparator
				.comparing(Employee::getAge).reversed()
				.thenComparing(Employee::getName);
		
		Collections.sort(employees, sortByAgeThenName);
		
		//lambda expression 
		/*Collections.sort(employees, (a, b) -> {
			return Integer.compare(a.getId(), b.getId());
		});*/
	
		System.out.println(employees.toString());
		               
		/*for(Employee e: employees) {
			System.out.println(e); 
		}*/
	}
}
