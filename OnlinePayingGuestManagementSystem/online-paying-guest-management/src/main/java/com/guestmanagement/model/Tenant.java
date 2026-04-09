package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Tenant entity in the
 * Paying Guest Management System.
 * It stores tenant details such as tenant ID,
 * name, email, password, and phone number.
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
public class Tenant {

	// Instance variables / data members
	public int id;
	public String name;
	public String email;
	public String password;
	public String phone;

	// Constructor
	public Tenant(int id, String name, String email, String password, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	// Method overriding / string representation
	public String toString() {
		return "ID:" + id + " | " + name + " | " + email + " | Ph:" + phone;
	}
}