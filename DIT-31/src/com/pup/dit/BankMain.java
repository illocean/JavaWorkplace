package com.pup.dit;

import javax.swing.JOptionPane;

/**
 * Group 3: try catch (demo)
 * 09 - 19 - 2026
 */
public class BankMain {
  public static void main(String[] args) throws ArithmeticException, IndexOutOfBoundsException {
    double result = 0;

    String[] subjects = {"English", "Bisaya", "Tagalog"};

    String subject = "Error";

    /**
     * Try block
     * type "try" ctrl + space
     */
    try {
      int index = Integer.parseInt(JOptionPane.showInputDialog(
    		  									null, 
    		  									"Choice a subject index: "));

      int firstNumber = Integer.parseInt(JOptionPane.showInputDialog(
    		  									null,
    		  									"Enter first number: "));

      int secondNumber =
          Integer.parseInt(JOptionPane.showInputDialog(
        		  								null,
        		  								"Enter second number: "));

      result = firstNumber / secondNumber;

      subject = subjects[index];

      // its like an if-else if another 'catch' caught then proceeds to 'finally'
    } catch (ArithmeticException e) {
      JOptionPane.showMessageDialog(null, "Cannot be divided by zero");

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Hoy mag-numbers ka lang ngani");
      
      /**
       * try block
       * "Exception e" is a mother of all exception (so all exception is caught)
       * you can change "e" its a variable only
       *
       * do not say the exact error on user side (UI) always  - contact the admin
       */
    } catch (Exception e) {
      // TODO: handle exception

      JOptionPane.showMessageDialog(null, "contact the admin ^^");

    } finally {
      JOptionPane.showMessageDialog(null, "result " + result + "\nSubject: " + subject);
    }
  }
}
