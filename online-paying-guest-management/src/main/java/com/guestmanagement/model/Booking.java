package com.guestmanagement.model;

import java.time.LocalDate;

class InvalidBookingException extends RuntimeException {// Identify only at Runtime 
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class Booking {
	
	private static int idCounter = 1;
    private int bookingId;
    private int tenantId;
    private int roomId;
    private LocalDate bookingDate;
    private boolean status;
 
    public Booking(int tenantId, int roomId) {
        
    	validateIds(tenantId, roomId);
       
 
        this.bookingId   = idCounter++;
        this.tenantId    = tenantId;
        this.roomId      = roomId;
        this.bookingDate = LocalDate.now();
        this.status      = true;
 
    }
    
    private static void validateIds(int tenantId, int roomId) {
        if (tenantId <= 0) {
            throw new InvalidBookingException("Tenant ID must be a positive integer. Received: " + tenantId);
        }
        if (roomId <= 0) {
            throw new InvalidBookingException("Room ID must be a positive integer. Received: " + roomId);
        }
    }
	
	public int getBookingId() {
		return bookingId;
	}
	
	public int getTenantId() {
	    return tenantId;
	}

	public int getRoomId() {
	    return roomId;
	}

	public LocalDate getBookingDate() {
	    return bookingDate;
	}

	public boolean getStatus() {
	    return status;
	}
	
	public void cancelBooking() {
		status = false;
	}
	
	
	@Override
	public String toString() {
	    return "Booking Details\n" +
	           "Booking ID : " + getBookingId() + "\n" +
	           "Tenant ID  : " + getTenantId() + "\n" +
	           "Room ID    : " + getRoomId() + "\n" +
	           "Booking Date : " + getBookingDate() + "\n" +
	           "Status : " + (getStatus() ? "Active" : "Cancelled");
	}
}
