package com.guestmanagement.model;

/**
 * ============================================================
 * Author : Rishwanth
 * ============================================================
 *
 * Description :
 * Represents a payment in the Paying Guest Management System.
 * Stores booking, amount, payment mode, and date details.
 *
 * ============================================================
 */

// Class
public class Payment {

    // Variables
	public int id;
	public int bookingId;
	public double amount;
	public String mode;
	public String date;

    // Constructor
	public Payment(int id, int bookingId, double amount, String mode, String date) {
		this.id = id;
		this.bookingId = bookingId;
		this.amount = amount;
		this.mode = mode;
		this.date = date;
	}

    // Display method
	public String toString() {
		return "PayID:" + id + " | BookingID:" + bookingId + " | Rs." + amount + " | " + mode + " | " + date;
	}
}