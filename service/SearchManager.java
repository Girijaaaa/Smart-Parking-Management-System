package service;

import model.ParkingSlot;
import model.Vehicle;

public class SearchManager {

    public Vehicle searchByVehicleNumber(ParkingSlot[] slots, String vehicleNumber) {
        for (ParkingSlot slot : slots) {
            Vehicle vehicle = slot.getVehicle();

            if (vehicle != null && vehicle.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) {
                return vehicle;
            }
        }

        return null;
    }

    public Vehicle searchByOwnerName(ParkingSlot[] slots, String ownerName) {
        for (ParkingSlot slot : slots) {
            Vehicle vehicle = slot.getVehicle();

            if (vehicle != null && vehicle.getOwnerName().equalsIgnoreCase(ownerName)) {
                return vehicle;
            }
        }

        return null;
    }
}