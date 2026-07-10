package com.guestmanagement.service;

import com.guestmanagement.exception.PGException;
import java.util.Scanner;

// Class demonstrating:
// 1. Method Overloading (Polymorphism - compile-time)
// 2. Exception Handling (try-catch, throw, custom exception)
// 3. Encapsulation

public class Input {

    // Static Scanner object
    private static Scanner sc = new Scanner(System.in);

    // ===== Method Overloading (Polymorphism) =====
    // Same method name, different parameters

    // Method 1: Read integer with prompt
    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("  " + getErrorMessage("number"));
            sc.next();
            System.out.print(prompt);
        }
        return sc.nextInt();
    }

    // Method 2: Read integer with prompt and range (Overloaded version)
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("  " + getErrorMessage("choice", min, max));
        }
    }

    // Read text input
    public static String readText(String prompt) {
        System.out.print(prompt);
        String text = sc.nextLine();
        if (text.isEmpty()) {
            text = sc.nextLine();
        }
        return text.trim();
    }

    // Read email with validation
    public static String readEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = sc.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("  " + getErrorMessage("empty email"));
                continue;
            }
            if (email.contains("@") && email.contains(".")) {
                return email;
            }
            System.out.println("  " + getErrorMessage("invalid email"));
        }
    }

    // Read phone with validation
    public static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = sc.nextLine().trim();
            if (phone.length() == 10 && phone.matches("[0-9]+")) {
                return phone;
            }
            System.out.println("  " + getErrorMessage("phone"));
        }
    }

    // Read password
    public static String readPassword(String prompt) {
        while (true) {
            System.out.print(prompt);
            String pass = new String(System.console().readPassword());
            if (pass.length() >= 4) {
                return pass;
            }
            System.out.println("  " + getErrorMessage("password"));
        }
    }

    // Read double
    public static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.println("  " + getErrorMessage("number"));
            sc.next();
            System.out.print(prompt);
        }
        return sc.nextDouble();
    }

    // Read double with validation (Overloaded version)
    public static double readDouble(String prompt, double min) {
        while (true) {
            double value = readDouble(prompt);
            if (value >= min) {
                return value;
            }
            System.out.println("  " + getErrorMessage("amount"));
        }
    }

    // ===== Exception Handling demo with custom exception =====
    // This method demonstrates throw keyword with custom exception
    public static int readIntSafe(String prompt) {
        try {
            return readInt(prompt);
        } catch (Exception e) {
            System.out.println("  Error reading input. Using default value 0.");
            sc.nextLine(); // clear buffer
            return 0;
        }
    }

    // Private helper method - throws custom PGException for demonstration
    public static String validateEmail(String email) throws PGException {
        if (email == null || email.isEmpty()) {
            throw new PGException("Email cannot be empty!");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new PGException("Invalid email format!");
        }
        return email;
    }

    // Using getErrorMessage instead of inline strings (Encapsulation)
    private static String getErrorMessage(String type) {
        // Switch expression (Java 14+)
        return switch (type) {
            case "number" -> "Enter a valid number!";
            case "phone" -> "Phone must be 10 digits!";
            case "password" -> "Password must be at least 4 characters!";
            case "empty email" -> "Email cannot be empty!";
            case "invalid email" -> "Enter a valid email!";
            default -> "Invalid input!";
        };
    }

    // Overloaded helper - with parameters (Polymorphism)
    private static String getErrorMessage(String type, int... range) {
        if (type.equals("choice") && range.length == 2) {
            return "Enter a choice between " + range[0] + " and " + range[1] + "!";
        }
        if (type.equals("amount")) {
            return "Amount must be positive!";
        }
        return "Invalid input!";
    }
}
