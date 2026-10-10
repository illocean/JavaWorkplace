package com.pup.dit.generics;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 09 - 26 - 2026
 * 
 * Notes.java
 * Wildcards 
 * 
 */
public class Dog {
	public static void main(String[] args) {
		ArrayList<String> dogNames = new ArrayList<String>(); 
		ArrayList<String> dogBreeds = new ArrayList<String>(); 
		ArrayList<Double> dogPrices = new ArrayList<Double>(); 
		ArrayList<Integer> dogAges = new ArrayList<Integer>(); 
		
		int choice;
	
		do {
			String name = JOptionPane.showInputDialog("Enter dog name: ");
			dogNames.add(name);
			
			String breed = JOptionPane.showInputDialog("Enter dog breed: ");
			dogBreeds.add(breed);
			
			double price = Double.parseDouble(JOptionPane.showInputDialog("Enter dog price of " + name));
			dogPrices.add(price);
			
			int age = Integer.parseInt(JOptionPane.showInputDialog("Enter dog age of " + name));
			dogAges.add(age);
			
			choice = JOptionPane.showConfirmDialog(null, "add more?", "add more?", JOptionPane.YES_NO_OPTION);
		} while (choice == JOptionPane.YES_OPTION);
		
		unbounded(dogNames, "names: ");
		unbounded(dogAges, "ages: ");
		unbounded(dogPrices, "prices: ");
		unbounded(dogBreeds, "breeds: ");
	}
	
	//outbounded + wildcard generic
	public static void unbounded (List<?> items, String title) {
		String result = "";
		for (Object item : items) {
			result = result + item + "\n";
		}
		JOptionPane.showMessageDialog(null, "title: " + "\n" + result);
	}
}
