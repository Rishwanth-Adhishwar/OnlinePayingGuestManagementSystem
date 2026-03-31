package com.guestmanagement.Main;

import com.guestmanagement.service.AdminMenu;
import com.guestmanagement.service.TenantMenu;

public class MainApp {
    public static void main(String args[]) {
        System.out.println("==================================");
        System.out.println("   PAYING GUEST MANAGEMENT SYSTEM  ");
        System.out.println("==================================");

        System.out.println();
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Tenant Portal");
            System.out.println("2. Admin Portal");
            System.out.println("0. Exit");
            choice = com.guestmanagement.service.Input.readInt("Enter choice: ");

            if (choice == 1) {
                TenantMenu.show();
            } else if (choice == 2) {
                AdminMenu.show();
            } else if (choice == 0) {
                System.out.println("\nThank you for using PG System!");
            } else {
                System.out.println("  Invalid choice.");
            }
        }
    }
}
