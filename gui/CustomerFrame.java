package gui;

import service.ParkingManager;

import javax.swing.*;
import java.awt.*;

public class CustomerFrame extends JFrame {

    private ParkingManager parkingManager;

    public CustomerFrame(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;

        setTitle("Parking Management System - Customer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Customer Dashboard",
                SwingConstants.CENTER
        );
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JButton parkingButton =
                new JButton("Park Vehicle");
        JButton reservationButton =
                new JButton("Reserve Slot");
        JButton searchButton =
                new JButton("Search Vehicle");
        JButton billingButton =
                new JButton("Billing");
        JButton logoutButton =
                new JButton("Logout");

        JPanel panel =
                new JPanel(new GridLayout(5, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 80, 30, 80
                )
        );

        panel.add(parkingButton);
        panel.add(reservationButton);
        panel.add(searchButton);
        panel.add(billingButton);
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

        billingButton.addActionListener(e -> {
            new BillingFrame(parkingManager).setVisible(true);
        });

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}