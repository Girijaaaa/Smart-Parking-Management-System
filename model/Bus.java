package model;

public class Bus extends Vehicle {

    public Bus(String vehicleNumber, String ownerName, String entryTime) {
        super(vehicleNumber, ownerName, entryTime);
    }

    @Override
    public double getRate() {
        return 80.0;
    }

    @Override
    public String getType() {
        return "Bus";
    }
}