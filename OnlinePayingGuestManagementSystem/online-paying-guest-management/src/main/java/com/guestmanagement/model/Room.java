package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Room entity in the
 * Paying Guest Management System.
 * It stores room details such as room ID,
 * property ID, room number, room type,
 * rent amount, and availability status.
 *
 * OOP Concepts Used :
 * - Class
 * - Constructor
 * - Object Creation
 * - Encapsulation (Data Members)
 * - Boolean State Handling
 * - Method Overriding (toString)
 *
 * ============================================================
 */

// Class
public class Room {

	// Instance variables / data members
	public int id;
	public int propertyId;
	public String roomNo;
	public String type;
	public double rent;
	public boolean available;

	// Constructor
	public Room(int id, int propertyId, String roomNo, String type, double rent) {
		this.id = id;
		this.propertyId = propertyId;
		this.roomNo = roomNo;
		this.type = type;
		this.rent = rent;
		this.available = true; // every new room starts as available
	}

	// Method overriding / string representation
	public String toString() {
		String status = available ? "Available" : "Booked";
		return "ID:" + id + " | Room:" + roomNo + " | Type:" + type + " | Rs." + rent + " | " + status;
	}
}