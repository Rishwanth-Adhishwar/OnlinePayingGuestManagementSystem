package com.guestmanagement.model;

import java.time.LocalDate;

public class Booking {
	
	public static class InvalidBookingException extends RuntimeException {// Identify only at Runtime 
        public InvalidBookingException(String message) {
            super(message);
        }
    }
 
    public static class BookingNotFoundException extends RuntimeException {
        public BookingNotFoundException(int bookingId) {
            super("Booking not found for ID: " + bookingId);
        }
    }
 
    public static class DuplicateBookingException extends RuntimeException {
        public DuplicateBookingException(int tenantId, int roomId) {
            super("Active booking already exists for Tenant ID " + tenantId
                    + " in Room ID " + roomId);
        }
    }
    
    
    private static int idCounter = 1;
    private int bookingId;
    private int tenantId;
    private int roomId;
    private LocalDate bookingDate;
    private boolean status;
    
	
    public Booking() {
    }
 
    public Booking(int tenantId, int roomId) {
        validateIds(tenantId, roomId);
        checkDuplicateActiveBooking(tenantId, roomId);
 
        this.bookingId   = idCounter++;
        this.tenantId    = tenantId;
        this.roomId      = roomId;
        this.bookingDate = LocalDate.now();
        this.status      = true;
 
        registerBooking(this);
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
	
	public void setStatus(boolean status) {
	    this.status = status;
	}
	
	
	
	@Override
	public String toString() {
	    return "Booking Details\n" +
	           "Booking ID : " + getBookingId() + "\n" +
	           "Tenant ID  : " + getTenantId() + "\n" +
	           "Room ID    : " + getRoomId() + "\n" +
	           "Booking Date : " + getBookingDate() + "\n" +
	           "Status : " + (isStatus() ? "Active" : "Cancelled");
	}
}
