package service;

import model.ParkingSlot;

public class ReservationManager {

    private HistoryManager historyManager;

    public ReservationManager() {
        historyManager = new HistoryManager();
    }

    public boolean reserveSlot(
            ParkingSlot slot,
            String name) {

        if (!slot.isOccupied() &&
                !slot.isReserved()) {

            slot.reserveSlot(name);

            historyManager.addRecord(
                    "Slot Reserved - Slot: " +
                    slot.getSlotNumber() +
                    " | Reserved By: " +
                    name
            );

            System.out.println(
                    "Slot " +
                    slot.getSlotNumber() +
                    " reserved for " +
                    name
            );

            return true;
        }

        System.out.println(
                "Slot cannot be reserved."
        );

        return false;
    }

    public boolean cancelReservation(
            ParkingSlot slot) {

        if (slot.isReserved()) {

            String name =
                    slot.getReservedBy();

            slot.cancelReservation();

            historyManager.addRecord(
                    "Reservation Cancelled - Slot: " +
                    slot.getSlotNumber() +
                    " | Reserved By: " +
                    name
            );

            System.out.println(
                    "Reservation for Slot " +
                    slot.getSlotNumber() +
                    " cancelled."
            );

            return true;
        }

        System.out.println(
                "Slot is not reserved."
        );

        return false;
    }
}