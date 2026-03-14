package com.guestmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    static List<Admin> adminList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public Admin(int userId, String name, String email, String password, String phone) {
        super(userId, name, email, password, phone);
    }

    // ADMIN REGISTER (optional)
    public static void registerAdmin() {

        System.out.println("Enter Admin ID:");
        int id = sc.nextInt();

        System.out.println("Enter Name:");
        String name = sc.next();

        System.out.println("Enter Email:");
        String email = sc.next();

        System.out.println("Enter Password:");
        String password = sc.next();

        System.out.println("Enter Phone:");
        String phone = sc.next();

        Admin admin = new Admin(id, name, email, password, phone);

        adminList.add(admin);

        System.out.println("Admin Account Created Successfully!");
    }

    // ADMIN LOGIN
    public static void login() {

        System.out.println("Enter Admin Email:");
        String email = sc.next();

        System.out.println("Enter Admin Password:");
        String password = sc.next();

        for (Admin a : adminList) {

            if (a.email.equals(email) && a.password.equals(password)) {

                System.out.println("Admin Login Successful!");
                return;
            }
        }

        System.out.println("Invalid Admin Email or Password!");
    }

    // ADMIN LOGOUT
    public static void logout() {

        System.out.println("Admin Logout Successful!");
    }
}