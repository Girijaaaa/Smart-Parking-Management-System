package model;

public abstract class Vehicle {
    private String vehicleNumber;
    private String ownerName;
    private String entryTime;

    public Vehicle(String vehicleNumber, String ownerName, String entryTime) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.entryTime = entryTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public abstract double getRate();

    public abstract String getType();

    public void displayVehicle() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Vehicle Type: " + getType());
        System.out.println("Entry Time: " + entryTime);
    }
}