package com.guestmanagement.model;

// Class demonstrating Encapsulation
public class Message implements Displayable {

    // Private fields
    private int id;
    private int senderId;
    private String senderName;
    private String message;
    private String date;

    // Constructor
    public Message(int id, int senderId, String senderName, String message, String date) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.date = date;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Implementing interface method
    @Override
    public void display() {
        printHeader();
        System.out.println("  From   : " + senderName);
        System.out.println("  Message: " + message);
        System.out.println("  Date   : " + date);
    }

    // Method Overriding
    @Override
    public String toString() {
        return "From:" + senderName + " | " + message + " | " + date;
    }
}
