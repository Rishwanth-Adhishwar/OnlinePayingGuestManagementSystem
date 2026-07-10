package com.guestmanagement.model;

// Class demonstrating Encapsulation
public class Booking implements Displayable {

    // Private fields
    private int id;
    private int tenantId;
    private int roomId;
    private String date;

    // Constructor
    public Booking(int id, int tenantId, int roomId, String date) {
        this.id = id;
        this.tenantId = tenantId;
        this.roomId = roomId;
        this.date = date;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
        System.out.println("  Booking ID : " + id);
        System.out.println("  Tenant ID  : " + tenantId);
        System.out.println("  Room ID    : " + roomId);
        System.out.println("  Date       : " + date);
    }

    // Method Overriding
    @Override
    public String toString() {
        return "BookingID:" + id + " | TenantID:" + tenantId + " | RoomID:" + roomId + " | Date:" + date;
    }
}
