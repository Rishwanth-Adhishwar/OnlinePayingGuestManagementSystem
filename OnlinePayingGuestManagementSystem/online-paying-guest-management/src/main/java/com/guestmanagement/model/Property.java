package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Property entity in the
 * Paying Guest Management System.
 * It stores property details such as property ID,
 * property name, location, and owner name.
 *
 * OOP Concepts Used :
 * - Class
 * - Constructor
 * - Object Creation
 * - Encapsulation (Data Members)
 * - Method Overriding (toString)
 *
 * ============================================================
 */

// Class
public class Property {

	// Instance variables / data members
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

	// Method overriding / string representation
	public String toString() {
		return "ID:" + id + " | " + name + " | " + location + " | Owner:" + owner;
	}
}