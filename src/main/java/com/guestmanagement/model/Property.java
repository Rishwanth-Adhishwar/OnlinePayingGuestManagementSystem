package com.guestmanagement.model;

// Class demonstrating Encapsulation (private fields + getters/setters)
public class Property implements Displayable {

    // Private fields (Encapsulation)
    private int id;
    private String name;
    private String location;
    private String owner;

    // Constructor
    public Property(int id, String name, String location, String owner) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.owner = owner;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // Implementing interface method (Polymorphism)
    @Override
    public void display() {
        printHeader();
        System.out.println("  ID      : " + id);
        System.out.println("  Name    : " + name);
        System.out.println("  Location: " + location);
        System.out.println("  Owner   : " + owner);
    }

    private void printHeader() {
        System.out.println("--- Property Details ---");
    }

    // Method Overriding
    @Override
    public String toString() {
        return "ID:" + id + " | " + name + " | " + location + " | Owner:" + owner;
    }
}
