package com.guestmanagement.model;

public class Tenant {
	public int id;
	public String name;
	public String email;
	public String password;
	public String phone;

	public Tenant(int id, String name, String email, String password, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public String toString() {
		return "ID:" + id + " | " + name + " | " + email + " | Ph:" + phone;
	}
}
