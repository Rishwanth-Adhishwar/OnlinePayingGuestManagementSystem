package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * Represents a tenant in the Paying Guest Management System.
 * Stores tenant personal and contact details.
 *
 * ============================================================
 */

// Class
public class Tenant {

    // Variables
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

    // Display method
	public String toString() {
		return "ID:" + id + " | " + name + " | " + email + " | Ph:" + phone;
	}
}