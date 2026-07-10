package com.guestmanagement.model;

// Abstract class (Abstraction concept)
// Cannot create objects of this class directly
// It is used as a base class for inheritance
public abstract class Person {

    // Private fields (Encapsulation)
    private int id;
    private String name;

    // Constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter methods (Encapsulation)
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

    // Abstract method (Abstraction)
    // Subclasses MUST provide their own implementation
    public abstract String getRole();
}
