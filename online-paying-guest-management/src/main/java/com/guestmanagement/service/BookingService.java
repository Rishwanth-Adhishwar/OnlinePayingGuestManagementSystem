package com.guestmanagement.service;

import com.guestmanagement.model.Booking;

public class BookingService {
<<<<<<< HEAD
	
	Booking[] booking = new Booking[100];
	
	int count = 0;
	
	public void bookRoom(int roomId,int tenantId) {
		
		if (count >= booking.length) {
			System.out.println("Booking storage full");
			return;
		}
		
		booking[count] = new Booking(tenantId, roomId);
		
		count++;
		
		System.out.println("Booking created successfully. Booking ID: " + booking[count-1].getBookingId());
		
	}
	
	public void cancelBooking(int bookingId) {
		
		boolean found = false;
		
		for(int i = 0; i < count; i++){
			
			if(bookingId == booking[i].getBookingId()) {
				
				found = true;
				
				if(booking[i].getStatus()) {
					booking[i].cancelBooking();
				}
				else {
					System.out.println("Already Cancelled.");
				}
				break;
			}
		}
		if(!found)
			System.out.println("Booking ID not found.");
	}
	
	public void viewBookingDetails(int bookingId) {
		
		for (int i = 0; i < count; i++) {
			
			if(bookingId == booking[i].getBookingId()) {
				
				System.out.println(booking[i]);
				return;
			}
		}
		System.out.println("Booking ID not found");
	}
=======
>>>>>>> branch 'main' of https://github.com/Rishwanth-Adhishwar/OnlinePayingGuestManagementSystem.git

    Booking[] booking = new Booking[100];
    int count = 0;

    // Create Booking
    public void bookRoom(int roomId, int tenantId) {

        if (count >= booking.length) {
            System.out.println("Booking storage full");
            return;
        }

        booking[count] = new Booking(tenantId, roomId);
        count++;

        System.out.println("Booking created successfully. Booking ID: " 
                           + booking[count - 1].getBookingId());
    }

    // Cancel Booking
    public void cancelBooking(int bookingId) {

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (bookingId == booking[i].getBookingId()) {
                booking[i].setStatus(false);
                found = true;
                break;
            }
        }

        if (found)
            System.out.println("Booking Cancelled.");
        else
            System.out.println("Booking ID not found");
    }

    // View Booking
    public void viewBookingDetails(int bookingId) {

        for (int i = 0; i < count; i++) {

            if (bookingId == booking[i].getBookingId()) {
                System.out.println(booking[i]);
                return;
            }
        }
        System.out.println("Booking ID not found");
    }

    // NEW: Get all bookings
    public Booking[] getAllBookings() {
        return booking;
    }

    // NEW: Get count
    public int getBookingCount() {
        return count;
    }
}