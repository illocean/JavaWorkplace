package com.pup.dit;

import javax.swing.JOptionPane;

/*
 * This calculates simple application
 * @author andador
 * @version 1.0
 */
public class Calculator {
  public static void main(String[] args) {
    
	//Calculate calculate = (new Calculate);
	  
	String[] options = {
			"Addition",
			"Substraction",
			"Product",
			"Division"
	};
	
	int choice = JOptionPane.showOptionDialog(
			null, 
			"Choice an operation", 
			"Simple Calculator", 
			JOptionPane.DEFAULT_OPTION, 
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]);
			
    double number1 = Double.parseDouble(JOptionPane.showInputDialog("Enter first number: "));
    double number2 = Double.parseDouble(JOptionPane.showInputDialog("Enter second number: "));
  
    double result = 0;
    
    if (choice == 0) {
    	result = sum(number1, number2);
    
    } else if (choice == 1){
    	result = difference(number1, number2);
    	
    } else if (choice == 2){
    	result = product(number1, number2);
    	
    } else if (choice == 3){
    	result = qoutient(number1, number2);
    	
    } else {
    	JOptionPane.showMessageDialog(null, "No operations selected");
    }
    JOptionPane.showConfirmDialog(null, result);
    
    sum(choice, choice); //ctrl + space (auto-complete)
  }

  /**
   * Sum of the two numbers
   * @param param1
   * @param param2
   * @return
   */
  private static double sum(double param1, double param2) {
    double sum = 0;

    sum = param1 + param2;

    return sum;
  }
  
  private static int sum(int param1, double param2) {
	  int sum = 0;
	  
	  sum = (int) (param1 + param2);
	  
	  return sum;
  }

  /**
   * Difference of the two numbers
   * @param param1
   * @param param2
   * @return
   */
  private static double difference(double param1, double param2) {
    double difference = 0;

    difference = param1 - param2;

    return difference;
  }

  /**
   * Product of the two numbers
   * @param param1
   * @param param2
   * @return
   */
  private static double product(double param1, double param2) {
    double product = 0;

    product = param1 * param2;

    return product;
  }

  /**
   * Divide two numbers
   * @param param1
   * @param param2
   * @return
   */
  private static double qoutient(double param1, double param2) {
    double qoutient = 0;

    if (param2 == 0) {
      JOptionPane.showMessageDialog(null, "cannot be divide by 0", "Error", JOptionPane.ERROR_MESSAGE);

      return 0;
    }

    qoutient = param1 / param2;

    return qoutient;
  }
}
