package com.guestmanagement.model;

public class Room {
    public int     id;
    public int     propertyId;
    public String  roomNo;
    public String  type;
    public double  rent;
    public boolean available;

    public Room(int id, int propertyId, String roomNo, String type, double rent) {
        this.id         = id;
        this.propertyId = propertyId;
        this.roomNo     = roomNo;
        this.type       = type;
        this.rent       = rent;
        this.available  = true;   // every new room starts as available
    }

    public String toString() {
        String status = available ? "Available" : "Booked";
        return "ID:" + id + " | Room:" + roomNo + " | Type:" + type
             + " | Rs." + rent + " | " + status;
    }
}
