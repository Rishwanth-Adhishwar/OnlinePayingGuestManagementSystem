package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * Represents a message in the Paying Guest Management System.
 * Stores sender details, message content, and date.
 *
 * ============================================================
 */

// Class
public class Message {

    // Variables
	public int id;
	public int senderId;
	public String senderName;
	public String message;
	public String date;

    // Constructor
	public Message(int id, int senderId, String senderName, String message, String date) {
		this.id = id;
		this.senderId = senderId;
		this.senderName = senderName;
		this.message = message;
		this.date = date;
	}

    // Display method
	public String toString() {
		return "From:" + senderName + " | " + message + " | " + date;
	}
}