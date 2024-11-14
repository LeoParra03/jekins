/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gymmembership;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author leo
 */
public class GymMembership {

    private static final Scanner scanner = new Scanner(System.in);

    // Lista de tipos de membresía disponibles
    private static final List<Membership> memberships = Arrays.asList(
            new Membership("Basic", 50),
            new Membership("Premium", 100),
            new Membership("Family", 150)
    );

    public static void main(String[] args) {
        System.out.println("Welcome to the Gym Membership Management System!");

        // 1. Selección de Membresía: Mostrar opciones y permitir al usuario elegir una membresía
        System.out.println("Please choose a membership plan:");
        for (int i = 0; i < memberships.size(); i++) {
            if (memberships.get(i).isAvailable())
                System.out.println((i + 1) + ". " + memberships.get(i).getName() + " - $" + memberships.get(i).getBaseCost());
        }

        int choice;
        Membership selectedMembership;
        do {
            choice = safelyGetIntInput();
            selectedMembership = memberships.get(choice - 1);
            if (!selectedMembership.isAvailable()) {
                System.out.println("The selected membership is not available. Please choose a different one.");
            }
        } while (!selectedMembership.isAvailable());
        selectedMembership = memberships.get(choice - 1);  // Obtener la membresía seleccionada

        // Mostrar la membresía seleccionada
        System.out.println("You selected: " + selectedMembership.getName());

        // 2. Características Adicionales: Preguntar si desea agregar características

        List<Feature> features = new ArrayList<>(addFeatures());

        // Crear un plan de membresía con las características seleccionadas
        MembershipPlan membershipPlan = new MembershipPlan(selectedMembership, features);
        for (Feature feature : features) {
            if (!feature.isAvailable()) {
                System.out.println("Feature " + feature.getName() + " is not available. Please choose a different feature.");
                features.remove(feature); // Remover características no disponibles
            }
        }
        List<MembershipPlan> membershipPlanList = new ArrayList<>();

        boolean multipleSignup = false;
        System.out.println("There is a 10% discount for all memberships if you sign up with multiple people for the same one");
        System.out.print("Would you like to sign up with other people? (yes/no): ");
        multipleSignup = safelyGetYesNoInput();

        if (multipleSignup) {
            membershipPlanList.add(membershipPlan);
            System.out.print("How many people would you like to sign up with?: ");
            int amount = safelyGetIntInput();
            for (int i = 0; i < amount; i++) {
                System.out.println("Additional Membership #" + (i + 1));
                MembershipPlan addedMembershipPlan = new MembershipPlan(selectedMembership, addFeatures());
                membershipPlanList.add(addedMembershipPlan);
                features.addAll(addedMembershipPlan.getFeatures());
            }
        }


        // 3. Calcular el costo total
        double finalCost = 0;
        if (!multipleSignup) {
            membershipPlan.calculateTotalCost();

            // Mostrar el costo después de los descuentos y recargos
            finalCost = membershipPlan.getTotalCost();
        } else {
            for (MembershipPlan currentMembershipPlan : membershipPlanList) {
                currentMembershipPlan.calculateTotalCost();
                finalCost += currentMembershipPlan.getTotalCost();
            }

            finalCost *= 0.9;
        }
        System.out.println("Final cost after discounts: $" + finalCost);
        System.out.println("\n--- Membership Plan Summary ---");
        System.out.println("Membership Type: " + selectedMembership.getName());
        System.out.println("Base Cost: $" + selectedMembership.getBaseCost());
        if (multipleSignup) System.out.println("Members: " + membershipPlanList.size());
        System.out.println("Additional Features:");
        Map<String, Integer> featureCounter = new HashMap<>();
        List<Feature> distinctFeatures = new ArrayList<>();
        for (Feature feature : features) {
            featureCounter.put(feature.getName(), featureCounter.getOrDefault(feature.getName(), 0) + 1);
            int counter = featureCounter.get(feature.getName());
            if (counter <= 1){
                distinctFeatures.add(feature);
            }
        }

        for (Feature feature: distinctFeatures){
            System.out.println("- " + featureCounter.get(feature.getName()) + " " + feature.getName() + " ($" + feature.getCost() + ")");
        }

        System.out.print("\nDo you want to confirm this membership plan? (yes/no): ");
        boolean confirmation = safelyGetYesNoInput();
        if (confirmation) {
            System.out.println("Membership plan confirmed! Thank you for signing up.");
        } else {
            System.out.println("Membership plan canceled. Please make your selections again.");
            finalCost = -1;
        }
        System.out.println("Total Cost (with discounts): $" + finalCost);
        scanner.close();
    }

    private static List<Feature> addFeatures() {
        List<Feature> featureList = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("Do you want to add any additional features?");
            System.out.println("1. Personal Training - $30");
            System.out.println("2. Group Classes - $20");
            System.out.println("3. Access to exclusive gym facilities - $50");
            System.out.println("4. Specialized Training Programs - $100");
            System.out.println("5. No more features");

            int featureChoice = safelyGetIntInput();
            Feature selectedFeature = null;
            switch (featureChoice) {
                case 1 -> selectedFeature = new Feature("Personal Training", 30);
                case 2 -> selectedFeature = new Feature("Group Classes", 20);
                case 3 -> selectedFeature = new Feature("Access to exclusive gym facilities", 50);
                case 4 -> selectedFeature = new Feature("Specialized Training Programs", 100);
                case 5 -> addMore = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (selectedFeature != null && selectedFeature.isAvailable()) {
                featureList.add(selectedFeature);
            } else if (selectedFeature != null) {
                System.out.println("The selected feature is not available. Please choose a different one.");
            }
            if (addMore) {
                System.out.println("Would you like to add another feature? (yes/no)");
                addMore = safelyGetYesNoInput();
            }
        }
        return featureList;
    }

	private static int safelyGetIntInput() {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return input;
    }

	// method for get yes/no input
	private static boolean safelyGetYesNoInput() {
		boolean validInput = false;
		boolean response = false;
		while (!validInput) {
			String input = scanner.next();
			if (input.equalsIgnoreCase("yes")) {
				response = true;
				validInput = true;
			} else if (input.equalsIgnoreCase("no")) {
				response = false;
				validInput = true;
			} else {
				System.out.println("Invalid input. Please enter 'yes' or 'no'.");
			}
		}
		return response;
	}
}
