package com.guestmanagement.menu;

import java.util.Scanner;
import com.guestmanagement.service.TenantService;

public class TenantMenu {

    private TenantService tenantService;
    private Scanner scanner;

    public TenantMenu() {
        tenantService = new TenantService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        int choice;

        do {
            System.out.println("\n---- Tenant Menu ----");
            System.out.println("1. Start Session (Login)");
            System.out.println("2. View Tenant Profile");
            System.out.println("3. Update Tenant Profile");
            System.out.println("4. Delete Tenant Account");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    startSession();
                    break;

                case 2:
                    tenantService.viewProfile();
                    break;

                case 3:
                    updateTenantProfile();
                    break;

                case 4:
                    tenantService.deleteAccount();
                    break;

                case 5:
                    tenantService.logout();
                    break;

                case 6:
                    System.out.println("Exiting Tenant Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    private void startSession() {

        System.out.print("Enter Tenant Session ID: ");
        String sessionId = scanner.nextLine();

        tenantService.startSession(sessionId);
    }

    private void updateTenantProfile() {

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine();

        tenantService.updateProfile(name, email, phone);
    }
}