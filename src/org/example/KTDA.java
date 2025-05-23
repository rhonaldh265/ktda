package org.example;

import java.util.*;

class FarmerWeight {
    private String farmerNumber;
    private double totalWeight;

    FarmerWeight(String farmerNumber, double totalWeight) {
        this.farmerNumber = farmerNumber;
        this.totalWeight = totalWeight;
    }

    public String getFarmerNumber() {
        return farmerNumber;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    @Override
    public String toString() {
        return farmerNumber + " (" + totalWeight + " kg)";
    }
}

public class KTDA {
    private static List<FarmerWeight> farmerWeights = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- KTDA Tea Collection System ---");
            System.out.println("1. Record today's weight");
            System.out.println("2. View all weights");
            System.out.println("3. View farmer's total");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> recordTodaysWeight();
                    case 2 -> viewAllWeights();
                    case 3 -> viewFarmerTotal();
                    case 4 -> {
                        System.out.println("Thank you");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private static void recordTodaysWeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter farmer number: ");
        String farmerNumber = scanner.nextLine();

        System.out.print("Enter today's weight (kg): ");
        try {
            double weight = Double.parseDouble(scanner.nextLine());
            farmerWeights.add(new FarmerWeight(farmerNumber, weight));
            System.out.println("Weight recorded successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight. Please enter a number.");
        }
    }

    private static void viewAllWeights() {
        if (farmerWeights.isEmpty()) {
            System.out.println("No weights recorded yet.");
            return;
        }
        System.out.println("All recorded weights:");
        farmerWeights.forEach(System.out::println);
    }

    private static void viewFarmerTotal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter farmer number: ");
        String farmerNumber = scanner.nextLine();

        double total = farmerWeights.stream()
                .filter(f -> f.getFarmerNumber().equals(farmerNumber))
                .mapToDouble(FarmerWeight::getTotalWeight)
                .sum();

        System.out.printf("Total weight for farmer %s: %.2f kg%n", farmerNumber, total);
    }
}