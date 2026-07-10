package com.guestmanagement.model;

// Interface (Abstraction concept)
// Defines a contract that implementing classes must follow
public interface Displayable {

    // Abstract method (no body)
    // Any class that implements this interface must override this method
    void display();

    // Default method (Java 8+ feature)
    default void printHeader() {
        System.out.println("--- " + getClass().getSimpleName() + " Details ---");
    }
}
