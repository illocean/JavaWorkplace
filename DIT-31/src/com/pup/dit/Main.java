package com.pup.dit;

//import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * This class will compute the Grades
 * 
 * @author andador
 * @version 1.0
 * @code
 * 
 * Date              NAME
 * 08/27/2026        Kim Phillip Andador
 * 08/28/2026        Sir Aj
 */

public class Main {
  public static void main(String[] args) {
//  TODO Auto-generated method stub

/*  Scanner input = new Scanner(System.in);

    System.out.print("Enter Student Name: ");
    String name = input.nextLine();

    System.out.println("Name: " + name);*/
//  ------------------------------------------------------------
//  String name = JOptionPane.showInputDialog("Enter name: ");
//  JOptionPane.showMessageDialog(null, "Name: " + name);

//  System.out.println("Name: " + name);
    
    double firstSemesterGrade = Double.parseDouble(JOptionPane.showInputDialog("Enter first semester grade: "));
    double secondSemesterGrade = Double.parseDouble(JOptionPane.showInputDialog("Enter second semester grade: "));
    																	
    double average = (firstSemesterGrade + secondSemesterGrade) / 2;
    
    JOptionPane.showMessageDialog(null, "Average: " + average);
    
    averageOfTwoNumbers(1,2);
    
  }
  
  /**
   * Compute Average of the two numbers
   * @param param1
   * @param param2
   * @return
   */
  public static double averageOfTwoNumbers(double param1, double param2) {
	  double average = 0;
			
	  average = (param1 + param2) / 2;
	  
	  return average;
  }
}
