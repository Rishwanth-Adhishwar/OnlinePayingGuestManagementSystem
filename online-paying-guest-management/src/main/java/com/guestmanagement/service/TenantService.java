package com.guestmanagement.service;

public class TenantService {

    // Session attribute
    private String sessionId;

    // Tenant attributes
    private int tenantId;
    private String name;
    private String email;
    private String phone;

    // Constructor
    public TenantService() {
        sessionId = null;

        // Sample data (can come from DB later)
        tenantId = 101;
        name = "Jeeva";
        email = "jeeva@mail.com";
        phone = "9876543210";
    }

    // Start Session (Login)
    public void startSession(String sessionId) {
        this.sessionId = sessionId;
        System.out.println("Session started for Tenant: " + sessionId);
    }

    // Logout
    public void logout() {

        if (sessionId == null) {
            System.out.println("No active session found.");
            return;
        }

        sessionId = null;

        System.out.println("Logout successful!");
        System.out.println("Returning to Main Menu...");
    }

    // View Tenant Profile
    public void viewProfile() {

        System.out.println("\n--- Tenant Profile ---");
        System.out.println("Tenant ID : " + tenantId);
        System.out.println("Name      : " + name);
        System.out.println("Email     : " + email);
        System.out.println("Phone     : " + phone);
    }

    // Update Tenant Profile
    public void updateProfile(String name, String email, String phone) {

        this.name = name;
        this.email = email;
        this.phone = phone;

        System.out.println("Profile updated successfully!");
    }

    // Delete Tenant Account
    public void deleteAccount() {

        tenantId = 0;
        name = null;
        email = null;
        phone = null;

        System.out.println("Tenant account deleted successfully.");
    }
}