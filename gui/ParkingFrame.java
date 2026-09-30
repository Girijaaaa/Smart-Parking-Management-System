package gui;

import model.Bus;
import model.Car;
import model.ElectricVehicle;
import model.ParkingSlot;
import model.Truck;
import model.Bike;
import model.Vehicle;
import service.ParkingManager;

import javax.swing.*;
import java.awt.*;

public class ParkingFrame extends JFrame {

    private ParkingManager parkingManager;

    public ParkingFrame(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;

        setTitle("Parking Management");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Parking Management",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JButton parkButton =
                new JButton("Park Vehicle");

        JButton removeButton =
                new JButton("Remove Vehicle");

        JButton displayButton =
                new JButton("Display Slots");

        JButton closeButton =
                new JButton("Close");

        JPanel panel =
                new JPanel(new GridLayout(4, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 100, 30, 100
                )
        );

        panel.add(parkButton);
        panel.add(removeButton);
        panel.add(displayButton);
        panel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        parkButton.addActionListener(
                e -> parkVehicle()
        );

        removeButton.addActionListener(
                e -> removeVehicle()
        );

        displayButton.addActionListener(
                e -> displaySlots()
        );

        closeButton.addActionListener(
                e -> dispose()
        );
    }

    private void parkVehicle() {

        String vehicleNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Vehicle Number:"
                );

        if (vehicleNumber == null ||
                vehicleNumber.trim().isEmpty()) {
            return;
        }

        String ownerName =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Owner Name:"
                );

        if (ownerName == null ||
                ownerName.trim().isEmpty()) {
            return;
        }

        String[] types = {
                "Car",
                "Bike",
                "Truck",
                "Bus",
                "Electric Vehicle"
        };

        String type =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select Vehicle Type:",
                        "Vehicle Type",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        types,
                        types[0]
                );

        if (type == null) {
            return;
        }

        Vehicle vehicle;

        switch (type) {

            case "Car":
                vehicle = new Car(
                        vehicleNumber,
                        ownerName,
                        "Current Time"
                );
                break;

            case "Bike":
                vehicle = new Bike(
                        vehicleNumber,
                        ownerName,
                        "Current Time"
                );
                break;

            case "Truck":
                vehicle = new Truck(
                        vehicleNumber,
                        ownerName,
                        "Current Time"
                );
                break;

            case "Bus":
                vehicle = new Bus(
                        vehicleNumber,
                        ownerName,
                        "Current Time"
                );
                break;

            default:
                vehicle = new ElectricVehicle(
                        vehicleNumber,
                        ownerName,
                        "Current Time"
                );
        }

        parkingManager.parkVehicle(vehicle);

        JOptionPane.showMessageDialog(
                this,
                "Parking process completed."
        );
    }

    private void removeVehicle() {

        String[] options = {
                "Vehicle Number",
                "Owner Name"
        };

        String choice =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Remove vehicle using:",
                        "Remove Vehicle",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == null) {
            return;
        }

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter " + choice + ":"
                );

        if (input == null ||
                input.trim().isEmpty()) {
            return;
        }

        boolean removed;

        if (choice.equals("Vehicle Number")) {

            removed =
                    parkingManager.removeVehicleByNumber(
                            input
                    );

        } else {

            removed =
                    parkingManager.removeVehicleByOwner(
                            input
                    );
        }

        if (removed) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle removed successfully."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle not found."
            );
        }
    }

    private void displaySlots() {

        JFrame frame =
                new JFrame("Parking Slots");

        frame.setSize(500, 600);
        frame.setLocationRelativeTo(this);

        JTextArea area =
                new JTextArea();

        area.setEditable(false);

        ParkingSlot[] slots =
                parkingManager.getSlots();

        for (ParkingSlot slot : slots) {

            area.append(
                    "Slot " +
                    slot.getSlotNumber()
            );

            if (slot.isOccupied()) {

                area.append(
                        " - Occupied\n"
                );

                Vehicle vehicle =
                        slot.getVehicle();

                area.append(
                        "   Vehicle: " +
                        vehicle.getVehicleNumber() +
                        "\n"
                );

                area.append(
                        "   Owner: " +
                        vehicle.getOwnerName() +
                        "\n"
                );

                area.append(
                        "   Type: " +
                        vehicle.getType() +
                        "\n"
                );

                area.append(
                        "   Rate: ₹" +
                        vehicle.getRate() +
                        "\n"
                );

            } else if (slot.isReserved()) {

                area.append(
                        " - Reserved by " +
                        slot.getReservedBy() +
                        "\n"
                );

            } else {

                area.append(
                        " - Available\n"
                );
            }

            area.append(
                    "-------------------------\n"
            );
        }

        frame.add(
                new JScrollPane(area)
        );

        frame.setVisible(true);
    }
}