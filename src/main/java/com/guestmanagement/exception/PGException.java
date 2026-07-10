package com.guestmanagement.exception;

// Custom Exception class (Exception Handling concept)
// This is a checked exception - must be handled using try-catch or throws
public class PGException extends Exception {

    // Constructor with message
    public PGException(String message) {
        super(message);
    }

    // Constructor overloading (Polymorphism - compile time)
    public PGException(String message, Throwable cause) {
        super(message, cause);
    }

    // Method to get user-friendly error message
    public String getUserMessage() {
        return "Oops! " + getMessage();
    }
}
