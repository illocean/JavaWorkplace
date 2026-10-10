package com.pup.dit.model;

import com.pup.dit.exception.InsufficientEquipmentException;

/**
 * 10 - 03 - 2026
 * SCHOOL EQUIPMENT BORROWING SYSTEM
 *
 * One record of the inventory. It owns its own rules, so the
 * quantities can never go negative no matter who calls it.
 *
 * Encapsulation: 'quantity' is the total stock, 'borrowed' is how much
 * of it is currently out, and available = total - out.
 */
public class Equipment {

	private final int id;
	private final String name;
	private final int quantity;
	private int borrowed;

	public Equipment(int id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.borrowed = 0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getBorrowed() {
		return borrowed;
	}

	public int getAvailable() {
		return quantity - borrowed;
	}

	/**
	 * @throws IllegalArgumentException qty is not positive - logic error, unchecked
	 * @throws InsufficientEquipmentException qty is valid but the stock is short
	 */
	public void borrow(int qty) throws InsufficientEquipmentException {

		if (qty <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than 0.");
		}

		if (qty > getAvailable()) {
			throw new InsufficientEquipmentException("Not enough " + name + " available.");
		}

		borrowed += qty;
	}

	/**
	 * @throws IllegalArgumentException qty is not positive, or more than
	 * 				 was actually borrowed - an invalid return transaction
	 */
	public void giveBack(int qty) {

		if (qty <= 0 || qty > borrowed) {
			throw new IllegalArgumentException(
					"Invalid return: only " + borrowed + " " + name + " "
					+ (borrowed == 1 ? "is" : "are") + " currently borrowed.");
		}

		borrowed -= qty;
	}

	@Override
	public String toString() {
		return "ID: " + id
				+ "\nName: " + name
				+ "\nQuantity: " + quantity
				+ "\nAvailable: " + getAvailable();
	}

}
