package com.guestmanagement.model;

import java.time.LocalDate;

public class Booking {
	
	private static int idCounter = 1;
	
	private int bookingId;
	private int tenantId;
	private int roomId;
	private LocalDate bookingDate;
	private boolean status;
	
	public Booking() {
		
	}
	
	public Booking(int tenantId,int roomId){
		
		bookingId = idCounter++;
		this.tenantId = tenantId;
		this.roomId = roomId;
		bookingDate = LocalDate.now();
		status = true;
		
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

	public boolean isStatus() {
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
