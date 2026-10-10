package com.pup.dit.generics;

import javax.swing.JOptionPane;

/**
 * 09 - 26 - 2026
 * 
 * Notes.java
 * Generics file no. 2
 * 
 */
public class Generics {

	public static void main(String[] args) {
		/*Box <String> box = new Box<>();
		
		String userInput = JOptionPane.showInputDialog("Enter a fruit: ");
		
		if (userInput != null && !userInput.trim().isEmpty()) {
			if (userInput.matches("^[a-zA-Z\\s]+$")) {
				box.setItem(userInput);
				
				JOptionPane.showMessageDialog(null, "item in box is: " + box.getItem());
			} else {
				JOptionPane.showConfirmDialog(null, "invalid input", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Input is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
		}*/
		
		String userInput = JOptionPane.showInputDialog("Enter a number: ");
		
		if (userInput != null && !userInput.trim().isEmpty()) { 
			try {
				//different to "int" = cannot be null, "Integer" is object (can be null)
				Integer userNumber = Integer.parseInt(userInput);
				Integer result = Box.method(userNumber);
				
				JOptionPane.showMessageDialog(null, "Inputted number is: " + result);
				
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Input is mandatory");
		}
	}
}
