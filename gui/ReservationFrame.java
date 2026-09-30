package gui;

import model.ParkingSlot;
import service.ParkingManager;
import service.ReservationManager;

import javax.swing.*;
import java.awt.*;

public class ReservationFrame extends JFrame {

    private ParkingManager parkingManager;
    private ReservationManager reservationManager;

    public ReservationFrame(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        reservationManager = new ReservationManager();

        setTitle("Reservation Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Reservation Management",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton reserveButton = new JButton("Reserve Slot");
        JButton cancelButton = new JButton("Cancel Reservation");
        JButton displayButton = new JButton("Display Slots");
        JButton closeButton = new JButton("Close");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 80, 30, 80)
        );

        panel.add(reserveButton);
        panel.add(cancelButton);
        panel.add(displayButton);
        panel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        reserveButton.addActionListener(e -> reserveSlot());
        cancelButton.addActionListener(e -> cancelReservation());
        displayButton.addActionListener(e -> displaySlots());
        closeButton.addActionListener(e -> dispose());
    }

    private void reserveSlot() {

        String name = JOptionPane.showInputDialog(
                this,
                "Enter Name:"
        );

        if (name == null || name.trim().isEmpty()) {
            return;
        }

        String input = JOptionPane.showInputDialog(
                this,
                "Enter Slot Number:"
        );

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            int slotNumber = Integer.parseInt(input);

            if (slotNumber < 1 || slotNumber > 25) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a slot number between 1 and 25."
                );
                return;
            }

            ParkingSlot slot =
                    parkingManager.getSlots()[slotNumber - 1];

            boolean success =
                    reservationManager.reserveSlot(slot, name);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Slot " + slotNumber +
                        " reserved for " + name + "."
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Slot " + slotNumber +
                        " is already occupied or reserved."
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid slot number."
            );
        }
    }

    private void cancelReservation() {

        String input = JOptionPane.showInputDialog(
                this,
                "Enter Slot Number:"
        );

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            int slotNumber = Integer.parseInt(input);

            if (slotNumber < 1 || slotNumber > 25) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a slot number between 1 and 25."
                );
                return;
            }

            ParkingSlot slot =
                    parkingManager.getSlots()[slotNumber - 1];

            boolean success =
                    reservationManager.cancelReservation(slot);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Reservation cancelled successfully."
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "This slot is not reserved."
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid slot number."
            );
        }
    }

    private void displaySlots() {

        JTextArea area = new JTextArea();
        area.setEditable(false);

        ParkingSlot[] slots =
                parkingManager.getSlots();

        for (ParkingSlot slot : slots) {

            area.append(
                    "Slot " +
                    slot.getSlotNumber()
            );

            if (slot.isOccupied()) {

                area.append(" - Occupied");

            } else if (slot.isReserved()) {

                area.append(
                        " - Reserved by " +
                        slot.getReservedBy()
                );

            } else {

                area.append(" - Available");
            }

            area.append("\n");
        }

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Parking Slots",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}