package service;

import interfaces.Billable;

public class BillingManager implements Billable {

    @Override
    public double calculateBill(double hours, double rate) {
        if (hours <= 0) {
            return 0;
        }

        return hours * rate;
    }

    public double calculateDiscount(double amount, String category) {
        if (category.equalsIgnoreCase("Student")) {
            return amount * 0.10;
        } else if (category.equalsIgnoreCase("Staff")) {
            return amount * 0.15;
        } else if (category.equalsIgnoreCase("Senior")) {
            return amount * 0.20;
        }

        return 0;
    }

    public double calculateFinalAmount(double amount, String category) {
        double discount = calculateDiscount(amount, category);
        return amount - discount;
    }

    public void displayBill(double hours, double rate, String category) {
        double amount = calculateBill(hours, rate);
        double discount = calculateDiscount(amount, category);
        double finalAmount = amount - discount;

        System.out.println("Parking Hours: " + hours);
        System.out.println("Rate per Hour: " + rate);
        System.out.println("Amount: " + amount);
        System.out.println("Discount: " + discount);
        System.out.println("Final Amount: " + finalAmount);
    }
}