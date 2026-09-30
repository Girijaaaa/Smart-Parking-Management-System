package gui;

import model.ParkingSlot;
import model.Vehicle;
import service.BillingManager;
import service.ParkingManager;

import javax.swing.*;
import java.awt.*;

public class BillingFrame extends JFrame {

    private ParkingManager parkingManager;
    private BillingManager billingManager;

    public BillingFrame(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        billingManager = new BillingManager();

        setTitle("Parking Billing");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Parking Billing",
                SwingConstants.CENTER
        );
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JButton billButton = new JButton("Generate Bill");
        JButton displayButton =
                new JButton("Display Parked Vehicles");
        JButton closeButton = new JButton("Close");

        JPanel panel =
                new JPanel(new GridLayout(3, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 80, 40, 80
                )
        );

        panel.add(billButton);
        panel.add(displayButton);
        panel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        billButton.addActionListener(e -> generateBill());
        displayButton.addActionListener(e -> displayVehicles());
        closeButton.addActionListener(e -> dispose());
    }

    private void generateBill() {

        String vehicleNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Vehicle Number:"
                );

        if (vehicleNumber == null ||
                vehicleNumber.trim().isEmpty()) {
            return;
        }

        Vehicle foundVehicle = null;

        for (ParkingSlot slot :
                parkingManager.getSlots()) {

            Vehicle vehicle = slot.getVehicle();

            if (vehicle != null &&
                    vehicle.getVehicleNumber()
                            .equalsIgnoreCase(vehicleNumber)) {

                foundVehicle = vehicle;
                break;
            }
        }

        if (foundVehicle == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle not found."
            );

            return;
        }

        String hoursInput =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Parking Hours:"
                );

        if (hoursInput == null ||
                hoursInput.trim().isEmpty()) {
            return;
        }

        try {

            double hours =
                    Double.parseDouble(hoursInput);

            if (hours <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hours must be greater than zero."
                );

                return;
            }

            String[] categories = {
                    "None",
                    "Student",
                    "Staff",
                    "Senior"
            };

            String category =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select Discount Category:",
                            "Discount",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            categories,
                            categories[0]
                    );

            if (category == null) {
                return;
            }

            double amount =
                    billingManager.calculateBill(
                            hours,
                            foundVehicle.getRate()
                    );

            double discount =
                    billingManager.calculateDiscount(
                            amount,
                            category
                    );

            double finalAmount =
                    amount - discount;

            String bill =
                    "PARKING BILL\n\n" +
                    "Vehicle Number: " +
                    foundVehicle.getVehicleNumber() +
                    "\nOwner Name: " +
                    foundVehicle.getOwnerName() +
                    "\nVehicle Type: " +
                    foundVehicle.getType() +
                    "\nParking Hours: " +
                    hours +
                    "\nRate per Hour: ₹" +
                    foundVehicle.getRate() +
                    "\nAmount: ₹" +
                    amount +
                    "\nDiscount: ₹" +
                    discount +
                    "\nFinal Amount: ₹" +
                    finalAmount;

            JOptionPane.showMessageDialog(
                    this,
                    bill,
                    "Bill",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number."
            );
        }
    }

    private void displayVehicles() {

        JTextArea area = new JTextArea();
        area.setEditable(false);

        boolean found = false;

        for (ParkingSlot slot :
                parkingManager.getSlots()) {

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
                        "\nRate: ₹" +
                        vehicle.getRate() +
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
                "Parked Vehicles",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}