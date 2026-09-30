package service;

import model.ParkingSlot;
import model.Vehicle;

public class AnalyticsManager {

    public int getTotalVehicles(ParkingSlot[] slots) {

        int count = 0;

        for (ParkingSlot slot : slots) {

            if (slot.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    public int getAvailableSlots(ParkingSlot[] slots) {

        int count = 0;

        for (ParkingSlot slot : slots) {

            if (!slot.isOccupied() &&
                    !slot.isReserved()) {
                count++;
            }
        }

        return count;
    }

    public int getReservedSlots(ParkingSlot[] slots) {

        int count = 0;

        for (ParkingSlot slot : slots) {

            if (slot.isReserved()) {
                count++;
            }
        }

        return count;
    }

    public void displayAnalytics(ParkingSlot[] slots) {

        int totalVehicles =
                getTotalVehicles(slots);

        int availableSlots =
                getAvailableSlots(slots);

        int reservedSlots =
                getReservedSlots(slots);

        System.out.println(
                "Total Vehicles: " +
                totalVehicles
        );

        System.out.println(
                "Available Slots: " +
                availableSlots
        );

        System.out.println(
                "Reserved Slots: " +
                reservedSlots
        );
    }

    public void displayVehicleTypes(ParkingSlot[] slots) {

        int cars = 0;
        int bikes = 0;
        int trucks = 0;
        int buses = 0;
        int electricVehicles = 0;

        for (ParkingSlot slot : slots) {

            Vehicle vehicle =
                    slot.getVehicle();

            if (vehicle != null) {

                String type =
                        vehicle.getType();

                switch (type) {

                    case "Car":
                        cars++;
                        break;

                    case "Bike":
                        bikes++;
                        break;

                    case "Truck":
                        trucks++;
                        break;

                    case "Bus":
                        buses++;
                        break;

                    case "Electric Vehicle":
                        electricVehicles++;
                        break;
                }
            }
        }

        System.out.println("Cars: " + cars);
        System.out.println("Bikes: " + bikes);
        System.out.println("Trucks: " + trucks);
        System.out.println("Buses: " + buses);
        System.out.println(
                "Electric Vehicles: " +
                electricVehicles
        );
    }
}