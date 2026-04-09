package com.guestmanagement.service;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class is used to handle all user input operations
 * in the Paying Guest Management System.
 * It provides methods to read integer, text, email,
 * phone number, password, double values, and menu choices.
 *
 * OOP Concepts Used :
 * - Class
 * - Static Methods
 * - Encapsulation
 * - Input Validation
 * - Loops and Conditional Statements
 *
 * ============================================================
 */

import java.util.Scanner;

// Class
public class Input {

	// Static object / Scanner class object creation

	static Scanner sc = new Scanner(System.in);

	// Static method / read integer input
	public static int readInt(String prompt) {
		System.out.print(prompt);

		// While loop / validation
		while (!sc.hasNextInt()) {
			System.out.println("  \u26A0 Enter a valid number!");
			sc.next();
			System.out.print(prompt);
		}
		return sc.nextInt();
	}

	// Static method / read text input
	public static String readText(String prompt) {
		System.out.print(prompt);

		String text = sc.nextLine();

		// Conditional statement
		if (text.isEmpty()) {
			text = sc.nextLine();
		}

		return text.trim();
	}

	// Static method / read valid email
	public static String readEmail(String prompt) {
		while (true) {
			System.out.print(prompt);
			String email = sc.nextLine().trim();

			// Conditional statement
			if (email.isEmpty()) {
				System.out.println("  \u26A0 Email cannot be empty!");
				continue;
			}

			if (email.contains("@") && email.contains(".")) {
				return email;
			}

			System.out.println("  \u26A0 Enter a valid email!");
		}
	}

	// Static method / read valid phone number
	public static String readPhone(String prompt) {
		while (true) {
			System.out.print(prompt);
			String phone = sc.nextLine().trim();

			// Validation condition
			if (phone.length() == 10 && phone.matches("[0-9]+")) {
				return phone;
			}

			System.out.println("  \u26A0 Phone must be 10 digits!");
		}
	}

	// Static method / read password
	public static String readPassword(String prompt) {
		while (true) {
			System.out.print(prompt);

			// Console input
			String pass = new String(System.console().readPassword());

			// Validation condition
			if (pass.length() >= 4) {
				return pass;
			}

			System.out.println("  \u26A0 Password must be at least 4 characters!");
		}
	}

	// Static method / read double input
	public static double readDouble(String prompt) {
		System.out.print(prompt);

		// While loop / validation
		while (!sc.hasNextDouble()) {
			System.out.println("  \u26A0 Enter a valid number!");
			sc.next();
			System.out.print(prompt);
		}

		return sc.nextDouble();
	}

	// Static method / validate menu choice
	public static int readMenu(int max) {
		int choice = readInt("Enter choice: ");

		// Conditional statement
		if (choice >= 0 && choice <= max) {
			return choice;
		}

		return -1;
	}
}