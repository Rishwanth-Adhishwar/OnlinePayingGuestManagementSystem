package com.guestmanagement.Main;

import com.guestmanagement.service.AdminMenu;
import com.guestmanagement.service.Input;
import com.guestmanagement.service.TenantMenu;

//main11
public class MainApp {
	public static void main(String[] args) {
		System.out.println("================================");
        System.out.println("   PG MANAGEMENT SYSTEM");
        System.out.println("================================");

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Tenant Portal");
            System.out.println("0. Exit");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) {
                // First check login, then show admin menu
                if (AdminMenu.login()) {
                    AdminMenu.show();
                }
            } else if (choice == 2) {
                TenantMenu.show();
            } else if (choice == 0) {
                System.out.println("  Goodbye!");
            } else {
                System.out.println("  Invalid choice. Try again.");
            }
        }
	}
}
