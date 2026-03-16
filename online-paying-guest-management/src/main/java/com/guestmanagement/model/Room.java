package com.guestmanagement.model;

public class Room {
	private int roomId;
	private String roomType;
	private double price;
	private int capacity;
	private boolean availability;
	
	
	
	public Room(int roomId, String roomType, double price, int capacity, boolean availability) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.price = price;
		this.capacity = capacity;
		this.availability = availability;
	}



	public int getRoomId() {
		return roomId;
	}



	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}



	public String getRoomType() {
		return roomType;
	}



	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public boolean isAvailability() {
		return availability;
	}



	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	
}
