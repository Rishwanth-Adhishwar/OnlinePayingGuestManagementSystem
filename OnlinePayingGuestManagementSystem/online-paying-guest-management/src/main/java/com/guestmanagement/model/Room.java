package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * Represents a room in the Paying Guest Management System.
 * Stores room details, rent, and availability status.
 *
 * ============================================================
 */

// Class
public class Room {

    // Variables
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
		this.available = true;
	}

    // Display method
	public String toString() {
		String status = available ? "Available" : "Booked";
		return "ID:" + id + " | Room:" + roomNo + " | Type:" + type + " | Rs." + rent + " | " + status;
	}
}