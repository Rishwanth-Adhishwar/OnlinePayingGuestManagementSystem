package com.guestmanagement.service;

import java.util.ArrayList;
import java.util.List;
import com.guestmanagement.model.Room;

public class RoomService {

    private List<Room> roomList;

    // Constructor
    public RoomService() {

        roomList = new ArrayList<>();

        // Initial rooms
        roomList.add(new Room(101, "Single", 8000, 1, true));
        roomList.add(new Room(102, "Double", 8000, 2, false));
        roomList.add(new Room(103, "Single", 5000, 1, true));
        roomList.add(new Room(104, "Triple", 10000, 3, true));
        roomList.add(new Room(105, "Double", 8000, 2, false));
    }

    // Add Room
    public void addRoom(Room room) {

        roomList.add(room);
        System.out.println("Room Added Successfully");
    }

    // Delete Room
    public void deleteRoom(int roomId) {

        roomList.removeIf(room -> room.getRoomId() == roomId);
        System.out.println("Room Deleted Successfully");
    }

    // View All Rooms
    public void viewRooms() {

        for (Room r : roomList) {

            System.out.println("Room ID: " + r.getRoomId());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("Price: " + r.getRoomPrice());
            System.out.println("Capacity: " + r.getCapacity() + " persons");
            System.out.println("Available: " + r.isAvailable());
            System.out.println("-----------------------");
        }
    }

    // Display Available Rooms
    public void displayAvailableRooms() {

        System.out.println("\nAvailable Rooms");

        for (Room r : roomList) {

            if (r.isAvailable()) {

                System.out.println("Room ID: " + r.getRoomId());
                System.out.println("Room Type: " + r.getRoomType());
                System.out.println("Price: " + r.getRoomPrice());
                System.out.println("Capacity: " + r.getCapacity() + " persons");
                System.out.println("-----------------------");
            }
        }
    }

    // Check Availability
    public void checkAvailability() {

        for (Room r : roomList) {

            if (r.isAvailable()) {
                System.out.println("Available Room ID: " + r.getRoomId());
            }
        }
    }

    // Generate Room Report
    public void generateRoomReport() {

        int availableCount = 0;

        for (Room r : roomList) {

            if (r.isAvailable()) {
                availableCount++;
            }
        }

        System.out.println("\nRoom Report");
        System.out.println("Total Rooms: " + roomList.size());
        System.out.println("Available Rooms: " + availableCount);
        System.out.println("Occupied Rooms: " + (roomList.size() - availableCount));
    }
 // Count Rooms
    public int countRooms() {
        return roomList.size();
    }
}