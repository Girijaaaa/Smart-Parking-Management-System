package model;

public class ElectricVehicle extends Vehicle {

    public ElectricVehicle(String vehicleNumber, String ownerName, String entryTime) {
        super(vehicleNumber, ownerName, entryTime);
    }

    @Override
    public double getRate() {
        return 40.0;
    }

    @Override
    public String getType() {
        return "Electric Vehicle";
    }
}