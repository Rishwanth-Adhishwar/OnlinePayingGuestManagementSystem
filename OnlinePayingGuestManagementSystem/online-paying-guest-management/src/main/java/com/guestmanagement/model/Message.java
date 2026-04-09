package com.guestmanagement.model;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class represents the Message entity in the
 * Paying Guest Management System.
 * It stores message details such as message ID,
 * sender ID, sender name, message content,
 * and sent date.
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
public class Message {

	// Instance variables / data members
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

	// Method overriding / string representation
	public String toString() {
		return "From:" + senderName + " | " + message + " | " + date;
	}
}