package com.guestmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    // 🔴 Custom Exceptions
    public static class InvalidBookingException extends RuntimeException {
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

    // 🔴 Static tracking
    private static int idCounter = 1;
    private static List<Booking> bookingList = new ArrayList<>();

    // 🔴 Fields
    private int bookingId;
    private int tenantId;
    private int roomId;
    private LocalDate bookingDate;
    private boolean status;

    // 🔴 Constructor
    public Booking(int tenantId, int roomId) {

        validateIds(tenantId, roomId);
        checkDuplicateActiveBooking(tenantId, roomId);

        this.bookingId = idCounter++;
        this.tenantId = tenantId;
        this.roomId = roomId;
        this.bookingDate = LocalDate.now();
        this.status = true;

        registerBooking(this);
    }

    // 🔴 Validation
    private static void validateIds(int tenantId, int roomId) {
        if (tenantId <= 0) {
            throw new InvalidBookingException("Tenant ID must be positive");
        }
        if (roomId <= 0) {
            throw new InvalidBookingException("Room ID must be positive");
        }
    }

    // 🔴 Duplicate Check
    private static void checkDuplicateActiveBooking(int tenantId, int roomId) {
        for (Booking b : bookingList) {
            if (b.tenantId == tenantId && b.roomId == roomId && b.status) {
                throw new DuplicateBookingException(tenantId, roomId);
            }
        }
    }

    // 🔴 Register booking
    private static void registerBooking(Booking booking) {
        bookingList.add(booking);
    }

    // 🔴 Getters
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

    public boolean isStatus() {   // ✅ FIXED (was missing)
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // 🔴 toString
    @Override
    public String toString() {
        return "Booking Details\n" +
               "Booking ID : " + bookingId + "\n" +
               "Tenant ID  : " + tenantId + "\n" +
               "Room ID    : " + roomId + "\n" +
               "Booking Date : " + bookingDate + "\n" +
               "Status : " + (status ? "Active" : "Cancelled");
    }
}