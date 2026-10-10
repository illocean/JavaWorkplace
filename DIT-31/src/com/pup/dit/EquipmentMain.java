package com.pup.dit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

import javax.swing.JOptionPane;

import com.pup.dit.exception.InsufficientEquipmentException;
import com.pup.dit.generics.EquipmentRegistry;
import com.pup.dit.model.Equipment;

public class EquipmentMain {

	private static final EquipmentRegistry<Equipment> REGISTRY
			= new EquipmentRegistry<>();

	private static final String MENU
			= "=====================================\n"
			+ "   SCHOOL EQUIPMENT BORROWING SYSTEM\n"
			+ "=====================================\n\n"
			+ "1. Add Equipment\n"
			+ "2. View All Equipment\n"
			+ "3. Search Equipment\n"
			+ "4. Borrow Equipment\n"
			+ "5. Return Equipment\n"
			+ "6. View Available Equipment\n"
			+ "7. Exit";

	public static void main(String[] args) {

		boolean running = true;

		while (running) {
			try {
				switch (Integer.parseInt(ask(MENU, "Main Menu").trim())) {

					case 1:
						addEquipment();
						break;

					case 2:
						viewAllEquipment();
						break;

					case 3:
						searchEquipment();
						break;

					case 4:
						borrowEquipment();
						break;

					case 5:
						returnEquipment();
						break;

					case 6:
						viewAvailableEquipment();
						break;

					case 7:
						running = false;
						break;

					default:
						error("Choice must be a number from 1 to 7.");
				}

			} catch (CancellationException e) {
				// the dialog was closed instead of answered
				running = false;

			} catch (NumberFormatException e) {
				error("Invalid input. Enter a number only.");

			} catch (InsufficientEquipmentException e) {
				error("Borrowing Failed:\n"
						+ e.getMessage()
						+ "\n\nBorrowing process completed.");

			} catch (IllegalArgumentException e) {
				error(e.getMessage());

			} catch (Exception e) {
				// never an empty catch - report it and keep the menu alive
				error("Something went wrong: " + e);
			}
		}

		info("Goodbye.");
	}

	private static void addEquipment() {

		int id = askInt("Equipment ID:");
		String name = ask("Equipment Name:", "Add Equipment").trim();
		int quantity = askInt("Quantity:");

		if (name.isEmpty()) {
			throw new IllegalArgumentException("Equipment name is required.");
		}

		if (id <= 0 || quantity <= 0) {
			throw new IllegalArgumentException("ID and quantity must be greater than 0.");
		}

		if (REGISTRY.findById(id) != null) {
			throw new IllegalArgumentException("Equipment ID " + id + " already exists.");
		}

		REGISTRY.add(new Equipment(id, name, quantity));

		info("Equipment successfully added.");
	}

	private static void viewAllEquipment() {

		if (REGISTRY.isEmpty()) {
			info("There is no equipment yet. Use option 1 to add some.");
			return;
		}

		info(EquipmentRegistry.toText(REGISTRY.all()));
	}

	private static void searchEquipment() {

		String key = ask("Search by ID or by name:", "Search Equipment")
				.trim()
				.toLowerCase();

		if (key.isEmpty()) {
			throw new IllegalArgumentException("Type something to search for.");
		}

		List<Equipment> hits = new ArrayList<>();

		for (Equipment item : REGISTRY.all()) {
			boolean idHit = String.valueOf(item.getId()).equals(key);
			boolean nameHit = item.getName().toLowerCase().contains(key);

			if (idHit || nameHit) {
				hits.add(item);
			}
		}

		if (hits.isEmpty()) {
			info("No equipment matched \"" + key + "\".");
			return;
		}

		info(EquipmentRegistry.toText(hits));
	}

	/**
	 * @throws InsufficientEquipmentException not enough stock - caught in main
	 */
	private static void borrowEquipment() throws InsufficientEquipmentException {

		Equipment item = require(askInt("Equipment ID:"));
		int quantity = askInt("Quantity to Borrow:");

		item.borrow(quantity);

		info("Borrowing successful.\n\nRemaining "
				+ item.getName() + ": " + item.getAvailable());
	}

	private static void returnEquipment() {

		Equipment item = require(askInt("Equipment ID:"));
		int quantity = askInt("Quantity to Return:");

		item.giveBack(quantity);

		info("Return successful.\n\nAvailable "
				+ item.getName() + ": " + item.getAvailable());
	}

	private static void viewAvailableEquipment() {

		List<Equipment> available = new ArrayList<>();

		for (Equipment item : REGISTRY.all()) {
			if (item.getAvailable() > 0) {
				available.add(item);
			}
		}

		if (available.isEmpty()) {
			info("No equipment is available for borrowing.");
			return;
		}

		info(EquipmentRegistry.toText(available));
	}

	/**
	 * search helper: look up the record, or report that the ID is unknown
	 */
	private static Equipment require(int id) {

		Equipment item = REGISTRY.findById(id);

		if (item == null) {
			throw new IllegalArgumentException("No equipment with ID " + id + ".");
		}

		return item;
	}

	/**
	 * ask helper: null comes back when the dialog is closed, so the
	 * program stops instead of reading null
	 */
	private static String ask(String message, String title) {

		String input = JOptionPane.showInputDialog(
					null,
					message,
					title,
					JOptionPane.QUESTION_MESSAGE);

		if (input == null) {
			throw new CancellationException();
		}

		return input;
	}

	/**
	 * number helper: keeps re asking until the input really is a whole
	 * number, so invalid input never kills the program
	 */
	private static int askInt(String message) {

		while (true) {
			try {
				return Integer.parseInt(ask(message, "Input").trim());
			} catch (NumberFormatException e) {
				error("Invalid input. Enter a whole number only.");
			}
		}
	}

	private static void info(String message) {

		JOptionPane.showMessageDialog(
				null,
				message,
				"Equipment System",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static void error(String message) {

		JOptionPane.showMessageDialog(
				null,
				message,
				"Input Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
