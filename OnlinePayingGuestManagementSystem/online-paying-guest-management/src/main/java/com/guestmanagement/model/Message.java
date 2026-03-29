package com.guestmanagement.model;

public class Message {
    public int id;
    public int senderId;
    public String senderName;
    public String message;
    public String date;

    public Message(int id, int senderId, String senderName, String message, String date) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.date = date;
    }

    public String toString() {
        return "From:" + senderName + " | " + message + " | " + date;
    }
}
