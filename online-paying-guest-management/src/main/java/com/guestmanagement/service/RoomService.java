package com.guestmanagement.service;

public class RoomService {
	
	
	
	
	
	
	public void viewAvailable() {
	    boolean found = false;
	    for (Room room : rooms) {
	        if (room.isAvailability()) {
	            System.out.println(room);
	            found = true;
	        }
	    }
	    if (!found) {
	        System.out.println("Rooms not available");
	    }
	}



}
