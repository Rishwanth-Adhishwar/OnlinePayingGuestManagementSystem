package com.guestmanagement.service;
public class TenantService {

    // Attribute for session management
    private String sessionId;

    // Constructor
    public TenantService() {
        sessionId = null;
    }

    // Method to simulate login session creation
    public void startSession(String sessionId) {
        this.sessionId = sessionId;
        System.out.println("Session started for Tenant: " + sessionId);
    }

    // Logout method (Your Sprint 4 task)
    public void logout() {

        if (sessionId == null) {
            System.out.println("No active session found.");
            return;
        }

        sessionId = null;

        System.out.println("Logout successful!");
        System.out.println("Returning to Main Menu...");
    }
}