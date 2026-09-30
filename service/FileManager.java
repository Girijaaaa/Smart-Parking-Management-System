package service;

import model.ParkingSlot;
import model.Vehicle;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String FILE_NAME =
            "parking_data.txt";

    public void saveParkingData(
            ParkingSlot[] slots) {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            for (ParkingSlot slot : slots) {

                Vehicle vehicle =
                        slot.getVehicle();

                if (vehicle != null) {

                    writer.write(
                            "Slot: " +
                            slot.getSlotNumber() +
                            ", Vehicle: " +
                            vehicle.getVehicleNumber() +
                            ", Owner: " +
                            vehicle.getOwnerName() +
                            ", Type: " +
                            vehicle.getType() +
                            "\n"
                    );
                }
            }

            writer.close();

            System.out.println(
                    "Parking data saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving parking data."
            );
        }
    }
}