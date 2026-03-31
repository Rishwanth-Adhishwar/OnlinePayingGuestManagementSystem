package com.guestmanagement.model;

public class Booking {
	public int id;
	public int tenantId;
	public int roomId;
	public String date;

	public Booking(int id, int tenantId, int roomId, String date) {
		this.id = id;
		this.tenantId = tenantId;
		this.roomId = roomId;
		this.date = date;
	}

	public String toString() {
		return "BookingID:" + id + " | TenantID:" + tenantId + " | RoomID:" + roomId + " | Date:" + date;
	}
}
