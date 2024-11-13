/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gymmembership;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            System.out.println((i + 1) + ". " + memberships.get(i).getName() + " - $" + memberships.get(i).getBaseCost());
        }

        int choice = scanner.nextInt();
        Membership selectedMembership = memberships.get(choice - 1);  // Obtener la membresía seleccionada

        // Mostrar la membresía seleccionada
        System.out.println("You selected: " + selectedMembership.getName());

        // 2. Características Adicionales: Preguntar si desea agregar características

        List<Feature> features = new ArrayList<>(addFeatures());


        // Crear un plan de membresía con las características seleccionadas
        MembershipPlan membershipPlan = new MembershipPlan(selectedMembership, features);
        List<MembershipPlan> membershipPlanList = new ArrayList<>();

        boolean multipleSignup = false;
        System.out.println("There is a 10% discount for all memberships if you sign up with multiple people for the same one");
        System.out.print("Would you like to sign up with other people? (yes/no): ");
        String multipleSignupChoice = scanner.next();
        multipleSignup = multipleSignupChoice.equalsIgnoreCase("yes");

        if (multipleSignup){
            membershipPlanList.add(membershipPlan);
            System.out.print("How many people would you like to sign up with?: ");
            int amount = scanner.nextInt();
            for (int i = 0; i < amount; i++) {
                System.out.println("Additional Membership #" + (i + 1));
                membershipPlanList.add(new MembershipPlan(selectedMembership, addFeatures()));
            }
        }


        // 3. Calcular el costo total
        double finalCost = 0;
        if (!multipleSignup) {
            membershipPlan.calculateTotalCost();

            // Mostrar el costo después de los descuentos y recargos
            finalCost = membershipPlan.getTotalCost();
        } else {
            for (MembershipPlan currentMembershipPlan: membershipPlanList){
                currentMembershipPlan.calculateTotalCost();
                finalCost += currentMembershipPlan.getTotalCost();
            }

            finalCost *= 0.9;
        }
        System.out.println("Final cost after discounts: $" + finalCost);
        scanner.close();
    }

    private static List<Feature> addFeatures(){
        List<Feature> featureList = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("Do you want to add any additional features?");
            System.out.println("1. Personal Training - $30");
            System.out.println("2. Group Classes - $20");
            System.out.println("3. No more features");

            int featureChoice = scanner.nextInt();
            if (featureChoice == 1) {
                featureList.add(new Feature("Personal Training", 30));
            } else if (featureChoice == 2) {
                featureList.add(new Feature("Group Classes", 20));
            } else if (featureChoice == 3) {
                addMore = false;  // Terminar el bucle
            }

            if (addMore) {
                System.out.println("Would you like to add another feature? (yes/no)");
                String response = scanner.next();
                addMore = response.equalsIgnoreCase("yes");
            }
        }
        return featureList;
    }
}
