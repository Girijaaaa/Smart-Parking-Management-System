package gui;

import model.ParkingSlot;
import model.Vehicle;
import service.ParkingManager;
import service.SearchManager;

import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {

    private ParkingManager parkingManager;
    private SearchManager searchManager;

    public SearchFrame(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        searchManager = new SearchManager();

        setTitle("Search Management");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Search Vehicle",
                SwingConstants.CENTER
        );
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JButton vehicleButton =
                new JButton("Search by Vehicle Number");

        JButton ownerButton =
                new JButton("Search by Owner Name");

        JButton displayButton =
                new JButton("Display All Vehicles");

        JButton closeButton =
                new JButton("Close");

        JPanel panel =
                new JPanel(new GridLayout(4, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 60, 30, 60
                )
        );

        panel.add(vehicleButton);
        panel.add(ownerButton);
        panel.add(displayButton);
        panel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        vehicleButton.addActionListener(
                e -> searchByVehicle()
        );

        ownerButton.addActionListener(
                e -> searchByOwner()
        );

        displayButton.addActionListener(
                e -> displayVehicles()
        );

        closeButton.addActionListener(
                e -> dispose()
        );
    }

    private void searchByVehicle() {

        String vehicleNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Vehicle Number:"
                );

        if (vehicleNumber == null ||
                vehicleNumber.trim().isEmpty()) {
            return;
        }

        Vehicle vehicle =
                searchManager.searchByVehicleNumber(
                        parkingManager.getSlots(),
                        vehicleNumber
                );

        if (vehicle != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle Found\n\n" +
                    "Vehicle Number: " +
                    vehicle.getVehicleNumber() +
                    "\nOwner: " +
                    vehicle.getOwnerName() +
                    "\nType: " +
                    vehicle.getType()
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle not found."
            );
        }
    }

    private void searchByOwner() {

        String ownerName =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Owner Name:"
                );

        if (ownerName == null ||
                ownerName.trim().isEmpty()) {
            return;
        }

        Vehicle vehicle =
                searchManager.searchByOwnerName(
                        parkingManager.getSlots(),
                        ownerName
                );

        if (vehicle != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle Found\n\n" +
                    "Vehicle Number: " +
                    vehicle.getVehicleNumber() +
                    "\nOwner: " +
                    vehicle.getOwnerName() +
                    "\nType: " +
                    vehicle.getType()
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle not found."
            );
        }
    }

    private void displayVehicles() {

        JTextArea area = new JTextArea();
        area.setEditable(false);

        ParkingSlot[] slots =
                parkingManager.getSlots();

        boolean found = false;

        for (ParkingSlot slot : slots) {

            Vehicle vehicle =
                    slot.getVehicle();

            if (vehicle != null) {

                found = true;

                area.append(
                        "Slot: " +
                        slot.getSlotNumber() +
                        "\nVehicle Number: " +
                        vehicle.getVehicleNumber() +
                        "\nOwner: " +
                        vehicle.getOwnerName() +
                        "\nType: " +
                        vehicle.getType() +
                        "\n-------------------------\n"
                );
            }
        }

        if (!found) {
            area.append(
                    "No vehicles currently parked."
            );
        }

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Vehicle Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}