-- Create Database
CREATE DATABASE IF NOT EXISTS guest_db;
USE guest_db;

-- Admins Table
CREATE TABLE IF NOT EXISTS admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50)
);

-- Properties Table
CREATE TABLE IF NOT EXISTS properties (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    location VARCHAR(200),
    owner VARCHAR(100)
);

-- Tenants Table
CREATE TABLE IF NOT EXISTS tenants (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50),
    phone VARCHAR(20)
);

-- Rooms Table
CREATE TABLE IF NOT EXISTS rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    room_no VARCHAR(20),
    type VARCHAR(50),
    rent DOUBLE,
    available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (property_id) REFERENCES properties(id)
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenant_id INT,
    room_id INT,
    date VARCHAR(20),
    FOREIGN KEY (tenant_id) REFERENCES tenants(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);

-- Payments Table
CREATE TABLE IF NOT EXISTS payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT,
    amount DOUBLE,
    mode VARCHAR(50),
    date VARCHAR(20),
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

-- Messages Table
CREATE TABLE IF NOT EXISTS messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT,
    receiver_id INT,
    message TEXT,
    date VARCHAR(20)
);

-- Insert Default Admin
INSERT INTO admins (name, email, password) VALUES ('Admin', 'admin@pg.com', 'admin123');
