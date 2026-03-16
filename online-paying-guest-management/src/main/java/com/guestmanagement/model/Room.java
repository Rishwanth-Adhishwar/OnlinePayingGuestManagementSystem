package com.guestmanagement.model;

public class Room {

    private int roomId;
    private String roomType;
    private double roomPrice;
    private int capacity;
    private boolean available;

    // Constructors1
    public Room(int roomId, String roomType, double roomPrice, int capacity, boolean available) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.capacity = capacity;
        this.available = available;
    }

    // Getter methods
    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
