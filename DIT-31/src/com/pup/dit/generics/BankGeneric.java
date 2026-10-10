package com.pup.dit.generics;

/**
 * 09 - 26 - 2026
 * Class generics 
 */

class BankAccount <T>{
	//using <T> disadvantages: logic computations
	private T accountNumber;
	private double balance;
	
	//contructors - to make an object for main class 
	BankAccount(T accountNumber, double balance) {
		this.accountNumber = accountNumber; 
		this.balance = balance;
	}
	
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited: " + amount);
	}
	
	//getter no. 1
	public T getAccountNumber() {
		return accountNumber;
	}
	
	//getter no. 2 
	public double getBalance() {
		return balance;
	}
}

public class BankGeneric {
	public static void main(String[] args) {
		
		//why need diamond operator? (<>) so that automatically it can inherent "BankAccount<Integer>"
		//datatype what to feed to account number!
		BankAccount<Integer> account1 = new BankAccount<>(123, 1000.00);
		BankAccount<String> account2 = new BankAccount<>("Duvan", 5000.00);
		
		account1.deposit(5000.50);
		account2.deposit(1000.75);
		
		System.out.println("\nAccount: " + account1.getAccountNumber());
		System.out.println("Balance: " + account1.getBalance());
		
		System.out.println("\nAccount: " + account2.getAccountNumber());
		System.out.println("Balance: " + account2.getBalance());
	}
}
