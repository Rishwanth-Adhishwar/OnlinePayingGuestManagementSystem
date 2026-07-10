package com.guestmanagement.model;

// Class demonstrating:
// 1. Inheritance - extends Person (is-a relationship)
// 2. Encapsulation - private fields with getters/setters
// 3. Polymorphism - method overriding (@Override)
// 4. Abstraction - implements Displayable interface

public class Tenant extends Person implements Displayable {

    // Private fields (Encapsulation - data hiding)
    private String email;
    private String password;
    private String phone;

    // Constructor
    public Tenant(int id, String name, String email, String password, String phone) {
        // Calling parent class constructor using super (Inheritance)
        super(id, name);
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Getter and Setter methods (Encapsulation - controlled access)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Simple validation in setter (Encapsulation benefit)
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Overriding abstract method from Person class (Polymorphism - runtime)
    @Override
    public String getRole() {
        return "Tenant";
    }

    // Implementing interface method (Polymorphism)
    @Override
    public void display() {
        printHeader();
        System.out.println("  ID   : " + getId());
        System.out.println("  Name : " + getName());
        System.out.println("  Email: " + email);
        System.out.println("  Phone: " + phone);
    }

    // Method Overriding - toString from Object class (Polymorphism)
    @Override
    public String toString() {
        return "ID:" + getId() + " | " + getName() + " | " + email + " | Ph:" + phone;
    }
}
