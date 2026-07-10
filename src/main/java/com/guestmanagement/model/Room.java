package com.guestmanagement.model;

// Class demonstrating Encapsulation
public class Room implements Displayable {

    // Private fields
    private int id;
    private int propertyId;
    private String roomNo;
    private String type;
    private double rent;
    private boolean available;

    // Constructor
    public Room(int id, int propertyId, String roomNo, String type, double rent) {
        this.id = id;
        this.propertyId = propertyId;
        this.roomNo = roomNo;
        this.type = type;
        this.rent = rent;
        this.available = true;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Implementing interface method
    @Override
    public void display() {
        printHeader();
        System.out.println("  ID        : " + id);
        System.out.println("  Room No   : " + roomNo);
        System.out.println("  Type      : " + type);
        System.out.println("  Rent      : Rs." + rent);
        System.out.println("  Available : " + (available ? "Yes" : "No"));
    }

    private void printHeader() {
        System.out.println("--- Room Details ---");
    }

    // Method Overriding
    @Override
    public String toString() {
        String status = available ? "Available" : "Booked";
        return "ID:" + id + " | Room:" + roomNo + " | Type:" + type + " | Rs." + rent + " | " + status;
    }
}
