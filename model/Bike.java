package model;

public class Bike extends Vehicle {

    public Bike(String vehicleNumber, String ownerName, String entryTime) {
        super(vehicleNumber, ownerName, entryTime);
    }

    @Override
    public double getRate() {
        return 30.0;
    }

    @Override
    public String getType() {
        return "Bike";
    }
}