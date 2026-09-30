# Smart-Parking-Management-System
A GUI-based parking management system built with Java Swing for efficient vehicle and parking slot management.
# Smart Parking Management System

A GUI-based parking management system built with Java Swing for efficient vehicle and parking slot management.

## Project Overview

The Smart Parking Management System is a Java-based application designed to manage vehicles, parking slots, reservations, billing, searching, parking history, and analytics.

The system provides separate access for Admin and Customer users and uses a graphical user interface developed with Java Swing.

## Features

- Admin and Customer login
- Vehicle parking management
- Multiple vehicle types
- Specific vehicle removal by vehicle number or owner name
- Parking slot display
- Slot reservation and cancellation
- Vehicle search
- Parking billing
- Discount calculation
- Parking history
- Parking analytics
- File handling for parking data
- Exception handling
- Graphical User Interface using Java Swing

## Vehicle Types

- Car
- Bike
- Truck
- Bus
- Electric Vehicle

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- Collections
- Exception Handling
- File Handling

## Java Concepts Used

- Classes and Objects
- Constructors
- Encapsulation
- Inheritance
- Method Overriding
- Abstract Classes
- Interfaces
- Access Modifiers
- Static Members
- Arrays
- ArrayList
- Exception Handling
- Packages
- Swing Components
- Event Handling
- File Handling
- Switch-Case
- Loops and Conditional Statements

## Project Structure

```text
Smart-Parking-Management-System
│
├── model
│   ├── Vehicle.java
│   ├── Car.java
│   ├── Bike.java
│   ├── Truck.java
│   ├── Bus.java
│   ├── ElectricVehicle.java
│   └── ParkingSlot.java
│
├── interfaces
│   ├── Parkable.java
│   └── Billable.java
│
├── service
│   ├── ParkingManager.java
│   ├── BillingManager.java
│   ├── ReservationManager.java
│   ├── SearchManager.java
│   ├── LoginManager.java
│   ├── HistoryManager.java
│   ├── FileManager.java
│   └── AnalyticsManager.java
│
├── exception
│   ├── ParkingException.java
│   ├── SlotNotAvailableException.java
│   └── VehicleAlreadyParkedException.java
│
├── gui
│   ├── LoginFrame.java
│   ├── AdminFrame.java
│   ├── CustomerFrame.java
│   ├── ParkingFrame.java
│   ├── ReservationFrame.java
│   ├── SearchFrame.java
│   ├── BillingFrame.java
│   └── HistoryFrame.java
│
└── main
    └── ParkingSystem.java
    
Login Details
Admin
Username: admin
Password: admin123
Customer
Username: customer
Password: customer123

The system allows a maximum of three unsuccessful login attempts.

How to Run

1. Compile the project
Open Command Prompt inside the project folder and run:
javac model\*.java interfaces\*.java service\*.java exception\*.java gui\*.java main\*.java

3. Run the application
java main.ParkingSystem

Main Operations
Parking
Users can enter vehicle details and select the vehicle type. The system assigns an available parking slot.
Vehicle Removal

A specific vehicle can be removed using:

Vehicle Number
Owner Name
Reservation

Users can reserve an available parking slot and cancel an existing reservation.

Billing
The system calculates the parking amount based on parking hours and vehicle rate. Discounts are available for selected categories.

Search
Vehicles can be searched using:
Vehicle Number
Owner Name

History
The system records parking, removal, reservation, and cancellation activities.

Analytics
The system displays:

Total vehicles
Available slots
Reserved slots
Vehicle type information

Author
Girija D
BS(Hons with Research) in Computer Science
