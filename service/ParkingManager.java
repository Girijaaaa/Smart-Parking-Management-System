package service;

import exception.SlotNotAvailableException;
import exception.VehicleAlreadyParkedException;
import interfaces.Parkable;
import model.ParkingSlot;
import model.Vehicle;

public class ParkingManager implements Parkable {

    private static ParkingSlot[] slots;
    private HistoryManager historyManager;
    private FileManager fileManager;

    public ParkingManager(int totalSlots) {

        if (slots == null) {

            slots = new ParkingSlot[totalSlots];

            for (int i = 0; i < totalSlots; i++) {
                slots[i] = new ParkingSlot(i + 1);
            }
        }

        historyManager = new HistoryManager();
        fileManager = new FileManager();
    }

    @Override
    public void parkVehicle(Vehicle vehicle) {

        try {

            for (ParkingSlot slot : slots) {

                Vehicle existingVehicle =
                        slot.getVehicle();

                if (existingVehicle != null &&
                        existingVehicle.getVehicleNumber()
                                .equalsIgnoreCase(
                                        vehicle.getVehicleNumber()
                                )) {

                    throw new VehicleAlreadyParkedException(
                            "Vehicle is already parked."
                    );
                }
            }

            for (ParkingSlot slot : slots) {

                if (!slot.isOccupied() &&
                        !slot.isReserved()) {

                    slot.parkVehicle(vehicle);

                    historyManager.addRecord(
                            "Vehicle Parked - " +
                            vehicle.getVehicleNumber() +
                            " | Slot: " +
                            slot.getSlotNumber()
                    );

                    fileManager.saveParkingData(slots);

                    System.out.println(
                            "Vehicle parked in Slot " +
                            slot.getSlotNumber()
                    );

                    return;
                }
            }

            throw new SlotNotAvailableException(
                    "No parking slot available."
            );

        } catch (VehicleAlreadyParkedException e) {

            System.out.println(e.getMessage());

        } catch (SlotNotAvailableException e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeVehicle() {

        System.out.println(
                "Use removeVehicleByNumber() or removeVehicleByOwner()."
        );
    }

    public boolean removeVehicleByNumber(
            String vehicleNumber) {

        for (ParkingSlot slot : slots) {

            Vehicle vehicle =
                    slot.getVehicle();

            if (vehicle != null &&
                    vehicle.getVehicleNumber()
                            .equalsIgnoreCase(vehicleNumber)) {

                historyManager.addRecord(
                        "Vehicle Removed - " +
                        vehicle.getVehicleNumber() +
                        " | Slot: " +
                        slot.getSlotNumber()
                );

                slot.removeVehicle();

                fileManager.saveParkingData(slots);

                System.out.println(
                        "Vehicle removed from Slot " +
                        slot.getSlotNumber()
                );

                return true;
            }
        }

        return false;
    }

    public boolean removeVehicleByOwner(
            String ownerName) {

        for (ParkingSlot slot : slots) {

            Vehicle vehicle =
                    slot.getVehicle();

            if (vehicle != null &&
                    vehicle.getOwnerName()
                            .equalsIgnoreCase(ownerName)) {

                historyManager.addRecord(
                        "Vehicle Removed - " +
                        vehicle.getVehicleNumber() +
                        " | Slot: " +
                        slot.getSlotNumber()
                );

                slot.removeVehicle();

                fileManager.saveParkingData(slots);

                System.out.println(
                        "Vehicle removed from Slot " +
                        slot.getSlotNumber()
                );

                return true;
            }
        }

        return false;
    }

    public void displaySlots() {

        for (ParkingSlot slot : slots) {

            slot.displaySlot();

            System.out.println(
                    "--------------------"
            );
        }
    }

    public ParkingSlot[] getSlots() {
        return slots;
    }
}