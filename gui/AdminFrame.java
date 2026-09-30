package gui;

import main.ParkingSystem;
import service.AnalyticsManager;
import service.ParkingManager;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    private ParkingManager parkingManager;
    private AnalyticsManager analyticsManager;

    public AdminFrame(ParkingManager parkingManager) {

        this.parkingManager = parkingManager;
        analyticsManager = ParkingSystem.analyticsManager;

        setTitle("Parking Management System - Admin");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Admin Dashboard",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JButton parkingButton =
                new JButton("Parking Management");

        JButton reservationButton =
                new JButton("Reservations");

        JButton searchButton =
                new JButton("Search");

        JButton historyButton =
                new JButton("History");

        JButton analyticsButton =
                new JButton("Analytics");

        JButton logoutButton =
                new JButton("Logout");

        JPanel panel =
                new JPanel(new GridLayout(6, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 80, 25, 80
                )
        );

        panel.add(parkingButton);
        panel.add(reservationButton);
        panel.add(searchButton);
        panel.add(historyButton);
        panel.add(analyticsButton);
        panel.add(logoutButton);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        parkingButton.addActionListener(e -> {
            new ParkingFrame(parkingManager).setVisible(true);
        });

        reservationButton.addActionListener(e -> {
            new ReservationFrame(parkingManager).setVisible(true);
        });

        searchButton.addActionListener(e -> {
            new SearchFrame(parkingManager).setVisible(true);
        });

        historyButton.addActionListener(e -> {
            new HistoryFrame().setVisible(true);
        });

        analyticsButton.addActionListener(e -> {
            showAnalytics();
        });

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void showAnalytics() {

        int totalVehicles =
                analyticsManager.getTotalVehicles(
                        parkingManager.getSlots()
                );

        int availableSlots =
                analyticsManager.getAvailableSlots(
                        parkingManager.getSlots()
                );

        int reservedSlots =
                analyticsManager.getReservedSlots(
                        parkingManager.getSlots()
                );

        String message =
                "PARKING ANALYTICS\n\n" +
                "Total Vehicles: " +
                totalVehicles +
                "\nAvailable Slots: " +
                availableSlots +
                "\nReserved Slots: " +
                reservedSlots;

        JOptionPane.showMessageDialog(
                this,
                message,
                "Analytics",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}