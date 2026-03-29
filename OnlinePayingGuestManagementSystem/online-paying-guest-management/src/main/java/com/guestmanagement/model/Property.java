package com.guestmanagement.model;

public class Property {
	public int id;
	public String name;
	public String location;
	public String owner;

	public Property(int id, String name, String location, String owner) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.owner = owner;
	}

	public String toString() {
		return "ID:" + id + " | " + name + " | " + location + " | Owner:" + owner;
	}
}
