package com.guestmanagement.service;

import java.util.Scanner;

public class Input {


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
	        while (true) {
	            System.out.print(prompt);
	            String email = sc.nextLine().trim();

	            if (email.isEmpty()) {
	                System.out.println("\u26A0\uFE0F Email cannot be empty!");
	            } 
	            else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
	                System.out.println(" \u26A0  Invalid email format!");
	            } 
	            else {
	                return email;
	            }
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

            String password;
            if (System.console() != null) {
                password = new String(System.console().readPassword());
            } else {
                password = sc.nextLine(); 
            }

            if (password.length() < 4) {
                System.out.println(" \u26A0 Password must be at least 4 characters!");
            } 
            else if (!password.matches(".*[A-Z].*")) {
                System.out.println(" \u26A0 Must contain at least one uppercase letter!");
            } 
            else if (!password.matches(".*\\d.*")) {
                System.out.println(" \u26A0 Must contain at least one number!");
            } 
            else {
                return password;
            }
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