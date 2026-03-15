package com.guestmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tenant extends User {

    static List<Tenant> tenantList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public Tenant(int userId, String name, String email, String password, String phone) {
        super(userId, name, email, password, phone);
    }

    // REGISTER METHOD
    public static void register() {

        System.out.println("Enter User ID:");
        int id = sc.nextInt();

        System.out.println("Enter Name:");
        String name = sc.next();

        System.out.println("Enter Email:");
        String email = sc.next();

        System.out.println("Enter Password:");
        String password = sc.next();

        System.out.println("Enter Phone:");
        String phone = sc.next();

        Tenant tenant = new Tenant(id, name, email, password, phone);

        tenantList.add(tenant);

        System.out.println("Account Created Successfully!");
    }

    // LOGIN METHOD
    public static void login() {

        System.out.println("Enter Email:");
        String email = sc.next();

        System.out.println("Enter Password:");
        String password = sc.next();

        for (Tenant t : tenantList) {

            if (t.email.equals(email) && t.password.equals(password)) {
                System.out.println("Login Successful!");
                return;
            }
        }

        System.out.println("Invalid Email or Password!");
    }

    // LOGOUT METHOD
    public static void logout() {

        System.out.println("Logout Successful!");
    }
}