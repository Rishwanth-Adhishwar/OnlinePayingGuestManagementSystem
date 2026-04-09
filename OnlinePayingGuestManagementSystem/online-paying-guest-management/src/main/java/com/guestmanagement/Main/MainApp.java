package com.guestmanagement.Main;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This is the main entry point of the
 * Paying Guest Management System.
 * It provides the main menu to access
 * Tenant Portal and Admin Portal.
 *
 * OOP Concepts Used :
 * - Class
 * - Main Method
 * - Static Method Calls
 * - Conditional Statements
 * - Loop
 *
 * ============================================================
 */

import com.guestmanagement.service.AdminMenu;
import com.guestmanagement.service.TenantMenu;

// Class
public class MainApp {

    // Main method / program execution starts here
    public static void main(String args[]) {
        System.out.println("\n");
        System.out.println("\u2728==================================\u2728");
        System.out.println("   \uD83C\uDFE0  PAYING GUEST MANAGEMENT SYSTEM  \uD83C\uDFE0");
        System.out.println("\u2728==================================\u2728");

        System.out.println();

        // Variable declaration
        int choice = -1;

        // While loop / main menu execution
        while (choice != 0) {
            System.out.println("\n\uD83D\uDCAC \u2502\u2502\u2502 MAIN MENU \u2502\u2502\u2502 \uD83D\uDCAC");
            System.out.println("\uD83D\uDC64 1. Tenant Portal");
            System.out.println("\uD83D\uDCBB 2. Admin Portal");
            System.out.println("\uD83D\uDEAA 0. Exit");

            choice = com.guestmanagement.service.Input.readInt("Enter choice: ");

            // Conditional statements
            if (choice == 1) {
                TenantMenu.show();
            } else if (choice == 2) {
                AdminMenu.show();
            } else if (choice == 0) {
                System.out.println("\n\uD83D\uDE4F Thank you for using PG System! \uD83D\uDE4F");
            } else {
                System.out.println("  \u26A0 Invalid choice.");
            }
        }
    }
}