package com.guestmanagement.service;



import com.guestmanagement.model.*;

// ── Everything a Tenant can do ─────────────────────────────
public class TenantMenu {

	// The tenant who is currently logged in (null = nobody)
	static Tenant currentTenant = null;

	// ── Tenant portal (register / login) ──────────────────
	public static void show() {
		int choice = -1;
		while (choice != 0) {
			System.out.println("\n--- TENANT PORTAL ---");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("0. Back");
			choice = Input.readInt("Enter choice: ");

			if (choice == 1)
				register();
			else if (choice == 2) {
				login();
				if (currentTenant != null)
					tenantMenu();
			} else if (choice == 0)
				System.out.println("  Going back.");
			else
				System.out.println("  Invalid choice.");
		}
	}

	// ── Register a new tenant ──────────────────────────────
	static void register() {
		System.out.println("\n--- Register ---");
		String name = Input.readText("Name     : ");
		String email = Input.readText("Email    : ");

		// Check email not already used
		for (Tenant t : Data.tenants) {
			if (t.email.equals(email)) {
				System.out.println("  Email already registered!");
				return;
			}
		}

		String pass = Input.readText("Password : ");
		String phone = Input.readText("Phone (10 digits) : ");

		// Validate phone number
		if (phone.length() != 10 || !phone.matches("[0-9]+")) {
			System.out.println("  Phone must be exactly 10 digits!");
			return;
		}

		Tenant t = new Tenant(Data.nextTenantId++, name, email, pass, phone);
		Data.tenants.add(t);
		System.out.println("  Registered! Your Tenant ID = " + t.id);
	}

	// ── Login ──────────────────────────────────────────────
	static void login() {
		System.out.println("\n--- Tenant Login ---");
		String email = Input.readText("Email    : ");
		String pass = Input.readText("Password : ");

		for (Tenant t : Data.tenants) {
			if (t.email.equals(email) && t.password.equals(pass)) {
				currentTenant = t;
				System.out.println("  Welcome, " + t.name + "!");
				return;
			}
		}
		System.out.println("  Wrong email or password.");
		currentTenant = null;
	}

	// ── Menu after login ───────────────────────────────────
	static void tenantMenu() {
		int choice = -1;
		while (choice != 0) {
			System.out.println("\n--- TENANT MENU (" + currentTenant.name + ") ---");
			System.out.println("1. View Available Rooms");
			System.out.println("2. Book a Room");
			System.out.println("3. Pay Rent");
			System.out.println("4. Send Message to Admin");
			System.out.println("5. My Bookings");
			System.out.println("0. Logout");
			choice = Input.readInt("Enter choice: ");

			if (choice == 1)
				viewAvailableRooms();
			else if (choice == 2)
				bookRoom();
			else if (choice == 3)
				makePayment();
			else if (choice == 4)
				sendMessage();
			else if (choice == 5)
				myBookings();
			else if (choice == 0)
				System.out.println("  Goodbye, " + currentTenant.name + "!");
			else
				System.out.println("  Invalid choice.");
		}
		currentTenant = null; // clear after logout
	}

	// ── View only available rooms ──────────────────────────
	static void viewAvailableRooms() {
		System.out.println("  --- Available Rooms ---");
		boolean found = false;
		for (Room r : Data.rooms) {
			if (r.available) {
				System.out.println("  " + r);
				found = true;
			}
		}
		if (!found)
			System.out.println("  No rooms available right now.");
	}

	// ── Book a room ────────────────────────────────────────
	static void bookRoom() {
		viewAvailableRooms();

		int roomId = Input.readInt("Room ID to book : ");
		String date = Input.readText("Move-in date    : ");

		// Find the room
		Room chosen = null;
		for (Room r : Data.rooms) {
			if (r.id == roomId) {
				chosen = r;
				break;
			}
		}

		if (chosen == null) {
			System.out.println("  Room not found!");
			return;
		}
		if (!chosen.available) {
			System.out.println("  Room is already booked!");
			return;
		}

		// Create booking and mark room as booked
		Booking b = new Booking(Data.nextBookingId++, currentTenant.id, roomId, date);
		Data.bookings.add(b);
		chosen.available = false;

		System.out.println(
				"  Booked! BookingID=" + b.id + " | Room:" + chosen.roomNo + " | Rs." + chosen.rent + "/month");
	}

	// ── Pay rent ───────────────────────────────────────────
	static void makePayment() {
		myBookings();

		int bookingId = Input.readInt("Booking ID to pay for : ");
		String mode = Input.readText("Payment mode (Cash/UPI/Online) : ");
		String date = Input.readText("Payment date : ");

		// Find the booking
		Booking booking = null;
		for (Booking b : Data.bookings) {
			if (b.id == bookingId) {
				booking = b;
				break;
			}
		}

		if (booking == null) {
			System.out.println("  Booking not found!");
			return;
		}
		if (booking.tenantId != currentTenant.id) {
			System.out.println("  This is not your booking!");
			return;
		}

		// Find rent amount from the room
		double rent = 0;
		for (Room r : Data.rooms) {
			if (r.id == booking.roomId) {
				rent = r.rent;
				break;
			}
		}

		Payment p = new Payment(Data.nextPaymentId++, bookingId, rent, mode, date);
		Data.payments.add(p);
		System.out.println("  Payment successful! Rs." + rent + " via " + mode);
	}

	// ── Send a message to admin ────────────────────────────
	static void sendMessage() {
		String text = Input.readText("Your message : ");
		String date = Input.readText("Date         : ");

		Message m = new Message(Data.nextMessageId++, currentTenant.id, currentTenant.name, text, date);
		Data.messages.add(m);
		System.out.println("  Message sent to admin!");
	}

	// ── View my bookings only ──────────────────────────────
	static void myBookings() {
		System.out.println("  --- My Bookings ---");
		boolean found = false;
		for (Booking b : Data.bookings) {
			if (b.tenantId == currentTenant.id) {
				System.out.println("  " + b);
				found = true;
			}
		}
		if (!found)
			System.out.println("  You have no bookings yet.");
	}
}
