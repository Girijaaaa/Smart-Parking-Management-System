package model;

public class Car extends Vehicle {

    public Car(String vehicleNumber, String ownerName, String entryTime) {
        super(vehicleNumber, ownerName, entryTime);
    }

    @Override
    public double getRate() {
        return 50.0;
    }

    @Override
    public String getType() {
        return "Car";
    }
}