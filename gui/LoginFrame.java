package gui;

import main.ParkingSystem;
import service.LoginManager;
import service.ParkingManager;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginManager loginManager;
    private ParkingManager parkingManager;

    public LoginFrame() {

        loginManager = new LoginManager();
        parkingManager = ParkingSystem.parkingManager;

        setTitle("Parking Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel =
                new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel =
                new JLabel("Username:");

        JLabel passwordLabel =
                new JLabel("Password:");

        usernameField =
                new JTextField();

        passwordField =
                new JPasswordField();

        JButton loginButton =
                new JButton("Login");

        JButton exitButton =
                new JButton("Exit");

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 40, 40, 40
                )
        );

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(loginButton);
        panel.add(exitButton);

        add(panel);

        loginButton.addActionListener(
                e -> login()
        );

        exitButton.addActionListener(
                e -> System.exit(0)
        );
    }

    private void login() {

        String username =
                usernameField.getText();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String role =
                loginManager.login(
                        username,
                        password
                );

        if (role == null) {

            int remaining =
                    3 - loginManager.getAttempts();

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.\n" +
                    "Attempts remaining: " +
                    remaining,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

        } else if (role.equals("BLOCKED")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Maximum login attempts reached.\n" +
                    "Login blocked.",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            System.exit(0);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!\nWelcome " +
                    role
            );

            dispose();

            if (role.equals("Admin")) {

                new AdminFrame(
                        parkingManager
                ).setVisible(true);

            } else {

                new CustomerFrame(
                        parkingManager
                ).setVisible(true);
            }
        }
    }
}