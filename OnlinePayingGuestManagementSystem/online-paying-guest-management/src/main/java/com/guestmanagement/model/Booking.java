package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Booking entity in the
 * Paying Guest Management System.
 * It stores booking details such as booking ID,
 * tenant ID, room ID, and booking date.
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
public class Booking {

	// Instance variables / data members
	public int id;
	public int tenantId;
	public int roomId;
	public String date;

	// Constructor
	public Booking(int id, int tenantId, int roomId, String date) {
		this.id = id;
		this.tenantId = tenantId;
		this.roomId = roomId;
		this.date = date;
	}

	// Method overriding / string representation
	public String toString() {
		return "BookingID:" + id + " | TenantID:" + tenantId + " | RoomID:" + roomId + " | Date:" + date;
	}
}