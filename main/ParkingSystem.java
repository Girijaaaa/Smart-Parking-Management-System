package main;

import gui.LoginFrame;
import service.AnalyticsManager;
import service.FileManager;
import service.ParkingManager;

public class ParkingSystem {

    public static ParkingManager parkingManager;
    public static FileManager fileManager;
    public static AnalyticsManager analyticsManager;

    public static void main(String[] args) {

        parkingManager = new ParkingManager(25);
        fileManager = new FileManager();
        analyticsManager = new AnalyticsManager();

        new LoginFrame().setVisible(true);
    }
}