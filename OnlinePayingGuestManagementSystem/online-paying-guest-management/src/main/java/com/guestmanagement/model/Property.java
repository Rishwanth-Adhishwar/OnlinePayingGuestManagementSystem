package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * Represents a property in the Paying Guest Management System.
 * Stores property name, location, and owner details.
 *
 * ============================================================
 */

// Class
public class Property {

    // Variables
	public int id;
	public String name;
	public String location;
	public String owner;

    // Constructor
	public Property(int id, String name, String location, String owner) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.owner = owner;
	}

    // Display method
	public String toString() {
		return "ID:" + id + " | " + name + " | " + location + " | Owner:" + owner;
	}
}