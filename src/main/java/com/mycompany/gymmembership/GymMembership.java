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
 *
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
        List<Feature> features = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("Do you want to add any additional features?");
            System.out.println("1. Personal Training - $30");
            System.out.println("2. Group Classes - $20");
            System.out.println("3. No more features");

            int featureChoice = scanner.nextInt();
            if (featureChoice == 1) {
                features.add(new Feature("Personal Training", 30));
            } else if (featureChoice == 2) {
                features.add(new Feature("Group Classes", 20));
            } else if (featureChoice == 3) {
                addMore = false;  // Terminar el bucle
            }

            if (addMore) {
                System.out.println("Would you like to add another feature? (yes/no)");
                String response = scanner.next();
                addMore = response.equalsIgnoreCase("yes");
            }
        }

        // Crear un plan de membresía con las características seleccionadas
        MembershipPlan membershipPlan = new MembershipPlan(selectedMembership, features);

        // 3. Calcular el costo total
        membershipPlan.calculateTotalCost();

        // Mostrar el costo después de los descuentos y recargos
        double finalCost = membershipPlan.getTotalCost();
        System.out.println("Final cost after discounts: $" + finalCost);
    }
}
