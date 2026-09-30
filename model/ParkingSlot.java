package model;

public class ParkingSlot {

    private int slotNumber;
    private boolean occupied;
    private boolean reserved;
    private String reservedBy;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.occupied = false;
        this.reserved = false;
        this.reservedBy = null;
        this.vehicle = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }

    public void reserveSlot(String name) {
        this.reserved = true;
        this.reservedBy = name;
    }

    public void cancelReservation() {
        this.reserved = false;
        this.reservedBy = null;
    }

    public void displaySlot() {
        System.out.println("Slot Number: " + slotNumber);
        System.out.println("Occupied: " + occupied);
        System.out.println("Reserved: " + reserved);

        if (reserved) {
            System.out.println("Reserved By: " + reservedBy);
        }

        if (vehicle != null) {
            System.out.println(
                    "Vehicle Number: " +
                    vehicle.getVehicleNumber()
            );

            System.out.println(
                    "Vehicle Type: " +
                    vehicle.getType()
            );
        }
    }
}