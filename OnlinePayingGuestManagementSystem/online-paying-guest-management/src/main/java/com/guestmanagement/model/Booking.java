package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * Represents a booking in the Paying Guest Management System.
 * Stores tenant, room, and booking date details.
 *
 * ============================================================
 */

// Class
public class Booking {

    // Variables
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

    // Display method
	public String toString() {
		return "BookingID:" + id + " | TenantID:" + tenantId + " | RoomID:" + roomId + " | Date:" + date;
	}
}