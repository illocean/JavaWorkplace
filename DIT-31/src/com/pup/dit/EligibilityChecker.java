package com.pup.dit;

import javax.swing.JOptionPane;

public class EligibilityChecker {

	/*
	 * Check eligibility of a Student for Enrollment
	 * @author andador
	 * 
	 * 9/5/2026
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int studentNumber = Integer.parseInt(JOptionPane.showInputDialog("Input Student Number: "));
		int studentAge = Integer.parseInt(JOptionPane.showInputDialog("Input Age: "));
		double studentGrade = Double.parseDouble(JOptionPane.showInputDialog("Input Grade: "));
	    int requirements = JOptionPane.showConfirmDialog(null, 
	    												"Do you have a requirements",
	    												"Requirements",
	    												JOptionPane.YES_NO_OPTION);
	
//	    boolean result = isEligible(studentAge, studentGrade, requirements, studentNumber);
	    isEligible(studentAge, studentGrade, requirements, studentNumber);
//	    JOptionPane.showMessageDialog(null, "Student: " + studentNumber + "\n Eligibility: " + result);
	}
	
	/**
	 * For checking if the Student is eligible to enroll
	 * @param studentAge age of the student
	 * @param studentGrade's Final grade
	 * @param requirements true = requirements are complete
	 * @return
	 */
	public static boolean isEligible(int studentAge, double studentGrade, int requirements, int studentNumber) {
		
		//maximum of 200 per line
		if (studentAge >= 18 
				&& studentGrade > 75 
				&& requirements == JOptionPane.YES_OPTION) {
		
			JOptionPane.showMessageDialog(null, "Student: " + studentNumber + "\n Is eligible");
			return true;
		}
		
		JOptionPane.showMessageDialog(null, "Student: " + studentNumber + "\n Is not eligible");
		return false;
	}

}
