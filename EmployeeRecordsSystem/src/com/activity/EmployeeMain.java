package com.activity;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.activity.model.Employee;

public class EmployeeMain {
	static List<Employee> Employees = new ArrayList<>();

	public static void main(String[] args) {
		String[] options = { "1. add", 
				"2. view all", 
				"3. find the highest salary", 
				"4. average of all salary", 
				"5. exit" };
		int menu;
		do {
			
			menu = JOptionPane.showOptionDialog(null, 
					"MAIN MENU", 
					"menu", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, 
					null, 
					options, 
					options[0]);
			
			switch (menu) {
			case 0: 
				add(); 
				break;
			case 1: 
				view(); 
				break;
			case 2: 
				highestSalary(); break;
			case 3: 
				averageSalary(); break;
			case 4: 
				break;
			default: 
				JOptionPane.showConfirmDialog(null, "Error 1-5 lang");
			}
		} while (menu != 4);
	}

	public static void add() {
		int employeeId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee Id: "));
		String firstName = JOptionPane.showInputDialog("Enter frist name: ");
		String lastName = JOptionPane.showInputDialog("Enter last name: ");
		String department = JOptionPane.showInputDialog("Enter department: ");
		Double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: "));
		
		Employees.add(new Employee(employeeId, firstName, lastName, department, salary));
	}

	private static void view() {
		StringBuilder sb = new StringBuilder();
		
		for (Employee e : Employees) {
			sb.append(e.toString()).append("\n");
		}
		
		JOptionPane.showMessageDialog(null, sb.length() == 0 ? "No employees" : sb);
	}

	private static void highestSalary() {
		if (Employees.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "No employees");
			return;
		}
		
		Employee highest = Employees.get(0);
		
		for (Employee e : Employees)
			if (e.getSalary() > highest.getSalary()) highest = e;
		JOptionPane.showMessageDialog(null, "Highest salary:\n" + highest);
	}

	private static void averageSalary() {
		if (Employees.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No employees");
			return;
		}
		
		double total = 0;
		
		for (Employee e : Employees) total += e.getSalary();
		JOptionPane.showMessageDialog(null, "Average salary: " 
					+ String.format("%.2f", total / Employees.size()));
	}
}
