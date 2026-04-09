package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Payment entity in the
 * Paying Guest Management System.
 * It stores payment details such as payment ID,
 * booking ID, amount, payment mode,
 * and payment date.
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
public class Payment {

	// Instance variables / data members
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

	// Method overriding / string representation
	public String toString() {
		return "PayID:" + id + " | BookingID:" + bookingId + " | Rs." + amount + " | " + mode + " | " + date;
	}
}