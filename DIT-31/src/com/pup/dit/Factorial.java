package com.pup.dit;

import javax.swing.JOptionPane;

public class Factorial {

	public static void main(String[] args) {
		
		//local number within public static void main
		int inputNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter a positive number: "));
	
		int result = factorial(inputNumber);
		
		JOptionPane.showMessageDialog(null, "Factorial of " + inputNumber + "\nis: " + result);
	}
	
	/**
	 * Factorial of a positive number
	 * @param number is the argument number
	 * @return
	 */
	private static int factorial(int number) {
		
		if (number == 1) {
			return 1;
		}
		
		return number * factorial(number -1);
	} 
}
