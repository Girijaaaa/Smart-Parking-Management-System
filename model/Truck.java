package model;

public class Truck extends Vehicle {

    public Truck(String vehicleNumber, String ownerName, String entryTime) {
        super(vehicleNumber, ownerName, entryTime);
    }

    @Override
    public double getRate() {
        return 70.0;
    }

    @Override
    public String getType() {
        return "Truck";
    }
}