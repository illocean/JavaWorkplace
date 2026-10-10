package com.pup.dit;

import javax.swing.JOptionPane;

import com.pup.dit.exception.InsufficientBalanceException;
import com.pup.dit.model.Bank;

public class BankMain2 {

	public static void main(String[] args) {
		
		double balance = 1000.00;

		try {
			String amount = JOptionPane.showInputDialog(
											null,
											"Current balance is: "
											+ "\n \n"
											+ "Enter amount to withdraw",
											"ATM Withdrawal",
											JOptionPane.QUESTION_MESSAGE);	
			if (amount == null) {
				throw new Exception("Transaction cancelled by the user");
			}
			
			//balance = balance - Double.parseDouble(amount);

			// instantiation of class to object
			Bank bank = new Bank(balance);
			
			bank.Withdraw(Double.parseDouble(amount));
			 
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(
							null,
							"Invalid input",
							"Input Error",
							JOptionPane.ERROR_MESSAGE);
		
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(
							null,
							"Invalid amount",
							"Input Error",
							JOptionPane.ERROR_MESSAGE);
			
		} catch (InsufficientBalanceException e) {
			JOptionPane.showMessageDialog(
							null,
							e.getMessage(), 
							"Input error: ", 
							JOptionPane.ERROR_MESSAGE);
			
		} catch (Exception e) {

		} finally {
			JOptionPane.showMessageDialog(
							null,
							"Thank you for using the ATM",
							"ATM System",
							JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
