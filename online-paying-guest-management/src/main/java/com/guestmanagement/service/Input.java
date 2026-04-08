package com.guestmanagement.service;

import java.util.Scanner;

public class Input {
	 static Scanner sc = new Scanner(System.in);

	    // Read a whole number (keeps asking until valid)
	    public static int readInt(String prompt) {
	        while (true) {
	            System.out.print(prompt);
	            try {
	                return Integer.parseInt(sc.nextLine().trim());
	            } catch (Exception e) {
	                System.out.println("  Please enter a valid number!");
	            }
	        }
	    }

	    // Read a decimal number (keeps asking until valid)
	    public static double readDouble(String prompt) {
	        while (true) {
	            System.out.print(prompt);
	            try {
	                return Double.parseDouble(sc.nextLine().trim());
	            } catch (Exception e) {
	                System.out.println("  Please enter a valid amount!");
	            }
	        }
	    }

	    // Read non-empty text (keeps asking until something is typed)
	    public static String readText(String prompt) {
	        while (true) {
	            System.out.print(prompt);
	            String s = sc.nextLine().trim();
	            if (!s.isEmpty()) return s;
	            System.out.println("  Cannot be empty!");
	        }
	    }
}
