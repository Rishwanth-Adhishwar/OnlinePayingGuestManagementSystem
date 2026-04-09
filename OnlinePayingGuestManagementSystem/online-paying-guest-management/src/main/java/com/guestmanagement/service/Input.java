package com.guestmanagement.service;

import java.util.Scanner;

public class Input {


	// Static object / Scanner class object creation

	static Scanner sc = new Scanner(System.in);

	public static int readInt(String prompt) {
		System.out.print(prompt);
		while (!sc.hasNextInt()) {
			System.out.println("  \u26A0 Enter a valid number!");
			sc.next();
			System.out.print(prompt);
		}
		return sc.nextInt();
	}

	public static String readText(String prompt) {
		System.out.print(prompt);
		String text = sc.nextLine();
		if (text.isEmpty()) {
			text = sc.nextLine();
		}
		return text.trim();
	}

	public static String readEmail(String prompt) {
		sc.nextLine();
		while (true) {
			System.out.print(prompt);
			String email = sc.nextLine().trim();
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

	public static String readPhone(String prompt) {
		while (true) {
			System.out.print(prompt);
			String phone = sc.nextLine().trim();
			if (phone.length() == 10 && phone.matches("[0-9]+")) {
				return phone;
			}
			System.out.println("  \u26A0 Phone must be 10 digits!");
		}
	}

	public static String readPassword(String prompt) {
		while (true) {
			System.out.print(prompt);
			String pass = new String(System.console().readPassword());
			if (pass.length() >= 4) {
				return pass;
			}
			System.out.println("  \u26A0 Password must be at least 4 characters!");
		}
	}

	public static double readDouble(String prompt) {
		System.out.print(prompt);
		while (!sc.hasNextDouble()) {
			System.out.println("  \u26A0 Enter a valid number!");
			sc.next();
			System.out.print(prompt);
		}
		return sc.nextDouble();
	}

	public static int readMenu(int max) {
		int choice = readInt("Enter choice: ");
		if (choice >= 0 && choice <= max) {
			return choice;
		}
		return -1;
	}
}
