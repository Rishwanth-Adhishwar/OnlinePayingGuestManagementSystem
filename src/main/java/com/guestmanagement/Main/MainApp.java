package com.guestmanagement.Main;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.service.AdminMenu;
import com.guestmanagement.service.TenantMenu;

// Main class - Entry point of the application
public class MainApp {

    // Main method
    public static void main(String[] args) {

        System.out.println("\n");
        System.out.println("\u2728==================================\u2728");
        System.out.println("   \uD83C\uDFE0  PAYING GUEST MANAGEMENT SYSTEM  \uD83C\uDFE0");
        System.out.println("\u2728==================================\u2728");

        System.out.println();

        int choice = -1;

        // Loop for continuous execution
        while (choice != 0) {

            System.out.println("\n\uD83D\uDCAC \u2502\u2502\u2502 MAIN MENU \u2502\u2502\u2502 \uD83D\uDCAC");
            System.out.println("\uD83D\uDC64 1. Tenant Portal");
            System.out.println("\uD83D\uDCBB 2. Admin Portal");
            System.out.println("\uD83D\uDEAA 0. Exit");

            choice = com.guestmanagement.service.Input.readInt("Enter choice: ");

            // Navigation with exception handling
            try {
                if (choice == 1) {
                    TenantMenu.show();

                } else if (choice == 2) {
                    AdminMenu.show();

                } else if (choice == 0) {
                    System.out.println("\n\uD83D\uDE4F Thank you for using PG System! \uD83D\uDE4F");

                } else {
                    System.out.println("  \u26A0 Invalid choice.");
                }
            } catch (Exception e) {
                // Catching any unexpected exception at top level
                System.out.println("  An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
