package com.pup.dit.model;

import javax.swing.JOptionPane;

import com.pup.dit.exception.InsufficientBalanceException;

/*
 * 09/19/26
 * 
 * is used to instantiation of object 
 */
public class Bank {
	private double balance;
	
	/**
	 * Encapsulation
	 * constructor
	 * @param balance
	 * 
	 * in "this.balance = balance;":
	 * 
	 * + this.balance pertains to Global
	 * + and '= balance is to its parameter'
	 */
	public Bank (Double balance) {
		this.balance = balance;
	}
	
	public void Withdraw(Double amount) throws IllegalArgumentException, InsufficientBalanceException {
		
		if (amount <= 0) {
			throw new IllegalArgumentException(
						"Amount must be greater than 0");
		}
		
		
		//custom exception: for business perspective
		if (amount > balance) throw new InsufficientBalanceException(
				"Insufficient balance \n"
				+ "Current balance: "
				+ balance);
		
		balance = balance - amount;
		//balance -= amount;
		JOptionPane.showMessageDialog(
				null,
				"Remaining balance: " 
				+ balance,
				"Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
