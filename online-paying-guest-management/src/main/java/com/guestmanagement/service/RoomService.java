package com.guestmanagement.service;

import com.guestmanagement.model.Room;

public class RoomService {

    Room[] rooms = {
        new Room(101, "Single", 8000, 1, true),
        new Room(102, "Double", 8000, 2, false),
        new Room(103, "Single", 5000, 1, true),
        new Room(104, "Triple", 10000, 3, true),
        new Room(105, "Double", 8000, 2, false)
    };

    // Display Available Rooms
    public void displayAvailableRooms() {

        System.out.println("Available Rooms");

        for (Room r : rooms) {
            if (r.isAvailable()) {
                System.out.println("Room ID: " + r.getRoomId());
                System.out.println("Room Type: " + r.getRoomType());
                System.out.println("Price: " + r.getRoomPrice());
                System.out.println("Capacity: " + r.getCapacity() + " persons");
                System.out.println("-----------------------");
            }
        }
    }

    // Generate Room Report
    public void generateRoomReport() {

        int availableCount = 0;

        for (Room r : rooms) {
            if (r.isAvailable()) {
                availableCount++;
            }
        }

        System.out.println("\nRoom Report");
        System.out.println("Total Rooms: " + rooms.length);
        System.out.println("Available Rooms: " + availableCount);
        System.out.println("Occupied Rooms: " + (rooms.length - availableCount));
    }
}