package interfaces;

import model.Vehicle;

public interface Parkable {
    void parkVehicle(Vehicle vehicle);
    void removeVehicle();
}