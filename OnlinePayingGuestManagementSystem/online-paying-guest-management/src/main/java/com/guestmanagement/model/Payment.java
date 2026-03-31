package com.guestmanagement.model;

public class Payment {
	public int id;
	public int bookingId;
	public double amount;
	public String mode;
	public String date;

	public Payment(int id, int bookingId, double amount, String mode, String date) {
		this.id = id;
		this.bookingId = bookingId;
		this.amount = amount;
		this.mode = mode;
		this.date = date;
	}

	public String toString() {
		return "PayID:" + id + " | BookingID:" + bookingId + " | Rs." + amount + " | " + mode + " | " + date;
	}
}
