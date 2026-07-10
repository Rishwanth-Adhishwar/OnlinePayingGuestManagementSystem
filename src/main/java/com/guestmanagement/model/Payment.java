package com.guestmanagement.model;

// Class demonstrating Encapsulation
public class Payment implements Displayable {

    // Private fields
    private int id;
    private int bookingId;
    private double amount;
    private String mode;
    private String date;

    // Constructor
    public Payment(int id, int bookingId, double amount, String mode, String date) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.mode = mode;
        this.date = date;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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
        System.out.println("  Payment ID : " + id);
        System.out.println("  Booking ID : " + bookingId);
        System.out.println("  Amount     : Rs." + amount);
        System.out.println("  Mode       : " + mode);
        System.out.println("  Date       : " + date);
    }

    private void printHeader() {
        System.out.println("--- Payment Details ---");
    }

    // Method Overriding
    @Override
    public String toString() {
        return "PayID:" + id + " | BookingID:" + bookingId + " | Rs." + amount + " | " + mode + " | " + date;
    }
}
